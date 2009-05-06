// $ANTLR 2.7.7 (20060906): "BackQuoteLanguage.g" -> "BackQuoteParser.java"$
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
 *
 **/

package tom.engine.parser;
  

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

import java.util.LinkedList;

import tom.engine.TomBase;
import tom.engine.xml.Constants;

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

import tom.engine.tools.ASTFactory;
import tom.engine.tools.SymbolTable;
import antlr.TokenStreamSelector;
import aterm.*;

public class BackQuoteParser extends antlr.LLkParser       implements BackQuoteParserTokenTypes
 {

	private final static String DEFAULT_MODULE_NAME = "default";
	private final static String TNODE_MODULE_NAME = "tnode";
    private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} private static boolean tom_equal_term_Declaration(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Declaration(Object t) {return  (t instanceof tom.engine.adt.tomdeclaration.types.Declaration) ;}private static boolean tom_equal_term_DeclarationList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_DeclarationList(Object t) {return  (t instanceof tom.engine.adt.tomdeclaration.types.DeclarationList) ;}private static boolean tom_equal_term_TomType(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomType(Object t) {return  (t instanceof tom.engine.adt.tomtype.types.TomType) ;}private static boolean tom_equal_term_TomTypeList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomTypeList(Object t) {return  (t instanceof tom.engine.adt.tomtype.types.TomTypeList) ;}private static boolean tom_equal_term_TomSymbolTable(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbolTable(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbolTable) ;}private static boolean tom_equal_term_TomSymbol(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbol(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbol) ;}private static boolean tom_equal_term_TomStructureTable(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomStructureTable(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomStructureTable) ;}private static boolean tom_equal_term_TargetLanguage(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TargetLanguage(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TargetLanguage) ;}private static boolean tom_equal_term_TomEntryList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomEntryList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomEntryList) ;}private static boolean tom_equal_term_TomEntry(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomEntry(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomEntry) ;}private static boolean tom_equal_term_TomVisitList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomVisitList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomVisitList) ;}private static boolean tom_equal_term_TomSymbolList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbolList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbolList) ;}private static boolean tom_equal_term_TextPosition(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TextPosition(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TextPosition) ;}private static boolean tom_equal_term_TomVisit(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomVisit(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomVisit) ;}private static boolean tom_equal_term_KeyEntry(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_KeyEntry(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.KeyEntry) ;}private static boolean tom_equal_term_ConstraintInstruction(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintInstruction(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.ConstraintInstruction) ;}private static boolean tom_equal_term_Instruction(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Instruction(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.Instruction) ;}private static boolean tom_equal_term_InstructionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_InstructionList(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.InstructionList) ;}private static boolean tom_equal_term_ConstraintInstructionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintInstructionList(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.ConstraintInstructionList) ;}private static boolean tom_equal_term_Slot(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Slot(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.Slot) ;}private static boolean tom_equal_term_SlotList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_SlotList(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.SlotList) ;}private static boolean tom_equal_term_PairNameDecl(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_PairNameDecl(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.PairNameDecl) ;}private static boolean tom_equal_term_PairNameDeclList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_PairNameDeclList(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.PairNameDeclList) ;}private static boolean tom_equal_term_TomNumber(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNumber(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNumber) ;}private static boolean tom_equal_term_TomNameList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNameList(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNameList) ;}private static boolean tom_equal_term_TomNumberList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNumberList(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNumberList) ;}private static boolean tom_equal_term_TomName(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomName(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomName) ;}private static boolean tom_equal_term_OptionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OptionList(Object t) {return  (t instanceof tom.engine.adt.tomoption.types.OptionList) ;}private static boolean tom_equal_term_Option(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Option(Object t) {return  (t instanceof tom.engine.adt.tomoption.types.Option) ;}private static boolean tom_equal_term_Expression(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Expression(Object t) {return  (t instanceof tom.engine.adt.tomexpression.types.Expression) ;}private static boolean tom_equal_term_NumericConstraintType(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_NumericConstraintType(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ;}private static boolean tom_equal_term_Constraint(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Constraint(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.Constraint) ;}private static boolean tom_equal_term_ConstraintList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintList(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ;}private static boolean tom_equal_term_Theory(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Theory(Object t) {return  (t instanceof tom.engine.adt.theory.types.Theory) ;}private static boolean tom_equal_term_ElementaryTheory(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ElementaryTheory(Object t) {return  (t instanceof tom.engine.adt.theory.types.ElementaryTheory) ;}private static boolean tom_equal_term_TomList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomList(Object t) {return  (t instanceof tom.engine.adt.tomterm.types.TomList) ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomTerm(Object t) {return  (t instanceof tom.engine.adt.tomterm.types.TomTerm) ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_ITL( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.ITL.make(t0) ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {return  (t instanceof tom.engine.adt.tomname.types.tomname.Name) ;}private static  tom.engine.adt.tomname.types.TomName  tom_make_Name( String  t0) { return  tom.engine.adt.tomname.types.tomname.Name.make(t0) ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {return  t.getString() ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_OriginTracking( tom.engine.adt.tomname.types.TomName  t0,  int  t1,  String  t2) { return  tom.engine.adt.tomoption.types.option.OriginTracking.make(t0, t1, t2) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_Constant() { return  tom.engine.adt.tomoption.types.option.Constant.make() ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_ModuleName( String  t0) { return  tom.engine.adt.tomoption.types.option.ModuleName.make(t0) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_Variable( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomtype.types.TomType  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.Variable.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_VariableStar( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomtype.types.TomType  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.VariableStar.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TargetLanguageToTomTerm( tom.engine.adt.tomsignature.types.TargetLanguage  t0) { return  tom.engine.adt.tomterm.types.tomterm.TargetLanguageToTomTerm.make(t0) ;}private static boolean tom_is_fun_sym_BackQuoteAppl( tom.engine.adt.tomterm.types.TomTerm  t) {return  (t instanceof tom.engine.adt.tomterm.types.tomterm.BackQuoteAppl) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_BackQuoteAppl( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomterm.types.TomList  t2) { return  tom.engine.adt.tomterm.types.tomterm.BackQuoteAppl.make(t0, t1, t2) ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_BackQuoteAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BackQuoteAppl_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getAstName() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_BackQuoteAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getArgs() ;}private static boolean tom_is_fun_sym_Composite( tom.engine.adt.tomterm.types.TomTerm  t) {return  (t instanceof tom.engine.adt.tomterm.types.tomterm.Composite) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_Composite( tom.engine.adt.tomterm.types.TomList  t0) { return  tom.engine.adt.tomterm.types.tomterm.Composite.make(t0) ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_Composite_Args( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getArgs() ;}private static boolean tom_is_fun_sym_concOption( tom.engine.adt.tomoption.types.OptionList  t) {return  ((t instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (t instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_empty_list_concOption() { return  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_cons_list_concOption( tom.engine.adt.tomoption.types.Option  e,  tom.engine.adt.tomoption.types.OptionList  l) { return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(e,l) ;}private static  tom.engine.adt.tomoption.types.Option  tom_get_head_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.getHeadconcOption() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_tail_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.getTailconcOption() ;}private static boolean tom_is_empty_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.isEmptyconcOption() ;}   private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end==tom_empty_list_concOption()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  t) {return  ((t instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (t instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_empty_list_concConstraint() { return  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_cons_list_concConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(e,l) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.getHeadconcConstraint() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_tail_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.getTailconcConstraint() ;}private static boolean tom_is_empty_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.isEmptyconcConstraint() ;}   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end==tom_empty_list_concConstraint()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concTomTerm( tom.engine.adt.tomterm.types.TomList  t) {return  ((t instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (t instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ;}private static  tom.engine.adt.tomterm.types.TomList  tom_empty_list_concTomTerm() { return  tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_cons_list_concTomTerm( tom.engine.adt.tomterm.types.TomTerm  e,  tom.engine.adt.tomterm.types.TomList  l) { return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(e,l) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_head_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.getHeadconcTomTerm() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_tail_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.getTailconcTomTerm() ;}private static boolean tom_is_empty_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.isEmptyconcTomTerm() ;}   private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {     if( l1.isEmptyconcTomTerm() ) {       return l2;     } else if( l2.isEmptyconcTomTerm() ) {       return l1;     } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;     } else {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;     }   }   private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end==tom_empty_list_concTomTerm()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;   }    
    
    // the lexer for backquote language
    BackQuoteLexer bqlexer = null;

    // the parser for tom language
    TomParser tomparser = null;

    // the current file's name
    String currentFile(){
      return tomparser.currentFile();
    }

    //constructor
    public BackQuoteParser(ParserSharedInputState state, TomParser tomparser){
      this(state);
      this.tomparser = tomparser;
      bqlexer = (BackQuoteLexer) selector().getStream("bqlexer");
    }

    // add token t to the buffer containing the target code
    private void addTargetCode(Token t){
      tomparser.addTargetCode(t);
    }

    // returns the selector
    private TokenStreamSelector selector(){
      return tomparser.selector();
    }
    
   private TomTerm buildBqAppl(Token id, LinkedList blockList, TomTerm term, boolean composite) {
     OptionList option = tom_cons_list_concOption(tom_make_OriginTracking(tom_make_Name(id.getText()),id.getLine(),currentFile()),tom_cons_list_concOption(tom_make_ModuleName(DEFAULT_MODULE_NAME),tom_empty_list_concOption()));
     TomList target = (term==null)?
       tom_empty_list_concTomTerm():
       tom_cons_list_concTomTerm(tom_make_TargetLanguageToTomTerm(tom_make_ITL(".")),tom_cons_list_concTomTerm(term,tom_empty_list_concTomTerm()));

     if(composite) {
			 TomList list = ASTFactory.makeList(blockList);
			 return tom_make_Composite(tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(option,tom_make_Name(id.getText()),list),tom_append_list_concTomTerm(target,tom_empty_list_concTomTerm())));
     } else {
			 return tom_make_Composite(tom_cons_list_concTomTerm(tom_make_Variable(option,tom_make_Name(id.getText()),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint()),tom_append_list_concTomTerm(target,tom_empty_list_concTomTerm())));
		 }

   }
 
   /*
    * add a term to a list of term
    * when newComposite is true, this means that a ',' has been read before the term
    */
    private void addTerm(LinkedList list, TomTerm term, boolean newComposite) {
      // if the list is empty put an empty composite in it to simplify the code
      if(list.isEmpty()) {
        list.add(tom_make_Composite(tom_empty_list_concTomTerm()));
      }
      TomTerm lastElement = (TomTerm) list.getLast();
      /*
       * when newComposite is true, we add the term, eventually wrapped by a Composite 
       * otherwise, the term is inserted (eventually unwrapped) into the last Composite of the list
       */
      if(newComposite) {
        {{if (tom_is_sort_TomTerm(lastElement)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )lastElement))) {if (tom_is_sort_TomTerm(term)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )term))) {
 
            list.add((( tom.engine.adt.tomterm.types.TomTerm )term)); 
            return; 
          }}}}}{if (tom_is_sort_TomTerm(lastElement)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )lastElement))) {if (tom_is_sort_TomTerm(term)) {
 
            list.add(tom_make_Composite(tom_cons_list_concTomTerm((( tom.engine.adt.tomterm.types.TomTerm )term),tom_empty_list_concTomTerm()))); 
            return; 
          }}}}}

      } else {
        {{if (tom_is_sort_TomTerm(lastElement)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )lastElement))) {if (tom_is_sort_TomTerm(term)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )term))) {
 
            list.set(list.size()-1,tom_make_Composite(tom_append_list_concTomTerm(tom_get_slot_Composite_Args((( tom.engine.adt.tomterm.types.TomTerm )lastElement)),tom_append_list_concTomTerm(tom_get_slot_Composite_Args((( tom.engine.adt.tomterm.types.TomTerm )term)),tom_empty_list_concTomTerm())))); 
            return;
          }}}}}{if (tom_is_sort_TomTerm(lastElement)) {if (tom_is_fun_sym_Composite((( tom.engine.adt.tomterm.types.TomTerm )lastElement))) {if (tom_is_sort_TomTerm(term)) {
 
            list.set(list.size()-1,tom_make_Composite(tom_append_list_concTomTerm(tom_get_slot_Composite_Args((( tom.engine.adt.tomterm.types.TomTerm )lastElement)),tom_cons_list_concTomTerm((( tom.engine.adt.tomterm.types.TomTerm )term),tom_empty_list_concTomTerm())))); 
            return;
          }}}}}

      }
    }

    // sorts attributes of xml term with lexicographical order
    private TomList sortAttributeList(TomList list){
      {{if (tom_is_sort_TomList(list)) {if (tom_is_fun_sym_concTomTerm((( tom.engine.adt.tomterm.types.TomList )list))) {if (tom_is_empty_concTomTerm_TomList((( tom.engine.adt.tomterm.types.TomList )list))) {
 return list; }}}}{if (tom_is_sort_TomList(list)) {if (tom_is_fun_sym_concTomTerm((( tom.engine.adt.tomterm.types.TomList )list))) { tom.engine.adt.tomterm.types.TomList  tomMatch3NameNumber_end_6=(( tom.engine.adt.tomterm.types.TomList )list);do {{if (!(tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_6))) { tom.engine.adt.tomterm.types.TomTerm  tom_e1=tom_get_head_concTomTerm_TomList(tomMatch3NameNumber_end_6); tom.engine.adt.tomterm.types.TomList  tomMatch3NameNumber_freshVar_7=tom_get_tail_concTomTerm_TomList(tomMatch3NameNumber_end_6); tom.engine.adt.tomterm.types.TomList  tomMatch3NameNumber_end_10=tomMatch3NameNumber_freshVar_7;do {{if (!(tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_10))) { tom.engine.adt.tomterm.types.TomTerm  tom_e2=tom_get_head_concTomTerm_TomList(tomMatch3NameNumber_end_10);{{if (tom_is_sort_TomTerm(tom_e1)) {if (tom_is_fun_sym_BackQuoteAppl((( tom.engine.adt.tomterm.types.TomTerm )tom_e1))) { tom.engine.adt.tomterm.types.TomList  tomMatch4NameNumber_freshVar_2=tom_get_slot_BackQuoteAppl_Args((( tom.engine.adt.tomterm.types.TomTerm )tom_e1));if (tom_is_fun_sym_concTomTerm(tomMatch4NameNumber_freshVar_2)) {if (!(tom_is_empty_concTomTerm_TomList(tomMatch4NameNumber_freshVar_2))) { tom.engine.adt.tomterm.types.TomTerm  tomMatch4NameNumber_freshVar_13=tom_get_head_concTomTerm_TomList(tomMatch4NameNumber_freshVar_2);if (tom_is_fun_sym_BackQuoteAppl(tomMatch4NameNumber_freshVar_13)) { tom.engine.adt.tomname.types.TomName  tomMatch4NameNumber_freshVar_12=tom_get_slot_BackQuoteAppl_AstName(tomMatch4NameNumber_freshVar_13);if (tom_is_fun_sym_Name(tomMatch4NameNumber_freshVar_12)) {if (tom_is_sort_TomTerm(tom_e2)) {if (tom_is_fun_sym_BackQuoteAppl((( tom.engine.adt.tomterm.types.TomTerm )tom_e2))) { tom.engine.adt.tomterm.types.TomList  tomMatch4NameNumber_freshVar_4=tom_get_slot_BackQuoteAppl_Args((( tom.engine.adt.tomterm.types.TomTerm )tom_e2));if (tom_is_fun_sym_concTomTerm(tomMatch4NameNumber_freshVar_4)) {if (!(tom_is_empty_concTomTerm_TomList(tomMatch4NameNumber_freshVar_4))) { tom.engine.adt.tomterm.types.TomTerm  tomMatch4NameNumber_freshVar_17=tom_get_head_concTomTerm_TomList(tomMatch4NameNumber_freshVar_4);if (tom_is_fun_sym_BackQuoteAppl(tomMatch4NameNumber_freshVar_17)) { tom.engine.adt.tomname.types.TomName  tomMatch4NameNumber_freshVar_16=tom_get_slot_BackQuoteAppl_AstName(tomMatch4NameNumber_freshVar_17);if (tom_is_fun_sym_Name(tomMatch4NameNumber_freshVar_16)) {




              if(tom_get_slot_Name_String(tomMatch4NameNumber_freshVar_12).compareTo(tom_get_slot_Name_String(tomMatch4NameNumber_freshVar_16)) > 0) {
                return sortAttributeList(tom_append_list_concTomTerm(tom_get_slice_concTomTerm((( tom.engine.adt.tomterm.types.TomList )list),tomMatch3NameNumber_end_6,tom_empty_list_concTomTerm()),tom_cons_list_concTomTerm(tom_e2,tom_append_list_concTomTerm(tom_get_slice_concTomTerm(tomMatch3NameNumber_freshVar_7,tomMatch3NameNumber_end_10,tom_empty_list_concTomTerm()),tom_cons_list_concTomTerm(tom_e1,tom_append_list_concTomTerm(tom_get_tail_concTomTerm_TomList(tomMatch3NameNumber_end_10),tom_empty_list_concTomTerm()))))));
              }
            }}}}}}}}}}}}}}

        }if (tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_10)) {tomMatch3NameNumber_end_10=tomMatch3NameNumber_freshVar_7;} else {tomMatch3NameNumber_end_10=tom_get_tail_concTomTerm_TomList(tomMatch3NameNumber_end_10);}}} while(!(tom_equal_term_TomList(tomMatch3NameNumber_end_10, tomMatch3NameNumber_freshVar_7)));}if (tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_6)) {tomMatch3NameNumber_end_6=(( tom.engine.adt.tomterm.types.TomList )list);} else {tomMatch3NameNumber_end_6=tom_get_tail_concTomTerm_TomList(tomMatch3NameNumber_end_6);}}} while(!(tom_equal_term_TomList(tomMatch3NameNumber_end_6, (( tom.engine.adt.tomterm.types.TomList )list))));}}}}

      return list;
    }
    
    // built a sorted TomList from a LinkedList
    private TomList buildAttributeList(LinkedList list){
      return sortAttributeList(ASTFactory.makeList(list));
    }
    
    // add double quotes around a string
    private String encodeName(String name) {
      return "\"" + name + "\"";
    }


