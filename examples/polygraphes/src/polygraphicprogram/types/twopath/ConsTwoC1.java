/* Generated by TOM (version 20071002 (src)): Do not edit this file */

package polygraphicprogram.types.twopath;


public final class ConsTwoC1 extends polygraphicprogram.types.twopath.TwoC1 implements tom.library.sl.Visitable  {

private ConsTwoC1() {}

private int hashCode;
private static ConsTwoC1 proto = new ConsTwoC1();
/* Generated by TOM (version 20071002 (src)): Do not edit this file */
/* Generated by TOM (version 20071002 (src)): Do not edit this file */
private static boolean tom_equal_term_char(char t1, char t2) { return (t1==t2) 
;
}
private static boolean tom_is_sort_char(char t) { return  true ;
}
private static boolean tom_equal_term_String(String t1, String t2) { return (t1.equals(t2)) 
;
}
private static boolean tom_is_sort_String(String t) { return  t instanceof String ;
}
private static boolean tom_equal_term_ThreePath(Object t1, Object t2) { return t1.equals(t2) 
;
}
private static boolean tom_is_sort_ThreePath(Object t) { return  t instanceof polygraphicprogram.types.ThreePath ;
}
private static boolean tom_equal_term_CellType(Object t1, Object t2) { return t1.equals(t2) 
;
}
private static boolean tom_is_sort_CellType(Object t) { return  t instanceof polygraphicprogram.types.CellType ;
}
private static boolean tom_equal_term_TwoPath(Object t1, Object t2) { return t1.equals(t2) 
;
}
private static boolean tom_is_sort_TwoPath(Object t) { return  t instanceof polygraphicprogram.types.TwoPath ;
}
private static boolean tom_equal_term_OnePath(Object t1, Object t2) { return t1.equals(t2) 
;
}
private static boolean tom_is_sort_OnePath(Object t) { return  t instanceof polygraphicprogram.types.OnePath ;
}
private static boolean tom_is_fun_sym_TwoId( polygraphicprogram.types.TwoPath  t) { return  (t instanceof polygraphicprogram.types.twopath.TwoId) ;
}
private static  polygraphicprogram.types.TwoPath  tom_make_TwoId( polygraphicprogram.types.OnePath  t0) { return  polygraphicprogram.types.twopath.TwoId.make(t0) ; }private static  polygraphicprogram.types.OnePath  tom_get_slot_TwoId_onePath( polygraphicprogram.types.TwoPath  t) { return  t.getonePath() ;
}
private static boolean tom_is_fun_sym_Id( polygraphicprogram.types.OnePath  t) { return  (t instanceof polygraphicprogram.types.onepath.Id) ;
}
private static  polygraphicprogram.types.OnePath  tom_make_Id() { return  polygraphicprogram.types.onepath.Id.make() ; }private static boolean tom_is_fun_sym_TwoC0( polygraphicprogram.types.TwoPath  t) { return  ((t instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (t instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) ;
}
private static  polygraphicprogram.types.TwoPath  tom_empty_list_TwoC0() { return polygraphicprogram.types.twopath.EmptyTwoC0.make() 

; }private static  polygraphicprogram.types.TwoPath  tom_cons_list_TwoC0( polygraphicprogram.types.TwoPath  e,  polygraphicprogram.types.TwoPath  l) { return  polygraphicprogram.types.twopath.ConsTwoC0.make(e,l) ; }private static  polygraphicprogram.types.TwoPath  tom_get_head_TwoC0_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.getHeadTwoC0() ;
}
private static  polygraphicprogram.types.TwoPath  tom_get_tail_TwoC0_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.getTailTwoC0() ;
}
private static boolean tom_is_empty_TwoC0_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.isEmptyTwoC0() ;
}

  private static   polygraphicprogram.types.TwoPath  tom_append_list_TwoC0( polygraphicprogram.types.TwoPath l1,  polygraphicprogram.types.TwoPath  l2) {
    if( l1.isEmptyTwoC0() ) {
      return l2;
    } else if( l2.isEmptyTwoC0() ) {
      return l1;
    } else if( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) ) {
      if( (( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? l1.getTailTwoC0() :tom_empty_list_TwoC0()).isEmptyTwoC0() ) {
        return  polygraphicprogram.types.twopath.ConsTwoC0.make((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? l1.getHeadTwoC0() :l1),l2) ;
      } else {
        return  polygraphicprogram.types.twopath.ConsTwoC0.make((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? l1.getHeadTwoC0() :l1),tom_append_list_TwoC0((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? l1.getTailTwoC0() :tom_empty_list_TwoC0()),l2)) ;
      }
    } else {
      return  polygraphicprogram.types.twopath.ConsTwoC0.make(l1,l2) ;
    }
  }
  private static   polygraphicprogram.types.TwoPath  tom_get_slice_TwoC0( polygraphicprogram.types.TwoPath  begin,  polygraphicprogram.types.TwoPath  end, polygraphicprogram.types.TwoPath  tail) {
    if(tom_equal_term_TwoPath(begin,end)) {
      return tail;
    } else {
      return  polygraphicprogram.types.twopath.ConsTwoC0.make((( ((begin instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (begin instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? begin.getHeadTwoC0() :begin),( polygraphicprogram.types.TwoPath )tom_get_slice_TwoC0((( ((begin instanceof polygraphicprogram.types.twopath.ConsTwoC0) || (begin instanceof polygraphicprogram.types.twopath.EmptyTwoC0)) )? begin.getTailTwoC0() :tom_empty_list_TwoC0()),end,tail)) ;
    }
  }
  private static boolean tom_is_fun_sym_TwoC1( polygraphicprogram.types.TwoPath  t) { return  ((t instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (t instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) ;
}
private static  polygraphicprogram.types.TwoPath  tom_empty_list_TwoC1() { return polygraphicprogram.types.twopath.EmptyTwoC1.make() 

; }private static  polygraphicprogram.types.TwoPath  tom_cons_list_TwoC1( polygraphicprogram.types.TwoPath  e,  polygraphicprogram.types.TwoPath  l) { return  polygraphicprogram.types.twopath.ConsTwoC1.make(e,l) ; }private static  polygraphicprogram.types.TwoPath  tom_get_head_TwoC1_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.getHeadTwoC1() ;
}
private static  polygraphicprogram.types.TwoPath  tom_get_tail_TwoC1_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.getTailTwoC1() ;
}
private static boolean tom_is_empty_TwoC1_TwoPath( polygraphicprogram.types.TwoPath  l) { return  l.isEmptyTwoC1() ;
}

  private static   polygraphicprogram.types.TwoPath  tom_append_list_TwoC1( polygraphicprogram.types.TwoPath l1,  polygraphicprogram.types.TwoPath  l2) {
    if( l1.isEmptyTwoC1() ) {
      return l2;
    } else if( l2.isEmptyTwoC1() ) {
      return l1;
    } else if( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) ) {
      if( (( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? l1.getTailTwoC1() :tom_empty_list_TwoC1()).isEmptyTwoC1() ) {
        return  polygraphicprogram.types.twopath.ConsTwoC1.make((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? l1.getHeadTwoC1() :l1),l2) ;
      } else {
        return  polygraphicprogram.types.twopath.ConsTwoC1.make((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? l1.getHeadTwoC1() :l1),tom_append_list_TwoC1((( ((l1 instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (l1 instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? l1.getTailTwoC1() :tom_empty_list_TwoC1()),l2)) ;
      }
    } else {
      return  polygraphicprogram.types.twopath.ConsTwoC1.make(l1,l2) ;
    }
  }
  private static   polygraphicprogram.types.TwoPath  tom_get_slice_TwoC1( polygraphicprogram.types.TwoPath  begin,  polygraphicprogram.types.TwoPath  end, polygraphicprogram.types.TwoPath  tail) {
    if(tom_equal_term_TwoPath(begin,end)) {
      return tail;
    } else {
      return  polygraphicprogram.types.twopath.ConsTwoC1.make((( ((begin instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (begin instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? begin.getHeadTwoC1() :begin),( polygraphicprogram.types.TwoPath )tom_get_slice_TwoC1((( ((begin instanceof polygraphicprogram.types.twopath.ConsTwoC1) || (begin instanceof polygraphicprogram.types.twopath.EmptyTwoC1)) )? begin.getTailTwoC1() :tom_empty_list_TwoC1()),end,tail)) ;
    }
  }
  
private polygraphicprogram.types.TwoPath _HeadTwoC1;
private polygraphicprogram.types.TwoPath _TailTwoC1;

/* static constructor */

private static ConsTwoC1 realMake(polygraphicprogram.types.TwoPath _HeadTwoC1, polygraphicprogram.types.TwoPath _TailTwoC1) {
proto.initHashCode( _HeadTwoC1,  _TailTwoC1);
return (ConsTwoC1) factory.build(proto);
}

public static polygraphicprogram.types.TwoPath make(polygraphicprogram.types.TwoPath x, polygraphicprogram.types.TwoPath y) {
if (true) { {
if(y!=
tom_make_TwoId(tom_make_Id())&&x.target()!=y.source())
{	System.out.println(x);
System.out.println(y);
throw new RuntimeException("composition of incompatible 2-Paths");
}
if (tom_is_sort_TwoPath(x)) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumberfreshSubject_1=(( polygraphicprogram.types.TwoPath )x);
if (tom_is_sort_TwoPath(y)) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumberfreshSubject_2=(( polygraphicprogram.types.TwoPath )y);
if (tom_is_fun_sym_TwoId(tomMatch79NameNumberfreshSubject_1)) {
{  polygraphicprogram.types.OnePath  tomMatch79NameNumber_freshVar_0=tom_get_slot_TwoId_onePath(tomMatch79NameNumberfreshSubject_1);
if (tom_is_fun_sym_Id(tomMatch79NameNumber_freshVar_0)) {
if ( true ) {
return 
tomMatch79NameNumberfreshSubject_2; 

}
}
}
}
if (tom_is_fun_sym_TwoId(tomMatch79NameNumberfreshSubject_2)) {
{  polygraphicprogram.types.OnePath  tomMatch79NameNumber_freshVar_1=tom_get_slot_TwoId_onePath(tomMatch79NameNumberfreshSubject_2);
if (tom_is_fun_sym_Id(tomMatch79NameNumber_freshVar_1)) {
if ( true ) {
return 
tomMatch79NameNumberfreshSubject_1; 

}
}
}
}
if (tom_is_fun_sym_TwoC1(tomMatch79NameNumberfreshSubject_1)) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumber_freshVar_2=tomMatch79NameNumberfreshSubject_1;
if (!( ( tom_is_empty_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_2) || tom_equal_term_TwoPath(tomMatch79NameNumber_freshVar_2, tom_empty_list_TwoC1()) ) )) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumber_freshVar_3=((tom_is_fun_sym_TwoC1(tomMatch79NameNumber_freshVar_2))?(tom_get_tail_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_2)):(tom_empty_list_TwoC1()));
if (!( ( tom_is_empty_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_3) || tom_equal_term_TwoPath(tomMatch79NameNumber_freshVar_3, tom_empty_list_TwoC1()) ) )) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumber_freshVar_4=((tom_is_fun_sym_TwoC1(tomMatch79NameNumber_freshVar_3))?(tom_get_tail_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_3)):(tom_empty_list_TwoC1()));
if ( ( tom_is_empty_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_4) || tom_equal_term_TwoPath(tomMatch79NameNumber_freshVar_4, tom_empty_list_TwoC1()) ) ) {
if ( true ) {
return 
tom_cons_list_TwoC1(((tom_is_fun_sym_TwoC1(tomMatch79NameNumber_freshVar_2))?(tom_get_head_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_2)):(tomMatch79NameNumber_freshVar_2)),tom_cons_list_TwoC1(tom_cons_list_TwoC1(((tom_is_fun_sym_TwoC1(tomMatch79NameNumber_freshVar_3))?(tom_get_head_TwoC1_TwoPath(tomMatch79NameNumber_freshVar_3)):(tomMatch79NameNumber_freshVar_3)),tom_cons_list_TwoC1(tomMatch79NameNumberfreshSubject_2,tom_empty_list_TwoC1())),tom_empty_list_TwoC1())); 

}
}
}
}
}
}
}
}
if (tom_is_fun_sym_TwoC0(tomMatch79NameNumberfreshSubject_1)) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumber_freshVar_5=tomMatch79NameNumberfreshSubject_1;
if ( ( tom_is_empty_TwoC0_TwoPath(tomMatch79NameNumber_freshVar_5) || tom_equal_term_TwoPath(tomMatch79NameNumber_freshVar_5, tom_empty_list_TwoC0()) ) ) {
if ( true ) {
return 
tomMatch79NameNumberfreshSubject_2; 

}
}
}
}
if (tom_is_fun_sym_TwoC0(tomMatch79NameNumberfreshSubject_2)) {
{  polygraphicprogram.types.TwoPath  tomMatch79NameNumber_freshVar_6=tomMatch79NameNumberfreshSubject_2;
if ( ( tom_is_empty_TwoC0_TwoPath(tomMatch79NameNumber_freshVar_6) || tom_equal_term_TwoPath(tomMatch79NameNumber_freshVar_6, tom_empty_list_TwoC0()) ) ) {
if ( true ) {
return 
tomMatch79NameNumberfreshSubject_1; 

}
}
}
}

}
}
}
}

}}
return realMake( x,  y);
}

