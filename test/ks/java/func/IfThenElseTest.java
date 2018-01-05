package ks.java.func;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IfThenElseTest {

	// round up if condition is true, round down otherwise
	private Function<Boolean, Function<Double, Integer>> round = 
		IfThenElse.ifThenElse(x -> (int) Math.ceil(x), x -> (int) Math.floor(x));

	// return 0.0 if x is divisible by 3, return Double.valueOf(x) otherwise
	private Function<Integer, Double> weirdFunction = 
		IfThenElse.ifThenElse(x -> x % 3 == 0, x -> 0.0, x -> Double.valueOf(x));
	
	@Test
	public void testThenStaticBranch() {
		int result = round.apply(true).apply(4.5);
		
		assertEquals(5, result);
	}
	
	@Test
	public void testElseStaticBranch() {
		int result = round.apply(false).apply(4.5);
		
		assertEquals(4, result);	
	}
	
	@Test
	public void testThenExprBranch() {
		double result = weirdFunction.apply(12);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testElseExprBranch() {
		double result = weirdFunction.apply(13);
		
		assertEquals(13.0, result);
	}
}
