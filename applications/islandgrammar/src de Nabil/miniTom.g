grammar miniTom;

options {
  output=AST;
  ASTLabelType=Tree;
  backtrack=true;
}

tokens {
  PROGRAM;
  CODE;
  SUBCODE;
  HOST;
}

@parser::header {
import org.antlr.runtime.tree.*;
}

@lexer::header {
import java.util.Queue;
import java.util.LinkedList;
import org.antlr.runtime.tree.*;
}

@lexer::members{
  int levelcounter=-1;
  public static Queue<Tree> SubTrees = new LinkedList<Tree>();
}

/* Parser rules */
program : LEFTPAR RIGHTPAR LEFTBR code* -> ^(PROGRAM code*) ;

code :
       s1=statement SEMICOLUMN -> ^(CODE $s1)
     | s2=subcode -> ^(CODE $s2);
//      | OBRA s3=name RARROW LEFTBR CBRA -> ^(HOST $s3);

subcode : LEFTBR inside+ RIGHTBR -> ^(SUBCODE inside+);

inside    : B  ;
statement : A+ ;

/* Lexer rules */
LEFTPAR    : '(' ;
RIGHTPAR   : ')' ;
LEFTBR     : '{' {levelcounter+=1;} ;
RIGHTBR    : '}'
           {
             if(levelcounter==0) {
               emit(Token.EOF_TOKEN);
             } else {
               levelcounter-=1;
             }
           }
           ;
SEMICOLUMN : ';' ;
COMMENT    : '/*' (options {greedy=false;} : . )* '*/'
           { System.out.println("found comment "+getText()); }
           ;
A          : 'alice' ;
B          : 'bob' ;
OBRA       : '[' ;
CBRA       : ']' ;
RARROW     : '->';
LETTER     : 'A'..'Z' ;
WS         : ('\r' | '\n' | '\t' | ' ' )* { $channel = HIDDEN; };