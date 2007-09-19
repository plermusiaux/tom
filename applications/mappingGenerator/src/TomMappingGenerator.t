import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class TomMappingGenerator {

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: java TomMappingGenerator start_point_package_name mapping_file_name");
      System.exit(0);
    }
    TomMappingGenerator gen = new TomMappingGenerator();
    gen.generateFromRelativePaths(args[0], args[1]);
  }

  /**
  * Launches the generator from relative paths
  *
  * @param startPoint the relative (to this current class) name of the start point
  * @param mappingsFileName the relative (to this current class) name of the mapping file to generate
  */
  public void generateFromRelativePaths(String startPoint, String mappingsFileName) throws IOException {
    File currentClassFile = new File(getPath()); 
    String parentPath = currentClassFile.getParent();
    String startPointFullPath = null;
    if (parentPath != null) { 
      String baseFolder = (new File(parentPath)).getCanonicalPath() + File.separator;
      startPointFullPath = baseFolder + startPoint;
      mappingsFileName = baseFolder + mappingsFileName;
    } else {
      startPointFullPath = startPoint;
    }

    File startPointFile = new File(startPointFullPath);
    if (!startPointFile.exists()) {
      throw new FileNotFoundException("Unable to find start path '" + startPointFullPath + "'.");
    }

    generate(startPointFile, mappingsFileName, null);
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
      generate(startPoint, strBuilder, usedTypes, declaredTypes, includeInClasspath);
      // generate a mapping for each used type that was not declared
      for(String usedTypeName: usedTypes.keySet()){
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
private static ArrayList myAdd(Object e,ArrayList l) {
  l.add(e);
  return l;
}
]%);      
      writer = new BufferedWriter(new FileWriter(mappingsFileName));
      writer.append(strBuilder.toString());
    } catch (Exception e) {
      System.out.println("An error occured. See the stack trace for more information.\n");
      e.printStackTrace();
    } finally {
      if (writer != null){
        writer.flush();
        writer.close();
      }
    }
  }

  private void generate(File startPointFile, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes,
      HashMap<String, Class<?>> declaredTypes, String includeInClasspath) throws IOException, ClassNotFoundException {
    if (startPointFile.isDirectory()) {
      File[] files = startPointFile.listFiles();
      for (File file : files) {
        generate(file, strBuilder, usedTypes, declaredTypes, includeInClasspath);
      }
    } else {
      if (!startPointFile.getName().endsWith(".class")) {
        return;
      }
      System.out.println("Extracting mapping for:" + startPointFile.getName());      
      extractMapping((new MGClassLoader(getURLPathsFromString(includeInClasspath))).getClassObj(startPointFile), strBuilder, usedTypes, declaredTypes);
    }
  }


  private void extractMapping(Class classObj, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes,
      HashMap<String, Class<?>> declaredTypes) throws ClassNotFoundException, IOException {    
    strBuilder.append("\n/*******************************************************************************/\n");
    // generate %typeterm
    generateTypeTerm(classObj, strBuilder, declaredTypes);
    // generate %op
    generateOperator(classObj, strBuilder, usedTypes);
    // generate %oparray (only for base classes)
    if (Object.class.equals(classObj.getSuperclass())) {
      generateOpArray(classObj.getName(), strBuilder);
    }
  }

  private void generateTypeTerm(Class classFName, StringBuilder strBuilder, HashMap<String, Class<?>> declaredTypes){
    String className = classFName.getCanonicalName().substring(classFName.getCanonicalName().lastIndexOf('.') + 1);
    declaredTypes.put(classFName.getCanonicalName(),classFName);
System.out.println("declared:" + classFName);
    strBuilder.append(%[
%typeterm @className@ {
  implement     { @classFName.getCanonicalName()@ }
  is_sort(t)    { t instanceof @classFName.getCanonicalName()@ }
  equals(t1,t2) { t1.equals(t2) }      
}
]%);
  }

  private void generateOperator(Class classFName, StringBuilder strBuilder, HashMap<String, Class<?>> usedTypes){
    String fullClassName = classFName.getCanonicalName();
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
      codomain = superClass.getCanonicalName().substring(superClass.getCanonicalName().lastIndexOf('.') + 1);
      usedTypes.put(superClass.getCanonicalName(),superClass);
System.out.println("used:" + superClass);
    }else{
      codomain = className;
      usedTypes.put(classFName.getCanonicalName(),classFName);
System.out.println("used:" + classFName);
    }
    strBuilder.append(%[
%op @codomain@ @className@(@getFieldsDeclarations(methods,usedTypes)@) {
  is_fsym(t)                { t instanceof @fullClassName@ } @getSlotDeclarations(methods,className)@     
}
]%);
  }

  private String getFieldsDeclarations(Method[] methods, HashMap<String, Class<?>> usedTypes) {
    StringBuilder result = new StringBuilder();
    for (Method m : methods) {
      // not a 'get' or an 'is'
      String methodName = m.getName();
      if (!methodName.startsWith("get") && !methodName.startsWith("is")) {
        continue;
      }
      String fieldName = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);
      fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
      if ("class".equalsIgnoreCase(fieldName)) {
        continue;
      }
      result.append(fieldName + ":");
      if (m.getReturnType().isPrimitive()) {
        result.append(m.getReturnType().getName());
      } else {
        result.append(m.getReturnType().getCanonicalName().substring(
            m.getReturnType().getCanonicalName().lastIndexOf('.') + 1));
      }
      usedTypes.put(m.getReturnType().getCanonicalName(),m.getReturnType());
      result.append(",");
    }
    // remove the ","
    String finalString = result.toString();
    return (finalString == null || "".equals(finalString)) ? "" : finalString.substring(0, finalString.length() - 1);
  }

  private String getSlotDeclarations(Method[] methods, String className){
    StringBuilder result = new StringBuilder();
    for(Method m: methods){
      // not a 'get' or an 'is'
      String methodName = m.getName();
      if (!methodName.startsWith("get") && !methodName.startsWith("is")) {continue;};
      String fieldName = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);
      fieldName = Character.toLowerCase(fieldName.charAt(0)) +  fieldName.substring(1);
      if ("class".equalsIgnoreCase(fieldName)) { continue; }
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
  implement                 { java.util.List }
  is_sort(t)                { t instanceof java.util.List }
  equals(t1,t2)             { t1.equals(t2) }
}

%oparray @className@List @opName@List(@className@*) {
  is_fsym(t)                { t instanceof java.util.List  }
  make_empty(n)             { new java.util.ArrayList(n) }
  make_append(e,l)          { myAdd(e,(ArrayList)l)  }
  get_element(l,n)          { (@className@)l.get(n)        }
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



