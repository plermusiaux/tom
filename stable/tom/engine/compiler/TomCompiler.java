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

package jtom.compiler;
  
import java.util.*;

import jtom.adt.tomsignature.types.*;

import jtom.runtime.Replace1;
import jtom.tools.TomTask;
import jtom.tools.TomTaskInput;
import jtom.tools.*;
import jtom.TomMessage;
import aterm.*;
import jtom.exception.TomRuntimeException;
import jtom.TomEnvironment;

public class TomCompiler extends TomTask {
  TomKernelCompiler tomKernelCompiler;
  private TomFactory tomFactory;
  private int absVarNumber = 0;

  public TomCompiler() {
    super("Tom Compiler");
    this.tomKernelCompiler = new TomKernelCompiler();
    this.tomFactory = new TomFactory();
  }
  
// ------------------------------------------------------------
  /* Generated by TOM: Do not edit this file *//* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/       /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/    /* Generated by TOM: Do not edit this file */ /*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/        
// ------------------------------------------------------------

  public void initProcess() {
  }
  
  public void process() {
    try {
      long startChrono = 0;
      boolean verbose = getInput().isVerbose(), intermediate = getInput().isIntermediate();
      if(verbose) { startChrono = System.currentTimeMillis();}
      
      TomTerm preCompiledTerm = preProcessing(environment().getTerm());
      //System.out.println("preCompiledTerm = \n" + preCompiledTerm);
      TomTerm compiledTerm = tomKernelCompiler.compileMatching(preCompiledTerm);
      
      if(verbose) {
        System.out.println("TOM compilation phase (" + (System.currentTimeMillis()-startChrono)+ " ms)");
      }
      if(intermediate) {
        Tools.generateOutput(getInput().getInputFileNameWithoutSuffix() + getInput().compiledSuffix, compiledTerm);
      }
      environment().setTerm(compiledTerm);
      
    } catch (Exception e) {
      addError("Exception occurs in TomCompiler: "+e.getMessage(), 
               getInput().getInputFile().getName(), 
               TomMessage.DEFAULT_ERROR_LINE_NUMBER, 
               TomMessage.TOM_ERROR);
      e.printStackTrace();
      return;
    }
  }

  private OptionList option() {
    return ast().makeOption();
  }

    /* 
     * preProcessing:
     * replaces MakeTerm by BuildList, BuildArray or BuildTerm
     *
     * transforms RuleSet into Function + Match + MakeTerm
     * abstract list-matching patterns
     * rename non-linear patterns
     */

