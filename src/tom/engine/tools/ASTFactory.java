/*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2004 INRIA
			    Nancy, France.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr

*/

package jtom.tools;

import java.util.*;
import jtom.adt.tomsignature.*;
import jtom.adt.tomsignature.types.*;
import jtom.xml.*;
import aterm.ATerm;

public class ASTFactory {

  private Factory tomSignatureFactory;
  
  public ASTFactory(Factory tomSignatureFactory) {
    this.tomSignatureFactory = tomSignatureFactory;
  }

  protected Factory tsf() {
    return tomSignatureFactory;
  }

  protected TomList cons(TomTerm t, TomList l) {
    return tsf().makeTomList(t,l);
  }

  protected TomList append(TomTerm t, TomList l) {
    return (TomList) l.append(t);
  }

  public TomList makeList(Collection c) {
    Object array[] = c.toArray();
    TomList list = tsf().makeTomList();
    for(int i=array.length-1; i>=0 ; i--) {
      ATerm elt = (ATerm)array[i];
      TomTerm term;
      if(elt instanceof TargetLanguage) {
        term = tsf().makeTomTerm_TargetLanguageToTomTerm((TargetLanguage)elt);
      } else if(elt instanceof TomType) {
        term = tsf().makeTomTerm_TomTypeToTomTerm((TomType)elt);
      } else if(elt instanceof Declaration) {
        term = tsf().makeTomTerm_DeclarationToTomTerm((Declaration)elt);
      } else if(elt instanceof Expression) {
        term = tsf().makeTomTerm_ExpressionToTomTerm((Expression)elt);
      } else if(elt instanceof TomName) {
        term = tsf().makeTomTerm_TomNameToTomTerm((TomName)elt);
      } else if(elt instanceof Instruction) {
        term = tsf().makeTomTerm_InstructionToTomTerm((Instruction)elt);
      } else {
        term = (TomTerm)elt;
      }
      list = cons(term,list);
    }
    return list;
  }

  public InstructionList makeInstructionList(Collection c) {
    Object array[] = c.toArray();
    InstructionList list = tsf().makeInstructionList();
    for(int i=array.length-1; i>=0 ; i--) {
      ATerm elt = (ATerm)array[i];
      Instruction term;
      if(elt instanceof TargetLanguage) {
        term = tsf().makeInstruction_TargetLanguageToInstruction((TargetLanguage)elt);
      } else if(elt instanceof TomTerm) {
        term = tsf().makeInstruction_TomTermToInstruction((TomTerm)elt);
          //System.out.println("term   = " + term);
      } else if(elt instanceof Instruction) {
        term = (Instruction)elt;
      } else {
        System.out.println("elt   = " + elt);
        term = (Instruction)elt;
      }
      list = tsf().makeInstructionList(term,list);
    }
    return list;
  }

