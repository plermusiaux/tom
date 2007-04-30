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

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.TemplateClass;
import tom.gom.backend.MappingTemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class MappingTemplate extends MappingTemplateClass {
  ClassName basicStrategy;
  GomClassList sortClasses;
  GomClassList operatorClasses;
  TemplateClass strategyMapping;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */   private static boolean tom_equal_term_SlotFieldList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_SlotFieldList(Object t) { return  t instanceof tom.gom.adt.objects.types.SlotFieldList ;}private static boolean tom_equal_term_GomClass(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_GomClass(Object t) { return  t instanceof tom.gom.adt.objects.types.GomClass ;}private static boolean tom_equal_term_ClassName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ClassName(Object t) { return  t instanceof tom.gom.adt.objects.types.ClassName ;}private static boolean tom_equal_term_GomClassList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_GomClassList(Object t) { return  t instanceof tom.gom.adt.objects.types.GomClassList ;}private static boolean tom_equal_term_ClassNameList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ClassNameList(Object t) { return  t instanceof tom.gom.adt.objects.types.ClassNameList ;}private static boolean tom_equal_term_HookList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_HookList(Object t) { return  t instanceof tom.gom.adt.objects.types.HookList ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Visitor( tom.gom.adt.objects.types.GomClass  t) { return  t.getVisitor() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Forward( tom.gom.adt.objects.types.GomClass  t) { return  t.getForward() ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_Operators( tom.gom.adt.objects.types.GomClass  t) { return  t.getOperators() ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_VariadicOperators( tom.gom.adt.objects.types.GomClass  t) { return  t.getVariadicOperators() ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_Slots( tom.gom.adt.objects.types.GomClass  t) { return  t.getSlots() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_SortClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) { return  t.getExtendsType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) { return  t.getVisitor() ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) { return  t.getSlots() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) { return  t.getAbstractType() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) { return  t.getMapping() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortName() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) { return  t.getEmpty() ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) { return  t.getCons() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) { return  t.getHooks() ;}private static boolean tom_is_fun_sym_TomMapping( tom.gom.adt.objects.types.GomClass  t) { return  t instanceof tom.gom.adt.objects.types.gomclass.TomMapping ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_ClassName( tom.gom.adt.objects.types.GomClass  t) { return  t.getClassName() ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_BasicStrategy( tom.gom.adt.objects.types.GomClass  t) { return  t.getBasicStrategy() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_SortClasses( tom.gom.adt.objects.types.GomClass  t) { return  t.getSortClasses() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_OperatorClasses( tom.gom.adt.objects.types.GomClass  t) { return  t.getOperatorClasses() ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) { return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.getHeadconcGomClass() ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.getTailconcGomClass() ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) { return  l.isEmptyconcGomClass() ;}   private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if(tom_is_empty_concGomClass_GomClassList(l1)) {       return l2;     } else if(tom_is_empty_concGomClass_GomClassList(l2)) {       return l1;     } else if(tom_is_empty_concGomClass_GomClassList(tom_get_tail_concGomClass_GomClassList(l1))) {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(l1),l2);     } else {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(tom_get_tail_concGomClass_GomClassList(l1),l2));     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if(tom_equal_term_GomClassList(begin,end)) {       return tail;     } else {       return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(tom_get_tail_concGomClass_GomClassList(begin),end,tail));     }   }    

  public MappingTemplate(GomClass gomClass, TemplateClass strategyMapping) {
    super(gomClass);
    if (tom_is_sort_GomClass(gomClass)) {{  tom.gom.adt.objects.types.GomClass  tomMatch1Position1=(( tom.gom.adt.objects.types.GomClass )gomClass);if ( ( tom_is_fun_sym_TomMapping(tomMatch1Position1) ||  false  ) ) {if ( true ) {



        this.basicStrategy = tom_get_slot_TomMapping_BasicStrategy(tomMatch1Position1);
        this.sortClasses = tom_get_slot_TomMapping_SortClasses(tomMatch1Position1);
        this.operatorClasses = tom_get_slot_TomMapping_OperatorClasses(tomMatch1Position1);
        this.strategyMapping = strategyMapping;
        return;
      }}}}

    throw new GomRuntimeException(
        "Wrong argument for MappingTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    if(GomEnvironment.getInstance().isBuiltinSort("boolean")) {
      writer.write("\n%include { boolean.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("String")) {
      writer.write("\n%include { string.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("int")) {
      writer.write("\n%include { int.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("char")) {
      writer.write("\n%include { char.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("double")) {
      writer.write("\n%include { double.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("long")) {
      writer.write("\n%include { long.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("float")) {
      writer.write("\n%include { float.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("ATerm")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("ATermList")) {
      writer.write("\n%include { atermlist.tom }\n"

);
    }

    // generate a %typeterm for each class
    if (tom_is_sort_GomClassList(sortClasses)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch2Position1=(( tom.gom.adt.objects.types.GomClassList )sortClasses);if ( ( tom_is_fun_sym_concGomClass(tomMatch2Position1) ||  false  ) ) {{ int tomMatch2Position1Index1=0;{  tom.gom.adt.objects.types.GomClassList  tomMatch2Position1List1=tomMatch2Position1;{  tom.gom.adt.objects.types.GomClassList  tomMatch2Position1Begin1=tomMatch2Position1List1;{  tom.gom.adt.objects.types.GomClassList  tomMatch2Position1End1=tomMatch2Position1List1;{while (!(tom_is_empty_concGomClass_GomClassList(tomMatch2Position1End1))) {tomMatch2Position1List1=tomMatch2Position1End1;{{  tom.gom.adt.objects.types.GomClassList  tomMatch2Position1Save2=tomMatch2Position1List1;{{  tom.gom.adt.objects.types.GomClass  tomMatch2Position1Position2=tom_get_head_concGomClass_GomClassList(tomMatch2Position1List1);tomMatch2Position1Index1=tomMatch2Position1Index1 + 1;tomMatch2Position1List1=tom_get_tail_concGomClass_GomClassList(tomMatch2Position1List1);if ( ( tom_is_fun_sym_SortClass(tomMatch2Position1Position2) ||  false  ) ) {if ( true ) {



        ((TemplateClass) templates.get(tom_get_slot_SortClass_ClassName(tomMatch2Position1Position2)))
          .generateTomMapping(writer,basicStrategy);
      }}}tomMatch2Position1List1=tomMatch2Position1Save2;}}tomMatch2Position1End1=tom_get_tail_concGomClass_GomClassList(tomMatch2Position1End1);}}tomMatch2Position1List1=tomMatch2Position1Begin1;}}}}}}}}


    // generate a %op for each operator
    if (tom_is_sort_GomClassList(operatorClasses)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch3Position1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);if ( ( tom_is_fun_sym_concGomClass(tomMatch3Position1) ||  false  ) ) {{ int tomMatch3Position1Index1=0;{  tom.gom.adt.objects.types.GomClassList  tomMatch3Position1List1=tomMatch3Position1;{  tom.gom.adt.objects.types.GomClassList  tomMatch3Position1Begin1=tomMatch3Position1List1;{  tom.gom.adt.objects.types.GomClassList  tomMatch3Position1End1=tomMatch3Position1List1;{while (!(tom_is_empty_concGomClass_GomClassList(tomMatch3Position1End1))) {tomMatch3Position1List1=tomMatch3Position1End1;{{  tom.gom.adt.objects.types.GomClassList  tomMatch3Position1Save2=tomMatch3Position1List1;{{  tom.gom.adt.objects.types.GomClass  tomMatch3Position1Position2=tom_get_head_concGomClass_GomClassList(tomMatch3Position1List1);tomMatch3Position1Index1=tomMatch3Position1Index1 + 1;tomMatch3Position1List1=tom_get_tail_concGomClass_GomClassList(tomMatch3Position1List1);if ( ( tom_is_fun_sym_OperatorClass(tomMatch3Position1Position2) ||  false  ) ) {if ( true ) {



        ((TemplateClass) templates.get(tom_get_slot_OperatorClass_ClassName(tomMatch3Position1Position2)))
          .generateTomMapping(writer,basicStrategy);
      }}}tomMatch3Position1List1=tomMatch3Position1Save2;}}tomMatch3Position1End1=tom_get_tail_concGomClass_GomClassList(tomMatch3Position1End1);}}tomMatch3Position1List1=tomMatch3Position1Begin1;}}}}}}}}


    // generate a %oplist for each variadic operator
    if (tom_is_sort_GomClassList(operatorClasses)) {{  tom.gom.adt.objects.types.GomClassList  tomMatch4Position1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);if ( ( tom_is_fun_sym_concGomClass(tomMatch4Position1) ||  false  ) ) {{ int tomMatch4Position1Index1=0;{  tom.gom.adt.objects.types.GomClassList  tomMatch4Position1List1=tomMatch4Position1;{  tom.gom.adt.objects.types.GomClassList  tomMatch4Position1Begin1=tomMatch4Position1List1;{  tom.gom.adt.objects.types.GomClassList  tomMatch4Position1End1=tomMatch4Position1List1;{while (!(tom_is_empty_concGomClass_GomClassList(tomMatch4Position1End1))) {tomMatch4Position1List1=tomMatch4Position1End1;{{  tom.gom.adt.objects.types.GomClassList  tomMatch4Position1Save2=tomMatch4Position1List1;{{  tom.gom.adt.objects.types.GomClass  tomMatch4Position1Position2=tom_get_head_concGomClass_GomClassList(tomMatch4Position1List1);tomMatch4Position1Index1=tomMatch4Position1Index1 + 1;tomMatch4Position1List1=tom_get_tail_concGomClass_GomClassList(tomMatch4Position1List1);if ( ( tom_is_fun_sym_VariadicOperatorClass(tomMatch4Position1Position2) ||  false  ) ) {if ( true ) {



        ((TemplateClass) templates.get(tom_get_slot_VariadicOperatorClass_ClassName(tomMatch4Position1Position2)))
          .generateTomMapping(writer,basicStrategy);
      }}}tomMatch4Position1List1=tomMatch4Position1Save2;}}tomMatch4Position1End1=tom_get_tail_concGomClass_GomClassList(tomMatch4Position1End1);}}tomMatch4Position1List1=tomMatch4Position1Begin1;}}}}}}}}

    /* Include the strategy mapping if needed */
    if (strategyMapping != null) {
      strategyMapping.generateTomMapping(writer,basicStrategy);
    }
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = GomEnvironment.getInstance().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    // log the generated mapping file name
    try {
      GomEnvironment.getInstance()
        .setLastGeneratedMapping(output.getCanonicalPath());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }
}
