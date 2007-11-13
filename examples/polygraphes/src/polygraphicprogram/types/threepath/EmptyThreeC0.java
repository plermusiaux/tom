/* Generated by TOM (version 20071002 (src)): Do not edit this file */

package polygraphicprogram.types.threepath;


public final class EmptyThreeC0 extends polygraphicprogram.types.threepath.ThreeC0 implements tom.library.sl.Visitable  {

private EmptyThreeC0() {}

private static int hashCode = hashFunction();
private static EmptyThreeC0 proto = (EmptyThreeC0) factory.build(new EmptyThreeC0());
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
private static  polygraphicprogram.types.ThreePath  tom_make_ThreeId( polygraphicprogram.types.TwoPath  t0) { return  polygraphicprogram.types.threepath.ThreeId.make(t0) ; }private static  polygraphicprogram.types.TwoPath  tom_make_TwoId( polygraphicprogram.types.OnePath  t0) { return  polygraphicprogram.types.twopath.TwoId.make(t0) ; }private static  polygraphicprogram.types.OnePath  tom_make_Id() { return  polygraphicprogram.types.onepath.Id.make() ; }

/* static constructor */

public static EmptyThreeC0 realMake() {
return proto;
}

public static polygraphicprogram.types.ThreePath make() {
if (true) {}if (true) { { return 
tom_make_ThreeId(tom_make_TwoId(tom_make_Id())); }}
return realMake();
}

/* name and arity */
@Override
public String symbolName() {
return "EmptyThreeC0";
}

private static int getArity() {
return 0;
}

public shared.SharedObject duplicate() {
return new EmptyThreeC0();
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

throw new RuntimeException("Unable to compare");
}

/* shared.SharedObject */
@Override
public final int hashCode() {
return hashCode;
}

public final boolean equivalent(shared.SharedObject obj) {
if(obj instanceof EmptyThreeC0) {

return true;
}
return false;
}

/* ThreePath interface */
@Override
public boolean isEmptyThreeC0() {
return true;
}

/* AbstractType */
@Override
public aterm.ATerm toATerm() {
return atermFactory.makeAppl(
atermFactory.makeAFun(symbolName(),getArity(),false),
new aterm.ATerm[] {});
}

public static polygraphicprogram.types.ThreePath fromTerm(aterm.ATerm trm) {
if(trm instanceof aterm.ATermAppl) {
aterm.ATermAppl appl = (aterm.ATermAppl) trm;
if(proto.symbolName().equals(appl.getName())) {
return make(

);
}
}
return null;
}


/* Visitable */
public int getChildCount() {
return 0;
}

public tom.library.sl.Visitable getChildAt(int index) {
switch(index) {

default: throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable v) {
switch(index) {

default: throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] childs) {
if (childs.length == 0) {
return make();
} else {
throw new IndexOutOfBoundsException();
}
}

public tom.library.sl.Visitable[] getChildren() {
return new tom.library.sl.Visitable[] {  };
}

/* internal use */
protected static int hashFunction() {
int a, b, c;
/* Set up the internal state */
a = 0x9e3779b9; /* the golden ratio; an arbitrary value */
b = (486409695<<8);
c = getArity();
/* -------------------------------------- handle most of the key */
/* ------------------------------------ handle the last 11 bytes */

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
