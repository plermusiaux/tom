/*
 * Copyright (c) 2009, INRIA
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

package list;

import org.junit.Test;
import org.junit.Assert;

import aterm.*;

public class TestSieve {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(TestSieve.class.getName());
  }

  @Test
  public void testGenereLength() {
    Assert.assertEquals(9,Sieve.genere(10).getLength());
    Assert.assertEquals(49,Sieve.genere(50).getLength());
    Assert.assertEquals(99,Sieve.genere(100).getLength());
  }

  @Test
  public void testGenereValues() {
    ATermList list = Sieve.genere(50).reverse();
    int val = 2;
    while (!list.isEmpty()) {
      int current = ((ATermInt)list.getFirst()).getInt();
      Assert.assertEquals(current,val++);
      list = list.getNext();
    }
  }

  @Test
  public void testElim() {
    ATermList list = Sieve.elim(Sieve.genere(50).reverse());
    while (!list.isEmpty()) {
      int current = ((ATermInt)list.getFirst()).getInt();
      Assert.assertTrue(current + " should be prime",
          isPrime(current));
      list = list.getNext();
    }
  }

  public static boolean isPrime(int n) {
    for(int i=2; i < Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      } 
    }
    return true;
  }
}