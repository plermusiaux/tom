import java.util.*;

import aterm.*;
import aterm.pure.*;
import adt.jgset.*;
import adt.jgset.types.*;

import jtom.runtime.GenericTraversal;
import jtom.runtime.Replace1;
import java.io.*;

public class Set1 {
    // Jean Goubault version 
  private Factory factory;
  private Comparator comparator;
  private GenericTraversal traversal;
  private int depth;
  private int collisions;
  private final static int[] mask =
  { 1 << 0, 
    1 << 1,
    1 << 2,
    1 << 3,
    1 << 4,
    1 << 5,
    1 << 6,
    1 << 7,
    1 << 8,
    1 << 9,
    1 << 10,
    1 << 11,
    1 << 12,
    1 << 13,
    1 << 14,
    1 << 15,
    1 << 16,
    1 << 17,
    1 << 18,
    1 << 19,
    1 << 20,
    1 << 21,
    1 << 22,
    1 << 23,
    1 << 24,
    1 << 25,
    1 << 26,
    1 << 27,
    1 << 28,
    1 << 29,
    1 << 30,
    1 << 31
  };

  %include { jgset.tom }

  public Set1(Factory factory) {
    this(factory, 31);
  }
  
  public Set1(Factory factory, int depth) {
    this.factory = factory;
    this.comparator = new MyComparator();
    this.traversal = new GenericTraversal();
    if (depth <= 32) {
      this.depth = depth;
    } else {this.depth = 32;}
  }
  public Factory getJgsetFactory() {
    return factory;
  }

  public JGSet add(ATerm elt, JGSet t) {
    return override(elt, t, 0);
  }

  public JGSet remove(ATerm elt, JGSet t) {
    return remove(elt, t, 0);
  }

  public boolean member(ATerm elt, JGSet t) {
    return member(elt, t, 0);
  }

  public JGSet union(JGSet t1, JGSet t2) {
    return union(t1, t2, 0);
  }

  public JGSet intersection(JGSet t1, JGSet t2) {
    JGSet result = intersection(t1, t2, 0);
    return reworkJGSet(result);
  }
  
  public int card(JGSet t) {
    %match(JGSet t) {
      emptyJGSet()    -> { return 0; }
      singleton(x) -> { return 1; }
      branch(l, r) -> {return card(l) + card(r);}
    }
    return 0;
  }

  public void topRepartition(JGSet t) {
    %match(JGSet t) {
      branch(l,r) -> { System.out.println("Left branch: "+card(l)+"\tright branch: "+card(r));return;}
      _ ->  {System.out.println("topRepartition: No a branch");}
    }
  }

    // getHead return the first left inner element found
  public ATerm getHead(JGSet t) {
    %match(JGSet t) {
      emptyJGSet() -> {
        return null;
      }
      singleton(x) -> {return x;}
      branch(l,r) -> {
        ATerm left = getHead(l);
        if(left != null) {
          return left;
        }
        return getHead(r);
      }
    }
    return null;
  }

  public JGSet getTail(JGSet t) {
    return remove(getHead(t), t);
  }
  
  /* Simple binary operation skeleton
 private JGSet f(JGSet m1, JGSet m2) {
   %match(JGSet m1, JGSet m2) {
      emptyJGSet, x -> {
        return f2(m2);
      }
      x, emptyJGSet -> {
        return f1(m1);
      }
      singleton(y) , x -> {
        return g2(y, m2);
      }
      x, singleton(y) -> {
        return g1(y, m1)
      }
      branch(l1, r1), branch(l2, r2) -> {
        return `branch(f(l1, l2, level+1), f(r1, r2, level+1));
      }
    }
  }*/

