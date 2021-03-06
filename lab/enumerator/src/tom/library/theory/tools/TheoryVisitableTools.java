package tom.library.theory.tools;

import tom.library.sl.Visitable;
import tom.library.sl.VisitableBuiltin;

/**
 * Tools to deal with {@code Visitable}.
 * 
 * @author nauval
 *
 */
public class TheoryVisitableTools {
	private static int size = 0;
	private static int val = 0;
	
	/**
	 * Returns size + total value
	 * @param term
	 * @return
	 */
	public static int size(Visitable term) {
		size = 0;
		if (term == null) {
			return Integer.MAX_VALUE;
		}
		if (term instanceof Visitable) {
			Visitable t = (Visitable) term;
			calculateSizeWithValue(t);
		}
		return size;
	}
	
	/**
	 * Returns a pair of constructor size and total of term's value.
	 * @param term
	 * @return 
	 */
	public static int[] pairSize(Visitable term) {
		size = 0;
		val = 0;
		if (term == null) {
			return new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
		}
		if (term instanceof Visitable) {
			Visitable t = (Visitable) term;
			calculateSizeValuePair(t);
		}
		
		return new int[] {size, val};
	}
	
	private static void calculateSizeWithValue(Visitable term) {
		if (term.getChildCount() == 0) {
			// calculate value
			size += calculateValue(term);
		} else {
			size ++;
		}
		for (Visitable v : term.getChildren()) {
			calculateSizeWithValue(v);
		}
	}
	
	private static void calculateSizeValuePair(Visitable term) {
		if (term.getChildCount() == 0) {
			// calculate value
			size ++;
			val += calculateValue(term);
		} else {
			size ++;
		}
		for (Visitable v : term.getChildren()) {
			calculateSizeValuePair(v);
		}
	}
	
	private static int calculateValue(Visitable term) {
		if (isValueInstanceOfInteger(term)) {
			return Math.abs(getValueFromTermInteger(term));
		} else if (isValueInstanceOfString(term)) {
			return getValueFromTermString(term).length();
		} else {
			return 1;
		}
	}
	
	public static boolean isValueInstanceOfInteger(Visitable term) {
		return castToVisitableBuiltin(term).getBuiltin() instanceof Integer;
	}
	
	public static boolean isValueInstanceOfString(Visitable term) {
		return castToVisitableBuiltin(term).getBuiltin() instanceof String;
	}
	
	public static String getValueFromTermString(Visitable term) {
		return (String) castToVisitableBuiltin(term).getBuiltin();
	}
	
	public static int getValueFromTermInteger(Visitable term) {
		return (Integer) castToVisitableBuiltin(term).getBuiltin(); 
	}
	
	private static VisitableBuiltin<?> castToVisitableBuiltin(Visitable term) {
		if (isInstanceOfVisitableBuiltin(term)) {
			return (VisitableBuiltin<?>) term;
		} else {
			return new VisitableBuiltin<Object>(null);
		}
	}
	
	private static boolean isInstanceOfVisitableBuiltin(Visitable term) {
		return term instanceof VisitableBuiltin<?>;
	}
	
	public static boolean isInstanceOfVisitable(Object term) {
		return term instanceof Visitable;
	}
}