private void init(polygraphicprogram.types.TwoPath _HeadTwoC1, polygraphicprogram.types.TwoPath _TailTwoC1, int hashCode) {
this._HeadTwoC1 = _HeadTwoC1;
this._TailTwoC1 = _TailTwoC1;

this.hashCode = hashCode;
}

private void initHashCode(polygraphicprogram.types.TwoPath _HeadTwoC1, polygraphicprogram.types.TwoPath _TailTwoC1) {
this._HeadTwoC1 = _HeadTwoC1;
this._TailTwoC1 = _TailTwoC1;

this.hashCode = hashFunction();
}

/* name and arity */
@Override
public String symbolName() {
return "ConsTwoC1";
}

private int getArity() {
return 2;
}

public shared.SharedObject duplicate() {
ConsTwoC1 clone = new ConsTwoC1();
clone.init( _HeadTwoC1,  _TailTwoC1, hashCode);
return clone;
}



/**
* This method implements a lexicographic order
*/
@Override
public int compareToLPO(Object o) {
/*
* We do not want to compare with any object, only members of the module
* In case of invalid argument, throw a ClassCastException, as the java api
* asks for it
*/
polygraphicprogram.PolygraphicProgramAbstractType ao = (polygraphicprogram.PolygraphicProgramAbstractType) o;
/* return 0 for equality */
if (ao == this)
return 0;
/* compare the symbols */
int symbCmp = this.symbolName().compareTo(ao.symbolName());
if (symbCmp != 0)
return symbCmp;
/* compare the childs */
ConsTwoC1 tco = (ConsTwoC1) ao;
int _HeadTwoC1Cmp = (this._HeadTwoC1).compareToLPO(tco._HeadTwoC1);
if(_HeadTwoC1Cmp != 0)
return _HeadTwoC1Cmp;

int _TailTwoC1Cmp = (this._TailTwoC1).compareToLPO(tco._TailTwoC1);
if(_TailTwoC1Cmp != 0)
return _TailTwoC1Cmp;

throw new RuntimeException("Unable to compare");
}

