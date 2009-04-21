/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2009, INRIA
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
 * Radu Kopetz e-mail: Radu.Kopetz@loria.fr
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/
package tom.library.sl;

/**                                                                                                                
 * An abstract implementation of Strategy, used 
 * as a super class by the class generated from %strategy    
 *
 */

public abstract class BasicStrategy implements Strategy {

  protected tom.library.sl.Environment environment;
  protected Strategy any;
  
  public BasicStrategy(Strategy v) {
    this.any = v;
  }
 
  public int getChildCount() {
    return 1;
  }
    
  public Visitable getChildAt(int i) {
    switch (i) {
      case 0: return any;
      default: throw new IndexOutOfBoundsException();
    }
  }

  public Visitable setChildAt(int i, Visitable child) {
    switch (i) {
      case 0: any = (Strategy) child; return this;
      default: throw new IndexOutOfBoundsException();
    }
  }

  public Visitable[] getChildren() {
    return new Visitable[]{any};
  }

  public Visitable setChildren(Visitable[] children) {
    any = (Strategy)children[0];
    return this;
  }

  public tom.library.sl.Environment getEnvironment() {
    if(environment!=null) {
      return environment;
    } else {
      throw new java.lang.RuntimeException("environment not initialized");
    }
  }

  public Position getPosition() {
    if(environment!=null) {
      return environment.getPosition();
    } else {
      throw new RuntimeException("environment not initialized");
    }
  }

  public Object getAncestor() {
    if(environment!=null) {
      return environment.getAncestor();
    } else {
      throw new RuntimeException("environment not initialized");
    }
  }

  public void setEnvironment(tom.library.sl.Environment env) {
    this.environment = env;
  }

  public Object visit(Environment envt, Introspector introspector) throws VisitFailure {
    setEnvironment(envt);
    int status = visit(introspector);
    if(status == Environment.SUCCESS) {
      return environment.getSubject();
    } else {
      throw new VisitFailure();
    }
  }


  public <T> T visit(T any, Introspector introspector) throws VisitFailure {
    tom.library.sl.AbstractStrategy.init(this,new tom.library.sl.Environment(introspector));
    environment.setRoot(any);
    int status = visit(introspector);
    if(status == tom.library.sl.Environment.SUCCESS) {
      return (T) environment.getRoot();
    } else {
      throw new VisitFailure();
    }
  }

  public int visit(Introspector introspector) {
    try {
      environment.setSubject(this.visitLight(environment.getSubject(),introspector));
      return tom.library.sl.Environment.SUCCESS;
    } catch(VisitFailure f) {
      return tom.library.sl.Environment.FAILURE;
    }
  }

  public abstract <T> T visitLight(T v, Introspector introspector) throws VisitFailure;

  public <T extends Visitable> T visit(Environment envt) throws VisitFailure {
    return (T) visit(envt,VisitableIntrospector.getInstance());
  }

  public <T extends Visitable> T visit(T any) throws VisitFailure {
    return (T) visit(any,VisitableIntrospector.getInstance());
  }

  public <T extends Visitable> T visitLight(T any) throws VisitFailure {
    return (T) visitLight(any,VisitableIntrospector.getInstance());
  }

}
