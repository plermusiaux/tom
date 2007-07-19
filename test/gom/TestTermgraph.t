/*
 * Copyright (c) 2004-2007, INRIA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gom;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import gom.testtermgraph.m.types.*;
import gom.testtermgraph.m.*;
import tom.library.sl.*;

public class TestTermgraph extends TestCase {

  %gom(--termgraph) {
    module m
      imports String
      abstract syntax
      Term = a()
      | b()
      | c()
      | f(arg1:Term) 
      | g(arg1:Term, arg2:Term)

      sort Term: graphrules(Test,Identity) {
          g(l:a(),&l) -> f(b())
          f(g(g(a(),&l),l:b())) -> f(c())
          g(x,y) -> f(x)
      }
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(TestTermgraph.class));
  }

  public void testExpand() {
    Term subject = `g(g(g(labTerm("a1",a()),labTerm("g",g(refTerm("a1"),refTerm("a2")))),labTerm("a2",a())),refTerm("g"));
    Term expanded = `g(g(g(a(),g(pathTerm(-1,-2,1),a())),pathTerm(-2,1,2,2)),pathTerm(-2,1,1,2));
    assertEquals(expanded,mAbstractType.expand(subject));
  }
    
    
  public void testGraphRules() {
    Term t1 = (Term) mAbstractType.expand(`g(refTerm("a"),labTerm("a",a())));
    try {
      assertEquals(`f(b()),Term.Test().visit(t1));
    } catch(VisitFailure e) { fail(); }
    Term t2 = (Term) mAbstractType.expand(`f(g(g(a(),refTerm("b")),labTerm("b",b()))));
    try {
      assertEquals(`f(c()),Term.Test().visit(t2));
    } catch(VisitFailure e) { fail(); }
    Term t3 = `g(g(g(a(),g(pathTerm(-1,-2,1),a())),pathTerm(-2,1,2,2)),pathTerm(-2,1,1,2));
    try {
      assertEquals(`g(g(f(a()),a()),g(pathTerm(-1,-2,1,1,1),pathTerm(-2,-2,1,2))),new Position(new int[]{1,1}).getOmega(Term.Test()).visit(t3));
    } catch(VisitFailure e) { fail(); }
    Term t4 = `g(g(g(f(a()),g(pathTerm(-1,-2,1),a())),pathTerm(-2,1,2,2)),pathTerm(-2,1,1,1,1));
    try {
      assertEquals(`g(g(f(f(a())),a()),pathTerm(-2,1,1,1,1)),new Position(new int[]{1,1}).getOmega(Term.Test()).visit(t4));
    } catch(VisitFailure e) { fail(); }
  }

}
