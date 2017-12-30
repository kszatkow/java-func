package ks.java.func;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FunctionTest {

	@Test
	public void tripleFunctionTest() {
		Function<Integer, Integer> triple = new TripleFunction();
		
		int result = triple.apply(4);
		
		assertEquals(12, result);
	}
	
	@Test
	public void squareFunctionTest() {
		Function<Integer, Integer> square = new SquareFunction();
		
		int result = square.apply(4);
		
		assertEquals(16, result);
	}
	
	
}
