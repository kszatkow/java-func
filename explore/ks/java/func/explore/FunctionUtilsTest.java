package ks.java.func.explore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ks.java.func.Function;
import ks.java.func.aux.SquareFunction;
import ks.java.func.aux.TripleFunction;

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
	
	@Test // this one is going to fail because of stack overflow
	public void testComposeOverflow() {
		final int composeitionsNum = 10000;
		
		Function<Integer, Integer> g = x -> x;
		Function<Integer, Integer> f = x -> x + 1;
		for (int i = 0; i < composeitionsNum; ++i) {
			g = FunctionUtils.compose(f, g);
		}
		final Function<Integer, Integer> composed = g;
		
		// this call is supposed to cause stack overflow because of too many compositions
		assertThrows(StackOverflowError.class, () -> composed.apply(1));
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
	public void testPartialA() {
		Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
		Function<Integer, Integer> add2 = FunctionUtils.partialA(2, add);
		
		int result = add2.apply(5);
		
		assertEquals(7, result);
	}
	
	@Test
	public void testPartialB() {
		Function<Integer, Function<Integer, Integer>> divide = x -> y -> x / y;
		Function<Integer, Integer> divideBy2 = FunctionUtils.partialB(2, divide);
		
		int result = divideBy2.apply(10);
		
		assertEquals(5, result);
	}
	
}
