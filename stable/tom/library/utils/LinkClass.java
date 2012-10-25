/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2012, INPL, INRIA
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
 * Jean-Christophe Bach e-mail: jeanchristophe.bach@inria.fr
 *
 **/

package tom.library.utils;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.emf.ecore.EObject;

public class LinkClass {
  private ConcurrentMap<EObject,ReferenceClass> table;

  public LinkClass() {
    this.table = new ConcurrentHashMap<EObject,ReferenceClass>();
  }

  public ReferenceClass put(EObject key, ReferenceClass value) {
    return this.table.put(key,value);
  }

  public boolean containsKey(EObject key) {
    return this.table.containsKey(key);
  }

  public ReferenceClass get(EObject key) {
    return this.table.get(key);
  }
}
