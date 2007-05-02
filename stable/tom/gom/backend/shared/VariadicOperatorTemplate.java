/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
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
import java.util.logging.*;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.backend.TemplateClass;
import tom.gom.backend.CodeGen;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.adt.objects.types.*;

public class VariadicOperatorTemplate extends TemplateHookedClass {
  ClassName abstractType;
  ClassName sortName;
  GomClass empty;
  GomClass cons;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  private static boolean tom_equal_term_Code(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Code(Object t) { return  t instanceof tom.gom.adt.code.types.Code ;}private static boolean tom_equal_term_Hook(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Hook(Object t) { return  t instanceof tom.gom.adt.objects.types.Hook ;}private static boolean tom_equal_term_SlotField(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_SlotField(Object t) { return  t instanceof tom.gom.adt.objects.types.SlotField ;}private static boolean tom_equal_term_SlotFieldList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_SlotFieldList(Object t) { return  t instanceof tom.gom.adt.objects.types.SlotFieldList ;}private static boolean tom_equal_term_GomClass(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_GomClass(Object t) { return  t instanceof tom.gom.adt.objects.types.GomClass ;}private static boolean tom_equal_term_ClassName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ClassName(Object t) { return  t instanceof tom.gom.adt.objects.types.ClassName ;}private static boolean tom_equal_term_HookList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_HookList(Object t) { return  t instanceof tom.gom.adt.objects.types.HookList ;}private static boolean tom_is_fun_sym_MappingHook( tom.gom.adt.objects.types.Hook  t) { return  t instanceof tom.gom.adt.objects.types.hook.MappingHook ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_MappingHook_Code( tom.gom.adt.objects.types.Hook  t) { return  t.getCode() ;}private static boolean tom_is_fun_sym_SlotField( tom.gom.adt.objects.types.SlotField  t) { return  t instanceof tom.gom.adt.objects.types.slotfield.SlotField ;}private static  String  tom_get_slot_SlotField_Name( tom.gom.adt.objects.types.SlotField  t) { return  t.getName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SlotField_Domain( tom.gom.adt.objects.types.SlotField  t) { return  t.getDomain() ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) { return  t.getExtendsType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) { return  t.getVisitor() ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) { return  t.getSlots() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) { return  t.getEmpty() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) { return  t.getCons() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_concSlotField( tom.gom.adt.objects.types.SlotFieldList  t) { return  t instanceof tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField || t instanceof tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_empty_list_concSlotField() { return  tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ; }private static  tom.gom.adt.objects.types.SlotFieldList  tom_cons_list_concSlotField( tom.gom.adt.objects.types.SlotField  e,  tom.gom.adt.objects.types.SlotFieldList  l) { return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make(e,l) ; }private static  tom.gom.adt.objects.types.SlotField  tom_get_head_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) { return  l.getHeadconcSlotField() ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_tail_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) { return  l.getTailconcSlotField() ;}private static boolean tom_is_empty_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) { return  l.isEmptyconcSlotField() ;}   private static   tom.gom.adt.objects.types.SlotFieldList  tom_append_list_concSlotField( tom.gom.adt.objects.types.SlotFieldList l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {     if(tom_is_empty_concSlotField_SlotFieldList(l1)) {       return l2;     } else if(tom_is_empty_concSlotField_SlotFieldList(l2)) {       return l1;     } else if(tom_is_empty_concSlotField_SlotFieldList(tom_get_tail_concSlotField_SlotFieldList(l1))) {       return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(tom_get_head_concSlotField_SlotFieldList(l1),l2);     } else {       return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(tom_get_head_concSlotField_SlotFieldList(l1),tom_append_list_concSlotField(tom_get_tail_concSlotField_SlotFieldList(l1),l2));     }   }   private static   tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_concSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end, tom.gom.adt.objects.types.SlotFieldList  tail) {     if(tom_equal_term_SlotFieldList(begin,end)) {       return tail;     } else {       return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(tom_get_head_concSlotField_SlotFieldList(begin),( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_concSlotField(tom_get_tail_concSlotField_SlotFieldList(begin),end,tail));     }   }   private static boolean tom_is_fun_sym_concHook( tom.gom.adt.objects.types.HookList  t) { return  t instanceof tom.gom.adt.objects.types.hooklist.ConsconcHook || t instanceof tom.gom.adt.objects.types.hooklist.EmptyconcHook ;}private static  tom.gom.adt.objects.types.HookList  tom_empty_list_concHook() { return  tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() ; }private static  tom.gom.adt.objects.types.HookList  tom_cons_list_concHook( tom.gom.adt.objects.types.Hook  e,  tom.gom.adt.objects.types.HookList  l) { return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make(e,l) ; }private static  tom.gom.adt.objects.types.Hook  tom_get_head_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.getHeadconcHook() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_tail_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.getTailconcHook() ;}private static boolean tom_is_empty_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.isEmptyconcHook() ;}   private static   tom.gom.adt.objects.types.HookList  tom_append_list_concHook( tom.gom.adt.objects.types.HookList l1,  tom.gom.adt.objects.types.HookList  l2) {     if(tom_is_empty_concHook_HookList(l1)) {       return l2;     } else if(tom_is_empty_concHook_HookList(l2)) {       return l1;     } else if(tom_is_empty_concHook_HookList(tom_get_tail_concHook_HookList(l1))) {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(l1),l2);     } else {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(l1),tom_append_list_concHook(tom_get_tail_concHook_HookList(l1),l2));     }   }   private static   tom.gom.adt.objects.types.HookList  tom_get_slice_concHook( tom.gom.adt.objects.types.HookList  begin,  tom.gom.adt.objects.types.HookList  end, tom.gom.adt.objects.types.HookList  tail) {     if(tom_equal_term_HookList(begin,end)) {       return tail;     } else {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(begin),( tom.gom.adt.objects.types.HookList )tom_get_slice_concHook(tom_get_tail_concHook_HookList(begin),end,tail));     }   }    

  public VariadicOperatorTemplate(File tomHomePath,
                                  List importList, 	
                                  GomClass gomClass,
                                  TemplateClass mapping) {
    super(gomClass,tomHomePath,importList,mapping);
    if (tom_is_sort_GomClass(gomClass)) {{  tom.gom.adt.objects.types.GomClass  tomMatch359NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )gomClass);if (tom_is_fun_sym_VariadicOperatorClass(tomMatch359NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.ClassName  tomMatch359NameNumber_freshVar_0=tom_get_slot_VariadicOperatorClass_AbstractType(tomMatch359NameNumberfreshSubject_1);{  tom.gom.adt.objects.types.ClassName  tomMatch359NameNumber_freshVar_1=tom_get_slot_VariadicOperatorClass_SortName(tomMatch359NameNumberfreshSubject_1);{  tom.gom.adt.objects.types.ClassName  tomMatch359NameNumber_freshVar_2=tom_get_slot_VariadicOperatorClass_Mapping(tomMatch359NameNumberfreshSubject_1);{  tom.gom.adt.objects.types.GomClass  tomMatch359NameNumber_freshVar_3=tom_get_slot_VariadicOperatorClass_Empty(tomMatch359NameNumberfreshSubject_1);{  tom.gom.adt.objects.types.GomClass  tomMatch359NameNumber_freshVar_4=tom_get_slot_VariadicOperatorClass_Cons(tomMatch359NameNumberfreshSubject_1);if ( true ) {





        this.abstractType = tomMatch359NameNumber_freshVar_0;
        this.sortName = tomMatch359NameNumber_freshVar_1;
        this.empty = tomMatch359NameNumber_freshVar_3;
        this.cons = tomMatch359NameNumber_freshVar_4;
        return;
      }}}}}}}}}

    throw new GomRuntimeException(
        "Wrong argument for VariadicOperatorTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {

    writer.write("\npackage "/* Generated by TOM (version 2.5alpha): Do not edit this file */+getPackage()+";\n"/* Generated by TOM (version 2.5alpha): Do not edit this file */+generateImport()+"\npublic abstract class "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+" extends "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" "/* Generated by TOM (version 2.5alpha): Do not edit this file */+generateInterface()+"{\n"/* Generated by TOM (version 2.5alpha): Do not edit this file */+generateBlock()+"\n"




);
generateBody(writer);
writer.write("\n}\n"

);
  }

  protected String generateInterface() {
    String interfaces = super.generateInterface();
    if (! interfaces.equals("")) {
      return "implements "+interfaces.substring(1);
    } else {
      return interfaces;
    }
  }


  private void generateBody(java.io.Writer writer) throws java.io.IOException {
    String domainClassName = fullClassName(
        cons.getSlots().getHeadconcSlotField().getDomain());
    writer.write("\n  public int length() {\n    int count = 0;\n    if(this instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" tl = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")this).getTail"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n      if (tl instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+") {\n        return 1+(("/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+")tl).length();\n      } else {\n        return 2;\n      }\n    } else {\n      return 0;\n    }\n    }\n\n  public "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[] toArray() {\n    "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[] array;\n    if(this instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+" h = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")this).getHead"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" tl = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")this).getTail"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n      if (tl instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+") {\n        "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[] tailArray =(("/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+")tl).toArray();\n        array = new "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[1+tailArray.length];\n        array[0]=h;\n        for(int i =0;i<tailArray.length;i++){\n          array[i+1]=tailArray[i];\n        }\n      } else {\n        array = new "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[1];\n        array[0]=h;\n      }\n    } else {\n      array = new "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[0];\n    }\n    return array;\n  }\n\n  public static "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" fromArray("/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+"[] array) {\n    "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" res = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(empty.getClassName())+".make();\n    for(int i = array.length; i>0;) {\n      res = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+".make(("/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+")array[--i],res);\n    }\n    return res;\n  }\n\n  public "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" reverse() {\n    if(this instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" cur = this;\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" rev = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(empty.getClassName())+".make();\n      while(cur instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n        rev = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+".make((("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")cur).getHead"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"(),rev);\n        cur = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")cur).getTail"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n      }\n      return rev;\n    } else {\n      return this;\n    }\n  }\n\n  public void toStringBuffer(java.lang.StringBuffer buffer) {\n    buffer.append(\""/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"(\");\n    if(this instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n      "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" cur = this;\n      while(cur instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n        "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+" elem = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")cur).getHead"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n        cur = (("/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+")cur).getTail"/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"();\n        "/* Generated by TOM (version 2.5alpha): Do not edit this file */+toStringChild("buffer","elem")+"\n        if(cur instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+") {\n          buffer.append(\",\");\n        }\n      }\n      if(!(cur instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(empty.getClassName())+")) {\n        buffer.append(\",\");\n        cur.toStringBuffer(buffer);\n      }\n    }\n    buffer.append(\")\");\n  }\n\n  public static "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" fromTerm(aterm.ATerm trm) {\n    if(trm instanceof aterm.ATermAppl) {\n      aterm.ATermAppl appl = (aterm.ATermAppl) trm;\n      if(\""/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"\".equals(appl.getName())) {\n        "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(sortName)+" res = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(empty.getClassName())+".make();\n\n        aterm.ATerm array[] = appl.getArgumentArray();\n        for(int i = array.length-1; i>=0; --i) {\n          "/* Generated by TOM (version 2.5alpha): Do not edit this file */+domainClassName+" elem = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fromATermElement("array[i]","elem")+";\n          res = "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(cons.getClassName())+".make(elem,res);\n        }\n        return res;\n      }\n    }\n    return null;\n  }\n"






























































































);
    if (! hooks.isEmptyconcHook()) {
      mapping.generate(writer); 
    }
  }

  private String toStringChild(String buffer, String element) {
    SlotField head = cons.getSlots().getHeadconcSlotField();
    StringBuffer res = new StringBuffer();
    toStringSlotField(res,head,element,buffer);
    return res.toString();
  }

  private String fromATermElement(String term, String element) {
    SlotField slot = cons.getSlots().getHeadconcSlotField();
    StringBuffer buffer = new StringBuffer();
    fromATermSlotField(buffer,slot,term);
    return buffer.toString();
  }

  public void generateTomMapping(Writer writer, ClassName basicStrategy)
      throws java.io.IOException {
    boolean hasHook = false;
    if (tom_is_sort_HookList(hooks)) {{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.HookList )hooks);if (tom_is_fun_sym_concHook(tomMatch360NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumber_freshVar_0=tomMatch360NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumberbegin_105=tomMatch360NameNumber_freshVar_0;{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumberend_105=tomMatch360NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumber_freshVar_1=tomMatch360NameNumberend_105;if (!(tom_is_empty_concHook_HookList(tomMatch360NameNumber_freshVar_1))) {if (tom_is_fun_sym_MappingHook(tom_get_head_concHook_HookList(tomMatch360NameNumber_freshVar_1))) {{  tom.gom.adt.code.types.Code  tomMatch360NameNumber_freshVar_4=tom_get_slot_MappingHook_Code(tom_get_head_concHook_HookList(tomMatch360NameNumber_freshVar_1));{  tom.gom.adt.objects.types.HookList  tomMatch360NameNumber_freshVar_2=tom_get_tail_concHook_HookList(tomMatch360NameNumber_freshVar_1);if ( true ) {

        CodeGen.generateCode(tomMatch360NameNumber_freshVar_4,writer);
        hasHook = true;
      }}}}}}if (tom_is_empty_concHook_HookList(tomMatch360NameNumberend_105)) {tomMatch360NameNumberend_105=tomMatch360NameNumberbegin_105;} else {tomMatch360NameNumberend_105=tom_get_tail_concHook_HookList(tomMatch360NameNumberend_105);}}} while(!(tom_equal_term_HookList(tomMatch360NameNumberend_105, tomMatch360NameNumberbegin_105)));}}}}}}if (tom_is_sort_GomClass(cons)) {{  tom.gom.adt.objects.types.GomClass  tomMatch361NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )cons);if (tom_is_fun_sym_OperatorClass(tomMatch361NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.SlotFieldList  tomMatch361NameNumber_freshVar_1=tom_get_slot_OperatorClass_Slots(tomMatch361NameNumberfreshSubject_1);if (tom_is_fun_sym_concSlotField(tomMatch361NameNumber_freshVar_1)) {{  tom.gom.adt.objects.types.SlotFieldList  tomMatch361NameNumber_freshVar_3=tomMatch361NameNumber_freshVar_1;if (!(tom_is_empty_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_3))) {{  tom.gom.adt.objects.types.SlotField  tomMatch361NameNumber_freshVar_0=tom_get_head_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_3);{  tom.gom.adt.objects.types.SlotFieldList  tomMatch361NameNumber_freshVar_4=tom_get_tail_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_3);if (!(tom_is_empty_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_4))) {{  tom.gom.adt.objects.types.SlotFieldList  tomMatch361NameNumber_freshVar_5=tom_get_tail_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_4);if (tom_is_empty_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_5)) {if (tom_is_fun_sym_SlotField(tomMatch361NameNumber_freshVar_0)) {{  tom.gom.adt.objects.types.ClassName  tomMatch361NameNumber_freshVar_2=tom_get_slot_SlotField_Domain(tomMatch361NameNumber_freshVar_0);if ( true ) {






    ClassName emptyClass = empty.getClassName();
    ClassName consClass = cons.getClassName();
    writer.write("\n%oplist "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className(sortName)+" "/* Generated by TOM (version 2.5alpha): Do not edit this file */+className()+"("/* Generated by TOM (version 2.5alpha): Do not edit this file */+className(tomMatch361NameNumber_freshVar_2)+"*) {\n  is_fsym(t) { t instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(consClass)+" || t instanceof "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(emptyClass)+" }\n  make_empty() { "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(emptyClass)+".make() }\n  make_insert(e,l) { "/* Generated by TOM (version 2.5alpha): Do not edit this file */+fullClassName(consClass)+".make(e,l) }\n  get_head(l) { l."/* Generated by TOM (version 2.5alpha): Do not edit this file */+getMethod(tomMatch361NameNumber_freshVar_0)+"() }\n  get_tail(l) { l."/* Generated by TOM (version 2.5alpha): Do not edit this file */+getMethod(tom_get_head_concSlotField_SlotFieldList(tomMatch361NameNumber_freshVar_4))+"() }\n  is_empty(l) { l."/* Generated by TOM (version 2.5alpha): Do not edit this file */+isOperatorMethod(emptyClass)+"() }\n}\n"








);
      }}}}}}}}}}}}}}}

    return;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
