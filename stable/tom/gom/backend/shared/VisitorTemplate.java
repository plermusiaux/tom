/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006 INRIA
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

import tom.gom.backend.TemplateClass;
import tom.gom.adt.objects.types.*;

public class VisitorTemplate extends TemplateClass {
  GomClassList sortClasses;
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */   private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getForward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_Operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperators()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_VariadicOperators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVariadicOperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_SortClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   

  public VisitorTemplate(ClassName className, GomClassList sortClasses, GomClassList operatorClasses) {
    super(className);
    this.sortClasses = sortClasses;
    this.operatorClasses = operatorClasses;
  }

  /* We may want to return the stringbuffer itself in the future, or directly write to a Stream */
  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write("package "+getPackage()+";\n");
    writer.write("\n");
    writer.write("public interface "+className()+" {\n");
    writer.write("\n");

    // generate a visit for each sort
     if(sortClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )sortClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end2=tom_match1_1_list1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_SortClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_sortName=tom_get_slot_SortClass_ClassName(tom_match1_1_2); if ( true ) {

        writer.write("\tpublic "+fullClassName(tom_sortName)+" "+visitMethod(tom_sortName)+"("+fullClassName(tom_sortName)+" arg) throws jjtraveler.VisitFailure;\n");
       } } } }tom_match1_1_list1=tom_match1_1_end2; } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }


    writer.write("\n");
    writer.write("}\n");
  }

}
