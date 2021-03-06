package tom.library.theory;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.contrib.theories.ParametersSuppliedBy;

// CHANTIER

/**
 * Mark a parameter of a {@link org.junit.contrib.theories.Theory Theory} method
 * with this annotation to have generated values supplied to it exhaustively as
 * in SmallCheck.
 */

@ParametersSuppliedBy(ForSomeValueSupplier.class)
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ForSome {

	public boolean exhaustive() default false;    // random enumeration by default
	public int minSampleSize() default 0;    // minimal size of the example
	public int maxSampleSize() default 10;   // maximal size of the example
	public int numberOfSamples() default 0;  // maximal number of samples (0 means no limit for exhaustive, and one per part in random)	

}
