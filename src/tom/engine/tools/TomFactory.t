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

    Pierre-Etienne Moreau e-mail: Pierre-Etienne.Moreau@loria.fr

*/

package jtom.tools;

import jtom.*;
import java.util.*;
import jtom.adt.tomsignature.types.*;
import jtom.xml.*;
import jtom.exception.TomRuntimeException;

public class TomFactory extends TomBase {

// ------------------------------------------------------------
  %include { ../adt/TomSignature.tom }
// ------------------------------------------------------------

  public TomFactory() {
    super();
  }
  
  public String encodeXMLString(SymbolTable symbolTable, String name) {
    name = "\"" + name + "\"";
    ast().makeStringSymbol(symbolTable,name, new LinkedList());
    return name;
  }

  public TomList metaEncodeTermList(SymbolTable symbolTable,TomList list) {
    %match(TomList list) {
      emptyTomList() -> { return `emptyTomList();}
      manyTomList(head,tail) -> {
        return `manyTomList(metaEncodeXMLAppl(symbolTable,head),
                            metaEncodeTermList(symbolTable,tail));
      }
    }
    return list;
  }

  public TomTerm encodeXMLAppl(SymbolTable symbolTable, TomTerm term) {
      /*
       * encode a String into a quoted-string
       * Appl(...,Name("string"),...) becomes
       * Appl(...,Name("\"string\""),...)
       */
    NameList newNameList = `concTomName();
    %match(TomTerm term) {
      Appl[nameList=(_*,Name(name),_*)] -> {
        newNameList = (NameList)newNameList.append(`Name(encodeXMLString(symbolTable,name)));
      }
    }
    term = term.setNameList(newNameList);
      //System.out.println("encodeXMLAppl = " + term);
    return term;
  }

  public TomTerm metaEncodeXMLAppl(SymbolTable symbolTable, TomTerm term) {
      /*
       * meta-encode a String into a TextNode
       * Appl(...,Name("\"string\""),...) becomes
       * Appl(...,Name("TextNode"),[Appl(...,Name("\"string\""),...)],...)
       */
      //System.out.println("metaEncode: " + term);
    %match(TomTerm term) {
      Appl[nameList=(Name(tomName))] -> {
          //System.out.println("tomName = " + tomName);
        TomSymbol tomSymbol = symbolTable.getSymbol(`tomName);
        if(tomSymbol != null) {
          if(isStringOperator(tomSymbol)) {
            Option info = `OriginTracking(Name(Constants.TEXT_NODE),-1,Name("??"));
            term = `Appl( ast().makeOption(info),
                          concTomName(Name(Constants.TEXT_NODE)),concTomTerm(term),
                          tsf().makeConstraintList());
              //System.out.println("metaEncodeXmlAppl = " + term);
          }
        }
      }
    }
    return term;
  }

  public boolean isExplicitTermList(LinkedList childs) {
    if(childs.size() == 1) {
      TomTerm term = (TomTerm) childs.getFirst();
      %match(TomTerm term) {
        Appl[nameList=(Name("")),args=args] -> {
          return true;
        }
      }
    }
    return false;
  }
  
  public LinkedList metaEncodeExplicitTermList(SymbolTable symbolTable, TomTerm term) {
    LinkedList list = new LinkedList();
    %match(TomTerm term) {
      Appl[nameList=(Name("")),args=args] -> {
        while(!`args.isEmpty()) {
          list.add(metaEncodeXMLAppl(symbolTable,`args.getHead()));
          `args = `args.getTail();
        }
        return list;
      }

      _ -> {
          //System.out.println("metaEncodeExplicitTermList: strange case: " + term);
        list.add(term);
        return list;
      }
    }
  }

  public TomTerm buildList(TomName name,TomList args) {
    %match(TomList args) {
      emptyTomList() -> {
        return `BuildEmptyList(name);
      }

      manyTomList(head@VariableStar[],tail) |
        manyTomList(Composite(concTomTerm(_*,head@VariableStar[])),tail) -> {
        TomTerm subList = buildList(name,`tail);
        return `BuildAppendList(name,head,subList);
      }

      manyTomList(head@BuildTerm[],tail) |
      manyTomList(head@BuildVariable[],tail) |
      manyTomList(head@Variable[],tail) |
      manyTomList(head@Composite(_),tail) -> {
        TomTerm subList = buildList(name,`tail);
        return `BuildConsList(name,head,subList);
      }

      manyTomList(head@TargetLanguageToTomTerm[],tail) -> {
        TomTerm subList = buildList(name,`tail);
        return subList;
      }

    }

    throw new TomRuntimeException("buildList strange term: " + args);
     
  }

  public TomTerm buildArray(TomName name,TomList args) {
    return buildArray(name,(TomList)args.reverse(),0);
  }

  private TomTerm buildArray(TomName name,TomList args, int size) {
    %match(TomList args) {
      emptyTomList() -> {
        return `BuildEmptyArray(name,size);
      }

      manyTomList(head@VariableStar[],tail) |
      manyTomList(Composite(concTomTerm(_*,head@VariableStar[])),tail) -> {
          /*System.out.println("head = " + head);*/
        TomTerm subList = buildArray(name,`tail,size+1);
        return `BuildAppendArray(name,head,subList);
      }

      manyTomList(head@BuildTerm[],tail) |
      manyTomList(head@BuildVariable[],tail) |
      manyTomList(head@Variable[],tail) |
      manyTomList(head@Composite(_),tail) -> {
        TomTerm subList = buildArray(name,`tail,size+1);
        return `BuildConsArray(name,head,subList);
      }

      manyTomList(head@TargetLanguageToTomTerm[],tail) -> {
	TomTerm subList = buildArray(name,`tail,size);
	return subList;
      }

    }

    throw new TomRuntimeException("buildArray strange term: " + args);
     
  }

  
}
