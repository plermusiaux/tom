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

package jtom.starter;

import java.util.logging.*;
import jtom.*;
import jtom.tools.*;
import aterm.*;

/**
 * The TomStarter "plugin". Only here to initialize the TomEnvironment.
 */
public class TomStarter extends TomGenericPlugin {

  private Object argToRelay;
  private String fileName = null;

  public TomStarter() {
    super("TomStarter");
  }

  public void setArg(Object arg) {
    argToRelay = arg;

    if (arg instanceof String) {
      fileName = (String)arg;  
    } else {
      getLogger().log(Level.SEVERE,
		      "TomStarter: A String was expected.");
    }
  }

  public Object getArg() {
    return argToRelay;
  }

  public void run() {
    // We need here to create the environment : 
    // We need to be sure we don't have side effects with the environment singleton
    TomEnvironment env = TomEnvironment.create();
    env.initInputFromArgs();
    env.updateEnvironment(fileName);
  }

}
