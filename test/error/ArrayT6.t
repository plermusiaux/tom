import jaterm.api.*;
import jaterm.shared.*;
import java.util.*;

public class ArrayT5 {

  private ATermFactory factory;
  
  private AFun fzero, fsuc, fplus,ffib;
  public ATermAppl tzero;

// ****** erreur ****** meme nom de variable dans get_element

  %typearray L {
    implement { ArrayList }
    get_fun_sym(t)   { ((t instanceof ArrayList)?factory.makeAFun("conc", 1, false):null) }
    get_element(l,l) { ((List)l).get(n) }
    cmp_fun_sym(t1,t2) { t1 == t2 }
    equals(l1,l2)    { l1.equals(l2) }
    get_size(l)      { ((List)l).size() }
  }

// ****** erreur ******

  %oparray L conc( E* ) {
    fsym            { factory.makeAFun("conc", 1, false) }
    make_empty(n)   { new ArrayList(n) }
    make_add(l,e,n) { myAdd((List)l,e,n) }
  }

  private Object myAdd(List l, Object e, int n) {
    l.add(n,e);
    return l;
  }
 
  %typeterm E {
    implement           { ATerm }
    get_fun_sym(t)      { (((ATermAppl)t).getAFun()) }
    cmp_fun_sym(t1,t2) { t1 == t2 } 
    get_subterm(t, n)   { (((ATermAppl)t).getArgument(n)) }
    equals(t1, t2)      { (t1.equals(t2)) }
  }

  %op E a {
    fsym { factory.makeAFun("a", 0, false) }
  }
  
  %op E b {
    fsym { factory.makeAFun("b", 0, false) }
  }

  %op E c {
    fsym { factory.makeAFun("c", 0, false) }
  }
  
  public ArrayT5(ATermFactory factory) {
    this.factory = factory;
  }

  public final static void main(String[] args) {
    ArrayT5 test = new ArrayT5(new PureFactory(16));
    test.run1();
  }

  public void run1() {
    ATerm ta = factory.makeAppl(factory.makeAFun("a", 0, false));
    ATerm tb = factory.makeAppl(factory.makeAFun("b", 0, false));
    ATerm tc = factory.makeAppl(factory.makeAFun("c", 0, false));

    ArrayList l = new ArrayList();
    l.add(ta);
    l.add(tb);
    l.add(tc);
    l.add(ta);
    l.add(tb);
    l.add(tc);

    List res = sort1(l);
    System.out.println(" l       = " + l);
    System.out.println("sorted l = " + res);
  }


  public ArrayList sort1(ArrayList l) {
    
    %match(L l) {
        
      conc(X1*,x,X2*,y,X3*) -> {
          /*
            System.out.print("X1 = " + X1);
            System.out.print("\tx  = " + x);
            System.out.print("\tX2 = " + X2);
            System.out.print("\ty  = " + y);
            System.out.println("\tX3 = " + X3);
          */
          
        String xname = ((ATermAppl)x).getName();
        String yname = ((ATermAppl)y).getName();

        if(xname.compareTo(yname) > 0) {
          ArrayList result = X1;
          result.add(y);
          result.addAll(X2);
          result.add(x);
          result.addAll(X3);
          return sort1(result);
        }
          
      }
        
      _ -> { return l; }
    }
    
  }
  
}

