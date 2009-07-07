package mi3.gom;

import base.data.types.t1.*;
import base.data.types.t2.*;
import base.data.types.listt1.*;
import base.data.types.T2;
import base.data.types.T1;
import base.data.types.ListT1;
import tom.library.sl.Introspector;

import mi3.mapping.*;

public class Module {
  /*
   * this file is hand-written (or generated for Gom data-structures)
   */

  //implementation of the interfaces defined by users
  public static class a_Mapping extends Mapping0<T1> {
    public Class getImplementation() {
      return a.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof a;
    }

    public T1 make() {
      return a.make();
    }

  }

  /** ------------------------------ */

  public static class b_Mapping extends Mapping0<T2> {
    public Class getImplementation() {
      return b.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof b;
    }

    public T2 make() {
      return b.make();
    }

  }

  /** ------------------------------ */

  public static class f_Mapping extends Mapping2<T1, T1, T2> {
    public Class getImplementation() {
      return f.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof f;
    }

    public T1 make(T1 t1, T2 t2) {
      return f.make(t1, t2);
    }

    public T1 get0(T1 t) {
      return t.gets1(); 
    }

    public T2 get1(T1 t) {
      return t.gets2(); 
    }

  }

  /** ------------------------------ */
  public static class g_Mapping extends Mapping1<T2, T2> {
    public Class getImplementation() {
      return g.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof g;
    }

    public T2 make(T2 t2) {
      return g.make(t2);
    }

    public T2 get0(T2 t) {
      return t.gets2();
    }

  }

  /** ------------------------------ */
  public static class h_Mapping extends Mapping1<T2, ListT1> {
    public Class getImplementation() {
      return h.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof h;
    }

    public T2 make(ListT1 l) {
      return h.make(l);
    }

    public ListT1 get0(T2 t) {
      return t.getts();
    }

  }

  /** ------------------------------ */
  public static class concT1_Mapping extends ListMapping<ListT1, T1> {
    public Class getImplementation() {
      return concT1.class;
    }

    public boolean isInstanceOf(Object subject) {
      return subject instanceof concT1;
    }

    public boolean isEmpty(ListT1 l) {
      return l.isEmptyconcT1();
    }

    public ListT1 makeEmpty() {
      return base.data.types.listt1.EmptyconcT1.make();
    }

    public ListT1 makeInsert(T1 o, ListT1 l) {
      return base.data.types.listt1.ConsconcT1.make(o,l);
    }

    public T1 getHead(ListT1 l) {
      return l.getHeadconcT1();
    }

    public ListT1 getTail(ListT1 l) {
      return l.getTailconcT1();
    }

  }

  /*
   * Not used at present
   */
  // split SL introspectors
  public interface Introspector1 {
    public <T> T setChildren(T o, Object[] children);
    public Object[] getChildren(Object o);
  }

  public interface Introspector2 {
    public <T> T setChildAt(T o, int i, Object child);
    public Object getChildAt(Object o, int i);
    public int getChildCount(Object o);
  }

  public class IntrospectorFactory {

    public Introspector makeFromIntrospector1(final Introspector1 introspector) {
      return new Introspector() {

        public <T> T setChildAt(T o, int i, Object child) {
          Object[] children = getChildren(o);
          children[i] = child;
          return setChildren(o, children);
        }

        public Object getChildAt(Object o, int i) {
          return getChildren(o)[i];
        }

        public int getChildCount(Object o) {
          return getChildren(o).length;
        }

        public <T> T setChildren(T o, Object[] children) {
          return introspector.setChildren(o, children);
        }

        public Object[] getChildren(Object o) {
          return introspector.getChildren(o);
        }
      };
    }

    public Introspector makeFromInstrospector2(final Introspector2 introspector) {
      return new Introspector() {

        public <T> T setChildAt(T o, int i, Object child) {
          return introspector.setChildAt(o, i, child);
        }

        public Object getChildAt(Object o, int i) {
          return introspector.getChildAt(o, i);
        }

        public int getChildCount(Object o) {
          return introspector.getChildCount(o);
        }

        //FIX in case of normalization rules
        public <T> T setChildren(T o, Object[] children) {
          for (int i = 0; i<children.length ; i++) {
            o = setChildAt(o, i, children[i]);
          }
          return o;
        }

        public Object[] getChildren(Object o) {
          Object[] children = new Object[getChildCount(o)];
          for (int i = 0; i< getChildCount(o); i++) {
            children[i] = getChildAt(o, i);
          }
          return children;
        }
      };
    }

  }
}
