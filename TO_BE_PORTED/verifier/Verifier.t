/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2009, INRIA
 * Nancy, France.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 *
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Antoine Reilles        e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.engine.verifier;

import tom.engine.*;
import aterm.*;
import java.util.*;
import tom.engine.tools.SymbolTable;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.il.*;
import tom.engine.adt.il.types.*;
import tom.library.sl.Strategy;
import tom.library.sl.VisitFailure;
import tom.library.sl.Introspector;
import tom.library.sl.AbstractStrategyBasic;

import tom.engine.exception.TomRuntimeException;

public class Verifier {

  // ------------------------------------------------------------
  %include { ../adt/tomsignature/TomSignature.tom }
  %include { ../adt/il/Il.tom }
  %include { ../../library/mapping/java/sl.tom }
  %include { ../../library/mapping/java/util/types/List.tom}
  %typeterm Collection {
    implement { java.util.Collection }
    is_sort(t) { ($t instanceof java.util.Collection) }
  }
  %typeterm Map {
    implement { java.util.Map }
    is_sort(t) { ($t instanceof java.util.Map) }
  }
  // ------------------------------------------------------------

  private SymbolTable symbolTable;
  private boolean camlsemantics = false;

  public Verifier(boolean camlsemantics) {
    super();
    this.camlsemantics = camlsemantics;
  }

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

  public boolean isCamlSemantics() {
    return camlsemantics;
  }

