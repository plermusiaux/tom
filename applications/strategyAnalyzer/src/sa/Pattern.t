package sa;

import sa.rule.types.*;
import tom.library.sl.*;

public class Pattern {
  %include { rule/Rule.tom }
  %include { sl.tom }

  private static Signature eSig = new Signature();
  private static Signature gSig = new Signature();

  public static void main(String args[]) {
    Term V = `Var("_");
    Term t = null;
    GomType type = null;

    // example 1
    eSig.addSymbol("a", `ConcGomType(), `GomType("T") );
    eSig.addSymbol("b", `ConcGomType(), `GomType("T") );
    eSig.addSymbol("g", `ConcGomType(GomType("T")), `GomType("T") );
    eSig.addSymbol("f", `ConcGomType(GomType("T"),GomType("T")), `GomType("T") );

    gSig.addSymbol("a", `ConcGomType(), `GomType("T") );
    gSig.addSymbol("b", `ConcGomType(), `GomType("T") );
    gSig.addSymbol("g", `ConcGomType(GomType("T")), `GomType("T") );
    gSig.addSymbol("f", `ConcGomType(GomType("T"),GomType("T")), `GomType("T") );

    Term a =`Appl("a", TermList());
    Term ga =`Appl("g", TermList(a));
    Term gv =`Appl("g", TermList(V));
    //t = `Appl("f", TermList(Sub(gv,Add(TermList(a,ga)))));
    //t = `Appl("f", TermList(Sub(V,Add(TermList(a,ga,gv)))));

    Term fa =`Appl("f", TermList(a));
    Term fga =`Appl("f", TermList(ga));
    Term fgv =`Appl("f", TermList(gv));
    Term fv =`Appl("f", TermList(V));
    //t = `Sub(fgv, Add(TermList(fa,fga)));
    t = `Sub(fv, Add(TermList(fa,fga,fgv)));
    type = `GomType("T");

    // example 2
    eSig.addSymbol("Nil", `ConcGomType(), `GomType("List") );
    eSig.addSymbol("Cons", `ConcGomType(GomType("T"),GomType("List")), `GomType("List") );

    gSig.addSymbol("Nil", `ConcGomType(), `GomType("List") );
    gSig.addSymbol("Cons", `ConcGomType(GomType("T"),GomType("List")), `GomType("List") );
    gSig.addSymbol("sep", `ConcGomType(GomType("T"),GomType("List")), `GomType("List") );

    Term y_ys = `Appl("Cons", TermList(V,V));
    Term x_y_ys = `Appl("Cons", TermList(V,y_ys));
    Term sep1 = `Appl("sep", TermList(V,x_y_ys));
    Term sep2 = `Appl("sep", TermList(V,V));

    //t = `Sub(sep2,sep1);
    //type = `GomType("List");

    System.out.println("pretty t = " + Pretty.toString(t));

    try {
      //t = `Repeat(OnceTopDown(Choice(SimplifyAdd(),SimplifySub()))).visitLight(t);
      Term oldT = null;
      while(oldT != t) {
        oldT = t;
        t = `Repeat(OnceTopDown(SimplifyAdd())).visitLight(t);
        System.out.println("t1 = " + Pretty.toString(t));
        t = `Repeat(OnceTopDown(SimplifySub())).visitLight(t);
        System.out.println("t2 = " + Pretty.toString(t));
        t = `Repeat(OnceTopDown(ExpandSub())).visitLight(t);
        System.out.println("t3 = " + Pretty.toString(t));

        //GomType type = gSig.getCodomain(t.getsymbol());
        //System.out.println("symbol = " + t.getsymbol());
        //System.out.println("type = " + type);
        t = eliminateIllTyped(t, type);
      }


    } catch(VisitFailure e) {
      System.out.println("failure on: " + t);
    }

    System.out.println("res = " + Pretty.toString(t));
  }

  %strategy SimplifyAdd() extends Fail() {
    visit Term {

      // flatten: (a + (b + c) + d) -> (a + b + c + d)
      s@Add(TermList(C1*, Add(TermList(tl*)), C2*)) -> {
        Term res = `Add(TermList(C1*,tl*,C2*));
        //System.out.println("flatten: " + Pretty.toString(`s) + " --> " + Pretty.toString(res));
        return res;
      }

      // a + x + b -> x
      Add(TermList(_*, Var("_"), _*)) -> {
        return `Var("_");
      }
      
      // t + t -> t
      Add(TermList(C1*, t, C2*, t, C3*)) -> {
        return `Add(TermList(C1*,t,C2*,C3*));
      }

      // g(t) + g(t') -> g(t + t')
      Add(TermList(C1*, Appl(f,tl1), C2*, Appl(f, tl2), C3*)) -> {
        TermList tl = `add(tl1,tl2);
        return `Add(TermList(C1*, Appl(f,tl), C2*, C3*));
      }

      // t + () -> t
      Add(TermList(C1*,Empty(),C2*)) -> {
        return `Add(TermList(C1*,C2*));
      }

      Add(TermList()) -> {
        return `Empty();
      }

    }
  }

