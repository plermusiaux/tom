/*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2003  LORIA (CNRST, INPL, INRIA, UHP, U-Nancy 2)
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

    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr

*/

package jtom.runtime;

import aterm.*;
import aterm.pure.*;
import java.util.*;

public class GenericTraversal {

    /*
     * Traverse a subject and collect
     */
  public void genericCollect(ATerm subject, Collect1 collect) {
    genericCollectArray(subject, collect, new ATerm[] {});
  }

  public void genericCollect(ATerm subject, Collect2 collect, Object arg1) {
    genericCollectArray(subject, collect, new Object[] {arg1});
  }

  public void genericCollect(ATerm subject, Collect3 collect, Object arg1, Object arg2) {
    genericCollectArray(subject, collect, new Object[] {arg1,arg2});
  }

  public void genericCollect(ATerm subject, Collect4 collect, Object arg1, Object arg2, Object arg3) {
    genericCollectArray(subject, collect, new Object[] {arg1,arg2,arg3});
  }
 
    /*
     * Traverse a subject and replace
     */
  public ATerm genericTraversal(ATerm subject, Replace1 replace) {
    return genericTraversalArray(subject, replace, new ATerm[] {});
  }

  public ATerm genericTraversal(ATerm subject, Replace2 replace, Object arg1) {
    return genericTraversalArray(subject, replace, new Object[] {arg1});
  }

  public ATerm genericTraversal(ATerm subject, Replace3 replace, Object arg1, Object arg2) {
    return genericTraversalArray(subject, replace, new Object[] {arg1,arg2});
  }
  
    /*
     * Traverse a subject and collect
     * %all(subject, collect(vTable,subject,f)); 
     */
  protected void genericCollectArray(ATerm subject, Collect collect, Object[] args) {
    try {
      if(collect.apply(subject,args)) { 
        if(subject instanceof ATermAppl) { 
          ATermAppl subjectAppl = (ATermAppl) subject; 
          for(int i=0 ; i<subjectAppl.getArity() ; i++) {
            ATerm term = subjectAppl.getArgument(i);
            genericCollectArray(term,collect,args); 
          } 
        } else if(subject instanceof ATermList) { 
          ATermList subjectList = (ATermList) subject; 
          while(!subjectList.isEmpty()) { 
            genericCollectArray(subjectList.getFirst(),collect,args); 
            subjectList = subjectList.getNext(); 
          } 
        } else if(subject instanceof ATermInt) {
          ATermInt subjectInt = (ATermInt) subject;
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Please, extend genericCollectArray");
      System.exit(0);
    }
  } 

  protected ATerm genericTraversalArray(ATerm subject, Replace replace, Object[] args) {
    ATerm res = subject;
    try {
      if(subject instanceof ATermAppl) { 
        res = genericMapterm((ATermAppl) subject, replace, args);
      } else if(subject instanceof ATermList) {
        res = genericMap((ATermList) subject, replace, args);
      } else if(subject instanceof ATermInt) {
        res = subject;
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Please, extend genericTraversalArray");
      System.exit(0);
    }
    return res;
  } 

 
    /*
     * Apply a function to each element of a list
     */
  private ATermList genericMap(ATermList subject, Replace replace, Object[] args) {
    ATermList res = subject;
    try {
      if(!subject.isEmpty()) {
        ATerm term = replace.apply(subject.getFirst(),args);
        ATermList list = genericMap(subject.getNext(),replace, args);
        res = list.insert(term);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Please, extend genericMap");
      System.exit(0);
    }
    return res;
  }

    /*
     * Apply a function to each subterm of a term
     */
  private ATermAppl genericMapterm(ATermAppl subject, Replace replace, Object[] args) {
    try {
      ATerm newSubterm;
      for(int i=0 ; i<subject.getArity() ; i++) {
        newSubterm = replace.apply(subject.getArgument(i),args);
        if(newSubterm != subject.getArgument(i)) {
          subject = subject.setArgument(newSubterm,i);
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("Please, extend genericMapterm");
      System.exit(0);
    }
    return subject;
  }

 
  public void genericCollectReach(ATerm subject, CollectReach collect,
                                 Collection collection) {
    try {
      if(subject instanceof ATermAppl) {
        ATerm newSubterm;
        ATermAppl subjectAppl = (ATermAppl) subject;
        collect.apply(subject,collection);
        for(int i=0 ; i<subjectAppl.getArity() ; i++) {
          Collection tmpCollection = new ArrayList();
          genericCollectReach(subjectAppl.getArgument(i),collect,tmpCollection);
          Iterator it = tmpCollection.iterator();
          while(it.hasNext()) {
            collection.add(subjectAppl.setArgument((ATerm)it.next(),i));
          }
        }
      } 
    } catch(Exception e) {
      System.out.println("exception: " + e);
      System.out.println("Please, extend genericCollectReplace");
      System.exit(0);
    }
  } 

  
}