  public OptionList makeOptionList(List argumentList) {
    OptionList list = tsf().makeOptionList();
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      ATerm elt = (ATerm)argumentList.get(i);
      Option term;
      if(elt instanceof TomName) {
        term = tsf().makeOption_TomNameToOption((TomName)elt);
      } else if(elt instanceof Declaration) {
        term = tsf().makeOption_DeclarationToOption((Declaration)elt);
      } else {
        term = (Option)elt;
      }
      list = tsf().makeOptionList(term,list);
    }
    return list;
  }

  public NameList makeNameList(List argumentList) {
    NameList list = tsf().makeNameList();
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      ATerm elt = (ATerm)argumentList.get(i);
      TomName term = (TomName) elt;
      list = tsf().makeNameList(term,list);
    }
    return list;
  }

  public TomTerm makeVariable(String name, String type) {
    return makeVariable(makeOption(), name, type);      
  }

  public TomTerm makeVariable(OptionList option, String name, String type) {
    return tsf().makeTomTerm_Variable(option, tsf().makeTomName_Name(name), tsf().makeTomType_TomTypeAlone(type), tsf().makeExpressionList());  
  }

  public TomTerm makeVariable(OptionList option, TomName name, TomType type) {
    return tsf().makeTomTerm_Variable(option, name, type, tsf().makeExpressionList());  
  }

  public TomTerm makeVariableStar(OptionList option, String name, String type) {
    return tsf().makeTomTerm_VariableStar(option, tsf().makeTomName_Name(name), tsf().makeTomType_TomTypeAlone(type), tsf().makeExpressionList());  
  }

  public TomTerm makeVariableStar(OptionList option, TomName name, TomType type) {
    return tsf().makeTomTerm_VariableStar(option, name, type, tsf().makeExpressionList());  
  }

  public TomTerm makeUnamedVariableStar(OptionList option, String type) {
    return tsf().makeTomTerm_UnamedVariableStar(option, tsf().makeTomType_TomTypeAlone(type));  
  }

  public TomSymbol makeSymbol(String symbolName, String resultType, TomTypeList typeList, SlotList slotList,
                              List optionList, TargetLanguage glFsym) {
    TomName name = tsf().makeTomName_Name(symbolName);
    TomType typesToType =  tsf().makeTomType_TypesToType(typeList, tsf().makeTomType_TomTypeAlone(resultType));
    OptionList options = makeOptionList(optionList);
    return tsf().makeTomSymbol_Symbol(name,typesToType,slotList,options,glFsym);
  }

  public OptionList makeOption() {
    return tsf().makeOptionList();
  }

  public OptionList makeOption(Option arg) {
    OptionList list = tsf().makeOptionList();
    if(arg!= null) {
      list = tsf().makeOptionList(arg,list);
    }
    return list;
  }

  public OptionList makeOption(Option arg, Option info) {
    OptionList list = tsf().makeOptionList();
    if(arg!= null) {
      list = tsf().makeOptionList(arg,list);
    }
    list = tsf().makeOptionList(info,list);
    return list;
  }

	public Option makeOriginTracking(String name, int line , String fileName) {
    return tsf().makeOption_OriginTracking(tsf().makeTomName_Name(name), line, tsf().makeTomName_Name( fileName));
  }
  
  public Declaration makeMakeDecl(String opname, TomType returnType, List argList, TargetLanguage tlcode, Option orgTrack) {
    TomName name = tsf().makeTomName_Name(opname);  
    return tsf().makeDeclaration_MakeDecl(name, returnType, makeList(argList), tlcode, orgTrack);
  }

  public TomType makeType(String typeNameTom, String typeNametGL) {
    return makeType(typeNameTom,tsf().makeTargetLanguage_ITL(typeNametGL));
  }

  public TomType makeType(String typeNameTom, TargetLanguage tlType) {
    TomType typeTom = tsf().makeTomType_ASTTomType(typeNameTom);
    TomType sortTL  = tsf().makeTomType_TLType(tlType);
    return tsf().makeTomType_Type(typeTom,sortTL);
  }

    /*
     * create an <sort> symbol
     * where <sort> could be int. double or String  
     */
  private void makeSortSymbol(SymbolTable symbolTable,
                             String sort,
                             String value, List optionList) {
    TomTypeList typeList = tsf().makeTomTypeList();
    TargetLanguage tlFsym = tsf().makeTargetLanguage_ITL(value);
    SlotList slotList = tsf().makeSlotList();
    TomSymbol astSymbol = makeSymbol(value,sort,typeList,slotList,optionList,tlFsym);
    symbolTable.putSymbol(value,astSymbol);
  }
  
    /*
     * create an integer symbol
     */
  public void makeIntegerSymbol(SymbolTable symbolTable,
                                String value, List optionList) {
    String sort = "int";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    /*
     * create a char symbol
     */
  public void makeCharSymbol(SymbolTable symbolTable,
                             String value, List optionList) {
    String sort = "char";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }
    /*
     * create a double symbol
     */
  public void makeDoubleSymbol(SymbolTable symbolTable,
                               String value, List optionList) {
    String sort = "double";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    /*
     * create a string symbol
     */
  public void makeStringSymbol(SymbolTable symbolTable,
                               String value, List optionList) {
    String sort = "String";
    makeSortSymbol(symbolTable, sort, value, optionList);
  } 
  
    /*
     * update the root of lhs: it becomes a defined symbol
     */
  public TomSymbol updateDefinedSymbol(SymbolTable symbolTable, TomTerm term) {
    if(term.isAppl() || term.isRecordAppl()) {
      String key = term.getNameList().getHead().getString();
      TomSymbol symbol = symbolTable.getSymbol(key);
      if (symbol != null) {
        OptionList optionList = symbol.getOption();
        optionList = (OptionList) optionList.append(tsf().makeOption_DefinedSymbol());
        symbolTable.putSymbol(key,symbol.setOption(optionList));
        return symbol;
      }
    }
    return null;
  }

  public TargetLanguage reworkTLCode(TargetLanguage code, boolean pretty) {
    if(!pretty){
      String newTlCode = code.getCode();      
      newTlCode = newTlCode.replace('\n', ' ');
      newTlCode = newTlCode.replace('\t', ' ');
      newTlCode = newTlCode.replace('\r', ' ');
        //System.out.println("reworking"+newTlCode);
      return code.setCode(newTlCode);
    } else
      return code;
  }

  public TomName makeName(String slotName) {
    if(slotName.length()>0)
      return tsf().makeTomName_Name(slotName);
    else
      return tsf().makeTomName_EmptyName();
  }

  public String encodeXMLString(SymbolTable symbolTable, String name) {
    name = "\"" + name + "\"";
    makeStringSymbol(symbolTable,name, new LinkedList());
    return name;
  }

  public TomTerm encodeXMLAppl(SymbolTable symbolTable, TomTerm term) {
      /*
       * encode a String into a quoted-string
       * Appl(...,Name("string"),...) becomes
       * Appl(...,Name("\"string\""),...)
       */
    if(term.isAppl()) {
      TomName astName = term.getAstName();
      if(astName.isName()) {
        String tomName = encodeXMLString(symbolTable,astName.getString());
        term = term.setAstName(tsf().makeTomName_Name(tomName));
          //System.out.println("encodeXMLAppl = " + term);
      }
    }
      return term;
  }

  public TomTerm metaEncodeXMLAppl(SymbolTable symbolTable, TomTerm term) {
      /*
       * meta-encode a String into a TextNode
       * Appl(...,Name("\"string\""),...) becomes
       * Appl(...,Name("TextNode"),[Appl(...,Name("\"string\""),...)],...)
       */
    if(term.isAppl()) {
      TomName astName = term.getAstName();
      if(astName.isName()) {
        String tomName = astName.getString();
        TomSymbol tomSymbol = symbolTable.getSymbol(tomName);
        if(tomSymbol != null) {
          TomType type = tomSymbol.getTypesToType().getCodomain();
            //System.out.println("type = " + type);
          if(type.isTomTypeAlone() && type.getString().equals("String")) {
            Option info = makeOriginTracking(Constants.TEXT_NODE,-1,"??");
            TomList list = tsf().makeTomList();
            list = tsf().makeTomList(term,list);
						NameList nameList = tsf().makeNameList();
						nameList = (NameList)nameList.append(tsf().makeTomName_Name(Constants.TEXT_NODE));
            term = tsf().makeTomTerm_Appl(
              makeOption(info),
							nameList,
							list);
              //System.out.println("metaEncodeXmlAppl = " + term);
          }
        }
      }
    }
    return term;
  }

  
}
