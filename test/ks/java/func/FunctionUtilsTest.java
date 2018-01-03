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
	
	@Test
	public void testDoubleManualCompose() {
		Function<
				// the first argument is a function x : int -> int ,e.g. square
				Function<Integer, Integer>,
				// the return type is a function taking a function y: int -> int and returning a function int -> int
				Function<
						Function<Integer, Integer>, 	// the second argument is a function int -> int, e.g. triple
						Function<Integer, Integer>		// the return type is a function int -> int, e.g square the tripled value:)
				>
		> compose = x -> y -> z -> x.apply(y.apply(z));		// (int -> int) -> ((int -> int) -> (int -> int))
															// same without unnecessary parentheses: (int -> int) -> (int -> int) -> int -> int
		int result = compose.apply(new SquareFunction()).apply(new TripleFunction()).apply(2);
		
		assertEquals(36, result);
	}
	
	@Test
	public void testTripleManualCompose() {
		// based on the example above it's quite easy to write triple composition
		// (int -> int) -> ((int -> int) -> ((int -> int) -> (int -> int)))
		// or without unnecessary parentheses: (int -> int) -> (int -> int) -> (int -> int) -> int -> int
		Function<
			Function<Integer, Integer>,
			Function<
				Function<Integer, Integer>,
				Function<
					Function<Integer, Integer>,
					Function<Integer, Integer>
				>
			>
		> compose = x -> y -> z -> arg -> x.apply(y.apply(z.apply(arg)));
		
		int result = compose.apply(new SquareFunction()).apply(new TripleFunction()).apply(new SquareFunction()).apply(2);
		
		assertEquals(144, result);
	}
	
	@Test
	public void testComposeSquareAndAdd() {
		// when using type aliases like BinaryOperator types get a bit shorter;)
		// (int -> int) -> (int -> int -> int) -> int -> int -> int
		Function<
			Function<Integer, Integer>,
			Function<
				BinaryOperator,
				BinaryOperator
			>
		> compose = x -> y -> arg1 -> arg2 -> x.apply(y.apply(arg1).apply(arg2));
		
		int result = compose.apply(new SquareFunction()).apply(x -> y -> x + y).apply(2).apply(3);
		
		assertEquals(25, result);
	}
	
	@Test
	public void testGenericComposeSimple() {
		Function<Integer, Integer> tripleSquare = FunctionUtils.genericCompose(new TripleFunction(), new SquareFunction());
		
		int result = tripleSquare.apply(2);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testGenericComposeVariousTypes() {
		Function<Integer, String> printSquare = FunctionUtils.genericCompose(x -> "Square of x is: " + x, new SquareFunction());
		
		String result = printSquare.apply(2);
		
		assertEquals("Square of x is: 4", result);
	}
	
	@Test
	public void testHigherComposeSimple() {
		// this one won't compile as java infers T, U, V to be of type Object and Function<Integer, Integer> is not of type Function<Object, Object>
//		int tripleSquared = FunctionUtils.higherCompose().apply(new TripleFunction()).apply(new SquareFunction()).apply(2);
		// dirty solution is to specify the types of T, U, V explicitly
		int tripleSquared = FunctionUtils.<Integer, Integer, Integer>higherCompose().apply(new TripleFunction()).apply(new SquareFunction()).apply(2);
		
		assertEquals(12, tripleSquared);
	}
	
	@Test
	public void testHigherAndThenSimple() {
		int tripleSquared = FunctionUtils.<Integer, Integer, Integer>higherAndThen().apply(new SquareFunction()).apply(new TripleFunction()).apply(2);
		
		assertEquals(12, tripleSquared);
	}
	
	@Test
	public void testHigherComposeVariousTypes() {
		Function<Double, Integer> f = a -> (int) (a * 3);
		Function<Long, Double> g = a -> a + 2.0;
		
		int result = FunctionUtils.<Long, Double, Integer>higherCompose().apply(f).apply(g).apply(1L);
		
		assertEquals(9, result);
	}
	
	@Test
	public void testComposeAndThenVariousTypes() {
		Function<Double, Integer> f = a -> (int) (a * 3);
		Function<Long, Double> g = a -> a + 2.0;
		
		int result = FunctionUtils.<Long, Double, Integer>higherAndThen().apply(g).apply(f).apply(1L);
		
		assertEquals(9, result);
	}
}
