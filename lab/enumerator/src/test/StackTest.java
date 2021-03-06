package test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import tom.library.theory.BadInputException;
import tom.library.theory.ForSome;
import tom.library.theory.PropCheck;
import examples.adt.stack.EmptyStackException;
import examples.adt.stack.IStack;
import examples.adt.stack.StackFactory;
import examples.adt.stack.stack.types.Stack;
import examples.adt.stack.stacklanguage.types.StackL;

@RunWith(PropCheck.class)
public class StackTest {

	private static StackFactory factory= StackFactory.getInstance(StackFactory.ARRAY);
	private static StackFactory factory2= StackFactory.getInstance(StackFactory.ARRAY);
	private static IStack init= factory.makeStack();
 
//	@BeforeClass
//	public static void setUp() {
//		factory1 = StackFactory.getInstance(StackFactory.ARRAY);
//		factory2 = StackFactory.getInstance(StackFactory.ARRAY);;
//		init = factory1.makeStack();
//	}

//	 @Ignore
	@Test
	public void testIsEmptyEmpty() {
		IStack s = init.empty();
		assertThat(s.isEmpty(), is(true));
	}

//	 @Ignore
	@Test
	public void testEmptySize() {
		IStack s = init.empty();
		assertThat(s.size(), is(0));
	}

//	@Ignore
	@Test(expected = EmptyStackException.class)
	public void testEmptyPop() throws EmptyStackException {
		IStack s = init.empty();
		s.pop();
	}

//	 @Ignore
	@Test(expected = EmptyStackException.class)
	public void testEmptyTop() throws EmptyStackException {
		IStack s = init.empty();
		s.top();
	}

//	@Ignore
	@Theory
	public void testNonEmptySize(
			@ForSome(minSampleSize = 25, maxSampleSize = 30, numberOfSamples = 20) Stack gs) {
		IStack s = factory.makeStack(gs);
		assumeThat(s.isEmpty(), is(false));
		assertThat(s.size(), greaterThan(0));
	}

//	 @Ignore
	@Theory
	public void testPushSize(
			@ForSome(minSampleSize = 25, maxSampleSize = 30) Stack gs,
			@ForSome(maxSampleSize = 10) int n) {
		IStack s = factory.makeStack(gs);
		int initSize = s.size();
//		s.push(n);
		int finalSize = s.push(n).size();
		assertThat(finalSize, is(initSize + 1));
	}

//	 @Ignore
	@Theory
	public void testPopSize(
			@ForSome(minSampleSize = 0, maxSampleSize = 50, numberOfSamples = 100) Stack gs)
			throws EmptyStackException {
		IStack s = factory.makeStack(gs);
		assumeThat(s.isEmpty(), equalTo(false));
		int initSize = s.size();
//		s.pop();
		assertThat(s.pop().size(), is(initSize - 1));
	}

	@Theory
	public void testPopPush(
			@ForSome(minSampleSize = 0, maxSampleSize = 30) Stack gs,
			@ForSome(maxSampleSize = 10) int n) throws EmptyStackException {
		IStack s = factory.makeStack(gs);
		IStack sclone = factory.makeStack(gs);
		assertThat(s.push(n).pop(), is(sclone));
	}

//	@Ignore
	@Theory public void testTop(
			@ForSome(minSampleSize = 10, maxSampleSize = 20) Stack gs,
			@ForSome(maxSampleSize = 10) int n) throws EmptyStackException {
		IStack s = factory.makeStack(gs);
		assertThat(s.push(n).top(), is(n));
	}
	
	@Theory public void testATerm(
			@ForSome(maxSampleSize = 100, numberOfSamples=2000) Stack gs) {
		assertThat(Stack.fromTerm(gs.toATerm()), is(gs));
	}
	
	
	
	// doesn't work for GOM (see StackFactory) 
	@Ignore
	@Theory
	public void testSameBehaviour(
			@ForSome(minSampleSize = 25, maxSampleSize = 30, numberOfSamples = 20) StackL gs)
			throws EmptyStackException, BadInputException {
		IStack stack1 = factory.evaluateStack(gs);
		IStack stack2 = factory2.evaluateStack(gs);

		
		 assertThat(stack1.isEmpty(), is(stack2.isEmpty()));
		
		 assertThat(stack1.size(), is(stack2.size()));
		
		 assumeThat(stack1.isEmpty(), equalTo(false));
		 assertThat(stack1.top(), is(stack2.top()));


		 assertThat(stack1.pop(), is(stack2.pop()));
		 
//		Integer sRes = stack1.pop();
//		Integer sBisRes = stack2.pop();
//		// same result
//		assertThat(sRes, is(sBisRes));
//		// same side-effect
//		assertThat(stack1.isEmpty(), is(stack2.isEmpty()));
//
//		assertThat(stack1.size(), is(stack2.size()));
//
//		assumeThat(stack1.isEmpty(), equalTo(false));
//		assertThat(stack1.top(), is(stack2.top()));

	}
	
	
	
	/*
	 * TEST GOM IMPLEMENTATION
	 */
	// use GOM for factory1 instead
//	@Theory public void testTopGom(
//			@ForSome(minSampleSize = 25, maxSampleSize = 50) Stack gs,
//			@ForSome(maxSampleSize = 10) int n) throws EmptyStackException {
//		assertThat(gs.push(n).top(), is(n));
//	}
	
	
}
