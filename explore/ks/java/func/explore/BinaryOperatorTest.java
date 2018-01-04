package ks.java.func.explore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BinaryOperatorTest {


	@Test
	public void testMultBinaryOp() {
		BinaryOperator mult = x -> y -> x * y; 
				
		int result = mult.apply(5).apply(13);
		
		assertEquals(65, result);
	}
	
}
