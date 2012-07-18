/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package definitions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * ne peut être utilisé avec le générateur actuel car on ne doit plus regarder
 * si oui ou non la taille minimal
 *
 * @author hubert
 */
class StrategyMakeMaxDimAtMost extends Strategy {

  @Override
  public Set<Slot> fillATerm(Slot aTerm, int ni, int distStrategy) {
    Set<Slot> res = new HashSet<Slot>();

    //fill the term by choosing one of its constructors
    Slot[] fields = aTerm.chooseMaxDimConstructor();

    //dispatch fields of the term between two categories: these whose dimension 
    //equals dimension of the term, and the others
    int currentDim = aTerm.getDimension();
    Set<Slot> listHigherDimFields = dispatchFields(fields, res, currentDim);

    //spread number of recursions of the curent term into each fields with the 
    //same dimension
    int[] nis = Random.pile(ni - 1, listHigherDimFields.size());

    //re-apply algorithm on same dimension fields in order to eliminate them
    int i = 0;
    for (Slot field : listHigherDimFields) {
      int rand;
      if (nis[i] == 0) {
        rand = 0;
      } else {
        rand = 1 + (int) (Math.random() * nis[i]);
      }
      res.addAll(propagate(field, rand));
      i++;
    }
    return res;
  }
  
  private Collection propagate(Slot field, int rand){
    Set<Slot> res = new HashSet<Slot>();
    
    return null;
  }
}
