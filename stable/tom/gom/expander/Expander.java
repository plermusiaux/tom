/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006-2007, INRIA
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

package tom.gom.expander;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.platform.PlatformLogRecord;

import org.antlr.runtime.*;
import tom.gom.parser.GomLanguageLexer;
import tom.gom.parser.GomLanguageParser;
import tom.gom.adt.gom.GomTree;
import tom.gom.adt.gom.GomAdaptor;

public class Expander {
  private GomStreamManager streamManager;

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.gom.types.GomModuleList  tom_append_list_ConcGomModule( tom.gom.adt.gom.types.GomModuleList l1,  tom.gom.adt.gom.types.GomModuleList  l2) {     if( l1.isEmptyConcGomModule() ) {       return l2;     } else if( l2.isEmptyConcGomModule() ) {       return l1;     } else if(  l1.getTailConcGomModule() .isEmptyConcGomModule() ) {       return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( l1.getHeadConcGomModule() ,l2) ;     } else {       return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( l1.getHeadConcGomModule() ,tom_append_list_ConcGomModule( l1.getTailConcGomModule() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.GomModuleList  tom_get_slice_ConcGomModule( tom.gom.adt.gom.types.GomModuleList  begin,  tom.gom.adt.gom.types.GomModuleList  end, tom.gom.adt.gom.types.GomModuleList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make( begin.getHeadConcGomModule() ,( tom.gom.adt.gom.types.GomModuleList )tom_get_slice_ConcGomModule( begin.getTailConcGomModule() ,end,tail)) ;     }   }      private static   tom.gom.adt.gom.types.SectionList  tom_append_list_ConcSection( tom.gom.adt.gom.types.SectionList l1,  tom.gom.adt.gom.types.SectionList  l2) {     if( l1.isEmptyConcSection() ) {       return l2;     } else if( l2.isEmptyConcSection() ) {       return l1;     } else if(  l1.getTailConcSection() .isEmptyConcSection() ) {       return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( l1.getHeadConcSection() ,l2) ;     } else {       return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( l1.getHeadConcSection() ,tom_append_list_ConcSection( l1.getTailConcSection() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SectionList  tom_get_slice_ConcSection( tom.gom.adt.gom.types.SectionList  begin,  tom.gom.adt.gom.types.SectionList  end, tom.gom.adt.gom.types.SectionList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.gom.types.sectionlist.ConsConcSection.make( begin.getHeadConcSection() ,( tom.gom.adt.gom.types.SectionList )tom_get_slice_ConcSection( begin.getTailConcSection() ,end,tail)) ;     }   }      private static   tom.gom.adt.gom.types.ImportList  tom_append_list_ConcImportedModule( tom.gom.adt.gom.types.ImportList l1,  tom.gom.adt.gom.types.ImportList  l2) {     if( l1.isEmptyConcImportedModule() ) {       return l2;     } else if( l2.isEmptyConcImportedModule() ) {       return l1;     } else if(  l1.getTailConcImportedModule() .isEmptyConcImportedModule() ) {       return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( l1.getHeadConcImportedModule() ,l2) ;     } else {       return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( l1.getHeadConcImportedModule() ,tom_append_list_ConcImportedModule( l1.getTailConcImportedModule() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.ImportList  tom_get_slice_ConcImportedModule( tom.gom.adt.gom.types.ImportList  begin,  tom.gom.adt.gom.types.ImportList  end, tom.gom.adt.gom.types.ImportList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.gom.types.importlist.ConsConcImportedModule.make( begin.getHeadConcImportedModule() ,( tom.gom.adt.gom.types.ImportList )tom_get_slice_ConcImportedModule( begin.getTailConcImportedModule() ,end,tail)) ;     }   }    

  public Expander(GomStreamManager streamManager) {
    this.streamManager = streamManager;
  }

  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  /*
   * Compute the transitive closure of imported modules
   */
  public GomModuleList expand(GomModule module) {
    GomModuleList result =  tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make(module, tom.gom.adt.gom.types.gommodulelist.EmptyConcGomModule.make() ) ;
    Set alreadyParsedModule = new HashSet();
    alreadyParsedModule.add(module.getModuleName());
    Set moduleToAnalyse = generateModuleToAnalyseSet(module, alreadyParsedModule);
    getLogger().log(Level.FINER, "GomExpander:moduleToAnalyse {0}",
        new Object[]{moduleToAnalyse});

    while (!moduleToAnalyse.isEmpty()) {
      HashSet newModuleToAnalyse = new HashSet();
      Iterator it = moduleToAnalyse.iterator();

      while(it.hasNext()) {
        GomModuleName moduleNameName = (GomModuleName)it.next();
        String moduleName = moduleNameName.getName();

        if(!environment().isBuiltin(moduleName)) {
          if(!alreadyParsedModule.contains(moduleNameName)) {
            GomModule importedModule = parse(moduleName);
            if(importedModule == null) {
              return null;
            }
            result = tom_append_list_ConcGomModule(result, tom.gom.adt.gom.types.gommodulelist.ConsConcGomModule.make(importedModule, tom.gom.adt.gom.types.gommodulelist.EmptyConcGomModule.make() ) );
            alreadyParsedModule.add(moduleNameName);
            newModuleToAnalyse.addAll(generateModuleToAnalyseSet(importedModule,alreadyParsedModule));
	  }
        } else {
          environment().markUsedBuiltin(moduleName); 
        }
      }
      moduleToAnalyse = newModuleToAnalyse;
    }
    return result;
  }

  /*
   * Compute immediate imported modules where already parsed modules are removed
   */
  private Set generateModuleToAnalyseSet(GomModule module, Set alreadyParsedModule) {
    HashSet moduleToAnalyse = new HashSet();
    ImportList importedModules = getImportList(module);
    while(!importedModules.isEmptyConcImportedModule()) {
      GomModuleName name = importedModules.getHeadConcImportedModule().getModuleName();
      if(!alreadyParsedModule.contains(name)) {
        moduleToAnalyse.add(name);
      }
      importedModules = importedModules.getTailConcImportedModule();
    }
    //System.out.println("*** generateModuleToAnalyseSet = " + moduleToAnalyse);
    return moduleToAnalyse;
  }

  private GomModule parse(String moduleName) {
    getLogger().log(Level.FINE, "Seeking for file {0}",
        new Object[]{moduleName});
    GomModule result = null;
    File importedModuleFile = findModuleFile(moduleName);
    if(importedModuleFile == null) {
      getLogger().log(Level.SEVERE,
          GomMessage.moduleNotFound.getMessage(),
          new Object[]{moduleName});
      return null;
    }
    CharStream inputStream = null;
    try {
      inputStream = new ANTLRReaderStream(new FileReader(importedModuleFile));
    } catch (FileNotFoundException e) {
      getLogger().log(Level.SEVERE,
          GomMessage.fileNotFound.getMessage(),
          new Object[]{moduleName+".gom"});
      return null;
    } catch (java.io.IOException e) {
      getLogger().log(Level.SEVERE,
          GomMessage.fileNotFound.getMessage(),
          new Object[]{moduleName+".gom"});
      return null;
    }
		GomLanguageLexer lexer = new GomLanguageLexer(inputStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GomLanguageParser parser = new GomLanguageParser(tokens,streamManager);
    parser.setTreeAdaptor(new GomAdaptor());
    try {
      GomTree tree = (GomTree)parser.module().getTree();
      result = (GomModule) tree.getTerm();
    } catch (RecognitionException re) {
      getLogger().log(new PlatformLogRecord(Level.SEVERE,
            GomMessage.detailedParseException,
            re.getMessage(),moduleName+".gom", lexer.getLine()));
      return null;
    }
    return result;
  }

  /**
   * find a module locally or thanks to the stream manager import list
   */
  private File findModuleFile(String moduleName) {
    String extendedModuleName = moduleName+".gom";
    File f = new File(extendedModuleName);
    if(f.exists()) {
      return f;
    }
    return streamManager.findModuleFile(extendedModuleName);
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

  public ImportList getImportList(GomModule module) {
    ImportList imports =  tom.gom.adt.gom.types.importlist.EmptyConcImportedModule.make() ;
    {if ( (module instanceof tom.gom.adt.gom.types.GomModule) ) {{  tom.gom.adt.gom.types.GomModule  tomMatch444NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.GomModule )module);if ( (tomMatch444NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.gommodule.GomModule) ) {{  tom.gom.adt.gom.types.GomModuleName  tomMatch444NameNumber_freshVar_0= tomMatch444NameNumberfreshSubject_1.getModuleName() ;{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_freshVar_1= tomMatch444NameNumberfreshSubject_1.getSectionList() ;if ( ((tomMatch444NameNumber_freshVar_1 instanceof tom.gom.adt.gom.types.sectionlist.ConsConcSection) || (tomMatch444NameNumber_freshVar_1 instanceof tom.gom.adt.gom.types.sectionlist.EmptyConcSection)) ) {{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_freshVar_2=tomMatch444NameNumber_freshVar_1;{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_begin_4=tomMatch444NameNumber_freshVar_2;{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_end_5=tomMatch444NameNumber_freshVar_2;do {{{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_freshVar_3=tomMatch444NameNumber_end_5;if (!( tomMatch444NameNumber_freshVar_3.isEmptyConcSection() )) {if ( ( tomMatch444NameNumber_freshVar_3.getHeadConcSection()  instanceof tom.gom.adt.gom.types.section.Imports) ) {{  tom.gom.adt.gom.types.ImportList  tomMatch444NameNumber_freshVar_8=  tomMatch444NameNumber_freshVar_3.getHeadConcSection() .getImportList() ;{  tom.gom.adt.gom.types.SectionList  tomMatch444NameNumber_freshVar_6= tomMatch444NameNumber_freshVar_3.getTailConcSection() ;if ( true ) {

        imports = tom_append_list_ConcImportedModule(tomMatch444NameNumber_freshVar_8,tom_append_list_ConcImportedModule(imports, tom.gom.adt.gom.types.importlist.EmptyConcImportedModule.make() ));
      }}}}}}if ( tomMatch444NameNumber_end_5.isEmptyConcSection() ) {tomMatch444NameNumber_end_5=tomMatch444NameNumber_begin_4;} else {tomMatch444NameNumber_end_5= tomMatch444NameNumber_end_5.getTailConcSection() ;}}} while(!( tomMatch444NameNumber_end_5.equals(tomMatch444NameNumber_begin_4) ));}}}}}}}}}}

    return imports;
  }
}
