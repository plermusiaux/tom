/* Generated by TOM (version 2.5): Do not edit this file *//*
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

package tom.gom.backend.strategy;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.MappingTemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class StratMappingTemplate extends MappingTemplateClass {
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.5): Do not edit this file *//* Generated by TOM (version 2.5): Do not edit this file *//* Generated by TOM (version 2.5): Do not edit this file */   /* Generated by TOM (version 2.5): Do not edit this file */ private static boolean tom_equal_term_SlotFieldList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_SlotFieldList(Object t) { return  t instanceof tom.gom.adt.objects.types.SlotFieldList ;}private static boolean tom_equal_term_GomClass(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_GomClass(Object t) { return  t instanceof tom.gom.adt.objects.types.GomClass ;}private static boolean tom_equal_term_ClassName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ClassName(Object t) { return  t instanceof tom.gom.adt.objects.types.ClassName ;}private static boolean tom_equal_term_GomClassList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_GomClassList(Object t) { return  t instanceof tom.gom.adt.objects.types.GomClassList ;}private static boolean tom_equal_term_HookList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_HookList(Object t) { return  t instanceof tom.gom.adt.objects.types.HookList ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) { return  t.getExtendsType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) { return  t.getVisitor() ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) { return  t.getSlots() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) { return  t.getEmpty() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) { return  t.getCons() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_TomMapping( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.TomMapping ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_BasicStrategy( tom.gom.adt.objects.types.GomClass  t) { return  t.getBasicStrategy() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_SortClasses( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortClasses() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_OperatorClasses( tom.gom.adt.objects.types.GomClass  t) { return  t.getOperatorClasses() ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) { return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.getHeadconcGomClass() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.getTailconcGomClass() ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.isEmptyconcGomClass() ;}   private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if(tom_is_empty_concGomClass_GomClassList(l1)) {       return l2;     } else if(tom_is_empty_concGomClass_GomClassList(l2)) {       return l1;     } else if(tom_is_empty_concGomClass_GomClassList(tom_get_tail_concGomClass_GomClassList(l1))) {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(l1),l2);     } else {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(tom_get_tail_concGomClass_GomClassList(l1),l2));     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if(tom_equal_term_GomClassList(begin,end)) {       return tail;     } else {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(tom_get_tail_concGomClass_GomClassList(begin),end,tail));     }   }    

  public StratMappingTemplate(GomClass gomClass) {
    super(gomClass);
    if (tom_is_sort_GomClass(gomClass)) {{  tom.gom.adt.objects.types.GomClass  tomMatch396NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )gomClass);if (tom_is_fun_sym_TomMapping(tomMatch396NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch396NameNumber_freshVar_0=tom_get_slot_TomMapping_OperatorClasses(tomMatch396NameNumberfreshSubject_1);if ( true ) {

        this.operatorClasses = tomMatch396NameNumber_freshVar_0;
        return;
      }}}}}

    throw new GomRuntimeException(
        "Wrong argument for MappingTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    generateTomMapping(writer, null);
  }

  public void generateTomMapping(Writer writer, ClassName basicStrategy)
      throws java.io.IOException {

    writer.write("\n   /*\n   %include { mustrategy.tom }\n   */\n"



);
    if (tom_is_sort_GomClassList(operatorClasses)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);if (tom_is_fun_sym_concGomClass(tomMatch397NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_3=tomMatch397NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_begin_5=tomMatch397NameNumber_freshVar_3;{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_end_6=tomMatch397NameNumber_freshVar_3;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_4=tomMatch397NameNumber_end_6;if (!(tom_is_empty_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_4))) {{  tom.gom.adt.objects.types.GomClass  tomMatch397NameNumber_freshVar_0=tom_get_head_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_4);{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_7=tom_get_tail_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_4);if (tom_is_fun_sym_OperatorClass(tomMatch397NameNumber_freshVar_0)) {{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_1=tom_get_slot_OperatorClass_ClassName(tomMatch397NameNumber_freshVar_0);{  tom.gom.adt.objects.types.SlotFieldList  tomMatch397NameNumber_freshVar_2=tom_get_slot_OperatorClass_Slots(tomMatch397NameNumber_freshVar_0);{  tom.gom.adt.objects.types.GomClass  tom_op=tomMatch397NameNumber_freshVar_0;if ( true ) {




        writer.write(
            (new tom.gom.backend.strategy.SOpTemplate(tom_op)).generateMapping());
        writer.write(
            (new tom.gom.backend.strategy.IsOpTemplate(tom_op)).generateMapping());
        writer.write(
            (new tom.gom.backend.strategy.MakeOpTemplate(tom_op)).generateMapping());
      }}}}}}}}}if (tom_is_empty_concGomClass_GomClassList(tomMatch397NameNumber_end_6)) {tomMatch397NameNumber_end_6=tomMatch397NameNumber_begin_5;} else {tomMatch397NameNumber_end_6=tom_get_tail_concGomClass_GomClassList(tomMatch397NameNumber_end_6);}}} while(!(tom_equal_term_GomClassList(tomMatch397NameNumber_end_6, tomMatch397NameNumber_begin_5)));}}}}if (tom_is_fun_sym_concGomClass(tomMatch397NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_9=tomMatch397NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_begin_11=tomMatch397NameNumber_freshVar_9;{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_end_12=tomMatch397NameNumber_freshVar_9;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_10=tomMatch397NameNumber_end_12;if (!(tom_is_empty_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10))) {if (tom_is_fun_sym_VariadicOperatorClass(tom_get_head_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10))) {{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_15=tom_get_slot_VariadicOperatorClass_ClassName(tom_get_head_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10));{  tom.gom.adt.objects.types.GomClass  tomMatch397NameNumber_freshVar_16=tom_get_slot_VariadicOperatorClass_Empty(tom_get_head_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10));{  tom.gom.adt.objects.types.GomClass  tomMatch397NameNumber_freshVar_17=tom_get_slot_VariadicOperatorClass_Cons(tom_get_head_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10));{  tom.gom.adt.objects.types.ClassName  tom_vopName=tomMatch397NameNumber_freshVar_15;if (tom_is_fun_sym_OperatorClass(tomMatch397NameNumber_freshVar_16)) {{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_18=tom_get_slot_OperatorClass_ClassName(tomMatch397NameNumber_freshVar_16);if (tom_is_fun_sym_OperatorClass(tomMatch397NameNumber_freshVar_17)) {{  tom.gom.adt.objects.types.ClassName  tomMatch397NameNumber_freshVar_19=tom_get_slot_OperatorClass_ClassName(tomMatch397NameNumber_freshVar_17);{  tom.gom.adt.objects.types.GomClassList  tomMatch397NameNumber_freshVar_13=tom_get_tail_concGomClass_GomClassList(tomMatch397NameNumber_freshVar_10);if ( true ) {





        writer.write("\n            %op Strategy _"/* Generated by TOM (version 2.5): Do not edit this file */+className(tom_vopName)+"(sub:Strategy) {\n            is_fsym(t) { false }\n            make(sub)  { `mu(MuVar(\"x_"/* Generated by TOM (version 2.5): Do not edit this file */+className(tom_vopName)+"\"),Choice(_"/* Generated by TOM (version 2.5): Do not edit this file */+className(tomMatch397NameNumber_freshVar_19)+"(sub,MuVar(\"x_"/* Generated by TOM (version 2.5): Do not edit this file */+className(tom_vopName)+"\")),_"/* Generated by TOM (version 2.5): Do not edit this file */+className(tomMatch397NameNumber_freshVar_18)+"())) }\n            }\n            "




);
      }}}}}}}}}}}}}if (tom_is_empty_concGomClass_GomClassList(tomMatch397NameNumber_end_12)) {tomMatch397NameNumber_end_12=tomMatch397NameNumber_begin_11;} else {tomMatch397NameNumber_end_12=tom_get_tail_concGomClass_GomClassList(tomMatch397NameNumber_end_12);}}} while(!(tom_equal_term_GomClassList(tomMatch397NameNumber_end_12, tomMatch397NameNumber_begin_11)));}}}}}}

      }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

}