  %strategy SimplifySub() extends Fail() {
    visit Term {
      // x - x -> x
      Sub(Var("_"), Var("_")) -> {
        return `Var("_");
      }

      // t - t -> ()
      Sub(t, t) -> {
        return `Empty();
      }
      //Sub(Appl(f,tl), Appl(f,tl)) -> {
      //  return `Appl(f,tl);
      //}

      // t - () -> t
      Sub(t, Empty()) -> {
        return `t;
      }

      // () - t -> ()
      Sub(Empty(),t) -> {
        return `Empty();
      }

      // (a + t + b) - t -> (a + b)
      Sub(Add(TermList(C1*,t,C2*)),t) -> {
        return `Add(TermList(C1*,C2*));
      }

      // g(t1,...,tn) - g(t1',...,tn') -> g(t1 - t1', ...,tn - tn')
      Sub(Appl(f,tl1), Appl(f, tl2)) && tl1!=tl2-> {
        TermList tl = `sub(tl1,tl2);
        return `Appl(f,TermList(tl*));
      }

      // g(t1,...,tn) - (a + g(t1',...,tn') + b) -> g(t1 - t1', ...,tn - tn') - (a + b)
      Sub(Appl(f,tl1),Add(TermList(C1*, Appl(f, tl2), C2*))) -> {
        TermList tl = `sub(tl1,tl2);
        return `Sub(Appl(f,TermList(tl*)), Add(TermList(C1*,C2*)));
      }

      // TODO: t - t' -> t if t' not matched t
      // g(t1,...,tn) - f(t1',...,tm') -> g(t1,...,tn)
      Sub(Appl(f,tl1), Appl(g, tl2)) && f!=g -> {
        return `Appl(f,(tl1));
      }

      // g(t1,...,tn) - (a + f(t1',...,tm') + b) -> g(t1,...,tn) - (a + b)
      Sub(Appl(f,tl1),Add(TermList(C1*, Appl(g, tl2), C2*))) && f!=g -> {
        return `Sub(Appl(f,tl1), Add(TermList(C1*,C2*)));
      }

      // x - (a + b) -> x - a - b
      Sub(t1, Add(TermList(head,tail*))) -> {
        return `Sub(Sub(t1,head), Add(TermList(tail*)));
      }

    }
  }

  // (a,b,c) - (a',b',c') -> (a - a', b - b', c - c')
  private static TermList sub(TermList tl1,TermList tl2) {
    TermList tl = `TermList();
    while(!tl1.isEmptyTermList()) {
      Term h1 = tl1.getHeadTermList();
      Term h2 = tl2.getHeadTermList();
      tl = `TermList(tl*, Sub(h1,h2));

      tl1 = tl1.getTailTermList();
      tl2 = tl2.getTailTermList();
    }
    return tl;
  }

  // (a,b,c) + (a',b',c') -> (a + a', b + b', c + c')
  private static TermList add(TermList tl1,TermList tl2) {
    TermList tl = `TermList();
    while(!tl1.isEmptyTermList()) {
      Term h1 = tl1.getHeadTermList();
      Term h2 = tl2.getHeadTermList();
      tl = `TermList(tl*, Add(TermList(h1,h2)));

      tl1 = tl1.getTailTermList();
      tl2 = tl2.getTailTermList();
    }
    return tl;
  }

  %strategy ExpandSub() extends Fail() {
    visit Term {
      // x - a -> expand AP
      Sub(Var("_"),t@Appl(f,args_f)) -> {
        //GomType codomain = gSig.getCodomain(`f);
        RuleCompiler ruleCompiler = new RuleCompiler(Pattern.eSig, Pattern.gSig);
        RuleList rl = ruleCompiler.expandAntiPatterns(`ConcRule(Rule(Anti(t),Var("_"))));
        //System.out.println("rl = " + Pretty.toString(rl));
        TermList tl = `TermList();
        %match(rl) {
          ConcRule(_*,Rule(lhs@Appl(g,args_g),rhs),_*) -> {
            //if(gSig.getCodomain(`g) == codomain) {
              Term newLhs = `TopDown(RemoveVar()).visitLight(`lhs);
              tl = `TermList(tl*,newLhs);
            //}
          }
        }
        return `Add(tl);
      }
    }
  }

  %strategy RemoveVar() extends Identity() {
    visit Term {
      Var(_) -> {
        return `Var("_");
      }
    }
  }

  private static Term eliminateIllTyped(Term t, GomType type) {
    //System.out.println(Pretty.toString(t) + ":" + type.getName());
    %match(t) {
      Var("_") -> {
        return t;
      }

      Appl(f,args) -> {
        if(gSig.getCodomain(`f) == type) {
          GomTypeList types = gSig.getDomain(`f);
          TermList tail = `args;
          TermList res = `TermList();
          while(!tail.isEmptyTermList()) {
            Term head = tail.getHeadTermList();
            GomType arg_type = types.getHeadConcGomType();
            res = `TermList(res*, eliminateIllTyped(head,arg_type));
            tail = tail.getTailTermList();
            types = types.getTailConcGomType();
          }
          return `Appl(f,res);
        } else {
          return `Empty();
        }
      }

      Sub(t1,t2) -> {
        return `Sub(eliminateIllTyped(t1,type),eliminateIllTyped(t2,type));
      }

      Add(tl) -> {
        TermList tail = `tl;
        TermList res = `TermList();
        while(!tail.isEmptyTermList()) {
          Term head = tail.getHeadTermList();
          res = `TermList(res*, eliminateIllTyped(head,type));
          tail = tail.getTailTermList();
        }
        return `Add(res);
      }

    }

    System.out.println("Should not be there: " + `t);
    return t;
  }

}
