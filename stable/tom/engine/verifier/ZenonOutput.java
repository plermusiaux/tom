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
import aterm.pure.*;
import java.util.*;

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

import tom.engine.adt.il.*;
import tom.engine.adt.il.types.*;
import tom.engine.adt.zenon.*;
import tom.engine.adt.zenon.types.*;

import tom.engine.exception.TomRuntimeException;


public class ZenonOutput {

  // ------------------------------------------------------------
          private static   tom.engine.adt.il.types.ExprList  tom_append_list_concExpr( tom.engine.adt.il.types.ExprList l1,  tom.engine.adt.il.types.ExprList  l2) {     if( l1.isEmptyconcExpr() ) {       return l2;     } else if( l2.isEmptyconcExpr() ) {       return l1;     } else if(  l1.getTailconcExpr() .isEmptyconcExpr() ) {       return  tom.engine.adt.il.types.exprlist.ConsconcExpr.make( l1.getHeadconcExpr() ,l2) ;     } else {       return  tom.engine.adt.il.types.exprlist.ConsconcExpr.make( l1.getHeadconcExpr() ,tom_append_list_concExpr( l1.getTailconcExpr() ,l2)) ;     }   }   private static   tom.engine.adt.il.types.ExprList  tom_get_slice_concExpr( tom.engine.adt.il.types.ExprList  begin,  tom.engine.adt.il.types.ExprList  end, tom.engine.adt.il.types.ExprList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcExpr()  ||  (end== tom.engine.adt.il.types.exprlist.EmptyconcExpr.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.il.types.exprlist.ConsconcExpr.make( begin.getHeadconcExpr() ,( tom.engine.adt.il.types.ExprList )tom_get_slice_concExpr( begin.getTailconcExpr() ,end,tail)) ;   }      private static   tom.engine.adt.il.types.SubstitutionList  tom_append_list_subs( tom.engine.adt.il.types.SubstitutionList l1,  tom.engine.adt.il.types.SubstitutionList  l2) {     if( l1.isEmptysubs() ) {       return l2;     } else if( l2.isEmptysubs() ) {       return l1;     } else if(  l1.getTailsubs() .isEmptysubs() ) {       return  tom.engine.adt.il.types.substitutionlist.Conssubs.make( l1.getHeadsubs() ,l2) ;     } else {       return  tom.engine.adt.il.types.substitutionlist.Conssubs.make( l1.getHeadsubs() ,tom_append_list_subs( l1.getTailsubs() ,l2)) ;     }   }   private static   tom.engine.adt.il.types.SubstitutionList  tom_get_slice_subs( tom.engine.adt.il.types.SubstitutionList  begin,  tom.engine.adt.il.types.SubstitutionList  end, tom.engine.adt.il.types.SubstitutionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptysubs()  ||  (end== tom.engine.adt.il.types.substitutionlist.Emptysubs.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.il.types.substitutionlist.Conssubs.make( begin.getHeadsubs() ,( tom.engine.adt.il.types.SubstitutionList )tom_get_slice_subs( begin.getTailsubs() ,end,tail)) ;   }      private static   tom.engine.adt.il.types.TermList  tom_append_list_concTerm( tom.engine.adt.il.types.TermList l1,  tom.engine.adt.il.types.TermList  l2) {     if( l1.isEmptyconcTerm() ) {       return l2;     } else if( l2.isEmptyconcTerm() ) {       return l1;     } else if(  l1.getTailconcTerm() .isEmptyconcTerm() ) {       return  tom.engine.adt.il.types.termlist.ConsconcTerm.make( l1.getHeadconcTerm() ,l2) ;     } else {       return  tom.engine.adt.il.types.termlist.ConsconcTerm.make( l1.getHeadconcTerm() ,tom_append_list_concTerm( l1.getTailconcTerm() ,l2)) ;     }   }   private static   tom.engine.adt.il.types.TermList  tom_get_slice_concTerm( tom.engine.adt.il.types.TermList  begin,  tom.engine.adt.il.types.TermList  end, tom.engine.adt.il.types.TermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTerm()  ||  (end== tom.engine.adt.il.types.termlist.EmptyconcTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.il.types.termlist.ConsconcTerm.make( begin.getHeadconcTerm() ,( tom.engine.adt.il.types.TermList )tom_get_slice_concTerm( begin.getTailconcTerm() ,end,tail)) ;   }       private static   tom.engine.adt.zenon.types.ZAxiomList  tom_append_list_zby( tom.engine.adt.zenon.types.ZAxiomList l1,  tom.engine.adt.zenon.types.ZAxiomList  l2) {     if( l1.isEmptyzby() ) {       return l2;     } else if( l2.isEmptyzby() ) {       return l1;     } else if(  l1.getTailzby() .isEmptyzby() ) {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,l2) ;     } else {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,tom_append_list_zby( l1.getTailzby() ,l2)) ;     }   }   private static   tom.engine.adt.zenon.types.ZAxiomList  tom_get_slice_zby( tom.engine.adt.zenon.types.ZAxiomList  begin,  tom.engine.adt.zenon.types.ZAxiomList  end, tom.engine.adt.zenon.types.ZAxiomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyzby()  ||  (end== tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( begin.getHeadzby() ,( tom.engine.adt.zenon.types.ZAxiomList )tom_get_slice_zby( begin.getTailzby() ,end,tail)) ;   }    






  // ------------------------------------------------------------

  private Verifier verifier;
  private TomIlTools tomiltools;

  public ZenonOutput(Verifier verifier) {
    this.verifier = verifier;
    this.tomiltools = new TomIlTools(verifier);
  }

  public Collection<ZSpec> zspecSetFromConstraintMap(Map<Instr,Expr> constraintMap) {
    Collection<ZSpec> resset = new HashSet<ZSpec>();
    for (Map.Entry<Instr,Expr> entry : constraintMap.entrySet()) {
      ZSpec spec = zspecFromMapEntry(entry);
      resset.add(spec);
    }
    return resset;
  }

  public ZSpec zspecFromMapEntry(Map.Entry<Instr,Expr> entry) {
    Instr accept = entry.getKey();
    Expr constraint = entry.getValue();

    List<ZTerm> subjectList = new LinkedList<ZTerm>();
    ZExpr pattern = null;
    ZExpr negpattern = null;

    // theorem to prove
    {{if ( (accept instanceof tom.engine.adt.il.types.Instr) ) {if ( ((( tom.engine.adt.il.types.Instr )accept) instanceof tom.engine.adt.il.types.instr.accept) ) {

        Constraint positivePattern = Constraint.fromTerm( (( tom.engine.adt.il.types.Instr )accept).getPositive() );
        ConstraintList negativePatternList = ConstraintList.fromTerm( (( tom.engine.adt.il.types.Instr )accept).getNegative() );
        // we need the substitution to generate the pattern part of the theorem
        SubstitutionList subsList = verifier.collectSubstitutionInConstraint(constraint);
        Map<String,ZTerm> variableMap = ztermVariableMapFromSubstitutionList(
                                          subsList,
                                          new HashMap<String,ZTerm>());
        tomiltools.getZTermSubjectListFromConstraint(positivePattern,
                                                     subjectList,
                                                     variableMap);
        pattern = tomiltools.constraintToZExpr(positivePattern,variableMap);
        if (verifier.isCamlSemantics()) {
          negpattern = tomiltools.constraintToZExpr(negativePatternList,variableMap);
        }
      }}}}


    ZExpr zenonConstraint = zexprFromExpr(constraint);

    ZExpr theorem = null;
    if (pattern != null && zenonConstraint != null) {
      if(verifier.isCamlSemantics() && negpattern != null) {
        theorem =  tom.engine.adt.zenon.types.zexpr.zequiv.make( tom.engine.adt.zenon.types.zexpr.zand.make(pattern,  tom.engine.adt.zenon.types.zexpr.znot.make(negpattern) ) , zenonConstraint) ;
      } else {
        theorem =  tom.engine.adt.zenon.types.zexpr.zequiv.make(pattern, zenonConstraint) ;
      }
    }

    // now we have to to build the axiom list, starting from the
    // signature. Again, the TomIlTools will be useful, it has access
    // to TomSignature and Zenon signature

    // collects symbols in pattern
    Collection<String> symbols = tomiltools.collectSymbols(pattern);
    // generates the axioms for this set of symbols
    ZAxiomList symbolsAxioms = tomiltools.symbolsDefinition(symbols);
    // generates axioms for all subterm operations
    ZAxiomList subtermAxioms = tomiltools.subtermsDefinition(symbols);

    for (ZTerm input : subjectList) {
      theorem =  tom.engine.adt.zenon.types.zexpr.zforall.make(input,  tom.engine.adt.zenon.types.ztype.ztype.make("T") , theorem) ;
    }
    ZSpec spec =  tom.engine.adt.zenon.types.zspec.zthm.make(theorem, tom_append_list_zby(symbolsAxioms,tom_append_list_zby(subtermAxioms, tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ))) ;

    return spec;
  }

  ZTerm ztermFromTerm(Term term) {
    {{if ( (term instanceof tom.engine.adt.il.types.Term) ) {if ( ((( tom.engine.adt.il.types.Term )term) instanceof tom.engine.adt.il.types.term.tau) ) {

        return ztermFromAbsTerm( (( tom.engine.adt.il.types.Term )term).getAbst() );
      }}}{if ( (term instanceof tom.engine.adt.il.types.Term) ) {if ( ((( tom.engine.adt.il.types.Term )term) instanceof tom.engine.adt.il.types.term.repr) ) {

        return  tom.engine.adt.zenon.types.zterm.zvar.make("Error in ztermFromTerm repr") ;
      }}}{if ( (term instanceof tom.engine.adt.il.types.Term) ) {if ( ((( tom.engine.adt.il.types.Term )term) instanceof tom.engine.adt.il.types.term.subterm) ) {

        return  tom.engine.adt.zenon.types.zterm.zvar.make("Error in ztermFromTerm subterm") ;
      }}}{if ( (term instanceof tom.engine.adt.il.types.Term) ) {if ( ((( tom.engine.adt.il.types.Term )term) instanceof tom.engine.adt.il.types.term.slot) ) {

        return  tom.engine.adt.zenon.types.zterm.zvar.make("Error in ztermFromTerm "+ term+" slot") ;
      }}}{if ( (term instanceof tom.engine.adt.il.types.Term) ) {if ( ((( tom.engine.adt.il.types.Term )term) instanceof tom.engine.adt.il.types.term.appSubsT) ) {

        // probleme: la substitution devrait etre appliquee
        return  tom.engine.adt.zenon.types.zterm.zvar.make("Error in ztermFromTerm appsubsT ") ;
      }}}}

    return  tom.engine.adt.zenon.types.zterm.zvar.make("match vide dans ztermFromTerm") ;
  }

  ZExpr zexprFromExpr(Expr expr) {
    {{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.iltrue) ) {
 return  tom.engine.adt.zenon.types.zexpr.ztrue.make() ;}}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.ilfalse) ) {
 return  tom.engine.adt.zenon.types.zexpr.zfalse.make() ;}}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.tisfsym) ) {

        return  tom.engine.adt.zenon.types.zexpr.zisfsym.make(ztermFromAbsTerm( (( tom.engine.adt.il.types.Expr )expr).getAbst() ), zsymbolFromSymbol( (( tom.engine.adt.il.types.Expr )expr).getSymbol() )) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.teq) ) {

        return  tom.engine.adt.zenon.types.zexpr.zeq.make(ztermFromAbsTerm( (( tom.engine.adt.il.types.Expr )expr).getLabst() ), ztermFromAbsTerm( (( tom.engine.adt.il.types.Expr )expr).getRabst() )) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.isfsym) ) {

        // this should not occur
        return  tom.engine.adt.zenon.types.zexpr.zisfsym.make( tom.engine.adt.zenon.types.zterm.zvar.make("Error in zexprFromExpr") ,  tom.engine.adt.zenon.types.zsymbol.zsymbol.make("isfsym") ) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.eq) ) {

        // this should not occur
        return  tom.engine.adt.zenon.types.zexpr.zeq.make( tom.engine.adt.zenon.types.zterm.zvar.make("Error in zexprFromExpr") ,  tom.engine.adt.zenon.types.zterm.zvar.make("eq") ) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.appSubsE) ) {

        // this should not occur
        return  tom.engine.adt.zenon.types.zexpr.zeq.make( tom.engine.adt.zenon.types.zterm.zvar.make("Error in zexprFromExpr") ,  tom.engine.adt.zenon.types.zterm.zvar.make("appSubsE") ) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.iland) ) {

        return  tom.engine.adt.zenon.types.zexpr.zand.make(zexprFromExpr( (( tom.engine.adt.il.types.Expr )expr).getLeft() ), zexprFromExpr( (( tom.engine.adt.il.types.Expr )expr).getRight() )) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.ilor) ) {

        return  tom.engine.adt.zenon.types.zexpr.zor.make(zexprFromExpr( (( tom.engine.adt.il.types.Expr )expr).getLeft() ), zexprFromExpr( (( tom.engine.adt.il.types.Expr )expr).getRight() )) ;
      }}}{if ( (expr instanceof tom.engine.adt.il.types.Expr) ) {if ( ((( tom.engine.adt.il.types.Expr )expr) instanceof tom.engine.adt.il.types.expr.ilnot) ) {

        return  tom.engine.adt.zenon.types.zexpr.znot.make(zexprFromExpr( (( tom.engine.adt.il.types.Expr )expr).getExp() )) ;
      }}}}

    return  tom.engine.adt.zenon.types.zexpr.zeq.make( tom.engine.adt.zenon.types.zterm.zvar.make("Error in zexprFromExpr") ,  tom.engine.adt.zenon.types.zterm.zvar.make("end " + expr.toString()) ) ;
  }

  ZSymbol zsymbolFromSymbol(Symbol symb) {
    {{if ( (symb instanceof tom.engine.adt.il.types.Symbol) ) {if ( ((( tom.engine.adt.il.types.Symbol )symb) instanceof tom.engine.adt.il.types.symbol.fsymbol) ) {

        return  tom.engine.adt.zenon.types.zsymbol.zsymbol.make( (( tom.engine.adt.il.types.Symbol )symb).getName() ) ;
      }}}}

    return  tom.engine.adt.zenon.types.zsymbol.zsymbol.make("random") ;
  }

  ZExpr zexprFromSeq(Seq seq) {
    {{if ( (seq instanceof tom.engine.adt.il.types.Seq) ) {if ( ((( tom.engine.adt.il.types.Seq )seq) instanceof tom.engine.adt.il.types.seq.seq) ) {

        return  tom.engine.adt.zenon.types.zexpr.ztrue.make() ;
      }}}{if ( (seq instanceof tom.engine.adt.il.types.Seq) ) {if ( ((( tom.engine.adt.il.types.Seq )seq) instanceof tom.engine.adt.il.types.seq.dedterm) ) { tom.engine.adt.il.types.TermList  tom_termlist= (( tom.engine.adt.il.types.Seq )seq).getTerms() ;{{if ( (tom_termlist instanceof tom.engine.adt.il.types.TermList) ) {if ( (((( tom.engine.adt.il.types.TermList )tom_termlist) instanceof tom.engine.adt.il.types.termlist.ConsconcTerm) || ((( tom.engine.adt.il.types.TermList )tom_termlist) instanceof tom.engine.adt.il.types.termlist.EmptyconcTerm)) ) { tom.engine.adt.il.types.TermList  tomMatch345NameNumber_end_4=(( tom.engine.adt.il.types.TermList )tom_termlist);do {{if (!( tomMatch345NameNumber_end_4.isEmptyconcTerm() )) { tom.engine.adt.il.types.TermList  tomMatch345NameNumber_freshVar_5= tomMatch345NameNumber_end_4.getTailconcTerm() ;if (!( tomMatch345NameNumber_freshVar_5.isEmptyconcTerm() )) {if (  tomMatch345NameNumber_freshVar_5.getTailconcTerm() .isEmptyconcTerm() ) {



            return  tom.engine.adt.zenon.types.zexpr.zeq.make(ztermFromTerm( tomMatch345NameNumber_end_4.getHeadconcTerm() ), ztermFromTerm( tomMatch345NameNumber_freshVar_5.getHeadconcTerm() )) ;
          }}}if ( tomMatch345NameNumber_end_4.isEmptyconcTerm() ) {tomMatch345NameNumber_end_4=(( tom.engine.adt.il.types.TermList )tom_termlist);} else {tomMatch345NameNumber_end_4= tomMatch345NameNumber_end_4.getTailconcTerm() ;}}} while(!( (tomMatch345NameNumber_end_4==(( tom.engine.adt.il.types.TermList )tom_termlist)) ));}}}}

      }}}{if ( (seq instanceof tom.engine.adt.il.types.Seq) ) {if ( ((( tom.engine.adt.il.types.Seq )seq) instanceof tom.engine.adt.il.types.seq.dedexpr) ) { tom.engine.adt.il.types.ExprList  tom_exprlist= (( tom.engine.adt.il.types.Seq )seq).getExprs() ;{{if ( (tom_exprlist instanceof tom.engine.adt.il.types.ExprList) ) {if ( (((( tom.engine.adt.il.types.ExprList )tom_exprlist) instanceof tom.engine.adt.il.types.exprlist.ConsconcExpr) || ((( tom.engine.adt.il.types.ExprList )tom_exprlist) instanceof tom.engine.adt.il.types.exprlist.EmptyconcExpr)) ) { tom.engine.adt.il.types.ExprList  tomMatch346NameNumber_end_4=(( tom.engine.adt.il.types.ExprList )tom_exprlist);do {{if (!( tomMatch346NameNumber_end_4.isEmptyconcExpr() )) { tom.engine.adt.il.types.ExprList  tomMatch346NameNumber_freshVar_5= tomMatch346NameNumber_end_4.getTailconcExpr() ;if (!( tomMatch346NameNumber_freshVar_5.isEmptyconcExpr() )) {if ( ( tomMatch346NameNumber_freshVar_5.getHeadconcExpr()  instanceof tom.engine.adt.il.types.expr.iltrue) ) {if (  tomMatch346NameNumber_freshVar_5.getTailconcExpr() .isEmptyconcExpr() ) {



            return zexprFromExpr( tomMatch346NameNumber_end_4.getHeadconcExpr() );
          }}}}if ( tomMatch346NameNumber_end_4.isEmptyconcExpr() ) {tomMatch346NameNumber_end_4=(( tom.engine.adt.il.types.ExprList )tom_exprlist);} else {tomMatch346NameNumber_end_4= tomMatch346NameNumber_end_4.getTailconcExpr() ;}}} while(!( (tomMatch346NameNumber_end_4==(( tom.engine.adt.il.types.ExprList )tom_exprlist)) ));}}}}

      }}}{if ( (seq instanceof tom.engine.adt.il.types.Seq) ) {if ( ((( tom.engine.adt.il.types.Seq )seq) instanceof tom.engine.adt.il.types.seq.dedexpr) ) { tom.engine.adt.il.types.ExprList  tom_exprlist= (( tom.engine.adt.il.types.Seq )seq).getExprs() ;{{if ( (tom_exprlist instanceof tom.engine.adt.il.types.ExprList) ) {if ( (((( tom.engine.adt.il.types.ExprList )tom_exprlist) instanceof tom.engine.adt.il.types.exprlist.ConsconcExpr) || ((( tom.engine.adt.il.types.ExprList )tom_exprlist) instanceof tom.engine.adt.il.types.exprlist.EmptyconcExpr)) ) { tom.engine.adt.il.types.ExprList  tomMatch347NameNumber_end_4=(( tom.engine.adt.il.types.ExprList )tom_exprlist);do {{if (!( tomMatch347NameNumber_end_4.isEmptyconcExpr() )) { tom.engine.adt.il.types.ExprList  tomMatch347NameNumber_freshVar_5= tomMatch347NameNumber_end_4.getTailconcExpr() ;if (!( tomMatch347NameNumber_freshVar_5.isEmptyconcExpr() )) {if ( ( tomMatch347NameNumber_freshVar_5.getHeadconcExpr()  instanceof tom.engine.adt.il.types.expr.ilfalse) ) {if (  tomMatch347NameNumber_freshVar_5.getTailconcExpr() .isEmptyconcExpr() ) {



            return  tom.engine.adt.zenon.types.zexpr.znot.make(zexprFromExpr( tomMatch347NameNumber_end_4.getHeadconcExpr() )) ;
          }}}}if ( tomMatch347NameNumber_end_4.isEmptyconcExpr() ) {tomMatch347NameNumber_end_4=(( tom.engine.adt.il.types.ExprList )tom_exprlist);} else {tomMatch347NameNumber_end_4= tomMatch347NameNumber_end_4.getTailconcExpr() ;}}} while(!( (tomMatch347NameNumber_end_4==(( tom.engine.adt.il.types.ExprList )tom_exprlist)) ));}}}}

      }}}}

    return  tom.engine.adt.zenon.types.zexpr.ztrue.make() ;
  }

  ZTerm ztermFromAbsTerm(AbsTerm absterm) {
    {{if ( (absterm instanceof tom.engine.adt.il.types.AbsTerm) ) {if ( ((( tom.engine.adt.il.types.AbsTerm )absterm) instanceof tom.engine.adt.il.types.absterm.absvar) ) { tom.engine.adt.il.types.Variable  tomMatch348NameNumber_freshVar_1= (( tom.engine.adt.il.types.AbsTerm )absterm).getVarname() ;if ( (tomMatch348NameNumber_freshVar_1 instanceof tom.engine.adt.il.types.variable.var) ) {

        return  tom.engine.adt.zenon.types.zterm.zvar.make( tomMatch348NameNumber_freshVar_1.getName() ) ;
      }}}}{if ( (absterm instanceof tom.engine.adt.il.types.AbsTerm) ) {if ( ((( tom.engine.adt.il.types.AbsTerm )absterm) instanceof tom.engine.adt.il.types.absterm.st) ) {

        return  tom.engine.adt.zenon.types.zterm.zst.make(ztermFromAbsTerm( (( tom.engine.adt.il.types.AbsTerm )absterm).getAbst() ),  (( tom.engine.adt.il.types.AbsTerm )absterm).getIndex() ) ;
      }}}{if ( (absterm instanceof tom.engine.adt.il.types.AbsTerm) ) {if ( ((( tom.engine.adt.il.types.AbsTerm )absterm) instanceof tom.engine.adt.il.types.absterm.sl) ) {

        return  tom.engine.adt.zenon.types.zterm.zsl.make(ztermFromAbsTerm( (( tom.engine.adt.il.types.AbsTerm )absterm).getAbst() ),  (( tom.engine.adt.il.types.AbsTerm )absterm).getName() ) ;
      }}}}

    return  tom.engine.adt.zenon.types.zterm.zvar.make("Error in ztermFromAbsTerm") ;
  }

  private Map<String,ZTerm> ztermVariableMapFromSubstitutionList(
                              SubstitutionList sublist,
                              Map<String,ZTerm> map) {
    {{if ( (sublist instanceof tom.engine.adt.il.types.SubstitutionList) ) {if ( (((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Conssubs) || ((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Emptysubs)) ) {if ( (( tom.engine.adt.il.types.SubstitutionList )sublist).isEmptysubs() ) {
 return map; }}}}{if ( (sublist instanceof tom.engine.adt.il.types.SubstitutionList) ) {if ( (((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Conssubs) || ((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Emptysubs)) ) {if (!( (( tom.engine.adt.il.types.SubstitutionList )sublist).isEmptysubs() )) {if ( ( (( tom.engine.adt.il.types.SubstitutionList )sublist).getHeadsubs()  instanceof tom.engine.adt.il.types.substitution.undefsubs) ) {

        return ztermVariableMapFromSubstitutionList( (( tom.engine.adt.il.types.SubstitutionList )sublist).getTailsubs() ,map);
      }}}}}{if ( (sublist instanceof tom.engine.adt.il.types.SubstitutionList) ) {if ( (((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Conssubs) || ((( tom.engine.adt.il.types.SubstitutionList )sublist) instanceof tom.engine.adt.il.types.substitutionlist.Emptysubs)) ) {if (!( (( tom.engine.adt.il.types.SubstitutionList )sublist).isEmptysubs() )) { tom.engine.adt.il.types.Substitution  tomMatch349NameNumber_freshVar_13= (( tom.engine.adt.il.types.SubstitutionList )sublist).getHeadsubs() ;if ( (tomMatch349NameNumber_freshVar_13 instanceof tom.engine.adt.il.types.substitution.is) ) { tom.engine.adt.il.types.Variable  tomMatch349NameNumber_freshVar_11= tomMatch349NameNumber_freshVar_13.getVar() ;if ( (tomMatch349NameNumber_freshVar_11 instanceof tom.engine.adt.il.types.variable.var) ) {

        map.put( tomMatch349NameNumber_freshVar_11.getName() ,ztermFromTerm( tomMatch349NameNumber_freshVar_13.getTerm() ));
        return ztermVariableMapFromSubstitutionList( (( tom.engine.adt.il.types.SubstitutionList )sublist).getTailsubs() ,map);
      }}}}}}}

    throw new TomRuntimeException(
        "verifier: strange substitution list: " + sublist);
  }
}
