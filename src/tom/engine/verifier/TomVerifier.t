/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
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

package jtom.verifier;

import jtom.*;

import aterm.*;
import java.util.*;
import java.util.logging.*;
import jtom.runtime.*;
import jtom.adt.tomsignature.types.*;
import jtom.adt.options.types.*;
import jtom.TomMessage;

/**
 * The TomVerifier plugin.
 */
public class TomVerifier extends TomGenericPlugin {

  %include{ ../adt/TomSignature.tom }
  %include{ ../adt/Options.tom }

  protected Verifier verif;

  public TomVerifier() {
    super("TomVerifier");
    verif = new Verifier();
  }

  public void run() {
    if(isActivated()) {
      try {
	long startChrono = System.currentTimeMillis();
	boolean verbose = getServer().getOptionBooleanValue("verbose");

	TomTerm extractTerm = `emptyTerm();
	// here the extraction stuff
			
	Collection matchSet = collectMatch( (TomTerm)getTerm() );
	// System.out.println("Extracted : " + matchSet);
		
	Collection purified = purify(matchSet);
	// System.out.println("Purified : " + purified);
			
	Collection derivations = getDerivations(purified);

	if(verbose)
	    System.out.println("TOM verification phase (" +(System.currentTimeMillis()-startChrono)+ " ms)");
	    
	environment().printAlertMessage("TomVerifier");
	
	if(!environment().isEclipseMode())
	    {
		// remove all warning (in command line only)
		environment().clearWarnings();
	    }
      }
      catch (Exception e) 
	  {
	      getLogger().log( Level.SEVERE,
			       "ExceptionMessage",
			       new Object[]{environment().getInputFile().getName(), "TomVerifier", e.getMessage()} );
	      e.printStackTrace();
	  }
    }
    else
	{
	    boolean verbose = getServer().getOptionBooleanValue("verbose");
	    
	    if(verbose)
		System.out.println("The verifier is not activated and thus WILL NOT RUN.");
	}
  }

  public TomOptionList declaredOptions() {
    return `concTomOption(OptionBoolean("verify", "", "Verify correctness of match compilation", False()) // activation flag
			  );
  }

  private boolean isActivated() {
    return getServer().getOptionBooleanValue("verify");
  }



    private Collect2 collect_match = new Collect2() {
	    public boolean apply(ATerm subject, Object astore) {
		Collection store = (Collection)astore;
		if (subject instanceof Instruction) {
		    %match(Instruction subject) {
			CompiledMatch(automata, (_*,TomTermToOption(PatternList(_)),_*))  -> {
			    store.add(subject);
			}

			// default rule
			_ -> {
			    return true;
			}
		    }//end match
		} else { 
		    return true;
		}
	    }//end apply
	}; //end new

    public Collection collectMatch(TomTerm subject) {
	Collection result = new HashSet();
	traversal().genericCollect(subject,collect_match,result);
	//collect_matching.apply(subject, result);
	return result;
    }

    public Collection purify(Collection subject) {
	Collection purified = new HashSet();
	Iterator it = subject.iterator();
	while (it.hasNext()) {
	    Instruction cm = (Instruction)it.next();
	    %match(Instruction cm) {
		CompiledMatch(automata, options)  -> {
		    // simplify the IL automata
		    purified.add(`CompiledMatch(simplify_il(automata),options));
		}
	    }
	}
	return purified;
    }

    Replace1 replace_simplify_il = new Replace1() {
	    public ATerm apply(ATerm subject) {
		if (subject instanceof Expression) {
		    %match(Expression subject) {
			Or(cond,FalseTL()) -> {
			    return traversal().genericTraversal(`cond,this);
			}
		    }
		} // end instanceof Expression
		else if (subject instanceof Instruction) {
		    %match(Instruction subject) {
			IfThenElse(TrueTL(),success,Nop()) -> {
			    return traversal().genericTraversal(`success,this);
			}
		    }
		} // end instanceof Instruction
		/*
		 * Default case : Traversal
		 */
		return traversal().genericTraversal(subject,this);
	    }//end apply
	};//end new Replace1 simplify_il
	
    private Instruction simplify_il(Instruction subject) {
	return (Instruction) replace_simplify_il.apply(subject);
    }

    public Collection getDerivations(Collection subject) {
	Collection derivations = new HashSet();
	Iterator it = subject.iterator();
	while (it.hasNext()) {
	    Instruction cm = (Instruction)it.next();
	    %match(Instruction cm) {
		CompiledMatch(automata, options)  -> {
		    derivations.add(`CompiledMatch(apply_replace_getDerivations(automata),options));
		}
	    }
	}
	return derivations;
    }

    private Instruction apply_replace_getDerivations(Instruction subject) {
	return (Instruction) replace_getDerivations.apply(subject);
    }

    Replace1 replace_getDerivations = new Replace1() {
	    public ATerm apply(ATerm subject) {
		if (subject instanceof Instruction) {
		    %match(Instruction subject) {
			CompiledPattern(patterns,automata) -> {
			    verif.build_tree(automata);
			    // do not modify the term (for now at least)
			    return traversal().genericTraversal(subject,this);
			}
		    }
		}// end instanceof Instruction
		/*
		 * Default case : Traversal
		 */
		return traversal().genericTraversal(subject,this);
	    }//end apply
	};//end new Replace1 

}
