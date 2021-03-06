/*
 * Gom
 *
 * Copyright (c) 2006-2016, Universite de Lorraine, Inria
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

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.TemplateClass;
import tom.gom.backend.MappingTemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class MappingTemplate extends MappingTemplateClass {
  GomClassList sortClasses;
  GomClassList operatorClasses;
  TemplateClass strategyMapping;

         private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_ConcGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if( l1.isEmptyConcGomClass() ) {       return l2;     } else if( l2.isEmptyConcGomClass() ) {       return l1;     } else if(  l1.getTailConcGomClass() .isEmptyConcGomClass() ) {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,l2) ;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,tom_append_list_ConcGomClass( l1.getTailConcGomClass() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_ConcGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGomClass()  ||  (end== tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( begin.getHeadConcGomClass() ,( tom.gom.adt.objects.types.GomClassList )tom_get_slice_ConcGomClass( begin.getTailConcGomClass() ,end,tail)) ;   }    

  public MappingTemplate(GomClass gomClass, TemplateClass strategyMapping, GomEnvironment gomEnvironment) {
    super(gomClass,gomEnvironment);
    {{if ( (gomClass instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )gomClass) instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )(( tom.gom.adt.objects.types.GomClass )gomClass)) instanceof tom.gom.adt.objects.types.gomclass.TomMapping) ) {


        this.sortClasses =  (( tom.gom.adt.objects.types.GomClass )gomClass).getSortClasses() ;
        this.operatorClasses =  (( tom.gom.adt.objects.types.GomClass )gomClass).getOperatorClasses() ;
        this.strategyMapping = strategyMapping;

        assert this.templates!=null ;
        return;
      }}}}}

    throw new GomRuntimeException(
        "Wrong argument for MappingTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    if(getGomEnvironment().isBuiltinSort("boolean")) {
      writer.write("\n%include { boolean.tom }\n"

);
    }
    if(getGomEnvironment().isBuiltinSort("String")) {
      writer.write("\n%include { string.tom }\n"

);
    }
    if(getGomEnvironment().isBuiltinSort("int")) {
      writer.write("\n%include { int.tom }\n"

);
    }
    if(getGomEnvironment().isBuiltinSort("char")) {
      writer.write("\n%include { char.tom }\n"

);
    }
    if (getGomEnvironment().isBuiltinSort("double")) {
      writer.write("\n%include { double.tom }\n"

);
    }
    if (getGomEnvironment().isBuiltinSort("long")) {
      writer.write("\n%include { long.tom }\n"

);
    }
    if (getGomEnvironment().isBuiltinSort("float")) {
      writer.write("\n%include { float.tom }\n"

);
    }
    if (getGomEnvironment().isBuiltinSort("ATerm")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }
    if (getGomEnvironment().isBuiltinSort("ATermList")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }

    // generate a %typeterm for each class
    {{if ( (sortClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {if ( (((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )sortClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || ((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )sortClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) { tom.gom.adt.objects.types.GomClassList  tomMatch551_end_4=(( tom.gom.adt.objects.types.GomClassList )sortClasses);do {{if (!( tomMatch551_end_4.isEmptyConcGomClass() )) { tom.gom.adt.objects.types.GomClass  tomMatch551_8= tomMatch551_end_4.getHeadConcGomClass() ;if ( (tomMatch551_8 instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )tomMatch551_8) instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {



        (templates.get( tomMatch551_8.getClassName() ))
          .generateTomMapping(writer);
      }}}if ( tomMatch551_end_4.isEmptyConcGomClass() ) {tomMatch551_end_4=(( tom.gom.adt.objects.types.GomClassList )sortClasses);} else {tomMatch551_end_4= tomMatch551_end_4.getTailConcGomClass() ;}}} while(!( (tomMatch551_end_4==(( tom.gom.adt.objects.types.GomClassList )sortClasses)) ));}}}}


    // generate a %op for each operator
    {{if ( (operatorClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {if ( (((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || ((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) { tom.gom.adt.objects.types.GomClassList  tomMatch552_end_4=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);do {{if (!( tomMatch552_end_4.isEmptyConcGomClass() )) { tom.gom.adt.objects.types.GomClass  tomMatch552_8= tomMatch552_end_4.getHeadConcGomClass() ;if ( (tomMatch552_8 instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )tomMatch552_8) instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {



        //System.out.println("templates = " + templates);
        //System.out.println("opname    = " + `opName);
        //System.out.println("result    = " + templates.get(`opName));
        (templates.get( tomMatch552_8.getClassName() ))
          .generateTomMapping(writer);
      }}}if ( tomMatch552_end_4.isEmptyConcGomClass() ) {tomMatch552_end_4=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);} else {tomMatch552_end_4= tomMatch552_end_4.getTailConcGomClass() ;}}} while(!( (tomMatch552_end_4==(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) ));}}}}


    // generate a %oplist for each variadic operator
    {{if ( (operatorClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {if ( (((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || ((( tom.gom.adt.objects.types.GomClassList )(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) { tom.gom.adt.objects.types.GomClassList  tomMatch553_end_4=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);do {{if (!( tomMatch553_end_4.isEmptyConcGomClass() )) { tom.gom.adt.objects.types.GomClass  tomMatch553_8= tomMatch553_end_4.getHeadConcGomClass() ;if ( (tomMatch553_8 instanceof tom.gom.adt.objects.types.GomClass) ) {if ( ((( tom.gom.adt.objects.types.GomClass )tomMatch553_8) instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {



        (templates.get( tomMatch553_8.getClassName() ))
          .generateTomMapping(writer);
      }}}if ( tomMatch553_end_4.isEmptyConcGomClass() ) {tomMatch553_end_4=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);} else {tomMatch553_end_4= tomMatch553_end_4.getTailConcGomClass() ;}}} while(!( (tomMatch553_end_4==(( tom.gom.adt.objects.types.GomClassList )operatorClasses)) ));}}}}

    /* Include the strategy mapping (_file.tom) if needed */
    if(strategyMapping != null) {
      strategyMapping.generateTomMapping(writer);
    }
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = getGomEnvironment().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    // log the generated mapping file name
    try {
      getGomEnvironment()
        .setLastGeneratedMapping(output.getCanonicalPath());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }
}