@Override
public int compareTo(Object o) {
/*
* We do not want to compare with any object, only members of the module
* In case of invalid argument, throw a ClassCastException, as the java api
* asks for it
*/
polygraphicprogram.PolygraphicProgramAbstractType ao = (polygraphicprogram.PolygraphicProgramAbstractType) o;
/* return 0 for equality */
if (ao == this)
return 0;
/* use the hash values to discriminate */

if(hashCode != ao.hashCode())
return (hashCode < ao.hashCode())?-1:1;

/* If not, compare the symbols : back to the normal order */
int symbCmp = this.symbolName().compareTo(ao.symbolName());
if (symbCmp != 0)
return symbCmp;
/* last resort: compare the childs */
ConsTwoC1 tco = (ConsTwoC1) ao;
int _HeadTwoC1Cmp = (this._HeadTwoC1).compareTo(tco._HeadTwoC1);
if(_HeadTwoC1Cmp != 0)
return _HeadTwoC1Cmp;

int _TailTwoC1Cmp = (this._TailTwoC1).compareTo(tco._TailTwoC1);
if(_TailTwoC1Cmp != 0)
return _TailTwoC1Cmp;

throw new RuntimeException("Unable to compare");
}

/* shared.SharedObject */
@Override
public final int hashCode() {
return hashCode;
}