protected BackQuoteParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public BackQuoteParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected BackQuoteParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public BackQuoteParser(TokenStream lexer) {
  this(lexer,1);
}

public BackQuoteParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final TomTerm  beginBackquote() throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		
		result = null; 
		TomList context = tom_empty_list_concTomTerm();
		
		
		ws();
		{
		switch ( LA(1)) {
		case BQ_BACKQUOTE:
		{
			match(BQ_BACKQUOTE);
			break;
		}
		case BQ_ID:
		case BQ_LPAREN:
		case XML:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		result=mainBqTerm(context);
		}
		if ( inputState.guessing==0 ) {
			selector().pop();
		}
		return result;
	}
	
	public final void ws() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop33:
		do {
			if ((LA(1)==BQ_WS)) {
				match(BQ_WS);
			}
			else {
				break _loop33;
			}
			
		} while (true);
		}
	}
	
	public final TomTerm  mainBqTerm(
		TomList context
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  id = null;
		
		result = null;
		TomTerm term = null;
		TomList list = tom_empty_list_concTomTerm();
		
		Token t = null;
		LinkedList blockList = new LinkedList();
		
		
		{
		switch ( LA(1)) {
		case BQ_LPAREN:
		case XML:
		{
			result=basicTerm(list);
			break;
		}
		case BQ_ID:
		{
			id = LT(1);
			match(BQ_ID);
			{
			if (((LA(1)==BQ_STAR))&&(LA(1) == BQ_STAR)) {
				match(BQ_STAR);
				if ( inputState.guessing==0 ) {
					
					String name = id.getText();
					Option ot = tom_make_OriginTracking(tom_make_Name(name),id.getLine(),currentFile());
					result = tom_make_VariableStar(tom_cons_list_concOption(ot,tom_empty_list_concOption()),tom_make_Name(name),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint());  
					
				}
			}
			else if (((LA(1)==EOF))&&(LA(1) == BQ_RBRACE)) {
				if ( inputState.guessing==0 ) {
					
					// generate an ERROR when a '}' is encoutered
					//System.out.println("ERROR");
					
				}
			}
			else if ((_tokenSet_0.member(LA(1)))) {
				ws();
				{
				if (((LA(1)==BQ_LPAREN))&&(LA(1) == BQ_LPAREN)) {
					match(BQ_LPAREN);
					ws();
					{
					switch ( LA(1)) {
					case BQ_ID:
					case BQ_STAR:
					case BQ_LPAREN:
					case BQ_DOT:
					case XML:
					case BQ_STRING:
					case BQ_WS:
					case BQ_INTEGER:
					case BQ_MINUS:
					case DOUBLE_QUOTE:
					case XML_START:
					case XML_EQUAL:
					case XML_CLOSE:
					case ANY:
					case XML_TEXT:
					{
						termList(blockList,list);
						break;
					}
					case BQ_RPAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(BQ_RPAREN);
					if ( inputState.guessing==0 ) {
						
						result = buildBqAppl(id,blockList,term,true);
						
					}
				}
				else if ((_tokenSet_1.member(LA(1)))) {
					t=targetCode();
					if ( inputState.guessing==0 ) {
						
						//System.out.println("targetCode = " + t);
						addTargetCode(t);
						String name = id.getText();
						OptionList ol = tom_cons_list_concOption(tom_make_OriginTracking(tom_make_Name(name),id.getLine(),currentFile()),tom_cons_list_concOption(tom_make_ModuleName(DEFAULT_MODULE_NAME),tom_empty_list_concOption()));
						//result = `BackQuoteAppl(ol,Name(name),concTomTerm());
						result = tom_make_Variable(ol,tom_make_Name(name),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint());
						
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomTerm  basicTerm(
		TomList context
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		
		result = null;
		TomTerm term = null;
		TomList localContext = tom_empty_list_concTomTerm();
		
		LinkedList blockList = new LinkedList();
		
		
		{
		switch ( LA(1)) {
		case XML:
		{
			match(XML);
			ws();
			match(BQ_LPAREN);
			ws();
			{
			_loop23:
			do {
				boolean synPredMatched22 = false;
				if (((_tokenSet_2.member(LA(1))))) {
					int _m22 = mark();
					synPredMatched22 = true;
					inputState.guessing++;
					try {
						{
						bqTerm(null);
						match(BQ_COMMA);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched22 = false;
					}
					rewind(_m22);
inputState.guessing--;
				}
				if ( synPredMatched22 ) {
					term=bqTerm(context);
					match(BQ_COMMA);
					ws();
					if ( inputState.guessing==0 ) {
						blockList.add(term);
					}
				}
				else {
					break _loop23;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				localContext = ASTFactory.makeList(blockList);
			}
			result=bqTerm(localContext);
			match(BQ_RPAREN);
			break;
		}
		case BQ_LPAREN:
		{
			match(BQ_LPAREN);
			ws();
			{
			switch ( LA(1)) {
			case BQ_ID:
			case BQ_STAR:
			case BQ_LPAREN:
			case BQ_DOT:
			case XML:
			case BQ_STRING:
			case BQ_WS:
			case BQ_INTEGER:
			case BQ_MINUS:
			case DOUBLE_QUOTE:
			case XML_START:
			case XML_EQUAL:
			case XML_CLOSE:
			case ANY:
			case XML_TEXT:
			{
				termList(blockList,context);
				break;
			}
			case BQ_RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(BQ_RPAREN);
			if ( inputState.guessing==0 ) {
				
				TomList compositeList = ASTFactory.makeList(blockList);
				result = tom_make_Composite(tom_cons_list_concTomTerm(tom_make_TargetLanguageToTomTerm(tom_make_ITL("(")),tom_append_list_concTomTerm(compositeList,tom_cons_list_concTomTerm(tom_make_TargetLanguageToTomTerm(tom_make_ITL(")"))
				,tom_empty_list_concTomTerm())))
				)
				
				
				
				
				;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final void termList(
		LinkedList list,TomList context
	) throws RecognitionException, TokenStreamException {
		
		Token  c = null;
		
		TomTerm term = null;
		
		
		term=bqTerm(context);
		if ( inputState.guessing==0 ) {
			addTerm(list,term,false);
		}
		{
		_loop28:
		do {
			if ((_tokenSet_3.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case BQ_COMMA:
				{
					c = LT(1);
					match(BQ_COMMA);
					ws();
					break;
				}
				case BQ_ID:
				case BQ_STAR:
				case BQ_LPAREN:
				case BQ_DOT:
				case XML:
				case BQ_STRING:
				case BQ_WS:
				case BQ_INTEGER:
				case BQ_MINUS:
				case DOUBLE_QUOTE:
				case XML_START:
				case XML_EQUAL:
				case XML_CLOSE:
				case ANY:
				case XML_TEXT:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				term=bqTerm(context);
				if ( inputState.guessing==0 ) {
					addTerm(list,term, (c!=null)); c = null;
				}
			}
			else {
				break _loop28;
			}
			
		} while (true);
		}
	}
	
	public final Token  targetCode() throws RecognitionException, TokenStreamException {
		Token result;
		
		Token  c = null;
		Token  i = null;
		Token  r = null;
		Token  t = null;
		Token  xcs = null;
		Token  xt = null;
		Token  xc = null;
		Token  xp = null;
		
		result = null;
		
		
		switch ( LA(1)) {
		case BQ_STAR:
		case BQ_DOT:
		case BQ_STRING:
		case BQ_WS:
		case BQ_INTEGER:
		case BQ_MINUS:
		case DOUBLE_QUOTE:
		case XML_START:
		case XML_EQUAL:
		case XML_CLOSE:
		case ANY:
		{
			result=target();
			break;
		}
		case BQ_COMMA:
		{
			c = LT(1);
			match(BQ_COMMA);
			if ( inputState.guessing==0 ) {
				result = c;
			}
			break;
		}
		case BQ_ID:
		{
			i = LT(1);
			match(BQ_ID);
			if ( inputState.guessing==0 ) {
				result = i;
			}
			break;
		}
		case BQ_RPAREN:
		{
			r = LT(1);
			match(BQ_RPAREN);
			if ( inputState.guessing==0 ) {
				result = r;
			}
			break;
		}
		case XML_START_ENDING:
		{
			t = LT(1);
			match(XML_START_ENDING);
			if ( inputState.guessing==0 ) {
				result = t;
			}
			break;
		}
		case XML_CLOSE_SINGLETON:
		{
			xcs = LT(1);
			match(XML_CLOSE_SINGLETON);
			if ( inputState.guessing==0 ) {
				result = xcs;
			}
			break;
		}
		case XML_TEXT:
		{
			xt = LT(1);
			match(XML_TEXT);
			if ( inputState.guessing==0 ) {
				result = xt;
			}
			break;
		}
		case XML_COMMENT:
		{
			xc = LT(1);
			match(XML_COMMENT);
			if ( inputState.guessing==0 ) {
				result = xc;
			}
			break;
		}
		case XML_PROC:
		{
			xp = LT(1);
			match(XML_PROC);
			if ( inputState.guessing==0 ) {
				result = xp;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return result;
	}
	
	public final TomTerm  bqTerm(
		TomList context
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  id = null;
		
		result = null;
		TomTerm term = null;
		TomList xmlTermList = tom_empty_list_concTomTerm();
		
		Token t = null;
		LinkedList blockList = new LinkedList();
		boolean arguments = false;
		
		
		switch ( LA(1)) {
		case BQ_LPAREN:
		case XML:
		{
			result=basicTerm(context);
			break;
		}
		case BQ_ID:
		{
			id = LT(1);
			match(BQ_ID);
			{
			if (((LA(1)==BQ_STAR))&&(LA(1) == BQ_STAR)) {
				match(BQ_STAR);
				if ( inputState.guessing==0 ) {
					
					String name = id.getText();
					Option ot = tom_make_OriginTracking(tom_make_Name(name),id.getLine(),currentFile());
					result = tom_make_VariableStar(tom_cons_list_concOption(ot,tom_empty_list_concOption()),tom_make_Name(name),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint());      
					
				}
			}
			else if ((_tokenSet_4.member(LA(1)))) {
				ws();
				{
				if (((LA(1)==BQ_LPAREN))&&(LA(1) == BQ_LPAREN)) {
					match(BQ_LPAREN);
					if ( inputState.guessing==0 ) {
						arguments = true;
					}
					ws();
					{
					switch ( LA(1)) {
					case BQ_ID:
					case BQ_STAR:
					case BQ_LPAREN:
					case BQ_DOT:
					case XML:
					case BQ_STRING:
					case BQ_WS:
					case BQ_INTEGER:
					case BQ_MINUS:
					case DOUBLE_QUOTE:
					case XML_START:
					case XML_EQUAL:
					case XML_CLOSE:
					case ANY:
					case XML_TEXT:
					{
						termList(blockList,context);
						break;
					}
					case BQ_RPAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(BQ_RPAREN);
				}
				else if ((_tokenSet_4.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				boolean synPredMatched15 = false;
				if (((LA(1)==BQ_DOT))) {
					int _m15 = mark();
					synPredMatched15 = true;
					inputState.guessing++;
					try {
						{
						match(BQ_DOT);
						term=bqTerm(null);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched15 = false;
					}
					rewind(_m15);
inputState.guessing--;
				}
				if ( synPredMatched15 ) {
					match(BQ_DOT);
					term=bqTerm(context);
				}
				else if ((_tokenSet_4.member(LA(1)))) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				if ( inputState.guessing==0 ) {
					
					result = buildBqAppl(id,blockList,term,arguments);
					
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			break;
		}
		default:
			boolean synPredMatched17 = false;
			if (((LA(1)==XML_START||LA(1)==XML_TEXT))) {
				int _m17 = mark();
				synPredMatched17 = true;
				inputState.guessing++;
				try {
					{
					xmlTerm(null);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched17 = false;
				}
				rewind(_m17);
inputState.guessing--;
			}
			if ( synPredMatched17 ) {
				result=xmlTerm(context);
			}
			else if ((_tokenSet_5.member(LA(1)))) {
				t=target();
				if ( inputState.guessing==0 ) {
					
					//System.out.println("target = " + t);
					result = tom_make_TargetLanguageToTomTerm(tom_make_ITL(t.getText()));
					
				}
			}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return result;
	}
	
	public final TomTerm  xmlTerm(
		TomList context
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  id = null;
		
		result = null;
		TomList attributeTomList = tom_empty_list_concTomTerm();
		TomList childrenTomList = tom_empty_list_concTomTerm();
		TomTerm term = null;
		
		LinkedList attributes = new LinkedList();
		LinkedList children = new LinkedList();
		
		
		{
		switch ( LA(1)) {
		case XML_START:
		{
			match(XML_START);
			ws();
			id = LT(1);
			match(BQ_ID);
			ws();
			xmlAttributeList(attributes,context);
			if ( inputState.guessing==0 ) {
				
				attributeTomList = buildAttributeList(attributes);
				
			}
			{
			switch ( LA(1)) {
			case XML_CLOSE_SINGLETON:
			{
				match(XML_CLOSE_SINGLETON);
				ws();
				break;
			}
			case XML_CLOSE:
			{
				match(XML_CLOSE);
				ws();
				xmlChildren(children,context);
				if ( inputState.guessing==0 ) {
					
					childrenTomList = ASTFactory.makeList(children);
					
				}
				match(XML_START_ENDING);
				ws();
				match(BQ_ID);
				ws();
				match(XML_CLOSE);
				ws();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
				TomList args = tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_Constant(),tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption())),tom_make_Name(encodeName(id.getText())),tom_empty_list_concTomTerm()
				),tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.CONC_TNODE),attributeTomList),tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.CONC_TNODE),childrenTomList)
				,tom_empty_list_concTomTerm())))
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				;
				
				if(context == null){
				result = tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.ELEMENT_NODE),args)
				
				
				
				;
				} else {
															result = tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.ELEMENT_NODE),tom_append_list_concTomTerm(context,tom_append_list_concTomTerm(args,tom_empty_list_concTomTerm()))
				)
				
				
				
				
				
				;
				}
				
				
			}
			break;
		}
		case XML_TEXT:
		{
			match(XML_TEXT);
			match(BQ_LPAREN);
			term=bqTerm(context);
			match(BQ_RPAREN);
			if ( inputState.guessing==0 ) {
				
				result = tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.TEXT_NODE),tom_append_list_concTomTerm(context,tom_cons_list_concTomTerm(term,tom_empty_list_concTomTerm()))
				)
				
				
				
				
				
				
				;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final Token  target() throws RecognitionException, TokenStreamException {
		Token result;
		
		Token  in = null;
		Token  str = null;
		Token  m = null;
		Token  s = null;
		Token  w = null;
		Token  d = null;
		Token  dq = null;
		Token  xs = null;
		Token  xe = null;
		Token  xc = null;
		Token  a = null;
		
		result = null;
		
		
		switch ( LA(1)) {
		case BQ_INTEGER:
		{
			in = LT(1);
			match(BQ_INTEGER);
			if ( inputState.guessing==0 ) {
				result = in;
			}
			break;
		}
		case BQ_STRING:
		{
			str = LT(1);
			match(BQ_STRING);
			if ( inputState.guessing==0 ) {
				result = str;
			}
			break;
		}
		case BQ_MINUS:
		{
			m = LT(1);
			match(BQ_MINUS);
			if ( inputState.guessing==0 ) {
				result = m;
			}
			break;
		}
		case BQ_STAR:
		{
			s = LT(1);
			match(BQ_STAR);
			if ( inputState.guessing==0 ) {
				result = s;
			}
			break;
		}
		case BQ_WS:
		{
			w = LT(1);
			match(BQ_WS);
			if ( inputState.guessing==0 ) {
				result = w;
			}
			break;
		}
		case BQ_DOT:
		{
			d = LT(1);
			match(BQ_DOT);
			if ( inputState.guessing==0 ) {
				result = d;
			}
			break;
		}
		case DOUBLE_QUOTE:
		{
			dq = LT(1);
			match(DOUBLE_QUOTE);
			if ( inputState.guessing==0 ) {
				result = dq;
			}
			break;
		}
		case XML_START:
		{
			xs = LT(1);
			match(XML_START);
			if ( inputState.guessing==0 ) {
				result = xs;
			}
			break;
		}
		case XML_EQUAL:
		{
			xe = LT(1);
			match(XML_EQUAL);
			if ( inputState.guessing==0 ) {
				result = xe;
			}
			break;
		}
		case XML_CLOSE:
		{
			xc = LT(1);
			match(XML_CLOSE);
			if ( inputState.guessing==0 ) {
				result = xc;
			}
			break;
		}
		case ANY:
		{
			a = LT(1);
			match(ANY);
			if ( inputState.guessing==0 ) {
				result = a;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		return result;
	}
	
	public final TomTerm  xmlAttributeStringOrVariable() throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  id = null;
		Token  string = null;
		result = null;
		
		{
		switch ( LA(1)) {
		case BQ_ID:
		{
			id = LT(1);
			match(BQ_ID);
			if ( inputState.guessing==0 ) {
				
				String name = id.getText();
				OptionList ol = tom_cons_list_concOption(tom_make_OriginTracking(tom_make_Name(name),id.getLine(),currentFile()),tom_cons_list_concOption(tom_make_ModuleName(DEFAULT_MODULE_NAME),tom_empty_list_concOption()));
				result = tom_make_Variable(ol,tom_make_Name(name),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint());
						   //result = `TargetLanguageToTomTerm(ITL(id.getText())); 
						
			}
			break;
		}
		case BQ_STRING:
		{
			string = LT(1);
			match(BQ_STRING);
			if ( inputState.guessing==0 ) {
				
						   result = tom_make_TargetLanguageToTomTerm(tom_make_ITL(string.getText()));
						
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomTerm  xmlAttribute(
		TomList context
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  id = null;
		
		result = null;
		TomTerm value = null;
		
		
		{
		id = LT(1);
		match(BQ_ID);
		{
		switch ( LA(1)) {
		case BQ_WS:
		case XML_EQUAL:
		{
			ws();
			match(XML_EQUAL);
			ws();
			value=xmlAttributeStringOrVariable();
			if ( inputState.guessing==0 ) {
				
				TomList args = tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_Constant(),tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption())),tom_make_Name(encodeName(id.getText())),tom_empty_list_concTomTerm()
				),tom_cons_list_concTomTerm(tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_Constant(),tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption())),tom_make_Name("\"true\""),tom_empty_list_concTomTerm()
				),tom_cons_list_concTomTerm(value,tom_empty_list_concTomTerm())))
				
				
				
				
				
				
				
				
				
				
				
				;
						    if(context != null) {
						    args = tom_append_list_concTomTerm(context,tom_append_list_concTomTerm(args,tom_empty_list_concTomTerm()));
						    }
						    result = tom_make_BackQuoteAppl(tom_cons_list_concOption(tom_make_ModuleName(TNODE_MODULE_NAME),tom_empty_list_concOption()),tom_make_Name(Constants.ATTRIBUTE_NODE),args)
				
				
				;
				
			}
			break;
		}
		case BQ_STAR:
		{
			match(BQ_STAR);
			if ( inputState.guessing==0 ) {
				
						result = tom_make_VariableStar(tom_empty_list_concOption(),tom_make_Name(id.getText()),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint())
				
				
				
				;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		}
		return result;
	}
	
	public final void xmlAttributeList(
		LinkedList attributeList, TomList context
	) throws RecognitionException, TokenStreamException {
		
		
		TomTerm term = null;
		
		
		{
		_loop41:
		do {
			if ((LA(1)==BQ_ID)) {
				term=xmlAttribute(context);
				ws();
				if ( inputState.guessing==0 ) {
					
					attributeList.add(term);
					
				}
			}
			else {
				break _loop41;
			}
			
		} while (true);
		}
	}
	
	public final void xmlChildren(
		LinkedList children, TomList context
	) throws RecognitionException, TokenStreamException {
		
		
		TomTerm term = null;
		
		
		{
		_loop44:
		do {
			if (((_tokenSet_2.member(LA(1))))&&(LA(1) != XML_START_ENDING && LA(1) != XML_CLOSE)) {
				term=bqTerm(context);
				if ( inputState.guessing==0 ) {
					children.add(term);
				}
			}
			else {
				break _loop44;
			}
			
		} while (true);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"BQ_BACKQUOTE",
		"BQ_ID",
		"BQ_STAR",
		"BQ_LPAREN",
		"BQ_RPAREN",
		"BQ_DOT",
		"\"xml\"",
		"BQ_COMMA",
		"BQ_STRING",
		"BQ_WS",
		"BQ_INTEGER",
		"BQ_MINUS",
		"DOUBLE_QUOTE",
		"XML_START",
		"XML_EQUAL",
		"XML_CLOSE",
		"ANY",
		"XML_START_ENDING",
		"XML_CLOSE_SINGLETON",
		"XML_TEXT",
		"XML_COMMENT",
		"XML_PROC",
		"BQ_RBRACE",
		"XML_SKIP",
		"BQ_SIMPLE_ID",
		"BQ_MINUS_ID",
		"BQ_MINUS_ID_PART",
		"BQ_DIGIT",
		"BQ_UNDERSCORE",
		"BQ_ESC",
		"BQ_HEX_DIGIT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 67107808L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 67107680L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 10483424L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 10485472L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 12582880L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 2093632L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}
