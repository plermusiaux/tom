/* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Gom
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

import tom.gom.backend.TemplateClass;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class BasicStrategyTemplate extends TemplateClass {
  ClassName visitor;
  ClassNameList importedVisitors;
  ClassName abstractType;
  ClassNameList importedAbstractTypes;
  GomClassList sortClasses;
  GomClassList operatorClasses;


  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_ConcGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if( l1.isEmptyConcGomClass() ) {       return l2;     } else if( l2.isEmptyConcGomClass() ) {       return l1;     } else if(  l1.getTailConcGomClass() .isEmptyConcGomClass() ) {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,l2) ;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,tom_append_list_ConcGomClass( l1.getTailConcGomClass() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_ConcGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( begin.getHeadConcGomClass() ,( tom.gom.adt.objects.types.GomClassList )tom_get_slice_ConcGomClass( begin.getTailConcGomClass() ,end,tail)) ;     }   }    

  public BasicStrategyTemplate(GomClass basic) {
    super(basic);
    {if ( (basic instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch370NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )basic);if ( (tomMatch370NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.VisitableFwdClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch370NameNumber_freshVar_0= tomMatch370NameNumberfreshSubject_1.getVisitor() ;{  tom.gom.adt.objects.types.ClassNameList  tomMatch370NameNumber_freshVar_1= tomMatch370NameNumberfreshSubject_1.getImportedVisitors() ;{  tom.gom.adt.objects.types.ClassName  tomMatch370NameNumber_freshVar_2= tomMatch370NameNumberfreshSubject_1.getAbstractType() ;{  tom.gom.adt.objects.types.ClassNameList  tomMatch370NameNumber_freshVar_3= tomMatch370NameNumberfreshSubject_1.getImportedAbstractTypes() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_freshVar_4= tomMatch370NameNumberfreshSubject_1.getSortClasses() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_freshVar_5= tomMatch370NameNumberfreshSubject_1.getOperatorClasses() ;if ( true ) {






        this.visitor = tomMatch370NameNumber_freshVar_0;
        this.importedVisitors = tomMatch370NameNumber_freshVar_1;
        this.abstractType = tomMatch370NameNumber_freshVar_2;
        this.importedAbstractTypes = tomMatch370NameNumber_freshVar_3;
        this.sortClasses = tomMatch370NameNumber_freshVar_4;
        this.operatorClasses = tomMatch370NameNumber_freshVar_5;
        return;
      }}}}}}}}}}}

    throw new GomRuntimeException(
        "Wrong argument for BasicStrategyTemplate: " + basic);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write("\npackage "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getPackage()+";\nimport tom.library.sl.*;\n   \n  public class "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" implements tom.library.sl.Strategy,"/* Generated by TOM (version 2.6alpha): Do not edit this file */+ className(visitor)+importedVisitorList(importedVisitors) +" {\n  private tom.library.sl.Environment environment;\n  protected Strategy any;\n  \n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+"(Strategy v) {\n    this.any = v;\n  }\n    \n  public int getChildCount() {\n    return 1;\n  }\n    \n  public Visitable getChildAt(int i) {\n    switch (i) {\n      case 0: return any;\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n    \n  public Visitable setChildAt(int i, Visitable child) {\n    switch (i) {\n      case 0: any = (Strategy) child; return this;\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n\n  public Visitable[] getChildren() {\n    return new Visitable[]{any};\n  }\n\n  public Visitable setChildren(Visitable[] children) {\n    any = (Strategy) children[0];\n    return this;\n  }\n\n  public tom.library.sl.Strategy accept(tom.library.sl.reflective.StrategyFwd v) throws tom.library.sl.VisitFailure {\n    return v.visit_Strategy(this,tom.library.sl.VisitableIntrospector.getInstance());\n  } \n\n  public tom.library.sl.Environment getEnvironment() {\n    if(environment!=null) {\n      return environment;\n    } else {\n      throw new java.lang.RuntimeException(\"environment not initialized\");\n    }\n  }\n\n  public void setEnvironment(tom.library.sl.Environment env) {\n    this.environment = env;\n  }\n \n  public Visitable visitLight(Visitable any) throws VisitFailure {\n    return (Visitable) visitLight(any,VisitableIntrospector.getInstance());\n  }\n\n  public Visitable visit(Environment envt) throws VisitFailure {\n    return (Visitable) visit(envt,VisitableIntrospector.getInstance());\n  }\n\n  public Visitable visit(Visitable any) throws VisitFailure{\n    return (Visitable) visit(any,VisitableIntrospector.getInstance());\n  }\n \n  public Visitable visit(Environment envt, Introspector i) throws VisitFailure {\n    setEnvironment(envt);\n    int status = visit(i);\n    if(status == Environment.SUCCESS) {\n      return (Visitable) environment.getSubject();\n    } else {\n      throw new VisitFailure();\n    }\n  }\n\n\n  public Object visit(Object any, Introspector i) throws VisitFailure {\n    tom.library.sl.AbstractStrategy.init(this,new tom.library.sl.Environment());\n    environment.setRoot(any);\n    int status = visit(i);\n    if(status == tom.library.sl.Environment.SUCCESS) {\n      return environment.getRoot();\n    } else {\n      throw new VisitFailure();\n    }\n  }\n\n  public int visit(Introspector i) {\n    try {\n      environment.setSubject(this.visitLight(environment.getSubject(),i));\n      return tom.library.sl.Environment.SUCCESS;\n    } catch(VisitFailure f) {\n      return tom.library.sl.Environment.FAILURE;\n    }\n  }\n\n  public Object visitLight(Object v, Introspector i) throws VisitFailure {\n    if (v instanceof "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(abstractType)+") {\n      return (("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(abstractType)+") v).accept(this,i);\n    }\n"





































































































);
generateDispatch(writer,importedAbstractTypes);
writer.write("\n    else {\n      return any.visitLight(v,i);\n    }\n  }\n"




);
generateVisitMethods(writer);
writer.write("\n}\n"

);
}

  private void generateVisitMethods(java.io.Writer writer) throws java.io.IOException {
    // generate a visit for each sort
    {if ( (sortClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )sortClasses);if ( ((tomMatch371NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch371NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_0=tomMatch371NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_begin_2=tomMatch371NameNumber_freshVar_0;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_end_3=tomMatch371NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_1=tomMatch371NameNumber_end_3;if (!( tomMatch371NameNumber_freshVar_1.isEmptyConcGomClass() )) {if ( ( tomMatch371NameNumber_freshVar_1.getHeadConcGomClass()  instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch371NameNumber_freshVar_6=  tomMatch371NameNumber_freshVar_1.getHeadConcGomClass() .getClassName() ;{  tom.gom.adt.objects.types.ClassName  tom_sortName=tomMatch371NameNumber_freshVar_6;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_4= tomMatch371NameNumber_freshVar_1.getTailConcGomClass() ;if ( true ) {


        writer.write("\n  public "/* Generated by TOM (version 2.6alpha): Do not edit this file */+ fullClassName(tom_sortName) +" "/* Generated by TOM (version 2.6alpha): Do not edit this file */+visitMethod(tom_sortName)+"("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(tom_sortName)+" arg, Introspector i) throws VisitFailure {\n   if(environment!=null) {\n      //TODO: must be removed\n      assert(arg.equals(environment.getSubject()));\n   return ("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(tom_sortName)+") any.visit(environment,i);\n   } else {\n    return ("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(tom_sortName)+") any.visitLight(arg,i);\n   }\n }\n"









);
      }}}}}}}if ( tomMatch371NameNumber_end_3.isEmptyConcGomClass() ) {tomMatch371NameNumber_end_3=tomMatch371NameNumber_begin_2;} else {tomMatch371NameNumber_end_3= tomMatch371NameNumber_end_3.getTailConcGomClass() ;}}} while(!( tomMatch371NameNumber_end_3.equals(tomMatch371NameNumber_begin_2) ));}}}}}}}

  }

  private void generateDispatch(java.io.Writer writer, ClassNameList types) throws java.io.IOException {
    while(!types.isEmptyConcClassName()) {
      writer.write("    else if (v instanceof "/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(types.getHeadConcClassName())+") {\n      return (("/* Generated by TOM (version 2.6alpha): Do not edit this file */+fullClassName(types.getHeadConcClassName())+") v).accept(this,i);\n    }"

);
      types = types.getTailConcClassName();
    }
  }
  
  private String importedVisitorList(ClassNameList list) {
    StringBuilder out = new StringBuilder();
    while(!list.isEmptyConcClassName()) {
      out.append(", "+fullClassName(list.getHeadConcClassName()));
      list = list.getTailConcClassName();
    }
    return out.toString();
  }

}
