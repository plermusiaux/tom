/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2016-2017, Universite de Lorraine
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

parser grammar TomIslandParser;
options { tokenVocab=TomIslandLexer; }

start : (island | water)*? ;

island 
  : matchStatement
  | strategyStatement
  | includeStatement
  | gomStatement
  | typeterm
  | operator
  | oplist
  | oparray
  | bqcomposite
  | metaquote
  ;

metaquote
  : LMETAQUOTE (AT (composite | bqcomposite) AT | water)* RMETAQUOTE
  ;

matchStatement
  : MATCH (LPAREN (bqterm (COMMA bqterm)*)? RPAREN)? LBRACE actionRule* RBRACE 
  ;

strategyStatement
  : STRATEGY ID LPAREN slotList? RPAREN EXTENDS bqterm LBRACE visit* RBRACE
  ;

includeStatement
  : INCLUDE LBRACE ID ((SLASH|BACKSLASH) ID)* (DOT ID)?  RBRACE 
  ;

gomStatement
  : GOM gomOptions? block
  ;

gomOptions
  : LPAREN DMINUSID (COMMA DMINUSID)* RPAREN
  ;

visit
  : VISIT ID LBRACE actionRule* RBRACE
  ;

actionRule
  : patternlist ((AND | OR) constraint)? ARROW block
  | patternlist ((AND | OR) constraint)? ARROW bqterm
  | c=constraint ARROW block
  | c=constraint ARROW bqterm
  ;

block 
  : LBRACE (island | block | water)*? RBRACE
  ;

water
  : .
  ;

slotList
  : slot (COMMA slot)*
  ;

slot
  : id1=ID COLON? id2=ID
  ;

patternlist
  : pattern (COMMA pattern)* 
  ;

constraint
  : constraint AND constraint
  | constraint OR constraint
  | pattern MATCH_SYMBOL bqterm
  | term GREATERTHAN term
  | term GREATEROREQ term
  | term LOWERTHAN term
  | term LOWEROREQ term
  | term DOUBLEEQ term
  | term DIFFERENT term
  | LPAREN c=constraint RPAREN
  ;

//used in constraints 
term
  : var=ID STAR?
  | fsym=ID LPAREN (term (COMMA term)*)? RPAREN 
  | constant
  ;

// may be change this syntax: `term:sort
// retricted form of bqterm
// used in rhs, match, strategy
bqterm
  : codomain=ID? BQUOTE? fsym=ID LPAREN (bqterm (COMMA bqterm)*)? RPAREN 
  | codomain=ID? BQUOTE? fsym=ID LSQUAREBR (pairSlotBqterm (COMMA pairSlotBqterm)*)? RSQUAREBR 
  | codomain=ID? BQUOTE? var=ID STAR?
  | codomain=ID? constant
  | UNDERSCORE
  ;

pairSlotBqterm
  : ID EQUAL bqterm
  ;

// general form of bqterm
// used as island nd in metaquote
bqcomposite
  : BQUOTE fsym=ID LSQUAREBR (pairSlotBqterm (COMMA pairSlotBqterm)*)? RSQUAREBR 
  | BQUOTE composite
  ;

composite
  : fsym=ID LPAREN composite* RPAREN
  | LPAREN composite* RPAREN
  | var=ID STAR?
  | constant
  | UNDERSCORE
  | waterexceptparen
  ;

waterexceptparen 
  :
  ~(LPAREN|RPAREN)+? 
  ;

pattern
  : ID AT pattern 
  | ANTI pattern
  | fsymbol explicitArgs
  | fsymbol implicitArgs
  | var=ID STAR?
  | UNDERSCORE STAR?
  | constant STAR?
  ;

fsymbol 
  : headSymbol
  | LPAREN headSymbol (PIPE headSymbol)* RPAREN
  ;

headSymbol
  : ID QMARK?
  | ID DQMARK?
  | constant
  ;

constant
  : INTEGER
  | LONG
  | CHAR
  | DOUBLE
  | STRING
  ;

explicitArgs
  : LPAREN (pattern (COMMA pattern)*)? RPAREN
  ;

implicitArgs
  : LSQUAREBR (ID EQUAL pattern (COMMA ID EQUAL pattern)*)? RSQUAREBR 
  ;

/*
 * signature
 */
typeterm
  : TYPETERM type=ID (EXTENDS supertype=ID)? LBRACE 
    implement isSort? equalsTerm?
    RBRACE
  ;

operator
  : OP codomain=ID opname=ID LPAREN slotList? RPAREN LBRACE 
    (isFsym | make | getSlot | getDefault)*
    RBRACE
  ;

oplist
  : OPLIST codomain=ID opname=ID LPAREN domain=ID STAR RPAREN LBRACE 
    (isFsym | makeEmptyList | makeInsertList | getHead | getTail | isEmptyList)*
    RBRACE
  ;

oparray
  : OPARRAY codomain=ID opname=ID LPAREN domain=ID STAR RPAREN LBRACE 
    (isFsym | makeEmptyArray | makeAppendArray | getElement | getSize)*
    RBRACE
  ;

implement
  : IMPLEMENT block
  ;

equalsTerm
  : EQUALS LPAREN id1=ID COMMA id2=ID RPAREN block
  ;

isSort
  : IS_SORT LPAREN ID RPAREN block
  ;

isFsym
  : IS_FSYM LPAREN ID RPAREN block
  ;

make
  : MAKE LPAREN (ID (COMMA ID)*)? RPAREN block
  ;

makeEmptyList
  : MAKE_EMPTY LPAREN RPAREN block
  ;

makeEmptyArray
  : MAKE_EMPTY LPAREN ID RPAREN block
  ;

makeAppendArray
  : MAKE_APPEND LPAREN id1=ID COMMA id2=ID RPAREN block
  ;
  
makeInsertList
  : MAKE_INSERT LPAREN id1=ID COMMA id2=ID RPAREN block
  ;
  
getSlot
  : GET_SLOT LPAREN id1=ID COMMA id2=ID RPAREN block
  ;

getHead
  : GET_HEAD LPAREN ID RPAREN block
  ;

getTail
  : GET_TAIL LPAREN ID RPAREN block
  ;

getElement
  : GET_ELEMENT LPAREN id1=ID COMMA id2=ID RPAREN block
  ;

isEmptyList
  : IS_EMPTY LPAREN ID RPAREN block
  ;

getSize
  : GET_SIZE LPAREN ID RPAREN block
  ;

getDefault
  : GET_DEFAULT LPAREN ID RPAREN block
  ;

