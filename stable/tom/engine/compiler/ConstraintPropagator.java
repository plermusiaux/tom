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
package tom.engine.compiler;

import java.util.ArrayList;

import tom.engine.TomBase;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.compiler.propagator.*;
import tom.engine.exception.TomRuntimeException;
import tom.library.sl.*;

/**
 * This class is in charge with launching all the propagators,
 * until no more propagations can be made 
 */
public class ConstraintPropagator {

//------------------------------------------------------	
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */             /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */   /* Generated by TOM (version 2.6alpha): Do not edit this file */ 



//------------------------------------------------------

  private static final String propagatorsPackage = "tom.engine.compiler.propagator.";

  private static final String[] propagatorsNames = {"SyntacticPropagator","VariadicPropagator","ArrayPropagator","GeneralPurposePropagator"};

  public static Constraint performPropagations(Constraint constraintToCompile) 
    throws ClassNotFoundException,InstantiationException,IllegalAccessException,VisitFailure{
    
    // counts the propagators that didn't change the expression
    int propCounter = 0;
    int propNb = propagatorsNames.length;    	

    // cache the propagators
    IBasePropagator[] prop = new IBasePropagator[propNb];
    for(int i=0 ; i < propNb ; i++) {
      prop[i] = (IBasePropagator)Class.forName(propagatorsPackage + propagatorsNames[i]).newInstance();
    }
    
    Constraint result= null;
    mainLoop: while(true) {
      for(int i=0 ; i < propNb ; i++) {
        result = prop[i].propagate(constraintToCompile);
        // if nothing was done, start counting 
        propCounter = (result == constraintToCompile) ? (propCounter+1) : 0;        
        // if we applied all the propagators and nothing changed,
        // it's time to stop
        if(propCounter == propNb) { break mainLoop; }
        // reinitialize
        constraintToCompile = result;
      }
    } // end while    
    return result;
  }
  
  
  //[radu] TODO: looks a little bit too complicated: I think we can treat the both cases
  // uniformly
  
