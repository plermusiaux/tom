public class BenchCons {
  %include { int.tom }

  %typeterm TomList {
    implement { List }
    equals(l1,l2) { l1==l2 }
  }

  %oplist TomList conc( int* ) {
    is_fsym(t) { t instanceof Cons || t instanceof Empty }
    make_empty()  { Empty.make() }
    make_insert(e,l) { Cons.make(Int.make(e),l) }
    get_head(l)   { l.getHead().getValue() }
    get_tail(l)   { l.getTail() }
    is_empty(l)   { l.isEmpty() }
  }
  
  public List genere(int n) {
    if(n>2) {
      List l = genere(n-1);
      return `conc(n,l*);
    } else {
      return `conc(2);
    }
  }

  public List elim(List l) {
    %match(TomList l) {
      conc(x*,e1,y*,e2,z*) -> {
        if(`e2%`e1 == 0) {
          return `elim(conc(x*,e1,y*,z*));
        }
      }
    }
    return l;
  }

  public List reverse(List l) {
    List res = `conc();
    while(true) {
      %match(TomList l) {
        conc() -> { return res; }
        conc(h,t*) -> {
          res = `conc(h,res*);
          l = `t;
        }
      }
    }
  }

  public void run(int max) {
    //System.out.println(" l = " + genere(100));

    long startChrono = System.currentTimeMillis();
    
    elim(reverse(genere(max)));
    
    System.out.println(max + " " + (System.currentTimeMillis()-startChrono));
    //System.out.println(factory);
  }

  public final static void main(String[] args) {
    BenchCons test = new BenchCons();
    int max = 100;
    try {
      max = Integer.parseInt(args[0]);
    } catch (Exception e) {
      System.out.println("Usage: java list.BenchCons <max>");
      return;
    }
    test.run(max);
  }

}

