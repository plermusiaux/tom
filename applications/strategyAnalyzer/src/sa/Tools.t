package sa;

import sa.rule.types.*;
import java.util.*;
import tom.library.sl.*;
import aterm.*;
import aterm.pure.*;

public class Tools {
  %include { rule/Rule.tom }
  %include { sl.tom }
  %include { aterm.tom }
  %include { java/util/types/Collection.tom }
  %include { java/util/types/Map.tom }
  %include { java/util/types/HashSet.tom }
  %include { java/util/types/Set.tom }

  private static final String AUX = "Aux";   // extension for auxiliary symbols
  private static int phiNumber = 0;          // sequence number for symbol (names)

  /*** helpers to build and decompose symbol names ***/
  
  /**
   * Builds a unique symbol (name)
   */
  public static String getName(String name) {
    return name + "_" + (phiNumber++);
  }

  /**
   * Given symbolName and operatorName
   * returns symbolName-operatorName
   */
  public static String addOperatorName(String symbolName, String operatorName) {
    return symbolName + "-" + operatorName;
  }

  /**
   * given symbolName and typeName
   * returns symbolName_typeName
   */
  public static String addTypeName(String symbol, String typeName) {
    return symbol + "_" + typeName;
  }

  /**
   * Given symbolName
   * returns symbolNameAUX
   */
  public static String addAuxExtension(String symbol) {
    return symbol + AUX;
  }

  /**
   * A symbol is of the form:
   * - symbolName[AUX]
   * - symbolName[AUX][_<number>]
   * - symbolName[AUX][_<number>]_typeName
   * - symbolName[AUX][_<number>]-operatorName[_<number>]_typeName
   * @returns symbolName[AUX]
   */
  public static String getSymbolName(String symbol) {
    int last = symbol.indexOf('_');
    if(last == -1) {
      last=symbol.length();
    }
    return symbol.substring(0,last);
  }

  /**
   * Determines if auxiliary symbol
   * @returns true if of the form symbolNameAUX; false otherwise
   */
  public static boolean isSymbolNameAux(String symbol) {
    boolean res = false;
    String name = getSymbolName(symbol);
    if(name.length() > AUX.length()) {
      res = name.substring(name.length()-AUX.length(),name.length()).equals(AUX);
    }
    return res;
  }

  /**
   * Retuns the name of the strategy it has been generated for (strips of the AUX)
   * @returns symbolName
   */
  public static String getSymbolNameMain(String symbol) {
    String name = getSymbolName(symbol);
    if(isSymbolNameAux(symbol)) {
      name = name.substring(0,name.length()-AUX.length());
    }
    return name;
  }

  /**
   * A symbol is expected to be of the form:
   * - symbolName[AUX][_<number>]_typeName
   * - symbolName[AUX][_<number>]-operatorName[_<number>]_typeName
   * @returns symbolName[AUX]
   */
  public static String getTypeName(String symbol) {
    int last = symbol.lastIndexOf('_');
    if(last == -1) { // nothing if type not specified in the symbol name 
      return ""; 
    }
    return symbol.substring(last+1,symbol.length());
  }

  /**
   * Given a symbol name of the form 
   * - symbolName[AUX][_<number>]-operatorName[_<number>][_typeName]
   * @returns operatorName
   */
  public static String getOperatorName(String symbol) {
    String aux = null;
    int last = symbol.indexOf('-');
    if(last != -1) { // if containing a composite
      int funLast = symbol.indexOf('_',last+1);
      if(funLast == -1) { //  if no other information after composite
        funLast = symbol.length();
      }
      aux = symbol.substring(last+1,funLast);
    }
    return aux;
  }

  private static boolean isVariableName(String name, Signature signature) {
    return signature.getCodomain(name) == null;
  }


