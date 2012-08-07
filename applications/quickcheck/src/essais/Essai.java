/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package essais;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;
import definitions.Scope;
import definitions.Sort;

/**
 *
 * @author hubert
 */
class Essai {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Scope scope = new Scope();

    Sort expr = new Sort(scope, "expr");
    expr.addConstructor("zero");
    expr.addConstructor("un");
    expr.addConstructor("plus", expr, expr);
    expr.addConstructor("mult", expr, expr);

    scope.setDependances();

    ATerm term = expr.generate(10);

    int n = term.getChildCount();

    System.out.println(term);
    System.out.println(n);

    for (int i = 0; i < n; i++) {
      System.out.println(term.getChildAt(i));
    }

    System.out.println(term.getChildAt(0) instanceof ATermAppl);

    System.out.println("=======================================");

    PureFactory factory = new PureFactory();
    ATermList list = factory.makeList(expr.generate(5));
    System.out.println(list);
    ATermList list2 = list.append(expr.generate(5));
    System.out.println(list);
    System.out.println(list2);
  }
}