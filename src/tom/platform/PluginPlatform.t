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

import java.text.*;
import java.util.*;
import java.util.logging.*;
import java.io.*;

import aterm.*;
import aterm.pure.*;

import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import tom.library.xml.*;

/**
 * The PluginPlatform manages plugins defined in an xml configuration file.
 * (which plugins are used and how they are ordered) with the intermediate
 * of a ConfigurationManager objet
 * It is main role is to run the plugins in the specified order and make some 
 * error management.
 *
 * @author Gr&eacute;gory ANDRIEN
 */
public class PluginPlatform {

  /** Used to analyse xml configuration file*/
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
	
  /** The PluginPlatform logger */
  private Logger logger;

  /** The status handler */
  private StatusHandler statusHandler;

  /** List of input file */
  private List inputFileList;
  /**
   * Class Pluginplatform constructor
   */
  public PluginPlatform(ConfigurationManager confManager, String loggerRadical) {
    pluginsList = confManager.getPluginsList();
    optionManager = confManager.getOptionManager();
    statusHandler = new StatusHandler();
    logger = Logger.getLogger(getClass().getName());
    inputFileList = optionManager.getInputFileList();
    Logger.getLogger(loggerRadical).addHandler(this.statusHandler);
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
    for(int i = 0; i < inputFileList.size(); i++) { // for each input file
      Object arg = inputFileList.get(i);
      logger.log(Level.FINER, "NowCompiling", arg);
      // runs the modules
      Iterator it = pluginsList.iterator();
      while(it.hasNext()) {
        Plugin plugin = (Plugin)it.next();
        plugin.setArg(arg);
        plugin.run();
        arg = plugin.getArg();
        if(statusHandler.hasError()) {
          logger.log(Level.SEVERE, "ProcessingError", arg);
          break;
        }
      }
    }
    
    int nbOfErrors   = statusHandler.nbOfErrors();
    int nbOfWarnings = statusHandler.nbOfWarnings();

    if(statusHandler.hasError()) {
      // this is the highest possible level > will be printed no matter what 
      logger.log(Level.OFF, "TaskErrorMessage", new Object[]{new Integer(nbOfErrors), new Integer(nbOfWarnings)});
      return 1;
    } else if( statusHandler.hasWarning() ) {
      logger.log(Level.OFF, "TaskWarningMessage", new Integer(nbOfWarnings));
      return 0;
    }
    return 0;
  }

  /**
   * An accessor method
   * @return the status handler.
   */
  public StatusHandler getStatusHandler() { return statusHandler; }

} // class PluginPlatform
