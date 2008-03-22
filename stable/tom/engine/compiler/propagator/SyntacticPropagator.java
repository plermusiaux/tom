/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2008, INRIA
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
 * Radu Kopetz e-mail: Radu.Kopetz@loria.fr
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/
package tom.engine.compiler.propagator;

import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomname.types.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.tools.SymbolTable;
import tom.engine.compiler.*;
import tom.engine.TomBase;
import java.util.*;
import tom.engine.exception.TomRuntimeException;
import tom.engine.compiler.Compiler;

/**
 * Syntactic propagator
 */
public class SyntacticPropagator implements IBasePropagator {

//--------------------------------------------------------
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_OrConstraintDisjunction( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyOrConstraintDisjunction() ) {       return l2;     } else if( l2.isEmptyOrConstraintDisjunction() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? l1.getTailOrConstraintDisjunction() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ).isEmptyOrConstraintDisjunction() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? l1.getHeadOrConstraintDisjunction() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? l1.getHeadOrConstraintDisjunction() :l1),tom_append_list_OrConstraintDisjunction((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? l1.getTailOrConstraintDisjunction() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_OrConstraintDisjunction( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrConstraintDisjunction()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? begin.getHeadOrConstraintDisjunction() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_OrConstraintDisjunction((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction)) )? begin.getTailOrConstraintDisjunction() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }      private static   tom.engine.adt.tomslot.types.SlotList  tom_append_list_concSlot( tom.engine.adt.tomslot.types.SlotList l1,  tom.engine.adt.tomslot.types.SlotList  l2) {     if( l1.isEmptyconcSlot() ) {       return l2;     } else if( l2.isEmptyconcSlot() ) {       return l1;     } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.SlotList  tom_get_slice_concSlot( tom.engine.adt.tomslot.types.SlotList  begin,  tom.engine.adt.tomslot.types.SlotList  end, tom.engine.adt.tomslot.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcSlot()  ||  (end== tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.engine.adt.tomslot.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;   }    /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   
	
//--------------------------------------------------------
  
  // contains variables that were already replaced (for optimizing reasons)
  private static ArrayList replacedVariables = null; 

  public Constraint propagate(Constraint constraint) throws VisitFailure {   
    replacedVariables = new ArrayList(); 
    return  (Constraint)tom_make_TopDown(tom_make_SyntacticPatternMatching()).visitLight(constraint);
  }	

  private static class SyntacticPatternMatching extends tom.library.sl.BasicStrategy {public SyntacticPatternMatching() {super(( new tom.library.sl.Identity() ));}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch172NameNumberfreshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )tom__arg);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch172NameNumber_freshVar_2=tomMatch172NameNumberfreshSubject_1;if ( (tomMatch172NameNumber_freshVar_2 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch172NameNumber_freshVar_0= tomMatch172NameNumber_freshVar_2.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch172NameNumber_freshVar_1= tomMatch172NameNumber_freshVar_2.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch172NameNumber_freshVar_7=tomMatch172NameNumber_freshVar_0;if ( (tomMatch172NameNumber_freshVar_7 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{  tom.engine.adt.tomoption.types.OptionList  tomMatch172NameNumber_freshVar_3= tomMatch172NameNumber_freshVar_7.getOption() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch172NameNumber_freshVar_4= tomMatch172NameNumber_freshVar_7.getNameList() ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch172NameNumber_freshVar_5= tomMatch172NameNumber_freshVar_7.getSlots() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch172NameNumber_freshVar_6= tomMatch172NameNumber_freshVar_7.getConstraints() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch172NameNumber_freshVar_8=tomMatch172NameNumber_freshVar_4;if ( ((tomMatch172NameNumber_freshVar_8 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch172NameNumber_freshVar_8 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {{  tom.engine.adt.tomname.types.TomNameList  tom_nameList=tomMatch172NameNumber_freshVar_4;if (!( tomMatch172NameNumber_freshVar_8.isEmptyconcTomName() )) {{  tom.engine.adt.tomname.types.TomName  tomMatch172NameNumber_freshVar_12= tomMatch172NameNumber_freshVar_8.getHeadconcTomName() ;if ( (tomMatch172NameNumber_freshVar_12 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch172NameNumber_freshVar_11= tomMatch172NameNumber_freshVar_12.getString() ;{  tom.engine.adt.tomname.types.TomName  tom_firstName= tomMatch172NameNumber_freshVar_8.getHeadconcTomName() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch172NameNumber_freshVar_9= tomMatch172NameNumber_freshVar_8.getTailconcTomName() ;{  tom.engine.adt.tomslot.types.SlotList  tom_slots=tomMatch172NameNumber_freshVar_5;{  tom.engine.adt.tomterm.types.TomTerm  tom_g=tomMatch172NameNumber_freshVar_1;{  tom.engine.adt.tomconstraint.types.Constraint  tom_m=tomMatch172NameNumberfreshSubject_1;{ boolean tomMatch172NameNumber_freshVar_15= false ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch172NameNumber_freshVar_13=tomMatch172NameNumber_freshVar_1;if ( (tomMatch172NameNumber_freshVar_13 instanceof tom.engine.adt.tomterm.types.tomterm.SymbolOf) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch172NameNumber_freshVar_14=tomMatch172NameNumber_freshVar_1;if ( (tomMatch172NameNumber_freshVar_14==tom_g) ) {tomMatch172NameNumber_freshVar_15= true ;}}}}if ((tomMatch172NameNumber_freshVar_15 ==  false )) {if ( true ) {


















        // if this a list or array, nothing to do
        if(!TomBase.isSyntacticOperator(
            Compiler.getSymbolTable().getSymbolFromName(tomMatch172NameNumber_freshVar_11))) { return tom_m; }
       
        //System.out.println("m = " + `m);
        Constraint lastPart =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ;
        ArrayList<TomTerm> freshVarList = new ArrayList<TomTerm>();
        // we build the last part only once, and we store the fresh variables we generate
        {if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumberfreshSubject_1=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumber_freshVar_0=tomMatch173NameNumberfreshSubject_1;if ( ((tomMatch173NameNumber_freshVar_0 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch173NameNumber_freshVar_0 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumber_begin_2=tomMatch173NameNumber_freshVar_0;{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumber_end_3=tomMatch173NameNumber_freshVar_0;do {{{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumber_freshVar_1=tomMatch173NameNumber_end_3;if (!( tomMatch173NameNumber_freshVar_1.isEmptyconcSlot() )) {{  tom.engine.adt.tomslot.types.Slot  tomMatch173NameNumber_freshVar_8= tomMatch173NameNumber_freshVar_1.getHeadconcSlot() ;if ( (tomMatch173NameNumber_freshVar_8 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch173NameNumber_freshVar_6= tomMatch173NameNumber_freshVar_8.getSlotName() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch173NameNumber_freshVar_7= tomMatch173NameNumber_freshVar_8.getAppl() ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch173NameNumber_freshVar_4= tomMatch173NameNumber_freshVar_1.getTailconcSlot() ;if ( true ) {

            TomTerm freshVar = Compiler.getFreshVariable(Compiler.getSlotType(tom_firstName,tomMatch173NameNumber_freshVar_6));
            // store the fresh variable
            freshVarList.add(freshVar);
            // build the last part
            lastPart = tom_append_list_AndConstraint(lastPart, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch173NameNumber_freshVar_7, freshVar) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) );              
          }}}}}}}}if ( tomMatch173NameNumber_end_3.isEmptyconcSlot() ) {tomMatch173NameNumber_end_3=tomMatch173NameNumber_begin_2;} else {tomMatch173NameNumber_end_3= tomMatch173NameNumber_end_3.getTailconcSlot() ;}}} while(!( (tomMatch173NameNumber_end_3==tomMatch173NameNumber_begin_2) ));}}}}}}}

        TomTerm freshSubject = Compiler.getFreshVariable(Compiler.getTermTypeFromTerm(tom_g));
        // take each symbol and build the disjunction
        Constraint l =  tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ;
        {if ( (tom_nameList instanceof tom.engine.adt.tomname.types.TomNameList) ) {{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumberfreshSubject_1=(( tom.engine.adt.tomname.types.TomNameList )tom_nameList);{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumber_freshVar_0=tomMatch174NameNumberfreshSubject_1;if ( ((tomMatch174NameNumber_freshVar_0 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch174NameNumber_freshVar_0 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumber_begin_2=tomMatch174NameNumber_freshVar_0;{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumber_end_3=tomMatch174NameNumber_freshVar_0;do {{{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumber_freshVar_1=tomMatch174NameNumber_end_3;if (!( tomMatch174NameNumber_freshVar_1.isEmptyconcTomName() )) {{  tom.engine.adt.tomname.types.TomName  tom_name= tomMatch174NameNumber_freshVar_1.getHeadconcTomName() ;{  tom.engine.adt.tomname.types.TomNameList  tomMatch174NameNumber_freshVar_4= tomMatch174NameNumber_freshVar_1.getTailconcTomName() ;if ( true ) {

            // the 'and' conjunction for each name
            Constraint andForName =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ;
            // add condition for symbolOf
            andForName =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tomMatch172NameNumber_freshVar_3,  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make(tom_name, tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) ,  tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,  tom.engine.adt.tomterm.types.tomterm.SymbolOf.make(freshSubject) ) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ;
            int counter = 0;          
            // for each slot
            {if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumberfreshSubject_1=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumber_freshVar_0=tomMatch175NameNumberfreshSubject_1;if ( ((tomMatch175NameNumber_freshVar_0 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch175NameNumber_freshVar_0 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumber_begin_2=tomMatch175NameNumber_freshVar_0;{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumber_end_3=tomMatch175NameNumber_freshVar_0;do {{{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumber_freshVar_1=tomMatch175NameNumber_end_3;if (!( tomMatch175NameNumber_freshVar_1.isEmptyconcSlot() )) {{  tom.engine.adt.tomslot.types.Slot  tomMatch175NameNumber_freshVar_8= tomMatch175NameNumber_freshVar_1.getHeadconcSlot() ;if ( (tomMatch175NameNumber_freshVar_8 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch175NameNumber_freshVar_6= tomMatch175NameNumber_freshVar_8.getSlotName() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch175NameNumber_freshVar_7= tomMatch175NameNumber_freshVar_8.getAppl() ;{  tom.engine.adt.tomslot.types.SlotList  tomMatch175NameNumber_freshVar_4= tomMatch175NameNumber_freshVar_1.getTailconcSlot() ;if ( true ) {
                                          
                TomTerm freshVar = freshVarList.get(counter);          
                andForName = tom_append_list_AndConstraint(andForName, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVar,  tom.engine.adt.tomterm.types.tomterm.Subterm.make(tom_name, tomMatch175NameNumber_freshVar_6, freshSubject) ) , tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) );
                counter++;
              }}}}}}}}if ( tomMatch175NameNumber_end_3.isEmptyconcSlot() ) {tomMatch175NameNumber_end_3=tomMatch175NameNumber_begin_2;} else {tomMatch175NameNumber_end_3= tomMatch175NameNumber_end_3.getTailconcSlot() ;}}} while(!( (tomMatch175NameNumber_end_3==tomMatch175NameNumber_begin_2) ));}}}}}}}
// match slots
            l = tom_append_list_OrConstraintDisjunction(l, tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraintDisjunction.make(andForName, tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraintDisjunction.make() ) );
          }}}}}if ( tomMatch174NameNumber_end_3.isEmptyconcTomName() ) {tomMatch174NameNumber_end_3=tomMatch174NameNumber_begin_2;} else {tomMatch174NameNumber_end_3= tomMatch174NameNumber_end_3.getTailconcTomName() ;}}} while(!( (tomMatch174NameNumber_end_3==tomMatch174NameNumber_begin_2) ));}}}}}}}

        return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshSubject, tom_g) ,tom_append_list_AndConstraint(l,tom_append_list_AndConstraint(lastPart, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(ConstraintPropagator.performDetach(tom_m), tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ))) ;
      }}}}}}}}}}}}}}}}}}}}}}}}}}}}return _visit_Constraint(tom__arg,introspector); }public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_SyntacticPatternMatching() { return new SyntacticPatternMatching();}

// end %strategy
}
