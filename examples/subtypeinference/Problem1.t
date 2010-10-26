import problem1.example.types.*;
public class Problem1{
  /*
  %gom {
    module Example
      abstract syntax
      Nat = zero()
          | suc(n:Nat)
          | square(m:Int)

      Int = uminus(n:Nat)
      | plus(n1:Int,n2:Int)

      Float = div(n1:Int,n2:Int)
      }
   */
  static class Nat extends Int{}

  static class Int extends Float{}

  static class Float {}

  %typeterm Nat {
    implement { problem1.example.types.Nat }
    is_sort(t) { ($t instanceof problem1.example.types.Nat) }

    equals(t1,t2) { ($t1==$t2) }

  }

  %typeterm Float {
    implement { problem1.example.types.Float }
    is_sort(t) { ($t instanceof problem1.example.types.Float) }

    equals(t1,t2) { ($t1==$t2) }

  }

  %typeterm Int {
    implement { problem1.example.types.Int }
    is_sort(t) { ($t instanceof problem1.example.types.Int) }

    equals(t1,t2) { ($t1==$t2) }

  }
  %subtype Nat <: Int
  %subtype Int <: Float

  %op Nat zero() {
    is_fsym(t) { ($t instanceof problem1.example.types.nat.zero) }
    make() { problem1.example.types.nat.zero.make() }
  }

  %op Nat suc(n:Nat) {
    is_fsym(t) { ($t instanceof problem1.example.types.nat.suc) }
    get_slot(n, t) { $t.getn() }
    make(t0) { problem1.example.types.nat.suc.make($t0) }
  }

  %op Nat square(m:Int) {
    is_fsym(t) { ($t instanceof problem1.example.types.nat.square) }
    get_slot(m, t) { $t.getm() }
    make(t0) { problem1.example.types.nat.square.make($t0) }
  }

  %op Float div(n1:Int, n2:Int) {
    is_fsym(t) { ($t instanceof problem1.example.types.float.div) }
    get_slot(n1, t) { $t.getn1() }
    get_slot(n2, t) { $t.getn2() }
    make(t0, t1) { problem1.example.types.float.div.make($t0, $t1) }
  }

  %op Int uminus(n:Nat) {
    is_fsym(t) { ($t instanceof problem1.example.types.int.uminus) }
    get_slot(n, t) { $t.getn() }
    make(t0) { problem1.example.types.int.uminus.make($t0) }
  }

  %op Int plus(n1:Int, n2:Int) {
    is_fsym(t) { ($t instanceof problem1.example.types.int.plus) }
    get_slot(n1, t) { $t.getn1() }
    get_slot(n2, t) { $t.getn2() }
    make(t0, t1) { problem1.example.types.int.plus.make($t0, $t1) }
  }

  public static void main(String[] args) {
    %match{
      x << zero() -> { System.out.println(`x); }
    }
  }
}