  /**
   * Detaches the annotations
   *  
   * a@...b@g(y) << t -> g(y) << t /\ a << t /\ ... /\ b << t
   * 
   * For variableStars: a@...b@X* << t -> Z* << t /\ X* << Z* /\ a << Z* /\ ... /\ b << Z*  
   * This is because the varStars can have at the rhs something that will generate loops,
   * and we don't want to duplicate that to the constraints  
   */
  public static Constraint performDetach(Constraint subject) {    
    Constraint result =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ; 
    {if ( (subject instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch147NameNumberfreshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )subject);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch147NameNumber_freshVar_2=tomMatch147NameNumberfreshSubject_1;if ( (tomMatch147NameNumber_freshVar_2 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_0= tomMatch147NameNumber_freshVar_2.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_1= tomMatch147NameNumber_freshVar_2.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_4=tomMatch147NameNumber_freshVar_0;{ boolean tomMatch147NameNumber_freshVar_8= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_3= null ;if ( (tomMatch147NameNumber_freshVar_4 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{tomMatch147NameNumber_freshVar_8= true ;tomMatch147NameNumber_freshVar_3= tomMatch147NameNumber_freshVar_4.getConstraints() ;}} else {if ( (tomMatch147NameNumber_freshVar_4 instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch147NameNumber_freshVar_8= true ;tomMatch147NameNumber_freshVar_3= tomMatch147NameNumber_freshVar_4.getConstraints() ;}} else {if ( (tomMatch147NameNumber_freshVar_4 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {{tomMatch147NameNumber_freshVar_8= true ;tomMatch147NameNumber_freshVar_3= tomMatch147NameNumber_freshVar_4.getConstraints() ;}}}}if ((tomMatch147NameNumber_freshVar_8 ==  true )) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tom_constraints=tomMatch147NameNumber_freshVar_3;{ boolean tomMatch147NameNumber_freshVar_7= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_5=tomMatch147NameNumber_freshVar_3;if ( ((tomMatch147NameNumber_freshVar_5 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch147NameNumber_freshVar_5 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_6=tomMatch147NameNumber_freshVar_3;if ( (tomMatch147NameNumber_freshVar_6==tom_constraints) ) {if ( tomMatch147NameNumber_freshVar_5.isEmptyconcConstraint() ) {tomMatch147NameNumber_freshVar_7= true ;}}}}}if ((tomMatch147NameNumber_freshVar_7 ==  false )) {if ( true ) {{if ( (tom_constraints instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumberfreshSubject_1=(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_constraints);{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumber_freshVar_0=tomMatch148NameNumberfreshSubject_1;if ( ((tomMatch148NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch148NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumber_begin_2=tomMatch148NameNumber_freshVar_0;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumber_end_3=tomMatch148NameNumber_freshVar_0;do {{{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumber_freshVar_1=tomMatch148NameNumber_end_3;if (!( tomMatch148NameNumber_freshVar_1.isEmptyconcConstraint() )) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch148NameNumber_freshVar_7= tomMatch148NameNumber_freshVar_1.getHeadconcConstraint() ;if ( (tomMatch148NameNumber_freshVar_7 instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch148NameNumber_freshVar_6= tomMatch148NameNumber_freshVar_7.getVariable() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch148NameNumber_freshVar_4= tomMatch148NameNumber_freshVar_1.getTailconcConstraint() ;if ( true ) {



            // add constraint to the list
            result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch148NameNumber_freshVar_6, tomMatch147NameNumber_freshVar_1) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ;                                                                                                                       
          }}}}}}}if ( tomMatch148NameNumber_end_3.isEmptyconcConstraint() ) {tomMatch148NameNumber_end_3=tomMatch148NameNumber_begin_2;} else {tomMatch148NameNumber_end_3= tomMatch148NameNumber_end_3.getTailconcConstraint() ;}}} while(!( (tomMatch148NameNumber_end_3==tomMatch148NameNumber_begin_2) ));}}}}}}}
// end match   
      }}}}}}}}}}}}}}if ( (subject instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch147NameNumberfreshSubject_1=(( tom.engine.adt.tomconstraint.types.Constraint )subject);{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch147NameNumber_freshVar_11=tomMatch147NameNumberfreshSubject_1;if ( (tomMatch147NameNumber_freshVar_11 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_9= tomMatch147NameNumber_freshVar_11.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_10= tomMatch147NameNumber_freshVar_11.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch147NameNumber_freshVar_14=tomMatch147NameNumber_freshVar_9;{ boolean tomMatch147NameNumber_freshVar_18= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_13= null ;{  tom.engine.adt.tomtype.types.TomType  tomMatch147NameNumber_freshVar_12= null ;if ( (tomMatch147NameNumber_freshVar_14 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{tomMatch147NameNumber_freshVar_18= true ;tomMatch147NameNumber_freshVar_12= tomMatch147NameNumber_freshVar_14.getAstType() ;tomMatch147NameNumber_freshVar_13= tomMatch147NameNumber_freshVar_14.getConstraints() ;}} else {if ( (tomMatch147NameNumber_freshVar_14 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {{tomMatch147NameNumber_freshVar_18= true ;tomMatch147NameNumber_freshVar_12= tomMatch147NameNumber_freshVar_14.getAstType() ;tomMatch147NameNumber_freshVar_13= tomMatch147NameNumber_freshVar_14.getConstraints() ;}}}if ((tomMatch147NameNumber_freshVar_18 ==  true )) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tom_constraints=tomMatch147NameNumber_freshVar_13;{ boolean tomMatch147NameNumber_freshVar_17= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_15=tomMatch147NameNumber_freshVar_13;if ( ((tomMatch147NameNumber_freshVar_15 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch147NameNumber_freshVar_15 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch147NameNumber_freshVar_16=tomMatch147NameNumber_freshVar_13;if ( (tomMatch147NameNumber_freshVar_16==tom_constraints) ) {if ( tomMatch147NameNumber_freshVar_15.isEmptyconcConstraint() ) {tomMatch147NameNumber_freshVar_17= true ;}}}}}if ((tomMatch147NameNumber_freshVar_17 ==  false )) {if ( true ) {
        
        TomTerm freshVariable = Compiler.getFreshVariableStar(tomMatch147NameNumber_freshVar_12);
        {if ( (tom_constraints instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumberfreshSubject_1=(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_constraints);{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_0=tomMatch149NameNumberfreshSubject_1;if ( ((tomMatch149NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch149NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_begin_2=tomMatch149NameNumber_freshVar_0;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_end_3=tomMatch149NameNumber_freshVar_0;do {{{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_1=tomMatch149NameNumber_end_3;if (!( tomMatch149NameNumber_freshVar_1.isEmptyconcConstraint() )) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch149NameNumber_freshVar_7= tomMatch149NameNumber_freshVar_1.getHeadconcConstraint() ;if ( (tomMatch149NameNumber_freshVar_7 instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch149NameNumber_freshVar_6= tomMatch149NameNumber_freshVar_7.getVariable() ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch149NameNumber_freshVar_4= tomMatch149NameNumber_freshVar_1.getTailconcConstraint() ;if ( true ) {

            result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch149NameNumber_freshVar_6, freshVariable) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ;                                                                                                                       
          }}}}}}}if ( tomMatch149NameNumber_end_3.isEmptyconcConstraint() ) {tomMatch149NameNumber_end_3=tomMatch149NameNumber_begin_2;} else {tomMatch149NameNumber_end_3= tomMatch149NameNumber_end_3.getTailconcConstraint() ;}}} while(!( (tomMatch149NameNumber_end_3==tomMatch149NameNumber_begin_2) ));}}}}}}}
// end match   
        result =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(freshVariable, tomMatch147NameNumber_freshVar_10) , tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tomMatch147NameNumber_freshVar_9.setConstraints( tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ), freshVariable) ,tom_append_list_AndConstraint(result, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ) 
;
      }}}}}}}}}}}}}}}}

    return result;
  }  
}
