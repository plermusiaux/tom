// $ANTLR 2.7.7 (20060906): "HostLanguage.g" -> "HostParser.java"$
/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2007, INRIA
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

package tom.engine.parser;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.*;
import java.io.*;

import tom.engine.Tom;
import tom.engine.TomStreamManager;
import tom.engine.TomMessage;
import tom.engine.exception.*;
import tom.engine.tools.SymbolTable;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;

import tom.engine.tools.ASTFactory;
import aterm.*;
import antlr.TokenStreamSelector;
import tom.platform.OptionManager;
import tom.platform.PluginPlatform;
import tom.platform.PlatformLogRecord;

public class HostParser extends antlr.LLkParser       implements HostParserTokenTypes
 {

  //--------------------------
    /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) { return  (t1.equals(t2)) ;}private static boolean tom_is_sort_String(String t) { return  t instanceof String ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_equal_term_int(int t1, int t2) { return  (t1==t2) ;}private static boolean tom_is_sort_int(int t) { return  true ;} private static boolean tom_equal_term_Position(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Position(Object t) { return  t instanceof tom.engine.adt.tomsignature.types.Position ;}private static boolean tom_equal_term_TargetLanguage(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TargetLanguage(Object t) { return  t instanceof tom.engine.adt.tomsignature.types.TargetLanguage ;}private static boolean tom_equal_term_TomName(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomName(Object t) { return  t instanceof tom.engine.adt.tomname.types.TomName ;}private static boolean tom_equal_term_TomList(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomList(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomList ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_TomTerm(Object t) { return  t instanceof tom.engine.adt.tomterm.types.TomTerm ;}private static boolean tom_equal_term_Option(Object t1, Object t2) { return  t1.equals(t2) ;}private static boolean tom_is_sort_Option(Object t) { return  t instanceof tom.engine.adt.tomoption.types.Option ;}private static  tom.engine.adt.tomsignature.types.Position  tom_make_TextPosition( int  t0,  int  t1) { return  tom.engine.adt.tomsignature.types.position.TextPosition.make(t0, t1) ; }private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_TL( String  t0,  tom.engine.adt.tomsignature.types.Position  t1,  tom.engine.adt.tomsignature.types.Position  t2) { return  tom.engine.adt.tomsignature.types.targetlanguage.TL.make(t0, t1, t2) ; }private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_ITL( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.ITL.make(t0) ; }private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_Comment( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.Comment.make(t0) ; }private static  tom.engine.adt.tomname.types.TomName  tom_make_Name( String  t0) { return  tom.engine.adt.tomname.types.tomname.Name.make(t0) ; }private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TargetLanguageToTomTerm( tom.engine.adt.tomsignature.types.TargetLanguage  t0) { return  tom.engine.adt.tomterm.types.tomterm.TargetLanguageToTomTerm.make(t0) ; }private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_Tom( tom.engine.adt.tomterm.types.TomList  t0) { return  tom.engine.adt.tomterm.types.tomterm.Tom.make(t0) ; }private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TomInclude( tom.engine.adt.tomterm.types.TomList  t0) { return  tom.engine.adt.tomterm.types.tomterm.TomInclude.make(t0) ; }private static  tom.engine.adt.tomoption.types.Option  tom_make_OriginTracking( tom.engine.adt.tomname.types.TomName  t0,  int  t1,  String  t2) { return  tom.engine.adt.tomoption.types.option.OriginTracking.make(t0, t1, t2) ; } 
  //--------------------------

  // the lexer selector
  private TokenStreamSelector selector = null;

  // the file to be parsed
  private String currentFile = null;

  private HashSet includedFileSet = null;
  private HashSet alreadyParsedFileSet = null;

  // the parser for tom constructs
  TomParser tomparser;

  // the lexer for target language
  HostLexer targetlexer = null;

  BackQuoteParser bqparser;

  OptionManager optionManager;

  TomStreamManager streamManager;

  // locations of target language blocks
  private int currentLine = 1;
  private int currentColumn = 1;

  private boolean skipComment = false;

  public HostParser(TokenStreamSelector selector, String currentFile,
                    HashSet includedFiles, HashSet alreadyParsedFiles,
                    OptionManager optionManager, TomStreamManager streamManager){
    this(selector);
    this.selector = selector;
    this.currentFile = currentFile;
    this.optionManager = optionManager;
    this.streamManager = streamManager;
    this.targetlexer = (HostLexer) selector.getStream("targetlexer");
    targetlexer.setParser(this);
    this.includedFileSet = new HashSet(includedFiles);
    this.alreadyParsedFileSet = alreadyParsedFiles;

    testIncludedFile(currentFile, includedFileSet);
    // then create the Tom mode parser
    tomparser = new TomParser(getInputState(),this, optionManager);
    bqparser = tomparser.bqparser;
  }

  private void setSkipComment() {
    skipComment = true;
	}
  public boolean isSkipComment() {
    return skipComment;
	}

  private OptionManager getOptionManager() {
    return optionManager;
  }

  private TomStreamManager getStreamManager() {
    return streamManager;
  }

  public TokenStreamSelector getSelector(){
    return selector;
  }

  public String getCurrentFile(){
    return currentFile;
  }

  public SymbolTable getSymbolTable() {
    return getStreamManager().getSymbolTable();
  }

  public void updatePosition(){
    updatePosition(getLine(),getColumn());
  }

  public void updatePosition(int i, int j){
    currentLine = i;
    currentColumn = j;
  }

  public int currentLine(){
    return currentLine;
  }

  public int currentColumn(){
    return currentColumn;
  }

  // remove braces of a code block
  private String cleanCode(String code){
    return code.substring(code.indexOf('{')+1,code.lastIndexOf('}'));
  }

  // remove the last right-brace of a code block
  private String removeLastBrace(String code){
    return code.substring(0,code.lastIndexOf("}"));
  }

  // returns the current goal language code
  private String getCode(){
    String result = targetlexer.target.toString();
    targetlexer.clearTarget();
    return result;
  }

  // add a token in the target code buffer
  public void addTargetCode(Token t){
    targetlexer.target.append(t.getText());
  }

  private String pureCode(String code){
    return code.replace('\t',' ');
  }

  private boolean isCorrect(String code){
    return (! code.equals("") && ! code.matches("\\s*"));
  }

  // returns the current line number
  public int getLine(){
    return targetlexer.getLine();
  }

  // returns the current column number
  public int getColumn(){
    return targetlexer.getColumn();
  }

  private void includeFile(String fileName, LinkedList list)
    throws TomException, TomIncludeException {
    TomTerm astTom;
    InputStream input;
    byte inputBuffer[];
    //  TomParser tomParser;
    HostParser parser = null;
    File file;
    String fileCanonicalName = "";
    fileName = fileName.trim();
    fileName = fileName.replace('/',File.separatorChar);
    fileName = fileName.replace('\\',File.separatorChar);
    if(fileName.equals("")) {
      throw new TomIncludeException(TomMessage.missingIncludedFile,new Object[]{currentFile, new Integer(getLine())});
    }

    file = new File(fileName);
    if(file.isAbsolute()) {
      try {
        file = file.getCanonicalFile();
      } catch (IOException e) {
        System.out.println("IO Exception when computing included file");
        e.printStackTrace();
      }

      if(!file.exists()) {
        file = null;
      }
    } else {
      // StreamManager shall find it
      file = getStreamManager().findFile(new File(currentFile).getParentFile(),fileName);
    }

    if(file == null) {
      throw new TomIncludeException(TomMessage.includedFileNotFound,new Object[]{fileName, currentFile, new Integer(getLine()), currentFile});
    }
    try {
      fileCanonicalName = file.getCanonicalPath();
      //if(testIncludedFile(fileCanonicalName, includedFileSet)) {
        //throw new TomIncludeException(TomMessage.includedFileCycle,new Object[]{fileName, new Integer(getLine()), currentFile});
      //}

      // if trying to include a file twice, or if in a cycle: discard
      if(testIncludedFile(fileCanonicalName, alreadyParsedFileSet) ||
	  testIncludedFile(fileCanonicalName, includedFileSet)) {
        if(!getStreamManager().isSilentDiscardImport(fileName)) {
          getLogger().log(new PlatformLogRecord(Level.INFO,
                TomMessage.includedFileAlreadyParsed,
                currentFile, fileName, getLine()));
        }
        return;
      }
      Reader fileReader = new BufferedReader(new FileReader(fileCanonicalName));
      parser = TomParserPlugin.newParser(fileReader,fileCanonicalName,
                                         includedFileSet,alreadyParsedFileSet,
                                         getOptionManager(), getStreamManager());
      parser.setSkipComment();
      astTom = parser.input();
      astTom = tom_make_TomInclude(astTom.getTomList());
      list.add(astTom);
    } catch (Exception e) {
      if(e instanceof TomIncludeException) {
        throw (TomIncludeException)e;
      }
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      throw new TomException(TomMessage.errorWhileIncludingFile,
          new Object[]{e.getClass(),
            fileName,
            currentFile,
            new Integer(getLine()),
            sw.toString()
          });
    }
  }

  private boolean testIncludedFile(String fileName, HashSet fileSet) {
    // !(true) if the set did not already contain the specified element.
    return !fileSet.add(fileName);
  }

  /*
   * this function receives a string that comes from %[ ... ]%
   * @@ corresponds to the char '@', so they a encoded into ]% (which cannot
   * appear in the string)
   * then, the string is split around the delimiter @
   * alternatively, each string correspond either to a metaString, or a string
   * to parse the @@ encoded by ]% is put back as a single '@' in the metaString
   */
  public String tomSplitter(String subject, LinkedList list) {

    String metaChar = "]%";
    String escapeChar = "@";

    //System.out.println("initial subject: '" + subject + "'");
    subject = subject.replaceAll(escapeChar+escapeChar,metaChar);
    //System.out.println("subject: '" + subject + "'");

    String split[] = subject.split(escapeChar);
    boolean metaMode = true;
    String res = "";
    for(int i=0 ; i<split.length ; i++) {
      if(metaMode) {
        // put back escapeChar instead of metaChar
        String code = metaEncodeCode(split[i].replaceAll(metaChar,escapeChar));
        metaMode = false;
        //System.out.println("metaString: '" + code + "'");
        list.add(tom_make_ITL(code));
      } else {
        String code = "+"+split[i]+"+";
        metaMode = true;
        //System.out.println("prg to parse: '" + code + "'");
        try {
          Reader codeReader = new BufferedReader(new StringReader(code));
          HostParser parser = TomParserPlugin.newParser(codeReader,getCurrentFile(),
              getOptionManager(), getStreamManager());
          TomTerm astTom = parser.input();
          list.add(astTom); 
        } catch (IOException e) {
          throw new TomRuntimeException("IOException catched in tomSplitter");
        } catch (Exception e) {
          throw new TomRuntimeException("Exception catched in tomSplitter");
        }
      }
    }
    return res;
  }

  private static String metaEncodeCode(String code) {
		/*
			 System.out.println("before: '" + code + "'");
			 for(int i=0 ; i<code.length() ; i++) {
			 System.out.print((int)code.charAt(i));
			 System.out.print(" ");
			 }
			 System.out.println();
		 */
		char bs = '\\';
		StringBuffer sb = new StringBuffer((int)1.5*code.length());
		for(int i=0 ; i<code.length() ; i++) {
			char c = code.charAt(i);
			switch(c) {
				case '\n': 
					sb.append(bs);
					sb.append('n');
					break;
				case '\r': 
					sb.append(bs);
					sb.append('r');
					break;
				case '\t': 
					sb.append(bs);
					sb.append('t');
					break;
				case '\"': 
				case '\\': 
					sb.append(bs);
					sb.append(c);
					break;
				default:
					sb.append(c);
			}
		}
    //System.out.println("sb = '" + sb + "'");
		sb.insert(0,'\"');
		sb.append('\"');
		return sb.toString();

/*
    code = code.replaceAll("\\\"","\\\\\"");
    code = code.replaceAll("\\\\n","\\\\\\\\n");
    code = code.replaceAll("\\\\t","\\\\\\\\t");
    code = code.replaceAll("\\\\r","\\\\\\\\r");

    code = code.replaceAll("\n","\\\\n");
    code = code.replaceAll("\r","\\\\r");
    code = code.replaceAll("\t","\\\\t");
    code = code.replaceAll("\"","\\\"");
    
    System.out.println("after: '" + code + "'");

    return "\"" + code + "\"";
*/


  }

  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

protected HostParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public HostParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected HostParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public HostParser(TokenStream lexer) {
  this(lexer,1);
}

public HostParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final TomTerm  input() throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  t = null;
		
		result = null;
		LinkedList list = new LinkedList();
		
		
		blockList(list);
		t = LT(1);
		match(Token.EOF_TYPE);
		
		// This TL is last block: do no need to specify line and column
		list.add(tom_make_TargetLanguageToTomTerm(tom_make_TL(getCode(),tom_make_TextPosition(currentLine(),currentColumn()),tom_make_TextPosition(t.getLine(),t.getColumn())))
		
		
		
		);
		String comment =
		"Generated by TOM (version " + Tom.VERSION + "): Do not edit this file";
		list.addFirst(tom_make_TargetLanguageToTomTerm(tom_make_Comment(comment)));
		result = tom_make_Tom(ASTFactory.makeList(list));
		
		return result;
	}
	
	public final void blockList(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		{
		_loop4:
		do {
			switch ( LA(1)) {
			case MATCH:
			{
				matchConstruct(list);
				break;
			}
			case STRATEGY:
			{
				strategyConstruct(list);
				break;
			}
			case GOM:
			{
				gomsignature(list);
				break;
			}
			case BACKQUOTE:
			{
				backquoteTerm(list);
				break;
			}
			case OPERATOR:
			{
				operator(list);
				break;
			}
			case OPERATORLIST:
			{
				operatorList(list);
				break;
			}
			case OPERATORARRAY:
			{
				operatorArray(list);
				break;
			}
			case INCLUDE:
			{
				includeConstruct(list);
				break;
			}
			case TYPETERM:
			{
				typeTerm(list);
				break;
			}
			case CODE:
			{
				code(list);
				break;
			}
			case STRING:
			{
				match(STRING);
				break;
			}
			case LBRACE:
			{
				match(LBRACE);
				blockList(list);
				match(RBRACE);
				break;
			}
			default:
			{
				break _loop4;
			}
			}
		} while (true);
		}
	}
	
	public final void matchConstruct(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(MATCH);
		
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn())
		)
		
		
		
		;
		list.add(code);
		}
		
		Option ot = tom_make_OriginTracking(tom_make_Name("Match"),t.getLine(),currentFile);
		
		Instruction match = tomparser.matchConstruct(ot);
		list.add(match);
		
	}
	
	public final void strategyConstruct(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(STRATEGY);
		
		// add the target code preceeding the construct
		String textCode = getCode();
		
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn())
		)
		
		
		
		;
		list.add(code);
		}
		
		Option ot = tom_make_OriginTracking(tom_make_Name("Strategy"),t.getLine(),currentFile);
		
		// call the tomparser for the construct
		Declaration strategy = tomparser.strategyConstruct(ot);
		list.add(strategy);
		
	}
	
	public final void gomsignature(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		int initialGomLine;
		TargetLanguage gomTL = null, code = null;
		LinkedList blockList = new LinkedList();
		String gomCode = null;
		
		
		t = LT(1);
		match(GOM);
		
		initialGomLine = t.getLine();
		
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn()))
		
		;
		list.add(code);
		}
		
		
		tom.gom.parser.BlockParser blockparser = 
		tom.gom.parser.BlockParser.makeBlockParser(targetlexer.getInputState());
		gomCode = cleanCode(blockparser.block().trim());
		String destDir = getStreamManager().getDestDir().getPath();
		
		File config_xml = null;
		try {
		String tom_home = System.getProperty("tom.home");
		if(tom_home != null) {
		config_xml = new File(tom_home,"Gom.xml");
		} else {
		// for the eclipse plugin for example
		String tom_xml_filename =
		((String)getOptionManager().getOptionValue("X"));
		config_xml =
		new File(new File(tom_xml_filename).getParentFile(),"Gom.xml");
		}
		config_xml = config_xml.getCanonicalFile();
		} catch (IOException e) {
		getLogger().log(
		Level.FINER,
		"Failed to get canonical path for "+config_xml.getPath());
		}
		
		String packageName =
		getStreamManager().getPackagePath().replace(File.separatorChar, '.');
		String inputFileNameWithoutExtension =
		getStreamManager().getRawFileName().toLowerCase();
		String subPackageName = "";
		if(packageName.equals("")) {
		subPackageName = inputFileNameWithoutExtension;
		} else {
		subPackageName = packageName + "." + inputFileNameWithoutExtension;
		}
		ArrayList parameters = new ArrayList();
		parameters.add("-X");
		parameters.add(config_xml.getPath());
		parameters.add("--destdir");
		parameters.add(destDir);
		parameters.add("--package");
		parameters.add(subPackageName);
		if(getOptionManager().getOptionValue("wall")==Boolean.TRUE) {
		parameters.add("--wall");
		}
		if(getOptionManager().getOptionValue("intermediate")==Boolean.TRUE) {
		parameters.add("--intermediate");
		}
		parameters.add("--intermediateName");
		parameters.add(getStreamManager().getRawFileName());
		if(getOptionManager().getOptionValue("verbose")==Boolean.TRUE) {
		parameters.add("--verbose");
		}
		/* treat user supplied options */
		textCode = t.getText();
		if (textCode.length() > 6) {
		String[] userOpts =
		textCode.substring(5,textCode.length()-1).split("\\s+");
		for (int i=0; i < userOpts.length; i++) {
		parameters.add(userOpts[i]);
		}
		}
		parameters.add("-");
		getLogger().log(Level.FINE,"Calling gom with: "+parameters);
		InputStream backupIn = System.in;
		System.setIn(new DataInputStream(new StringBufferInputStream(gomCode)));
		
		/* Prepare arguments */
		Object[] preparams = parameters.toArray();
		String[] params = new String[preparams.length];
		for (int i = 0; i < preparams.length; i++) {
		params[i] = (String)preparams[i];
		}
		int res = 1;
		res = tom.gom.Gom.exec(params);
		System.setIn(backupIn);
		if (res != 0 ) {
		throw new TomException(TomMessage.gomFailure,new Object[]{currentFile,new Integer(initialGomLine)});
		}
		String generatedMapping = tom.gom.tools.GomEnvironment.getInstance().getLastGeneratedMapping();
		
		// Simulate the inclusion of generated Tom file
		/*
		* We shall not need to test the validity of the generatedMapping file name
		* as gom returned <> 0 if it is not valid
		* 
		* Anyway, for an empty gom signature, no files are generated 
		*/
		if (generatedMapping != null){
			includeFile(generatedMapping, list);
		}
		
		updatePosition();
		
	}
	
	public final void backquoteTerm(
		LinkedList list
	) throws RecognitionException, TokenStreamException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(BACKQUOTE);
		
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn())
		)
		
		
		
		;
		list.add(code);
		}
		
		Option ot = tom_make_OriginTracking(tom_make_Name("Backquote"),t.getLine(),currentFile);
		TomTerm bqTerm = bqparser.beginBackquote();
		
		// update position for new target block
		updatePosition();
		list.add(bqTerm);
		
	}
	
	public final void operator(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(OPERATOR);
		
		String textCode = pureCode(getCode());
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn()))
		
		
		;
		list.add(tom_make_TargetLanguageToTomTerm(code));
		}
		
		Declaration operatorDecl = tomparser.operator();
		list.add(operatorDecl);
		
	}
	
	public final void operatorList(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(OPERATORLIST);
		
		String textCode = pureCode(getCode());
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn()))
		
		
		;
		list.add(tom_make_TargetLanguageToTomTerm(code));
		}
		
		Declaration operatorListDecl = tomparser.operatorList();
		list.add(operatorListDecl);
		
	}
	
	public final void operatorArray(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(OPERATORARRAY);
		
		String textCode = pureCode(getCode());
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn()))
		
		
		;
		list.add(tom_make_TargetLanguageToTomTerm(code));
		}
		
		Declaration operatorArrayDecl = tomparser.operatorArray();
		list.add(operatorArrayDecl);
		
	}
	
	public final void includeConstruct(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage tlCode = null;
		LinkedList blockList = new LinkedList();
		
		
		t = LT(1);
		match(INCLUDE);
		
		TargetLanguage code = null;
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn()))
		
		
		;
		list.add(tom_make_TargetLanguageToTomTerm(code));
		}
		
		tlCode=goalLanguage(blockList);
		
		includeFile(tlCode.getCode(),list);
		updatePosition();
		
	}
	
	public final void typeTerm(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  tt = null;
		
		TargetLanguage code = null;
		int line, column;
		
		
		{
		tt = LT(1);
		match(TYPETERM);
		
		line = tt.getLine();
		column = tt.getColumn();
		
		}
		
		// addPreviousCode...
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(line,column))
		
		
		;
		list.add(code);
		}
		Declaration termdecl = tomparser.typeTerm();
		
		list.add(termdecl);
		
	}
	
	public final void code(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		TargetLanguage code = null;
		
		
		t = LT(1);
		match(CODE);
		
		String textCode = getCode();
		if(isCorrect(textCode)) {
		code = tom_make_TL(textCode,tom_make_TextPosition(currentLine,currentColumn),tom_make_TextPosition(t.getLine(),t.getColumn())
		)
		
		
		
		;
		list.add(code);
		}
		textCode = t.getText();
		String metacode = textCode.substring(2,textCode.length()-2);
		tomSplitter(metacode, list);
		updatePosition(targetlexer.getInputState().getLine(),targetlexer.getInputState().getColumn());
		
	}
	
	public final TargetLanguage  goalLanguage(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		TargetLanguage result;
		
		Token  t1 = null;
		Token  t2 = null;
		
		result =  null;
		
		
		t1 = LT(1);
		match(LBRACE);
		
		updatePosition(t1.getLine(),t1.getColumn());
		
		blockList(list);
		t2 = LT(1);
		match(RBRACE);
		
		result = tom_make_TL(cleanCode(getCode()),tom_make_TextPosition(currentLine(),currentColumn()),tom_make_TextPosition(t2.getLine(),t2.getColumn())
		)
		
		
		;
		targetlexer.clearTarget();
		
		return result;
	}
	
	public final TargetLanguage  targetLanguage(
		LinkedList list
	) throws RecognitionException, TokenStreamException, TomException {
		TargetLanguage result;
		
		Token  t = null;
		
		result = null;
		
		
		blockList(list);
		t = LT(1);
		match(RBRACE);
		
		String code = removeLastBrace(getCode());
		
		//System.out.println("code = " + code);
		//System.out.println("list = " + list);
		
		result = tom_make_TL(code,tom_make_TextPosition(currentLine(),currentColumn()),tom_make_TextPosition(t.getLine(),t.getColumn())
		)
		
		
		;
		targetlexer.clearTarget();
		
		return result;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"STRING",
		"LBRACE",
		"RBRACE",
		"STRATEGY",
		"MATCH",
		"GOM",
		"BACKQUOTE",
		"OPERATOR",
		"OPERATORLIST",
		"OPERATORARRAY",
		"INCLUDE",
		"CODE",
		"TYPETERM",
		"ESC",
		"HEX_DIGIT",
		"WS",
		"COMMENT",
		"SL_COMMENT",
		"ML_COMMENT",
		"TARGET"
	};
	
	
	}
