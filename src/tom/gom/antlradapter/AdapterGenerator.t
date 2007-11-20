/*
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

package tom.gom.antlradapter;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.backend.CodeGen;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.code.types.*;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;
import tom.library.sl.VisitFailure;
import tom.library.sl.Strategy;

public class AdapterGenerator {

  /* Attributes needed to call tom properly */
  private File tomHomePath;
  private GomStreamManager streamManager;
  private String grammarPkg = "";
  private String grammarName = "";

  AdapterGenerator(File tomHomePath, GomStreamManager streamManager, String grammar) {
    this.tomHomePath = tomHomePath;
    this.streamManager = streamManager;
    int lastDot = grammar.lastIndexOf('.');
    if (-1 != lastDot) {
      // the grammar is in a package different from the gom file
      this.grammarPkg = grammar.substring(0,lastDot);
      this.grammarName = grammar.substring(lastDot+1,grammar.length());
    } else {
      this.grammarPkg = streamManager.getDefaultPackagePath();
      this.grammarName = grammar;
    }
  }

  %include { ../adt/gom/Gom.tom}
  %include { ../../library/mapping/java/sl.tom }
  %include { ../../library/mapping/java/util/types/Collection.tom }
  %typeterm Writer {
    implement { Writer }
  }

  public void generate(ModuleList moduleList, HookDeclList hookDecls) {
    writeTokenFile(moduleList);
    writeAdapterFile(moduleList);
    writeTreeFile(moduleList);
  }

  public int writeTokenFile(ModuleList moduleList) {
    try {
       File output = tokenFileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer =
         new BufferedWriter(
             new OutputStreamWriter(
               new FileOutputStream(output)));
       generateTokenFile(moduleList, writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  public int writeTreeFile(ModuleList moduleList) {
    try {
       File output = treeFileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer =
         new BufferedWriter(
             new OutputStreamWriter(
               new FileOutputStream(output)));
       generateTreeFile(moduleList, writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  public int writeAdapterFile(ModuleList moduleList) {
    try {
       File output = adaptorFileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer =
         new BufferedWriter(
             new OutputStreamWriter(
               new FileOutputStream(output)));
       generateAdapterFile(moduleList, writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  private String adapterPkg() {
    String packagePrefix = streamManager.getDefaultPackagePath();
    return ((packagePrefix=="")?filename():packagePrefix+"."+filename()).toLowerCase();
  }

  public void generateAdapterFile(ModuleList moduleList, Writer writer)
    throws java.io.IOException {
    writer.write(
    %[
package @adapterPkg()@;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class @filename()@Adaptor extends CommonTreeAdaptor {

	public Object create(Token payload) {
		return new @filename()@Tree(payload);
	}

}
]%);
  }

  public void generateTreeFile(ModuleList moduleList, Writer writer)
    throws java.io.IOException {
    Collection operatorset = new HashSet();
    Collection slotset = new HashSet();
    try {
      `TopDown(Sequence(CollectOperators(operatorset),CollectSlots(slotset))).visitLight(moduleList);
    } catch (VisitFailure f) {
      throw new GomRuntimeException("CollectOperators should not fail");
    }
    writer.write(%[
package @adapterPkg()@;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.*;
]%);
    if (!"".equals(grammarPkg)) {
    writer.write(%[
import @grammarPkg@.@grammarName@Parser;
]%);
    }
    writer.write(%[

public class @filename()@Tree extends CommonTree {

  private int termIndex = 0;
  /* Use SharedObject as type, as most general type */
  private shared.SharedObject inAstTerm = null;

  public @filename()@Tree(CommonTree node) {
    super(node);
    this.token = node.token;
    initAstTerm(node.token);
  }

  public @filename()@Tree(Token t) {
    this.token = t;
    initAstTerm(t);
  }

  private void initAstTerm(Token t) {
    if(null==t) {
      return;
    }
    switch (t.getType()) {
]%);
    Iterator it = operatorset.iterator();
    while(it.hasNext()) {
      OperatorDecl opDecl = (OperatorDecl) it.next();
      %match(opDecl) {
        op@OperatorDecl[Name=opName,Prod=Variadic[]] -> {
          Code code =
            `CodeList(
                Code("      case "+grammarName+"Parser."),
                Code(opName),
                Code(":\n"),
                Code("      {\n"),
                Code("        inAstTerm = "),
                Empty(op),
                Code(".make();\n"),
                Code("        break;\n"),
                Code("        }\n"));
          CodeGen.generateCode(code,writer);
        }
        op@OperatorDecl[Name=opName,Prod=Slots[Slots=concSlot()]] -> {
          /* Initialise constants */
          Code code =
            `CodeList(
                Code("      case "+grammarName+"Parser."),
                Code(opName),
                Code(":\n"),
                Code("      {\n"),
                Code("        inAstTerm = "),
                FullOperatorClass(op),
                Code(".make();\n"),
                Code("        break;\n"),
                Code("        }\n"));
          CodeGen.generateCode(code,writer);
        }
      }
    }
    writer.write(%[
    }
  }

]%);
    /* Add fields for each slot : first for variadic operators, then constructor slots */
    it = operatorset.iterator();
    while(it.hasNext()) {
      OperatorDecl op = (OperatorDecl) it.next();
      try {
        `GenerateSlots(writer).visitLight(op);
      } catch (VisitFailure f) {
        throw new GomRuntimeException("GenerateSlots for variadic operators should not fail");
      }
    }
    it = slotset.iterator();
    while(it.hasNext()) {
      Slot slot = (Slot) it.next();
      try {
        `GenerateSlots(writer).visitLight(slot);
      } catch (VisitFailure f) {
        throw new GomRuntimeException("GenerateSlots for slots should not fail");
      }
    }

    writer.write(%[

  public shared.SharedObject getTerm() {
    return inAstTerm;
  }

  public void addChild(Tree t) {
    super.addChild(t);
    if (null==t) {
      return;
    }
    @filename()@Tree tree = (@filename()@Tree) t;
    if(this.token == null || tree.token == null) {
      return;
    }

    /* Depending on the token number and the child count, fill the correct field */
    switch (this.token.getType()) {
]%);

    it = operatorset.iterator();
    while(it.hasNext()) {
      OperatorDecl op = (OperatorDecl) it.next();
      generateAddChildCase(op, writer);
    }

    writer.write(%[
      default: break;
    }

    termIndex++;
    /* Instantiate the term if needed */
  }
]%);
    writer.write(%[
}
]%);
  }

  public void generateTokenFile(ModuleList moduleList, Writer writer)
    throws java.io.IOException {
    Collection bag = new HashSet();
    try {
      `TopDown(CollectOperatorNames(bag)).visitLight(moduleList);
    } catch (VisitFailure f) {
      throw new GomRuntimeException("CollectOperatorNames should not fail");
    }
    Iterator it = bag.iterator();
    while(it.hasNext()) {
      String op = (String) it.next();
      writer.write(op + ";\n");
    }
  }

  %strategy CollectOperatorNames(bag:Collection) extends Identity() {
    visit OperatorDecl {
      OperatorDecl[Name=name] -> {
        bag.add(`name);
      }
    }
  }

  %strategy CollectOperators(bag:Collection) extends Identity() {
    visit OperatorDecl {
      op@OperatorDecl[] -> {
        bag.add(`op);
      }
    }
  }

  %strategy CollectSlots(bag:Collection) extends Identity() {
    visit Slot {
      slot@Slot[] -> {
        bag.add(`slot);
      }
    }
  }

  %strategy GenerateSlots(writer:Writer) extends Identity() {
    visit Slot {
      Slot[Name=name,Sort=sortDecl] -> {
        Code code =
          `CodeList(
              Code("  "),
              FullSortClass(sortDecl),
              Code(" field"),
              Code(name),
              Code(";\n")
           );
        try {
          CodeGen.generateCode(code,writer);
        } catch (IOException e) {
          throw new VisitFailure("IOException " + e);
        }
      }
    }
  }

  protected void generateAddChildCase(OperatorDecl opDecl, Writer writer) throws IOException {
    %match(opDecl) { 
      op@OperatorDecl[Name=opName,Sort=sortDecl,Prod=prod] -> {
        Code code =
          `CodeList(
              Code("      case "+grammarName+"Parser."),
              Code(opName),
              Code(":\n"),
              Code("      {\n")
              );
        %match(prod) {
          Slots[Slots=slotList] -> {
            code = `CodeList(code,
                Code("        "),
                FullSortClass(sortDecl),
                Code(" term = ("),
                FullOperatorClass(op),
                Code(") inAstTerm;\n"),
                Code("        "),
                Code("switch(termIndex) {\n")
                );
            int idx = 0;
            SlotList sList = `slotList;
            while(sList.isConsconcSlot()) {
              Slot slot = sList.getHeadconcSlot();
              sList = sList.getTailconcSlot();
              Code cast = genGetSubterm(slot.getSort());
              code = `CodeList(code,
                  Code("          "),
                  Code("case "+idx+":\n"),
                  Code("            field"),
                  Code(slot.getName() + " = "),
                  cast*,
                  Code(";\n")
                  );
              if(idx == `slotList.length() - 1) {
                code = `CodeList(code,
                    Code("            inAstTerm = "),
                    FullOperatorClass(op),
                    Code(".make("),
                    Code(genArgsList(slotList)),
                    Code(");\n")
                    );
              }
              code = `CodeList(code,
                  Code("            break;\n")
                  );
              idx++;
            }
            code = `CodeList(code,
                Code("        "),
                Code("}\n")
                );
          }
          Variadic[Sort=domainSort] -> {
            Code cast = genGetSubterm(`domainSort);
            code = `CodeList(code,
                Code("        "),
                FullSortClass(domainSort),
                Code(" elem = "),
                cast*,
                Code(";\n"),
                Code("        "),
                FullOperatorClass(op),
                Code(" list = ("),
                FullOperatorClass(op),
                Code(") inAstTerm;\n"),
                Code("        "),
                Code("inAstTerm = list.append(elem);\n")
                );
          }
        }
        code = `CodeList(code,
            Code("        break;\n"),
            Code("        }\n"));
        CodeGen.generateCode(code,writer);
      }
    }
  }

  protected Code genGetSubterm(SortDecl sort) {
    Code code = `CodeList();;
    %match(sort) {
      SortDecl[] -> {
        code = `CodeList(code,
            Code("("),
            FullSortClass(sort),
            Code(") tree.getTerm()"));
      }
      BuiltinSortDecl[Name=name] -> {
        if("int".equals(`name)) {
          code = `CodeList(code,
              Code("Integer.parseInt(t.getText())"));
        } else if ("String".equals(`name)) {
          code = `CodeList(code,
              Code("t.getText()"));
        } else {
          throw new RuntimeException("Unsupported builtin");
        }
      }
    }
    return code;
  }

  protected String genArgsList(SlotList slots) {
    String res = "";
    SlotList sList = slots;
    while(sList.isConsconcSlot()) {
      Slot slot = sList.getHeadconcSlot();
      sList = sList.getTailconcSlot();
      res += "field" + slot.getName();
      if(sList.isConsconcSlot()) {
        res += ", ";
      }
    }
    return res;
  }

  protected String fullFileName() {
    return (adapterPkg() + "." + filename()).replace('.',File.separatorChar);
  }

  protected String filename() {
    String filename = (new File(streamManager.getOutputFileName())).getName();
    int dotidx = filename.indexOf('.');
    if(-1 != dotidx) {
      filename = filename.substring(0,dotidx);
    }
    return filename;
  }

  protected File tokenFileToGenerate() {
    File output = new File(
        streamManager.getDestDir(),
        fullFileName()+"TokenList.txt");
    return output;
  }

  protected File adaptorFileToGenerate() {
    File output = new File(
        streamManager.getDestDir(),
        fullFileName()+"Adaptor.java");
    return output;
  }

  protected File treeFileToGenerate() {
    File output = new File(
        streamManager.getDestDir(),
        fullFileName()+"Tree.java");
    return output;
  }
}
