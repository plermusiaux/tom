/* Generated by TOM: Do not edit this file */ /*
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

package jtom.backend;

import java.io.IOException;

import jtom.adt.tomsignature.types.*;
import jtom.tools.TomTaskInput;
import jtom.tools.OutputCode;
import jtom.exception.TomRuntimeException;
import jtom.TomEnvironment;
 
public abstract class TomImperativeGenerator extends TomGenericGenerator {

  protected String modifier = "";
  public TomImperativeGenerator(OutputCode output) {
    super(output);
  }

  // ------------------------------------------------------------
  /* Generated by TOM: Do not edit this file *//* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/       /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/    /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/        
  // ------------------------------------------------------------

  protected abstract void buildNamedBlock(int deep, String blockName, InstructionList instList) throws IOException;
  protected abstract void buildExpTrue(int deep) throws IOException;
  protected abstract void buildExpFalse(int deep) throws IOException;
  

  /*
   * the method implementations are here common to C and Java
   */

  protected void buildInstructionSequence(int deep, Instruction instruction) throws IOException {
    generateInstruction(deep, instruction);
    return;
  }

  protected void buildComment(int deep, String text) throws IOException {
    output.writeln("/* " + text + " */");
    return;
  }
  
  protected void buildListOrArray(int deep, TomTerm list) throws IOException {
     { jtom.adt.tomsignature.types.TomTerm tom_match1_1=(( jtom.adt.tomsignature.types.TomTerm)list);{ if(tom_is_fun_sym_BuildEmptyList(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildEmptyList_astName(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; 

        output.write("tom_empty_list_" + name  + "()");
        return;
      }} }} } if(tom_is_fun_sym_BuildConsList(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildConsList_astName(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_2=tom_get_slot_BuildConsList_headTerm(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_3=tom_get_slot_BuildConsList_tailTerm(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; { jtom.adt.tomsignature.types.TomTerm headTerm=tom_match1_1_2; { jtom.adt.tomsignature.types.TomTerm tailTerm=tom_match1_1_3; 


        output.write("tom_cons_list_" + name  + "(");
        generate(deep,headTerm );
        output.write(",");
        generate(deep,tailTerm );
        output.write(")");
        return;
      }}}} }}}} } if(tom_is_fun_sym_BuildAppendList(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildAppendList_astName(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_2=tom_get_slot_BuildAppendList_headTerm(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_3=tom_get_slot_BuildAppendList_tailTerm(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; { jtom.adt.tomsignature.types.TomTerm headTerm=tom_match1_1_2; { jtom.adt.tomsignature.types.TomTerm tailTerm=tom_match1_1_3; 


        output.write("tom_append_list_" + name  + "(");
        generate(deep,headTerm );
        output.write(",");
        generate(deep,tailTerm );
        output.write(")");
        return;
      }}}} }}}} } if(tom_is_fun_sym_BuildEmptyArray(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildEmptyArray_astName(tom_match1_1); { int  tom_match1_1_2=tom_get_slot_BuildEmptyArray_size(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; { int  size=tom_match1_1_2; 


        output.write("tom_empty_array_" + name  + "(" + size  + ")");
        return;
      }}} }}} } if(tom_is_fun_sym_BuildConsArray(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildConsArray_astName(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_2=tom_get_slot_BuildConsArray_headTerm(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_3=tom_get_slot_BuildConsArray_tailTerm(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; { jtom.adt.tomsignature.types.TomTerm headTerm=tom_match1_1_2; { jtom.adt.tomsignature.types.TomTerm tailTerm=tom_match1_1_3; 


        output.write("tom_cons_array_" + name  + "(");
        generate(deep,headTerm );
        output.write(",");
        generate(deep,tailTerm );
        output.write(")");
        return;
      }}}} }}}} } if(tom_is_fun_sym_BuildAppendArray(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1=tom_get_slot_BuildAppendArray_astName(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_2=tom_get_slot_BuildAppendArray_headTerm(tom_match1_1); { jtom.adt.tomsignature.types.TomTerm tom_match1_1_3=tom_get_slot_BuildAppendArray_tailTerm(tom_match1_1); if(tom_is_fun_sym_Name(tom_match1_1_1) ||  false ) { { String  tom_match1_1_1_1=tom_get_slot_Name_string(tom_match1_1_1); { String  name=tom_match1_1_1_1; { jtom.adt.tomsignature.types.TomTerm headTerm=tom_match1_1_2; { jtom.adt.tomsignature.types.TomTerm tailTerm=tom_match1_1_3; 


        output.write("tom_append_array_" + name  + "(");
        generate(deep,headTerm );
        output.write(",");
        generate(deep,tailTerm );
        output.write(")");
        return;
      }}}} }}}} }}} 

  }

  protected void buildFunctionCall(int deep, String name, TomList argList) throws IOException {
    output.write(name);
    output.writeOpenBrace();
    while(!argList.isEmpty()) {
      generate(deep,argList.getHead());
      argList = argList.getTail();
      if(!argList.isEmpty()) {
        output.writeComa();
      }
    }
    output.writeCloseBrace();
  }

  protected void buildFunctionBegin(int deep, String tomName, TomList varList) throws IOException {
    TomSymbol tomSymbol = symbolTable().getSymbol(tomName);
    String glType = getTLType(getSymbolCodomain(tomSymbol));
    String name = tomSymbol.getAstName().getString();
    
    output.write(deep,glType + " " + name + "(");
    while(!varList.isEmpty()) {
      TomTerm localVar = varList.getHead();
      matchBlock: {
         { jtom.adt.tomsignature.types.TomTerm tom_match2_1=(( jtom.adt.tomsignature.types.TomTerm)localVar);{ if(tom_is_fun_sym_Variable(tom_match2_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm v=tom_match2_1; { jtom.adt.tomsignature.types.TomType tom_match2_1_3=tom_get_slot_Variable_astType(tom_match2_1); { jtom.adt.tomsignature.types.TomType type2=tom_match2_1_3; 

            output.write(deep,getTLType(type2 ) + " ");
            generate(deep,v );
            break matchBlock;
          }}} } 

            System.out.println("MakeFunction: strange term: " + localVar);
            throw new TomRuntimeException(new Throwable("MakeFunction: strange term: " + localVar));
          }} 

      }
      varList = varList.getTail();
      if(!varList.isEmpty()) {
        output.write(deep,", ");
        
      }
    }
    output.writeln(deep,") {");
  }
  
  protected void buildFunctionEnd(int deep) throws IOException {
    output.writeln(deep,"}");
  }
  
  protected void buildExpNot(int deep, Expression exp) throws IOException {
    output.write("!(");
    generateExpression(deep,exp);
    output.write(")");
  }

  protected void buildRef(int deep, TomTerm term) throws IOException {
    generate(deep,term);
  }

  protected void buildExpCast(int deep, TomType tlType, Expression exp) throws IOException {
    /*
      TomType expType = getTermType(exp);
      if(expType.hasTlType() && expType.getTlType() == tlType) {
      generateExpression(deep,exp);
      } else {
      output.write("((" + getTLCode(tlType) + ")");
      generateExpression(deep,exp);
      output.write(")");
      }
    */
    output.write("((" + getTLCode(tlType) + ")");
    generateExpression(deep,exp);
    output.write(")");
  }
  
  protected void buildLet(int deep, TomTerm var, OptionList optionList, TomType tlType, 
                          Expression exp, Instruction body) throws IOException {
    output.write(deep,"{" + getTLCode(tlType) + " ");
    buildAssignVar(deep,var,optionList,exp);
    generateInstruction(deep,body);
    output.writeln("}");
  }

  protected void buildLetRef(int deep, TomTerm var, OptionList optionList, TomType tlType, 
                             Expression exp, Instruction body) throws IOException {
    buildLet(deep,var,optionList,tlType,exp,body);
  }

  protected void buildAssignVar(int deep, TomTerm var, OptionList list, Expression exp) throws IOException {
    //output.indent(deep);
    generate(deep,var);
    output.write("=");
    generateExpression(deep,exp);
    output.writeln(";");
    if(getInput().isDebugMode() && !list.isEmpty()) {
      output.write("jtom.debug.TomDebugger.debugger.addSubstitution(\""+debugKey+"\",\"");
      generate(deep,var);
      output.write("\", ");
      generateExpression(deep,exp);
      output.writeln(");");
    }
  }

  protected void buildLetAssign(int deep, TomTerm var, OptionList list, Expression exp, Instruction body) throws IOException {
    buildAssignVar(deep, var, list, exp);
    generateInstruction(deep,body);
  }

  protected void buildUnamedBlock(int deep, InstructionList instList) throws IOException {
    output.writeln("{");
    generateInstructionList(deep+1,instList);
    output.writeln("}");
  }

  protected void buildIfThenElse(int deep, Expression exp, Instruction succes) throws IOException {
    output.write(deep,"if("); 
    generateExpression(deep,exp); 
    output.writeln(") {");
    generateInstruction(deep+1,succes);
    output.writeln(deep,"}");
  }

  protected void buildIfThenElseWithFailure(int deep, Expression exp, Instruction succes, Instruction failure) throws IOException {
    output.write(deep,"if("); 
    generateExpression(deep,exp); 
    output.writeln(") {");
    generateInstruction(deep+1,succes);
    output.writeln(deep,"} else {");
    generateInstruction(deep+1,failure);
    output.writeln(deep,"}");
  }

  protected void buildDoWhile(int deep, Instruction succes, Expression exp) throws IOException {
    output.writeln(deep,"do {");
    generateInstruction(deep+1,succes);
    output.write(deep,"} while(");
    generateExpression(deep,exp);
    output.writeln(");");
  }

  protected void buildWhileDo(int deep, Expression exp, Instruction succes) throws IOException {
    output.write(deep,"while (");
    generateExpression(deep,exp);
    output.writeln(") {");
    generateInstruction(deep+1,succes);
    output.writeln(deep,"}");
  }

  protected void buildReturn(int deep, TomTerm exp) throws IOException {
    output.write(deep,"return ");
    generate(deep,exp);
    output.writeln(deep,";");
  }

  protected void buildExpGetHead(int deep, TomType domain, TomType codomain, TomTerm var) throws IOException {
    //output.write("((" + getTLType(codomain) + ")tom_get_head_" + getTomType(domain) + "(");
    output.write("tom_get_head_" + getTomType(domain) + "(");
    generate(deep,var);
    output.write(")");
  }

  protected void buildExpGetSubterm(int deep, TomType domain, TomType codomain, TomTerm exp, int number) throws IOException {
    TomType type = tom_make_TypesToType(tom_cons_list_concTomType(domain,tom_empty_list_concTomType()),codomain) ;
    String s = (String)getSubtermMap.get(type);
    if(s == null) {
      s = "((" + getTLType(codomain) + ")tom_get_subterm_" + getTomType(domain) + "(";
      //s = "tom_get_subterm_" + getTomType(domain) + "(";
      getSubtermMap.put(type,s);
    }
    output.write(s);
    generate(deep,exp);
    output.write(", " + number + "))");
  }

  protected void buildExpGetElement(int deep, TomType domain, TomType codomain, TomTerm varName, TomTerm varIndex) throws IOException {
    output.write("((" + getTLType(codomain) + ")tom_get_element_" + getTomType(domain) + "(");
    generate(deep,varName);
    output.write(",");
    generate(deep,varIndex);
    output.write("))");
  }
  
  protected void buildGetSubtermDecl(int deep, String name1, String name2, String type1, TomType tlType1, TomType tlType2, TargetLanguage tlCode) throws IOException {
    String args[];
    if(getInput().isStrictType()) {
      args = new String[] { getTLCode(tlType1), name1,
                            getTLCode(tlType2), name2 };
    } else {
      args = new String[] { getTLType(getUniversalType()), name1,
                            getTLCode(tlType2), name2 };
    }
    generateTargetLanguage(deep, genDecl(getTLType(getUniversalType()), "tom_get_subterm", type1,
                                         args, tlCode));
  }


  protected TargetLanguage genDeclMake(String opname, TomType returnType, 
                                       TomList argList, TargetLanguage tlCode) {
    String s = "";
    if(!getInput().isGenDecl()) {
      return tom_make_ITL("") ;
    }
      
    s = modifier + getTLType(returnType) + " tom_make_" + opname + "(";
    while(!argList.isEmpty()) {
      TomTerm arg = argList.getHead();
      matchBlock: {
         { jtom.adt.tomsignature.types.TomTerm tom_match3_1=(( jtom.adt.tomsignature.types.TomTerm)arg);{ if(tom_is_fun_sym_Variable(tom_match3_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match3_1_2=tom_get_slot_Variable_astName(tom_match3_1); { jtom.adt.tomsignature.types.TomType tom_match3_1_3=tom_get_slot_Variable_astType(tom_match3_1); if(tom_is_fun_sym_Name(tom_match3_1_2) ||  false ) { { String  tom_match3_1_2_1=tom_get_slot_Name_string(tom_match3_1_2); { String  name=tom_match3_1_2_1; if(tom_is_fun_sym_Type(tom_match3_1_3) ||  false ) { { jtom.adt.tomsignature.types.TomType tom_match3_1_3_2=tom_get_slot_Type_tlType(tom_match3_1_3); if(tom_is_fun_sym_TLType(tom_match3_1_3_2) ||  false ) { { jtom.adt.tomsignature.types.TomType tlType=tom_match3_1_3_2; 

            s += getTLCode(tlType ) + " " + name ;
            break matchBlock;
          } }} }}} }}} } 


            System.out.println("genDeclMake: strange term: " + arg);
            throw new TomRuntimeException(new Throwable("genDeclMake: strange term: " + arg));
          }} 

      }
      argList = argList.getTail();
      if(!argList.isEmpty()) {
        s += ", ";
      }
    }
    s += ") { ";
    if (getInput().isDebugMode()) {
      s += "\n"+getTLType(returnType)+ " debugVar = " + tlCode.getCode() +";\n";
      s += "jtom.debug.TomDebugger.debugger.termCreation(debugVar);\n";
      s += "return  debugVar;\n}";
    } else {
      s += "return " + tlCode.getCode() + "; }";
    }
    return tom_make_TL(s,tlCode.getStart(),tlCode.getEnd()) ;
  }

  protected TargetLanguage genDeclList(String name, TomType listType, TomType eltType) {
    //%variable
    String s = "";
    if(!getInput().isGenDecl()) {
      return tom_make_ITL("") ;
    }

    String tomType = getTomType(listType);
    String glType = getTLType(listType);
    String tlEltType = getTLType(eltType);

    String utype = glType;
    if(!getInput().isStrictType()) {
      utype = getTLType(getUniversalType());
    }
    
    String listCast = "(" + glType + ")";
    String eltCast = "(" + getTLType(eltType) + ")";
    String is_empty = "tom_is_empty_" + tomType;
    String term_equal = "tom_terms_equal_" + tomType;
    String make_insert = listCast + "tom_cons_list_" + name;
    String make_empty = listCast + "tom_empty_list_" + name;
    String get_head = eltCast + "tom_get_head_" + tomType;
    String get_tail = listCast + "tom_get_tail_" + tomType;
    String get_slice = listCast + "tom_get_slice_" + name;

    s+= modifier + utype + " tom_append_list_" + name +  "(" + utype + " l1, " + utype + " l2) {\n";
    s+= "   if(" + is_empty + "(l1)) {\n";
    s+= "    return l2;\n";  
    s+= "   } else if(" + is_empty + "(l2)) {\n";
    s+= "    return l1;\n";  
    s+= "   } else if(" + is_empty + "(" + get_tail + "(l1))) {\n";  
    s+= "    return " + make_insert + "(" + get_head + "(l1),l2);\n";
    s+= "   } else { \n";  
    s+= "    return " + make_insert + "(" + get_head + "(l1),tom_append_list_" + name +  "(" + get_tail + "(l1),l2));\n";
    s+= "   }\n";
    s+= "  }\n";
    s+= "\n";
    
    s+= modifier + utype + " tom_get_slice_" + name + "(" + utype + " begin, " + utype + " end) {\n"; 
    s+= "   if(" + term_equal + "(begin,end)) {\n";
    s+= "     return " +  make_empty + "();\n";
    s+= "   } else {\n";
    s+= "     return " +  make_insert + "(" + get_head + "(begin)," + 
      get_slice + "(" + get_tail + "(begin),end));\n";
    s+= "   }\n";
    s+= "  }\n";
    s+= "\n";
    //If necessary we remove \n code depending on --pretty option
    return ast().reworkTLCode(tom_make_ITL(s) , getInput().isPretty());
  }

  protected TargetLanguage genDeclArray(String name, TomType listType, TomType eltType) {
    String s = "";
    if(!getInput().isGenDecl()) {
      return tom_make_ITL("") ;
    }

    String tomType = getTomType(listType);
    String glType = getTLType(listType);
    String tlEltType = getTLType(eltType);
    String utype = glType;
    if(!getInput().isStrictType()) {
      utype =  getTLType(getUniversalType());
    }
    
    String listCast = "(" + glType + ")";
    String eltCast = "(" + getTLType(eltType) + ")";
    String make_empty = listCast + "tom_empty_array_" + name;
    String make_insert = listCast + "tom_cons_array_" + name;
    String get_element = eltCast + "tom_get_element_" + tomType;
    
    s = modifier + utype + " tom_get_slice_" + name +  "(" + utype + " subject, int begin, int end) {\n";
    s+= "   " + glType + " result = " + make_empty + "(end - begin);\n";
    s+= "    while( begin != end ) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(subject, begin),result);\n";
    s+= "      begin++;\n";
    s+="     }\n";
    s+= "    return result;\n";
    s+= "  }\n";
    s+= "\n";
    
    s+= modifier + utype + " tom_append_array_" + name +  "(" + utype + " l2, " + utype + " l1) {\n";
    s+= "    int size1 = tom_get_size_" + tomType + "(l1);\n";
    s+= "    int size2 = tom_get_size_" + tomType + "(l2);\n";
    s+= "    int index;\n";
    s+= "   " + glType + " result = " + make_empty + "(size1+size2);\n";

    s+= "    index=size1;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l1,(size1-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";

    s+= "    index=size2;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l2,(size2-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";
   
    s+= "    return result;\n";
    s+= "  }\n";

    //If necessary we remove \n code depending on --pretty option
    return ast().reworkTLCode(tom_make_ITL(s) , getInput().isPretty());
  }

  protected TargetLanguage genDecl(String returnType,
                                   String declName,
                                   String suffix,
                                   String args[],
                                   TargetLanguage tlCode) {
    String s = "";
    if(!getInput().isGenDecl()) {
      return tom_make_ITL("") ;
    }
    s = modifier + returnType + " " + declName + "_" + suffix + "(";
    for(int i=0 ; i<args.length ; ) {
      s+= args[i] + " " + args[i+1];
      i+=2;
      if(i<args.length) {
        s+= ", ";
      }
    } 
    s += ") { return " + tlCode.getCode() + "; }";
    if(tlCode.isTL()) {
      return tom_make_TL(s,tlCode.getStart(),tlCode.getEnd()) ;
    } else {
      return tom_make_ITL(s) ; // pas de \n donc pas besoin de reworkTL
    }
  }

  
} // class TomImperativeGenerator
