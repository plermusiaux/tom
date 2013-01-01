/*
 * Copyright (c) 2004-2013, INPL, INRIA
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

package propp;

import aterm.*;
import aterm.pure.*;
import java.util.*;
import propp.seq.*;
import propp.seq.types.*;
import java.io.*;
import antlr.CommonAST;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class TestPropp {
  private SPropp test;

	public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(TestPropp.class.getName());
	}

  @Before
  public void setUp() {
    test = new SPropp();
  }

  %include { seq/Seq.tom }

  @Test
	public void testComparePair1() {
		assertEquals("Pair(3,\"something\") is greater than Pair(2,\"anything\")",
								 1,
								 test.comparePair(`Pair(3,"something"),`Pair(2,"anything")));
	}

  @Test
	public void testComparePair2() {
		assertEquals("Pair(4,\"something\") is greater than Pair(8,\"anything\")",
								 -1,
								 test.comparePair(`Pair(4,"something"),`Pair(8,"anything")));
	}
}
