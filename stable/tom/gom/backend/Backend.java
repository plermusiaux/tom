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

package tom.gom.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

import tom.library.strategy.mutraveler.MuTraveler;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

public class Backend {
  TemplateFactory templatefactory;
  private File tomHomePath;
  private List importList = null;
  private boolean strategySupport = true;

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_VisitableFwdClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VisitableFwdClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VisitableFwdClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VisitableFwdClass_Fwd( tom.gom.adt.objects.types.GomClass  t) {  return  t.getFwd()  ;}private static boolean tom_is_fun_sym_AbstractTypeClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_AbstractTypeClass_SortList( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortList()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_AbstractTypeClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getForward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_Operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperators()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_VariadicOperators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVariadicOperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_SortClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getExtendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getEmpty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getCons()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_VisitorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VisitorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VisitorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_VisitorClass_SortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_VisitorClass_OperatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperatorClasses()  ;}private static boolean tom_is_fun_sym_FwdClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.FwdClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_FwdClass_ImportedVisitors( tom.gom.adt.objects.types.GomClass  t) {  return  t.getImportedVisitors()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_FwdClass_ImportedAbstractTypes( tom.gom.adt.objects.types.GomClass  t) {  return  t.getImportedAbstractTypes()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_FwdClass_SortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_FwdClass_OperatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperatorClasses()  ;}private static boolean tom_is_fun_sym_TomMapping( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.TomMapping  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_BasicStrategy( tom.gom.adt.objects.types.GomClass  t) {  return  t.getBasicStrategy()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_SortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_OperatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperatorClasses()  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  t instanceof tom.gom.adt.objects.types.classname.ClassName  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_Pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getPkg()  ;}private static  String  tom_get_slot_ClassName_Name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getName()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_int(int t1, int t2) {  return  (t1==t2)  ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */   


  Backend(TemplateFactory templatefactory,
          File tomHomePath,
          boolean strategySupport,
          List importList) {
    this.templatefactory = templatefactory;
    this.tomHomePath = tomHomePath;
    this.strategySupport = strategySupport;
    this.importList = importList;
  }

  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  public int generate(GomClassList classList) {
    int errno = 0;
    Set mappingSet = new HashSet();
    Map generators =
      new HashMap();
    // prepare stuff for the mappings
     if(classList instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tomMatch1Position1=(( tom.gom.adt.objects.types.GomClassList )classList); if ( ( tom_is_fun_sym_concGomClass(tomMatch1Position1) ||  false  ) ) { {int tomMatch1Position1Index1=0; { tom.gom.adt.objects.types.GomClassList  tomMatch1Position1List1=tomMatch1Position1; { tom.gom.adt.objects.types.GomClassList  tomMatch1Position1Begin1=tomMatch1Position1List1; { tom.gom.adt.objects.types.GomClassList  tomMatch1Position1End1=tomMatch1Position1List1; { while (!(tom_is_empty_concGomClass_GomClassList(tomMatch1Position1End1))) {tomMatch1Position1List1=tomMatch1Position1End1; { { tom.gom.adt.objects.types.GomClassList  tomMatch1Position1Save2=tomMatch1Position1List1; { { tom.gom.adt.objects.types.GomClass  tomMatch1Position1Position2=tom_get_head_concGomClass_GomClassList(tomMatch1Position1List1);tomMatch1Position1Index1=tomMatch1Position1Index1 + 1;tomMatch1Position1List1=tom_get_tail_concGomClass_GomClassList(tomMatch1Position1List1); if ( ( tom_is_fun_sym_TomMapping(tomMatch1Position1Position2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tomMatch1Position1Position2NameNumberClassName=tom_get_slot_TomMapping_ClassName(tomMatch1Position1Position2); if ( ( tom_is_fun_sym_ClassName(tomMatch1Position1Position2NameNumberClassName) ||  false  ) ) { { String  tom_pkg=tom_get_slot_ClassName_Pkg(tomMatch1Position1Position2NameNumberClassName); { String  tom_name=tom_get_slot_ClassName_Name(tomMatch1Position1Position2NameNumberClassName); { tom.gom.adt.objects.types.ClassName  tom_className=tomMatch1Position1Position2NameNumberClassName; { tom.gom.adt.objects.types.GomClass  tom_gomclass=tomMatch1Position1Position2; if ( true ) {



        ClassName smappingclass = tom_make_ClassName(tom_pkg,"_"+tom_name);
        GomClass nGomClass =
          tom_gomclass.setClassName(smappingclass);
        TemplateClass stratMapping =
          new tom.gom.backend.strategy.StratMappingTemplate(nGomClass);
        generators.put(smappingclass,stratMapping);

        TemplateClass mapping = null;
        if(strategySupport) {
          mapping =
            templatefactory.makeTomMappingTemplate(tom_gomclass,stratMapping);
        } else {
          mapping =
            templatefactory.makeTomMappingTemplate(tom_gomclass,null);
        }
        mappingSet.add(mapping);
        generators.put(tom_className,mapping);
       } } } } } } } } }tomMatch1Position1List1=tomMatch1Position1Save2; } }tomMatch1Position1End1=tom_get_tail_concGomClass_GomClassList(tomMatch1Position1End1); } }tomMatch1Position1List1=tomMatch1Position1Begin1; } } } } } } } }

    // generate a class for each element of the list
    while (!classList.isEmptyconcGomClass()) {
      GomClass gomclass = classList.getHeadconcGomClass();
      classList = classList.getTailconcGomClass();
      errno += generateClass(gomclass,generators);
    }
    /* The mappings may need to access generators */
    Iterator it = mappingSet.iterator();
    while (it.hasNext()) {
      ((MappingTemplateClass)it.next()).addTemplates(generators);
    }
    it = generators.keySet().iterator();
    while (it.hasNext()) {
      ((TemplateClass)generators.get(it.next())).generateFile();
    }

    return 1;
  }

  /*
   * Create template classes for the different classes to generate
   */
  public int generateClass(
      GomClass gomclass,
      Map generators) {
     if(gomclass instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tomMatch2Position1=(( tom.gom.adt.objects.types.GomClass )gomclass); if ( ( tom_is_fun_sym_TomMapping(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_TomMapping_ClassName(tomMatch2Position1); if ( true ) {

        /* It was processed by the caller: check it is already in generators */
        if (!generators.containsKey(tom_className)) {
          throw new GomRuntimeException(
              "Mapping should be processed before generateClass is called");
        }
        return 1;
       } } } if ( ( tom_is_fun_sym_FwdClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_FwdClass_ClassName(tomMatch2Position1); if ( true ) {

        TemplateClass fwd = templatefactory.makeForwardTemplate(gomclass);
        generators.put(tom_className,fwd);
        return 1;
       } } } if ( ( tom_is_fun_sym_VisitableFwdClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_VisitableFwdClass_ClassName(tomMatch2Position1); if ( true ) {

        TemplateClass visitablefwd =
          templatefactory.makeVisitableForwardTemplate(gomclass);
        generators.put(tom_className,visitablefwd);
        return 1;
       } } } if ( ( tom_is_fun_sym_VisitorClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_VisitorClass_ClassName(tomMatch2Position1); if ( true ) {

        TemplateClass visitor = templatefactory.makeVisitorTemplate(gomclass);
        generators.put(tom_className,visitor);
        return 1;
       } } } if ( ( tom_is_fun_sym_AbstractTypeClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_AbstractTypeClass_ClassName(tomMatch2Position1); { tom.gom.adt.objects.types.ClassName  tom_mapping=tom_get_slot_AbstractTypeClass_Mapping(tomMatch2Position1); if ( true ) {

        TemplateClass abstracttype =
          templatefactory.makeAbstractTypeTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tom_mapping));
        generators.put(tom_className,abstracttype);
        return 1;
       } } } } if ( ( tom_is_fun_sym_SortClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_SortClass_ClassName(tomMatch2Position1); { tom.gom.adt.objects.types.ClassName  tom_mapping=tom_get_slot_SortClass_Mapping(tomMatch2Position1); if ( true ) {

        TemplateClass sort =
          templatefactory.makeSortTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tom_mapping));
        generators.put(tom_className,sort);
        return 1;
       } } } } if ( ( tom_is_fun_sym_OperatorClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_OperatorClass_ClassName(tomMatch2Position1); { tom.gom.adt.objects.types.ClassName  tom_mapping=tom_get_slot_OperatorClass_Mapping(tomMatch2Position1); if ( true ) {



        TemplateClass operator = templatefactory.makeOperatorTemplate(
            tomHomePath,
            importList,
            gomclass,
            (TemplateClass)generators.get(tom_mapping));
        generators.put(tom_className,operator);

        TemplateClass sOpStrat =
          new tom.gom.backend.strategy.SOpTemplate(gomclass);
        sOpStrat.generateFile();

        TemplateClass makeOpStrat = new tom.gom.backend.strategy.MakeOpTemplate(gomclass);
        makeOpStrat.generateFile();
         TemplateClass whenOpStrat =
           new tom.gom.backend.strategy.WhenOpTemplate(gomclass);
        whenOpStrat.generateFile();

       return 1;
       } } } } if ( ( tom_is_fun_sym_VariadicOperatorClass(tomMatch2Position1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_get_slot_VariadicOperatorClass_ClassName(tomMatch2Position1); { tom.gom.adt.objects.types.ClassName  tom_mapping=tom_get_slot_VariadicOperatorClass_Mapping(tomMatch2Position1); { tom.gom.adt.objects.types.GomClass  tom_empty=tom_get_slot_VariadicOperatorClass_Empty(tomMatch2Position1); { tom.gom.adt.objects.types.GomClass  tom_cons=tom_get_slot_VariadicOperatorClass_Cons(tomMatch2Position1); if ( true ) {




        TemplateClass operator =
          templatefactory.makeVariadicOperatorTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tom_mapping));
        generators.put(tom_className,operator);
        /* Generate files for cons and empty */
        int ret = 1;
        ret+=generateClass(tom_empty,generators);
        ret+=generateClass(tom_cons,generators);

        return ret;
       } } } } } } } }

    throw new GomRuntimeException("Trying to generate code for a strange class: "+gomclass);
  }
}
