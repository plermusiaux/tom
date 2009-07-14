package sa;

import sa.rule.types.*;
import java.util.*;
import tom.library.sl.*;
import aterm.*;
import aterm.pure.*;

public class Tools {
  %include { rule/Rule.tom }
  %include { sl.tom }
  %include { java/util/types/Collection.tom }
  %include { java/util/types/Map.tom }
  %include { java/util/types/HashSet.tom }
  %include { java/util/types/Set.tom }

  /**
   * encode: transforms a string "f(a,X)" into its Term representation
   * e.g. `Appl("f",TermList(Appl("a",TermList()),Var("X")))
   * variables are in capital letters
   * anti-pattern are introduced by anti(t)
   * alias are introduced by at(t1,t2)
   * rules are introduced by rule(lhs,rhs)
   * @param exp the string representation of the term to meta-encode
   * @return meta-encoding using Appl and TermList
   */
  public static Term encode(String stringterm) {
    //System.out.println("encode: " + stringterm);
    Term res = null;
    ATermFactory factory = SingletonFactory.getInstance();
    ATerm at = factory.parse(stringterm);
    res = encode(at);
    //System.out.println("encode: " + res);
    return res;
  }
  
  private static Term encode(ATerm at) {
    Term res = null;
    switch(at.getType()) {
	  	case ATerm.APPL:
	  		ATermAppl appl = (ATermAppl) at;
        String name = appl.getName();
        ATermList args = appl.getArguments();
        if(args.isEmpty() && isVariableName(name)) {
          res = `Var(name);
        } else if(name.equals("anti") && args.getLength()==1) {
          Term term = encode(args.getFirst());
          res = `Anti(term);
        } else if(name.equals("at") && args.getLength()==2) {
          Term var = encode(args.getFirst());
          Term term = encode(args.getNext().getFirst());
          res = `At(var,term);
        } else {
          res = `Appl(name,encodeList(args));
        }
       break; 
    }
    return res;
  }

