package ks.java.func;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Disabled;
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
	
	@Test
	@Disabled
	public void testComposeOverflow() {
		final int composeitionsNum = 10000;
		
		Function<Integer, Integer> g = x -> x;
		Function<Integer, Integer> f = x -> x + 1;
		for (int i = 0; i < composeitionsNum; ++i) {
			g = FunctionUtils.compose(f, g);
		}
		
		// this call is supposed to cause stack overflow because of too many compositions
		assertNotNull(g.apply(1));
	}
}
