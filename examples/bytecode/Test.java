/*
 * Copyright (c) 2004-2006, INRIA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the INRIA nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
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
package bytecode;



import java.io.FileReader;

public class Test {

  public static void lire1(String nameFile){
    try {
      new FileReader(nameFile).read();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      new FileReader(nameFile).read();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void lire2(int Entier,String nameFile){
    try {
      new FileReader(nameFile).read();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public static void lire3(String nameFile){
    try {
      FileReader f=new FileReader(nameFile);
      f.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //public static void lire4 (String nameFile){
  //		Test2 t2 = new Test2();
  //		t2.lire1(nameFile);
  //	}
  /*public static void lire4(String nameFile){
    new SecureAccess().sread(nameFile);
    }*/
  /*
     public static void utiliserAutreClassloader () throws ClassNotFoundException{
//ClassLoader clMechant=this.getClass().getClassLoader();
ClassLoader clm = new ClassLoaderMechant();
clm.loadClass("Test3Secure");
     }
   */

  public static void main(String[] args) {
    String nameFile = args[0];
    lire1(nameFile);
    lire2(1,nameFile);
    lire3(nameFile);
    //lire4(nameFile);

  }

}