  private JGSet reworkJGSet(JGSet t) {
    Replace1 replace = new Replace1() {
        public ATerm apply(ATerm t) {
          %match(JGSet t) {
            emptyJGSet() -> {return t;}
            singleton(x) -> {return t;}
            branch(emptyJGSet(), s@singleton(x)) -> {return s;}
            branch(s@singleton(x), emptyJGSet()) -> {return s;}
            branch(e@emptyJGSet(), emptyJGSet()) -> {return e;}
            branch(l1, l2) -> {return `branch(reworkJGSet(l1), reworkJGSet(l2));}
            _ -> { return traversal.genericTraversal(t,this); }
          }
        }
      };
    
    JGSet res = (JGSet)replace.apply(t);
    if(res != t) {
      res = reworkJGSet(res);
    }
    return res;
  }
  
  private JGSet union(JGSet m1, JGSet m2, int level) {
    %match(JGSet m1, JGSet m2) {
      emptyJGSet(), x -> {
        return m2;
      }

      x, emptyJGSet() -> {
        return m1;
      }

      singleton(y), x -> {
        return override(y, x, level);
      }

      x, singleton(y) -> {
        return underride(y, x, level);
      }

      branch(l1, r1), branch(l2, r2) -> {
        int l = level+1;
        return `branch(union(l1, l2, l), union(r1, r2, l));
      }
    }
    return null;
  }
  
  private JGSet intersection(JGSet m1, JGSet m2, int level) {
    %match(JGSet m1, JGSet m2) {
      emptyJGSet(), x |
        x, emptyJGSet() -> { 
        return `emptyJGSet();
      }
      
      s@singleton(y), x |
      x, s@singleton(y) -> {
        if (member(y, x, level)) {
          return s;
        } else {
          return `emptyJGSet();
        }
      }

      branch(l1, r1), branch(l2, r2) -> {
        int l = level+1;
        return `branch(intersection(l1, l2, l), intersection(r1, r2, l));        
      }
    }
    return null;
  }
  
  public JGSet restriction(JGSet m1, JGSet m2, int level) {
    %match(JGSet m1, JGSet m2) {
      emptyJGSet(), x |
      x, emptyJGSet() -> { 
        return `emptyJGSet();
      }
      
      singleton(y), x -> {
        return remove(y, x, level);
      }

      x, singleton(y) -> {
        if (member(y, x)) {
          return m2;
        } else {
          return `emptyJGSet();
        }
      }

      branch(l1, r1), branch(l2, r2) -> {
        int l = level+1;
        return `branch(restriction(l1, l2, l), restriction(r1, r2, l));
      }
    }
    return null;
  }
  
  private JGSet remove(ATerm elt, JGSet t, int level) {
    %match(JGSet t) {
      emptyJGSet()     -> {return t;}

      singleton(x)   -> {
        if (x == elt) {return `emptyJGSet();}
        else {return t;}
      }

      branch(l, r) -> {
        JGSet l1 = null, r1=null;
        if( isBitZero(elt, level) ) {
          l1 = remove(elt, l, level+1);
          r1 = r;
        } else {
          l1 = l;
          r1 = remove(elt, r, level+1);
        }
        %match(JGSet l1, JGSet r1) {
          emptyJGSet(), singleton(x) -> {return r1;}
          singleton(x), emptyJGSet() -> {return l1;}
          _, _ -> {return `branch(l1, r1);}
        }
      }
    }
    return null;
  }

  private boolean member(ATerm elt, JGSet t, int level) {
    %match(JGSet t) {
      emptyJGSet() -> {return false;}
      
      singleton(x) -> {
        if(x == elt) return true;
      }
      
      branch(l, r) -> {
        if(level == depth) {
          return (member(elt, l, level) || member(elt, r, level));
        }
        if( isBitZero(elt, level)) {
          return member(elt, l, level+1);
        } else {
          return member(elt, r, level+1);
        }
      }
    }
    return false;
  }
  
  private JGSet override(ATerm elt, JGSet t, int level) {
    int lev = level+1;
    %match(JGSet t) {
      emptyJGSet()      -> {return `singleton(elt);}

      singleton(x)   -> {
        if(x == elt) {  return `singleton(elt);}
        else if( level >= depth ) {
          System.out.println("Collision!!!!!!!!");
          collisions++;
            // Create 1rst list of element as it was a branch
          return `branch(t, singleton(elt));
          
        }
        else if ( isBitZero(elt, level) && isBitZero(x, level) )  { return `branch(override(elt, t, lev), emptyJGSet);}
        else if ( isBitOne(elt, level)  && isBitOne(x, level) )   { return `branch(emptyJGSet, override(elt, t, lev));}
        else if ( isBitZero(elt, level) && isBitOne(x, level) ) { return `branch(singleton(elt), t);}
        else if ( isBitOne(elt, level)  && isBitZero(x, level) ){ return `branch(t, singleton(elt));}
      }
      
      branch(l, r) -> {
        if(level >= depth) {
          System.out.println("Collision!!!!!!!!");
          collisions++;
            //continue list of element
          return `branch(t, singleton(elt));
        }
        if (isBitZero(elt, level)) {
          return `branch(override(elt, l, lev), r);
        } else {
          return `branch(l, override(elt, r, lev));
        }
      }
    }
    return null;
  }
  
