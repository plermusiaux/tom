public class JLstring {

%include {charlist.tom}

  public final static void main(String[] args) {
    JLstring test = new JLstring();
    
    //test.f("hello");

    test.f2("hello");
  }

  /*
  public void f(String s) {
    Character l = `Char('l');
    Character o = `Char('o');
    %match(String s) {
      ("he",X1*,x@Char('l'),y@Char('o'),_*) -> {
        System.out.println("X1   = " + X1);
        System.out.println("char = " + x);
        System.out.println("char = " + y);
        return s;
      }
      _        -> { return "unknown"; }
    }
  }
  */

  /*
  public void f2(String s) {
    %match(String s) {
      (X1*,x@"l",x@"l",X2*) -> {
        System.out.println("X1   = " + X1);
        System.out.println("char = " + x);
        System.out.println("X2   = " + X2);
      }
    }
  }
  */

  public void f2(String s) {
    String s2 = "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaabaa";
    %match(String s2) {
      (X1*,x,X2*,x,X3*,x,X4*,x,X5*,x,X6*,x,X7*) -> {
        if(x.equals("b")) {
             System.out.println(X1 + " " + X2+ " " + X3 + " " + X4 + " " + X5 + " " + X6 + " " + X7);
        }
      }
    }
  }

}
