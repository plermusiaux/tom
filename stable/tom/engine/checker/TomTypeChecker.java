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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.platform.OptionParser;

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

import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;
import tom.library.sl.*;

/**
 * The TomTypeChecker plugin.
 */
public class TomTypeChecker extends TomChecker {

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_append_list_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList l1,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  l2) {     if( l1.isEmptyconcConstraintInstruction() ) {       return l2;     } else if( l2.isEmptyconcConstraintInstruction() ) {       return l1;     } else if(  l1.getTailconcConstraintInstruction() .isEmptyconcConstraintInstruction() ) {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,tom_append_list_concConstraintInstruction( l1.getTailconcConstraintInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_get_slice_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList  begin,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  end, tom.engine.adt.tominstruction.types.ConstraintInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraintInstruction()  ||  (end== tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( begin.getHeadconcConstraintInstruction() ,( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_get_slice_concConstraintInstruction( begin.getTailconcConstraintInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_append_list_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList l1,  tom.engine.adt.tomsignature.types.TomVisitList  l2) {     if( l1.isEmptyconcTomVisit() ) {       return l2;     } else if( l2.isEmptyconcTomVisit() ) {       return l1;     } else if(  l1.getTailconcTomVisit() .isEmptyconcTomVisit() ) {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,tom_append_list_concTomVisit( l1.getTailconcTomVisit() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slice_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList  begin,  tom.engine.adt.tomsignature.types.TomVisitList  end, tom.engine.adt.tomsignature.types.TomVisitList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomVisit()  ||  (end== tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( begin.getHeadconcTomVisit() ,( tom.engine.adt.tomsignature.types.TomVisitList )tom_get_slice_concTomVisit( begin.getTailconcTomVisit() ,end,tail)) ;   }      private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {     if( l1.isEmptyconcTomTerm() ) {       return l2;     } else if( l2.isEmptyconcTomTerm() ) {       return l1;     } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;     } else {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;     }   }   private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end== tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;   }    /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Choice) )) {       if(( ((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ):( null )) ==null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):l1):new tom.library.sl.Choice(((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Choice(((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):l1):new tom.library.sl.Choice(((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):l1),tom_append_list_Choice(((( (l1 instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Choice(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin):new tom.library.sl.Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)) );   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  v) { return ( ( (( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )==null)?v:new tom.library.sl.Choice(v,( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) ))) ) );}   


  /** the declared options string */
  public static final String DECLARED_OPTIONS = 
    "<options>" +
    "<boolean name='noTypeCheck' altName='' description='Do not perform type checking' value='false'/>" +
    "</options>";

  /**
   * inherited from OptionOwner interface (plugin) 
   */
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomTypeChecker.DECLARED_OPTIONS);
  }

  /** Constructor */
  public TomTypeChecker() {
    super("TomTypeChecker");
  }

  public void run() {
    if(isActivated()) {
      strictType = !getOptionBooleanValue("lazyType");
      long startChrono = System.currentTimeMillis();
      try {
        // clean up internals
        reinit();
        // perform analyse
        try {
          TomTerm subject = (TomTerm) getWorkingTerm();
          //System.out.println("type checking: ");
          //System.out.println(subject);
          tom_make_TopDownCollect(tom_make_checkTypeInference(this)).visitLight(subject);
        } catch(tom.library.sl.VisitFailure e) {
          System.out.println("strategy failed");
        }
        // verbose
        getLogger().log( Level.INFO, TomMessage.tomTypeCheckingPhase.getMessage(), Integer.valueOf((int)(System.currentTimeMillis()-startChrono)) );
      } catch (Exception e) {
        getLogger().log( Level.SEVERE, TomMessage.exceptionMessage.getMessage(), new Object[]{getClass().getName(), getStreamManager().getInputFileName(),e.getMessage()} );
        e.printStackTrace();
      }
    } else {
      // type checker desactivated    
      getLogger().log(Level.INFO, TomMessage.typeCheckerInactivated.getMessage());
    }
  }

  private boolean isActivated() {
    return !getOptionBooleanValue("noTypeCheck");
  }

  /**
   * Main type checking entry point:
   * We check all Match
   */
  private static class checkTypeInference extends tom.library.sl.BasicStrategy {private  TomTypeChecker  ttc;public checkTypeInference( TomTypeChecker  ttc) {super(( new tom.library.sl.Identity() ));this.ttc=ttc;}public  TomTypeChecker  getttc() {return ttc;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {{  tom.engine.adt.tominstruction.types.Instruction  tomMatch123NameNumberfreshSubject_1=(( tom.engine.adt.tominstruction.types.Instruction )tom__arg);{  tom.engine.adt.tominstruction.types.Instruction  tomMatch123NameNumber_freshVar_2=tomMatch123NameNumberfreshSubject_1;if ( (tomMatch123NameNumber_freshVar_2 instanceof tom.engine.adt.tominstruction.types.instruction.Match) ) {{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch123NameNumber_freshVar_0= tomMatch123NameNumber_freshVar_2.getConstraintInstructionList() ;{  tom.engine.adt.tomoption.types.OptionList  tomMatch123NameNumber_freshVar_1= tomMatch123NameNumber_freshVar_2.getOption() ;if ( true ) {



  
        ttc.currentTomStructureOrgTrack = TomBase.findOriginTracking(tomMatch123NameNumber_freshVar_1);
        ttc.verifyMatchVariable(tomMatch123NameNumber_freshVar_0);
        throw new tom.library.sl.VisitFailure();// to stop the top-downd
      }}}}}}}}return _visit_Instruction(tom__arg,introspector); }public  tom.engine.adt.tomdeclaration.types.Declaration  visit_Declaration( tom.engine.adt.tomdeclaration.types.Declaration  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {{  tom.engine.adt.tomdeclaration.types.Declaration  tomMatch124NameNumberfreshSubject_1=(( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg);{  tom.engine.adt.tomdeclaration.types.Declaration  tomMatch124NameNumber_freshVar_4=tomMatch124NameNumberfreshSubject_1;if ( (tomMatch124NameNumber_freshVar_4 instanceof tom.engine.adt.tomdeclaration.types.declaration.Strategy) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch124NameNumber_freshVar_0= tomMatch124NameNumber_freshVar_4.getSName() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch124NameNumber_freshVar_1= tomMatch124NameNumber_freshVar_4.getExtendsTerm() ;{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch124NameNumber_freshVar_2= tomMatch124NameNumber_freshVar_4.getVisitList() ;{  tom.engine.adt.tomoption.types.Option  tomMatch124NameNumber_freshVar_3= tomMatch124NameNumber_freshVar_4.getOrgTrack() ;if ( true ) {




        ttc.currentTomStructureOrgTrack = tomMatch124NameNumber_freshVar_3;
        ttc.verifyStrategyVariable(tomMatch124NameNumber_freshVar_2);
        throw new tom.library.sl.VisitFailure();// to stop the top-downd
      }}}}}}}}}}return _visit_Declaration(tom__arg,introspector); }public  tom.engine.adt.tomdeclaration.types.Declaration  _visit_Declaration( tom.engine.adt.tomdeclaration.types.Declaration  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomdeclaration.types.Declaration )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomdeclaration.types.Declaration )any.visitLight(arg,introspector));} }public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return (( tom.engine.adt.tominstruction.types.Instruction )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {return visit_Declaration((( tom.engine.adt.tomdeclaration.types.Declaration )v),introspector);}if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_checkTypeInference( TomTypeChecker  t0) { return new checkTypeInference(t0);}




  /* 
   * Collect unknown (not in symbol table) appls without ()
   */
  private static class collectUnknownAppls extends tom.library.sl.BasicStrategy {private  TomTypeChecker  ttc;public collectUnknownAppls( TomTypeChecker  ttc) {super(( new tom.library.sl.Identity() ));this.ttc=ttc;}public  TomTypeChecker  getttc() {return ttc;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch125NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch125NameNumber_freshVar_0=tomMatch125NameNumberfreshSubject_1;if ( (tomMatch125NameNumber_freshVar_0 instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {{  tom.engine.adt.tomterm.types.TomTerm  tom_app=tomMatch125NameNumberfreshSubject_1;if ( true ) {


        if(ttc.symbolTable().getSymbolFromName(ttc.getName(tom_app))==null) {
          ttc.messageError(findOriginTrackingFileName(tom_app.getOption()),
              findOriginTrackingLine(tom_app.getOption()),
              TomMessage.unknownVariableInWhen,
              new Object[]{ttc.getName(tom_app)});
        }
        // else, it's actually app()
        // else, it's a unknown (ie : java) function
      }}}}}}}return _visit_TomTerm(tom__arg,introspector); }public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomterm.types.TomTerm )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {return visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}



  private void verifyMatchVariable(ConstraintInstructionList constraintInstructionList) throws VisitFailure {
    {if ( (constraintInstructionList instanceof tom.engine.adt.tominstruction.types.ConstraintInstructionList) ) {{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumberfreshSubject_1=(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList);{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumber_freshVar_0=tomMatch126NameNumberfreshSubject_1;if ( ((tomMatch126NameNumber_freshVar_0 instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction) || (tomMatch126NameNumber_freshVar_0 instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction)) ) {{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumber_begin_2=tomMatch126NameNumber_freshVar_0;{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumber_end_3=tomMatch126NameNumber_freshVar_0;do {{{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumber_freshVar_1=tomMatch126NameNumber_end_3;if (!( tomMatch126NameNumber_freshVar_1.isEmptyconcConstraintInstruction() )) {{  tom.engine.adt.tominstruction.types.ConstraintInstruction  tomMatch126NameNumber_freshVar_8= tomMatch126NameNumber_freshVar_1.getHeadconcConstraintInstruction() ;if ( (tomMatch126NameNumber_freshVar_8 instanceof tom.engine.adt.tominstruction.types.constraintinstruction.ConstraintInstruction) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch126NameNumber_freshVar_6= tomMatch126NameNumber_freshVar_8.getConstraint() ;{  tom.engine.adt.tominstruction.types.Instruction  tomMatch126NameNumber_freshVar_7= tomMatch126NameNumber_freshVar_8.getAction() ;{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch126NameNumber_freshVar_4= tomMatch126NameNumber_freshVar_1.getTailconcConstraintInstruction() ;if ( true ) {


        // collect variables
        ArrayList<TomTerm> variableList = new ArrayList<TomTerm>();
        TomBase.collectVariable(variableList, tomMatch126NameNumber_freshVar_6);
        verifyVariableTypeListCoherence(variableList);        

        // TODO: check in the action that a VariableStar is under the right symbol
        verifyListVariableInAction(tomMatch126NameNumber_freshVar_7);
        //System.out.println(`action);
      }}}}}}}}if ( tomMatch126NameNumber_end_3.isEmptyconcConstraintInstruction() ) {tomMatch126NameNumber_end_3=tomMatch126NameNumber_begin_2;} else {tomMatch126NameNumber_end_3= tomMatch126NameNumber_end_3.getTailconcConstraintInstruction() ;}}} while(!( (tomMatch126NameNumber_end_3==tomMatch126NameNumber_begin_2) ));}}}}}}}
    
  }

  private void verifyVariableTypeListCoherence(ArrayList<TomTerm> list) {
    // compute multiplicities
    //System.out.println("list = " + list);
    HashMap<TomName,TomTerm> map = new HashMap<TomName,TomTerm>();
    for(TomTerm variable:list) {
      TomName name = variable.getAstName();
      if(map.containsKey(name)) {
        TomTerm var = map.get(name);
        //System.out.println("variable = " + variable);
        //System.out.println("var = " + var);
        TomType type1 = var.getAstType();
        TomType type2 = variable.getAstType();
        // we use getTomType because type1 may be a TypeWithSymbol and type2 a TomType
        if(!TomBase.getTomType(type1).equals(TomBase.getTomType(type2))) {
          messageError(findOriginTrackingFileName(variable.getOption()),
              findOriginTrackingLine(variable.getOption()),
              TomMessage.incoherentVariable,
              new Object[]{name.getString(), TomBase.getTomType(type1), TomBase.getTomType(type2)});
        }
      } else {
        map.put(name,variable);
      }
    }
  }

  private void verifyStrategyVariable(TomVisitList list) {
    {if ( (list instanceof tom.engine.adt.tomsignature.types.TomVisitList) ) {{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumberfreshSubject_1=(( tom.engine.adt.tomsignature.types.TomVisitList )list);{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumber_freshVar_0=tomMatch127NameNumberfreshSubject_1;if ( ((tomMatch127NameNumber_freshVar_0 instanceof tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit) || (tomMatch127NameNumber_freshVar_0 instanceof tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit)) ) {{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumber_begin_2=tomMatch127NameNumber_freshVar_0;{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumber_end_3=tomMatch127NameNumber_freshVar_0;do {{{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumber_freshVar_1=tomMatch127NameNumber_end_3;if (!( tomMatch127NameNumber_freshVar_1.isEmptyconcTomVisit() )) {{  tom.engine.adt.tomsignature.types.TomVisit  tomMatch127NameNumber_freshVar_9= tomMatch127NameNumber_freshVar_1.getHeadconcTomVisit() ;if ( (tomMatch127NameNumber_freshVar_9 instanceof tom.engine.adt.tomsignature.types.tomvisit.VisitTerm) ) {{  tom.engine.adt.tomtype.types.TomType  tomMatch127NameNumber_freshVar_6= tomMatch127NameNumber_freshVar_9.getVNode() ;{  tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch127NameNumber_freshVar_7= tomMatch127NameNumber_freshVar_9.getAstConstraintInstructionList() ;{  tom.engine.adt.tomoption.types.OptionList  tomMatch127NameNumber_freshVar_8= tomMatch127NameNumber_freshVar_9.getOption() ;{  tom.engine.adt.tomtype.types.TomType  tomMatch127NameNumber_freshVar_11=tomMatch127NameNumber_freshVar_6;if ( (tomMatch127NameNumber_freshVar_11 instanceof tom.engine.adt.tomtype.types.tomtype.TomTypeAlone) ) {{  String  tomMatch127NameNumber_freshVar_10= tomMatch127NameNumber_freshVar_11.getString() ;{  tom.engine.adt.tomoption.types.OptionList  tom_options=tomMatch127NameNumber_freshVar_8;{  tom.engine.adt.tomsignature.types.TomVisitList  tomMatch127NameNumber_freshVar_4= tomMatch127NameNumber_freshVar_1.getTailconcTomVisit() ;if ( true ) {

        String fileName = findOriginTrackingFileName(tom_options);
        messageError(fileName,
            findOriginTrackingLine(tom_options),
            TomMessage.unknownVisitedType,
            new Object[]{(tomMatch127NameNumber_freshVar_10)});
      }}}}}}}}}}}}}if ( tomMatch127NameNumber_end_3.isEmptyconcTomVisit() ) {tomMatch127NameNumber_end_3=tomMatch127NameNumber_begin_2;} else {tomMatch127NameNumber_end_3= tomMatch127NameNumber_end_3.getTailconcTomVisit() ;}}} while(!( (tomMatch127NameNumber_end_3==tomMatch127NameNumber_begin_2) ));}}}}}}}

  }

  /*
   * forbid to use a X* under the wrong symbol
   * like in f(X*) -> g(X*)
   */
  private void verifyListVariableInAction(Instruction action) {
    //System.out.println("Action = " + action);
    try {
      tom_make_TopDown(tom_make_checkVariableStar(this)).visitLight(action);
    } catch(VisitFailure e) {
      System.out.println("strategy failed");
    }
  }
 
  private static class checkVariableStar extends tom.library.sl.BasicStrategy {private  TomTypeChecker  ttc;public checkVariableStar( TomTypeChecker  ttc) {super(( new tom.library.sl.Identity() ));this.ttc=ttc;}public  TomTypeChecker  getttc() {return ttc;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch128NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);{  tom.engine.adt.tomterm.types.TomTerm  tomMatch128NameNumber_freshVar_2=tomMatch128NameNumberfreshSubject_1;{ boolean tomMatch128NameNumber_freshVar_19= false ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch128NameNumber_freshVar_1= null ;{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_0= null ;if ( (tomMatch128NameNumber_freshVar_2 instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendList) ) {{tomMatch128NameNumber_freshVar_19= true ;tomMatch128NameNumber_freshVar_0= tomMatch128NameNumber_freshVar_2.getAstName() ;tomMatch128NameNumber_freshVar_1= tomMatch128NameNumber_freshVar_2.getHeadTerm() ;}} else {if ( (tomMatch128NameNumber_freshVar_2 instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendArray) ) {{tomMatch128NameNumber_freshVar_19= true ;tomMatch128NameNumber_freshVar_0= tomMatch128NameNumber_freshVar_2.getAstName() ;tomMatch128NameNumber_freshVar_1= tomMatch128NameNumber_freshVar_2.getHeadTerm() ;}}}if ((tomMatch128NameNumber_freshVar_19 ==  true )) {{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_4=tomMatch128NameNumber_freshVar_0;if ( (tomMatch128NameNumber_freshVar_4 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch128NameNumber_freshVar_3= tomMatch128NameNumber_freshVar_4.getString() ;{  String  tom_listName=tomMatch128NameNumber_freshVar_3;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch128NameNumber_freshVar_6=tomMatch128NameNumber_freshVar_1;if ( (tomMatch128NameNumber_freshVar_6 instanceof tom.engine.adt.tomterm.types.tomterm.Composite) ) {{  tom.engine.adt.tomterm.types.TomList  tomMatch128NameNumber_freshVar_5= tomMatch128NameNumber_freshVar_6.getArgs() ;{  tom.engine.adt.tomterm.types.TomList  tomMatch128NameNumber_freshVar_7=tomMatch128NameNumber_freshVar_5;if ( ((tomMatch128NameNumber_freshVar_7 instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (tomMatch128NameNumber_freshVar_7 instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {if (!( tomMatch128NameNumber_freshVar_7.isEmptyconcTomTerm() )) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch128NameNumber_freshVar_12= tomMatch128NameNumber_freshVar_7.getHeadconcTomTerm() ;if ( (tomMatch128NameNumber_freshVar_12 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{  tom.engine.adt.tomoption.types.OptionList  tomMatch128NameNumber_freshVar_9= tomMatch128NameNumber_freshVar_12.getOption() ;{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_10= tomMatch128NameNumber_freshVar_12.getAstName() ;{  tom.engine.adt.tomtype.types.TomType  tomMatch128NameNumber_freshVar_11= tomMatch128NameNumber_freshVar_12.getAstType() ;{  tom.engine.adt.tomoption.types.OptionList  tom_options=tomMatch128NameNumber_freshVar_9;{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_14=tomMatch128NameNumber_freshVar_10;if ( (tomMatch128NameNumber_freshVar_14 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch128NameNumber_freshVar_13= tomMatch128NameNumber_freshVar_14.getString() ;{  tom.engine.adt.tomtype.types.TomType  tomMatch128NameNumber_freshVar_16=tomMatch128NameNumber_freshVar_11;if ( (tomMatch128NameNumber_freshVar_16 instanceof tom.engine.adt.tomtype.types.tomtype.TypeWithSymbol) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_15= tomMatch128NameNumber_freshVar_16.getRootSymbolName() ;{  tom.engine.adt.tomname.types.TomName  tomMatch128NameNumber_freshVar_18=tomMatch128NameNumber_freshVar_15;if ( (tomMatch128NameNumber_freshVar_18 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {{  String  tomMatch128NameNumber_freshVar_17= tomMatch128NameNumber_freshVar_18.getString() ;{  String  tom_rootName=tomMatch128NameNumber_freshVar_17;{  tom.engine.adt.tomterm.types.TomList  tomMatch128NameNumber_freshVar_8= tomMatch128NameNumber_freshVar_7.getTailconcTomTerm() ;if ( tomMatch128NameNumber_freshVar_8.isEmptyconcTomTerm() ) {if ( true ) {


        if(!tom_listName.equals(tom_rootName)) {
          ttc.messageError(findOriginTrackingFileName(tom_options),
              findOriginTrackingLine(tom_options),
              TomMessage.incoherentVariableStar,
              new Object[]{ (tomMatch128NameNumber_freshVar_13),(tom_rootName),(tom_listName) });
        }
      }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}return _visit_TomTerm(tom__arg,introspector); }public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));} else {return (( tom.engine.adt.tomterm.types.TomTerm )any.visitLight(arg,introspector));} }public Object visitLight(Object v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {return visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector);}if (!((environment ==  null ))) {return any.visit(environment,introspector);} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_checkVariableStar( TomTypeChecker  t0) { return new checkVariableStar(t0);}


}
