/* Generated by TOM (version 20071002 (src)): Do not edit this file */

package polygraphicprogram.types;        

//import polygraphicprogram.types.onepath.*;
//import polygraphicprogram.*;

public abstract class OnePath extends polygraphicprogram.PolygraphicProgramAbstractType {

public boolean defined(){return true;}

@Override
public polygraphicprogram.PolygraphicProgramAbstractType accept(polygraphicprogram.PolygraphicProgramVisitor v) throws tom.library.sl.VisitFailure {
return v.visit_OnePath(this);
}

public boolean isId() {
return false;
}


public boolean isOneCell() {
return false;
}


public boolean isConsOneC0() {
return false;
}


public boolean isEmptyOneC0() {
return false;
}


public polygraphicprogram.types.OnePath getHeadOneC0() {
throw new UnsupportedOperationException("This OnePath has no HeadOneC0");
}

public OnePath setHeadOneC0(polygraphicprogram.types.OnePath _arg) {
throw new UnsupportedOperationException("This OnePath has no HeadOneC0");
}


public String getName() {
throw new UnsupportedOperationException("This OnePath has no Name");
}

public OnePath setName(String _arg) {
throw new UnsupportedOperationException("This OnePath has no Name");
}


public polygraphicprogram.types.OnePath getTailOneC0() {
throw new UnsupportedOperationException("This OnePath has no TailOneC0");
}

public OnePath setTailOneC0(polygraphicprogram.types.OnePath _arg) {
throw new UnsupportedOperationException("This OnePath has no TailOneC0");
}


public static polygraphicprogram.types.OnePath fromTerm(aterm.ATerm trm) {
polygraphicprogram.types.OnePath tmp;

tmp = polygraphicprogram.types.onepath.Id.fromTerm(trm);
if (tmp != null) {
return tmp;
}

tmp = polygraphicprogram.types.onepath.OneCell.fromTerm(trm);
if (tmp != null) {
return tmp;
}

tmp = polygraphicprogram.types.onepath.ConsOneC0.fromTerm(trm);
if (tmp != null) {
return tmp;
}

tmp = polygraphicprogram.types.onepath.EmptyOneC0.fromTerm(trm);
if (tmp != null) {
return tmp;
}

tmp = polygraphicprogram.types.onepath.OneC0.fromTerm(trm);
if (tmp != null) {
return tmp;
}

throw new IllegalArgumentException("This is not a OnePath " + trm);
}

public static polygraphicprogram.types.OnePath fromString(String s) {
return fromTerm(atermFactory.parse(s));
}

public static polygraphicprogram.types.OnePath fromStream(java.io.InputStream stream) throws java.io.IOException {
return fromTerm(atermFactory.readFromFile(stream));
}


public int length() {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

public polygraphicprogram.types.OnePath reverse() {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

/**
* Collection
*/
/*
public boolean add(Object o) {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public boolean addAll(java.util.Collection c) {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public void clear() {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public boolean containsAll(java.util.Collection c) {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

public boolean contains(Object o) {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

public boolean equals(Object o) { return this == o; }

public int hashCode() { return hashCode(); }

public boolean isEmpty() { return false; }

public java.util.Iterator iterator() {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

public boolean remove(Object o) {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public boolean removeAll(java.util.Collection c) {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public boolean retainAll(java.util.Collection c) {
throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
}

public int size() { return length(); }

public Object[] toArray() {
throw new IllegalArgumentException(
"This "+this.getClass().getName()+" is not a list");
}

public Object[] toArray(Object[] a) {
throw new UnsupportedOperationException("Not yet implemented");
}
*/

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


}
