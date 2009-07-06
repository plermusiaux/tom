package mi3;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;


import base.data.types.*;
import base.data.types.t1.*;
import base.data.types.t2.*;
import base.data.types.listt1.*;
import tom.library.sl.*;
import java.util.List;

public class MainGom extends TestCase {

  %include { gom/mapping.tom }
  %include { sl.tom }

  /*
   * new way of defining signatures in Tom
   %module {
     module Signature

     base.data.types.T1 // defines the implementation of T1
     T1 = a()
        | f(s1:T1, s2:T2)
  
     base.data.types.T2
     T2 = b()
        | g(s2:T2)
        | h(ts:ListT1)

     ListT1 = concT1(T1*)
  }
  */

  // generated by Tom, in this file
  public interface ISignature {
    mi3.mapping.Mapping0<base.data.types.T1> getMapping_a();
    mi3.mapping.Mapping0<base.data.types.T2> getMapping_b();
    mi3.mapping.Mapping2<base.data.types.T1,base.data.types.T1,base.data.types.T2> getMapping_f();
    mi3.mapping.Mapping1<base.data.types.T2,base.data.types.T2> getMapping_g();
    mi3.mapping.Mapping1<base.data.types.T2,base.data.types.ListT1> getMapping_h();
    mi3.mapping.ListMapping<base.data.types.ListT1,base.data.types.T1> getMapping_concT1();
  }

  public static Strategy _a() {
    return new mi3.congruence._Strategy(getSignature().getMapping_a());
  }

  public static Strategy _b() {
    return new mi3.congruence._Strategy(getSignature().getMapping_b());
  }

  public static Strategy _f(Strategy s1, Strategy s2) {
    return new mi3.congruence._Strategy(getSignature().getMapping_f(),s1,s2);
  }

  public static Strategy _g(Strategy s1) {
    return new mi3.congruence._Strategy(getSignature().getMapping_g(),s1);
  }

  public static Strategy _h(Strategy s1) {
    return new mi3.congruence._Strategy(getSignature().getMapping_h(),s1);
  }

  /*
   * the body of the following method is hand-written
   * but an empty method with return null; is generated
   */
  public static ISignature getSignature() {
    return signature;
  }

  /* code by hand */
  private static ISignature signature = new ISignature() {
    private mi3.gom.Module.a_Mapping a_Mapping = new mi3.gom.Module.a_Mapping();
    private mi3.gom.Module.b_Mapping b_Mapping = new mi3.gom.Module.b_Mapping();
    private mi3.gom.Module.f_Mapping f_Mapping = new mi3.gom.Module.f_Mapping();
    private mi3.gom.Module.g_Mapping g_Mapping = new mi3.gom.Module.g_Mapping();
    private mi3.gom.Module.h_Mapping h_Mapping = new mi3.gom.Module.h_Mapping();
    private mi3.gom.Module.concT1_Mapping concT1_Mapping = new mi3.gom.Module.concT1_Mapping();

    public mi3.mapping.Mapping0<T1>       getMapping_a() { return a_Mapping; }
    public mi3.mapping.Mapping0<T2>       getMapping_b() { return b_Mapping; }
    public mi3.mapping.Mapping2<T1,T1,T2> getMapping_f() { return f_Mapping; }
    public mi3.mapping.Mapping1<T2,T2>    getMapping_g() { return g_Mapping; }
    public mi3.mapping.Mapping1<T2,ListT1>    getMapping_h() { return h_Mapping; }
    public mi3.mapping.ListMapping<ListT1,T1>    getMapping_concT1() { return concT1_Mapping; }
  };

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(MainGom.class));
  }

  public void testMatch() {
    T1 subject = `f(f(a(),b()),g(b()));
    %match(subject) {
      f(x,g(y)) -> { 
        assertEquals(`x,`f(a(),b()));
        assertEquals(`y,`b());
        return;
      }
    }
    fail();
  }


  public void testVisit() {
    T1 subject = `f(f(a(),b()),g(b()));
    try {
      T1 res = (T1) `Repeat(OnceBottomUp(Rule())).visitLight(subject, mi3.mapping.Introspector.instance);
      assertEquals(res, `a());
    } catch(VisitFailure e) {
      fail();
    }

  }

  public void testCongruence() {
    T1 subject = `f(f(a(),b()),g(b()));
    try {
      T1 res = (T1) `_f(Rule(), Rule2()).visitLight(subject, mi3.mapping.Introspector.instance);
      assertEquals(res, `f(a(),b()));
    } catch(VisitFailure e) {
      fail();
    }
  }

  %strategy Rule() extends Fail() {
    visit T1 {
      f(x,y) -> x
    }
  }

  %strategy Rule2() extends Fail() {
    visit T2 {
      g(x) -> x
    }
  }


  public void test_listMatchFirst() {
    ListT1 subject = `concT1(a(), f(a(), b()), f(a(), g(b())));

    %match(subject) {
      concT1(first, _*) -> {

        assertEquals(`first, `a());
        return;
      }
    }
    fail();
  }

  public void test_listMatchLast() {
    Object subject = `concT1(a(), f(a(), b()), f(a(), g(b())));

    %match(subject) {
      concT1(_*, last) -> {

        assertEquals(`last, `f(a(), g(b())));
        return;
      }
    }
    fail();
  }

  public void test_listMatchCount() {
    T2 subject = `h(concT1(a(), f(a(), b()), f(a(), g(b()))));

    int i = 0;
    %match(subject) {
      h(concT1(_*, x, _*)) -> {
        i++;
      }
    }
    assertEquals(i,3);
  }

  public void test_listMatchNonLinear() {
    T2 subject = `h(concT1(a(), f(a(), b()), f(a(),b()), a(), f(a(), g(b()))));

    %match(subject) {
      h(concT1(X1*, x, X2*, x, X3*)) -> {
        assertEquals(`x,`a());
        assertEquals(`X1,`concT1());
        assertEquals(`X2,`concT1(f(a(),b()), f(a(),b())));
        assertEquals(`X3,`concT1(f(a(), g(b()))));
        return;
      }
      _ -> {
        fail();
      }
    }
  }

  public void test_listMatchAny() {
    T2 subject = `h(concT1(a(), f(a(), b()), f(a(), g(b()))));

    %match(subject) {
      !h(concT1(_*, a(), _*)) -> {
        fail();
        return;
      }
    }
  }

}
