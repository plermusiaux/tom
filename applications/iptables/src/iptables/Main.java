package iptables;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;
import iptables.analyser.types.*;
import iptables.analyser.AnalyserAdaptor;
import iptables.iptables.IptablesAdaptor;
import iptables.iptables.types.*;
import java.util.*;
import java.io.*;
import org.kohsuke.args4j.*;


public class Main {
	protected static MainOptions options = new MainOptions();

	public static void main(String[] args) {
		CmdLineParser optionParser = new CmdLineParser(options);
		optionParser.setUsageWidth(80);
		try {
			// parse the arguments.
			optionParser.parseArgument(args);
			//if( options.arguments.isEmpty() ) {
			if( options.help || options.h ) {
				throw new CmdLineException("Help");
			}
		} catch( CmdLineException e ) {
			// if there's a problem in the command line,
			// you'll get this exception. this will report
			// an error message.
			System.err.println(e.getMessage());
			System.err.println("java Main [options...] arguments ...");
			// print the list of available options
			optionParser.printUsage(System.err);
			System.err.println();
			return;
		}

		try {
			InputStream fileinput = System.in;
			if(options.in != null) {
				fileinput = new FileInputStream(options.in);
			}
			// Parse the input expression and build an AST
			LangageLexer lexer = new LangageLexer(new ANTLRInputStream(fileinput));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LangageParser ruleParser = new LangageParser(tokens);
			/*
			 * MODIFY HERE
			 * file
			 * Action
			 */
			Tree b1 = (Tree) ruleParser.file().getTree();
			IptablesBlocks inst = (IptablesBlocks) IptablesAdaptor.getTerm(b1);
			System.out.println("inst = " + inst);

			Rules rs = Iptables.wrapBlocks(inst);
			System.out.println("rules = " + rs);

			rs = Analyser.checkIntegrity(rs);
			System.out.println("new rules = " + rs);

			System.out.println("### Iptables ###");
			IptablesOutput.printTranslation(rs);
			System.out.println("### Packet Filter ###");
			PacketFilterOutput.printTranslation(rs);

			PrintStream outputfile = System.out;
			if(options.out != null) {
				outputfile = new PrintStream(options.out);
			}
			PrintStream tomoutputfile = System.out;
		} catch (Exception e) {
			System.err.println("exception: " + e);
			e.printStackTrace();
			return;
		}
	}
}
