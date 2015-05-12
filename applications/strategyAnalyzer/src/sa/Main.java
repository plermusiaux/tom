package sa;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.tree.Tree;
import sa.rule.RuleAdaptor;
import sa.rule.types.*;
import java.util.*;
import java.io.*;
import org.kohsuke.args4j.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Main {
  protected static Options options = new Options();

  public static void main(String[] args) {
    Pretty pretty = new Pretty();

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

      if(options.newparser) {
        // Parse the input expression and build an AST
        RuleLexer lexer = new RuleLexer(new ANTLRInputStream(fileinput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RuleParser ruleParser = new RuleParser(tokens);
        Tree b = (Tree) ruleParser.program().getTree();
        Program t = (Program) RuleAdaptor.getTerm(b);


        System.out.println(t);
        //System.out.println(pretty.toString(expl));
        System.out.println("------------------------------------------   ");

      } else {
        // Parse the input expression and build an AST
        RuleLexer lexer = new RuleLexer(new ANTLRInputStream(fileinput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RuleParser ruleParser = new RuleParser(tokens);
        Tree b = (Tree) ruleParser.expressionlist().getTree();
        ExpressionList expl = (ExpressionList) RuleAdaptor.getTerm(b);
        //       System.out.println(pretty.toString(expl));
        //       System.out.println("------------------------------------------   ");

        Compiler compiler = Compiler.getInstance();
        compiler.setSignature(expl);

        // Transforms the strategy into a rewrite system
        List<Rule> generatedRules = compiler.compile();
        Map<String,Integer> extractedSignature = compiler.getExtractedSignature();
        Map<String,Integer> generatedSignature = compiler.getGeneratedSignature();

        // transform the LINEAR TRS: compile Aps and remove ATs
        RuleCompiler ruleCompiler = new RuleCompiler(extractedSignature,generatedSignature);
        if(options.withAP == false) {
          generatedRules = ruleCompiler.expandAntiPatterns(generatedRules);
        }      
        // if we don't expand the anti-patterns then we should keep the at-annotations as well
        // otherwise output is strange
        if(options.withAT == false && options.withAP == false) {
          generatedRules = ruleCompiler.expandAt(generatedRules);
        }
        // refresh the signatures (presently no modifications)
        extractedSignature = ruleCompiler.getExtractedSignature();
        generatedSignature = ruleCompiler.getGeneratedSignature();


        PrintStream outputfile = System.out;
        if(options.out != null) {
          outputfile = new PrintStream(options.out);
        }
        PrintStream tomoutputfile = System.out;
        if(options.classname != null) {
          tomoutputfile = new PrintStream(options.classname+".t");
        }

        if(options.classname != null) {
          tomoutputfile.println( Pretty.generateTom(generatedRules,generatedSignature,options.classname) );
        } 
        if(options.aprove) {
          boolean innermost = false;
          outputfile.println( Pretty.generateAprove(generatedRules,extractedSignature,innermost) );
        }
      }
    } catch (Exception e) {
      System.err.println("exception: " + e);
      e.printStackTrace();
      return;
    }
  }

}
