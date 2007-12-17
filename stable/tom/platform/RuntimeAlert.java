/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
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
 *
 **/

package tom.platform;

import aterm.pure.*;
import java.util.logging.*;

import tom.platform.adt.platformalert.*;
import tom.platform.adt.platformalert.types.*;

public class RuntimeAlert {
  
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.platform.adt.platformalert.types.AlertList  tom_append_list_concAlert( tom.platform.adt.platformalert.types.AlertList l1,  tom.platform.adt.platformalert.types.AlertList  l2) {     if( l1.isEmptyconcAlert() ) {       return l2;     } else if( l2.isEmptyconcAlert() ) {       return l1;     } else if(  l1.getTailconcAlert() .isEmptyconcAlert() ) {       return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( l1.getHeadconcAlert() ,l2) ;     } else {       return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( l1.getHeadconcAlert() ,tom_append_list_concAlert( l1.getTailconcAlert() ,l2)) ;     }   }   private static   tom.platform.adt.platformalert.types.AlertList  tom_get_slice_concAlert( tom.platform.adt.platformalert.types.AlertList  begin,  tom.platform.adt.platformalert.types.AlertList  end, tom.platform.adt.platformalert.types.AlertList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make( begin.getHeadconcAlert() ,( tom.platform.adt.platformalert.types.AlertList )tom_get_slice_concAlert( begin.getTailconcAlert() ,end,tail)) ;     }   }    

  private AlertList errors;
  private AlertList warnings;
  private int nbErrors;
  private int nbWarnings;

  public RuntimeAlert() {
    errors =  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ;
    warnings =  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ;
    nbErrors = 0;
    nbWarnings = 0;
  }

  /**
   * Add the warning only if it is not already in the list 
   */
  public void addWarning(String message, String file, int line) {
    Alert entry =  tom.platform.adt.platformalert.types.alert.Warning.make(message, file, line) ;
    {if ( (entry instanceof tom.platform.adt.platformalert.types.Alert) ) {{  tom.platform.adt.platformalert.types.Alert  tomMatch572NameNumberfreshSubject_1=(( tom.platform.adt.platformalert.types.Alert )entry);if ( (warnings instanceof tom.platform.adt.platformalert.types.AlertList) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumberfreshSubject_2=(( tom.platform.adt.platformalert.types.AlertList )warnings);{ boolean tomMatch572NameNumber_freshVar_7= false ;if ( ((tomMatch572NameNumberfreshSubject_2 instanceof tom.platform.adt.platformalert.types.alertlist.ConsconcAlert) || (tomMatch572NameNumberfreshSubject_2 instanceof tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert)) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumber_freshVar_0=tomMatch572NameNumberfreshSubject_2;{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumber_begin_2=tomMatch572NameNumber_freshVar_0;{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumber_end_3=tomMatch572NameNumber_freshVar_0;do {{{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumber_freshVar_1=tomMatch572NameNumber_end_3;if (!( tomMatch572NameNumber_freshVar_1.isEmptyconcAlert() )) {{  tom.platform.adt.platformalert.types.Alert  tomMatch572NameNumber_freshVar_6= tomMatch572NameNumber_freshVar_1.getHeadconcAlert() ;if ( tomMatch572NameNumber_freshVar_6.equals(tomMatch572NameNumberfreshSubject_1) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch572NameNumber_freshVar_4= tomMatch572NameNumber_freshVar_1.getTailconcAlert() ;tomMatch572NameNumber_freshVar_7= true ;}}}}}if ( tomMatch572NameNumber_end_3.isEmptyconcAlert() ) {tomMatch572NameNumber_end_3=tomMatch572NameNumber_begin_2;} else {tomMatch572NameNumber_end_3= tomMatch572NameNumber_end_3.getTailconcAlert() ;}}} while(!( tomMatch572NameNumber_end_3.equals(tomMatch572NameNumber_begin_2) ));}}}}if ((tomMatch572NameNumber_freshVar_7 ==  false )) {if ( true ) {

        warnings =  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make(entry,tom_append_list_concAlert(warnings, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() )) ;
        nbWarnings++;   
      }}}}}}}}
    
  }
  
  /**
   * Add the error only if it is not already in the list 
   */
  public void addError(String message, String file, int line) {
    Alert entry =  tom.platform.adt.platformalert.types.alert.Error.make(message, file, line) ;
    {if ( (entry instanceof tom.platform.adt.platformalert.types.Alert) ) {{  tom.platform.adt.platformalert.types.Alert  tomMatch573NameNumberfreshSubject_1=(( tom.platform.adt.platformalert.types.Alert )entry);if ( (errors instanceof tom.platform.adt.platformalert.types.AlertList) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumberfreshSubject_2=(( tom.platform.adt.platformalert.types.AlertList )errors);{ boolean tomMatch573NameNumber_freshVar_7= false ;if ( ((tomMatch573NameNumberfreshSubject_2 instanceof tom.platform.adt.platformalert.types.alertlist.ConsconcAlert) || (tomMatch573NameNumberfreshSubject_2 instanceof tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert)) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumber_freshVar_0=tomMatch573NameNumberfreshSubject_2;{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumber_begin_2=tomMatch573NameNumber_freshVar_0;{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumber_end_3=tomMatch573NameNumber_freshVar_0;do {{{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumber_freshVar_1=tomMatch573NameNumber_end_3;if (!( tomMatch573NameNumber_freshVar_1.isEmptyconcAlert() )) {{  tom.platform.adt.platformalert.types.Alert  tomMatch573NameNumber_freshVar_6= tomMatch573NameNumber_freshVar_1.getHeadconcAlert() ;if ( tomMatch573NameNumber_freshVar_6.equals(tomMatch573NameNumberfreshSubject_1) ) {{  tom.platform.adt.platformalert.types.AlertList  tomMatch573NameNumber_freshVar_4= tomMatch573NameNumber_freshVar_1.getTailconcAlert() ;tomMatch573NameNumber_freshVar_7= true ;}}}}}if ( tomMatch573NameNumber_end_3.isEmptyconcAlert() ) {tomMatch573NameNumber_end_3=tomMatch573NameNumber_begin_2;} else {tomMatch573NameNumber_end_3= tomMatch573NameNumber_end_3.getTailconcAlert() ;}}} while(!( tomMatch573NameNumber_end_3.equals(tomMatch573NameNumber_begin_2) ));}}}}if ((tomMatch573NameNumber_freshVar_7 ==  false )) {if ( true ) {

        errors =  tom.platform.adt.platformalert.types.alertlist.ConsconcAlert.make(entry,tom_append_list_concAlert(errors, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() )) ;
        nbErrors++;    
      }}}}}}}}
    
  }
  
  public int getNbErrors() {
    return nbErrors;
  }

  public int getNbWarnings() {
    return nbWarnings;
  }

  public boolean hasErrors() {
    return (nbErrors>0);
  }

  public boolean hasWarnings() {
    return (nbWarnings>0);
  }

  public AlertList getErrors() {
    return errors;
  }

  public AlertList getWarnings() {
    return warnings;
  }
  
  public void concat(RuntimeAlert newErrors) {
    if(newErrors.getErrors() !=  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ) {
      AlertList newAlerts = newErrors.getErrors();
      errors = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(errors, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ));
      nbErrors += newErrors.getNbErrors();      
    }
    if(newErrors.getWarnings() !=  tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ) {
      AlertList newAlerts = newErrors.getWarnings();
      warnings = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(warnings, tom.platform.adt.platformalert.types.alertlist.EmptyconcAlert.make() ));
      nbWarnings += newErrors.getNbWarnings();
    }
  }

  /**
   * @param record
   */
  public void add(PlatformLogRecord record) {
    
	PlatformFormatter formatter = new PlatformFormatter();   
	  
	if(record.getLevel() == Level.SEVERE) {
      addError(formatter.formatMessage(record), record.getFilePath(), record.getLine());
    } else if(record.getLevel() == Level.WARNING) {
      addWarning(formatter.formatMessage(record), record.getFilePath(), record.getLine());
    }
  }

} //class RuntimeAlert
