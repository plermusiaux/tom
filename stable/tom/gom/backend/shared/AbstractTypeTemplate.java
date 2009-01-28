/* Generated by TOM (version 2.6): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2006-2008, INRIA
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
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom.backend.shared;

import java.io.*;
import java.util.*;

import tom.gom.backend.TemplateClass;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.tools.GomEnvironment;
import tom.platform.OptionManager;

public class AbstractTypeTemplate extends TemplateHookedClass {
  ClassNameList sortList;

  /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */ /* Generated by TOM (version 2.6): Do not edit this file *//* Generated by TOM (version 2.6): Do not edit this file */  /* Generated by TOM (version 2.6): Do not edit this file */  
  boolean maximalsharing;

  public AbstractTypeTemplate(File tomHomePath,
                              OptionManager manager,
                              List importList,
                              GomClass gomClass,
                              TemplateClass mapping,
                              boolean maximalsharing,
                              GomEnvironment gomEnvironment){
    super(gomClass,manager,tomHomePath,importList,mapping,gomEnvironment);
    this.maximalsharing = maximalsharing;
    {{if ( (gomClass instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )gomClass) instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass) ) {

        this.sortList =  (( tom.gom.adt.objects.types.GomClass )gomClass).getSortList() ;
        return;
      }}}}

    throw new GomRuntimeException(
        "Bad argument for AbstractTypeTemplate: " + gomClass);
  }

  public GomEnvironment getGomEnvironment() {
    return this.gomEnvironment;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    
    writer.write(
"\npackage "/* Generated by TOM (version 2.6): Do not edit this file */+getPackage()+";\n"/* Generated by TOM (version 2.6): Do not edit this file */+generateImport()+"\n"


);

    if (maximalsharing) {
    writer.write(
"\npublic abstract class "/* Generated by TOM (version 2.6): Do not edit this file */+className()+" implements shared.SharedObjectWithID, tom.library.sl.Visitable, Comparable "/* Generated by TOM (version 2.6): Do not edit this file */+generateInterface()+" {\n"

);
} else {
    writer.write(
"\npublic abstract class "/* Generated by TOM (version 2.6): Do not edit this file */+className()+" implements tom.library.sl.Visitable, Cloneable, Comparable "/* Generated by TOM (version 2.6): Do not edit this file */+generateInterface()+" {\n"

);
}

    if (hooks.containsTomCode()) {
      mapping.generate(writer); 
    }
    writer.write(
"\n"/* Generated by TOM (version 2.6): Do not edit this file */+generateBlock()+"\n"

);

    if (maximalsharing) {
    writer.write(
"\n  private int uniqueID;\n\n  protected static final shared.SharedObjectFactory factory = shared.SingletonSharedObjectFactory.getInstance();\n"



);
    }

writer.write(
"\n  protected static final aterm.ATermFactory atermFactory = aterm.pure.SingletonFactory.getInstance();\n\n  public abstract aterm.ATerm toATerm();\n\n  public abstract String symbolName();\n\n  @Override\n  public String toString() {\n    java.lang.StringBuilder buffer = new java.lang.StringBuilder();\n    toStringBuilder(buffer);\n    return buffer.toString();\n  }\n\n  public abstract void toStringBuilder(java.lang.StringBuilder buffer);\n\n  public abstract int compareTo(Object o);\n\n  public abstract int compareToLPO(Object o);\n\n"



















);

if (maximalsharing) {
 writer.write(
"\n  public int getUniqueIdentifier() {\n    return uniqueID;\n  }\n\n  public void setUniqueIdentifier(int uniqueID) {\n    this.uniqueID = uniqueID;\n  }\n\n}\n"









);
} else {
  //implement the Cloneable interface and decalre a public method clone()
 writer.write(
"\n public abstract Object clone();\n}\n"


);
}
 }

}
