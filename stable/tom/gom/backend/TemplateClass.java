/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2006-2008, INRIA
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
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/
package tom.gom.backend;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import java.io.*;

public abstract class TemplateClass {
  protected GomClass gomClass;
  protected ClassName className;

  public TemplateClass(GomClass gomClass) {
    this.gomClass = gomClass;
    this.className = gomClass.getClassName();
  }

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */  

  public abstract void generate(Writer writer) throws java.io.IOException;

  public String className() {
    return className(this.className);
  }

  public String className(ClassName clsName) {
    {if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch357NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.ClassName )clsName);{  tom.gom.adt.objects.types.ClassName  tomMatch357NameNumber_freshVar_1=tomMatch357NameNumberfreshSubject_1;if ( (tomMatch357NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch357NameNumber_freshVar_0= tomMatch357NameNumber_freshVar_1.getName() ;if ( true ) {

        return tomMatch357NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:className got a strange ClassName "+clsName);
  }

  public String fullClassName() {
    return fullClassName(this.className);
  }

  public static String fullClassName(ClassName clsName) {
    {if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch358NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.ClassName )clsName);{  tom.gom.adt.objects.types.ClassName  tomMatch358NameNumber_freshVar_2=tomMatch358NameNumberfreshSubject_1;if ( (tomMatch358NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch358NameNumber_freshVar_0= tomMatch358NameNumber_freshVar_2.getPkg() ;{  String  tomMatch358NameNumber_freshVar_1= tomMatch358NameNumber_freshVar_2.getName() ;{  String  tom_pkgPrefix=tomMatch358NameNumber_freshVar_0;{  String  tom_name=tomMatch358NameNumber_freshVar_1;if ( true ) {

        if(tom_pkgPrefix.length()==0) {
          return tom_name;
        } else {
          return tom_pkgPrefix+"."+tom_name;
        }
      }}}}}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:fullClassName got a strange ClassName "+clsName);
  }

  public String getPackage() {
    return getPackage(this.className);
  }

  public String getPackage(ClassName clsName) {
    {if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch359NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.ClassName )clsName);{  tom.gom.adt.objects.types.ClassName  tomMatch359NameNumber_freshVar_1=tomMatch359NameNumberfreshSubject_1;if ( (tomMatch359NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch359NameNumber_freshVar_0= tomMatch359NameNumber_freshVar_1.getPkg() ;if ( true ) {

        return tomMatch359NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:getPackage got a strange ClassName "+clsName);
  }

  public String hasMethod(SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch360NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch360NameNumber_freshVar_1=tomMatch360NameNumberfreshSubject_1;if ( (tomMatch360NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  String  tomMatch360NameNumber_freshVar_0= tomMatch360NameNumber_freshVar_1.getName() ;if ( true ) {

        return "has"+tomMatch360NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:hasMethod got a strange SlotField "+slot);
  }

  public String getMethod(SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch361NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch361NameNumber_freshVar_1=tomMatch361NameNumberfreshSubject_1;if ( (tomMatch361NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  String  tomMatch361NameNumber_freshVar_0= tomMatch361NameNumber_freshVar_1.getName() ;if ( true ) {

        return "get"+tomMatch361NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:getMethod got a strange SlotField "+slot);
  }

  public String setMethod(SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch362NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch362NameNumber_freshVar_1=tomMatch362NameNumberfreshSubject_1;if ( (tomMatch362NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  String  tomMatch362NameNumber_freshVar_0= tomMatch362NameNumber_freshVar_1.getName() ;if ( true ) {

        return "set"+tomMatch362NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:setMethod got a strange SlotField "+slot);
  }

  public String index(SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch363NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch363NameNumber_freshVar_1=tomMatch363NameNumberfreshSubject_1;if ( (tomMatch363NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  String  tomMatch363NameNumber_freshVar_0= tomMatch363NameNumber_freshVar_1.getName() ;if ( true ) {

        return "index_"+tomMatch363NameNumber_freshVar_0;
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:index got a strange SlotField "+slot);
  }

  public String slotDomain(SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch364NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch364NameNumber_freshVar_1=tomMatch364NameNumberfreshSubject_1;if ( (tomMatch364NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch364NameNumber_freshVar_0= tomMatch364NameNumber_freshVar_1.getDomain() ;if ( true ) {

        return fullClassName(tomMatch364NameNumber_freshVar_0);
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:slotDomain got a strange SlotField "+slot);
  }

  private String fieldName(String fieldName) {
    return "_"+fieldName;
  }

  public String classFieldName(ClassName clsName) {
    {if ( (clsName instanceof tom.gom.adt.objects.types.ClassName) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch365NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.ClassName )clsName);{  tom.gom.adt.objects.types.ClassName  tomMatch365NameNumber_freshVar_1=tomMatch365NameNumberfreshSubject_1;if ( (tomMatch365NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch365NameNumber_freshVar_0= tomMatch365NameNumber_freshVar_1.getName() ;if ( true ) {

        return tomMatch365NameNumber_freshVar_0.toLowerCase();
      }}}}}}}

    throw new GomRuntimeException(
        "TemplateClass:classFieldName got a strange ClassName "+clsName);
  }

  public void toStringSlotField(StringBuilder res, SlotField slot,
                                String element, String buffer) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch366NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch366NameNumber_freshVar_1=tomMatch366NameNumberfreshSubject_1;if ( (tomMatch366NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch366NameNumber_freshVar_0= tomMatch366NameNumber_freshVar_1.getDomain() ;{  tom.gom.adt.objects.types.ClassName  tom_domain=tomMatch366NameNumber_freshVar_0;if ( true ) {

        if(!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+".toStringBuilder("/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+");\n"
);
        } else {
          if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )
              || tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )
              || tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+");\n"
);
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+");\n            "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append(\"l\");\n"

);
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append(((int)"/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+"-(int)'0'));\n"
);
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+"?1:0);\n"
);
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            String atchar = "@";
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\"');\n            for (int i = 0; i < "/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+".length(); i++) {\n              char c = "/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+".charAt(i);\n              switch (c) {\n                case '\\n':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('n');\n                  break;\n                case '\\t':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('t');\n                  break;\n                case '\\b':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('b');\n                  break;\n                case '\\r':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('r');\n                  break;\n                case '\\f':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('f');\n                  break;\n                case '\\\\':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  break;\n                case '\\'':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\'');\n                  break;\n                case '\\\"':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\"');\n                  break;\n                case '!':\n                case '"/* Generated by TOM (version 2.6alpha): Do not edit this file */+atchar+"':\n                case '#':\n                case '$':\n                case '%':\n                case '^':\n                case '&':\n                case '*':\n                case '(':\n                case ')':\n                case '-':\n                case '_':\n                case '+':\n                case '=':\n                case '|':\n                case '~':\n                case '{':\n                case '}':\n                case '[':\n                case ']':\n                case ';':\n                case ':':\n                case '<':\n                case '>':\n                case ',':\n                case '.':\n                case '?':\n                case ' ':\n                case '/':\n                  "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append(c);\n                  break;\n\n                default:\n                  if (java.lang.Character.isLetterOrDigit(c)) {\n                    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append(c);\n                  } else {\n                    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\\\\');\n                    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append((char) ('0' + c / 64));\n                    c = (char) (c % 64);\n                    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append((char) ('0' + c / 8));\n                    c = (char) (c % 8);\n                    "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append((char) ('0' + c));\n                  }\n              }\n            }\n            "/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append('\"');\n"

















































































);
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) ||tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") )) {
            res.append(""/* Generated by TOM (version 2.6alpha): Do not edit this file */+buffer+".append("/* Generated by TOM (version 2.6alpha): Do not edit this file */+element+".toString());\n"
);
          } else {
            throw new GomRuntimeException("Builtin " + tom_domain+ " not supported");
          }
        }
      }}}}}}}}

  }

  public void toATermSlotField(StringBuilder res, SlotField slot) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch367NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch367NameNumber_freshVar_1=tomMatch367NameNumberfreshSubject_1;if ( (tomMatch367NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch367NameNumber_freshVar_0= tomMatch367NameNumber_freshVar_1.getDomain() ;{  tom.gom.adt.objects.types.ClassName  tom_domain=tomMatch367NameNumber_freshVar_0;if ( true ) {

        if(!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res.append(getMethod(slot));
          res.append("().toATerm()");
        } else {
          if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("()?1:0)");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            res.append("(aterm.ATerm) atermFactory.makeLong(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            res.append("(aterm.ATerm) atermFactory.makeInt(((int)");
            res.append(getMethod(slot));
            res.append("()-(int)'0'))");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            res.append("(aterm.ATerm) atermFactory.makeAppl(");
            res.append("atermFactory.makeAFun(");
            res.append(getMethod(slot));
            res.append("() ,0 , true))");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) ||tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") )){
            res.append(getMethod(slot));
            res.append("()");
          } else {
            throw new GomRuntimeException("Builtin " + tom_domain+ " not supported");
          }
        }
      }}}}}}}}

  }

  public void fromATermSlotField(StringBuilder buffer, SlotField slot, String appl) {
    {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch368NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch368NameNumber_freshVar_1=tomMatch368NameNumberfreshSubject_1;if ( (tomMatch368NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch368NameNumber_freshVar_0= tomMatch368NameNumber_freshVar_1.getDomain() ;{  tom.gom.adt.objects.types.ClassName  tom_domain=tomMatch368NameNumber_freshVar_0;if ( true ) {

        if(!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          buffer.append(fullClassName(tom_domain));
          buffer.append(".fromTerm(");
          buffer.append(appl);
          buffer.append(")");
        } else {
          if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "int") )) {
            buffer.append("((aterm.ATermInt)").append(appl).append(").getInt()");
          } else  if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "float") )) {
            buffer.append("(float) ((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "boolean") )) {
            buffer.append("(((aterm.ATermInt)").append(appl).append(").getInt()==0?false:true)");
          } else  if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "long") )) {
            buffer.append("((aterm.ATermLong)").append(appl).append(").getLong()");
          } else  if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "double") )) {
            buffer.append("((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "char") )) {
            buffer.append("(char) (((aterm.ATermInt)").append(appl).append(").getInt()+(int)'0')");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("", "String") )) {
            buffer.append("(String) ((aterm.ATermAppl)").append(appl).append(").getAFun().getName()");
          } else if (tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATerm") ) || tom_domain.equals( tom.gom.adt.objects.types.classname.ClassName.make("aterm", "ATermList") ) ){
            buffer.append(appl);
          } else {
            throw new GomRuntimeException("Builtin " + tom_domain+ " not supported");
          }
        }
      }}}}}}}}

  }

  protected String primitiveToReferenceType(String classname) {
    {if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_0=tomMatch369NameNumberfreshSubject_1;if ( "byte".equals(tomMatch369NameNumber_freshVar_0) ) {if ( true ) {
 return "java.lang.Byte"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_1=tomMatch369NameNumberfreshSubject_1;if ( "short".equals(tomMatch369NameNumber_freshVar_1) ) {if ( true ) {
 return "java.lang.Short"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_2=tomMatch369NameNumberfreshSubject_1;if ( "int".equals(tomMatch369NameNumber_freshVar_2) ) {if ( true ) {
 return "java.lang.Integer"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_3=tomMatch369NameNumberfreshSubject_1;if ( "long".equals(tomMatch369NameNumber_freshVar_3) ) {if ( true ) {
 return "java.lang.Long"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_4=tomMatch369NameNumberfreshSubject_1;if ( "float".equals(tomMatch369NameNumber_freshVar_4) ) {if ( true ) {
 return "java.lang.Float"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_5=tomMatch369NameNumberfreshSubject_1;if ( "double".equals(tomMatch369NameNumber_freshVar_5) ) {if ( true ) {
 return "java.lang.Double"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_6=tomMatch369NameNumberfreshSubject_1;if ( "boolean".equals(tomMatch369NameNumber_freshVar_6) ) {if ( true ) {
 return "java.lang.Boolean"; }}}}}if ( true ) {{  String  tomMatch369NameNumberfreshSubject_1=(( String )classname);{  String  tomMatch369NameNumber_freshVar_7=tomMatch369NameNumberfreshSubject_1;if ( "char".equals(tomMatch369NameNumber_freshVar_7) ) {if ( true ) {
 return "java.lang.Character"; }}}}}}

    return classname;
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".java";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = GomEnvironment.getInstance().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    return output;
  }

  public int generateFile() {
    try {
       File output = fileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
       generate(writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  public String visitMethod(ClassName sortName) {
    return "visit_"+className(sortName);
  }

  public String isOperatorMethod(ClassName opName) {
    return "is"+className(opName);
  }

  public String getCollectionMethod(ClassName opName) {
    return "getCollection"+className(opName);
  }

  protected void slotDecl(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      {if ( (slot instanceof tom.gom.adt.objects.types.SlotField) ) {{  tom.gom.adt.objects.types.SlotField  tomMatch370NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.SlotField )slot);{  tom.gom.adt.objects.types.SlotField  tomMatch370NameNumber_freshVar_2=tomMatch370NameNumberfreshSubject_1;if ( (tomMatch370NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.slotfield.SlotField) ) {{  String  tomMatch370NameNumber_freshVar_0= tomMatch370NameNumber_freshVar_2.getName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch370NameNumber_freshVar_1= tomMatch370NameNumber_freshVar_2.getDomain() ;{  tom.gom.adt.objects.types.ClassName  tomMatch370NameNumber_freshVar_4=tomMatch370NameNumber_freshVar_1;if ( (tomMatch370NameNumber_freshVar_4 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch370NameNumber_freshVar_3= tomMatch370NameNumber_freshVar_4.getName() ;if ( true ) {

          writer.write(tomMatch370NameNumber_freshVar_0);
          writer.write(":");
          writer.write(tomMatch370NameNumber_freshVar_3);
          index++;
        }}}}}}}}}}}

    }
  }

  protected void slotArgs(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("t"+index);
      index++;
    }
  }

  protected void slotArgsWithDollar(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyConcSlotField()) {
      SlotField slot = slotList.getHeadConcSlotField();
      slotList = slotList.getTailConcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("$t"+index);
      index++;
    }
  }

  public void generateTomMapping(Writer writer)
      throws java.io.IOException {
    return;
  }

}
