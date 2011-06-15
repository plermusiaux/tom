/*
* 
* TOM - To One Matching Compiler
* 
* Copyright (c) 2000-2011, INPL, INRIA
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

package tom.engine.desugarer;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.tomtype.types.tomtypelist.concTomType;
import tom.engine.adt.tomterm.types.tomlist.concTomTerm;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.tools.ASTFactory;
import tom.engine.tools.TomGenericPlugin;
import tom.engine.tools.Tools;
import tom.engine.tools.SymbolTable;
import tom.engine.xml.Constants;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;

import tom.library.sl.*;

/**
* The Desugarer plugin.
* Perform syntax expansion and more.
*
* replaces  _  by a fresh variable _* by a fresh varstar 
* replaces 'TermAppl' by its 'RecordAppl' form
* replaces 'XMLAppl' by its 'RecordAppl' form
*   when no slotName exits, the position becomes the slotName
*/
public class DesugarerPlugin extends TomGenericPlugin {



  private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {
    if( l1.isEmptyconcTomTerm() ) {
      return l2;
    } else if( l2.isEmptyconcTomTerm() ) {
      return l1;
    } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {
      return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;
    } else {
      return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end== tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.code.types.BQTermList  tom_append_list_concBQTerm( tom.engine.adt.code.types.BQTermList l1,  tom.engine.adt.code.types.BQTermList  l2) {
    if( l1.isEmptyconcBQTerm() ) {
      return l2;
    } else if( l2.isEmptyconcBQTerm() ) {
      return l1;
    } else if(  l1.getTailconcBQTerm() .isEmptyconcBQTerm() ) {
      return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,l2) ;
    } else {
      return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,tom_append_list_concBQTerm( l1.getTailconcBQTerm() ,l2)) ;
    }
  }
  private static   tom.engine.adt.code.types.BQTermList  tom_get_slice_concBQTerm( tom.engine.adt.code.types.BQTermList  begin,  tom.engine.adt.code.types.BQTermList  end, tom.engine.adt.code.types.BQTermList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcBQTerm()  ||  (end== tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( begin.getHeadconcBQTerm() ,( tom.engine.adt.code.types.BQTermList )tom_get_slice_concBQTerm( begin.getTailconcBQTerm() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.code.types.BQTerm  tom_append_list_Composite( tom.engine.adt.code.types.BQTerm l1,  tom.engine.adt.code.types.BQTerm  l2) {
    if( l1.isEmptyComposite() ) {
      return l2;
    } else if( l2.isEmptyComposite() ) {
      return l1;
    } else if(  l1.getTailComposite() .isEmptyComposite() ) {
      return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,l2) ;
    } else {
      return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,tom_append_list_Composite( l1.getTailComposite() ,l2)) ;
    }
  }
  private static   tom.engine.adt.code.types.BQTerm  tom_get_slice_Composite( tom.engine.adt.code.types.BQTerm  begin,  tom.engine.adt.code.types.BQTerm  end, tom.engine.adt.code.types.BQTerm  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyComposite()  ||  (end== tom.engine.adt.code.types.bqterm.EmptyComposite.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.code.types.bqterm.ConsComposite.make( begin.getHeadComposite() ,( tom.engine.adt.code.types.BQTerm )tom_get_slice_Composite( begin.getTailComposite() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {
    if( l1.isEmptyconcTomName() ) {
      return l2;
    } else if( l2.isEmptyconcTomName() ) {
      return l1;
    } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {
      return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;
    } else {
      return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomslot.types.SlotList  tom_append_list_concSlot( tom.engine.adt.tomslot.types.SlotList l1,  tom.engine.adt.tomslot.types.SlotList  l2) {
    if( l1.isEmptyconcSlot() ) {
      return l2;
    } else if( l2.isEmptyconcSlot() ) {
      return l1;
    } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {
      return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;
    } else {
      return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomslot.types.SlotList  tom_get_slice_concSlot( tom.engine.adt.tomslot.types.SlotList  begin,  tom.engine.adt.tomslot.types.SlotList  end, tom.engine.adt.tomslot.types.SlotList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcSlot()  ||  (end== tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.engine.adt.tomslot.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {
    if( l1.isEmptyconcOption() ) {
      return l2;
    } else if( l2.isEmptyconcOption() ) {
      return l1;
    } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {
      return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;
    } else {
      return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {
    if( l1.isEmptyconcConstraint() ) {
      return l2;
    } else if( l2.isEmptyconcConstraint() ) {
      return l1;
    } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {
      return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;
    } else {
      return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.Sequence )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Sequence.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.Choice )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Choice.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.SequenceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.SequenceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.ChoiceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.ChoiceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;
  }
  private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { 
return ( 
 tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) )

;
}
private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_TopDownIdStopOnSuccess( tom.library.sl.Strategy  v) { 
return (
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) ) )) 

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}


private static Logger logger = Logger.getLogger("tom.engine.desugarer.DesugarerPlugin");
private SymbolTable symbolTable;
private int freshCounter = 0;

// FIXME : generate truly fresh variables
private TomName getFreshVariable() {
freshCounter++;
return 
 tom.engine.adt.tomname.types.tomname.Name.make("_f_r_e_s_h_v_a_r_" + freshCounter) ;
}

public SymbolTable getSymbolTable() {
return this.symbolTable;
} 

public void setSymbolTable(SymbolTable symbolTable) {
this.symbolTable = symbolTable;
}

public DesugarerPlugin() {
super("DesugarerPlugin");
}

public void run(Map informationTracker) {
long startChrono = System.currentTimeMillis();
try {
setSymbolTable(getStreamManager().getSymbolTable());
updateSymbolTable();
Code code = (Code) getWorkingTerm();

// replace underscores by fresh variables
code = 
tom_make_TopDown(tom_make_DesugarUnderscore(this)).visitLight(code);

// replace TermAppl and XmlAppl by RecordAppl
code = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceXMLApplTomSyntax(this)).visitLight(code);
code = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceTermApplTomSyntax(this)).visitLight(code);
// replace BQRecordAppl by BQTermAppl
code = 
tom_make_TopDown(tom_make_replaceBQRecordApplTomSyntax(this)).visitLight(code);

setWorkingTerm(code);      

// verbose
TomMessage.info(logger,null,0,TomMessage.tomDesugaringPhase,
Integer.valueOf((int)(System.currentTimeMillis()-startChrono)));
} catch (Exception e) {
TomMessage.error(logger,
getStreamManager().getInputFileName(), 0,
TomMessage.exceptionMessage, e.getMessage());
e.printStackTrace();
return;
}
}

/* 
* replaces  _  by a fresh variable _* by a fresh varstar    
*/

public static class DesugarUnderscore extends tom.library.sl.AbstractStrategyBasic {
private  tom.engine.desugarer.DesugarerPlugin  desugarer;
public DesugarUnderscore( tom.engine.desugarer.DesugarerPlugin  desugarer) {
super(( new tom.library.sl.Identity() ));
this.desugarer=desugarer;
}
public  tom.engine.desugarer.DesugarerPlugin  getdesugarer() {
return desugarer;
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];
stratChilds[0] = super.getChildAt(0);
return stratChilds;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
if ( ( (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstName()  instanceof tom.engine.adt.tomname.types.tomname.EmptyName) ) {

return 
 tom.engine.adt.tomterm.types.tomterm.Variable.make( (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getOptions() , desugarer.getFreshVariable(),  (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstType() ,  (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ) ;


}
}
}

}
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
if ( ( (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstName()  instanceof tom.engine.adt.tomname.types.tomname.EmptyName) ) {

return 
 tom.engine.adt.tomterm.types.tomterm.VariableStar.make( (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getOptions() , desugarer.getFreshVariable(),  (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAstType() ,  (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ) ;


}
}
}

}


}
return _visit_TomTerm(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_DesugarUnderscore( tom.engine.desugarer.DesugarerPlugin  t0) { 
return new DesugarUnderscore(t0);
}


/**
* updateSymbol is called before a first syntax expansion phase
* this phase updates the symbolTable 
* this is performed by recursively traversing each symbol
* - default IsFsymDecl and MakeDecl are added
* - TermAppl are transformed into RecordAppl
* - BQRecordAppl are transformed into BQAppl
*/
public void updateSymbolTable() {
for(String tomName:getSymbolTable().keySymbolIterable()) {
TomSymbol tomSymbol = getSymbolFromName(tomName);
/*
* add default IsFsymDecl unless it is a builtin type
* add default IsFsymDecl and MakeDecl unless:
*  - it is a builtin type
*  - another option (if_sfsym, get_slot, etc) is already defined for this operator
*/
if(!getSymbolTable().isBuiltinType(TomBase.getTomType(TomBase.getSymbolCodomain(tomSymbol)))) {
tomSymbol = addDefaultMake(tomSymbol);
tomSymbol = addDefaultIsFsym(tomSymbol);
}
try {
tomSymbol = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceTermApplTomSyntax(this)).visitLight(tomSymbol);
tomSymbol = 
tom_make_TopDown(tom_make_replaceBQRecordApplTomSyntax(this)).visitLight(tomSymbol);
} catch(tom.library.sl.VisitFailure e) {
System.out.println("should not be there");
}
//System.out.println("symbol = " + tomSymbol);
getSymbolTable().putSymbol(tomName,tomSymbol);
}
}

private TomSymbol addDefaultIsFsym(TomSymbol tomSymbol) {

{
{
if ( (tomSymbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch227_1= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getOptions() ;
if ( ((tomMatch227_1 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch227_1 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch227__end__6=tomMatch227_1;
do {
{
if (!( tomMatch227__end__6.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch227_10= tomMatch227__end__6.getHeadconcOption() ;
if ( (tomMatch227_10 instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( tomMatch227_10.getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl) ) {

return tomSymbol;

}
}
}
if ( tomMatch227__end__6.isEmptyconcOption() ) {
tomMatch227__end__6=tomMatch227_1;
} else {
tomMatch227__end__6= tomMatch227__end__6.getTailconcOption() ;
}

}
} while(!( (tomMatch227__end__6==tomMatch227_1) ));
}
}
}

}
{
if ( (tomSymbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomtype.types.TomType  tomMatch227_14= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getTypesToType() ;
 tom.engine.adt.tomoption.types.OptionList  tomMatch227_16= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getOptions() ;
 tom.engine.adt.tomname.types.TomName  tom_name= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getAstName() ;
if ( (tomMatch227_14 instanceof tom.engine.adt.tomtype.types.tomtype.TypesToType) ) {
if ( ((tomMatch227_16 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch227_16 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch227__end__24=tomMatch227_16;
do {
{
if (!( tomMatch227__end__24.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch227_30= tomMatch227__end__24.getHeadconcOption() ;
if ( (tomMatch227_30 instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {
 int  tom_line= tomMatch227_30.getLine() ;
 String  tom_file= tomMatch227_30.getFileName() ;

Declaration isfsym = 
 tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl.make(tom_name,  tom.engine.adt.code.types.bqterm.BQVariable.make( tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.OriginTracking.make( tom.engine.adt.tomname.types.tomname.Name.make("t") , tom_line, tom_file) , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) ,  tom.engine.adt.tomname.types.tomname.Name.make("t") ,  tomMatch227_14.getCodomain() ) ,  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ,  tom.engine.adt.tomoption.types.option.OriginTracking.make( tom.engine.adt.tomname.types.tomname.Name.make("is_fsym") , tom_line, tom_file) ) ;
return 
 tom.engine.adt.tomsignature.types.tomsymbol.Symbol.make(tom_name, tomMatch227_14,  (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getPairNameDeclList() , tom_append_list_concOption(tom_get_slice_concOption(tomMatch227_16,tomMatch227__end__24, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ), tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tomMatch227__end__24.getHeadconcOption() , tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.DeclarationToOption.make(isfsym) ,tom_append_list_concOption( tomMatch227__end__24.getTailconcOption() , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ) )) ;


}
}
if ( tomMatch227__end__24.isEmptyconcOption() ) {
tomMatch227__end__24=tomMatch227_16;
} else {
tomMatch227__end__24= tomMatch227__end__24.getTailconcOption() ;
}

}
} while(!( (tomMatch227__end__24==tomMatch227_16) ));
}
}
}
}

}


}

return tomSymbol;
}

private TomSymbol addDefaultMake(TomSymbol tomSymbol) {

{
{
if ( (tomSymbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch228_1= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getOptions() ;
if ( ((tomMatch228_1 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch228_1 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch228__end__6=tomMatch228_1;
do {
{
if (!( tomMatch228__end__6.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch228_10= tomMatch228__end__6.getHeadconcOption() ;
if ( (tomMatch228_10 instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
 tom.engine.adt.tomdeclaration.types.Declaration  tomMatch228_9= tomMatch228_10.getAstDeclaration() ;
boolean tomMatch228_12= false ;
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyList) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyArray) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeAddList) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeAddArray) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetImplementationDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetSlotDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetDefaultDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetHeadDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetTailDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.IsEmptyDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetElementDecl) ) {
tomMatch228_12= true ;
} else {
if ( (tomMatch228_9 instanceof tom.engine.adt.tomdeclaration.types.declaration.GetSizeDecl) ) {
tomMatch228_12= true ;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
if (tomMatch228_12) {

return tomSymbol;

}

}
}
if ( tomMatch228__end__6.isEmptyconcOption() ) {
tomMatch228__end__6=tomMatch228_1;
} else {
tomMatch228__end__6= tomMatch228__end__6.getTailconcOption() ;
}

}
} while(!( (tomMatch228__end__6==tomMatch228_1) ));
}
}
}

}
{
if ( (tomSymbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomtype.types.TomType  tomMatch228_15= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getTypesToType() ;
 tom.engine.adt.tomoption.types.OptionList  tomMatch228_17= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getOptions() ;
 tom.engine.adt.tomname.types.TomName  tom_name= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getAstName() ;
if ( (tomMatch228_15 instanceof tom.engine.adt.tomtype.types.tomtype.TypesToType) ) {
 tom.engine.adt.tomtype.types.TomType  tom_codomain= tomMatch228_15.getCodomain() ;
if ( ((tomMatch228_17 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch228_17 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch228__end__25=tomMatch228_17;
do {
{
if (!( tomMatch228__end__25.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch228_31= tomMatch228__end__25.getHeadconcOption() ;
if ( (tomMatch228_31 instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {

//build variables for make
BQTermList argsAST = 
 tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ;
int index = 0;
for(TomType subtermType:(concTomType)
 tomMatch228_15.getDomain() ) {
BQTerm variable = 
 tom.engine.adt.code.types.bqterm.BQVariable.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ,  tom.engine.adt.tomname.types.tomname.Name.make("t"+index) , subtermType) ;
argsAST = 
tom_append_list_concBQTerm(argsAST, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(variable, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) );
index++;
}
BQTerm functionCall = 
 tom.engine.adt.code.types.bqterm.FunctionCall.make(tom_name, tom_codomain, argsAST) ;
Declaration make = 
 tom.engine.adt.tomdeclaration.types.declaration.MakeDecl.make(tom_name, tom_codomain, argsAST,  tom.engine.adt.tominstruction.types.instruction.BQTermToInstruction.make(functionCall) ,  tom.engine.adt.tomoption.types.option.OriginTracking.make( tom.engine.adt.tomname.types.tomname.Name.make("make") ,  tomMatch228_31.getLine() ,  tomMatch228_31.getFileName() ) ) ;
return 
 tom.engine.adt.tomsignature.types.tomsymbol.Symbol.make(tom_name, tomMatch228_15,  (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getPairNameDeclList() , tom_append_list_concOption(tom_get_slice_concOption(tomMatch228_17,tomMatch228__end__25, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ), tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tomMatch228__end__25.getHeadconcOption() , tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.DeclarationToOption.make(make) ,tom_append_list_concOption( tomMatch228__end__25.getTailconcOption() , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ) )) ;


}
}
if ( tomMatch228__end__25.isEmptyconcOption() ) {
tomMatch228__end__25=tomMatch228_17;
} else {
tomMatch228__end__25= tomMatch228__end__25.getTailconcOption() ;
}

}
} while(!( (tomMatch228__end__25==tomMatch228_17) ));
}
}
}
}

}


}

return tomSymbol;
}

/**
* The 'replaceTermApplTomSyntax' phase replaces:
* - each 'TermAppl' by its typed record form:
*    placeholders are not removed
*    slotName are attached to arguments
*/

public static class replaceTermApplTomSyntax extends tom.library.sl.AbstractStrategyBasic {
private  tom.engine.desugarer.DesugarerPlugin  desugarer;
public replaceTermApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  desugarer) {
super(( new tom.library.sl.Identity() ));
this.desugarer=desugarer;
}
public  tom.engine.desugarer.DesugarerPlugin  getdesugarer() {
return desugarer;
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];
stratChilds[0] = super.getChildAt(0);
return stratChilds;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {

return desugarer.replaceTermAppl(
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getOptions() ,
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getNameList() ,
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getArgs() ,
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() );


}
}

}

}
return _visit_TomTerm(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_replaceTermApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  t0) { 
return new replaceTermApplTomSyntax(t0);
}


/**
* The 'replaceXMLApplTomSyntax' phase replaces:
* - each 'XMLAppl' by its typed record form:
*/

public static class replaceXMLApplTomSyntax extends tom.library.sl.AbstractStrategyBasic {
private  tom.engine.desugarer.DesugarerPlugin  desugarer;
public replaceXMLApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  desugarer) {
super(( new tom.library.sl.Identity() ));
this.desugarer=desugarer;
}
public  tom.engine.desugarer.DesugarerPlugin  getdesugarer() {
return desugarer;
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];
stratChilds[0] = super.getChildAt(0);
return stratChilds;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.XMLAppl) ) {

//System.out.println("replaceXML in:\n" + `subject);
return desugarer.replaceXMLAppl(
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getOptions() , 
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getNameList() , 
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getAttrList() , 
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getChildList() ,
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() );


}
}

}

}
return _visit_TomTerm(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_replaceXMLApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  t0) { 
return new replaceXMLApplTomSyntax(t0);
}


/**
* The 'replaceBQRecordApplTomSyntax' phase replaces:
* - each 'BQRecordAppl' by its BQTermAppl form:
*    BQDefault are added
*/

public static class replaceBQRecordApplTomSyntax extends tom.library.sl.AbstractStrategyBasic {
private  tom.engine.desugarer.DesugarerPlugin  desugarer;
public replaceBQRecordApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  desugarer) {
super(( new tom.library.sl.Identity() ));
this.desugarer=desugarer;
}
public  tom.engine.desugarer.DesugarerPlugin  getdesugarer() {
return desugarer;
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];
stratChilds[0] = super.getChildAt(0);
return stratChilds;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {
return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )tom__arg) instanceof tom.engine.adt.code.types.bqterm.BQRecordAppl) ) {

return desugarer.replaceBQRecordAppl(
 (( tom.engine.adt.code.types.BQTerm )tom__arg).getOptions() ,
 (( tom.engine.adt.code.types.BQTerm )tom__arg).getAstName() ,
 (( tom.engine.adt.code.types.BQTerm )tom__arg).getSlots() );


}
}

}

}
return _visit_BQTerm(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_replaceBQRecordApplTomSyntax( tom.engine.desugarer.DesugarerPlugin  t0) { 
return new replaceBQRecordApplTomSyntax(t0);
}


/**
* Replace 'TermAppl' by its 'RecordAppl' form
* when no slotName exits, the position becomes the slotName
*/
protected TomTerm replaceTermAppl(OptionList option, TomNameList nameList, TomList args, ConstraintList constraints) {
TomName headName = nameList.getHeadconcTomName();
if(headName instanceof AntiName) {
headName = ((AntiName)headName).getName();
}
String opName = headName.getString();
TomSymbol tomSymbol = getSymbolFromName(opName);

//System.out.println("replaceTermAppl: " + tomSymbol);
//System.out.println("  nameList = " + nameList);

/*
* may be for constant patterns: f(1) for instance
*/
if(tomSymbol==null && args.isEmptyconcTomTerm()) {
return 
 tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(option, nameList,  tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() , constraints) ;
}

SlotList slotList = 
 tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ;
if(opName.equals("") || tomSymbol==null || TomBase.isListOperator(tomSymbol) || TomBase.isArrayOperator(tomSymbol)) {
for(TomTerm arg:(concTomTerm)args) {
try {
TomTerm subterm = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceTermApplTomSyntax(this)).visitLight(arg);
TomName slotName = 
 tom.engine.adt.tomname.types.tomname.EmptyName.make() ;
/*
* we cannot optimize when subterm.isUnamedVariable
* since it can be constrained
*/	  
slotList = 
tom_append_list_concSlot(slotList, tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(slotName, subterm) , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) );
} catch(tom.library.sl.VisitFailure e) {
System.out.println("should not be there");
}
}
} else {
PairNameDeclList pairNameDeclList = tomSymbol.getPairNameDeclList();
for(TomTerm arg:(concTomTerm)args) {
try {
TomTerm subterm = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceTermApplTomSyntax(this)).visitLight(arg);
TomName slotName = pairNameDeclList.getHeadconcPairNameDecl().getSlotName();
/*
* we cannot optimize when subterm.isUnamedVariable
* since it can be constrained
*/	  
slotList = 
tom_append_list_concSlot(slotList, tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(slotName, subterm) , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) );
pairNameDeclList = pairNameDeclList.getTailconcPairNameDecl();
} catch(tom.library.sl.VisitFailure e) {
System.out.println("should not be there");
}
}
}
return 
 tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(option, nameList, slotList, constraints) ;
}

/**
* Replace 'XMLAppl' by its 'RecordAppl' form
* when no slotName exits, the position becomes the slotName
*/
protected TomTerm replaceXMLAppl(OptionList optionList, TomNameList nameList,
TomList attrList, TomList childList, ConstraintList constraints) {
boolean implicitAttribute = TomBase.hasImplicitXMLAttribut(optionList);
boolean implicitChild     = TomBase.hasImplicitXMLChild(optionList);

TomList newAttrList  = 
 tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;
TomList newChildList = 
 tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;

if(implicitAttribute) { newAttrList  = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( tom.engine.adt.tomterm.types.tomterm.VariableStar.make(convertOriginTracking("_*",optionList), getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,tom_append_list_concTomTerm(newAttrList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ; }
if(implicitChild)     { newChildList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( tom.engine.adt.tomterm.types.tomterm.VariableStar.make(convertOriginTracking("_*",optionList), getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,tom_append_list_concTomTerm(newChildList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ; }

/*
* the list of attributes should not be typed before the sort
* the sortAttribute is extended to compare RecordAppl
*/

attrList = sortAttributeList(attrList);

/*
* Attributes: go from implicit notation to explicit notation
*/
Strategy typeStrategy = 
tom_make_TopDownIdStopOnSuccess(tom_make_replaceXMLApplTomSyntax(this));
for(TomTerm attr:(concTomTerm)attrList) {
try {
TomTerm newPattern = typeStrategy.visitLight(attr);
newAttrList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(newPattern,tom_append_list_concTomTerm(newAttrList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ;
if(implicitAttribute) {
newAttrList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( tom.engine.adt.tomterm.types.tomterm.VariableStar.make(convertOriginTracking("_*",optionList), getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,tom_append_list_concTomTerm(newAttrList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ;
}
} catch(tom.library.sl.VisitFailure e) {
System.out.println("should not be there");
}
}
newAttrList = newAttrList.reverse();

/*
* Childs: go from implicit notation to explicit notation
*/
for(TomTerm child:(concTomTerm)childList) {
try {
TomTerm newPattern = typeStrategy.visitLight(child);
newChildList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(newPattern,tom_append_list_concTomTerm(newChildList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ;
if(implicitChild) {
if(newPattern.isVariableStar()) {
// remove the previously inserted pattern
newChildList = newChildList.getTailconcTomTerm();
if(newChildList.getHeadconcTomTerm().isVariableStar()) {
// remove the previously inserted star
newChildList = newChildList.getTailconcTomTerm();
}
// re-insert the pattern
newChildList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(newPattern,tom_append_list_concTomTerm(newChildList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ;
} else {
newChildList = 
 tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( tom.engine.adt.tomterm.types.tomterm.VariableStar.make(convertOriginTracking("_*",optionList), getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,tom_append_list_concTomTerm(newChildList, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) ;
}
}
} catch(tom.library.sl.VisitFailure e) {
System.out.println("should not be there");
}
}
newChildList = newChildList.reverse();

/*
* encode the name and put it into the table of symbols
*/
TomNameList newNameList = 
 tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ;
matchBlock: 
{

{
{
if ( (nameList instanceof tom.engine.adt.tomname.types.TomNameList) ) {
if ( (((( tom.engine.adt.tomname.types.TomNameList )nameList) instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || ((( tom.engine.adt.tomname.types.TomNameList )nameList) instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( (( tom.engine.adt.tomname.types.TomNameList )nameList).isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch232_4= (( tom.engine.adt.tomname.types.TomNameList )nameList).getHeadconcTomName() ;
if ( (tomMatch232_4 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if ( "_".equals( tomMatch232_4.getString() ) ) {
if (  (( tom.engine.adt.tomname.types.TomNameList )nameList).getTailconcTomName() .isEmptyconcTomName() ) {

break matchBlock;

}
}
}
}
}
}

}
{
if ( (nameList instanceof tom.engine.adt.tomname.types.TomNameList) ) {
if ( (((( tom.engine.adt.tomname.types.TomNameList )nameList) instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || ((( tom.engine.adt.tomname.types.TomNameList )nameList) instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch232__end__10=(( tom.engine.adt.tomname.types.TomNameList )nameList);
do {
{
if (!( tomMatch232__end__10.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch232_14= tomMatch232__end__10.getHeadconcTomName() ;
if ( (tomMatch232_14 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

newNameList = 
tom_append_list_concTomName(newNameList, tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( tom.engine.adt.tomname.types.tomname.Name.make(ASTFactory.encodeXMLString(getSymbolTable(), tomMatch232_14.getString() )) , tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) );


}
}
if ( tomMatch232__end__10.isEmptyconcTomName() ) {
tomMatch232__end__10=(( tom.engine.adt.tomname.types.TomNameList )nameList);
} else {
tomMatch232__end__10= tomMatch232__end__10.getTailconcTomName() ;
}

}
} while(!( (tomMatch232__end__10==(( tom.engine.adt.tomname.types.TomNameList )nameList)) ));
}
}

}


}

}

/*
* any XML node
*/
TomTerm xmlHead;

if(newNameList.isEmptyconcTomName()) {
xmlHead = 
 tom.engine.adt.tomterm.types.tomterm.Variable.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() , getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
} else {
xmlHead = 
 tom.engine.adt.tomterm.types.tomterm.TermAppl.make(convertOriginTracking(newNameList.getHeadconcTomName().getString(),optionList), newNameList,  tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
}
try {
SlotList newArgs = 
 tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.SLOT_NAME) , typeStrategy.visitLight(xmlHead)) , tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.SLOT_ATTRLIST) , typeStrategy.visitLight( tom.engine.adt.tomterm.types.tomterm.TermAppl.make(convertOriginTracking("CONC_TNODE",optionList),  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.CONC_TNODE) , tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) , newAttrList,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) , tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.SLOT_CHILDLIST) , typeStrategy.visitLight( tom.engine.adt.tomterm.types.tomterm.TermAppl.make(convertOriginTracking("CONC_TNODE",optionList),  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.CONC_TNODE) , tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) , newChildList,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) ) ) ;

TomTerm result = 
 tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(optionList,  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( tom.engine.adt.tomname.types.tomname.Name.make(Constants.ELEMENT_NODE) , tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) , newArgs, constraints) ;

//System.out.println("replaceXML out:\n" + result);
return result;
} catch(tom.library.sl.VisitFailure e) {
//must never be executed
TomTerm star = 
 tom.engine.adt.tomterm.types.tomterm.VariableStar.make(convertOriginTracking("_*",optionList), getFreshVariable(), getSymbolTable().TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;
return star;
}
}

/* auxilliary methods */

private TomList sortAttributeList(TomList attrList) {

{
{
if ( (attrList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )attrList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )attrList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if ( (( tom.engine.adt.tomterm.types.TomList )attrList).isEmptyconcTomTerm() ) {
return attrList; 
}
}
}

}
{
if ( (attrList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )attrList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )attrList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
 tom.engine.adt.tomterm.types.TomList  tomMatch233__end__6=(( tom.engine.adt.tomterm.types.TomList )attrList);
do {
{
 tom.engine.adt.tomterm.types.TomList  tom_X1=tom_get_slice_concTomTerm((( tom.engine.adt.tomterm.types.TomList )attrList),tomMatch233__end__6, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() );
if (!( tomMatch233__end__6.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tom_e1= tomMatch233__end__6.getHeadconcTomTerm() ;
 tom.engine.adt.tomterm.types.TomList  tomMatch233_7= tomMatch233__end__6.getTailconcTomTerm() ;
 tom.engine.adt.tomterm.types.TomList  tomMatch233__end__10=tomMatch233_7;
do {
{
 tom.engine.adt.tomterm.types.TomList  tom_X2=tom_get_slice_concTomTerm(tomMatch233_7,tomMatch233__end__10, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() );
if (!( tomMatch233__end__10.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tom_e2= tomMatch233__end__10.getHeadconcTomTerm() ;
 tom.engine.adt.tomterm.types.TomList  tom_X3= tomMatch233__end__10.getTailconcTomTerm() ;
{
{
if ( (tom_e1 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e1) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomterm.types.TomList  tomMatch234_2= (( tom.engine.adt.tomterm.types.TomTerm )tom_e1).getArgs() ;
if ( ((tomMatch234_2 instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (tomMatch234_2 instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( tomMatch234_2.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_13= tomMatch234_2.getHeadconcTomTerm() ;
if ( (tomMatch234_13 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_12= tomMatch234_13.getNameList() ;
if ( ((tomMatch234_12 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_12 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_12.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_21= tomMatch234_12.getHeadconcTomName() ;
if ( (tomMatch234_21 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_12.getTailconcTomName() .isEmptyconcTomName() ) {
if ( (tom_e2 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e2) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomterm.types.TomList  tomMatch234_4= (( tom.engine.adt.tomterm.types.TomTerm )tom_e2).getArgs() ;
if ( ((tomMatch234_4 instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (tomMatch234_4 instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( tomMatch234_4.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_15= tomMatch234_4.getHeadconcTomTerm() ;
if ( (tomMatch234_15 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_14= tomMatch234_15.getNameList() ;
if ( ((tomMatch234_14 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_14 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_14.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_23= tomMatch234_14.getHeadconcTomName() ;
if ( (tomMatch234_23 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_14.getTailconcTomName() .isEmptyconcTomName() ) {

if(
 tomMatch234_21.getString() .compareTo(
 tomMatch234_23.getString() ) > 0) {
return 
sortAttributeList(tom_append_list_concTomTerm(tom_X1, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e2,tom_append_list_concTomTerm(tom_X2, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e1,tom_append_list_concTomTerm(tom_X3, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) )) ));
}


}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

}
{
if ( (tom_e1 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e1) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomterm.types.TomList  tomMatch234_26= (( tom.engine.adt.tomterm.types.TomTerm )tom_e1).getArgs() ;
if ( ((tomMatch234_26 instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (tomMatch234_26 instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( tomMatch234_26.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_37= tomMatch234_26.getHeadconcTomTerm() ;
if ( (tomMatch234_37 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_36= tomMatch234_37.getNameList() ;
if ( ((tomMatch234_36 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_36 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_36.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_45= tomMatch234_36.getHeadconcTomName() ;
if ( (tomMatch234_45 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_36.getTailconcTomName() .isEmptyconcTomName() ) {
if ( (tom_e2 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e2) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomterm.types.TomList  tomMatch234_28= (( tom.engine.adt.tomterm.types.TomTerm )tom_e2).getArgs() ;
if ( ((tomMatch234_28 instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (tomMatch234_28 instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( tomMatch234_28.isEmptyconcTomTerm() )) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_39= tomMatch234_28.getHeadconcTomTerm() ;
if ( (tomMatch234_39 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_38= tomMatch234_39.getNameList() ;
if ( ((tomMatch234_38 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_38 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_38.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_47= tomMatch234_38.getHeadconcTomName() ;
if ( (tomMatch234_47 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_38.getTailconcTomName() .isEmptyconcTomName() ) {

if(
 tomMatch234_45.getString() .compareTo(
 tomMatch234_47.getString() ) > 0) {
return 
sortAttributeList(tom_append_list_concTomTerm(tom_X1, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e2,tom_append_list_concTomTerm(tom_X2, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e1,tom_append_list_concTomTerm(tom_X3, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) )) ));
}


}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

}
{
if ( (tom_e1 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e1) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch234_50= (( tom.engine.adt.tomterm.types.TomTerm )tom_e1).getSlots() ;
if ( ((tomMatch234_50 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch234_50 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( tomMatch234_50.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch234_62= tomMatch234_50.getHeadconcSlot() ;
if ( (tomMatch234_62 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_61= tomMatch234_62.getAppl() ;
if ( (tomMatch234_61 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_63= tomMatch234_61.getNameList() ;
if ( ((tomMatch234_63 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_63 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_63.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_76= tomMatch234_63.getHeadconcTomName() ;
if ( (tomMatch234_76 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_63.getTailconcTomName() .isEmptyconcTomName() ) {
if ( (tom_e2 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e2) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch234_52= (( tom.engine.adt.tomterm.types.TomTerm )tom_e2).getSlots() ;
if ( ((tomMatch234_52 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch234_52 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( tomMatch234_52.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch234_67= tomMatch234_52.getHeadconcSlot() ;
if ( (tomMatch234_67 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_66= tomMatch234_67.getAppl() ;
if ( ( tomMatch234_62.getSlotName() == tomMatch234_67.getSlotName() ) ) {
if ( (tomMatch234_66 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_68= tomMatch234_66.getNameList() ;
if ( ((tomMatch234_68 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_68 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_68.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_78= tomMatch234_68.getHeadconcTomName() ;
if ( (tomMatch234_78 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_68.getTailconcTomName() .isEmptyconcTomName() ) {

if(
 tomMatch234_76.getString() .compareTo(
 tomMatch234_78.getString() ) > 0) {
return 
sortAttributeList(tom_append_list_concTomTerm(tom_X1, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e2,tom_append_list_concTomTerm(tom_X2, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e1,tom_append_list_concTomTerm(tom_X3, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) )) ));
}


}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

}
{
if ( (tom_e1 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e1) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch234_81= (( tom.engine.adt.tomterm.types.TomTerm )tom_e1).getSlots() ;
if ( ((tomMatch234_81 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch234_81 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( tomMatch234_81.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch234_93= tomMatch234_81.getHeadconcSlot() ;
if ( (tomMatch234_93 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_92= tomMatch234_93.getAppl() ;
if ( (tomMatch234_92 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_94= tomMatch234_92.getNameList() ;
if ( ((tomMatch234_94 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_94 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_94.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_107= tomMatch234_94.getHeadconcTomName() ;
if ( (tomMatch234_107 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_94.getTailconcTomName() .isEmptyconcTomName() ) {
if ( (tom_e2 instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom_e2) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch234_83= (( tom.engine.adt.tomterm.types.TomTerm )tom_e2).getSlots() ;
if ( ((tomMatch234_83 instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || (tomMatch234_83 instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( tomMatch234_83.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch234_98= tomMatch234_83.getHeadconcSlot() ;
if ( (tomMatch234_98 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch234_97= tomMatch234_98.getAppl() ;
if ( ( tomMatch234_93.getSlotName() == tomMatch234_98.getSlotName() ) ) {
if ( (tomMatch234_97 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
 tom.engine.adt.tomname.types.TomNameList  tomMatch234_99= tomMatch234_97.getNameList() ;
if ( ((tomMatch234_99 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch234_99 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch234_99.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch234_109= tomMatch234_99.getHeadconcTomName() ;
if ( (tomMatch234_109 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch234_99.getTailconcTomName() .isEmptyconcTomName() ) {

if(
 tomMatch234_107.getString() .compareTo(
 tomMatch234_109.getString() ) > 0) {
return 
sortAttributeList(tom_append_list_concTomTerm(tom_X1, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e2,tom_append_list_concTomTerm(tom_X2, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(tom_e1,tom_append_list_concTomTerm(tom_X3, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() )) )) ));
}


}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}

}


}



}
if ( tomMatch233__end__10.isEmptyconcTomTerm() ) {
tomMatch233__end__10=tomMatch233_7;
} else {
tomMatch233__end__10= tomMatch233__end__10.getTailconcTomTerm() ;
}

}
} while(!( (tomMatch233__end__10==tomMatch233_7) ));
}
if ( tomMatch233__end__6.isEmptyconcTomTerm() ) {
tomMatch233__end__6=(( tom.engine.adt.tomterm.types.TomList )attrList);
} else {
tomMatch233__end__6= tomMatch233__end__6.getTailconcTomTerm() ;
}

}
} while(!( (tomMatch233__end__6==(( tom.engine.adt.tomterm.types.TomList )attrList)) ));
}
}

}


}

return attrList;
}

private OptionList convertOriginTracking(String name,OptionList optionList) {
Option originTracking = TomBase.findOriginTracking(optionList);

{
{
if ( (originTracking instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )originTracking) instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {

return 
 tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.OriginTracking.make( tom.engine.adt.tomname.types.tomname.Name.make(name) ,  (( tom.engine.adt.tomoption.types.Option )originTracking).getLine() ,  (( tom.engine.adt.tomoption.types.Option )originTracking).getFileName() ) , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) ;


}
}

}

}

System.out.println("Warning: no OriginTracking information");
return 
 tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;
}

/**
* Replace 'BQRecordAppl' by its 'BQTermAppl' form
* BQDefault are automatically added
*/
protected BQTerm replaceBQRecordAppl(OptionList option, TomName symbolName, BQSlotList slots) {
String opName = symbolName.getString();
TomSymbol tomSymbol = getSymbolFromName(opName);

//System.out.println("replaceBQRecordAppl: " + tomSymbol);
int arity = TomBase.getArity(tomSymbol);
//System.out.println("arity: " + arity);
ArrayList<BQTerm> termArray = new ArrayList<BQTerm>(arity);
for(int i=0; i<arity ; i++) {
termArray.add(
 tom.engine.adt.code.types.bqterm.ConsComposite.make( tom.engine.adt.code.types.compositemember.CompositeBQTerm.make( tom.engine.adt.code.types.bqterm.BQDefault.make() ) , tom.engine.adt.code.types.bqterm.EmptyComposite.make() ) );
}
for(BQSlot slot:slots.getCollectionconcBQSlot()) {

{
{
if ( (slot instanceof tom.engine.adt.tomslot.types.BQSlot) ) {
if ( ((( tom.engine.adt.tomslot.types.BQSlot )slot) instanceof tom.engine.adt.tomslot.types.bqslot.PairSlotBQTerm) ) {

int slotIndex = TomBase.getSlotIndex(tomSymbol,
 (( tom.engine.adt.tomslot.types.BQSlot )slot).getSlotName() );
//System.out.println("termArray.size(): " + termArray.size());
//System.out.println("slotName: " + `slotName);
//System.out.println("slotIndex: " + slotIndex);
termArray.set(slotIndex,
 (( tom.engine.adt.tomslot.types.BQSlot )slot).getBqterm() );


}
}

}

}

}
//System.out.println("argList = " +  `ASTFactory.makeBQTermList(termArray));
return 
 tom.engine.adt.code.types.bqterm.BQAppl.make(option, symbolName, ASTFactory.makeBQTermList(termArray)) ;
}    
}
