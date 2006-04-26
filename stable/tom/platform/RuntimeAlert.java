/* Generated by TOM (version 2.3rc1): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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

package tom.platform;

import aterm.pure.*;
import java.util.logging.*;

import tom.platform.adt.platformalert.*;
import tom.platform.adt.platformalert.types.*;

public class RuntimeAlert {
  
  /* Generated by TOM (version 2.3rc1): Do not edit this file *//* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private  char  tom_set_stamp_char( char  c) {  return  c  ;}private void tom_check_stamp_char( char  c) { ;}private boolean tom_terms_equal_char( char  t1,  char  t2) {  return  (t1==t2)  ;}private  Character  tom_set_stamp_Character( Character  c) {  return  c  ;}private void tom_check_stamp_Character( Character  c) { ;}private boolean tom_terms_equal_Character(Object t1, Object t2) {  return  (t1.equals(t2))  ;}private boolean tom_is_fun_sym_Char( Character  t) {  return  (t!= null) && (t instanceof Character)  ;}private  Character  tom_make_Char( char  c) { return  new Character(c) ; }private  char  tom_get_slot_Char_c( Character  t) {  return  t.charValue()  ;} private  String  tom_set_stamp_String( String  s) {  return  s  ;}private void tom_check_stamp_String( String  s) { ;}private boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}private boolean tom_is_fun_sym_concString( String  t) {  return  (t!= null) && (t instanceof String)  ;}private  String  tom_empty_list_concString() { return  "" ; }private  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }private  char  tom_get_head_concString_String( String  s) {  return  s.charAt(0)  ;}private  String  tom_get_tail_concString_String( String  s) {  return  s.substring(1)  ;}private boolean tom_is_empty_concString_String( String  s) {  return  (s.length()==0)  ;}private  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_concString_String(l1)) {     return l2;    } else if(tom_is_empty_concString_String(l2)) {     return l1;    } else if(tom_is_empty_concString_String(( String )tom_get_tail_concString_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),tom_append_list_concString(( String )tom_get_tail_concString_String(l1),l2));    }   }  private  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_concString_String(begin),end));    }   }    /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */ private boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_double( double  t1,  double  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_ATerm(Object t1, Object t2) {  return  t1 == t2 ;} /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private boolean tom_terms_equal_ATermList(Object l1, Object l2) {  return  l1==l2  ;} private  tom.platform.adt.platformalert.types.AlertList tom_get_implementation_AlertList( tom.platform.adt.platformalert.types.AlertList t) {  return t ;}private  tom.platform.adt.platformalert.types.AlertList tom_set_stamp_AlertList( tom.platform.adt.platformalert.types.AlertList t) {  return (tom.platform.adt.platformalert.types.AlertList)t.setAnnotation(aterm.pure.SingletonFactory.getInstance().makeList(),aterm.pure.SingletonFactory.getInstance().makeList()) ;}private void tom_check_stamp_AlertList( tom.platform.adt.platformalert.types.AlertList t) { if(t.getAnnotation(aterm.pure.SingletonFactory.getInstance().makeList()) == aterm.pure.SingletonFactory.getInstance().makeList())  return; else throw new RuntimeException("bad stamp");}private boolean tom_terms_equal_AlertList(Object t1, Object t2) {  return t1.equals(t2) ;}private boolean tom_is_fun_sym_concAlert( tom.platform.adt.platformalert.types.AlertList t) {  return (t!= null) && t.isSortAlertList() ;}private  tom.platform.adt.platformalert.types.AlertList tom_empty_list_concAlert() { return tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlertList(); }private  tom.platform.adt.platformalert.types.AlertList tom_cons_list_concAlert( tom.platform.adt.platformalert.types.Alert e,  tom.platform.adt.platformalert.types.AlertList l) { return tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlertList(e,l); }private  tom.platform.adt.platformalert.types.Alert tom_get_head_concAlert_AlertList( tom.platform.adt.platformalert.types.AlertList l) {  return l.getHead() ;}private  tom.platform.adt.platformalert.types.AlertList tom_get_tail_concAlert_AlertList( tom.platform.adt.platformalert.types.AlertList l) {  return l.getTail() ;}private boolean tom_is_empty_concAlert_AlertList( tom.platform.adt.platformalert.types.AlertList l) {  return l.isEmpty() ;}private  tom.platform.adt.platformalert.types.AlertList tom_append_list_concAlert( tom.platform.adt.platformalert.types.AlertList l1,  tom.platform.adt.platformalert.types.AlertList l2) {    if(tom_is_empty_concAlert_AlertList(l1)) {     return l2;    } else if(tom_is_empty_concAlert_AlertList(l2)) {     return l1;    } else if(tom_is_empty_concAlert_AlertList(( tom.platform.adt.platformalert.types.AlertList)tom_get_tail_concAlert_AlertList(l1))) {     return ( tom.platform.adt.platformalert.types.AlertList)tom_cons_list_concAlert(( tom.platform.adt.platformalert.types.Alert)tom_get_head_concAlert_AlertList(l1),l2);    } else {      return ( tom.platform.adt.platformalert.types.AlertList)tom_cons_list_concAlert(( tom.platform.adt.platformalert.types.Alert)tom_get_head_concAlert_AlertList(l1),tom_append_list_concAlert(( tom.platform.adt.platformalert.types.AlertList)tom_get_tail_concAlert_AlertList(l1),l2));    }   }  private  tom.platform.adt.platformalert.types.AlertList tom_get_slice_concAlert( tom.platform.adt.platformalert.types.AlertList begin,  tom.platform.adt.platformalert.types.AlertList end) {    if(tom_terms_equal_AlertList(begin,end)) {      return ( tom.platform.adt.platformalert.types.AlertList)tom_empty_list_concAlert();    } else {      return ( tom.platform.adt.platformalert.types.AlertList)tom_cons_list_concAlert(( tom.platform.adt.platformalert.types.Alert)tom_get_head_concAlert_AlertList(begin),( tom.platform.adt.platformalert.types.AlertList)tom_get_slice_concAlert(( tom.platform.adt.platformalert.types.AlertList)tom_get_tail_concAlert_AlertList(begin),end));    }   }  private boolean tom_is_fun_sym_emptyAlertList( tom.platform.adt.platformalert.types.AlertList t) {  return  (t!= null) && t.isEmpty() ;}private  tom.platform.adt.platformalert.types.AlertList tom_make_emptyAlertList() { return tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlertList(); }private boolean tom_is_fun_sym_manyAlertList( tom.platform.adt.platformalert.types.AlertList t) {  return  (t!= null) && t.isMany() ;}private  tom.platform.adt.platformalert.types.AlertList tom_make_manyAlertList( tom.platform.adt.platformalert.types.Alert e,  tom.platform.adt.platformalert.types.AlertList l) { return tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlertList(e,l); }private  tom.platform.adt.platformalert.types.Alert tom_get_slot_manyAlertList_head( tom.platform.adt.platformalert.types.AlertList t) {  return  t.getHead() ;}private  tom.platform.adt.platformalert.types.AlertList tom_get_slot_manyAlertList_tail( tom.platform.adt.platformalert.types.AlertList t) {  return  t.getTail() ;}private  tom.platform.adt.platformalert.types.Alert tom_get_implementation_Alert( tom.platform.adt.platformalert.types.Alert t) {  return t ;}private  tom.platform.adt.platformalert.types.Alert tom_set_stamp_Alert( tom.platform.adt.platformalert.types.Alert t) {  return (tom.platform.adt.platformalert.types.Alert)t.setAnnotation(aterm.pure.SingletonFactory.getInstance().makeList(),aterm.pure.SingletonFactory.getInstance().makeList()) ;}private void tom_check_stamp_Alert( tom.platform.adt.platformalert.types.Alert t) { if(t.getAnnotation(aterm.pure.SingletonFactory.getInstance().makeList()) == aterm.pure.SingletonFactory.getInstance().makeList())  return; else throw new RuntimeException("bad stamp");}private boolean tom_terms_equal_Alert(Object t1, Object t2) {  return t1.equals(t2) ;}private boolean tom_is_fun_sym_Warning( tom.platform.adt.platformalert.types.Alert t) {  return  (t!= null) && t.isWarning() ;}private  tom.platform.adt.platformalert.types.Alert tom_make_Warning( String  t0,  String  t1,  int  t2) { return  tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlert_Warning(t0, t1, t2); }private  String  tom_get_slot_Warning_message( tom.platform.adt.platformalert.types.Alert t) {  return  t.getMessage() ;}private  String  tom_get_slot_Warning_file( tom.platform.adt.platformalert.types.Alert t) {  return  t.getFile() ;}private  int  tom_get_slot_Warning_line( tom.platform.adt.platformalert.types.Alert t) {  return  t.getLine() ;}private boolean tom_is_fun_sym_Error( tom.platform.adt.platformalert.types.Alert t) {  return  (t!= null) && t.isError() ;}private  tom.platform.adt.platformalert.types.Alert tom_make_Error( String  t0,  String  t1,  int  t2) { return  tom.platform.adt.platformalert.PlatformAlertFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeAlert_Error(t0, t1, t2); }private  String  tom_get_slot_Error_message( tom.platform.adt.platformalert.types.Alert t) {  return  t.getMessage() ;}private  String  tom_get_slot_Error_file( tom.platform.adt.platformalert.types.Alert t) {  return  t.getFile() ;}private  int  tom_get_slot_Error_line( tom.platform.adt.platformalert.types.Alert t) {  return  t.getLine() ;}  

  private AlertList errors;
  private AlertList warnings;
  private int nbErrors;
  private int nbWarnings;

  public RuntimeAlert() {
    errors = tom_empty_list_concAlert();
    warnings = tom_empty_list_concAlert();
    nbErrors = 0;
    nbWarnings = 0;
  }

  public void addWarning(String message, String file, int line) {
    Alert entry = tom_make_Warning(message,file,line);
    warnings = tom_cons_list_concAlert(entry,tom_append_list_concAlert(warnings,tom_empty_list_concAlert()));
    nbWarnings++;
  }
  
  public void addError(String message, String file, int line) {
    Alert entry = tom_make_Error(message,file,line);
    errors = tom_cons_list_concAlert(entry,tom_append_list_concAlert(errors,tom_empty_list_concAlert()));
    nbErrors++;
  }
  
  public int getNbErrors() {
    return nbErrors;
  }

  public int getNbWarnings() {
    return nbWarnings;
  }

  public boolean hasErrors() {
    return (nbErrors>0);
  }

  public boolean hasWarnings() {
    return (nbWarnings>0);
  }

  public AlertList getErrors() {
    return errors;
  }

  public AlertList getWarnings() {
    return warnings;
  }
  
  public void concat(RuntimeAlert newErrors) {
    if(newErrors.getErrors() != tom_empty_list_concAlert()) {
      AlertList newAlerts = newErrors.getErrors();
      errors = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(errors,tom_empty_list_concAlert()));
      nbErrors += newErrors.getNbErrors();      
    }
    if(newErrors.getWarnings() != tom_empty_list_concAlert()) {
      AlertList newAlerts = newErrors.getWarnings();
      warnings = tom_append_list_concAlert(newAlerts,tom_append_list_concAlert(warnings,tom_empty_list_concAlert()));
      nbWarnings += newErrors.getNbWarnings();
    }
  }

  /**
   * @param record
   */
  public void add(PlatformLogRecord record) {
    if(record.getLevel() == Level.SEVERE) {
      addError(record.getMessage(), record.getFilePath(), record.getLine());
    } else if(record.getLevel() == Level.WARNING) {
      addWarning(record.getMessage(), record.getFilePath(), record.getLine());
    }
  }

} //class RuntimeAlert
