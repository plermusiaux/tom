/*
 * Copyright (c) 2004-2006, INRIA
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

package strategy;

import strategy.term.*;
import strategy.term.types.*;

import tom.library.strategy.mutraveler.MuTraveler;
import tom.library.strategy.mutraveler.Position;
import tom.library.strategy.mutraveler.Identity;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.Visitable;
import jjtraveler.VisitFailure;

public class Rewrite1 {

  %include { term/term.tom }
  %include { mutraveler.tom }
  
  public final static void main(String[] args) {
    Rewrite1 test = new Rewrite1();
    test.run();
  }

  public void run() {
    //Term subject = `g(d(),d());
    Term subject = `f(g(g(a(),b()),g(a(),a())));

   VisitableVisitor rule = `RewriteSystem(subject);
   VisitableVisitor ruleId = `RewriteSystemId();

    try {
      System.out.println("subject       = " + subject);
      System.out.println("onceBottomUp  = " + MuTraveler.init(`OnceBottomUp(rule)).visit(subject));
      System.out.println("onceBottomUpId= " + MuTraveler.init(`OnceBottomUpId(ruleId)).visit(subject));
      System.out.println("bottomUp      = " + MuTraveler.init(`BottomUp(Try(rule))).visit(subject));
      System.out.println("bottomUpId    = " + MuTraveler.init(`BottomUp(ruleId)).visit(subject));
      System.out.println("innermost     = " + MuTraveler.init(`Innermost(rule)).visit(subject));
      System.out.println("innermostSlow = " + MuTraveler.init(`Repeat(OnceBottomUp(rule))).visit(subject));
      System.out.println("innermostId   = " + MuTraveler.init(`InnermostId(ruleId)).visit(subject));
    } catch (VisitFailure e) {
      System.out.println("reduction failed on: " + subject);
    }

  }

  %strategy RewriteSystem(subject:Term) extends Fail() {
    visit Term {
      a() -> { 
        Position pos = getPosition();
        System.out.println("a -> b at " + pos);
        System.out.println(subject + " at " + pos + " = " + pos.getSubterm().visit(subject));
        System.out.println("rwr into: " + pos.getReplace(`b()).visit(subject));

        return `b();
      }
      b() -> {
        System.out.println("b -> c at " + getPosition());
        return `c();
      }
      g(c(),c()) -> {
        System.out.println("g(c,c) -> c at " + getPosition());
        return `c(); }
    }
  }

  %strategy RewriteSystemId() extends `Identity() {
    visit Term {
      a() -> {
        System.out.println("a -> b at " + getPosition());
        return `b();
      }
      b() -> {
        System.out.println("b -> c at " + getPosition());
        return `c();
      }
      g(c(),c()) -> {
        System.out.println("g(c,c) -> c at " + getPosition());
        return `c();
      }
    }
  }
} 
