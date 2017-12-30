package ks.java.func;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class FunctionUtilsTest {

	@Test
	public void testTripleSquareCompose() {
		Function<Integer, Integer> tripleSquare = FunctionUtils.compose(new TripleFunction(), new SquareFunction());
	
		int result = tripleSquare.apply(2);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testSquareTripleCompose() {
		Function<Integer, Integer> squareTriple = FunctionUtils.compose(new SquareFunction(), new TripleFunction());
	
		int result = squareTriple.apply(2);
		
		assertEquals(36, result);
	}
}
