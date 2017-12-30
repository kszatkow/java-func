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
	
	@Test
	public void addFunctionTest() {
		Function<Integer, Function<Integer, Integer>> add = new AddFunction();
		
		int result = add.apply(5).apply(13);
		
		assertEquals(18, result);
	}
	
	@Test
	public void multBinaryOpTest() {
		BinaryOperator mult = x -> y -> x * y; 
				
		int result = mult.apply(5).apply(13);
		
		assertEquals(65, result);
	}
	
}