  public static Rule encodeRule(String stringterm) {
    //System.out.println("encodeRule: " + stringterm);
    Rule res = null;
    ATermFactory factory = SingletonFactory.getInstance();
    ATerm at = factory.parse(stringterm);
    switch(at.getType()) {
	  	case ATerm.APPL:
	  		ATermAppl appl = (ATermAppl) at;
        String name = appl.getName();
        ATermList args = appl.getArguments();
        if(name.equals("rule") && args.getLength()==2) {
          Term lhs = encode(args.getFirst());
          Term rhs = encode(args.getNext().getFirst());
          res = `Rule(lhs,rhs);
        }
        break;
    }
    //System.out.println("encodeRule: " + res);
    return res;
  }

  private static boolean isVariableName(String name) {
    boolean res = true;
    for(int i=0 ; i<name.length(); i++) {
      res &= (Character.isUpperCase(name.charAt(i)) || Character.isDigit(name.charAt(i)) || name.charAt(i)=='_');
    }
    return name.startsWith("var_") || res;
  }

  private static TermList encodeList(ATermList list) {
    if(list.isEmpty()) {
      return `TermList();
    } else {
      Term head = encode(list.getFirst());
      TermList tail = encodeList(list.getNext());
      return `TermList(head,tail*);
    }
  }

  /**
    * metaEncodeConsNil: transforms a Term representation into a generic term representation
    * for instance, Appl("f",TermList(Appl("b",TermList()))) is transformed into
    * the string "Appl(symb_f,Cons(Appl(symb_b,Nil()),Nil()))"
    * this string can be encoded into a Term, using the "encode" method
    */
  public static Term metaEncodeConsNil(Term t) {
    return encode(encodeConsNil(t));
  }

  private static String encodeConsNil(Term t) {
    %match(t) {
      Appl(symb,args) -> {
        return "Appl(symb_" + `symb + "," + encodeConsNil(`args) + ")";
      }

      Var(name) -> {
        return "var_" + `name;
      }

      Anti(term) -> {
        //System.out.println("ENCODE ANTI: " + `term);
        return "anti(" + encodeConsNil(`term) + ")";
      }
    }
    return null;
  }

  private static String encodeConsNil(TermList t) {
    %match(t) {
      TermList(head,tail*) -> {
        return "Cons(" + encodeConsNil(`head) + "," + encodeConsNil(`tail) + ")";
      }
      TermList() -> {
        return "Nil()";
      }
    }
    return "null";
  }

  /*
   * go from
   * Appl("Appl",TermList(Appl("symb_f",TermList()),Appl("Cons",TermList(Appl("Appl",TermList(Appl("symb_a",TermList()),Appl("Nil",TermList()))),Appl("Nil",TermList())))))
    * to Appl("f",TermList(Appl("b",TermList())))
     *
     */
  public static Term decodeConsNil(Term t) {

//     System.out.println("IN DECODE = "+ `t);
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
    %match(t) {
      Appl("Cons",TermList(head,tail)) -> {
//         System.out.println("HEAD = "+ `head);
//         System.out.println("TAIL = "+ `tail);
        TermList newTail = decodeConsNilList(`tail);
        return `TermList(decodeConsNil(head), newTail*);
      }

      Appl("Nil",TermList()) -> {
        return `TermList();
      }

    }
    return null;
  }





  public static TermList generalDecodeConsNilList(TermList t) {
    %match(t) {
      TermList(head,tail*) -> { 
        TermList newTail = generalDecodeConsNilList(`tail);
        return `TermList(generalDecodeConsNil(head),newTail*);
      }
    }
    return t; // liste vide
  }

  /** 
   * Decodes (the) encoded (part of) terms
   * The term can contain Anti and At - only the encoded parts are decoded
   */
  public static Term generalDecodeConsNil(Term t) {
    %match(t) {
      Appl("Appl",TermList(Appl(symb_name,TermList()),args)) -> {
          String name = `symb_name.substring("symb_".length());
          return `Appl(name,generalDecodeConsNilList(args));
      }
      Appl(rule_name,args) -> {
          return `Appl(rule_name,generalDecodeConsNilList(args));
      }
      Var(symb_name) -> {
        String name = `symb_name.startsWith("var_")?`symb_name.substring("var_".length()):`symb_name;
        return `Var(name);
      }
      At(var_name,term) -> {
          return `At(var_name,generalDecodeConsNil(term));
      }
      Anti(term) -> {
          return `Anti(generalDecodeConsNil(term));
      }      
    }
    // never
    return t;
  }

  public static TermList generalDecodeConsNilList(Term t) {
    %match(t) {
      Appl("Cons",TermList(head,tail)) -> {
        TermList newTail = generalDecodeConsNilList(`tail);
        return `TermList(generalDecodeConsNil(head), newTail*);
      }

      Appl("Nil",TermList()) -> {
        return `TermList();
      }

    }
    // never
    return `TermList();
  }


  /**
    * generalMetaEncodeConsNil: transforms a Term representation into a generic term representation
    * - the term can contain Anti and At - only the terms in the original signature are encoded
    */

  public static Term generalMetaEncodeConsNil(Term t, Set<String> extractedSignature) {
    %match(t) {
      Appl(symb,args) -> {
        if(extractedSignature.contains(`symb)){
          return encode("Appl(symb_" + `symb + "," + encodeConsNil(`args) + ")");
        } else {
          return `Appl(symb,generalMetaEncodeConsNil(args,extractedSignature));
        }
      }
      // could be default
      Var(name) -> {
        return `Var(name);
      }
      Anti(term) -> {
        return `Anti(generalMetaEncodeConsNil(term,extractedSignature));
      }
      At(t1,t2) -> {
        return `At(generalMetaEncodeConsNil(t1,extractedSignature),generalMetaEncodeConsNil(t2,extractedSignature));
      }
    }
    return t;
  }

  private static TermList generalMetaEncodeConsNil(TermList t, Set<String> extractedSignature) {
    %match(t) {
      TermList(head,tail*) -> {
        Term headEnc = generalMetaEncodeConsNil(`head,extractedSignature);
        TermList tailEnc = generalMetaEncodeConsNil(`tail,extractedSignature);
        return `TermList(headEnc,tailEnc*);
      }
      // could be default
      TermList() -> {
        return `TermList();
      }
    }
    return t;
  }





}