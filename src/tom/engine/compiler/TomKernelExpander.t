/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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

package tom.engine.compiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import tom.engine.TomBase;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.exception.TomRuntimeException;
import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;
import tom.library.traversal.Replace2;
import aterm.ATerm;

public class TomKernelExpander extends TomBase {

  private SymbolTable symbolTable;

  public TomKernelExpander() {
    super();
  }

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  private SymbolTable getSymbolTable() {
    return symbolTable;
  }

  protected TomSymbol getSymbolFromName(String tomName) {
    return getSymbolFromName(tomName, getSymbolTable());
  }

  protected TomSymbol getSymbolFromType(TomType tomType) {
    return getSymbolFromType(tomType, getSymbolTable());
  }
  // ------------------------------------------------------------
  %include { adt/tomsignature/TomSignature.tom }
  // ------------------------------------------------------------

  /*
   * The "expandVariable" phase expands RecordAppl into Variable
   * we focus on
   * - RewriteRule
   * - MatchingCondition
   * - EqualityCondition
   * - Match
   *
   * Variable and TermAppl are are expanded in the TomTerm case
   */

  protected Replace2 replace_expandVariable = new Replace2() {
      public ATerm apply(ATerm subject, Object arg1) {
        TomTerm contextSubject = (TomTerm)arg1;

        if(contextSubject == null) {
          throw new TomRuntimeException("expandVariable: null contextSubject");
        }

				//System.out.println("expandVariable: " + subject );
				//System.out.println("contextSubject: " + contextSubject);
				%match(Option subject) {
					OriginTracking[] -> { return subject; }
				}
				%match(TargetLanguage subject) {
					TL[] -> { return subject; }
					ITL[] -> { return subject; }
					Comment[] -> { return subject; }
				}
				%match(TomType subject) {
					TomTypeAlone(tomType) -> {
						TomType type = getType(`tomType);
						if(type != null) {
							return type;
						} else {
							return subject; // useful for TomTypeAlone("unknown type")
						}
					}
				}
				%match(TomRule subject) {
					RewriteRule(Term(lhs@RecordAppl[nameList=(Name(tomName))]),
							Term(rhs),
							condList,
							option)  -> {
						TomSymbol tomSymbol = getSymbolFromName(`tomName);
						TomType symbolType = getSymbolCodomain(tomSymbol);
						TomTerm newLhs = `Term(expandVariable(contextSubject,lhs));
						// build the list of variables that occur in the lhs
						HashSet set = new HashSet();
						collectVariable(set,newLhs);
						TomList varList = ASTFactory.makeList(set);
						InstructionList newCondList = `emptyInstructionList();
						while(!`condList.isEmpty()) {
							Instruction cond = `condList.getHead();

							Instruction newCond = replaceInstantiatedVariableInstruction(`varList,cond);
							newCond = expandVariableInstruction(contextSubject,newCond);

							newCondList = `manyInstructionList(newCond,newCondList);
							collectVariable(set,newCond);
							varList = ASTFactory.makeList(set);
							`condList = `condList.getTail();
						}

						TomTerm newRhs = replaceInstantiatedVariable(`varList,`rhs);
						newRhs = `Term(expandVariable(TomTypeToTomTerm(symbolType),newRhs));

						return `RewriteRule(newLhs,newRhs,newCondList,option);
					}
				} // end match
				%match(TomVisit subject) {
					VisitTerm(type,patternInstructionList,options) -> {
						TomType newType = `expandType(contextSubject,type);
						PatternInstructionList newPatternInstructionList = `expandVariablePatternInstructionList(TomTypeToTomTerm(newType),patternInstructionList);
						return `VisitTerm(newType, newPatternInstructionList,options);
					}
				} //end match
				%match(Instruction subject) {
					MatchingCondition[lhs=lhs@Variable[astName=Name(_), astType=lhsType],
						                rhs=rhs@Variable[astName=Name(_), astType=rhsType]] -> {
							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(rhsType),lhs);
							return `MatchingCondition(newLhs,rhs);
						}

					MatchingCondition[lhs=lhs@RecordAppl[nameList=(Name(lhsName))],
						                rhs=rhs@Variable[astName=Name(_), astType=rhsType]] -> {
							TomSymbol lhsSymbol = getSymbolFromName(`lhsName);
							TomType type;
							if(lhsSymbol != null) {
								type = getSymbolCodomain(lhsSymbol);
							} else {
								throw new TomRuntimeException("lhs has an unknown sort: " + `lhsName);
							}

							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(type),lhs);
							TomTerm newRhs = `expandVariable(TomTypeToTomTerm(type),rhs);
							return `MatchingCondition(newLhs,newRhs);
						}
					
					MatchingCondition[lhs=lhs@Variable[astName=Name(_), astType=lhsType],
						                rhs=rhs@RecordAppl[nameList=(Name(rhsName))]] -> {
							TomSymbol rhsSymbol = getSymbolFromName(`rhsName);
							TomType type;
							if(rhsSymbol != null) {
								type = getSymbolCodomain(rhsSymbol);
							} else {
								throw new TomRuntimeException("rhs has an unknown sort: " + `rhsName);
							}

							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(type),lhs);
							TomTerm newRhs = `expandVariable(TomTypeToTomTerm(type),rhs);
							return `MatchingCondition(newLhs,newRhs);
						}