  private JGSet underride(ATerm elt, JGSet t, int level) {
    int lev = level+1;
    %match(JGSet t) {
      emptyJGSet()     -> {return `singleton(elt);}

      singleton(x)   -> {
        if(x == elt) {  return t;}
        else if ( isBitZero(elt, level) && isBitZero(x, level) )  { return `branch(underride(elt, t, lev), emptyJGSet);}
        else if ( isBitOne(elt, level)  && isBitOne(x, level) )   { return `branch(emptyJGSet, underride(elt, t, lev));}
        else if ( isBitZero(elt, level) && isBitOne(x, level) ) { return `branch(singleton(elt), t);}
        else if ( isBitOne(elt, level)  && isBitZero(x, level) ){ return `branch(t, singleton(elt));}
      }

      branch(l, r) -> {
        if (isBitZero(elt, level)) {return `branch(underride(elt, l, lev), r);}
        else {return `branch(l, underride(elt, r, lev));}
      }
    }
    return null;
  }

  private boolean isBitZero(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) == 0);
  }
  
  private boolean isBitOne(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) > 0);
  }
  
  public final static void main(String[] args) {
    Factory fact = new Factory(new PureFactory());
    Set1 test;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int input = 0;
    while(true) {
      try {
        String str = "";
        System.out.print(">Depth:");
        str = in.readLine();
        try {
          input = Integer.parseInt(str, 10);
        }catch (NumberFormatException e) {
          System.out.println("Not a valid number");
          continue;
        }
        if( input == 0 ) System.exit(0);
        test = new Set1(fact, input);
        while(true) {
          try {
            str = "";
            System.out.print(">Nb elements(will be multiply by 3):");
            str = in.readLine();
            try {
              input = Integer.parseInt(str, 10);
            }catch (NumberFormatException e) {
              System.out.println("Not a valid number");
              continue;
            }
            if( input == 0 ) System.exit(0);
            test.run(input);
            break;
          } catch (IOException e) {
          }
        }
      } catch (IOException e) {
      }
    }    
  }

      // Execution 
  public void run(int n) {

    JGSet t0 = `emptyJGSet();
    JGSet t00 = `emptyJGSet();
    JGSet t1 = `emptyJGSet();
    JGSet t2 = `emptyJGSet();
    Element e1 = `e1();
    Element e2 = `e2();
    Element e3 = `e3();

    
    Element array[] = new Element[3*n];
    array[0] = e1;
    array[1] = e2;
    array[2] = e3;
    for(int i=1 ; i<n ; i++) {
      Element old_e1 = array[3*i+0-3];
      Element old_e2 = array[3*i+1-3];
      Element old_e3 = array[3*i+2-3];
      array[3*i+0] = `f(old_e1);
      array[3*i+1] = `f(old_e2);
      array[3*i+2] = `f(old_e3);
    }
    
      // Adding elements to JGSet t
    long startChrono = System.currentTimeMillis();
    for(int i=0 ; i<3*n ; i++) {
      t1 = add(array[i], t1);
    }    
    long stopChrono = System.currentTimeMillis();
    int size = card(t1);
    System.out.println("JGSet tree size = " + size + " in " + (stopChrono-startChrono) + " ms");
      // Repartition issue
    topRepartition(t1);

      // getHead and Tail
    System.out.println("getHead de empty: "+getHead(t0));
    t0 = add(e1, t0);
    System.out.println("getHead de t0: "+t0+" = " +getHead(t0));
    System.out.println("getTail de t0: "+getTail(t0));

    startChrono = System.currentTimeMillis();
    ATerm trm = getHead(t1), trm2 = getHead(t1);
    stopChrono = System.currentTimeMillis();
    if (trm == trm2) {System.out.println("getHead is OK");}
    System.out.println("2 times getHead from tree in: "+ (stopChrono-startChrono) + " ms");
    
    startChrono = System.currentTimeMillis();
    t0 = getTail(t1);
    stopChrono = System.currentTimeMillis();
    if (t0 == remove(trm, t1)) {System.out.println("getTail is OK");}
    System.out.println("getTail from tree in: "+ (stopChrono-startChrono) + " ms");
    
      //Adding element to a java JGSet
    TreeSet set = new TreeSet(comparator);
    startChrono = System.currentTimeMillis();
    for(int i=0 ; i<3*n ; i++) {
      set.add(array[i]);
    }    
    stopChrono = System.currentTimeMillis();
    System.out.println("Java set  size = " + set.size() + " in " + (stopChrono-startChrono) + " ms");
    
      // Looking for elements in a JGSet
    startChrono = System.currentTimeMillis();
    for(int i=0 ; i<3*n ; i++) {
      if( member(array[i], t1) == false) System.out.println("Loose an element");
    }
    stopChrono = System.currentTimeMillis();
    System.out.println("Found each element of JGSet size = " + size + " in " + (stopChrono-startChrono) + " ms");
    
      // Looking for elements in a java JGSet
    startChrono = System.currentTimeMillis();
    for(int i=0 ; i<3*n ; i++) {
      if( set.contains(array[i]) == false) System.out.println("Loose an element");
    }
    stopChrono = System.currentTimeMillis();
    System.out.println("Found each element of java JGSet size = " + size + " in " + (stopChrono-startChrono) + " ms");
    
      //Maximal sharing
    TreeSet set2 = new TreeSet(comparator);
    for(int i=3*n-1 ; i>=0 ; i--) {
      t2 = add(array[i], t2);
      set2.add(array[i]);
    }
    if (t1 == t2) System.out.println("Maximal sharing is OK");
    
      //Union and intersection
    startChrono = System.currentTimeMillis();
    JGSet t3 = union(t1, t2);
    stopChrono = System.currentTimeMillis();
    if (t1 == t3) System.out.println("Simple union OK");
    System.out.println("Simple union for JGSet in " + (stopChrono-startChrono) + " ms");

    startChrono = System.currentTimeMillis();
    set.addAll(set2);
    stopChrono = System.currentTimeMillis();
    System.out.println("Simple union for java JGSet in " + (stopChrono-startChrono) + " ms");

     
    startChrono = System.currentTimeMillis();
    JGSet t4 = intersection(t1, t2);
    stopChrono = System.currentTimeMillis();
    if (t1 == t4) System.out.println("Simple intersection OK");
    System.out.println("Simple intersection for JGSet in " + (stopChrono-startChrono) + " ms");

    startChrono = System.currentTimeMillis();
    set.containsAll(set2);
    stopChrono = System.currentTimeMillis();
    System.out.println("Simple intersection for java JGSet in " + (stopChrono-startChrono) + " ms");
    
    JGSet t5 = `emptyJGSet();
    JGSet t6 = `emptyJGSet();
    JGSet t7 = `emptyJGSet();
    TreeSet set5 = new TreeSet(comparator);
    TreeSet set6 = new TreeSet(comparator);
    TreeSet set7 = new TreeSet(comparator);
    for(int i=0 ; i<2*n ; i++) {
      t5 = add(array[i], t5);
      set5.add(array[i]);
    }
    for(int i=n ; i<3*n ; i++) {
      t6 = add(array[i], t6);
      set6.add(array[i]);
    }
    for(int i=n ; i<2*n ; i++) {
      t7 = add(array[i], t7);
      set6.add(array[i]);
    }
    
    startChrono = System.currentTimeMillis();
    JGSet t8 = union(t5, t6);
    stopChrono = System.currentTimeMillis();
    if (t1 == t8) {System.out.println("Complex union for JGSet in " + (stopChrono-startChrono) + " ms");} else {System.out.println("Complex union failed with \n\tt5:"+t5+" \nand \tt6: "+t6+"\n\tres: "+t8+"\n\tbuilt: "+t1);}

    startChrono = System.currentTimeMillis();
    set5.addAll(set6);
    stopChrono = System.currentTimeMillis();
    System.out.println("Complex union for java JGSet in " + (stopChrono-startChrono) + " ms");


    startChrono = System.currentTimeMillis();
    JGSet t9 = intersection(t5, t6);
    stopChrono = System.currentTimeMillis();
    if (t7 == t9) {System.out.println("Complex intersection for JGSet in " + (stopChrono-startChrono) + " ms");} else {System.out.println("Complex intersection");}// failed with t5:\t"+t5+"\nt6:\t"+t6+"\nres:\t"+t9+"\nbuilt:\t"+t7);}

    startChrono = System.currentTimeMillis();
    set5.containsAll(set6);
    stopChrono = System.currentTimeMillis();
    System.out.println("Complex intersection for java JGSet in " + (stopChrono-startChrono) + " ms");
    
  }

  class MyComparator implements Comparator {
    public int compare(Object o1, Object o2) {
      if(o1==o2) {
        return 0;
      }

      int ho1 = ((ATerm)o1).getUniqueIdentifier();
      int ho2 = ((ATerm)o2).getUniqueIdentifier();
      
      if(ho1 < ho2) {
        return -1;
      } else if(ho1 > ho2) {
        return 1;
      } else {
        System.out.println("hum");
      }
      return 1;
    }
  }

}

