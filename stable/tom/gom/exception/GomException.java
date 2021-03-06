/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2016, Universite de Lorraine, Inria
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

package tom.gom.exception;

import java.text.MessageFormat;
import tom.platform.PlatformMessage;

public class GomException extends Exception {
  private static final long serialVersionUID = 1L;

  protected final PlatformMessage msg;
  protected final Object[] detail;

  public GomException(PlatformMessage message) {
    super(message.getMessage());
    this.msg = message;
    this.detail = new Object[]{};
  }

  public GomException(PlatformMessage message, Object[] detail) {
    super(message.getMessage());
    this.msg = message;
    this.detail = detail.clone();
  }

  public PlatformMessage getPlatformMessage() {
    return msg;
  }

  public Object[] getParameters() {
    return detail;
  }

  public String toString() {
    return MessageFormat.format(msg.getMessage(),getParameters());
  }
}