public final boolean equivalent(shared.SharedObject obj) {
if(obj instanceof ConsTwoC1) {

ConsTwoC1 peer = (ConsTwoC1) obj;
return _HeadTwoC1==peer._HeadTwoC1 && _TailTwoC1==peer._TailTwoC1 && true;
}
return false;
}

/* TwoPath interface */
@Override
public boolean isConsTwoC1() {
return true;
}

@Override
public polygraphicprogram.types.TwoPath getHeadTwoC1() {
return _HeadTwoC1;
}

@Override
public polygraphicprogram.types.TwoPath setHeadTwoC1(polygraphicprogram.types.TwoPath set_arg) {
return make(set_arg, _TailTwoC1);
}
@Override
public polygraphicprogram.types.TwoPath getTailTwoC1() {
return _TailTwoC1;
}

@Override
public polygraphicprogram.types.TwoPath setTailTwoC1(polygraphicprogram.types.TwoPath set_arg) {
return make(_HeadTwoC1, set_arg);
}
/* AbstractType */
@Override
public aterm.ATerm toATerm() {
return atermFactory.makeAppl(
atermFactory.makeAFun(symbolName(),getArity(),false),
new aterm.ATerm[] {getHeadTwoC1().toATerm(), getTailTwoC1().toATerm()});
}

