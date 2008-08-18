import tom.library.sl.*;
import lambda.types.*;
import org.antlr.runtime.*;
import java.util.*;

public class FreshLambda {

  %include { sl.tom }
  %include { lambda/Lambda.tom }

  // returns t[u/x]
  public static LTerm substitute(LTerm t, LVar x, LTerm u) {
    %match(t) {
      App(t1,t2) -> { return `App(substitute(t1,x,u),substitute(t2,x,u)); }
      Abs(lam(v,t1)) -> { return `Abs(lam(v,substitute(t1,x,u))); }
      Let(letin(v,t1,t2)) -> {
        return `Let(letin(v,substitute(t1,x,u),substitute(t2,x,u)));
      }
      Fix(fixpoint(v,t1)) -> { return `Fix(fixpoint(v,substitute(t1,x,u))); }
      Var(y) -> { if (`y.equals(x)) return u; else return t; }
      Constr(f, c) -> { return `Constr(f,substitute(c,x,u)); }
      Case(s,r) -> { return `Case(substitute(s,x,u),substitute(r,x,u)); }
    }
    throw new RuntimeException();
  }

  public static Rules substitute(Rules r, LVar x, LTerm u) {
    %match(r) {
      EmptyRList() -> { return r; }
      ConsRList(t,ts) -> {
        return `ConsRList(substitute(t,x,u),substitute(ts,x,u));
      }
    }
    throw new RuntimeException();
  }

  public static Clause substitute(Clause c, LVar x, LTerm u) {
    %match(c) {
      Rule(p,t) -> { return `Rule(p,substitute(t,x,u)); }
    }
    throw new RuntimeException();
  }

  public static LTermList substitute(LTermList l, LVar x, LTerm u) {
    %match(l) {
      EmptyLTList() -> { return l; }
      ConsLTList(t,ts) -> {
        return `ConsLTList(substitute(t,x,u),substitute(ts,x,u));
      }
    }
    throw new RuntimeException();
  }

  public static LTerm substitute(LTerm t, Hashtable<LVar,LTerm> m) {
    LTerm res = t;
    for(Map.Entry<LVar,LTerm> e: m.entrySet()) {
      res = substitute(res,e.getKey(),e.getValue());
    }
    return res;
  }

  /* matching */

  public static Hashtable<LVar,LTerm> 
    match(Hashtable<LVar,LTerm> m, Pattern p, LTerm t) {
      %match(p,t) {
        PVar(x), u -> { 
          if (m.containsKey(`x)) return (m.get(`x).equals(`u) ? m : null); 
          else { m.put(`x,`u); return m; }
        }
        PFun(f,l1), Constr(f,l2) -> { return `match(m,l1,l2); }
      }
      return null;
    }

  public static Hashtable<LVar,LTerm>
    match(Hashtable<LVar,LTerm> m, PatternList l1, LTermList l2) {
      %match(l1,l2) {
        ConsPList(p,ps),ConsLTList(x,xs) -> {
          return `match(m,p,x) != null ? `match(m,ps,xs) : null;
        }
        EmptyPList(),EmptyLTList() -> { return m; }
      }
      return null;
    }

  /* beta reduction */

  public static LTerm caseof(LTerm t, Rules r) throws VisitFailure {
    %match(t,r) {
      Constr[],ConsRList(Rule(lhs,rhs),rs) -> {
        Hashtable<LVar,LTerm> m = match(new Hashtable(),`lhs,t);
        if (m!=null) return `substitute(rhs,m);
        else return caseof(t,`rs);
      }
    }
    throw new VisitFailure();
  }

  %strategy HeadBeta() extends Fail() {
    visit LTerm {
      App(Abs(lam(x,t)),u) -> { return `substitute(t,x,u); }
      Let(letin(x,u,t)) -> { return `substitute(t,x,u); }
      Fix(fixpoint(x,t)) -> { return `substitute(t,x,Fix(fixpoint(x,t))); }
      Case(t,r) -> { return `caseof(t,r); }
    }
  }

  public static LTerm beta(LTerm t) {
    try { return (LTerm) `Outermost(HeadBeta()).visit(t); }
    catch (VisitFailure e) { return t; }
  }

  /* parser loop */

  public static void main(String[] args) {
    try{
      LambdaLexer lexer = new LambdaLexer(new ANTLRInputStream(System.in));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      LambdaParser parser = new LambdaParser(tokens);
      LTerm t = null, tmp = null;
      for(RawLTerm rt:parser.toplevel()) {
        System.out.println("\nparsed: " + Printer.pretty(rt));
        tmp = t;
        t = beta(rt.convert());
        System.out.println("\nnormal form : " + Printer.pretty(t.export()));
        if (tmp!=null)
          System.out.println("\nequals previous modulo alpha: " 
              + t.equals(tmp) + ";");
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
