package jtom.backend;

import java.io.*;
import java.util.logging.*;

import aterm.*;
import jtom.*;
import jtom.adt.tomsignature.types.*;
import tom.platform.adt.platformoption.types.*;
import tom.platform.OptionParser;
import jtom.tools.*;


/**
 * The TomBackend plugin.
 */
public class TomBackend extends TomGenericPlugin {

  %include { adt/TomSignature.tom }
  %include { adt/PlatformOption.tom }

  private final static int defaultDeep = 2;
  public static final String DECLARED_OPTIONS = "<options><boolean name='noOutput' altName='' description='Do not generate code' value='false'/><boolean name='jCode' altName='j' description='Generate Java code' value='true'/><boolean name='cCode' altName='c' description='Generate C code' value='false'/><boolean name='eCode' altName='e' description='Generate Eiffel code' value='false'/><boolean name='camlCode' altName='' description='Generate Caml code' value='false'/></options>";

  private TomAbstractGenerator generator;
  private Writer writer;
  
  private String generatedFileName;

  public TomBackend() {
    super("TomBackend");
  }

  public void run() {
    if(isActivated() == true) {
      try {
        int errorsAtStart = getStatusHandler().nbOfErrors();
        int warningsAtStart = getStatusHandler().nbOfWarnings();
        
        long startChrono = System.currentTimeMillis();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(environment().getOutputFile())));
        
        OutputCode output = new OutputCode(writer, defaultDeep, getOptionManager());
        
        if(getOptionBooleanValue("jCode")) {
          generator = new TomJavaGenerator(output, getOptionManager());
        } else if(getOptionBooleanValue("cCode")) {
          generator = new TomCGenerator(output, getOptionManager());
        } else if(getOptionBooleanValue("eCode")) {
          generator = new TomEiffelGenerator(output, getOptionManager());
        } else if(getOptionBooleanValue("camlCode")) {
          generator = new TomCamlGenerator(output, getOptionManager());
        }
        
        generator.generate(defaultDeep, (TomTerm)getWorkingTerm());
        
        getLogger().log( Level.INFO, "TomGenerationPhase",
                         new Integer((int)(System.currentTimeMillis()-startChrono)) );
        
        writer.close();
        generatedFileName = environment().getOutputFile().getAbsolutePath();
        printAlertMessage(errorsAtStart, warningsAtStart);
      }
      catch (Exception e) {
        getLogger().log( Level.SEVERE, "ExceptionMessage",
                         new Object[]{environment().getInputFile().getName(), "TomBackend", e.getMessage()} );
        
        e.printStackTrace();
      }
    } else { // backend desactivated
      getLogger().log(Level.INFO, "The backend is not activated and thus WILL NOT RUN.\nNo output !");
    }
  }
  
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomBackend.DECLARED_OPTIONS);
  }

  public void setOption(String optionName, Object optionValue) {
    setOptionValue(optionName, optionValue);
    
    if(optionValue.equals(Boolean.TRUE)) {// no more than 1 type of code can be activated at a time
	    if(optionName.equals("jCode") || optionName.equals("j")) { 
		    //System.out.println("Java code activated, other codes desactivated");
		    setOptionValue("cCode", Boolean.FALSE);
		    setOptionValue("eCode", Boolean.FALSE);
		    setOptionValue("camlCode", Boolean.FALSE); 
      } else if(optionName.equals("cCode") || optionName.equals("c")) { 
		    //System.out.println("C code activated, other codes desactivated");
		    setOptionValue("jCode", Boolean.FALSE);
		    setOptionValue("eCode", Boolean.FALSE);
		    setOptionValue("camlCode", Boolean.FALSE); 
      } else if(optionName.equals("eCode") || optionName.equals("e")) { 
		    //System.out.println("Eiffel code activated, other codes desactivated");
		    setOptionValue("jCode", Boolean.FALSE);
		    setOptionValue("cCode", Boolean.FALSE);
		    setOptionValue("camlCode", Boolean.FALSE); 
      } else if(optionName.equals("camlCode")) { 
		    //System.out.println("Caml code activated, other codes desactivated");
		    setOptionValue("jCode", Boolean.FALSE);
		    setOptionValue("cCode", Boolean.FALSE);
		    setOptionValue("eCode", Boolean.FALSE); 
      }
    }
  }
  
  private boolean isActivated() {
    return !getOptionBooleanValue("noOutput");
  }
  
  public Object[] getArgs() {
    return new Object[]{generatedFileName};
  }

} // class TomBackend
