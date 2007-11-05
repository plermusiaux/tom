import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TomMappingGenerator {

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println(%[
Usage: java TomMappingGenerator startPoint mappingsFileName [includeInClasspath]

where:
  startPoint            = a folder name (or a file name) to generate the mappings for (can be full paths or relative to TomMappingGenerator). If this is a folder, mappings are generated recursively for all contained classes.

  mappingsFileName      = destination file name for the mappings (can be a full path or relative to TomMappingGenerator).

  includeInClasspath    = a ; separated string that contains full paths to folders, jars or class names to include in CLASSPATH if needed  
                            
]%);
      System.exit(0);
    }
    TomMappingGenerator gen = new TomMappingGenerator();
    if ( args.length > 2 ) {
      gen.generateFromRelativePaths(args[0], args[1], args[2]);
    }else{
      gen.generateFromRelativePaths(args[0], args[1], null);
    }
  }

  /**
  * Launches the generator from relative paths
  *
  * @param startPoint full or relative (to this current class) name of the start point
  * @param mappingsFileName full or relative (to this current class) name of the mapping file to generate
  * @param includeInClasspath the path to include into classpath when searching for classes
  */
  public void generateFromRelativePaths(String startPoint, String mappingsFileName, String includeInClassPath) throws IOException {

    File startPointFile = new File(startPoint);
    if (!startPointFile.exists()){

      if (startPointFile.isAbsolute()){
        throw new FileNotFoundException("Unable to resolve start path '" + startPoint + "'.");
      }

      File currentClassFile = new File(getPath());       
      String parentPath = currentClassFile.getParent();
      String startPointFullPath = null;
      String baseFolder = null;
      if (parentPath != null) { 
        baseFolder = (new File(parentPath)).getCanonicalPath() + File.separator;
        startPointFullPath = baseFolder + startPoint;        
      } else {
        startPointFullPath = startPoint;
      }

      startPointFile = new File(startPointFullPath);
      if (!startPointFile.exists()) {
        throw new FileNotFoundException("Unable to resolve start path '" + startPointFullPath + "').");
      }  

      File mappingsFile = new File(mappingsFileName);
      if (!mappingsFile.isAbsolute()){
        mappingsFileName = baseFolder + mappingsFileName;
      }
    }
    generate(startPointFile, mappingsFileName, includeInClassPath);
  }

  /**
  * Launches the generator from full paths (it assumes that the paths received are correct)
  *
  * @param startPoint the File object for the start point (this method assumes that the file exists)
  * @param mappingsFileName the full name of the mapping file to generate
  * @param includeInClasspath the path to include into classpath when searching for classes
  *     
  */
  public void generate(File startPoint, String mappingsFileName, String includeInClasspath) throws IOException {

    Writer writer = null;    
    StringBuilder strBuilder = new StringBuilder("%include { Collection.tom }\n");
    try {
      // the types used in operator declaration
      HashMap<String, Class<?>> usedTypes = new HashMap<String, Class<?>>();
      // the types declared
      HashMap<String, Class<?>> declaredTypes = new HashMap<String, Class<?>>();      
      generate(startPoint, strBuilder, usedTypes, declaredTypes, getURLPathsFromString(includeInClasspath));
      // generate a mapping for each used type that was not declared
      // make sure it is not declared twice
      ArrayList<String> processed = new ArrayList<String>();
      for(String usedTypeName: usedTypes.keySet()){
        if (processed.contains(usedTypeName)) { continue; }
        processed.add(usedTypeName);
        Class usedTypeClass = usedTypes.get(usedTypeName);
        if (!declaredTypes.containsKey(usedTypeName) && !Collection.class.equals(usedTypeClass)){
          if (usedTypeClass.isPrimitive()){
            strBuilder.insert(0, "%include { " + usedTypeClass.getName() + ".tom }\n");
          } else {
            // generate %typeterm
            generateTypeTerm(usedTypeClass, strBuilder, declaredTypes);
          }
        }
      }  
      // add 'myAdd' method
      strBuilder.append(%[      
private static java.util.List myAdd(Object e,java.util.List l) {
  l.add(e);
  return l;
}
]%);      
      writer = new BufferedWriter(new FileWriter(mappingsFileName));
      writer.append(strBuilder.toString());
    } catch (Exception e) {
      System.out.println("An error occured:"  + e.getMessage());
      e.printStackTrace();
    } finally {
      if (writer != null){
        writer.flush();
        writer.close();
      }
    }
  }

  private void generate(File startPointFile, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes,
      HashMap<String, Class<?>> declaredTypes, URL[] includeInClasspath) throws IOException, ClassNotFoundException {
    if (startPointFile.isDirectory()) {
      File[] files = startPointFile.listFiles();
      for (File file : files) {
        generate(file, strBuilder, usedTypes, declaredTypes, includeInClasspath);
      }
    } else {
      if (!startPointFile.getName().endsWith(".class")) {
        return;
      }
      System.out.print("Extracting mapping for:" + startPointFile.getName() + " ... ");      
      try {
        extractMapping((new MGClassLoader(includeInClasspath)).getClassObj(startPointFile), strBuilder, usedTypes, declaredTypes);
      } catch (NoClassDefFoundError e) {
        System.out.println("\nThe following class couldn't be found: " + e.getMessage());
        if (e.getCause() != null) {System.out.println("Cause: " + e.getCause().getMessage());}      
      } catch (Throwable e) {
        System.out.println("\nAn error occured while extracting the mapping: " + e.getMessage());
        if (e.getCause() != null) {System.out.println("Cause: " + e.getCause().getMessage());}
      }
      System.out.println("Done !");
    }
  }


  private void extractMapping(Class classObj, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes,
      HashMap<String, Class<?>> declaredTypes) throws ClassNotFoundException, IOException {    
    strBuilder.append("\n/*******************************************************************************/\n");    
    // generate %typeterm
    generateTypeTerm(classObj, strBuilder, declaredTypes);
    // generate %op    
    try{ // sometimes getMethods throws a NullPointerException
        generateOperator(classObj, strBuilder, usedTypes);    
    } catch ( NullPointerException e ) {
      System.out.println("\n An %op couldn't be generated");
    } 
    // generate %oparray (only for base classes)
    if (Object.class.equals(classObj.getSuperclass())) {
      generateOpArray(classObj.getName(), strBuilder);
    }
  }

  private void generateTypeTerm(Class classFName, StringBuilder strBuilder, HashMap<String, Class<?>> declaredTypes){
    String className = classFName.getName().substring(classFName.getName().lastIndexOf('.') + 1);
    declaredTypes.put(classFName.getName(),classFName);
    strBuilder.append(%[
%typeterm @className@ {
  implement     { @classFName.getName()@ }
  is_sort(t)    { t instanceof @classFName.getName()@ }
  equals(t1,t2) { t1.equals(t2) }      
}
]%);
  }

  private void generateOperator(Class classFName, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes){
    String fullClassName = classFName.getName();
    String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
    Method[] methods = classFName.getMethods();    
    // find the class that is the highest in the hierarchy
    Class superClass = null;    
    while(classFName.getSuperclass() != null && !Object.class.equals(classFName.getSuperclass()) ) {      
      superClass = classFName.getSuperclass();
      classFName = superClass;
    }
    String codomain = null;
    // if we have some super class
    if(superClass != null) {
      codomain = superClass.getName().substring(superClass.getName().lastIndexOf('.') + 1);
      usedTypes.put(superClass.getName(),superClass);
    }else{
      codomain = className;
      usedTypes.put(classFName.getName(),classFName);
    }
    strBuilder.append(%[
%op @codomain@ @className@(@getFieldsDeclarations(methods,usedTypes)@) {
  is_fsym(t)                { t instanceof @fullClassName@ } @getSlotDeclarations(methods,className)@     
}
]%);
  }

  private String getFieldsDeclarations(Method[] methods, HashMap<String, Class<?>> usedTypes) {
    StringBuilder result = new StringBuilder();
    // avoids declaring twice the same field (in case we have 2 getters for the same field)
    ArrayList<String> declaredFields = new ArrayList<String>(); 
    for (Method m : methods) {
      // not a 'get' or an 'is'
      String methodName = m.getName();
      if (!methodName.startsWith("get") && !methodName.startsWith("is")) {
        continue;
      }
      String fieldName = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);
      fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
      if ("class".equalsIgnoreCase(fieldName) || declaredFields.contains(fieldName)) {
        continue;
      }
      declaredFields.add(fieldName);
      result.append(fieldName + ":");
      if (m.getReturnType().isPrimitive()) {
        result.append(m.getReturnType().getName());
      } else {
        result.append(m.getReturnType().getName().substring(
            m.getReturnType().getName().lastIndexOf('.') + 1));
      }
      usedTypes.put(m.getReturnType().getName(),m.getReturnType());
      result.append(",");
    }
    // remove the ","
    String finalString = result.toString();
    return (finalString == null || "".equals(finalString)) ? "" : finalString.substring(0, finalString.length() - 1);
  }

  private String getSlotDeclarations(Method[] methods, String className){
    StringBuilder result = new StringBuilder();
    // avoids declaring twice the same slot (in case we have 2 getters for the same field)
    ArrayList<String> declaredFields = new ArrayList<String>(); 
    for(Method m: methods){
      // not a 'get' or an 'is'
      String methodName = m.getName();
      if (!methodName.startsWith("get") && !methodName.startsWith("is")) { continue; };
      String fieldName = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);      
      fieldName = Character.toLowerCase(fieldName.charAt(0)) +  fieldName.substring(1);
      if ("class".equalsIgnoreCase(fieldName) || declaredFields.contains(fieldName)) { continue; }
      declaredFields.add(fieldName);
      result.append(%[
  get_slot(@fieldName@, t)  { ((@className@)t).@methodName@() }]%);    
    }
    return result.toString(); 
  }

  private void generateOpArray(String className, StringBuilder strBuilder){
    className = className.substring(className.lastIndexOf('.') + 1);
    String opName = Character.toLowerCase(className.charAt(0)) +  className.substring(1); 
    strBuilder.append(%[ 
%typeterm @className@List {
  implement                 { java.util.List<@className@> }
  is_sort(t)                { t instanceof java.util.List }
  equals(t1,t2)             { t1.equals(t2) }
}

%oparray @className@List @opName@List(@className@*) {
  is_fsym(t)                { t instanceof java.util.List  }
  make_empty(n)             { new java.util.ArrayList<@className@>(n) }
  make_append(e,l)          { (java.util.ArrayList<@className@>)myAdd(e,l)  }
  get_element(l,n)          { l.get(n)        }
  get_size(l)               { l.size()                }
} 
]%); 
  }

  private String getPath() {
    String className = getClass().getName();
    return ClassLoader.getSystemResource(className + ".class").getPath();
  }

  /**
  * 
  * @param a string containing classpath entries separated by ; 
  * @return an array of URLs obtained from the string received 
  */
  private URL[] getURLPathsFromString(String pathString){
    if (pathString == null) { return new URL[0];}
    String[] paths = pathString.split(";");
    URL[] result = new URL[paths.length];
    int j = 0;
    for(String path:paths){
      File file = new File(path);
      if (!file.exists()){
        System.out.println("Couldn't find location: " + path);
      }else{
        try{
          result[j] = file.toURL();
          j++;
        } catch(MalformedURLException e){
          System.out.println("Couldn't transform to url: " + path);
          j--;
        }
      }
    }
    return result;
  }
}



