/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2007, INRIA
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
import aterm.ATerm;
import tom.engine.tools.ASTFactory;

import tom.library.sl.*;
import jjtraveler.VisitFailure;

/**
 * The TomSyntaxChecker plugin - justs adds anti-pattern facilities to the TomSyntaxChecker.
 */
public class TomSyntaxCheckerAp extends TomSyntaxChecker {

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_equal_term_int(int t1, int t2) { return  (t1==t2) ;}private static boolean tom_is_sort_int(int t) { return  true ;} private static boolean tom_equal_term_TomType(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomType(Object t) { return  t instanceof tom.engine.adt.tomtype.types.TomType ;}private static boolean tom_equal_term_TomNameList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomNameList(Object t) { return  t instanceof tom.engine.adt.tomname.types.TomNameList ;}private static boolean tom_equal_term_TomName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomName(Object t) { return  t instanceof tom.engine.adt.tomname.types.TomName ;}private static boolean tom_equal_term_TomList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomList(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomList ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomTerm(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomTerm ;}private static boolean tom_equal_term_OptionList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_OptionList(Object t) { return  t instanceof tom.engine.adt.tomoption.types.OptionList ;}private static boolean tom_equal_term_Constraint(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Constraint(Object t) { return  t instanceof tom.engine.adt.tomconstraint.types.Constraint ;}private static boolean tom_equal_term_ConstraintList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_ConstraintList(Object t) { return  t instanceof tom.engine.adt.tomconstraint.types.ConstraintList ;}private static boolean tom_equal_term_SlotList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_SlotList(Object t) { return  t instanceof tom.engine.adt.tomslot.types.SlotList ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getNameList() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getArgs() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;}private static boolean tom_is_fun_sym_RecordAppl( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_RecordAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_RecordAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getNameList() ;}private static  tom.engine.adt.tomslot.types.SlotList  tom_get_slot_RecordAppl_Slots( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getSlots() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_RecordAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;}private static boolean tom_is_fun_sym_XMLAppl( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.XMLAppl ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_XMLAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_XMLAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getNameList() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_XMLAppl_AttrList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getAttrList() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_XMLAppl_ChildList( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getChildList() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_XMLAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;}private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.Variable ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getAstName() ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getAstType() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;}private static boolean tom_is_fun_sym_UnamedVariable( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_UnamedVariable_Option( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getOption() ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_UnamedVariable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getAstType() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_UnamedVariable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getConstraints() ;}private static boolean tom_is_fun_sym_AntiTerm( tom.engine.adt.tomterm.types.TomTerm  t) { return  t instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_AntiTerm_TomTerm( tom.engine.adt.tomterm.types.TomTerm  t) { return  t.getTomTerm() ;}private static boolean tom_is_fun_sym_AssignTo( tom.engine.adt.tomconstraint.types.Constraint  t) { return  t instanceof tom.engine.adt.tomconstraint.types.constraint.AssignTo ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_AssignTo_Variable( tom.engine.adt.tomconstraint.types.Constraint  t) { return  t.getVariable() ;}private static boolean tom_is_fun_sym_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  t) { return  t instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint || t instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_empty_list_concConstraint() { return  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ; }private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_cons_list_concConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(e,l) ; }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  l.getHeadconcConstraint() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_tail_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  l.getTailconcConstraint() ;}private static boolean tom_is_empty_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  l.isEmptyconcConstraint() ;}   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if(tom_is_empty_concConstraint_ConstraintList(l1)) {       return l2;     } else if(tom_is_empty_concConstraint_ConstraintList(l2)) {       return l1;     } else if(tom_is_empty_concConstraint_ConstraintList(tom_get_tail_concConstraint_ConstraintList(l1))) {       return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(tom_get_head_concConstraint_ConstraintList(l1),l2);     } else {       return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(tom_get_head_concConstraint_ConstraintList(l1),tom_append_list_concConstraint(tom_get_tail_concConstraint_ConstraintList(l1),l2));     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if(tom_equal_term_ConstraintList(begin,end)) {       return tail;     } else {       return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(tom_get_head_concConstraint_ConstraintList(begin),( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint(tom_get_tail_concConstraint_ConstraintList(begin),end,tail));     }   }    /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_equal_term_Strategy(Object t1, Object t2) { return t1.equals(t2);}private static boolean tom_is_sort_Strategy(Object t) { return  t instanceof tom.library.sl.Strategy ;}/* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_mu( tom.library.sl.Strategy  var,  tom.library.sl.Strategy  v) { return  new tom.library.sl.Mu(var,v) ; }private static  tom.library.sl.Strategy  tom_make_MuVar( String  name) { return  new tom.library.sl.MuVar(name) ; }private static  tom.library.sl.Strategy  tom_make_Identity() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_make_All( tom.library.sl.Strategy  v) { return  new tom.library.sl.All(v) ; }private static boolean tom_is_fun_sym_Sequence( tom.library.sl.Strategy  t) { return  (t instanceof tom.library.sl.Sequence) ;}private static  tom.library.sl.Strategy  tom_empty_list_Sequence() { return  new tom.library.sl.Identity() ; }private static  tom.library.sl.Strategy  tom_cons_list_Sequence( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { return  new tom.library.sl.Sequence(head,tail) ; }private static  tom.library.sl.Strategy  tom_get_head_Sequence_Strategy( tom.library.sl.Strategy  t) { return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.FIRST) ;}private static  tom.library.sl.Strategy  tom_get_tail_Sequence_Strategy( tom.library.sl.Strategy  t) { return  (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.THEN) ;}private static boolean tom_is_empty_Sequence_Strategy( tom.library.sl.Strategy  t) { return  t instanceof tom.library.sl.Identity ;}   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy l1,  tom.library.sl.Strategy  l2) {     if(tom_is_empty_Sequence_Strategy(l1)) {       return l2;     } else if(tom_is_empty_Sequence_Strategy(l2)) {       return l1;     } else if(tom_is_fun_sym_Sequence(l1)) {       if(tom_is_empty_Sequence_Strategy(((tom_is_fun_sym_Sequence(l1))?tom_get_tail_Sequence_Strategy(l1):tom_empty_list_Sequence()))) {         return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_head_Sequence_Strategy(l1):l1),l2);       } else {         return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_head_Sequence_Strategy(l1):l1),tom_append_list_Sequence(((tom_is_fun_sym_Sequence(l1))?tom_get_tail_Sequence_Strategy(l1):tom_empty_list_Sequence()),l2));       }     } else {       return ( tom.library.sl.Strategy )tom_cons_list_Sequence(l1, l2);     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if(tom_equal_term_Strategy(begin,end)) {       return tail;     } else {       return ( tom.library.sl.Strategy )tom_cons_list_Sequence(((tom_is_fun_sym_Sequence(begin))?tom_get_head_Sequence_Strategy(begin):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((tom_is_fun_sym_Sequence(begin))?tom_get_tail_Sequence_Strategy(begin):tom_empty_list_Sequence()),end,tail));     }   }    /* Generated by TOM (version 2.5alpha): Do not edit this file */ /* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence()))) ; }   

  /**
   * Basicaly ignores the anti-symbol
   */
  public  TermDescription validateTerm(TomTerm term, TomType expectedType, boolean listSymbol, boolean topLevel, boolean permissive) {
    if (tom_is_sort_TomTerm(term)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch112NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )term);if (tom_is_fun_sym_AntiTerm(tomMatch112NameNumberfreshSubject_1)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch112NameNumber_freshVar_1=tom_get_slot_AntiTerm_TomTerm(tomMatch112NameNumberfreshSubject_1);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch112NameNumber_freshVar_0=tomMatch112NameNumber_freshVar_1;{ boolean tomMatch112NameNumber_freshVar_3= false ;{  tom.engine.adt.tomoption.types.OptionList  tomMatch112NameNumber_freshVar_2= null ;if (tom_is_fun_sym_TermAppl(tomMatch112NameNumber_freshVar_0)) {{tomMatch112NameNumber_freshVar_3= true ;tomMatch112NameNumber_freshVar_2=tom_get_slot_TermAppl_Option(tomMatch112NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_Variable(tomMatch112NameNumber_freshVar_0)) {{tomMatch112NameNumber_freshVar_3= true ;tomMatch112NameNumber_freshVar_2=tom_get_slot_Variable_Option(tomMatch112NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_RecordAppl(tomMatch112NameNumber_freshVar_0)) {{tomMatch112NameNumber_freshVar_3= true ;tomMatch112NameNumber_freshVar_2=tom_get_slot_RecordAppl_Option(tomMatch112NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_XMLAppl(tomMatch112NameNumber_freshVar_0)) {{tomMatch112NameNumber_freshVar_3= true ;tomMatch112NameNumber_freshVar_2=tom_get_slot_XMLAppl_Option(tomMatch112NameNumber_freshVar_0);}}}}}if ((tomMatch112NameNumber_freshVar_3 ==  true )) {{  tom.engine.adt.tomterm.types.TomTerm  tom_t=tomMatch112NameNumber_freshVar_0;if ( true ) {


        checkForAnnotations(tom_t,tomMatch112NameNumber_freshVar_2);
        return super.validateTerm(tom_t, expectedType, listSymbol, topLevel, permissive);
      }}}}}}}}}}


    return super.validateTerm(term, expectedType, listSymbol, topLevel, permissive);
  }

  public  TermDescription analyseTerm(TomTerm term) {
    if (tom_is_sort_TomTerm(term)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch113NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )term);if (tom_is_fun_sym_AntiTerm(tomMatch113NameNumberfreshSubject_1)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch113NameNumber_freshVar_1=tom_get_slot_AntiTerm_TomTerm(tomMatch113NameNumberfreshSubject_1);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch113NameNumber_freshVar_0=tomMatch113NameNumber_freshVar_1;{ boolean tomMatch113NameNumber_freshVar_2= false ;if (tom_is_fun_sym_TermAppl(tomMatch113NameNumber_freshVar_0)) {tomMatch113NameNumber_freshVar_2= true ;} else {if (tom_is_fun_sym_Variable(tomMatch113NameNumber_freshVar_0)) {tomMatch113NameNumber_freshVar_2= true ;} else {if (tom_is_fun_sym_RecordAppl(tomMatch113NameNumber_freshVar_0)) {tomMatch113NameNumber_freshVar_2= true ;} else {if (tom_is_fun_sym_XMLAppl(tomMatch113NameNumber_freshVar_0)) {tomMatch113NameNumber_freshVar_2= true ;}}}}if ((tomMatch113NameNumber_freshVar_2 ==  true )) {if ( true ) {


    	  return super.analyseTerm(tomMatch113NameNumber_freshVar_0);
      }}}}}}}}

    return super.analyseTerm(term);
  }

  /**
   * Checks if the given term contains annotations
   * 
   * @param t the term to search
   */
  private void checkForAnnotations(TomTerm t, OptionList options){	  
    String fileName = findOriginTrackingFileName(options);
    int decLine = findOriginTrackingLine(options);
    try {
      tom_make_TopDown(tom_make_CheckForAnnotations(fileName,decLine,t)).visit(t);
    } catch(VisitFailure e) { }
  }


  /**
   * Given a term, it checks if it contains annotations
   * - if the annotations are on head, allow them
   * - error otherwise 
   */  
  private static class CheckForAnnotations extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy  {private  String  fileName; private  int  decLine; private  tom.engine.adt.tomterm.types.TomTerm  headTerm; public CheckForAnnotations( String  fileName,  int  decLine,  tom.engine.adt.tomterm.types.TomTerm  headTerm) { super(tom_make_Identity());this.fileName=fileName;this.decLine=decLine;this.headTerm=headTerm;}public  String  getfileName() { return fileName;}public  int  getdecLine() { return decLine;}public  tom.engine.adt.tomterm.types.TomTerm  getheadTerm() { return headTerm;}public jjtraveler.Visitable[] getChildren() {jjtraveler.Visitable[] stratChilds = new jjtraveler.Visitable[getChildCount()];for (int i = 0; i < getChildCount(); i++) {stratChilds[i]=getChildAt(i);}return stratChilds;}public jjtraveler.Visitable setChildren(jjtraveler.Visitable[] children) {for (int i = 0; i < getChildCount(); i++) {setChildAt(i,children[i]);}return this;}public int getChildCount() { return 1; }public jjtraveler.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public jjtraveler.Visitable setChildAt(int index, jjtraveler.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg) throws jjtraveler.VisitFailure {if (tom_is_sort_TomTerm(tom__arg)) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch114NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch114NameNumber_freshVar_0=tomMatch114NameNumberfreshSubject_1;{ boolean tomMatch114NameNumber_freshVar_6= false ;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumber_freshVar_1= null ;if (tom_is_fun_sym_TermAppl(tomMatch114NameNumber_freshVar_0)) {{tomMatch114NameNumber_freshVar_6= true ;tomMatch114NameNumber_freshVar_1=tom_get_slot_TermAppl_Constraints(tomMatch114NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_Variable(tomMatch114NameNumber_freshVar_0)) {{tomMatch114NameNumber_freshVar_6= true ;tomMatch114NameNumber_freshVar_1=tom_get_slot_Variable_Constraints(tomMatch114NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_RecordAppl(tomMatch114NameNumber_freshVar_0)) {{tomMatch114NameNumber_freshVar_6= true ;tomMatch114NameNumber_freshVar_1=tom_get_slot_RecordAppl_Constraints(tomMatch114NameNumber_freshVar_0);}} else {if (tom_is_fun_sym_UnamedVariable(tomMatch114NameNumber_freshVar_0)) {{tomMatch114NameNumber_freshVar_6= true ;tomMatch114NameNumber_freshVar_1=tom_get_slot_UnamedVariable_Constraints(tomMatch114NameNumber_freshVar_0);}}}}}if ((tomMatch114NameNumber_freshVar_6 ==  true )) {if (tom_is_fun_sym_concConstraint(tomMatch114NameNumber_freshVar_1)) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumber_freshVar_2=tomMatch114NameNumber_freshVar_1;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumberbegin_36=tomMatch114NameNumber_freshVar_2;{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumberend_36=tomMatch114NameNumber_freshVar_2;do {{{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumber_freshVar_3=tomMatch114NameNumberend_36;if (!(tom_is_empty_concConstraint_ConstraintList(tomMatch114NameNumber_freshVar_3))) {if (tom_is_fun_sym_AssignTo(tom_get_head_concConstraint_ConstraintList(tomMatch114NameNumber_freshVar_3))) {{  tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch114NameNumber_freshVar_4=tom_get_tail_concConstraint_ConstraintList(tomMatch114NameNumber_freshVar_3);if ( true ) {


    	if (tomMatch114NameNumber_freshVar_0!= headTerm){  
    		TomChecker.messageError(getClass().getName(),fileName,decLine,
    				TomMessage.illegalAnnotationInAntiPattern, new Object[]{});
    	}	
        //throw new TomRuntimeException("Illegal use of annotations in " + `t);
      }}}}}if (tom_is_empty_concConstraint_ConstraintList(tomMatch114NameNumberend_36)) {tomMatch114NameNumberend_36=tomMatch114NameNumberbegin_36;} else {tomMatch114NameNumberend_36=tom_get_tail_concConstraint_ConstraintList(tomMatch114NameNumberend_36);}}} while(!(tom_equal_term_ConstraintList(tomMatch114NameNumberend_36, tomMatch114NameNumberbegin_36)));}}}}}}}}}}return super.visit_TomTerm(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_CheckForAnnotations( String  t0,  int  t1,  tom.engine.adt.tomterm.types.TomTerm  t2) { return new CheckForAnnotations(t0,t1,t2); }



}