  public BQTerm termFromBQTerm(BQTerm tomterm) {
    %match(tomterm) {
      ExpressionToBQTerm(expr) -> {
        return `termFromExpresssion(expr);
      }
      BQVariable[AstName=name] -> {
        return `termFromTomName(name);
      }
    }
    System.out.println("termFromBQTerm don't know how to handle this: " + tomterm);
    return `repr("foirade");
  }

  Variable variableFromTomName(TomName name) {
    %match(name) {
      Name(stringname) -> {
        return `var(stringname);
      }
      PositionName(numberlist) -> {
        return `var(TomBase.tomNumberListToString(numberlist));
      }
      EmptyName() -> {
        return `var("emptyName");
      }
    }
    return `var("error while building variable name");
  }

  Term termFromTomName(TomName name) {
    return `tau(absvar(variableFromTomName(name)));
  }

  public Term termFromExpresssion(Expression expression) {
    %match(expression) {
      GetSlot[AstName=Name(symbolName),SlotNameString=slotName,Variable=tomterm] -> {
        Term term = termFromBQTerm(`tomterm);
        return `slot(fsymbol(symbolName),term,slotName);
      }
      BQTermToExpression(BQVariable[AstName=name]) -> {
        Term term = termFromTomName(`name);
        return `term;
      }
      Cast[Source=expr] -> {
        return termFromExpresssion(`expr);
      }
    }
    System.out.println("termFromExpression don't know how to handle this: " + expression);
    return `repr("autre foirade avec " + expression);
  }

  public Expr exprFromExpression(Expression expression) {
    %match(expression) {
      TrueTL()  -> { return `iltrue(subs(undefsubs())); }
      FalseTL() -> { return `ilfalse(); }
      IsFsym[AstName=Name(symbolName),Variable=bqterm] -> {
        Term term = termFromBQTerm(`bqterm);
        return `isfsym(term,fsymbol(symbolName));
      }
      EqualTerm[Kid1=t1,Kid2=t2] -> {
        return `eq(termFromBQTerm(t1),termFromBQTerm(t2));
      }
      IsSort[] -> { return `iltrue(subs(undefsubs())); }
    }
    System.out.println("exprFromExpression don't know how to handle this: " + expression);
    return `ilfalse();
  }

  public Instr instrFromInstructionList(InstructionList instrlist) {
    InstrList list = `semicolon();
    while (!instrlist.isEmptyconcInstruction()) {
      Instruction i = (Instruction) instrlist.getHeadconcInstruction();
      instrlist = instrlist.getTailconcInstruction();
      list = `semicolon(list*,instrFromInstruction(i));
    }
    return `sequence(list);
  }

  public Instr instrFromInstruction(Instruction automata) {
    %match(automata) {
      TypedAction[PositivePattern=positivePattern,NegativePatternList=negativePatternList] -> {
        return `accept(positivePattern.toATerm(),negativePatternList.toATerm());
      }

      If(cond,ift,iff) -> {
        return `ITE(exprFromExpression(cond),
            instrFromInstruction(ift),
            instrFromInstruction(iff));
      }
      (Let|LetRef)(BQVariable[AstName=avar],expr,body) -> {
        Variable thevar = variableFromTomName(`avar);
        return `ILLet(thevar,
            termFromExpresssion(expr),
            instrFromInstruction(body));
      }
      Assign(BQVariable[AstName=avar],expr) -> {
        Variable thevar = variableFromTomName(`avar);
        return `ILLet(thevar,
            termFromExpresssion(expr),
            refuse()); /* check that refuse is correct here */
      }
      CompiledPattern[AutomataInst=instr] -> {
        return instrFromInstruction(`instr);
      }
      AbstractBlock(concInstruction(instr)) -> {
        return instrFromInstruction(`instr);
      }
      AbstractBlock(concInstruction(instrlist*)) -> {
        return instrFromInstructionList(`instrlist);
      }
      Nop() -> {
        // tom uses nop in the iffalse part of ITE
        return `refuse();
      }
    }
    System.out.println("instrFromInstruction don't know how to handle this : " + automata);
    return `refuse();
  }

  private SubstitutionList abstractSubstitutionFromAccept(Instr instr) {
    SubstitutionList substitution = `subs();
    %match(instr) {
      accept(positive,_) -> {
        Constraint positivePattern = Constraint.fromTerm(`positive);
        ArrayList<TomTerm> subjectList = new ArrayList<TomTerm>();
        try{
          `TopDown(CollectSubjects(subjectList)).visitLight(positivePattern);
        }catch(VisitFailure e){
          throw new TomRuntimeException("VisitFailure in Verifier.abstractSubstitutionFromAccept:" + e.getMessage()); 
        }
        for(TomTerm subject: subjectList){
          %match(subject) {
            Variable[AstName=name] -> {
              substitution = `subs(substitution*,
                  is(
                    variableFromTomName(name),
                    termFromTomTerm(subject)));
            }
          }          
        }
      }
    }
    return substitution;
  }

  %strategy CollectSubjects(List list) extends Identity(){
    visit Constraint {
      MatchConstraint(_,s) -> {
        list.add(`s);
      }
    }
  }

  public Collection build_tree(Instruction automata) {
    // System.out.println("Build derivation tree for: " + automata);

    // collects the accept in the automata
    Collection localAccepts = collectAccept(automata);

    Iterator iter = localAccepts.iterator();
    Collection treeList = new HashSet();
    while(iter.hasNext()) {
      Instr localAccept = (Instr) iter.next();

      // builds the initial abstract substitution
      SubstitutionList initialsubstitution = abstractSubstitutionFromAccept(localAccept);
      Environment startingenv = `env(initialsubstitution,
          instrFromInstruction(automata));

      Deriv startingderiv = `ebs(startingenv,
          env(subs(undefsubs()),localAccept));

      Collection treeListPre = applySemanticsRules(startingderiv);
      // replace substitutions in trees
      Iterator it = treeListPre.iterator();
      while(it.hasNext()) {
        DerivTree tree = (DerivTree) it.next();
        SubstitutionList outputsubst = getOutputSubstitution(tree);
        tree = replaceUndefinedSubstitution(tree,outputsubst);
        treeList.add(tree);
      }
    }

    return treeList;
  }

  public Map<Instr,Expr> getConstraints(Instruction automata) {
    // collects the accept in the automata
    Collection localAccepts = collectAccept(automata);

    Iterator iter = localAccepts.iterator();
    Map<Instr,Expr> constraintList = new HashMap<Instr,Expr>();
    while(iter.hasNext()) {
      Instr localAccept = (Instr) iter.next();

      // builds the initial abstract substitution
      SubstitutionList initialsubstitution = abstractSubstitutionFromAccept(localAccept);
      Expr constraints = buildConstraint(initialsubstitution,
          instrFromInstruction(automata),
          localAccept);
      constraintList.put(localAccept,constraints);
    }
    return constraintList;
  }

  %strategy substitutionCollector(outsubst:SubstRef) extends `Identity() {
    visit Expr {
      t@iltrue(subs(undefsubs())) -> {
        `Fail().visitLight(`t);
      }
      iltrue(x) -> {
        // outsubst.set(`x);
        outsubst.set(`x);
      }
    }
  }
  public SubstitutionList collectSubstitutionInConstraint(Expr expr) {
    SubstRef output = new SubstRef(`subs());
    try {
      `mu(MuVar("x"),Try(Sequence(substitutionCollector(output),All(MuVar("x"))))).visitLight(expr);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy substitutionCollector failed");
    }
    return output.get();
  }

  %strategy outputSubstitutionCollector(outsubst:SubstRef) extends `Identity() {
    visit Deriv {
      ebs(env(e,accept[]),env(subs(undefsubs()),accept[])) -> {
        outsubst.set(`e);
      }
    }
  }

  public SubstitutionList getOutputSubstitution(DerivTree subject) {
    SubstRef output = new SubstRef(`subs());
    try {
      `TopDown(outputSubstitutionCollector(output)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy outputSubstitutionCollector failed");
    }
    return output.get();
  }

  %strategy acceptCollector(store:Collection) extends `Identity() {
    visit Instruction {
      TypedAction[PositivePattern=positive,NegativePatternList=negative]  -> {
        store.add(`accept(positive.toATerm(),negative.toATerm()));
      }
    }
  }

  public Collection collectAccept(Instruction subject) {
    Collection result = new HashSet();
    try {
      `TopDown(acceptCollector(result)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy collectAccept failed");
    }
    return result;
  }


  /**
   * The axioms the mapping has to verify
   */
  protected Seq seqFromTerm(Term sp) {
    TermList ded = `concTerm(sp);
    %match(sp) {
      appSubsT[] -> {
        TermList follow = applyMappingRules(replaceVariablesInTerm(sp));
        ded = `concTerm(ded*,follow*);
      }
    }
    return `dedterm(concTerm(ded*));
  }

  protected ExprList exprListFromExpr(Expr sp) {
    ExprList ded = `concExpr(sp);
    %match(sp) {
      appSubsE[] -> {
        ExprList follow = applyExprRules(replaceVariablesInExpr(sp));
        ded = `concExpr(ded*,follow*);
      }
    }

    // System.out.println("dedexpr gives: " + ded);
    return `ded;
  }

  protected SubstitutionList reduceSubstitutionWithMappingRules(SubstitutionList subst) {
    %match(subst) {
      subs() -> {
        return subst;
      }
      subs(is(v,term),t*) -> {
        SubstitutionList tail = reduceSubstitutionWithMappingRules(`t*);
        return `subs(is(v,reduceTermWithMappingRules(replaceVariablesInTerm(appSubsT(tail*,term)))),tail*);
      }
      subs(undefsubs(),t*) -> {
        SubstitutionList tail = reduceSubstitutionWithMappingRules(`t*);
        return `subs(undefsubs(),tail*);
      }
    }
    return subst;
  }
  protected Expr reduceWithMappingRules(Expr ex) {
    %match(ex) {
      eq(tau(tl),tau(tr)) -> {
        return `teq(tl,tr);
      }
      isfsym(tau(t),symbol) -> {
        return `tisfsym(t,symbol);
      }
      eq(lt,rt) -> {
        // first reduce the argument
        return reduceWithMappingRules(`eq(reduceTermWithMappingRules(lt),reduceTermWithMappingRules(rt)));
      }
      isfsym(t,symbol) -> {
        return reduceWithMappingRules(`isfsym(reduceTermWithMappingRules(t),symbol));
      }
      ilnot(e) -> {
        return `ilnot(reduceWithMappingRules(e));
      }
      iltrue[Subst=substitutionList] -> {
        return `iltrue(reduceSubstitutionWithMappingRules(substitutionList));
      }
      ilfalse[] -> {
        return `ex;
      }
      iland(lt,rt) -> {
        return `iland(reduceWithMappingRules(lt),reduceWithMappingRules(rt));
      }
      ilor(lt,rt) -> {
        return `ilor(reduceWithMappingRules(lt),reduceWithMappingRules(rt));
      }
    }
    System.out.println("reduceWithMappingRules : nothing applies to:" + ex);
    return `ex;
  }

  protected Term reduceTermWithMappingRules(Term trm) {
    %match(trm) {
      tau[] -> {
        return `trm;
      }
      subterm(s,t@subterm[],index) -> {
        return `reduceTermWithMappingRules(subterm(s,reduceTermWithMappingRules(t),index));
      }
      slot(s,t@slot[],slotName) -> {
        return `reduceTermWithMappingRules(slot(s,reduceTermWithMappingRules(t),slotName));
      }
      subterm(s,tau(t),index) -> {
        // we shall test if term t has symbol s
        AbsTerm term = `st(s,t,index);
        return `tau(term);
      }
      slot(s,tau(t),slotName) -> {
        // we shall test if term t has symbol s
        AbsTerm term = `sl(s,t,slotName);
        return `tau(term);
      }
    }
    System.out.println("reduceTermWithMappingRules : nothing applies to:" + trm);
    return `trm;
  }

  protected TermList applyMappingRules(Term trm) {
    %match(trm) {
      tau[] -> {
        return `concTerm(trm);
      }
      subterm(s,t@subterm[],index) -> {
        // first reduce the argument
        TermList reduced = applyMappingRules(`t);
        TermList res = `concTerm(trm);
        while(!reduced.isEmptyconcTerm()) {
          Term head = reduced.getHeadconcTerm();
          if (head.istau()) {
            TermList hl = applyMappingRules(head);
            while(!hl.isEmptyconcTerm()) {
              Term h = hl.getHeadconcTerm();
              res = `concTerm(res*,subterm(s,h,index));
              hl = hl.getTailconcTerm();
            }
          } else {
            res = `concTerm(res*,subterm(s,head,index));
          }
          reduced = reduced.getTailconcTerm();
        }
        return `concTerm(res*);
      }
      slot(s,t@slot[],slotName) -> {
        // first reduce the argument
        TermList reduced = applyMappingRules(`t);
        TermList res = `concTerm(trm);
        while(!reduced.isEmptyconcTerm()) {
          Term head = reduced.getHeadconcTerm();
          if (head.istau()) {
            TermList hl = applyMappingRules(head);
            while(!hl.isEmptyconcTerm()) {
              Term h = hl.getHeadconcTerm();
              res = `concTerm(res*,slot(s,h,slotName));
              hl = hl.getTailconcTerm();
            }
          } else {
            res = `concTerm(res*,slot(s,head,slotName));
          }
          reduced = reduced.getTailconcTerm();
        }
        return `concTerm(res*);
      }
      subterm(s,tau(t),index) -> {
        // we shall test if term t has symbol s
        AbsTerm term = `st(s,t,index);
        return `concTerm(trm,tau(term));
      }
      slot(s,tau(t),slotName) -> {
        // we shall test if term t has symbol s
        AbsTerm term = `sl(s,t,slotName);
        return `concTerm(trm,tau(term));
      }
    }
    System.out.println("apply TermRules : nothing applies to:" + trm);
    return `concTerm(trm);
  }

  protected ExprList applyExprRules(Expr ex) {
    %match(ex) {
      eq(tau(tl),tau(tr)) -> {
        return `concExpr(ex,teq(tl,tr));
      }
      isfsym(tau(t),symbol) -> {
        return `concExpr(ex,tisfsym(t,symbol));
      }
      eq(lt,rt) -> {
        // first reduce the argument
        Term reducedl = applyMappingRules(`lt).reverse().getHeadconcTerm();
        Term reducedr = applyMappingRules(`rt).reverse().getHeadconcTerm();

        ExprList taill = `applyExprRules(eq(reducedl,reducedr));
        ExprList res = `concExpr(ex,taill*);
      }
      isfsym(t,symbol) -> {
        // first reduce the argument
        TermList reduced = applyMappingRules(`t);
        ExprList res = `concExpr(ex);
        while(!reduced.isEmptyconcTerm()) {
          Term head = reduced.getHeadconcTerm();
          res = `concExpr(res*,isfsym(head,symbol));
          reduced = reduced.getTailconcTerm();
        }
        %match(res) {
          concExpr(hl*,tail) -> {
            ExprList taill = `applyExprRules(tail);
            return `concExpr(hl*,taill*);
          }
        }
      }
      ilnot(e) -> {
        ExprList exprList = `applyExprRules(e);
        ExprList newExprList = `concExpr(ex);
        while(!exprList.isEmptyconcExpr()) {
          Expr localExpr = exprList.getHeadconcExpr();
          exprList = exprList.getTailconcExpr();
          newExprList = `concExpr(newExprList*,ilnot(localExpr));
        }
        return newExprList;
      }
      (iltrue|ilfalse)[] -> {
        return `concExpr(ex);
      }
    }
    System.out.println("apply ExprRules : nothing applies to:" + ex);
    return `concExpr(ex);
  }

  protected Expr buildConstraint(SubstitutionList substitution, Instr pil,Instr goal) {
    %match(pil) {
      sequence(semicolon(h,t*)) -> {
        Expr goalFromHead = buildConstraint(substitution,`h,goal);
        if (!`t.isEmptysemicolon()) {
          Expr refuseFromHead = buildConstraint(substitution,`h,`refuse());
          Expr goalFromTail = buildConstraint(substitution,`sequence(t),goal);
          if(this.isCamlSemantics()) {
            return `ilor(goalFromHead,iland(refuseFromHead,goalFromTail));
          } else {
            return `ilor(goalFromHead,goalFromTail);
          }
        } else {
          return goalFromHead;
        }
      }
      ILLet(x,u,i) -> {
        // update the substitution
        Term t = replaceVariablesInTerm(`appSubsT(substitution,u));
        substitution = `subs(substitution*,is(x,t));
        //return `iland(eq(tau(absvar(x)),u),buildConstraint(substitution,i,goal));
        return `buildConstraint(substitution,i,goal);
      }
      ITE(exp,ift,iff) -> {
        Expr closedExpr = replaceVariablesInExpr(`appSubsE(substitution,exp));
        Expr constraintTrue  = `iland(closedExpr,buildConstraint(substitution,ift,goal));
        Expr constraintFalse = `iland(ilnot(closedExpr),buildConstraint(substitution,iff,goal));
        return `ilor(constraintTrue,constraintFalse);
      }
      refuse[] -> {
        if (pil == goal) {
          return `iltrue(subs(undefsubs()));
        } else {
          return `ilfalse();
        }
      }
      accept[] -> {
        if (pil == goal) {
          return `iltrue(substitution);
        } else {
          return `ilfalse();
        }
      }
    }
    // default case, should not happen
    return `ilfalse();
  }

  protected Collection applySemanticsRules(Deriv post) {
    Collection c = new HashSet();
    %match(post) {
      ebs(env(e,sequence(semicolon(h,t*))),env(subs(undefsubs()),ip)) -> {
        if(`instructionContains(h,ip)) {
          // ends the derivation
          Deriv up = `ebs(env(e,h),env(subs(undefsubs()),ip));
          Collection pre_list = applySemanticsRules(up);

          Iterator it = pre_list.iterator();
          while(it.hasNext()) {
            DerivTree pre = (DerivTree) it.next();
            c.add(`derivrule("seqa",post,pre,seq()));
          }
        } else {
          // continue the derivation with t
          Deriv up = `ebs(env(e,sequence(t*)),env(subs(undefsubs()),ip));
          Collection post_list = applySemanticsRules(up);

          if(this.isCamlSemantics()) {
            up = `ebs(env(e,h),env(subs(undefsubs()),refuse()));
            Collection pre_list = applySemanticsRules(up);
            Iterator it = pre_list.iterator();
            while(it.hasNext()) {
              DerivTree pre = (DerivTree) it.next();
              Iterator it2 = post_list.iterator();
              while(it2.hasNext()) {
                DerivTree pre2 = (DerivTree) it2.next();
                c.add(`derivrule2("seqb",post,pre,pre2,seq()));
              }
            }
          } else {
            Iterator it = post_list.iterator();
            while(it.hasNext()) {
              DerivTree pre = (DerivTree) it.next();
              c.add(`derivrule2("seqb",post,endderiv(),pre,seq()));
            }
          }
        }
      }
      // let rule
      ebs(env(e,ILLet(x,u,i)),env(subs(undefsubs()),ip)) -> {
        // build condition
        Seq cond = seqFromTerm(`appSubsT(e,u));
        // find "t"
        Term t = null;
        %match(cond) {
          dedterm(concTerm(_*,r)) -> { t = `r; }
          _ -> { if (t == null) {
            System.out.println("seqFromTerm has a problem with " + cond);
          }
          }
        }
        Deriv up = `ebs(
            env(subs(e*,is(x,t)),i),
            env(subs(undefsubs()),ip)
            );
        Collection pre_list = applySemanticsRules(up);
        Iterator it = pre_list.iterator();
        while(it.hasNext()) {
          DerivTree pre = (DerivTree) it.next();
          c.add(`derivrule("let",post,pre,cond));
        }
      }
      // iftrue/iffalse rule
      ebs(env(e,ITE(exp,ift,iff)),env(subs(undefsubs()),ip)) -> {
        // build condition
        ExprList cond = exprListFromExpr(`appSubsE(e,exp));

        Deriv up = `ebs(env(e,ift),env(subs(undefsubs()),ip));
        String rulename = "iftrue";

        Collection pre_list = applySemanticsRules(up);
        Iterator it = pre_list.iterator();
        while(it.hasNext()) {
          DerivTree pre = (DerivTree) it.next();
          c.add(`derivrule(rulename,post,pre,dedexpr(concExpr(cond*,iltrue(subs(undefsubs()))))));
        }

        up = `ebs(env(e,iff),env(subs(undefsubs()),ip));
        rulename = "iffalse";

        pre_list = applySemanticsRules(up);
        it = pre_list.iterator();
        while(it.hasNext()) {
          DerivTree pre = (DerivTree) it.next();
          c.add(`derivrule(rulename,post,pre,dedexpr(concExpr(cond*,ilfalse()))));
        }
      }
      // axioms
      ebs(env(_,accept[]),env(subs(undefsubs()),accept[])) -> {
        c.add(`derivrule("axiom_accept",post,endderiv(),seq()));
      }
      ebs(env(_,refuse[]),env(subs(undefsubs()),refuse[])) -> {
        c.add(`derivrule("axiom_refuse",post,endderiv(),seq()));
      }
      _ -> {
        if (c.isEmpty()) {
          //System.out.println("Error " + post);
        }
      }
    }
    return c;
  }

  %strategy stratInstructionContains(goal:Instr,c:Collection) extends `Identity() {
    visit Instr {
      x -> {
        if (`x == goal) {
          c.add(goal);
          `Fail().visitLight(`x);
        }
      }
    }
  }
  protected boolean instructionContains(Instr i, Instr goal) {
    Collection collect = new HashSet();
    try {
      `mu(MuVar("x"),Try(Sequence(stratInstructionContains(goal,collect),All(MuVar("x"))))).visitLight(i);
    } catch(tom.library.sl.VisitFailure e) {
      System.out.println("strategy instructionContains failed");
    }
    return !collect.isEmpty();
  }

  /**
   * To replace undefsubst in tree by the computed value
   * which leads to axiom
   */
  %strategy replaceUndefsubs(arg:SubstitutionList) extends `Identity() {
    visit SubstitutionList {
      (undefsubs()) -> {
        return arg;
      }
    }
  }

  private DerivTree replaceUndefinedSubstitution(DerivTree subject,
      SubstitutionList subs) {
    try {
      subject = (DerivTree) `TopDown(replaceUndefsubs(subs)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy replaceUndefsubs failed");
    }
    return subject;
  }

  %typeterm SubstRef {
    implement { SubstRef }
    is_sort(t) { ($t instanceof SubstRef) }
  }

  private class SubstRef {
    private SubstitutionList sublist;
    public SubstRef(SubstitutionList slist) {
      sublist = slist;
    }
    public void set(SubstitutionList ssublist) {
      this.sublist = ssublist;
    }
    public SubstitutionList get() {
      return sublist;
    }
  }

  /**
   * These functions deals with substitution application
   */
  %strategy replaceVariableByTerm(map:Map) extends `Identity() {
    visit Term {
      t@tau(absvar(v@var[])) -> {
        if (map.containsKey(`v)) {
          return (Term)map.get(`v);
        }
        return `t;
      }
    }
  }

  public Term replaceVariablesInTerm(Term subject) {
    %match(subject) {
      appSubsT(sublist,term) -> {
        Map map = buildVariableMap(`sublist, new HashMap());
        Term t = `term;
        try {
          t = (Term) `TopDown(replaceVariableByTerm(map)).visitLight(`term);
        } catch (tom.library.sl.VisitFailure e) {
          throw new TomRuntimeException("Strategy replaceVariableByTerm failed");
        }
        return t;
      }
    }
    return subject;
  }

  public Expr replaceVariablesInExpr(Expr subject) {
    %match(subject) {
      appSubsE(sublist,term) -> {
        Map map = buildVariableMap(`sublist, new HashMap());
        Expr t = `term;
        try {
          t = (Expr) `TopDown(replaceVariableByTerm(map)).visitLight(`term);
        } catch (tom.library.sl.VisitFailure e) {
          throw new TomRuntimeException("Strategy replaceVariableByTerm failed");
        }
        return t;
      }
    }
    return subject;
  }

  private Map buildVariableMap(SubstitutionList sublist, Map map) {
    %match(SubstitutionList sublist) {
      ()                -> { return map; }
      (undefsubs(),t*)  -> { return buildVariableMap(`t,map);}
      (is(v,term),t*)   -> {
        map.put(`v,`term);
        return buildVariableMap(`t,map);
      }
    }
    return null;
  }

  public void mappingReduce(Map input) {
    Iterator it = input.keySet().iterator();
    while(it.hasNext()) {
      Object key = it.next();
      Expr value = (Expr) input.get(key);
      input.put(key,reduceWithMappingRules(value));
    }
  }

  public void booleanReduce(Map input) {
    Iterator it = input.keySet().iterator();
    while(it.hasNext()) {
      Object key = it.next();
      Expr value = (Expr) input.get(key);
      input.put(key,booleanSimplify(value));
    }
  }

  public Expr booleanSimplify(Expr expr) {
    Strategy booleanSimplifier = new BooleanSimplifier();
    Expr res = `ilfalse();
    try {
      res = (Expr) `InnermostId(booleanSimplifier).visitLight(expr);
    } catch (tom.library.sl.VisitFailure e) {
      System.out.println("humm");
    }
    return res;
  }

  public class BooleanSimplifier extends AbstractStrategyBasic {
    public BooleanSimplifier() {
      super(`Identity());
    }

    public Object visitLight(Object o, Introspector i) throws tom.library.sl.VisitFailure {
      if (o instanceof Expr) {
        Expr arg = (Expr) o;
        %match(arg) {
          iland(ilfalse(),_) -> {
            return `ilfalse();
          }
          iland(_,ilfalse()) -> {
            return `ilfalse();
          }
          ilor(lt@iltrue[],_) -> {
            return `lt;
          }
          ilor(_,lt@iltrue[]) -> {
            return `lt;
          }
          ilor(ilfalse(),right) -> {
            return `right;
          }
          ilor(left,ilfalse()) -> {
            return `left;
          }
          ilnot(iltrue[]) -> {
            return `ilfalse();
          }
          ilnot(ilfalse[]) -> {
            return `iltrue(subs());
          }
        }
      }
      return any.visitLight(o,i);
    }
  }

}