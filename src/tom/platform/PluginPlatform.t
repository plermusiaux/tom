/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
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

import java.util.*;
import java.util.logging.*;

import aterm.*;
import aterm.pure.*;

import tom.library.adt.tnode.*;

/**
 * The PluginPlatform manages plugins defined in an xml configuration file.
 * (which plugins are used and how they are ordered) with the intermediate
 * of a ConfigurationManager objet
 * It is main role is to run the plugins in the specified order and make some 
 * error management.
 *
 */
public class PluginPlatform {

  /** Used to analyse xml configuration file */
  %include{ adt/TNode.tom }

  /**
   * Accessor method necessary when including adt/TNode.tom
   * @return a TNodeFactory
   */  
  public TNodeFactory getTNodeFactory() {
    return TNodeFactory.getInstance(SingletonFactory.getInstance());
  }
    
  /** The List of reference to plugins. */
  private List pluginsList;
    
  /** The option manager */
  private OptionManager optionManager;
	
  /** The status handler */
  private StatusHandler statusHandler;

  /** List of input arg */
  private List inputToCompileList;

  /** List of generated object cleared before each run */
  private List lastGeneratedObjects;

  /** List of generated object cleared before each run */
  private RuntimeAlert lastRunAlert;
  
  /** Class Pluginplatform constructor */
  public PluginPlatform(ConfigurationManager confManager, String loggerRadical) {
    statusHandler = new StatusHandler();
    Logger.getLogger(loggerRadical).addHandler(this.statusHandler);
    inputToCompileList = new ArrayList();
    pluginsList = confManager.getPluginsList();
    optionManager = confManager.getOptionManager();
    inputToCompileList = optionManager.getInputToCompileList();
  }
  
  /**
   * The main method which runs the PluginPlatform.
   * 
   * @param argumentList the command line
   * @return an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  public int run() {
    // intialize run instances
    lastGeneratedObjects = new ArrayList();
    //lastRunAlert = new RuntimeAlert();
    // for each input we call the sequence of plug-ins
    for(int i=0; i < inputToCompileList.size(); i++) {
      Object input = inputToCompileList.get(i);
      Object[] pluginArg = new Object[]{input};
      Object initArgument = input;
      boolean success = true;
      statusHandler.clear();
      getLogger().log(Level.FINER, "NowCompiling", input);
      // runs the plugins
      Iterator it = pluginsList.iterator();
      while(it.hasNext()) {
        Plugin plugin = (Plugin)it.next();
        lastRunAlert = plugin.setArgs(pluginArg);
        if(statusHandler.hasError()) {
          getLogger().log(Level.SEVERE, "SettingArgError");
          success = false;
          break;
        }
        RuntimeAlert runAlert = plugin.run();
        lastRunAlert.concat(runAlert);
        if(statusHandler.hasError()) {
          success = false;
          getLogger().log(Level.SEVERE, "ProcessingError",
                          new Object[]{plugin.getClass().getName(), initArgument});
          break;
        }
        pluginArg = plugin.getArgs();
      }
      if(success) {
        // save the first element of last plugin getArg response
        // this shall correspond to a generated file name
        lastGeneratedObjects.add(pluginArg[0]);
      } 
      //else { 
      //break;
      //}
    }
    
    int nbOfErrors   = statusHandler.nbOfErrors();
    int nbOfWarnings = statusHandler.nbOfWarnings();

    if(statusHandler.hasError()) {
      // this is the highest possible level > will be printed no matter what 
      getLogger().log(Level.SEVERE, "PluginPlatformTaskErrorMessage",
                      new Integer(nbOfErrors));
      return 1;
    } else if( statusHandler.hasWarning() ) {
      getLogger().log(Level.INFO, "PluginPlatformTaskWarningMessage",
                      new Integer(nbOfWarnings));
      return 0;
    }
    return 0;
  }

  /**
   * An accessor method
   * @return the status handler.
   */
  public StatusHandler getStatusHandler() {
    return statusHandler;
  }

  /** return the list of last generated objects */
  public List getLastGeneratedObjects() {
    return lastGeneratedObjects;
  }

  /** return the alerts generated during last run */
  public RuntimeAlert getLastRunAlert() {
    return lastRunAlert;
  }

  /** logger accessor in case of logging needs*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
  
} // class PluginPlatform
