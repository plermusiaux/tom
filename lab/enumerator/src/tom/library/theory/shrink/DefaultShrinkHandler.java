package tom.library.theory.shrink;

import org.junit.contrib.theories.internal.Assignments;

import tom.library.theory.internal.CounterExample;
import tom.library.theory.internal.ExecutionHandler;
import tom.library.theory.internal.ParameterizedAssertionFailure;
import tom.library.theory.internal.TestObject;
import tom.library.theory.shrink.suppliers.BigShrinkValueSupplier;
import tom.library.theory.shrink.suppliers.ShrinkParameterSupplier;
import tom.library.theory.shrink.suppliers.ShrinkValueSupplier;

public class DefaultShrinkHandler implements ShrinkHandler {
	private TestObject testObject;
	private int shrunkCount = 0;
	private CounterExample initialCounterExample;
	private CounterExample currentCounterExample;
	
	public DefaultShrinkHandler(TestObject testObject) {
		this.testObject = testObject;
	}
	
	@Override
	public void shrink(CounterExample counterExample) throws Throwable {
		initializeCounterExamples(counterExample);
		handleShrink();
	}
	
	protected void handleShrink() throws Throwable {
		ExecutionHandler handler = new ExecutionHandler(this) {
			@Override
			public void handleFailures(Throwable e, String methodName, Object... params) throws Throwable {
				/*
				 * use repeatShrink() and comment the method for throwing failure 
				 * if use the previous version of shrink.
				 */
				 repeatShrink(e, params);
				
				/*
				 * un-comment  throwParameterizedAssertionFailureWithCounterExamples() 
				 * when use the new version.
				 */
				//throwParameterizedAssertionFailureWithCounterExamples(e, params);
			}
		};
		/*
		 * To use previous version, use
		 * evaluateAssignment(handler, new ShrinkValueSupplier());
		 * 
		 * BigShrinkValueSupplier() generates large number of smaller terms
		 * from the counter-example and sort them. 
		 */
		//evaluateAssignment(handler, new BigShrinkValueSupplier());
		evaluateAssignment(handler, new ShrinkValueSupplier());
		
		
	}
	
	protected void repeatShrink(Throwable e, Object... params) throws Throwable {
		CounterExample temporaryCounterExample = CounterExample.build(params);
		if (temporaryCounterExample.isSmallerThan(currentCounterExample)) {
			increaseShrunkCount();
			currentCounterExample = temporaryCounterExample;
			handleShrink();
		}
		throwParameterizedAssertionFailureWithCounterExamples(e, params);
	}
	
	private void evaluateAssignment(ExecutionHandler handler,
			ShrinkParameterSupplier supplier) throws Throwable {
		ShrinkAssignmentRunner runner = new ShrinkAssignmentRunner(testObject, handler, supplier);
    	runner.runWithAssignment(getUnassignedAssignments(), currentCounterExample);
	}

	private Assignments getUnassignedAssignments() throws Exception {
		return Assignments.allUnassigned(testObject.getMethod(), testObject.getTestClass());
	}
	
	private void increaseShrunkCount() {
		shrunkCount++;
	}

	protected void initializeCounterExamples(CounterExample counterExample) {
		initialCounterExample = counterExample;
		currentCounterExample = counterExample;
	}
	
	protected void throwParameterizedAssertionFailureWithCounterExamples(Throwable e, Object... params) throws Throwable {
		if (params.length == 0) {
			throw e;
		}
		throw new ParameterizedAssertionFailure(e, testObject.getMethodName(), shrunkCount, 
				initialCounterExample.getCounterExamples(), params);
	}
}
