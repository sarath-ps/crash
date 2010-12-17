/*
 * Copyright (C) 2010 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.crsh.command.info;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class OptionInfo extends ParameterInfo {

  /** . */
  private final int arity;

  /** . */
  private final List<String> names;

  public OptionInfo(
    Type javaType,
    List<String> names,
    String description,
    boolean required,
    int arity,
    boolean password) throws IllegalValueTypeException, IllegalParameterException {
    super(
      javaType,
      description,
      required,
      password);

    //
    if (arity > 1 && getType().getMultiplicity() == Multiplicity.SINGLE) {
      throw new IllegalParameterException();
    }

    //
    if (getType().getMultiplicity() == Multiplicity.LIST && getType().getValueType() == SimpleValueType.BOOLEAN) {
      throw new IllegalParameterException();
    }

    //
    for (String name : names) {
      if (name == null || name.length() == 0) {
        throw new IllegalParameterException();
      }
    }

    //
    this.arity = arity;
    this.names = Collections.unmodifiableList(new ArrayList<String>(names));
  }

  public int getArity() {
    return arity;
  }

  public List<String> getNames() {
    return names;
  }
}