  Replace1 replace_preProcessing = new Replace1() {
      public ATerm apply(ATerm subject) {
        String debugKey = "";
        if(subject instanceof TomTerm) {
           { jtom.adt.tomsignature.types.TomTerm tom_match1_1=(( jtom.adt.tomsignature.types.TomTerm)subject);{ if(tom_is_fun_sym_MakeTerm(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match1_1_1=tom_get_slot_MakeTerm_kid1(tom_match1_1); if(tom_is_fun_sym_VariableStar(tom_match1_1_1) || tom_is_fun_sym_Variable(tom_match1_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm var=tom_match1_1_1; 

              return var ;
            } }} } if(tom_is_fun_sym_MakeTerm(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match1_1_1=tom_get_slot_MakeTerm_kid1(tom_match1_1); if(tom_is_fun_sym_Appl(tom_match1_1_1) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match1_1_1_2=tom_get_slot_Appl_nameList(tom_match1_1_1); { jtom.adt.tomsignature.types.TomList tom_match1_1_1_3=tom_get_slot_Appl_args(tom_match1_1_1); if(tom_is_fun_sym_concTomName(tom_match1_1_1_2) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match1_1_1_2_list1=tom_match1_1_1_2; if(!(tom_is_empty_NameList(tom_match1_1_1_2_list1))) { { jtom.adt.tomsignature.types.TomName tom_match1_1_1_2_1=tom_get_head_NameList(tom_match1_1_1_2_list1);tom_match1_1_1_2_list1=tom_get_tail_NameList(tom_match1_1_1_2_list1); if(tom_is_fun_sym_Name(tom_match1_1_1_2_1) ||  false ) { { jtom.adt.tomsignature.types.TomName name=tom_match1_1_1_2_1; { String  tom_match1_1_1_2_1_1=tom_get_slot_Name_string(tom_match1_1_1_2_1); { String  tomName=tom_match1_1_1_2_1_1; if(tom_is_empty_NameList(tom_match1_1_1_2_list1)) { { jtom.adt.tomsignature.types.TomList termArgs=tom_match1_1_1_3; 


              TomSymbol tomSymbol = symbolTable().getSymbol(tomName );
              TomList newTermArgs = (TomList) traversal().genericTraversal(termArgs ,replace_preProcessing_makeTerm);
              if(tomSymbol==null || isDefinedSymbol(tomSymbol)) {
                return tom_make_FunctionCall(name,newTermArgs) ;
              } else {
                if(isListOperator(tomSymbol)) {
                  return tomFactory.buildList(name ,newTermArgs);
                } else if(isArrayOperator(tomSymbol)) {
                  return tomFactory.buildArray(name ,newTermArgs);
                } else {
                  return tom_make_BuildTerm(name,newTermArgs) ;
                }
              }
            } }}}} }} }} }}} }} }}} 

 // end match
        } else if(subject instanceof Instruction) {
           { jtom.adt.tomsignature.types.Instruction tom_match2_1=(( jtom.adt.tomsignature.types.Instruction)subject);{ if(tom_is_fun_sym_Match(tom_match2_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match2_1_1=tom_get_slot_Match_subjectList(tom_match2_1); { jtom.adt.tomsignature.types.TomTerm tom_match2_1_2=tom_get_slot_Match_astPatternList(tom_match2_1); { jtom.adt.tomsignature.types.OptionList tom_match2_1_3=tom_get_slot_Match_option(tom_match2_1); if(tom_is_fun_sym_SubjectList(tom_match2_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomList tom_match2_1_1_1=tom_get_slot_SubjectList_tomList(tom_match2_1_1); { jtom.adt.tomsignature.types.TomList l1=tom_match2_1_1_1; if(tom_is_fun_sym_PatternList(tom_match2_1_2) ||  false ) { { jtom.adt.tomsignature.types.TomList tom_match2_1_2_1=tom_get_slot_PatternList_tomList(tom_match2_1_2); { jtom.adt.tomsignature.types.TomList l2=tom_match2_1_2_1; { jtom.adt.tomsignature.types.OptionList matchOptionList=tom_match2_1_3; 

              TomList patternList = l2 ;
              Option orgTrack = findOriginTracking(matchOptionList );
              if(getInput().isDebugMode()) {
                debugKey = orgTrack.getFileName().getString() + orgTrack.getLine();
              }
              TomList newPatternList = empty();
              while(!patternList.isEmpty()) {
                /*
                 * the call to preProcessing performs the recursive expansion
                 * of nested match constructs
                 */
                TomTerm elt = preProcessing(patternList.getHead());
                TomTerm newPatternAction = elt;
              
                matchBlock: {
                   { jtom.adt.tomsignature.types.TomTerm tom_match3_1=(( jtom.adt.tomsignature.types.TomTerm)elt);{ if(tom_is_fun_sym_PatternAction(tom_match3_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match3_1_1=tom_get_slot_PatternAction_termList(tom_match3_1); { jtom.adt.tomsignature.types.Instruction tom_match3_1_2=tom_get_slot_PatternAction_action(tom_match3_1); { jtom.adt.tomsignature.types.OptionList tom_match3_1_3=tom_get_slot_PatternAction_option(tom_match3_1); if(tom_is_fun_sym_TermList(tom_match3_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomList tom_match3_1_1_1=tom_get_slot_TermList_tomList(tom_match3_1_1); { jtom.adt.tomsignature.types.TomList termList=tom_match3_1_1_1; { jtom.adt.tomsignature.types.Instruction actionInst=tom_match3_1_2; { jtom.adt.tomsignature.types.OptionList option=tom_match3_1_3; 

                      TomList newTermList = empty();
                      Instruction newActionInst = actionInst ;
                      /* generate equality checks */
                      ArrayList equalityCheck = new ArrayList();
                      TomList renamedTermList = linearizePattern(termList ,equalityCheck);
                      newPatternAction = tom_make_PatternAction(tom_make_TermList(renamedTermList),actionInst,option) ;        
                    
                      /* abstract patterns */
                      ArrayList abstractedPattern  = new ArrayList();
                      ArrayList introducedVariable = new ArrayList();
                      newTermList = abstractPatternList(renamedTermList, abstractedPattern, introducedVariable);

                      if(abstractedPattern.size() > 0) {
                        /* generate a new match construct */
                      
                        TomTerm generatedPatternAction =
                          tom_make_PatternAction(tom_make_TermList(ast().makeList(abstractedPattern)),newActionInst,tom_empty_list_concOption()) ;        
                        /* We reconstruct only a list of option with orgTrack and GeneratedMatch*/
                        OptionList generatedMatchOptionList = tom_cons_list_concOption(orgTrack,tom_cons_list_concOption(tom_make_GeneratedMatch(),tom_empty_list_concOption())) ;
                        Instruction generatedMatch =
                          tom_make_Match(tom_make_SubjectList(ast().makeList(introducedVariable)),tom_make_PatternList(cons(generatedPatternAction,empty())),generatedMatchOptionList) 

;
                        /*System.out.println("Generate new Match"+generatedMatch); */
                        generatedMatch = preProcessingInstruction(generatedMatch);
                        newPatternAction =
                          tom_make_PatternAction(tom_make_TermList(newTermList),generatedMatch,option) ;
                      
                        /*System.out.println("newPatternAction = " + newPatternAction); */
                      }
                      /* do nothing */
                      break matchBlock;
                    }}}} }}}} } 


                      System.out.println("preProcessing: strange PatternAction: " + elt);
                      //System.out.println("termList = " + elt.getTermList());
                      //System.out.println("tom      = " + elt.getTom()); 
                      throw new TomRuntimeException(new Throwable("preProcessing: strange PatternAction: " + elt));
                    }} 

                } // end matchBlock
              
                newPatternList = append(newPatternAction,newPatternList);
                patternList = patternList.getTail();
              }
            
              Instruction newMatch = tom_make_Match(tom_make_SubjectList(l1),tom_make_PatternList(newPatternList),matchOptionList) 

;
              return newMatch;
            }}} }}} }}}} } if(tom_is_fun_sym_RuleSet(tom_match2_1) ||  false ) { { jtom.adt.tomsignature.types.TomRuleList tom_match2_1_1=tom_get_slot_RuleSet_ruleList(tom_match2_1); { jtom.adt.tomsignature.types.Option tom_match2_1_2=tom_get_slot_RuleSet_orgTrack(tom_match2_1); if(tom_is_fun_sym_manyTomRuleList(tom_match2_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomRuleList rl=tom_match2_1_1; { jtom.adt.tomsignature.types.TomRule tom_match2_1_1_1=tom_get_slot_manyTomRuleList_head(tom_match2_1_1); if(tom_is_fun_sym_RewriteRule(tom_match2_1_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match2_1_1_1_1=tom_get_slot_RewriteRule_lhs(tom_match2_1_1_1); if(tom_is_fun_sym_Term(tom_match2_1_1_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match2_1_1_1_1_1=tom_get_slot_Term_tomTerm(tom_match2_1_1_1_1); if(tom_is_fun_sym_Appl(tom_match2_1_1_1_1_1) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match2_1_1_1_1_1_2=tom_get_slot_Appl_nameList(tom_match2_1_1_1_1_1); if(tom_is_fun_sym_concTomName(tom_match2_1_1_1_1_1_2) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match2_1_1_1_1_1_2_list1=tom_match2_1_1_1_1_1_2; if(!(tom_is_empty_NameList(tom_match2_1_1_1_1_1_2_list1))) { { jtom.adt.tomsignature.types.TomName tom_match2_1_1_1_1_1_2_1=tom_get_head_NameList(tom_match2_1_1_1_1_1_2_list1);tom_match2_1_1_1_1_1_2_list1=tom_get_tail_NameList(tom_match2_1_1_1_1_1_2_list1); if(tom_is_fun_sym_Name(tom_match2_1_1_1_1_1_2_1) ||  false ) { { String  tom_match2_1_1_1_1_1_2_1_1=tom_get_slot_Name_string(tom_match2_1_1_1_1_1_2_1); { String  tomName=tom_match2_1_1_1_1_1_2_1_1; if(tom_is_empty_NameList(tom_match2_1_1_1_1_1_2_list1)) { { jtom.adt.tomsignature.types.Option orgTrack=tom_match2_1_2; 


              TomRuleList ruleList = rl ;
              if(getInput().isDebugMode()) {
                debugKey = orgTrack .getFileName().getString() + orgTrack .getLine();
              }
              TomSymbol tomSymbol = symbolTable().getSymbol(tomName );
              TomName name = tomSymbol.getAstName();
              TomTypeList typesList = tomSymbol.getTypesToType().getDomain();        
              TomNumberList path = tsf().makeTomNumberList();
              TomList matchArgumentsList = empty();
              TomList patternActionList  = empty();
              TomTerm variable;
              int index = 0;
            
              path = (TomNumberList) path.append(tom_make_RuleVar() );
            
              while(!typesList.isEmpty()) {
                TomType subtermType = typesList.getHead();
                variable = tom_make_Variable(option(),tom_make_PositionName(appendNumber(index,path)),subtermType,tom_empty_list_concConstraint()) ;
                matchArgumentsList = append(variable,matchArgumentsList);
                typesList = typesList.getTail();
                index++;
              }
            
              boolean hasDefaultCase = false;
              while(!ruleList.isEmpty()) {
                TomRule rule = ruleList.getHead();
                 { jtom.adt.tomsignature.types.TomRule tom_match4_1=(( jtom.adt.tomsignature.types.TomRule)rule);{ if(tom_is_fun_sym_RewriteRule(tom_match4_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match4_1_1=tom_get_slot_RewriteRule_lhs(tom_match4_1); { jtom.adt.tomsignature.types.TomTerm tom_match4_1_2=tom_get_slot_RewriteRule_rhs(tom_match4_1); { jtom.adt.tomsignature.types.InstructionList tom_match4_1_3=tom_get_slot_RewriteRule_condList(tom_match4_1); { jtom.adt.tomsignature.types.OptionList tom_match4_1_4=tom_get_slot_RewriteRule_option(tom_match4_1); if(tom_is_fun_sym_Term(tom_match4_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match4_1_1_1=tom_get_slot_Term_tomTerm(tom_match4_1_1); if(tom_is_fun_sym_Appl(tom_match4_1_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomList tom_match4_1_1_1_3=tom_get_slot_Appl_args(tom_match4_1_1_1); { jtom.adt.tomsignature.types.TomList matchPatternsList=tom_match4_1_1_1_3; if(tom_is_fun_sym_Term(tom_match4_1_2) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match4_1_2_1=tom_get_slot_Term_tomTerm(tom_match4_1_2); { jtom.adt.tomsignature.types.TomTerm rhsTerm=tom_match4_1_2_1; { jtom.adt.tomsignature.types.InstructionList condList=tom_match4_1_3; { jtom.adt.tomsignature.types.OptionList option=tom_match4_1_4; 




                  
                    TomTerm newRhs = preProcessing(tom_make_MakeTerm(rhsTerm) );
                    Instruction rhsInst = tom_make_Return(newRhs) ;
                    if(getInput().isDebugMode()) {
                      TargetLanguage tl = tsf().makeTargetLanguage_ITL("jtom.debug.TomDebugger.debugger.patternSuccess(\""+debugKey+"\");\n");
                      rhsInst = tom_make_UnamedBlock(tom_cons_list_concInstruction(tom_make_TargetLanguageToInstruction(tl),tom_cons_list_concInstruction(rhsInst,tom_empty_list_concInstruction()))) ;
                    }
                    Instruction newRhsInst = buildCondition(condList,rhsInst) ;
                  
                    patternActionList = append(tom_make_PatternAction(tom_make_TermList(matchPatternsList),newRhsInst,option) ,patternActionList);
                    hasDefaultCase = hasDefaultCase || (isDefaultCase(matchPatternsList) && condList.isEmpty());
                  }}}} }}} }} }}}}} }}} 
 
                ruleList = ruleList.getTail();
              }
            
              TomTerm subjectListAST = tom_make_SubjectList(matchArgumentsList) ;
              Instruction makeFunctionBeginAST = tom_make_MakeFunctionBegin(name,subjectListAST) ;
              ArrayList optionList = new ArrayList();
              optionList.add(orgTrack );
              //optionList.add(tsf().makeOption_GeneratedMatch());
              OptionList generatedOptions = ast().makeOptionList(optionList);
              Instruction matchAST = tom_make_Match(tom_make_SubjectList(matchArgumentsList),tom_make_PatternList(patternActionList),generatedOptions) 

;

              Instruction buildAST;
              if(hasDefaultCase) {
                buildAST = tom_make_Nop() ;
              } else {
                buildAST = tom_make_Return(tom_make_BuildTerm(name,(TomList)traversal().genericTraversal(matchArgumentsList,replace_preProcessing_makeTerm))) ;
              }

              InstructionList l;
              if(getInput().isECode()) {
                l = tom_cons_list_concInstruction(makeFunctionBeginAST,tom_cons_list_concInstruction(tom_make_LocalVariable(),tom_cons_list_concInstruction(tom_make_EndLocalVariable(),tom_cons_list_concInstruction(matchAST,tom_cons_list_concInstruction(buildAST,tom_cons_list_concInstruction(tom_make_MakeFunctionEnd(),tom_empty_list_concInstruction())))))) 






;
              } else {
                l = tom_cons_list_concInstruction(makeFunctionBeginAST,tom_cons_list_concInstruction(matchAST,tom_cons_list_concInstruction(buildAST,tom_cons_list_concInstruction(tom_make_MakeFunctionEnd(),tom_empty_list_concInstruction())))) 




;
              }
            
              return preProcessingInstruction(tom_make_AbstractBlock(l) );
            } }}} }} }} }} }} }} }}} }}} }}} 

 // end match

        } // end instanceof Instruction

          /*
           * Defaul case: traversal
           */
        return traversal().genericTraversal(subject,this);
      } // end apply
    };
  
  private boolean isDefaultCase(TomList l) {
     { jtom.adt.tomsignature.types.TomList tom_match5_1=(( jtom.adt.tomsignature.types.TomList)l);{ if(tom_is_fun_sym_emptyTomList(tom_match5_1) ||  false ) { 

        return true;
       } if(tom_is_fun_sym_manyTomList(tom_match5_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match5_1_1=tom_get_slot_manyTomList_head(tom_match5_1); { jtom.adt.tomsignature.types.TomList tom_match5_1_2=tom_get_slot_manyTomList_tail(tom_match5_1); if(tom_is_fun_sym_UnamedVariableStar(tom_match5_1_1) || tom_is_fun_sym_UnamedVariable(tom_match5_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomList tail=tom_match5_1_2; 

        return isDefaultCase(tail);
      } }}} } if(tom_is_fun_sym_manyTomList(tom_match5_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match5_1_1=tom_get_slot_manyTomList_head(tom_match5_1); { jtom.adt.tomsignature.types.TomList tom_match5_1_2=tom_get_slot_manyTomList_tail(tom_match5_1); if(tom_is_fun_sym_VariableStar(tom_match5_1_1) || tom_is_fun_sym_Variable(tom_match5_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomList tail=tom_match5_1_2; 

        return isDefaultCase(tail);
      } }}} }}} 

    return false;
  }

  Replace1 replace_preProcessing_makeTerm = new Replace1() {
      public ATerm apply(ATerm t) {
        return preProcessing(tom_make_MakeTerm((TomTerm)t) );
      }
    }; 

  private TomTerm preProcessing(TomTerm subject) {
      //System.out.println("preProcessing subject: " + subject);
    return (TomTerm) replace_preProcessing.apply(subject); 
  }
  
  private Instruction preProcessingInstruction(Instruction subject) {
      //System.out.println("preProcessing subject: " + subject);
    return (Instruction) replace_preProcessing.apply(subject); 
  }

 
  private Instruction buildCondition(InstructionList condList, Instruction action) {
     { jtom.adt.tomsignature.types.InstructionList tom_match6_1=(( jtom.adt.tomsignature.types.InstructionList)condList);{ if(tom_is_fun_sym_emptyInstructionList(tom_match6_1) ||  false ) { 
 return action;  } if(tom_is_fun_sym_manyInstructionList(tom_match6_1) ||  false ) { { jtom.adt.tomsignature.types.Instruction tom_match6_1_1=tom_get_slot_manyInstructionList_head(tom_match6_1); { jtom.adt.tomsignature.types.InstructionList tom_match6_1_2=tom_get_slot_manyInstructionList_tail(tom_match6_1); if(tom_is_fun_sym_MatchingCondition(tom_match6_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match6_1_1_1=tom_get_slot_MatchingCondition_lhs(tom_match6_1_1); { jtom.adt.tomsignature.types.TomTerm tom_match6_1_1_2=tom_get_slot_MatchingCondition_rhs(tom_match6_1_1); { jtom.adt.tomsignature.types.TomTerm pattern=tom_match6_1_1_1; { jtom.adt.tomsignature.types.TomTerm subject=tom_match6_1_1_2; { jtom.adt.tomsignature.types.InstructionList tail=tom_match6_1_2; 


        Instruction newAction = buildCondition(tail,action) ;

        TomType subjectType = getTermType(pattern );
        TomNumberList path = tsf().makeTomNumberList();
        path = (TomNumberList) path.append(tom_make_RuleVar() );
        TomTerm newSubject = preProcessing(tom_make_MakeTerm(subject) );
        TomTerm introducedVariable = newSubject;
        TomTerm generatedPatternAction =
          tom_make_PatternAction(tom_make_TermList(cons(pattern,empty())),newAction,option()) ;        

          // Warning: The options are not good
        Instruction generatedMatch =
          tom_make_Match(tom_make_SubjectList(cons(introducedVariable,empty())),tom_make_PatternList(cons(generatedPatternAction,empty())),option()) 

;
        return generatedMatch;
      }}}}} }}} } if(tom_is_fun_sym_manyInstructionList(tom_match6_1) ||  false ) { { jtom.adt.tomsignature.types.Instruction tom_match6_1_1=tom_get_slot_manyInstructionList_head(tom_match6_1); { jtom.adt.tomsignature.types.InstructionList tom_match6_1_2=tom_get_slot_manyInstructionList_tail(tom_match6_1); if(tom_is_fun_sym_EqualityCondition(tom_match6_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match6_1_1_1=tom_get_slot_EqualityCondition_lhs(tom_match6_1_1); { jtom.adt.tomsignature.types.TomTerm tom_match6_1_1_2=tom_get_slot_EqualityCondition_rhs(tom_match6_1_1); { jtom.adt.tomsignature.types.TomTerm lhs=tom_match6_1_1_1; { jtom.adt.tomsignature.types.TomTerm rhs=tom_match6_1_1_2; { jtom.adt.tomsignature.types.InstructionList tail=tom_match6_1_2; 


        Instruction newAction = buildCondition(tail,action) ;

        TomTerm newLhs = preProcessing(tom_make_MakeTerm(lhs) );
        TomTerm newRhs = preProcessing(tom_make_MakeTerm(rhs) );
        Expression equality = tom_make_EqualTerm(newLhs,newRhs) ;
        Instruction generatedTest = tom_make_IfThenElse(equality,newAction,tom_make_Nop()) ;
        return generatedTest;
      }}}}} }}} } 


        throw new TomRuntimeException(new Throwable("buildCondition strange term: " + condList));
      }} 

  }
  
  private TomTerm renameVariable(TomTerm subject,
                                 Map multiplicityMap,
                                 ArrayList equalityCheck) {
    TomTerm renamedTerm = subject;
    
     { jtom.adt.tomsignature.types.TomTerm tom_match7_1=(( jtom.adt.tomsignature.types.TomTerm)subject);{ if(tom_is_fun_sym_UnamedVariableStar(tom_match7_1) || tom_is_fun_sym_UnamedVariable(tom_match7_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm var=tom_match7_1; { jtom.adt.tomsignature.types.ConstraintList tom_match7_1_3=tom_get_slot_UnamedVariable_constraints(tom_match7_1); { jtom.adt.tomsignature.types.ConstraintList constraints=tom_match7_1_3; 

        ConstraintList newConstraintList = renameVariableInConstraintList(constraints,multiplicityMap,equalityCheck) ;
        return var .setConstraints(newConstraintList);
      }}} } if(tom_is_fun_sym_VariableStar(tom_match7_1) || tom_is_fun_sym_Variable(tom_match7_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm var=tom_match7_1; { jtom.adt.tomsignature.types.TomName tom_match7_1_2=tom_get_slot_Variable_astName(tom_match7_1); { jtom.adt.tomsignature.types.ConstraintList tom_match7_1_4=tom_get_slot_Variable_constraints(tom_match7_1); { jtom.adt.tomsignature.types.TomName name=tom_match7_1_2; { jtom.adt.tomsignature.types.ConstraintList clist=tom_match7_1_4; 


        ConstraintList newConstraintList = renameVariableInConstraintList(clist ,multiplicityMap,equalityCheck);
        if(!multiplicityMap.containsKey(name )) {
          // We see this variable for the first time
          multiplicityMap.put(name ,new Integer(1));
          renamedTerm = var .setConstraints(newConstraintList);
        } else {
          // We have already seen this variable
          Integer multiplicity = (Integer) multiplicityMap.get(name );
          int mult = multiplicity.intValue(); 
          multiplicityMap.put(name ,new Integer(mult+1));
          
          TomNumberList path = tsf().makeTomNumberList();
          path = (TomNumberList) path.append(tom_make_RenamedVar(name) );
          path = (TomNumberList) path.append(makeNumber(mult));

          renamedTerm = var .setAstName(tom_make_PositionName(path) );
          renamedTerm = renamedTerm.setConstraints(tom_cons_list_concConstraint(tom_make_Equal(var.setConstraints(tom_empty_list_concConstraint())),tom_append_list_concConstraint(newConstraintList,tom_empty_list_concConstraint())) );
        }

        return renamedTerm;
      }}}}} } if(tom_is_fun_sym_Appl(tom_match7_1) ||  false ) { { jtom.adt.tomsignature.types.OptionList tom_match7_1_1=tom_get_slot_Appl_option(tom_match7_1); { jtom.adt.tomsignature.types.NameList tom_match7_1_2=tom_get_slot_Appl_nameList(tom_match7_1); { jtom.adt.tomsignature.types.TomList tom_match7_1_3=tom_get_slot_Appl_args(tom_match7_1); { jtom.adt.tomsignature.types.ConstraintList tom_match7_1_4=tom_get_slot_Appl_constraints(tom_match7_1); { jtom.adt.tomsignature.types.OptionList optionList=tom_match7_1_1; { jtom.adt.tomsignature.types.NameList nameList=tom_match7_1_2; { jtom.adt.tomsignature.types.TomList arguments=tom_match7_1_3; { jtom.adt.tomsignature.types.ConstraintList constraints=tom_match7_1_4; 


        TomList args = arguments ;
        TomList newArgs = empty();
        while(!args.isEmpty()) {
          TomTerm elt = args.getHead();
          TomTerm newElt = renameVariable(elt,multiplicityMap,equalityCheck);
          newArgs = append(newElt,newArgs);
          args = args.getTail();
        }
        ConstraintList newConstraintList = renameVariableInConstraintList(constraints ,multiplicityMap,equalityCheck);
        renamedTerm = tom_make_Appl(optionList,nameList,newArgs,newConstraintList) ;
        return renamedTerm;
      }}}}}}}} }}} 

    return renamedTerm;
  }

  private ConstraintList renameVariableInConstraintList(ConstraintList constraintList,
                                                Map multiplicityMap,
                                                ArrayList equalityCheck) {
    ArrayList list = new ArrayList();
    while(!constraintList.isEmpty()) {
      Constraint cstElt = constraintList.getHead();
      Constraint newCstElt = cstElt;
       { jtom.adt.tomsignature.types.Constraint tom_match8_1=(( jtom.adt.tomsignature.types.Constraint)cstElt);{ if(tom_is_fun_sym_AssignTo(tom_match8_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm tom_match8_1_1=tom_get_slot_AssignTo_variable(tom_match8_1); if(tom_is_fun_sym_Variable(tom_match8_1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm var=tom_match8_1_1; 

          newCstElt = tom_make_AssignTo(renameVariable(var,multiplicityMap,equalityCheck)) ;
        } }} }}} 

      list.add(newCstElt);
      constraintList = constraintList.getTail();
    }
    return ast().makeConstraintList(list);
  }

  private TomList linearizePattern(TomList subject, ArrayList equalityCheck) {
    Map multiplicityMap = new HashMap();
      // perform the renaming and generate equality checks
    TomList newList = empty();
    while(!subject.isEmpty()) {
      TomTerm elt = subject.getHead();
      TomTerm newElt = renameVariable(elt,multiplicityMap,equalityCheck);
      newList = append(newElt,newList);
      subject = subject.getTail();
    }
    return newList;
  }
  
  private TomTerm abstractPattern(TomTerm subject,
                                  ArrayList abstractedPattern,
                                  ArrayList introducedVariable)  {
    TomTerm abstractedTerm = subject;
     { jtom.adt.tomsignature.types.TomTerm tom_match9_1=(( jtom.adt.tomsignature.types.TomTerm)subject);{ if(tom_is_fun_sym_Appl(tom_match9_1) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match9_1_2=tom_get_slot_Appl_nameList(tom_match9_1); { jtom.adt.tomsignature.types.TomList tom_match9_1_3=tom_get_slot_Appl_args(tom_match9_1); if(tom_is_fun_sym_concTomName(tom_match9_1_2) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match9_1_2_list1=tom_match9_1_2; if(!(tom_is_empty_NameList(tom_match9_1_2_list1))) { { jtom.adt.tomsignature.types.TomName tom_match9_1_2_1=tom_get_head_NameList(tom_match9_1_2_list1);tom_match9_1_2_list1=tom_get_tail_NameList(tom_match9_1_2_list1); if(tom_is_fun_sym_Name(tom_match9_1_2_1) ||  false ) { { String  tom_match9_1_2_1_1=tom_get_slot_Name_string(tom_match9_1_2_1); { String  tomName=tom_match9_1_2_1_1; { jtom.adt.tomsignature.types.TomList arguments=tom_match9_1_3; 

        TomList args = arguments ;
        TomSymbol tomSymbol = symbolTable().getSymbol(tomName );
        
        TomList newArgs = empty();
        if(isListOperator(tomSymbol) || isArrayOperator(tomSymbol)) {
          while(!args.isEmpty()) {
            TomTerm elt = args.getHead();
            TomTerm newElt = elt;
             { jtom.adt.tomsignature.types.TomTerm tom_match10_1=(( jtom.adt.tomsignature.types.TomTerm)elt);{ if(tom_is_fun_sym_Appl(tom_match10_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm appl=tom_match10_1; { jtom.adt.tomsignature.types.NameList tom_match10_1_2=tom_get_slot_Appl_nameList(tom_match10_1); if(tom_is_fun_sym_concTomName(tom_match10_1_2) ||  false ) { { jtom.adt.tomsignature.types.NameList tom_match10_1_2_list1=tom_match10_1_2; if(!(tom_is_empty_NameList(tom_match10_1_2_list1))) { { jtom.adt.tomsignature.types.TomName tom_match10_1_2_1=tom_get_head_NameList(tom_match10_1_2_list1);tom_match10_1_2_list1=tom_get_tail_NameList(tom_match10_1_2_list1); if(tom_is_fun_sym_Name(tom_match10_1_2_1) ||  false ) { { String  tom_match10_1_2_1_1=tom_get_slot_Name_string(tom_match10_1_2_1); { String  tomName2=tom_match10_1_2_1_1; 

                /*
                 * we no longer abstract syntactic subterm
                 * they are compiled by the TomKernelCompiler
                 */

                  //System.out.println("Abstract: " + appl);
                TomSymbol tomSymbol2 = symbolTable().getSymbol(tomName2 );
                if(isListOperator(tomSymbol2) || isArrayOperator(tomSymbol2)) {
                  TomType type2 = tomSymbol2.getTypesToType().getCodomain();
                  abstractedPattern.add(appl );
                  
                  TomNumberList path = tsf().makeTomNumberList();
                  //path = append(`AbsVar(makeNumber(introducedVariable.size())),path);
                  absVarNumber++;
                  path = (TomNumberList) path.append(tom_make_AbsVar(makeNumber(absVarNumber)) );
                  
                  TomTerm newVariable = tom_make_Variable(option(),tom_make_PositionName(path),type2,tom_empty_list_concConstraint()) ;
                  
                  //System.out.println("newVariable = " + newVariable);
                  
                  introducedVariable.add(newVariable);
                  newElt = newVariable;
                }
              }} }} }} }}} }}} 

            newArgs = append(newElt,newArgs);
            args = args.getTail();
          }
        } else {
          newArgs = abstractPatternList(args,abstractedPattern,introducedVariable);
        }
        abstractedTerm = subject.setArgs(newArgs);
      }}} }} }} }}} }}} 
 // end match
    return abstractedTerm;
  }

  private TomList abstractPatternList(TomList subjectList,
                                      ArrayList abstractedPattern,
                                      ArrayList introducedVariable)  {
    TomList newList = empty();
    while(!subjectList.isEmpty()) {
      TomTerm elt = subjectList.getHead();
      TomTerm newElt = abstractPattern(elt,abstractedPattern,introducedVariable);
      newList = append(newElt,newList);
      subjectList = subjectList.getTail();
    }
    return newList;
  }
  
} //class TomCompiler