public static polygraphicprogram.types.TwoPath fromTerm(aterm.ATerm trm) {
if(trm instanceof aterm.ATermAppl) {
aterm.ATermAppl appl = (aterm.ATermAppl) trm;
if(proto.symbolName().equals(appl.getName())) {
return make(
polygraphicprogram.types.TwoPath.fromTerm(appl.getArgument(0)), polygraphicprogram.types.TwoPath.fromTerm(appl.getArgument(1))
);
}
}
return null;
}


/* Visitable */
public int getChildCount() {
return 2;
}

public tom.library.sl.Visitable getChildAt(int index) {
switch(index) {
case 0: return _HeadTwoC1;
case 1: return _TailTwoC1;

default: throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable v) {
switch(index) {
case 0: return make((polygraphicprogram.types.TwoPath) v, _TailTwoC1);
case 1: return make(_HeadTwoC1, (polygraphicprogram.types.TwoPath) v);

default: throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] childs) {
if (childs.length == 2) {
return make((polygraphicprogram.types.TwoPath) childs[0], (polygraphicprogram.types.TwoPath) childs[1]);
} else {
throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable[] getChildren() {
return new tom.library.sl.Visitable[] {  _HeadTwoC1,  _TailTwoC1 };
}

/* internal use */
protected  int hashFunction() {
int a, b, c;
/* Set up the internal state */
a = 0x9e3779b9; /* the golden ratio; an arbitrary value */
b = (-235880883<<8);
c = getArity();
/* -------------------------------------- handle most of the key */
/* ------------------------------------ handle the last 11 bytes */
a += (_HeadTwoC1.hashCode() << 8);
a += (_TailTwoC1.hashCode());

a -= b; a -= c; a ^= (c >> 13);
b -= c; b -= a; b ^= (a << 8);
c -= a; c -= b; c ^= (b >> 13);
a -= b; a -= c; a ^= (c >> 12);
b -= c; b -= a; b ^= (a << 16);
c -= a; c -= b; c ^= (b >> 5);
a -= b; a -= c; a ^= (c >> 3);
b -= c; b -= a; b ^= (a << 10);
c -= a; c -= b; c ^= (b >> 15);
/* ------------------------------------------- report the result */
return c;
}

}
