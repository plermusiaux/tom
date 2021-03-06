/*
 * Copyright (c) 2004-2015, Universite de Lorraine, Inria
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
 *  - Neither the name of the Inria nor the names of its
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
package lsruntime.graphic;

import java.awt.geom.Point2D;

public class Point3D extends Point2D.Double {
  
  public double direction;
  
  public Point3D(double x, double y, double direction) {
    super(x,y);
    this.direction = direction;
  }
  
  public Point3D(double x, double y) {
    super(x,y);
    this.direction = 0.0;
  }
  
  public Point3D() {
    super();
    this.direction = 0.0;
  }
  
  public Point3D(Point3D p) {
    super(p.getX(),p.getY());
    this.direction = p.getDirection();
  }
  
  public double getDirection() {
    return direction;
  }
  
  public void setDirection(double dir) {
    this.direction = dir;
  }
  
  public void setLocation(Point3D p) {
    this.setLocation((Point2D) p);
    this.direction = p.getDirection();
  }
  
  public String toString() {
    String buffer = super.toString() + "dir:"+getDirection();
    return buffer;
  }
  
  public void avance(double longueur) {
    double new_x = getX() + longueur * Math.cos( Math.toRadians(getDirection()) );
    double new_y = getY() + longueur * Math.sin( Math.toRadians(getDirection()) );
    setLocation(new_x,new_y);
  }
  
  public void tourne(double angle) {
    direction += angle;
  }
}
