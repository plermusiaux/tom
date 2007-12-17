/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006-2007, INRIA
 * Nancy, France.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 *
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom.backend.shared;

import java.io.*;
import java.util.*;

import tom.gom.backend.TemplateClass;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import tom.platform.OptionManager;

public class SortTemplate extends TemplateHookedClass {
  ClassName abstractType;
  ClassName visitor;
  ClassNameList operatorList;
  ClassNameList variadicOperatorList;
  SlotFieldList slotList;

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.objects.types.ClassNameList  tom_append_list_ConcClassName( tom.gom.adt.objects.types.ClassNameList l1,  tom.gom.adt.objects.types.ClassNameList  l2) {     if( l1.isEmptyConcClassName() ) {       return l2;     } else if( l2.isEmptyConcClassName() ) {       return l1;     } else if(  l1.getTailConcClassName() .isEmptyConcClassName() ) {       return  tom.gom.adt.objects.types.classnamelist.ConsConcClassName.make( l1.getHeadConcClassName() ,l2) ;     } else {       return  tom.gom.adt.objects.types.classnamelist.ConsConcClassName.make( l1.getHeadConcClassName() ,tom_append_list_ConcClassName( l1.getTailConcClassName() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.ClassNameList  tom_get_slice_ConcClassName( tom.gom.adt.objects.types.ClassNameList  begin,  tom.gom.adt.objects.types.ClassNameList  end, tom.gom.adt.objects.types.ClassNameList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.objects.types.classnamelist.ConsConcClassName.make( begin.getHeadConcClassName() ,( tom.gom.adt.objects.types.ClassNameList )tom_get_slice_ConcClassName( begin.getTailConcClassName() ,end,tail)) ;     }   }    

  public SortTemplate(File tomHomePath,
                      OptionManager manager,
                      List importList, 	
                      GomClass gomClass,
                      TemplateClass mapping) {
    super(gomClass,manager,tomHomePath,importList,mapping);
    {if ( (gomClass instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch397NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )gomClass);if ( (tomMatch397NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_0= tomMatch397NameNumberfreshSubject_1.getAbstractType() ;{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_1= tomMatch397NameNumberfreshSubject_1.getVisitor() ;{  tom.gom.adt.objects.types.ClassNameList  tomMatch397NameNumber_freshVar_2= tomMatch397NameNumberfreshSubject_1.getOperators() ;{  tom.gom.adt.objects.types.ClassNameList  tomMatch397NameNumber_freshVar_3= tomMatch397NameNumberfreshSubject_1.getVariadicOperators() ;{  tom.gom.adt.objects.types.SlotFieldList  tomMatch397NameNumber_freshVar_4= tomMatch397NameNumberfreshSubject_1.getSlotFields() ;if ( true ) {





        this.abstractType = tomMatch397NameNumber_freshVar_0;
        this.visitor = tomMatch397NameNumber_freshVar_1;
        this.operatorList = tomMatch397NameNumber_freshVar_2;
        this.variadicOperatorList = tomMatch397NameNumber_freshVar_3;
        this.slotList = tomMatch397NameNumber_freshVar_4;
        return;
      }}}}}}}}}}

    throw new GomRuntimeException(
        "Bad argument for SortTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write("\npackage "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getPackage()+";        \n"/* Generated by TOM (version 2.6alpha): Do not edit this file */+generateImport()+"\n//import "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getPackage()+"."/* Generated by TOM (version 2.6alpha): Do not edit this file */+className().toLowerCase()+".*;\n//import "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getPackage().substring(0,getPackage().lastIndexOf("."))+".*;\n\npublic abstract class "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" extends "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(abstractType)+" {\n\n"/* Generated by TOM (version 2.6alpha): Do not edit this file */+generateBlock()+"\n  @Override\n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(abstractType)+" accept("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(visitor)+" v,tom.library.sl.Introspector i) throws tom.library.sl.VisitFailure {\n    return v."/* Generated by TOM (version 2.6alpha): Do not edit this file */+visitMethod(className)+"(this,i);\n  }\n"












);
generateBody(writer);
writer.write("\n}\n"

);
  }

  public void generateBody(java.io.Writer writer) throws java.io.IOException {
    // methods for each operator
    ClassNameList consum = operatorList;
    while (!consum.isEmptyConcClassName()) {
      ClassName operatorName = consum.getHeadConcClassName();
      consum = consum.getTailConcClassName();

      writer.write("\n  public boolean "/* Generated by TOM (version 2.6alpha): Do not edit this file */+isOperatorMethod(operatorName)+"() {\n    return false;\n  }\n\n"




);
    }
    // methods for each slot
    SlotFieldList sl = slotList;
    while (!sl.isEmptyConcSlotField()) {
      SlotField slot = sl.getHeadConcSlotField();
      sl = sl.getTailConcSlotField();

      writer.write("\n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+slotDomain(slot)+" "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getMethod(slot)+"() {\n    throw new UnsupportedOperationException(\"This "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" has no "/* Generated by TOM (version 2.6alpha): Do not edit this file */+slot.getName()+"\");\n  }\n\n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" "/* Generated by TOM (version 2.6alpha): Do not edit this file */+setMethod(slot)+"("/* Generated by TOM (version 2.6alpha): Do not edit this file */+slotDomain(slot)+" _arg) {\n    throw new UnsupportedOperationException(\"This "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" has no "/* Generated by TOM (version 2.6alpha): Do not edit this file */+slot.getName()+"\");\n  }\n\n"








);

    }

    /* fromTerm method, dispatching to operator classes */
    writer.write("\n  public static "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" fromTerm(aterm.ATerm trm) {\n    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" tmp;\n"


);
    generateFromTerm(writer,"trm","tmp");
    writer.write("\n    throw new IllegalArgumentException(\"This is not a "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" \" + trm);\n  }\n\n  public static "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" fromString(String s) {\n    return fromTerm(atermFactory.parse(s));\n  }\n\n  public static "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" fromStream(java.io.InputStream stream) throws java.io.IOException {\n    return fromTerm(atermFactory.readFromFile(stream));\n  }\n\n"











);

    /* length and reverse prototypes, only usable on lists */
    writer.write("\n  public int length() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" reverse() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  /**\n   * Collection\n   */\n  /*\n  public boolean add(Object o) {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public boolean addAll(java.util.Collection c) {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public void clear() {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public boolean containsAll(java.util.Collection c) {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public boolean contains(Object o) {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public boolean equals(Object o) { return this == o; }\n\n  public int hashCode() { return hashCode(); }\n\n  public boolean isEmpty() { return false; }\n\n  public java.util.Iterator iterator() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public boolean remove(Object o) {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public boolean removeAll(java.util.Collection c) {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public boolean retainAll(java.util.Collection c) {\n    throw new UnsupportedOperationException(\"This object \"+this.getClass().getName()+\" is not mutable\");\n  }\n\n  public int size() { return length(); }\n\n  public Object[] toArray() {\n    throw new IllegalArgumentException(\n      \"This \"+this.getClass().getName()+\" is not a list\");\n  }\n\n  public Object[] toArray(Object[] a) {\n    throw new UnsupportedOperationException(\"Not yet implemented\");\n  }\n  */\n  "






































































);
  /*
    // methods for each variadic operator
    consum = variadicOperatorList;
    while(!consum.isEmptyConcClassName()) {
      ClassName operatorName = consum.getHeadConcClassName();
      consum = consum.getTailConcClassName();
      // look for the corresponding domain
matchblock: {
      %match(slotList) {
        ConcSlotField(_*,slot@SlotField[Name=opname,Domain=domain],_*) -> {
          if(`opname.equals("Head"+operatorName.getName())) {
      writer.write(%[
  public java.util.Collection<@primitiveToReferenceType(slotDomain(`slot))@> @getCollectionMethod(operatorName)@() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }
]%);
      break matchblock;
          }
        }
      }
      }
    }
  */
    if (!hooks.isEmptyConcHook()) {
      mapping.generate(writer); 
    }
  }

  private void generateFromTerm(java.io.Writer writer, String trm, String tmp) throws java.io.IOException {
    ClassNameList consum = tom_append_list_ConcClassName(operatorList,tom_append_list_ConcClassName(variadicOperatorList, tom.gom.adt.objects.types.classnamelist.EmptyConcClassName.make() ));
    while (!consum.isEmptyConcClassName()) {
      ClassName operatorName = consum.getHeadConcClassName();
      consum = consum.getTailConcClassName();
      writer.write("\n    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+tmp+" = "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(operatorName)+".fromTerm("/* Generated by TOM (version 2.6alpha): Do not edit this file */+trm+");\n    if ("/* Generated by TOM (version 2.6alpha): Do not edit this file */+tmp+" != null) {\n      return tmp;\n    }\n"




);
    }
  }

  public void generateTomMapping(Writer writer, ClassName basicStrategy)
      throws java.io.IOException {
    writer.write("\n%typeterm "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" {\n  implement { "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+" }\n  is_sort(t) { ($t instanceof "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName()+") }\n  equals(t1,t2) { $t1.equals($t2) }\n  visitor_fwd { "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(basicStrategy)+" }\n}\n"






);
    return;
  }
}