					MatchingCondition[lhs=lhs@RecordAppl[nameList=(Name(lhsName),_*)],
						                rhs=rhs@RecordAppl[nameList=(Name(rhsName))]] -> {
							TomSymbol lhsSymbol = getSymbolFromName(`lhsName);
							TomSymbol rhsSymbol = getSymbolFromName(`rhsName);
							TomType type;
							// rhs is an application
							if(lhsSymbol != null) {
								type = getSymbolCodomain(lhsSymbol);
							} else if(rhsSymbol != null) {
								type = getSymbolCodomain(rhsSymbol);
							} else {
								// lhs is a variable, but rhs has an unknown top symbol
								// since lhs is a fresh variable, we look for rhs type
								throw new TomRuntimeException("rhs has an unknown sort: " + `rhsName);
							}

							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(type),lhs);
							TomTerm newRhs = `expandVariable(TomTypeToTomTerm(type),rhs);
							return `MatchingCondition(newLhs,newRhs);
						}

					EqualityCondition[lhs=lhs@Variable[astName=Name(_), astType=type],
						rhs=rhs@Variable[astName=Name(_), astType=type]] -> {
							return `TypedEqualityCondition(type,lhs,rhs);
						}

					EqualityCondition[lhs=lhs@Variable[astName=Name(_), astType=type],
						rhs=rhs@RecordAppl[nameList=(Name(_))]] -> {
							TomTerm newRhs = `expandVariable(TomTypeToTomTerm(type),rhs);
							return `TypedEqualityCondition(type,lhs,newRhs);
						}

					EqualityCondition[lhs=lhs@RecordAppl[nameList=(Name(_))],
						rhs=rhs@Variable[astName=Name(_), astType=type]] -> {
							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(type),lhs);
							return `TypedEqualityCondition(type,newLhs,rhs);
						}

					EqualityCondition[lhs=lhs@RecordAppl[nameList=(Name(lhsName))],
						rhs=rhs@RecordAppl[nameList=(Name(rhsName))]] -> {
							TomSymbol lhsSymbol = getSymbolFromName(`lhsName);
							TomSymbol rhsSymbol = getSymbolFromName(`rhsName);
							TomType type;

							if(lhsSymbol != null) {
								type = getSymbolCodomain(lhsSymbol);
							} else if(rhsSymbol != null) {
								type = getSymbolCodomain(rhsSymbol);
							} else {
								// lhs and rhs have an unknown top symbol
								throw new TomRuntimeException("lhs and rhs have an unknown sort: " + `lhsName + ",  " + `rhsName);
							}

							//System.out.println("EqualityCondition type = " + type);

							TomTerm newLhs = `expandVariable(TomTypeToTomTerm(type),lhs);
							TomTerm newRhs = `expandVariable(TomTypeToTomTerm(type),rhs);

							//System.out.println("lhs    = " + lhs);
							//System.out.println("newLhs = " + newLhs);

							return `TypedEqualityCondition(type,newLhs,newRhs);
						}

					Match(tomSubjectList,patternInstructionList, option) -> {
						//System.out.println("tomSubjectList = " + tomSubjectList);
						TomTerm newSubjectList = expandVariable(contextSubject,`tomSubjectList);
						//System.out.println("newSubjectList = " + newSubjectList);
						PatternInstructionList newPatternInstructionList = expandVariablePatternInstructionList(newSubjectList,`patternInstructionList);
						return `Match(newSubjectList,newPatternInstructionList, option);
					}
				} // end match
				%match(TomTerm contextSubject, Pattern subject) {
					SubjectList(l1), Pattern(subjectList,termList, guardList) -> {
						//System.out.println("expandVariable.9: "+l1+"(" + termList + ")");

						// process a list of subterms
						ArrayList list = new ArrayList();
						while(!`termList.isEmpty()) {
							list.add(expandVariable(`l1.getHead(), `termList.getHead()));
							`termList = `termList.getTail();
							`l1 = `l1.getTail();
						}
						TomList newTermList = ASTFactory.makeList(list);

						// process a list of guards
						list.clear();
						// build the list of variables that occur in the lhs
						HashSet set = new HashSet();
						collectVariable(set,newTermList);
						TomList varList = ASTFactory.makeList(set);
						//System.out.println("varList = " + varList);
						while(!`guardList.isEmpty()) {
							list.add(replaceInstantiatedVariable(`varList, `guardList.getHead()));
							`guardList = `guardList.getTail();
						}
						TomList newGuardList = ASTFactory.makeList(list);
						//System.out.println("newGuardList = " + newGuardList);
						return `Pattern(subjectList,newTermList,newGuardList);
					}
				} // end match
				%match(TomTerm contextSubject, TomTerm subject) {
					_, RecordAppl[option=option,nameList=nameList@(Name(tomName),_*),slots=slotList,constraints=constraints] -> {
						TomSymbol tomSymbol = null;
						if(`tomName.equals("")) {
							if(contextSubject.hasAstType()) {
								tomSymbol = getSymbolFromType(contextSubject.getAstType());
								if(tomSymbol==null) {
									throw new TomRuntimeException("No symbol found for type '" + contextSubject.getAstType() + "'");
								}
								`nameList = `concTomName(tomSymbol.getAstName());
							}
						} else {
							tomSymbol = getSymbolFromName(`tomName);
						}

						if(tomSymbol != null) {
							SlotList subterm = expandVariableList(tomSymbol, `slotList);
							ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(getSymbolCodomain(tomSymbol)),`constraints);
							return `RecordAppl(option,nameList,subterm,newConstraints);
						} else {
							%match(TomTerm contextSubject) {
								TomTypeToTomTerm(type@Type[]) -> {
									SlotList subterm = expandVariableList(`emptySymbol(), `slotList);
									ConstraintList newConstraints = expandVariableConstraintList(`emptyTerm(),`constraints);
									return `RecordAppl(option,nameList,subterm,newConstraints);
								}
								Variable[astType=type] -> {
									ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(type),`constraints);
									SlotList subterm = expandVariableList(`emptySymbol(), `slotList);
									return `RecordAppl(option,nameList,subterm,newConstraints);
								}
								Tom(concTomTerm(_*,var@Variable[astName=Name(varName)],_*)) -> {
									ConstraintList newConstraints = expandVariableConstraintList(`contextSubject,`constraints);
									SlotList subterm = expandVariableList(`emptySymbol(), `slotList);
									return `RecordAppl(option,nameList,subterm,newConstraints);
								}

								_ -> {
									// do nothing

									//System.out.println("contextSubject = " + contextSubject);
									//System.out.println("subject        = " + subject);

								}
							}
						}
					}


					context@TomTypeToTomTerm(type@Type[]), Variable[option=option,astName=astName,astType=TomTypeAlone[],constraints=constraints] -> {
						// create a variable
						return `Variable(option,astName,type,expandVariableConstraintList(context,constraints));
					}
					
					Variable[astType=type1] , Variable[option=option,astName=astName,astType=TomTypeAlone[],constraints=constraints] -> {
						ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(type1),`constraints);
						// create a variable
						return `Variable(option,astName,type1,newConstraints);
					}

					_, Variable[option=option,astName=Name(strName),astType=TomTypeAlone(tomType),constraints=constraints] -> {
						// create a variable
						TomType localType = getType(`tomType);
						if(localType != null) {
							return `Variable(option,Name(strName),localType,constraints);
						} else {
							// do nothing
						}
					}

					TomTypeToTomTerm(type@Type[]) ,Placeholder[option=option,constraints=constraints] -> {
						ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(type),`constraints);
						// create an unamed variable
						return `UnamedVariable(option,type,newConstraints);
					}

					Variable[astType=type1] , Placeholder[option=option,constraints=constraints] -> {
						ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(type1),`constraints);
						// create an unamed variable
						return `UnamedVariable(option,type1,newConstraints);
					}

					_, TLVar(strName,TomTypeAlone(tomType)) -> {
						// create a variable: its type is ensured by checker
						TomType localType = getType(`tomType);
						OptionList option = ASTFactory.makeOption();
						return `Variable(option,Name(strName),localType,concConstraint());
					}

					_, TLVar(strName,localType@Type[]) -> {
						// create a variable: its type is ensured by checker
						OptionList option = ASTFactory.makeOption();
						return `Variable(option,Name(strName),localType,concConstraint());
					}

				} // end match
				//System.out.println("TomKernelCompiler.expandVariable default:\n\t" + subject );
				//System.out.println("subject: " + subject);
				//System.out.println("context: " + contextSubject);
        return traversal().genericTraversal(subject,this,contextSubject);
      } // end apply
    }; // end new

  protected TomTerm expandVariable(TomTerm contextSubject, TomTerm subject) {
    return (TomTerm) replace_expandVariable.apply(subject,contextSubject);
  }

  protected TomType expandType(TomTerm contextSubject, TomType subject) {
    return (TomType) replace_expandVariable.apply(subject,contextSubject);
  }

  private Instruction expandVariableInstruction(TomTerm contextSubject, Instruction subject) {
    return (Instruction) replace_expandVariable.apply(subject,contextSubject);
  }

  protected ConstraintList expandVariableConstraintList(TomTerm contextSubject, ConstraintList subject) {
    return (ConstraintList) replace_expandVariable.apply(subject,contextSubject);
  }

  protected PatternInstructionList expandVariablePatternInstructionList(TomTerm contextSubject, PatternInstructionList subject) {
    return (PatternInstructionList) replace_expandVariable.apply(subject,contextSubject);
  }

  private TomType getTypeFromVariableList(TomName name, TomList list) {

    //System.out.println("name = " + name);
    //System.out.println("list = " + list);

    %match(TomName name,TomList list) {
      _,emptyTomList() -> {
         System.out.println("getTypeFromVariableList. Stange case '" + name + "' not found");
         throw new TomRuntimeException("getTypeFromVariableList. Stange case '" + name + "' not found");
       }

      varName, manyTomList(Variable[astName=varName,astType=type@Type[]],_) -> { return `type; }
      varName, manyTomList(VariableStar[astName=varName,astType=type@Type[]],_) -> { return `type; }
      _, manyTomList(_,tail) -> { return getTypeFromVariableList(name,`tail); }

    }
    return null;
  }

  /*
   * perform type inference of subterms (subtermList)
   * under a given operator (symbol)
   */
  private SlotList expandVariableList(TomSymbol symbol, SlotList subtermList) {
    if(symbol == null) {
      throw new TomRuntimeException("expandVariableList: null symbol");
    }

    if(subtermList.isEmpty()) {
      return `emptySlotList();
    }

    //System.out.println("symbol = " + subject.getAstName());
    %match(TomSymbol symbol, SlotList subtermList) {
      emptySymbol(), manySlotList(PairSlotAppl(slotName,slotAppl),tail) -> {
        /*
         * If the top symbol is unknown, the subterms
         * are expanded in an empty context
         */
        return `manySlotList(PairSlotAppl(slotName,expandVariable(emptyTerm(),slotAppl)), expandVariableList(symbol,tail));
      }

      symb@Symbol[typesToType=TypesToType(typeList,codomain)],
           manySlotList(PairSlotAppl(slotName,slotAppl),tail) -> {
        // process a list of subterms and a list of types
        if(isListOperator(`symb) || isArrayOperator(`symb)) {
          /*
           * TODO:
           * when the symbol is an associative operator,
           * the signature has the form: List conc( Element* )
           * the list of types is reduced to the singleton { Element }
           *
           * consider a pattern: conc(E1*,x,E2*,y,E3*)
           *  assign the type "Element" to each subterm: x and y
           *  assign the type "List" to each subtermList: E1*,E2* and E3*
           */

          //System.out.println("listOperator: " + symb);
          //System.out.println("subtermList: " + subtermList);

          %match(TomTerm slotAppl) {
            VariableStar[option=option,astName=name,constraints=constraints] -> {
              ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(codomain),`constraints);
              return `manySlotList(PairSlotAppl(slotName,VariableStar(option,name,codomain,newConstraints)), expandVariableList(symbol,tail));
            }

            UnamedVariableStar[option=option,constraints=constraints] -> {
              ConstraintList newConstraints = expandVariableConstraintList(`TomTypeToTomTerm(codomain),`constraints);
              return `manySlotList(PairSlotAppl(slotName,UnamedVariableStar(option,codomain,newConstraints)), expandVariableList(symbol,tail));
            }

            _ -> {
              TomType domainType = `typeList.getHead();
              return `manySlotList(PairSlotAppl(slotName,expandVariable(TomTypeToTomTerm(domainType), slotAppl)), expandVariableList(symbol,tail));

            }
          }
        } else {
          //TomType type = `typeList.getHead();
          return `manySlotList(PairSlotAppl(slotName,expandVariable(TomTypeToTomTerm(getSlotType(symb,slotName)), slotAppl)), expandVariableList(symbol,tail));
        }
      }
    }
		System.out.println("expandVariableList: strange case: '" + symbol + "'");
		throw new TomRuntimeException("expandVariableList: strange case: '" + symbol + "'");
  }

  protected Replace2 replace_replaceInstantiatedVariable = new Replace2() {
      public ATerm apply(ATerm subject, Object arg1) {
        TomList instantiatedVariable = (TomList)arg1;

        if(instantiatedVariable == null) {
          throw new TomRuntimeException("replaceInstantiatedVariable: null instantiatedVariable");
        }

				//System.out.println("instantiatedVariable = " + instantiatedVariable);
				//System.out.println("subject = " + subject);
				%match(TomList instantiatedVariable, TomTerm subject) {
					concTomTerm(_*,var@(Variable|VariableStar)[astName=opNameAST] ,_*), RecordAppl[nameList=(opNameAST),slots=concSlot()] -> {
						return `var;
					}
					concTomTerm(_*,var@Variable[astName=opNameAST] ,_*), Variable[astName=opNameAST] -> {
						return `var;
					}
					concTomTerm(_*,var@VariableStar[astName=opNameAST] ,_*), VariableStar[astName=opNameAST] -> {
						return `var;
					}
				}

        return traversal().genericTraversal(subject,this,instantiatedVariable);
      } // end apply
    }; // end new


  protected TomTerm replaceInstantiatedVariable(TomList instantiatedVariable, TomTerm subject) {
    return (TomTerm) replace_replaceInstantiatedVariable.apply(subject,instantiatedVariable);
  }
  protected Instruction replaceInstantiatedVariableInstruction(TomList instantiatedVariable, Instruction subject) {
    return (Instruction) replace_replaceInstantiatedVariable.apply(subject,instantiatedVariable);
  }

  private TomType getType(String tomName) {
    TomType tomType = getSymbolTable().getType(tomName);
    return tomType;
  }

}
