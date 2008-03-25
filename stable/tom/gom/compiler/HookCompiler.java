/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2006-2008, INRIA
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

package tom.gom.compiler;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

import tom.library.sl.*;

public class HookCompiler {

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.objects.types.SlotFieldList  tom_append_list_ConcSlotField( tom.gom.adt.objects.types.SlotFieldList l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {     if( l1.isEmptyConcSlotField() ) {       return l2;     } else if( l2.isEmptyConcSlotField() ) {       return l1;     } else if(  l1.getTailConcSlotField() .isEmptyConcSlotField() ) {       return  tom.gom.adt.objects.types.slotfieldlist.ConsConcSlotField.make( l1.getHeadConcSlotField() ,l2) ;     } else {       return  tom.gom.adt.objects.types.slotfieldlist.ConsConcSlotField.make( l1.getHeadConcSlotField() ,tom_append_list_ConcSlotField( l1.getTailConcSlotField() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_ConcSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end, tom.gom.adt.objects.types.SlotFieldList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSlotField()  ||  (end== tom.gom.adt.objects.types.slotfieldlist.EmptyConcSlotField.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.objects.types.slotfieldlist.ConsConcSlotField.make( begin.getHeadConcSlotField() ,( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_ConcSlotField( begin.getTailConcSlotField() ,end,tail)) ;   }      private static   tom.gom.adt.objects.types.HookList  tom_append_list_ConcHook( tom.gom.adt.objects.types.HookList l1,  tom.gom.adt.objects.types.HookList  l2) {     if( l1.isEmptyConcHook() ) {       return l2;     } else if( l2.isEmptyConcHook() ) {       return l1;     } else if(  l1.getTailConcHook() .isEmptyConcHook() ) {       return  tom.gom.adt.objects.types.hooklist.ConsConcHook.make( l1.getHeadConcHook() ,l2) ;     } else {       return  tom.gom.adt.objects.types.hooklist.ConsConcHook.make( l1.getHeadConcHook() ,tom_append_list_ConcHook( l1.getTailConcHook() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.HookList  tom_get_slice_ConcHook( tom.gom.adt.objects.types.HookList  begin,  tom.gom.adt.objects.types.HookList  end, tom.gom.adt.objects.types.HookList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcHook()  ||  (end== tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.objects.types.hooklist.ConsConcHook.make( begin.getHeadConcHook() ,( tom.gom.adt.objects.types.HookList )tom_get_slice_ConcHook( begin.getTailConcHook() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.HookDeclList  tom_append_list_ConcHookDecl( tom.gom.adt.gom.types.HookDeclList l1,  tom.gom.adt.gom.types.HookDeclList  l2) {     if( l1.isEmptyConcHookDecl() ) {       return l2;     } else if( l2.isEmptyConcHookDecl() ) {       return l1;     } else if(  l1.getTailConcHookDecl() .isEmptyConcHookDecl() ) {       return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( l1.getHeadConcHookDecl() ,l2) ;     } else {       return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( l1.getHeadConcHookDecl() ,tom_append_list_ConcHookDecl( l1.getTailConcHookDecl() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.HookDeclList  tom_get_slice_ConcHookDecl( tom.gom.adt.gom.types.HookDeclList  begin,  tom.gom.adt.gom.types.HookDeclList  end, tom.gom.adt.gom.types.HookDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcHookDecl()  ||  (end== tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl.make( begin.getHeadConcHookDecl() ,( tom.gom.adt.gom.types.HookDeclList )tom_get_slice_ConcHookDecl( begin.getTailConcHookDecl() ,end,tail)) ;   }      private static   tom.gom.adt.gom.types.SlotList  tom_append_list_ConcSlot( tom.gom.adt.gom.types.SlotList l1,  tom.gom.adt.gom.types.SlotList  l2) {     if( l1.isEmptyConcSlot() ) {       return l2;     } else if( l2.isEmptyConcSlot() ) {       return l1;     } else if(  l1.getTailConcSlot() .isEmptyConcSlot() ) {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,l2) ;     } else {       return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( l1.getHeadConcSlot() ,tom_append_list_ConcSlot( l1.getTailConcSlot() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SlotList  tom_get_slice_ConcSlot( tom.gom.adt.gom.types.SlotList  begin,  tom.gom.adt.gom.types.SlotList  end, tom.gom.adt.gom.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcSlot()  ||  (end== tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.gom.types.slotlist.ConsConcSlot.make( begin.getHeadConcSlot() ,( tom.gom.adt.gom.types.SlotList )tom_get_slice_ConcSlot( begin.getTailConcSlot() ,end,tail)) ;   }    /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   


  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  private static Map sortClassNameForSortDecl;
  HookCompiler(Map sortClassNameForSortDecl) {
    this.sortClassNameForSortDecl = sortClassNameForSortDecl;
  }
  /**
    * Process the hooks, and attach them to the correct classes.
    */
  public GomClassList compile(
      HookDeclList declList,
      GomClassList classes,
      Map declToClassName) {
    /* for each hook, find the class, and attach the hook */
    {if ( (declList instanceof tom.gom.adt.gom.types.HookDeclList) ) {{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDeclList )declList);{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumber_freshVar_0=tomMatch439NameNumberfreshSubject_1;if ( ((tomMatch439NameNumber_freshVar_0 instanceof tom.gom.adt.gom.types.hookdecllist.ConsConcHookDecl) || (tomMatch439NameNumber_freshVar_0 instanceof tom.gom.adt.gom.types.hookdecllist.EmptyConcHookDecl)) ) {{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumber_begin_2=tomMatch439NameNumber_freshVar_0;{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumber_end_3=tomMatch439NameNumber_freshVar_0;do {{{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumber_freshVar_1=tomMatch439NameNumber_end_3;if (!( tomMatch439NameNumber_freshVar_1.isEmptyConcHookDecl() )) {{  tom.gom.adt.gom.types.HookDecl  tom_hook= tomMatch439NameNumber_freshVar_1.getHeadConcHookDecl() ;{  tom.gom.adt.gom.types.HookDeclList  tomMatch439NameNumber_freshVar_4= tomMatch439NameNumber_freshVar_1.getTailConcHookDecl() ;if ( true ) {

        Decl decl = tom_hook.getPointcut();
        {if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumber_freshVar_1=tomMatch440NameNumberfreshSubject_1;if ( (tomMatch440NameNumber_freshVar_1 instanceof tom.gom.adt.gom.types.decl.CutModule) ) {{  tom.gom.adt.gom.types.ModuleDecl  tomMatch440NameNumber_freshVar_0= tomMatch440NameNumber_freshVar_1.getMDecl() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch440NameNumber_freshVar_0);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachModuleHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }
          }}}}}}if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumber_freshVar_3=tomMatch440NameNumberfreshSubject_1;if ( (tomMatch440NameNumber_freshVar_3 instanceof tom.gom.adt.gom.types.decl.CutSort) ) {{  tom.gom.adt.gom.types.SortDecl  tomMatch440NameNumber_freshVar_2= tomMatch440NameNumber_freshVar_3.getSort() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch440NameNumber_freshVar_2);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachSortHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }
          }}}}}}if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);{  tom.gom.adt.gom.types.Decl  tomMatch440NameNumber_freshVar_5=tomMatch440NameNumberfreshSubject_1;if ( (tomMatch440NameNumber_freshVar_5 instanceof tom.gom.adt.gom.types.decl.CutOperator) ) {{  tom.gom.adt.gom.types.OperatorDecl  tomMatch440NameNumber_freshVar_4= tomMatch440NameNumber_freshVar_5.getODecl() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch440NameNumber_freshVar_4);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachOperatorHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }     
          }}}}}}}

      }}}}}if ( tomMatch439NameNumber_end_3.isEmptyConcHookDecl() ) {tomMatch439NameNumber_end_3=tomMatch439NameNumber_begin_2;} else {tomMatch439NameNumber_end_3= tomMatch439NameNumber_end_3.getTailConcHookDecl() ;}}} while(!( (tomMatch439NameNumber_end_3==tomMatch439NameNumber_begin_2) ));}}}}}}}

    return classes;
  }

  private static class AttachModuleHook extends tom.library.sl.BasicStrategy {private  tom.gom.adt.objects.types.ClassName  cName;private  tom.gom.adt.gom.types.HookDecl  hook;public AttachModuleHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) {super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() {return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() {return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch441NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);{  tom.gom.adt.objects.types.GomClass  tomMatch441NameNumber_freshVar_2=tomMatch441NameNumberfreshSubject_1;if ( (tomMatch441NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch441NameNumber_freshVar_0= tomMatch441NameNumber_freshVar_2.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch441NameNumber_freshVar_1= tomMatch441NameNumber_freshVar_2.getHooks() ;if ( true ) {



          if (tomMatch441NameNumber_freshVar_0== cName) {
            return
              tomMatch441NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(tomMatch441NameNumber_freshVar_1, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
          }
        }}}}}}}}return _visit_GomClass(tom__arg,introspector); }public  tom.gom.adt.objects.types.GomClass  _visit_GomClass( tom.gom.adt.objects.types.GomClass  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.gom.adt.objects.types.GomClass )any.visit(environment,introspector));} else {return (( tom.gom.adt.objects.types.GomClass )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.gom.adt.objects.types.GomClass) ) {return visit_GomClass((( tom.gom.adt.objects.types.GomClass )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_AttachModuleHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachModuleHook(t0,t1);}private static class AttachSortHook extends tom.library.sl.BasicStrategy {private  tom.gom.adt.objects.types.ClassName  cName;private  tom.gom.adt.gom.types.HookDecl  hook;public AttachSortHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) {super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() {return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() {return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch442NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);{  tom.gom.adt.objects.types.GomClass  tomMatch442NameNumber_freshVar_2=tomMatch442NameNumberfreshSubject_1;if ( (tomMatch442NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch442NameNumber_freshVar_0= tomMatch442NameNumber_freshVar_2.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch442NameNumber_freshVar_1= tomMatch442NameNumber_freshVar_2.getHooks() ;if ( true ) {







          if (tomMatch442NameNumber_freshVar_0== cName) {
            return
              tomMatch442NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(tomMatch442NameNumber_freshVar_1, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
          }
        }}}}}}}}return _visit_GomClass(tom__arg,introspector); }public  tom.gom.adt.objects.types.GomClass  _visit_GomClass( tom.gom.adt.objects.types.GomClass  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.gom.adt.objects.types.GomClass )any.visit(environment,introspector));} else {return (( tom.gom.adt.objects.types.GomClass )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.gom.adt.objects.types.GomClass) ) {return visit_GomClass((( tom.gom.adt.objects.types.GomClass )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_AttachSortHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachSortHook(t0,t1);}private static class AttachOperatorHook extends tom.library.sl.BasicStrategy {private  tom.gom.adt.objects.types.ClassName  cName;private  tom.gom.adt.gom.types.HookDecl  hook;public AttachOperatorHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) {super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() {return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() {return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumber_freshVar_4=tomMatch443NameNumberfreshSubject_1;if ( (tomMatch443NameNumber_freshVar_4 instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch443NameNumber_freshVar_0= tomMatch443NameNumber_freshVar_4.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch443NameNumber_freshVar_1= tomMatch443NameNumber_freshVar_4.getHooks() ;{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumber_freshVar_2= tomMatch443NameNumber_freshVar_4.getEmpty() ;{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumber_freshVar_3= tomMatch443NameNumber_freshVar_4.getCons() ;{  tom.gom.adt.objects.types.HookList  tom_oldHooks=tomMatch443NameNumber_freshVar_1;{  tom.gom.adt.objects.types.GomClass  tom_emptyClass=tomMatch443NameNumber_freshVar_2;{  tom.gom.adt.objects.types.GomClass  tom_consClass=tomMatch443NameNumber_freshVar_3;{  tom.gom.adt.objects.types.GomClass  tom_obj=tomMatch443NameNumberfreshSubject_1;if ( true ) {








            if (tomMatch443NameNumber_freshVar_0== cName) {
              /* We may want to attach the hook to the cons or empty */
              if (hook.isMakeHookDecl()) {
                if (hook.getSlotArgs() !=  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) {
                  HookList oldConsHooks = tom_consClass.getHooks();
                  GomClass newCons =
                    tom_consClass.setHooks(
                         tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(oldConsHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
                  return tom_obj.setCons(newCons);
                } else if (hook.getSlotArgs() ==  tom.gom.adt.gom.types.slotlist.EmptyConcSlot.make() ) {
                  HookList oldEmptyHooks = tom_emptyClass.getHooks();
                  GomClass newEmpty =
                    tom_emptyClass.setHooks(
                         tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(oldEmptyHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
                  return tom_obj.setEmpty(newEmpty);
                }
              } else if (hook.isImportHookDecl()) {
                /* We will want to attach the hook directly to the 3 classes */
                /* in case we use these imports for the corresponding Make hooks */
                HookList oldConsHooks = tom_consClass.getHooks();
                GomClass newCons =
                  tom_consClass.setHooks(
                       tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(oldConsHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
                HookList oldEmptyHooks = tom_emptyClass.getHooks();
                GomClass newEmpty =
                  tom_emptyClass.setHooks(
                       tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(oldEmptyHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
                GomClass newobj = tom_obj.setEmpty(newEmpty);
                newobj = newobj.setCons(newCons);
                return newobj.setHooks( tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(tom_oldHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
              } else {                
                return
                  tom_obj.setHooks( tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(tom_oldHooks, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
              }
            }
          }}}}}}}}}}}}}if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);{  tom.gom.adt.objects.types.GomClass  tomMatch443NameNumber_freshVar_7=tomMatch443NameNumberfreshSubject_1;if ( (tomMatch443NameNumber_freshVar_7 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch443NameNumber_freshVar_5= tomMatch443NameNumber_freshVar_7.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch443NameNumber_freshVar_6= tomMatch443NameNumber_freshVar_7.getHooks() ;if ( true ) {

          if (tomMatch443NameNumber_freshVar_5== cName) {
            return
              tomMatch443NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsConcHook.make(makeHooksFromHookDecl(hook),tom_append_list_ConcHook(tomMatch443NameNumber_freshVar_6, tom.gom.adt.objects.types.hooklist.EmptyConcHook.make() )) );
          }
        }}}}}}}}return _visit_GomClass(tom__arg,introspector); }public  tom.gom.adt.objects.types.GomClass  _visit_GomClass( tom.gom.adt.objects.types.GomClass  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.gom.adt.objects.types.GomClass )any.visit(environment,introspector));} else {return (( tom.gom.adt.objects.types.GomClass )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.gom.adt.objects.types.GomClass) ) {return visit_GomClass((( tom.gom.adt.objects.types.GomClass )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_AttachOperatorHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachOperatorHook(t0,t1);}



  private static Hook makeHooksFromHookDecl(HookDecl hookDecl) {
    {if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumber_freshVar_2=tomMatch444NameNumberfreshSubject_1;if ( (tomMatch444NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.hookdecl.MakeHookDecl) ) {{  tom.gom.adt.gom.types.SlotList  tomMatch444NameNumber_freshVar_0= tomMatch444NameNumber_freshVar_2.getSlotArgs() ;{  tom.gom.adt.code.types.Code  tomMatch444NameNumber_freshVar_1= tomMatch444NameNumber_freshVar_2.getCode() ;if ( true ) {

        SlotFieldList newArgs = makeSlotFieldListFromSlotList(tomMatch444NameNumber_freshVar_0);
        return  tom.gom.adt.objects.types.hook.MakeHook.make(newArgs, tomMatch444NameNumber_freshVar_1) ;
      }}}}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumber_freshVar_4=tomMatch444NameNumberfreshSubject_1;if ( (tomMatch444NameNumber_freshVar_4 instanceof tom.gom.adt.gom.types.hookdecl.BlockHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch444NameNumber_freshVar_3= tomMatch444NameNumber_freshVar_4.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.BlockHook.make(tomMatch444NameNumber_freshVar_3) ;
      }}}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumber_freshVar_6=tomMatch444NameNumberfreshSubject_1;if ( (tomMatch444NameNumber_freshVar_6 instanceof tom.gom.adt.gom.types.hookdecl.InterfaceHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch444NameNumber_freshVar_5= tomMatch444NameNumber_freshVar_6.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.InterfaceHook.make(tomMatch444NameNumber_freshVar_5) ;
      }}}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumber_freshVar_8=tomMatch444NameNumberfreshSubject_1;if ( (tomMatch444NameNumber_freshVar_8 instanceof tom.gom.adt.gom.types.hookdecl.ImportHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch444NameNumber_freshVar_7= tomMatch444NameNumber_freshVar_8.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.ImportHook.make(tomMatch444NameNumber_freshVar_7) ;
      }}}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);{  tom.gom.adt.gom.types.HookDecl  tomMatch444NameNumber_freshVar_10=tomMatch444NameNumberfreshSubject_1;if ( (tomMatch444NameNumber_freshVar_10 instanceof tom.gom.adt.gom.types.hookdecl.MappingHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch444NameNumber_freshVar_9= tomMatch444NameNumber_freshVar_10.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.MappingHook.make(tomMatch444NameNumber_freshVar_9) ;
      }}}}}}}

    throw new GomRuntimeException(
        "Hook declaration " + hookDecl+ " not processed");
  }

  private static SlotFieldList makeSlotFieldListFromSlotList(SlotList args) {
    SlotFieldList newArgs =  tom.gom.adt.objects.types.slotfieldlist.EmptyConcSlotField.make() ;
    while(!args.isEmptyConcSlot()) {
      Slot arg = args.getHeadConcSlot();
      args = args.getTailConcSlot();
      {if ( (arg instanceof tom.gom.adt.gom.types.Slot) ) {{  tom.gom.adt.gom.types.Slot  tomMatch445NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Slot )arg);{  tom.gom.adt.gom.types.Slot  tomMatch445NameNumber_freshVar_2=tomMatch445NameNumberfreshSubject_1;if ( (tomMatch445NameNumber_freshVar_2 instanceof tom.gom.adt.gom.types.slot.Slot) ) {{  String  tomMatch445NameNumber_freshVar_0= tomMatch445NameNumber_freshVar_2.getName() ;{  tom.gom.adt.gom.types.SortDecl  tomMatch445NameNumber_freshVar_1= tomMatch445NameNumber_freshVar_2.getSort() ;if ( true ) {

          ClassName slotClassName = (ClassName)
            sortClassNameForSortDecl.get(tomMatch445NameNumber_freshVar_1);
          newArgs = tom_append_list_ConcSlotField(newArgs, tom.gom.adt.objects.types.slotfieldlist.ConsConcSlotField.make( tom.gom.adt.objects.types.slotfield.SlotField.make(tomMatch445NameNumber_freshVar_0, slotClassName) , tom.gom.adt.objects.types.slotfieldlist.EmptyConcSlotField.make() ) );
        }}}}}}}}

    }
    return newArgs;
  }
}
