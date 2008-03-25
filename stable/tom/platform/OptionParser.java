/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2008, INRIA
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

import java.io.*;

import aterm.*;
import aterm.pure.*;

import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;
import tom.library.xml.*;

/**
 * Helper class to parse OptionOwner options.
 * The options have to comply with the following this DTD
 *
 * <PRE><CODE>
 * < !ELEMENT options (boolean*,integer*,string*) >
 *
 * < !ELEMENT boolean EMPTY >
 * < !ATTLIST boolean
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value (true|false) #REQUIRED >
 *
 * < !ELEMENT integer EMPTY >
 * < !ATTLIST integer
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 *
 * < !ELEMENT string EMPTY >
 * < !ATTLIST string
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 * </CODE></PRE>
 */
public class OptionParser {
  
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if( l1.isEmptyconcTNode() ) {       return l2;     } else if( l2.isEmptyconcTNode() ) {       return l1;     } else if(  l1.getTailconcTNode() .isEmptyconcTNode() ) {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,l2) ;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,tom_append_list_concTNode( l1.getTailconcTNode() ,l2)) ;     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTNode()  ||  (end== tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( begin.getHeadconcTNode() ,( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode( begin.getTailconcTNode() ,end,tail)) ;   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {     if( l1.isEmptyconcPlatformOption() ) {       return l2;     } else if( l2.isEmptyconcPlatformOption() ) {       return l1;     } else if(  l1.getTailconcPlatformOption() .isEmptyconcPlatformOption() ) {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,l2) ;     } else {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,tom_append_list_concPlatformOption( l1.getTailconcPlatformOption() ,l2)) ;     }   }   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end, tom.platform.adt.platformoption.types.PlatformOptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPlatformOption()  ||  (end== tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( begin.getHeadconcPlatformOption() ,( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption( begin.getTailconcPlatformOption() ,end,tail)) ;   }    


  
  /**
   * An XMLTools for doing the stuff
   */
  private static XmlTools xtools = new XmlTools();
  
  /**
   * @return a PlatformOptionList extracted from the a String
   */
  public static PlatformOptionList xmlToOptionList(String xmlString) {
    InputStream stream = new ByteArrayInputStream(xmlString.getBytes());
    TNode node = xtools.convertXMLToTNode(stream);
    return xmlNodeToOptionList(node.getDocElem());
  }
  
  /**
   * @return a PlatformOptionList extracted from a TNode
   */
  public static PlatformOptionList xmlNodeToOptionList(TNode optionsNode) {
    PlatformOptionList list =  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ;
    {{ Object tomMatch574NameNumber_freshVar_0=optionsNode;if ( (tomMatch574NameNumber_freshVar_0 instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch574NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tomMatch574NameNumber_freshVar_0);{  tom.library.adt.tnode.types.TNode  tomMatch574NameNumber_freshVar_4=tomMatch574NameNumberfreshSubject_1;if ( (tomMatch574NameNumber_freshVar_4 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch574NameNumber_freshVar_1= tomMatch574NameNumber_freshVar_4.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_2= tomMatch574NameNumber_freshVar_4.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_3= tomMatch574NameNumber_freshVar_4.getChildList() ;{  String  tomMatch574NameNumber_freshVar_5=tomMatch574NameNumber_freshVar_1;if ( "options".equals(tomMatch574NameNumber_freshVar_5) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_6=tomMatch574NameNumber_freshVar_2;if ( ((tomMatch574NameNumber_freshVar_6 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_6 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_8=tomMatch574NameNumber_freshVar_3;if ( ((tomMatch574NameNumber_freshVar_8 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_8 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_begin_10=tomMatch574NameNumber_freshVar_8;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_end_11=tomMatch574NameNumber_freshVar_8;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_9=tomMatch574NameNumber_end_11;if (!( tomMatch574NameNumber_freshVar_9.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tom_option= tomMatch574NameNumber_freshVar_9.getHeadconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_12= tomMatch574NameNumber_freshVar_9.getTailconcTNode() ;if ( true ) {{{ Object tomMatch575NameNumber_freshVar_0=tom_option;if ( (tomMatch575NameNumber_freshVar_0 instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tomMatch575NameNumber_freshVar_0);{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_4=tomMatch575NameNumberfreshSubject_1;if ( (tomMatch575NameNumber_freshVar_4 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch575NameNumber_freshVar_1= tomMatch575NameNumber_freshVar_4.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_2= tomMatch575NameNumber_freshVar_4.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_3= tomMatch575NameNumber_freshVar_4.getChildList() ;{  String  tomMatch575NameNumber_freshVar_5=tomMatch575NameNumber_freshVar_1;if ( "boolean".equals(tomMatch575NameNumber_freshVar_5) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_6=tomMatch575NameNumber_freshVar_2;if ( ((tomMatch575NameNumber_freshVar_6 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_6 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_8=tomMatch575NameNumber_freshVar_6;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_9=tomMatch575NameNumber_freshVar_6;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_7=tomMatch575NameNumber_end_9;if (!( tomMatch575NameNumber_freshVar_7.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_28= tomMatch575NameNumber_freshVar_7.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_28 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_25= tomMatch575NameNumber_freshVar_28.getName() ;{  String  tomMatch575NameNumber_freshVar_26= tomMatch575NameNumber_freshVar_28.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_27= tomMatch575NameNumber_freshVar_28.getValue() ;{  String  tomMatch575NameNumber_freshVar_29=tomMatch575NameNumber_freshVar_25;if ( "altName".equals(tomMatch575NameNumber_freshVar_29) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_10= tomMatch575NameNumber_freshVar_7.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_12=tomMatch575NameNumber_freshVar_10;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_13=tomMatch575NameNumber_freshVar_10;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_11=tomMatch575NameNumber_end_13;if (!( tomMatch575NameNumber_freshVar_11.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_33= tomMatch575NameNumber_freshVar_11.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_33 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_30= tomMatch575NameNumber_freshVar_33.getName() ;{  String  tomMatch575NameNumber_freshVar_31= tomMatch575NameNumber_freshVar_33.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_32= tomMatch575NameNumber_freshVar_33.getValue() ;{  String  tomMatch575NameNumber_freshVar_34=tomMatch575NameNumber_freshVar_30;if ( "description".equals(tomMatch575NameNumber_freshVar_34) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_14= tomMatch575NameNumber_freshVar_11.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_16=tomMatch575NameNumber_freshVar_14;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_17=tomMatch575NameNumber_freshVar_14;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_15=tomMatch575NameNumber_end_17;if (!( tomMatch575NameNumber_freshVar_15.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_38= tomMatch575NameNumber_freshVar_15.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_38 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_35= tomMatch575NameNumber_freshVar_38.getName() ;{  String  tomMatch575NameNumber_freshVar_36= tomMatch575NameNumber_freshVar_38.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_37= tomMatch575NameNumber_freshVar_38.getValue() ;{  String  tomMatch575NameNumber_freshVar_39=tomMatch575NameNumber_freshVar_35;if ( "name".equals(tomMatch575NameNumber_freshVar_39) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_18= tomMatch575NameNumber_freshVar_15.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_20=tomMatch575NameNumber_freshVar_18;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_21=tomMatch575NameNumber_freshVar_18;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_19=tomMatch575NameNumber_end_21;if (!( tomMatch575NameNumber_freshVar_19.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_43= tomMatch575NameNumber_freshVar_19.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_43 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_40= tomMatch575NameNumber_freshVar_43.getName() ;{  String  tomMatch575NameNumber_freshVar_41= tomMatch575NameNumber_freshVar_43.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_42= tomMatch575NameNumber_freshVar_43.getValue() ;{  String  tomMatch575NameNumber_freshVar_44=tomMatch575NameNumber_freshVar_40;if ( "value".equals(tomMatch575NameNumber_freshVar_44) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_22= tomMatch575NameNumber_freshVar_19.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_24=tomMatch575NameNumber_freshVar_3;if ( ((tomMatch575NameNumber_freshVar_24 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_24 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch575NameNumber_freshVar_24.isEmptyconcTNode() ) {if ( true ) {


 
            PlatformBoolean bool = Boolean.valueOf(tomMatch575NameNumber_freshVar_42).booleanValue()? tom.platform.adt.platformoption.types.platformboolean.True.make() : tom.platform.adt.platformoption.types.platformboolean.False.make() ;
            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch575NameNumber_freshVar_37, tomMatch575NameNumber_freshVar_27, tomMatch575NameNumber_freshVar_32,  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(bool) , "") , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) ); 
          }}}}}}}}}}}}}}if ( tomMatch575NameNumber_end_21.isEmptyconcTNode() ) {tomMatch575NameNumber_end_21=tomMatch575NameNumber_begin_20;} else {tomMatch575NameNumber_end_21= tomMatch575NameNumber_end_21.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_21==tomMatch575NameNumber_begin_20) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_17.isEmptyconcTNode() ) {tomMatch575NameNumber_end_17=tomMatch575NameNumber_begin_16;} else {tomMatch575NameNumber_end_17= tomMatch575NameNumber_end_17.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_17==tomMatch575NameNumber_begin_16) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_13.isEmptyconcTNode() ) {tomMatch575NameNumber_end_13=tomMatch575NameNumber_begin_12;} else {tomMatch575NameNumber_end_13= tomMatch575NameNumber_end_13.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_13==tomMatch575NameNumber_begin_12) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_9.isEmptyconcTNode() ) {tomMatch575NameNumber_end_9=tomMatch575NameNumber_begin_8;} else {tomMatch575NameNumber_end_9= tomMatch575NameNumber_end_9.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_9==tomMatch575NameNumber_begin_8) ));}}}}}}}}}}}}}}{ Object tomMatch575NameNumber_freshVar_45=tom_option;if ( (tomMatch575NameNumber_freshVar_45 instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tomMatch575NameNumber_freshVar_45);{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_49=tomMatch575NameNumberfreshSubject_1;if ( (tomMatch575NameNumber_freshVar_49 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch575NameNumber_freshVar_46= tomMatch575NameNumber_freshVar_49.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_47= tomMatch575NameNumber_freshVar_49.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_48= tomMatch575NameNumber_freshVar_49.getChildList() ;{  String  tomMatch575NameNumber_freshVar_50=tomMatch575NameNumber_freshVar_46;if ( "integer".equals(tomMatch575NameNumber_freshVar_50) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_51=tomMatch575NameNumber_freshVar_47;if ( ((tomMatch575NameNumber_freshVar_51 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_51 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_53=tomMatch575NameNumber_freshVar_51;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_54=tomMatch575NameNumber_freshVar_51;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_52=tomMatch575NameNumber_end_54;if (!( tomMatch575NameNumber_freshVar_52.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_77= tomMatch575NameNumber_freshVar_52.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_77 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_74= tomMatch575NameNumber_freshVar_77.getName() ;{  String  tomMatch575NameNumber_freshVar_75= tomMatch575NameNumber_freshVar_77.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_76= tomMatch575NameNumber_freshVar_77.getValue() ;{  String  tomMatch575NameNumber_freshVar_78=tomMatch575NameNumber_freshVar_74;if ( "altName".equals(tomMatch575NameNumber_freshVar_78) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_55= tomMatch575NameNumber_freshVar_52.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_57=tomMatch575NameNumber_freshVar_55;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_58=tomMatch575NameNumber_freshVar_55;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_56=tomMatch575NameNumber_end_58;if (!( tomMatch575NameNumber_freshVar_56.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_82= tomMatch575NameNumber_freshVar_56.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_82 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_79= tomMatch575NameNumber_freshVar_82.getName() ;{  String  tomMatch575NameNumber_freshVar_80= tomMatch575NameNumber_freshVar_82.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_81= tomMatch575NameNumber_freshVar_82.getValue() ;{  String  tomMatch575NameNumber_freshVar_83=tomMatch575NameNumber_freshVar_79;if ( "attrName".equals(tomMatch575NameNumber_freshVar_83) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_59= tomMatch575NameNumber_freshVar_56.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_61=tomMatch575NameNumber_freshVar_59;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_62=tomMatch575NameNumber_freshVar_59;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_60=tomMatch575NameNumber_end_62;if (!( tomMatch575NameNumber_freshVar_60.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_87= tomMatch575NameNumber_freshVar_60.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_87 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_84= tomMatch575NameNumber_freshVar_87.getName() ;{  String  tomMatch575NameNumber_freshVar_85= tomMatch575NameNumber_freshVar_87.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_86= tomMatch575NameNumber_freshVar_87.getValue() ;{  String  tomMatch575NameNumber_freshVar_88=tomMatch575NameNumber_freshVar_84;if ( "description".equals(tomMatch575NameNumber_freshVar_88) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_63= tomMatch575NameNumber_freshVar_60.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_65=tomMatch575NameNumber_freshVar_63;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_66=tomMatch575NameNumber_freshVar_63;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_64=tomMatch575NameNumber_end_66;if (!( tomMatch575NameNumber_freshVar_64.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_92= tomMatch575NameNumber_freshVar_64.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_92 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_89= tomMatch575NameNumber_freshVar_92.getName() ;{  String  tomMatch575NameNumber_freshVar_90= tomMatch575NameNumber_freshVar_92.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_91= tomMatch575NameNumber_freshVar_92.getValue() ;{  String  tomMatch575NameNumber_freshVar_93=tomMatch575NameNumber_freshVar_89;if ( "name".equals(tomMatch575NameNumber_freshVar_93) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_67= tomMatch575NameNumber_freshVar_64.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_69=tomMatch575NameNumber_freshVar_67;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_70=tomMatch575NameNumber_freshVar_67;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_68=tomMatch575NameNumber_end_70;if (!( tomMatch575NameNumber_freshVar_68.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_97= tomMatch575NameNumber_freshVar_68.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_97 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_94= tomMatch575NameNumber_freshVar_97.getName() ;{  String  tomMatch575NameNumber_freshVar_95= tomMatch575NameNumber_freshVar_97.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_96= tomMatch575NameNumber_freshVar_97.getValue() ;{  String  tomMatch575NameNumber_freshVar_98=tomMatch575NameNumber_freshVar_94;if ( "value".equals(tomMatch575NameNumber_freshVar_98) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_71= tomMatch575NameNumber_freshVar_68.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_73=tomMatch575NameNumber_freshVar_48;if ( ((tomMatch575NameNumber_freshVar_73 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_73 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch575NameNumber_freshVar_73.isEmptyconcTNode() ) {if ( true ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch575NameNumber_freshVar_91, tomMatch575NameNumber_freshVar_76, tomMatch575NameNumber_freshVar_86,  tom.platform.adt.platformoption.types.platformvalue.IntegerValue.make(Integer.parseInt(tomMatch575NameNumber_freshVar_96)) , tomMatch575NameNumber_freshVar_81) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}}}}}}}}}}if ( tomMatch575NameNumber_end_70.isEmptyconcTNode() ) {tomMatch575NameNumber_end_70=tomMatch575NameNumber_begin_69;} else {tomMatch575NameNumber_end_70= tomMatch575NameNumber_end_70.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_70==tomMatch575NameNumber_begin_69) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_66.isEmptyconcTNode() ) {tomMatch575NameNumber_end_66=tomMatch575NameNumber_begin_65;} else {tomMatch575NameNumber_end_66= tomMatch575NameNumber_end_66.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_66==tomMatch575NameNumber_begin_65) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_62.isEmptyconcTNode() ) {tomMatch575NameNumber_end_62=tomMatch575NameNumber_begin_61;} else {tomMatch575NameNumber_end_62= tomMatch575NameNumber_end_62.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_62==tomMatch575NameNumber_begin_61) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_58.isEmptyconcTNode() ) {tomMatch575NameNumber_end_58=tomMatch575NameNumber_begin_57;} else {tomMatch575NameNumber_end_58= tomMatch575NameNumber_end_58.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_58==tomMatch575NameNumber_begin_57) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_54.isEmptyconcTNode() ) {tomMatch575NameNumber_end_54=tomMatch575NameNumber_begin_53;} else {tomMatch575NameNumber_end_54= tomMatch575NameNumber_end_54.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_54==tomMatch575NameNumber_begin_53) ));}}}}}}}}}}}}}}{ Object tomMatch575NameNumber_freshVar_99=tom_option;if ( (tomMatch575NameNumber_freshVar_99 instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tomMatch575NameNumber_freshVar_99);{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_103=tomMatch575NameNumberfreshSubject_1;if ( (tomMatch575NameNumber_freshVar_103 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch575NameNumber_freshVar_100= tomMatch575NameNumber_freshVar_103.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_101= tomMatch575NameNumber_freshVar_103.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_102= tomMatch575NameNumber_freshVar_103.getChildList() ;{  String  tomMatch575NameNumber_freshVar_104=tomMatch575NameNumber_freshVar_100;if ( "string".equals(tomMatch575NameNumber_freshVar_104) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_105=tomMatch575NameNumber_freshVar_101;if ( ((tomMatch575NameNumber_freshVar_105 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_105 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_107=tomMatch575NameNumber_freshVar_105;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_108=tomMatch575NameNumber_freshVar_105;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_106=tomMatch575NameNumber_end_108;if (!( tomMatch575NameNumber_freshVar_106.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_131= tomMatch575NameNumber_freshVar_106.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_131 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_128= tomMatch575NameNumber_freshVar_131.getName() ;{  String  tomMatch575NameNumber_freshVar_129= tomMatch575NameNumber_freshVar_131.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_130= tomMatch575NameNumber_freshVar_131.getValue() ;{  String  tomMatch575NameNumber_freshVar_132=tomMatch575NameNumber_freshVar_128;if ( "altName".equals(tomMatch575NameNumber_freshVar_132) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_109= tomMatch575NameNumber_freshVar_106.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_111=tomMatch575NameNumber_freshVar_109;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_112=tomMatch575NameNumber_freshVar_109;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_110=tomMatch575NameNumber_end_112;if (!( tomMatch575NameNumber_freshVar_110.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_136= tomMatch575NameNumber_freshVar_110.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_136 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_133= tomMatch575NameNumber_freshVar_136.getName() ;{  String  tomMatch575NameNumber_freshVar_134= tomMatch575NameNumber_freshVar_136.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_135= tomMatch575NameNumber_freshVar_136.getValue() ;{  String  tomMatch575NameNumber_freshVar_137=tomMatch575NameNumber_freshVar_133;if ( "attrName".equals(tomMatch575NameNumber_freshVar_137) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_113= tomMatch575NameNumber_freshVar_110.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_115=tomMatch575NameNumber_freshVar_113;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_116=tomMatch575NameNumber_freshVar_113;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_114=tomMatch575NameNumber_end_116;if (!( tomMatch575NameNumber_freshVar_114.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_141= tomMatch575NameNumber_freshVar_114.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_141 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_138= tomMatch575NameNumber_freshVar_141.getName() ;{  String  tomMatch575NameNumber_freshVar_139= tomMatch575NameNumber_freshVar_141.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_140= tomMatch575NameNumber_freshVar_141.getValue() ;{  String  tomMatch575NameNumber_freshVar_142=tomMatch575NameNumber_freshVar_138;if ( "description".equals(tomMatch575NameNumber_freshVar_142) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_117= tomMatch575NameNumber_freshVar_114.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_119=tomMatch575NameNumber_freshVar_117;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_120=tomMatch575NameNumber_freshVar_117;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_118=tomMatch575NameNumber_end_120;if (!( tomMatch575NameNumber_freshVar_118.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_146= tomMatch575NameNumber_freshVar_118.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_146 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_143= tomMatch575NameNumber_freshVar_146.getName() ;{  String  tomMatch575NameNumber_freshVar_144= tomMatch575NameNumber_freshVar_146.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_145= tomMatch575NameNumber_freshVar_146.getValue() ;{  String  tomMatch575NameNumber_freshVar_147=tomMatch575NameNumber_freshVar_143;if ( "name".equals(tomMatch575NameNumber_freshVar_147) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_121= tomMatch575NameNumber_freshVar_118.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_123=tomMatch575NameNumber_freshVar_121;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_124=tomMatch575NameNumber_freshVar_121;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_122=tomMatch575NameNumber_end_124;if (!( tomMatch575NameNumber_freshVar_122.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumber_freshVar_151= tomMatch575NameNumber_freshVar_122.getHeadconcTNode() ;if ( (tomMatch575NameNumber_freshVar_151 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch575NameNumber_freshVar_148= tomMatch575NameNumber_freshVar_151.getName() ;{  String  tomMatch575NameNumber_freshVar_149= tomMatch575NameNumber_freshVar_151.getSpecified() ;{  String  tomMatch575NameNumber_freshVar_150= tomMatch575NameNumber_freshVar_151.getValue() ;{  String  tomMatch575NameNumber_freshVar_152=tomMatch575NameNumber_freshVar_148;if ( "value".equals(tomMatch575NameNumber_freshVar_152) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_125= tomMatch575NameNumber_freshVar_122.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_127=tomMatch575NameNumber_freshVar_102;if ( ((tomMatch575NameNumber_freshVar_127 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_127 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch575NameNumber_freshVar_127.isEmptyconcTNode() ) {if ( true ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch575NameNumber_freshVar_145, tomMatch575NameNumber_freshVar_130, tomMatch575NameNumber_freshVar_140,  tom.platform.adt.platformoption.types.platformvalue.StringValue.make(tomMatch575NameNumber_freshVar_150) , tomMatch575NameNumber_freshVar_135) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}}}}}}}}}}if ( tomMatch575NameNumber_end_124.isEmptyconcTNode() ) {tomMatch575NameNumber_end_124=tomMatch575NameNumber_begin_123;} else {tomMatch575NameNumber_end_124= tomMatch575NameNumber_end_124.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_124==tomMatch575NameNumber_begin_123) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_120.isEmptyconcTNode() ) {tomMatch575NameNumber_end_120=tomMatch575NameNumber_begin_119;} else {tomMatch575NameNumber_end_120= tomMatch575NameNumber_end_120.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_120==tomMatch575NameNumber_begin_119) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_116.isEmptyconcTNode() ) {tomMatch575NameNumber_end_116=tomMatch575NameNumber_begin_115;} else {tomMatch575NameNumber_end_116= tomMatch575NameNumber_end_116.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_116==tomMatch575NameNumber_begin_115) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_112.isEmptyconcTNode() ) {tomMatch575NameNumber_end_112=tomMatch575NameNumber_begin_111;} else {tomMatch575NameNumber_end_112= tomMatch575NameNumber_end_112.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_112==tomMatch575NameNumber_begin_111) ));}}}}}}}}}}}}if ( tomMatch575NameNumber_end_108.isEmptyconcTNode() ) {tomMatch575NameNumber_end_108=tomMatch575NameNumber_begin_107;} else {tomMatch575NameNumber_end_108= tomMatch575NameNumber_end_108.getTailconcTNode() ;}}} while(!( (tomMatch575NameNumber_end_108==tomMatch575NameNumber_begin_107) ));}}}}}}}}}}}}}}}

      }}}}}if ( tomMatch574NameNumber_end_11.isEmptyconcTNode() ) {tomMatch574NameNumber_end_11=tomMatch574NameNumber_begin_10;} else {tomMatch574NameNumber_end_11= tomMatch574NameNumber_end_11.getTailconcTNode() ;}}} while(!( (tomMatch574NameNumber_end_11==tomMatch574NameNumber_begin_10) ));}}}}}}}}}}}}}}}}}

    return list;
  }

}
