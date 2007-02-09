/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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
 * Radu Kopetz  e-mail: Radu.Kopetz@loria.fr
 *
 **/

package tom.engine.compiler.antipattern;

import java.io.*;
import java.util.*;

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

import tom.engine.exception.*;

import tom.library.strategy.mutraveler.MuTraveler;

import jjtraveler.reflective.VisitableVisitor;
import tom.library.strategy.mutraveler.MuStrategy;
import jjtraveler.VisitFailure;

/**
 * Contains methods for transforming an anti-pattern problem into a disunification one
 */
public class TomAntiPatternTransform {	

//	------------------------------------------------------------
//	%include { adt/tomconstraint/TomConstraint.tom }
  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Option(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_AConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Constraint(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_EmptyType() { return  tom.engine.adt.tomtype.types.tomtype.EmptyType.make(); }private static  tom.engine.adt.tomname.types.TomName  tom_make_Name( String  t0) { return  tom.engine.adt.tomname.types.tomname.Name.make(t0); }private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.Variable  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_Variable( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomtype.types.TomType  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.Variable.make(t0, t1, t2, t3); }private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstType()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_AntiTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_AntiTerm_TomTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTomTerm()  ;}private static boolean tom_is_fun_sym_Neg( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.Neg  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_Neg( tom.engine.adt.tomconstraint.types.Constraint  t0) { return  tom.engine.adt.tomconstraint.types.constraint.Neg.make(t0); }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_slot_Neg_c( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getc()  ;}private static boolean tom_is_fun_sym_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.AndConstraint  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_AndConstraint( tom.engine.adt.tomconstraint.types.AConstraintList  t0) { return  tom.engine.adt.tomconstraint.types.constraint.AndConstraint.make(t0); }private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_get_slot_AndConstraint_cla( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getcla()  ;}private static boolean tom_is_fun_sym_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.OrConstraint  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_OrConstraint( tom.engine.adt.tomconstraint.types.OConstraintList  t0) { return  tom.engine.adt.tomconstraint.types.constraint.OrConstraint.make(t0); }private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_get_slot_OrConstraint_clo( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getclo()  ;}private static boolean tom_is_fun_sym_EqualConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.EqualConstraint  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_EqualConstraint( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomterm.types.TomTerm  t1) { return  tom.engine.adt.tomconstraint.types.constraint.EqualConstraint.make(t0, t1); }private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_EqualConstraint_pattern( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getpattern()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_EqualConstraint_genTerm( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getgenTerm()  ;}private static boolean tom_is_fun_sym_NEqualConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.NEqualConstraint  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_NEqualConstraint( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomterm.types.TomTerm  t1) { return  tom.engine.adt.tomconstraint.types.constraint.NEqualConstraint.make(t0, t1); }private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_NEqualConstraint_pattern( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getpattern()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_NEqualConstraint_genTerm( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getgenTerm()  ;}private static boolean tom_is_fun_sym_Exists( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.Exists  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_Exists( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomconstraint.types.Constraint  t1) { return  tom.engine.adt.tomconstraint.types.constraint.Exists.make(t0, t1); }private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_Exists_var( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getvar()  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_slot_Exists_cons( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getcons()  ;}private static boolean tom_is_fun_sym_ForAll( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.constraint.ForAll  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_ForAll( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomconstraint.types.Constraint  t1) { return  tom.engine.adt.tomconstraint.types.constraint.ForAll.make(t0, t1); }private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_ForAll_var( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getvar()  ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_slot_ForAll_cons( tom.engine.adt.tomconstraint.types.Constraint  t) {  return  t.getcons()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_empty_list_concOption() { return  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ; }private static  tom.engine.adt.tomoption.types.OptionList  tom_cons_list_concOption( tom.engine.adt.tomoption.types.Option  e,  tom.engine.adt.tomoption.types.OptionList  l) { return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(e,l) ; }private static  tom.engine.adt.tomoption.types.Option  tom_get_head_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.getHeadconcOption()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_tail_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.getTailconcOption()  ;}private static boolean tom_is_empty_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {  return  l.isEmptyconcOption()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList  l1,  tom.engine.adt.tomoption.types.OptionList  l2) {    if(tom_is_empty_concOption_OptionList(l1)) {     return l2;    } else if(tom_is_empty_concOption_OptionList(l2)) {     return l1;    } else if(tom_is_empty_concOption_OptionList(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(l1))) {     return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(l1),l2);    } else {      return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(l1),tom_append_list_concOption(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(l1),l2));    }   }  private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end) {    if(tom_terms_equal_OptionList(begin,end)) {      return ( tom.engine.adt.tomoption.types.OptionList )tom_empty_list_concOption();    } else {      return ( tom.engine.adt.tomoption.types.OptionList )tom_cons_list_concOption(( tom.engine.adt.tomoption.types.Option )tom_get_head_concOption_OptionList(begin),( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption(( tom.engine.adt.tomoption.types.OptionList )tom_get_tail_concOption_OptionList(begin),end));    }   }  private static boolean tom_is_fun_sym_concOr( tom.engine.adt.tomconstraint.types.OConstraintList  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.oconstraintlist.ConsconcOr || t instanceof tom.engine.adt.tomconstraint.types.oconstraintlist.EmptyconcOr  ;}private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_empty_list_concOr() { return  tom.engine.adt.tomconstraint.types.oconstraintlist.EmptyconcOr.make() ; }private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_cons_list_concOr( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.OConstraintList  l) { return  tom.engine.adt.tomconstraint.types.oconstraintlist.ConsconcOr.make(e,l) ; }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concOr_OConstraintList( tom.engine.adt.tomconstraint.types.OConstraintList  l) {  return  l.getHeadconcOr()  ;}private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_get_tail_concOr_OConstraintList( tom.engine.adt.tomconstraint.types.OConstraintList  l) {  return  l.getTailconcOr()  ;}private static boolean tom_is_empty_concOr_OConstraintList( tom.engine.adt.tomconstraint.types.OConstraintList  l) {  return  l.isEmptyconcOr()  ;}private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_append_list_concOr( tom.engine.adt.tomconstraint.types.OConstraintList  l1,  tom.engine.adt.tomconstraint.types.OConstraintList  l2) {    if(tom_is_empty_concOr_OConstraintList(l1)) {     return l2;    } else if(tom_is_empty_concOr_OConstraintList(l2)) {     return l1;    } else if(tom_is_empty_concOr_OConstraintList(( tom.engine.adt.tomconstraint.types.OConstraintList )tom_get_tail_concOr_OConstraintList(l1))) {     return ( tom.engine.adt.tomconstraint.types.OConstraintList )tom_cons_list_concOr(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concOr_OConstraintList(l1),l2);    } else {      return ( tom.engine.adt.tomconstraint.types.OConstraintList )tom_cons_list_concOr(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concOr_OConstraintList(l1),tom_append_list_concOr(( tom.engine.adt.tomconstraint.types.OConstraintList )tom_get_tail_concOr_OConstraintList(l1),l2));    }   }  private static  tom.engine.adt.tomconstraint.types.OConstraintList  tom_get_slice_concOr( tom.engine.adt.tomconstraint.types.OConstraintList  begin,  tom.engine.adt.tomconstraint.types.OConstraintList  end) {    if(tom_terms_equal_OConstraintList(begin,end)) {      return ( tom.engine.adt.tomconstraint.types.OConstraintList )tom_empty_list_concOr();    } else {      return ( tom.engine.adt.tomconstraint.types.OConstraintList )tom_cons_list_concOr(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concOr_OConstraintList(begin),( tom.engine.adt.tomconstraint.types.OConstraintList )tom_get_slice_concOr(( tom.engine.adt.tomconstraint.types.OConstraintList )tom_get_tail_concOr_OConstraintList(begin),end));    }   }  private static boolean tom_is_fun_sym_concAnd( tom.engine.adt.tomconstraint.types.AConstraintList  t) {  return  t instanceof tom.engine.adt.tomconstraint.types.aconstraintlist.ConsconcAnd || t instanceof tom.engine.adt.tomconstraint.types.aconstraintlist.EmptyconcAnd  ;}private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_empty_list_concAnd() { return  tom.engine.adt.tomconstraint.types.aconstraintlist.EmptyconcAnd.make() ; }private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_cons_list_concAnd( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.AConstraintList  l) { return  tom.engine.adt.tomconstraint.types.aconstraintlist.ConsconcAnd.make(e,l) ; }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concAnd_AConstraintList( tom.engine.adt.tomconstraint.types.AConstraintList  l) {  return  l.getHeadconcAnd()  ;}private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_get_tail_concAnd_AConstraintList( tom.engine.adt.tomconstraint.types.AConstraintList  l) {  return  l.getTailconcAnd()  ;}private static boolean tom_is_empty_concAnd_AConstraintList( tom.engine.adt.tomconstraint.types.AConstraintList  l) {  return  l.isEmptyconcAnd()  ;}private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_append_list_concAnd( tom.engine.adt.tomconstraint.types.AConstraintList  l1,  tom.engine.adt.tomconstraint.types.AConstraintList  l2) {    if(tom_is_empty_concAnd_AConstraintList(l1)) {     return l2;    } else if(tom_is_empty_concAnd_AConstraintList(l2)) {     return l1;    } else if(tom_is_empty_concAnd_AConstraintList(( tom.engine.adt.tomconstraint.types.AConstraintList )tom_get_tail_concAnd_AConstraintList(l1))) {     return ( tom.engine.adt.tomconstraint.types.AConstraintList )tom_cons_list_concAnd(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concAnd_AConstraintList(l1),l2);    } else {      return ( tom.engine.adt.tomconstraint.types.AConstraintList )tom_cons_list_concAnd(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concAnd_AConstraintList(l1),tom_append_list_concAnd(( tom.engine.adt.tomconstraint.types.AConstraintList )tom_get_tail_concAnd_AConstraintList(l1),l2));    }   }  private static  tom.engine.adt.tomconstraint.types.AConstraintList  tom_get_slice_concAnd( tom.engine.adt.tomconstraint.types.AConstraintList  begin,  tom.engine.adt.tomconstraint.types.AConstraintList  end) {    if(tom_terms_equal_AConstraintList(begin,end)) {      return ( tom.engine.adt.tomconstraint.types.AConstraintList )tom_empty_list_concAnd();    } else {      return ( tom.engine.adt.tomconstraint.types.AConstraintList )tom_cons_list_concAnd(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concAnd_AConstraintList(begin),( tom.engine.adt.tomconstraint.types.AConstraintList )tom_get_slice_concAnd(( tom.engine.adt.tomconstraint.types.AConstraintList )tom_get_tail_concAnd_AConstraintList(begin),end));    }   }  private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_empty_list_concConstraint() { return  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ; }private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_cons_list_concConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(e,l) ; }private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.getHeadconcConstraint()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_tail_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.getTailconcConstraint()  ;}private static boolean tom_is_empty_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {  return  l.isEmptyconcConstraint()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {    if(tom_is_empty_concConstraint_ConstraintList(l1)) {     return l2;    } else if(tom_is_empty_concConstraint_ConstraintList(l2)) {     return l1;    } else if(tom_is_empty_concConstraint_ConstraintList(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(l1))) {     return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(l1),l2);    } else {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(l1),tom_append_list_concConstraint(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(l1),l2));    }   }  private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end) {    if(tom_terms_equal_ConstraintList(begin,end)) {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_empty_list_concConstraint();    } else {      return ( tom.engine.adt.tomconstraint.types.ConstraintList )tom_cons_list_concConstraint(( tom.engine.adt.tomconstraint.types.Constraint )tom_get_head_concConstraint_ConstraintList(begin),( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint(( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_tail_concConstraint_ConstraintList(begin),end));    }   }   /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_Strategy(Object t1, Object t2) {  return t1.equals(t2) ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_mu( tom.library.strategy.mutraveler.MuStrategy  var,  tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.Mu(var,v) ; }/* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Identity() { return  new tom.library.strategy.mutraveler.Identity() ; }private static boolean tom_is_fun_sym_Sequence( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (t instanceof tom.library.strategy.mutraveler.Sequence)  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_empty_list_Sequence() { return  new tom.library.strategy.mutraveler.Identity() ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_cons_list_Sequence( tom.library.strategy.mutraveler.MuStrategy  head,  tom.library.strategy.mutraveler.MuStrategy  tail) { return  new tom.library.strategy.mutraveler.Sequence(head,tail) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_head_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (tom.library.strategy.mutraveler.MuStrategy)t.getChildAt(tom.library.strategy.mutraveler.Sequence.FIRST)  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_tail_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  (tom.library.strategy.mutraveler.MuStrategy)t.getChildAt(tom.library.strategy.mutraveler.Sequence.THEN)  ;}private static boolean tom_is_empty_Sequence_Strategy( tom.library.strategy.mutraveler.MuStrategy  t) {  return  t instanceof tom.library.strategy.mutraveler.Identity  ;}private static  tom.library.strategy.mutraveler.MuStrategy  tom_append_list_Sequence( tom.library.strategy.mutraveler.MuStrategy  l1,  tom.library.strategy.mutraveler.MuStrategy  l2) {    if(tom_is_empty_Sequence_Strategy(l1)) {     return l2;    } else if(tom_is_empty_Sequence_Strategy(l2)) {     return l1;    } else if(tom_is_empty_Sequence_Strategy(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(l1))) {     return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(l1),l2);    } else {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(l1),tom_append_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(l1),l2));    }   }  private static  tom.library.strategy.mutraveler.MuStrategy  tom_get_slice_Sequence( tom.library.strategy.mutraveler.MuStrategy  begin,  tom.library.strategy.mutraveler.MuStrategy  end) {    if(tom_terms_equal_Strategy(begin,end)) {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_empty_list_Sequence();    } else {      return ( tom.library.strategy.mutraveler.MuStrategy )tom_cons_list_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_head_Sequence_Strategy(begin),( tom.library.strategy.mutraveler.MuStrategy )tom_get_slice_Sequence(( tom.library.strategy.mutraveler.MuStrategy )tom_get_tail_Sequence_Strategy(begin),end));    }   }  private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Choice( tom.library.strategy.mutraveler.MuStrategy  first,  tom.library.strategy.mutraveler.MuStrategy  then) { return  new tom.library.strategy.mutraveler.Choice(first,then) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_All( tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.All(v) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_MuVar( String  name) { return  new tom.library.strategy.mutraveler.MuVar(name) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_OneId( tom.library.strategy.mutraveler.MuStrategy  v) { return  new tom.library.strategy.mutraveler.OneId(v) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_SequenceId( tom.library.strategy.mutraveler.MuStrategy  first,  tom.library.strategy.mutraveler.MuStrategy  then) { return  new tom.library.strategy.mutraveler.SequenceId(first,then) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_ChoiceId( tom.library.strategy.mutraveler.MuStrategy  first,  tom.library.strategy.mutraveler.MuStrategy  then) { return  new tom.library.strategy.mutraveler.ChoiceId(first,then) ; } /* Generated by TOM (version 2.5alpha): Do not edit this file */private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_Try( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_Choice(v,tom_make_Identity()) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_TopDownCollect( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_make_Try(tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence())))) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_OnceTopDownId( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_make_ChoiceId(v,tom_make_OneId(tom_make_MuVar("_x")))) ; }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_InnermostId( tom.library.strategy.mutraveler.MuStrategy  v) { return tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_cons_list_Sequence(tom_make_SequenceId(v,tom_make_MuVar("_x")),tom_empty_list_Sequence()))) ; }   /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_terms_equal_Collection(Object l1, Object l2) {  return  l1.equals(l2)  ;} 

	
//	------------------------------------------------------------

	private static int varCounter = 0;
	
	/**
	 * transforms the anti-pattern problem received into a disunification one
	 * @param c the anti-pattern problem to transform 
	 * @return corresponding disunification problem 
	 */
	public static Constraint transform(Constraint c) {	
    // eliminate anti
    Constraint noAnti = applyMainRule(c);    
    // transform the problem into a disunification one		
    return (Constraint) tom_make_InnermostId(tom_make_TransformIntoDisunification()).apply(noAnti);			
	}	
	
	/**
   * applies the main rule that transforms ap problems
   * into dis-unification ones
   */
  private static Constraint applyMainRule(Constraint c) {
    /*
     * to improve the efficiency, we should
     * first, abstract anti symbols, and get cAntiReplaced
     * store the tuple (abstractedTerm, variable) during the abstraction
     * then, re-instantiate the abstractedVariables to deduce cNoAnti
     * this would avoid the double recursive traversal
     */
	
    // first get the constraint without the anti
    Constraint cNoAnti = (Constraint) tom_make_OnceTopDownId(tom_make_ElimAnti()).apply(c);
    // if nothing changed, time to exit
    if(cNoAnti == c) {
      return c;
    } 
    cNoAnti = tom_make_Neg(applyMainRule(cNoAnti));

    /*
     * find an Anti(...)
     * an then collect (under this Anti) variables which are not under another Anti
     * TODO: this is strange since we do not collect variables which are in other branches
     */
    Collection quantifiedVarList = new ArrayList();
    tom_make_OnceTopDownId(tom_make_SequenceId(tom_make_ElimAnti(),tom_make_TopDownCollect(tom_make_CollectPositiveVariable(quantifiedVarList)))).apply(c);

    //`OnceTopDownId(_Anti(TopDownCollect(CollectPositiveVariable(quantifiedVarList)))).apply(c);

    //System.out.println("quantifiedVarList = " + quantifiedVarList);
    Iterator it = quantifiedVarList.iterator();
    while(it.hasNext()) {
      TomTerm t = (TomTerm)it.next();
      cNoAnti = tom_make_ForAll(t,cNoAnti);
    }

    // get the constraint with a variable instead of anti
    String varName = "v" + (varCounter++);
    TomTerm abstractVariable = tom_make_Variable(tom_empty_list_concOption(),tom_make_Name(varName),tom_make_EmptyType(),tom_empty_list_concConstraint());
    //Constraint cAntiReplaced = (Constraint) `OnceTopDownId(SequenceId(ElimAnti(),AbstractTerm(abstractVariable))).apply(c);
    Constraint cAntiReplaced = (Constraint) tom_make_OnceTopDownId(tom_make_AbstractTerm(abstractVariable)).apply(c);
    cAntiReplaced = applyMainRule(cAntiReplaced);

    return tom_make_AndConstraint(tom_cons_list_concAnd(tom_make_Exists(abstractVariable,cAntiReplaced),tom_cons_list_concAnd(cNoAnti,tom_empty_list_concAnd())));
  }

  // collect variables, a do not inspect under an AntiTerm
	 private static class CollectPositiveVariable  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  java.util.Collection  bag;  public CollectPositiveVariable(  java.util.Collection  bag ) { super(tom_make_Identity() ); this.bag=bag; } public  java.util.Collection  getbag() { return bag;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); if ( ( tom_is_fun_sym_AntiTerm(tom_match1_1) ||  false  ) ) { if ( true ) {


        throw new VisitFailure();
       } } if ( ( tom_is_fun_sym_Variable(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_v=tom_match1_1; if ( true ) {


        bag.add(tom_v);
        throw new VisitFailure();
       } } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_CollectPositiveVariable( java.util.Collection  t0) { return new CollectPositiveVariable(t0); }



	// remove an anti-symbol
	 private static class ElimAnti  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { public ElimAnti( ) { super(tom_make_Identity() ); } public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); if ( ( tom_is_fun_sym_AntiTerm(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_p=tom_get_slot_AntiTerm_TomTerm(tom_match2_1); if ( true ) {

 return tom_p;  } } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_ElimAnti() { return new ElimAnti(); }


	
	// replace a term by another (a variable)
	 private static class AbstractTerm  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  tom.engine.adt.tomterm.types.TomTerm  variable;  public AbstractTerm(  tom.engine.adt.tomterm.types.TomTerm  variable ) { super(tom_make_Identity() ); this.variable=variable; } public  tom.engine.adt.tomterm.types.TomTerm  getvariable() { return variable;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); if ( ( tom_is_fun_sym_AntiTerm(tom_match3_1) ||  false  ) ) { if ( true ) {

 return variable;  } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_AbstractTerm( tom.engine.adt.tomterm.types.TomTerm  t0) { return new AbstractTerm(t0); }


	
	// applies symple logic rules for eliminating 
	// the not and thus creating a real disunification problem
	 private static class TransformIntoDisunification  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { public TransformIntoDisunification( ) { super(tom_make_Identity() ); } public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint(  tom.engine.adt.tomconstraint.types.Constraint  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomconstraint.types.Constraint ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1=(( tom.engine.adt.tomconstraint.types.Constraint )tom__arg); if ( ( tom_is_fun_sym_Exists(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_cons=tom_get_slot_Exists_cons(tom_match4_1); { tom.engine.adt.tomterm.types.TomTerm  tom_a=tom_get_slot_Exists_var(tom_match4_1); if ( ( tom_is_fun_sym_Exists(tom_match4_1_cons) ||  false  ) ) { if (tom_terms_equal_TomTerm(tom_a, tom_get_slot_Exists_var(tom_match4_1_cons))) { { tom.engine.adt.tomconstraint.types.Constraint  tom_e=tom_match4_1_cons; if ( true ) {


 return tom_e;  } } } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_AndConstraint(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.AConstraintList  tom_a=tom_get_slot_AndConstraint_cla(tom_match4_1_c); if ( true ) {



				AConstraintList l = tom_a;
				OConstraintList result = tom_empty_list_concOr();			
				while(!l.isEmptyconcAnd()){
					result = tom_cons_list_concOr(tom_make_Neg(l.getHeadconcAnd()),tom_append_list_concOr(result,tom_empty_list_concOr()));
					l = l.getTailconcAnd();
				}				
				result = result.reverse();
				return tom_make_OrConstraint(result);
			 } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_OrConstraint(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.OConstraintList  tom_a=tom_get_slot_OrConstraint_clo(tom_match4_1_c); if ( true ) {



				OConstraintList l = tom_a;
				AConstraintList result = tom_empty_list_concAnd();
				while(!l.isEmptyconcOr()){
					result = tom_cons_list_concAnd(tom_make_Neg(l.getHeadconcOr()),tom_append_list_concAnd(result,tom_empty_list_concAnd()));
					l = l.getTailconcOr();
				}
				result = result.reverse();
				return tom_make_AndConstraint(result);
			 } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_Neg(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_x=tom_get_slot_Neg_c(tom_match4_1_c); if ( true ) {


 return tom_x;  } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_EqualConstraint(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_a=tom_get_slot_EqualConstraint_pattern(tom_match4_1_c); { tom.engine.adt.tomterm.types.TomTerm  tom_b=tom_get_slot_EqualConstraint_genTerm(tom_match4_1_c); if ( true ) {


 return tom_make_NEqualConstraint(tom_a,tom_b); } } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_NEqualConstraint(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_a=tom_get_slot_NEqualConstraint_pattern(tom_match4_1_c); { tom.engine.adt.tomterm.types.TomTerm  tom_b=tom_get_slot_NEqualConstraint_genTerm(tom_match4_1_c); if ( true ) {


 return tom_make_EqualConstraint(tom_a,tom_b); } } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_Exists(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_v=tom_get_slot_Exists_var(tom_match4_1_c); { tom.engine.adt.tomconstraint.types.Constraint  tom_c=tom_get_slot_Exists_cons(tom_match4_1_c); if ( true ) {


 return tom_make_ForAll(tom_v,tom_make_Neg(tom_c));  } } } } } } if ( ( tom_is_fun_sym_Neg(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomconstraint.types.Constraint  tom_match4_1_c=tom_get_slot_Neg_c(tom_match4_1); if ( ( tom_is_fun_sym_ForAll(tom_match4_1_c) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_v=tom_get_slot_ForAll_var(tom_match4_1_c); { tom.engine.adt.tomconstraint.types.Constraint  tom_c=tom_get_slot_ForAll_cons(tom_match4_1_c); if ( true ) {


 return tom_make_Exists(tom_v,tom_make_Neg(tom_c));  } } } } } } } } return super.visit_Constraint(tom__arg) ;  } }private static  tom.library.strategy.mutraveler.MuStrategy  tom_make_TransformIntoDisunification() { return new TransformIntoDisunification(); }

		
} // end class
