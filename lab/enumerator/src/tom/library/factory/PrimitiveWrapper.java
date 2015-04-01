package tom.library.factory;

import java.lang.annotation.Annotation;

import org.apache.commons.lang.ClassUtils;

/**
 * wraps a primitive parameter
 * @author Ahmad
 *
 */
public class PrimitiveWrapper extends ParamWrapper {

	/**
	 * name of the java primitive wrapper for this type 
	 * e.g. Integer for int
	 */
	private String javaWrapperName;
		
	/**
	 * constructs the wrapper
	 * uses the org.apache.commons.lang.ClassUtils to get java Wrapper for a primitive
	 * @param param primitive to be wrapped
	 * @param paramIndex index of the parameter among the constructor parameters
	 * @param paramAnnotations 
	 */
	public PrimitiveWrapper(Class param, int paramIndex, ConstructorWrapper declaringCons) {
		super(param, paramIndex, declaringCons);
		this.javaWrapperName = ClassUtils.primitiveToWrapper(param).getSimpleName();
	}
	
	@Override
	public String getType() {
		return this.javaWrapperName;
	}

	@Override
	public String enumerate() {
		StringBuilder enumStatement = new StringBuilder();
		enumStatement.append("final Enumeration<");
		enumStatement.append(javaWrapperName);
		enumStatement.append("> ");
		enumStatement.append(paramName+"Enum");
		enumStatement.append(" = new Enumeration<");
		enumStatement.append(javaWrapperName);
		enumStatement.append(">(Combinators.make");
		enumStatement.append(javaWrapperName);
		enumStatement.append("().parts()");
		
		if (enumerateAnnotation != null) {
			enumStatement.append(".take(BigInteger.valueOf(");
			enumStatement.append(enumerateAnnotation.maxSize());
			enumStatement.append("))");
		}
		
		enumStatement.append(")");
		return enumStatement.toString();
	}

}