  /*** helpers to build AST ***/
  private static Term Anti(Term t) { return `Anti(t); }
  private static Term Var(String name) { return `Var(name); }
  private static Term Appl(Term t1, Term t2) { return _appl(Signature.APPL,t1,t2); }
  private static Rule Rule(Term lhs, Term rhs) { return `Rule(lhs,rhs); }
  private static Term Nil() { return _appl(Signature.NIL); }
  private static Term Cons(Term t1, Term t2) { return _appl(Signature.CONS,t1,t2); }
  private static Term _appl(String name, Term... args) {
    TermList tl = `TermList();
    for(Term t:args) {
      tl = `TermList(tl*,t);
    }
    return `Appl(name,tl);
  }

  /**
    * metaEncodeConsNil: transforms a Term representation into a generic term representation
    * for instance, the term f(b()) (implemented by Appl("f",TermList(Appl("b",TermList()))))
    * is transformed into the term Appl(symb_f,Cons(Appl(symb_b,Nil()),Nil()))
    * implemented by Appl("Appl",TermList(Appl("symb_f",TermList()),Appl("Cons",TermList(Appl("Appl",TermList(Appl("symb_a",TermList()),Appl("Nil",TermList()))),Appl("Nil",TermList())))))
    */
  public static Term metaEncodeConsNil(Term t, Signature signature) {
    //return encode(encodeConsNil(t,signature),signature);
    return encodeConsNil(t,signature);
  }
  
  private static Term encodeConsNil(Term t, Signature signature) {
    %match(t) {
      Appl(symb,args) -> {
        String symbName = "symb_" + `symb;
        if(!Main.options.metalevel) {
          signature.addSymbol(symbName,new ArrayList<String>(),Signature.TERM);
        } else {
          signature.addSymbol(symbName,new ArrayList<String>(),Signature.METASYMBOL);
        }
        //return "Appl(" + symbName + "," + encodeConsNil(`args,signature) + ")";
        return Appl(_appl(symbName), encodeConsNil(`args,signature));
      }

      Var(name) -> {
        //return "var_" + `name;
        return Var("var_"+`name);
      }

      Anti(term) -> {
        //System.out.println("ENCODE ANTI: " + `term);
        //return "anti(" + encodeConsNil(`term,signature) + ")";
        return Anti(encodeConsNil(`term,signature));
      }
    }
    return null;
  }

  private static Term encodeConsNil(TermList t, Signature signature) {
    %match(t) {
      TermList(head,tail*) -> {
        return Cons(encodeConsNil(`head,signature),encodeConsNil(`tail,signature));
      }
      TermList() -> {
        return Nil();
      }
    }
    return null;
  }

  /*
   * go from meta-level to term level
   * Appl("Appl",TermList(Appl("symb_f",TermList()),Appl("Cons",TermList(Appl("Appl",TermList(Appl("symb_a",TermList()),Appl("Nil",TermList()))),Appl("Nil",TermList())))))
    * is decoded to Appl("f",TermList(Appl("b",TermList())))
     *
     */
  public static Term decodeConsNil(Term t) {

    //System.out.println("IN DECODE = "+ `t);
    %match(t) {
      Appl("Appl",TermList(Appl(symb_name,TermList()),args)) -> {
          String name = `symb_name.substring("symb_".length());
          return `Appl(name,decodeConsNilList(args));
      }

      Var(symb_name) -> {
        String name = `symb_name.substring("var_".length());
        return `Var(name);
      }
    }
    // identity to not decode an already decoded term
    return t;
  }

  public static TermList decodeConsNilList(Term t) {
     //System.out.println("IN DECODE LIST = "+ `t);
    %match(t) {
      Appl("Cons",TermList(head,tail)) -> {
         //System.out.println("HEAD = "+ `head);
         //System.out.println("TAIL = "+ `tail);
        TermList newTail = decodeConsNilList(`tail);
        return `TermList(decodeConsNil(head), newTail*);
      }

      Appl("Nil",TermList()) -> {
         //System.out.println("NIL");
        return `TermList();
      }

    }
    return null;
  }

  /*
   * generate a term for the form f(Z1,...,Zn)
   * @param name the symbol name 
   * @param arity the arity of the symbol
   * @return the Term that represents the term
   */
  public static Term genAbstractTerm(String name, int arity, String varname) {
    TermList args = `TermList();
    for(int i=arity ; i>= 1 ; i--) {
      Term var = `Var(varname+"_"+i);
      args = `TermList(var, args*);
    }
    return `Appl(name, args);
  }
 
  /*
   * tools for manipulating Program
   */
  public static StratDecl getStratDecl(String name, Program program) {
    %match(program) {
      Program(_,ConcStratDecl(_*,decl@StratDecl(n,_,_),_*)) -> {
        if(`n.equals(name)) {
          return `decl;
        }
      }
    }
    return null;
  }

  /*
  public static List<String> gomTypeListToStringList(GomTypeList argTypes) {
    List<String> res = new ArrayList<String>();
    while(!argTypes.isEmptyConcGomType()) {
      String name = argTypes.getHeadConcGomType().getName();
      res.add(name);
      argTypes = argTypes.getTailConcGomType();
    }
    return res;
  }
*/
//   public static StrategyOperator getRuleOperator(Rule rule) {
//     StrategyOperator op = StrategyOperator.IDENTITY;
//     %match(rule){
//       Rule(Appl(symbol,args),_) ->{
//         String opSymb = `symbol;
//         op = Tools.getOperator(opSymb);
//       }
//     }
//     return op;
//   }


//   public static String getArgumentSymbol(Rule rule) {
//     String funSymb = null;
//     %match(rule){
//       Rule(Appl(symbol,TermList(Appl(fun,args),_*)),_) ->{
//         funSymb = `fun;
//       }
//     }
//     return funSymb;
//   }


}
