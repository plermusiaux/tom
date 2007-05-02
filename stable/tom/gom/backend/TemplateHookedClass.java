/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
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

package tom.gom.backend;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import tom.gom.backend.CodeGen;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

public abstract class TemplateHookedClass extends TemplateClass {
  protected HookList hooks;
  protected File tomHomePath;
  protected List importList;
  protected TemplateClass mapping;
 
  public TemplateHookedClass(GomClass gomClass,
                             File tomHomePath,
                             List importList,
                             TemplateClass mapping) {
    super(gomClass);
    this.hooks = gomClass.getHooks();
    this.tomHomePath = tomHomePath;
    this.importList = importList;
    this.mapping = mapping;
  }

  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */   private static boolean tom_equal_term_Code(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Code(Object t) { return  t instanceof tom.gom.adt.code.types.Code ;}private static boolean tom_equal_term_Hook(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Hook(Object t) { return  t instanceof tom.gom.adt.objects.types.Hook ;}private static boolean tom_equal_term_HookList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_HookList(Object t) { return  t instanceof tom.gom.adt.objects.types.HookList ;}private static boolean tom_is_fun_sym_BlockHook( tom.gom.adt.objects.types.Hook  t) { return  t instanceof tom.gom.adt.objects.types.hook.BlockHook ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_BlockHook_Code( tom.gom.adt.objects.types.Hook  t) { return  t.getCode() ;}private static boolean tom_is_fun_sym_InterfaceHook( tom.gom.adt.objects.types.Hook  t) { return  t instanceof tom.gom.adt.objects.types.hook.InterfaceHook ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_InterfaceHook_Code( tom.gom.adt.objects.types.Hook  t) { return  t.getCode() ;}private static boolean tom_is_fun_sym_ImportHook( tom.gom.adt.objects.types.Hook  t) { return  t instanceof tom.gom.adt.objects.types.hook.ImportHook ;}private static  tom.gom.adt.code.types.Code  tom_get_slot_ImportHook_Code( tom.gom.adt.objects.types.Hook  t) { return  t.getCode() ;}private static boolean tom_is_fun_sym_concHook( tom.gom.adt.objects.types.HookList  t) { return  t instanceof tom.gom.adt.objects.types.hooklist.ConsconcHook || t instanceof tom.gom.adt.objects.types.hooklist.EmptyconcHook ;}private static  tom.gom.adt.objects.types.HookList  tom_empty_list_concHook() { return  tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() ; }private static  tom.gom.adt.objects.types.HookList  tom_cons_list_concHook( tom.gom.adt.objects.types.Hook  e,  tom.gom.adt.objects.types.HookList  l) { return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make(e,l) ; }private static  tom.gom.adt.objects.types.Hook  tom_get_head_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.getHeadconcHook() ;}private static  tom.gom.adt.objects.types.HookList  tom_get_tail_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.getTailconcHook() ;}private static boolean tom_is_empty_concHook_HookList( tom.gom.adt.objects.types.HookList  l) { return  l.isEmptyconcHook() ;}   private static   tom.gom.adt.objects.types.HookList  tom_append_list_concHook( tom.gom.adt.objects.types.HookList l1,  tom.gom.adt.objects.types.HookList  l2) {     if(tom_is_empty_concHook_HookList(l1)) {       return l2;     } else if(tom_is_empty_concHook_HookList(l2)) {       return l1;     } else if(tom_is_empty_concHook_HookList(tom_get_tail_concHook_HookList(l1))) {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(l1),l2);     } else {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(l1),tom_append_list_concHook(tom_get_tail_concHook_HookList(l1),l2));     }   }   private static   tom.gom.adt.objects.types.HookList  tom_get_slice_concHook( tom.gom.adt.objects.types.HookList  begin,  tom.gom.adt.objects.types.HookList  end, tom.gom.adt.objects.types.HookList  tail) {     if(tom_equal_term_HookList(begin,end)) {       return tail;     } else {       return ( tom.gom.adt.objects.types.HookList )tom_cons_list_concHook(tom_get_head_concHook_HookList(begin),( tom.gom.adt.objects.types.HookList )tom_get_slice_concHook(tom_get_tail_concHook_HookList(begin),end,tail));     }   }    

