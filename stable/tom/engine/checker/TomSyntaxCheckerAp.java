/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2009, INRIA
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import tom.engine.TomMessage;
import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomconstraint.types.constraint.*;

import tom.engine.xml.Constants;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.PlatformOptionList;
import tom.engine.tools.ASTFactory;

import tom.library.sl.*;
import tom.library.sl.VisitFailure;

/**
 * The TomSyntaxChecker plugin - justs adds anti-pattern facilities to the TomSyntaxChecker.
 */
public class TomSyntaxCheckerAp extends TomSyntaxChecker {

        private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }         private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }     private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   




  /**
   * Basicaly ignores the anti-symbol
   */
  public  TermDescription validateTerm(TomTerm term, TomType expectedType, boolean listSymbol, boolean topLevel, boolean permissive) {
    {{if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch125NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )term).getTomTerm() ;boolean tomMatch125NameNumber_freshVar_5= false ; tom.engine.adt.tomoption.types.OptionList  tomMatch125NameNumber_freshVar_3= null ;if ( (tomMatch125NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {{tomMatch125NameNumber_freshVar_5= true ;tomMatch125NameNumber_freshVar_3= tomMatch125NameNumber_freshVar_1.getOption() ;}} else {if ( (tomMatch125NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch125NameNumber_freshVar_5= true ;tomMatch125NameNumber_freshVar_3= tomMatch125NameNumber_freshVar_1.getOption() ;}} else {if ( (tomMatch125NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{tomMatch125NameNumber_freshVar_5= true ;tomMatch125NameNumber_freshVar_3= tomMatch125NameNumber_freshVar_1.getOption() ;}} else {if ( (tomMatch125NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.XMLAppl) ) {{tomMatch125NameNumber_freshVar_5= true ;tomMatch125NameNumber_freshVar_3= tomMatch125NameNumber_freshVar_1.getOption() ;}}}}}if ((tomMatch125NameNumber_freshVar_5 ==  true )) { tom.engine.adt.tomterm.types.TomTerm  tom_t=tomMatch125NameNumber_freshVar_1;


        checkForAnnotations(tom_t,tomMatch125NameNumber_freshVar_3);
        return super.validateTerm(tom_t, expectedType, listSymbol, topLevel, permissive);
      }}}}}

    return super.validateTerm(term, expectedType, listSymbol, topLevel, permissive);
  }

  public TermDescription analyseTerm(TomTerm term) {
    {{if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch126NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )term).getTomTerm() ;boolean tomMatch126NameNumber_freshVar_4= false ;if ( (tomMatch126NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {tomMatch126NameNumber_freshVar_4= true ;} else {if ( (tomMatch126NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {tomMatch126NameNumber_freshVar_4= true ;} else {if ( (tomMatch126NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {tomMatch126NameNumber_freshVar_4= true ;} else {if ( (tomMatch126NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.XMLAppl) ) {tomMatch126NameNumber_freshVar_4= true ;}}}}if ((tomMatch126NameNumber_freshVar_4 ==  true )) {


    	  return super.analyseTerm(tomMatch126NameNumber_freshVar_1);
      }}}}}

    return super.analyseTerm(term);
  }

  /**
   * Checks if the given term contains annotations
   * 
   * @param t the term to search
   */
  private void checkForAnnotations(TomTerm t, OptionList options) {	  
    String fileName = findOriginTrackingFileName(options);
    int decLine = findOriginTrackingLine(options);
    try {
      tom_make_TopDown(tom_make_CheckForAnnotations(fileName,decLine,t,this)).visitLight(t);
    } catch(VisitFailure e) {
      throw new TomRuntimeException("Cannot be there");
    }
  }

  /**
   * Given a term, it checks if it contains annotations
   * - if the annotations are on head, allow them
   * - error otherwise 
   */  
  public static class CheckForAnnotations extends tom.library.sl.BasicStrategy {private  String  fileName;private  int  decLine;private  tom.engine.adt.tomterm.types.TomTerm  headTerm;private  TomSyntaxChecker  tsca;public CheckForAnnotations( String  fileName,  int  decLine,  tom.engine.adt.tomterm.types.TomTerm  headTerm,  TomSyntaxChecker  tsca) {super(( new tom.library.sl.Identity() ));this.fileName=fileName;this.decLine=decLine;this.headTerm=headTerm;this.tsca=tsca;}public  String  getfileName() {return fileName;}public  int  getdecLine() {return decLine;}public  tom.engine.adt.tomterm.types.TomTerm  getheadTerm() {return headTerm;}public  TomSyntaxChecker  gettsca() {return tsca;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {boolean tomMatch127NameNumber_freshVar_10= false ; tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch127NameNumber_freshVar_1= null ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {{tomMatch127NameNumber_freshVar_10= true ;tomMatch127NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{tomMatch127NameNumber_freshVar_10= true ;tomMatch127NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {{tomMatch127NameNumber_freshVar_10= true ;tomMatch127NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {{tomMatch127NameNumber_freshVar_10= true ;tomMatch127NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;}}}}}if ((tomMatch127NameNumber_freshVar_10 ==  true )) {if ( ((tomMatch127NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (tomMatch127NameNumber_freshVar_1 instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) { tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch127NameNumber_end_6=tomMatch127NameNumber_freshVar_1;do {{if (!( tomMatch127NameNumber_end_6.isEmptyconcConstraint() )) {if ( ( tomMatch127NameNumber_end_6.getHeadconcConstraint()  instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo) ) {


        if((( tom.engine.adt.tomterm.types.TomTerm )tom__arg)!= headTerm) {
          /** TomChecker.messageError(getClass().getName(),fileName,decLine,
              TomMessage.illegalAnnotationInAntiPattern, new Object[]{}); */
          tsca.messageError(getClass().getName(),fileName,decLine,
              TomMessage.illegalAnnotationInAntiPattern, new Object[]{});
        }	
      }}if ( tomMatch127NameNumber_end_6.isEmptyconcConstraint() ) {tomMatch127NameNumber_end_6=tomMatch127NameNumber_freshVar_1;} else {tomMatch127NameNumber_end_6= tomMatch127NameNumber_end_6.getTailconcConstraint() ;}}} while(!( (tomMatch127NameNumber_end_6==tomMatch127NameNumber_freshVar_1) ));}}}}}return _visit_TomTerm(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_CheckForAnnotations( String  t0,  int  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  TomSyntaxChecker  t3) { return new CheckForAnnotations(t0,t1,t2,t3);}



}
