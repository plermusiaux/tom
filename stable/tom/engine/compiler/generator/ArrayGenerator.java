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
package tom.engine.compiler.generator;

import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tomexpression.types.expression.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.library.sl.*;
import tom.engine.tools.SymbolTable;
import tom.engine.exception.TomRuntimeException;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.TomBase;

import tom.engine.compiler.*;
/**
 * Array Generator
 */
public class ArrayGenerator implements IBaseGenerator{

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}   
	

  public Expression generate(Expression expression) throws VisitFailure {
    return (Expression)tom_make_TopDown(tom_make_Generator()).visitLight(expression);		
  }

  // If we find ConstraintToExpression it means that this constraint was not processed	
  private static class Generator extends tom.library.sl.BasicStrategy {public Generator() {super(( new tom.library.sl.Identity() ));}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch161NameNumberfreshSubject_1=(( tom.engine.adt.tomexpression.types.Expression )tom__arg);{  tom.engine.adt.tomexpression.types.Expression  tomMatch161NameNumber_freshVar_1=tomMatch161NameNumberfreshSubject_1;if ( (tomMatch161NameNumber_freshVar_1 instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch161NameNumber_freshVar_0= tomMatch161NameNumber_freshVar_1.getcons() ;{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch161NameNumber_freshVar_4=tomMatch161NameNumber_freshVar_0;if ( (tomMatch161NameNumber_freshVar_4 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_2= tomMatch161NameNumber_freshVar_4.getPattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_3= tomMatch161NameNumber_freshVar_4.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_5=tomMatch161NameNumber_freshVar_2;{ boolean tomMatch161NameNumber_freshVar_11= false ;if ( (tomMatch161NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {tomMatch161NameNumber_freshVar_11= true ;} else {if ( (tomMatch161NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {tomMatch161NameNumber_freshVar_11= true ;}}if ((tomMatch161NameNumber_freshVar_11 ==  true )) {{  tom.engine.adt.tomterm.types.TomTerm  tom_v=tomMatch161NameNumber_freshVar_2;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_10=tomMatch161NameNumber_freshVar_3;if ( (tomMatch161NameNumber_freshVar_10 instanceof tom.engine.adt.tomterm.types.tomterm.VariableHeadArray) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch161NameNumber_freshVar_6= tomMatch161NameNumber_freshVar_10.getOpname() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_7= tomMatch161NameNumber_freshVar_10.getSubject() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_8= tomMatch161NameNumber_freshVar_10.getBeginIndex() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch161NameNumber_freshVar_9= tomMatch161NameNumber_freshVar_10.getEndIndex() ;{  tom.engine.adt.tomname.types.TomName  tom_opName=tomMatch161NameNumber_freshVar_6;{  tom.engine.adt.tomterm.types.TomTerm  tom_subject=tomMatch161NameNumber_freshVar_7;{  tom.engine.adt.tomterm.types.TomTerm  tom_end=tomMatch161NameNumber_freshVar_9;if ( true ) {











        Expression doWhileTest =  tom.engine.adt.tomexpression.types.expression.Negation.make( tom.engine.adt.tomexpression.types.expression.GreaterThan.make( tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make(tom_end) ,  tom.engine.adt.tomexpression.types.expression.GetSize.make(tom_opName, tom_subject) ) ) ;
        // expression at the end of the loop 
        Expression endExpression =  tom.engine.adt.tomexpression.types.expression.ConstraintToExpression.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_end,  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make( tom.engine.adt.tomexpression.types.expression.AddOne.make(tom_end) ) ) ) ;        
        // if we have a varStar, then add its declaration also
        if(tom_v.isVariableStar()) {
          Expression varDeclaration =  tom.engine.adt.tomexpression.types.expression.ConstraintToExpression.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_v,  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make( tom.engine.adt.tomexpression.types.expression.GetSliceArray.make(tom_opName, tom_subject, tomMatch161NameNumber_freshVar_8, tom_end) ) ) ) 
;
          return  tom.engine.adt.tomexpression.types.expression.And.make( tom.engine.adt.tomexpression.types.expression.DoWhileExpression.make(endExpression, doWhileTest) , varDeclaration) ;
        }
        return  tom.engine.adt.tomexpression.types.expression.DoWhileExpression.make(endExpression, doWhileTest) ;		        		      
      }}}}}}}}}}}}}}}}}}}}}}}}return _visit_Expression(tom__arg,introspector); }public  tom.engine.adt.tomexpression.types.Expression  _visit_Expression( tom.engine.adt.tomexpression.types.Expression  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomexpression.types.Expression )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomexpression.types.Expression )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomexpression.types.Expression) ) {return visit_Expression((( tom.engine.adt.tomexpression.types.Expression )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_Generator() { return new Generator();}


 // end strategy
  
  /**
   * return an element of the list
   * when domain=codomain, the test is extended to:
   *   is_fsym_f(t)?get_element(t):t 
   *   the element itself is returned when it is not an array operator operator
   *   this occurs because the last element of an array may not be an array
   */ 
  private static Expression genGetElement(TomName opName, TomTerm var, Expression getElem) {
    TomSymbol tomSymbol = tom.engine.compiler.Compiler.getSymbolTable().getSymbolFromName(((Name)opName).getString());
    TomType domain = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();
    TomType codomain = TomBase.getSymbolCodomain(tomSymbol);
    if(domain==codomain) {
      return  tom.engine.adt.tomexpression.types.expression.Conditional.make( tom.engine.adt.tomexpression.types.expression.IsFsym.make(opName, var) , getElem,  tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make(var) ) ;
    }
    return getElem;
  }
}