  protected String generateBlock() {
    StringBuffer res = new StringBuffer();
    HookList h = tom_append_list_concHook(hooks,tom_empty_list_concHook());   
    if (tom_is_sort_HookList(h)) {{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.HookList )h);if (tom_is_fun_sym_concHook(tomMatch325NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumber_freshVar_0=tomMatch325NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumberbegin_82=tomMatch325NameNumber_freshVar_0;{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumberend_82=tomMatch325NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumber_freshVar_1=tomMatch325NameNumberend_82;if (!(tom_is_empty_concHook_HookList(tomMatch325NameNumber_freshVar_1))) {if (tom_is_fun_sym_BlockHook(tom_get_head_concHook_HookList(tomMatch325NameNumber_freshVar_1))) {{  tom.gom.adt.code.types.Code  tomMatch325NameNumber_freshVar_32=tom_get_slot_BlockHook_Code(tom_get_head_concHook_HookList(tomMatch325NameNumber_freshVar_1));{  tom.gom.adt.objects.types.HookList  tomMatch325NameNumber_freshVar_2=tom_get_tail_concHook_HookList(tomMatch325NameNumber_freshVar_1);if ( true ) {

        res.append(CodeGen.generateCode(tomMatch325NameNumber_freshVar_32));
        res.append("\n");
      }}}}}}if (tom_is_empty_concHook_HookList(tomMatch325NameNumberend_82)) {tomMatch325NameNumberend_82=tomMatch325NameNumberbegin_82;} else {tomMatch325NameNumberend_82=tom_get_tail_concHook_HookList(tomMatch325NameNumberend_82);}}} while(!(tom_equal_term_HookList(tomMatch325NameNumberend_82, tomMatch325NameNumberbegin_82)));}}}}}}

    return res.toString();
  }

  protected String generateImport() {
    StringBuffer res = new StringBuffer();
    HookList h = tom_append_list_concHook(hooks,tom_empty_list_concHook());   
    if (tom_is_sort_HookList(h)) {{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.HookList )h);if (tom_is_fun_sym_concHook(tomMatch326NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumber_freshVar_0=tomMatch326NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumberbegin_83=tomMatch326NameNumber_freshVar_0;{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumberend_83=tomMatch326NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumber_freshVar_1=tomMatch326NameNumberend_83;if (!(tom_is_empty_concHook_HookList(tomMatch326NameNumber_freshVar_1))) {if (tom_is_fun_sym_ImportHook(tom_get_head_concHook_HookList(tomMatch326NameNumber_freshVar_1))) {{  tom.gom.adt.code.types.Code  tomMatch326NameNumber_freshVar_32=tom_get_slot_ImportHook_Code(tom_get_head_concHook_HookList(tomMatch326NameNumber_freshVar_1));{  tom.gom.adt.objects.types.HookList  tomMatch326NameNumber_freshVar_2=tom_get_tail_concHook_HookList(tomMatch326NameNumber_freshVar_1);if ( true ) {

        res.append(CodeGen.generateCode(tomMatch326NameNumber_freshVar_32));
        res.append("\n");
      }}}}}}if (tom_is_empty_concHook_HookList(tomMatch326NameNumberend_83)) {tomMatch326NameNumberend_83=tomMatch326NameNumberbegin_83;} else {tomMatch326NameNumberend_83=tom_get_tail_concHook_HookList(tomMatch326NameNumberend_83);}}} while(!(tom_equal_term_HookList(tomMatch326NameNumberend_83, tomMatch326NameNumberbegin_83)));}}}}}}

    return res.toString();
  }

  protected String generateInterface() {
    StringBuffer res = new StringBuffer();
    HookList h = tom_append_list_concHook(hooks,tom_empty_list_concHook());   
    if (tom_is_sort_HookList(h)) {{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.HookList )h);if (tom_is_fun_sym_concHook(tomMatch327NameNumberfreshSubject_1)) {{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumber_freshVar_0=tomMatch327NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumberbegin_84=tomMatch327NameNumber_freshVar_0;{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumberend_84=tomMatch327NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumber_freshVar_1=tomMatch327NameNumberend_84;if (!(tom_is_empty_concHook_HookList(tomMatch327NameNumber_freshVar_1))) {if (tom_is_fun_sym_InterfaceHook(tom_get_head_concHook_HookList(tomMatch327NameNumber_freshVar_1))) {{  tom.gom.adt.code.types.Code  tomMatch327NameNumber_freshVar_32=tom_get_slot_InterfaceHook_Code(tom_get_head_concHook_HookList(tomMatch327NameNumber_freshVar_1));{  tom.gom.adt.objects.types.HookList  tomMatch327NameNumber_freshVar_2=tom_get_tail_concHook_HookList(tomMatch327NameNumber_freshVar_1);if ( true ) {

        res.append(",");
        res.append(CodeGen.generateCode(tomMatch327NameNumber_freshVar_32));
        res.append("\n");
      }}}}}}if (tom_is_empty_concHook_HookList(tomMatch327NameNumberend_84)) {tomMatch327NameNumberend_84=tomMatch327NameNumberbegin_84;} else {tomMatch327NameNumberend_84=tom_get_tail_concHook_HookList(tomMatch327NameNumberend_84);}}} while(!(tom_equal_term_HookList(tomMatch327NameNumberend_84, tomMatch327NameNumberbegin_84)));}}}}}}

    return res.toString();
  }

  /*
   * The function for generating the file is extended, to be able to call Tom if
   * necessary (i.e. if there are user defined hooks)
   */
  public int generateFile() {
    if (hooks.isEmptyconcHook()) {
      try {
        File output = fileToGenerate();
        // make sure the directory exists
        output.getParentFile().mkdirs();
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
        generate(writer);
        writer.flush();
        writer.close();
      } catch(Exception e) {
        e.printStackTrace();
        return 1;
      }
    } else { /* We need to call tom to generate the file */
      File xmlFile = new File(tomHomePath,"Tom.xml");
      if(!xmlFile.exists()) {
        getLogger().log(Level.FINER,"Failed to get canonical path for "+xmlFile.getPath());
      }
      String file_path = null;
      try {
        File output = fileToGenerate();
        file_path = output.getCanonicalPath();
      } catch (IOException e) {
        getLogger().log(Level.FINER,"Failed to get canonical path for "+fileName());
      }

      ArrayList tomParams = new ArrayList();      

      try {
        Iterator it = importList.iterator();
        while(it.hasNext()){
          String importPath = ((File)it.next()).getCanonicalPath();
          tomParams.add("--import");
          tomParams.add(importPath);
        }
      } catch (IOException e) {
        getLogger().log(Level.SEVERE,"Failed compute import list: " + e.getMessage());
      }

      tomParams.add("-X");
      tomParams.add(xmlFile.getPath());
      tomParams.add("--optimize");
      tomParams.add("--optimize2");
      /*
       * we need to add --wall if this option is active
       * we need to add --verbose if this option is active
       */
      tomParams.add("--output");
      tomParams.add(file_path);
      tomParams.add("-");     

      //String[] params = {"-X",xmlFile.getPath(),"--optimize","--optimize2","--output",file_path,"-"};
      //String[] params = {"-X",config_xml,"--output",file_path,"-"};

      //System.out.println("params: " + tomParams);

      try {
        StringWriter gen = new StringWriter();
        generate(gen);
        InputStream backupIn = System.in;
        System.setIn(new DataInputStream(new StringBufferInputStream(gen.toString())));
        int res = tom.engine.Tom.exec((String[])tomParams.toArray(new String[tomParams.size()]));
        //      int res = tom.engine.Tom.exec(params);
        System.setIn(backupIn);
        if (res != 0 ) {
          getLogger().log(Level.SEVERE, tom.gom.GomMessage.tomFailure.getMessage(),new Object[]{file_path});
          return res;
        }
      } catch (IOException e) {
        getLogger().log(Level.SEVERE,
            "Failed generate Tom code: " + e.getMessage());
      }
    }
    return 0;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

}
