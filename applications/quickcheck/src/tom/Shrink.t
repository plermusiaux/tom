package logic.model;

import aterm.ATerm;
import aterm.ATermList;
import aterm.ATermPlaceholder;
import aterm.pure.PureFactory;
import java.util.*;
import tom.library.sl.Strategy;
import tom.library.sl.VisitFailure;

public class Shrink{
  %include{int.tom}
  %include{string.tom}
  %include{aterm.tom}

  %include{sl.tom}
  %include { util/types/Collection.tom }
  %typeterm DomainInterpretation { 
    implement {DomainInterpretation} 
    is_sort(t) { ($t instanceof DomainInterpretation) } 
  }

  private static DomainInterpretation getCorrespondingDomain(DomainInterpretation[] subDoms, ATerm term){
    for(int i = 0; i<subDoms.length; i++){
      if(subDoms[i].includes(term)){
        return subDoms[i];
      }
    }
    throw new UnsupportedOperationException("The term " + term + " is not included in " + Arrays.toString(subDoms));
  }


  private static ATermList s1_aux(ATermList list, DomainInterpretation domain){
    %match(list){
      concATerm() -> {return `concATerm();}
      concATerm(hd, tl*) -> {
        if(domain.includes(`hd)){
          ATermList tail = `s1_aux(tl*, domain);
          return `concATerm(hd, tail*);
        } else {
          %match(`hd){
            ATermAppl(_, listFields) -> {
              ATermList a = s1_aux(`listFields, domain);
              ATermList b = s1_aux(`tl*, domain);
              return `concATerm(a*, b*);
            }
            _ -> {throw new UnsupportedOperationException();}
          }
        }
      }
    }
    return null; // unreachable
  }

  public static ATermList s1(ATerm term, DomainInterpretation domain){
    ATermList list = null;
    breakmatch : {
      %match(term){
        ATermAppl(_, listFields) -> {list = s1_aux(`listFields, domain); break breakmatch;}
        _ -> {throw new UnsupportedOperationException();}
      }
    }
    if(list.isEmpty()){
      return `concATerm(term);
    } else {
      return list;
    }
  }

  public static ATermList s1(ATermList list, DomainInterpretation domain){
    if (list.isEmpty()){
      return list;
    }
    ATerm head = list.getFirst();
    return s1(head, domain).concat(s1(list.getNext(), domain));
  }

  public static ATermList s1WithDepth(ATerm term, DomainInterpretation domain, int depth){
    if(depth==0) {
      return s1(term, domain);
    }
    
    DomainInterpretation[] subDoms = domain.getDepsDomains();
    
    ATermFactory factory = term.getFactory();
    AFun fun = ((ATermAppl) term).getAFun();
    ATerm[] args = ((ATermAppl) term).getArgumentArray();
    int n = term.getChildCount();
    
    ATermList list = factory.makeList();
    for(int childIndex = 0; childIndex<n; childIndex++){
      ATerm child = args[childIndex];
      DomainInterpretation dom = getCorrespondingDomain(subDoms, child);
      ATermList l = s1WithDepth(child, dom, depth-1);
      
      
      while(!l.isEmpty()){
        ATerm[] newArgs = Arrays.copyOf(args, n); // here is compulsory
        ATerm head = l.getFirst();
        l = l.getNext();
        
      }
    }
    return list;
  }

  public static Collection s1bis(ATerm term, DomainInterpretation domain) {
    Collection bag = new HashSet();
    try {
      `TopDownStopOnSuccess(SelectSameType(bag,domain,term)).visitLight(term, new LocalIntrospector());
    } catch (VisitFailure e) {
      System.out.println("failure");
    }
    return bag;
  }

  %strategy SelectSameType(bag:Collection,domain:DomainInterpretation,root:ATerm) extends Fail() {
    visit ATerm { 
      subject@ATermAppl[] -> {
        if(`subject != root && domain.includes(`subject)) {
          `bag.add(`subject);
          return `subject;
        }
      }
    }
  }



  public static Iterator<ATerm> toIterator(final ATermList list){
    return new Iterator<ATerm>() {

      private ATermList state = list;

      @Override
      public boolean hasNext(){
        return !state.isEmpty();
      }
    
      @Override
      public ATerm next(){
        if(hasNext()){
          ATerm res = state.getFirst();
          state = state.getNext();
          return res;
        } else {
          throw new NoSuchElementException();
        }
      }

      @Override
      public void remove(){
        state = state.removeElementAt(0);
      }

    };
  }


}

