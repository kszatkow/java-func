package ks.java.func;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ks.java.func.aux.AddFunction;
import ks.java.func.aux.SquareFunction;
import ks.java.func.aux.TripleFunction;

public class FunctionTest {

	@Test
	public void testTripleFunction() {
		Function<Integer, Integer> triple = new TripleFunction();
		
		int result = triple.apply(4);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testSquareFunction() {
		Function<Integer, Integer> square = new SquareFunction();
		
		int result = square.apply(4);
		
		assertEquals(16, result);
	}
	
	@Test
	public void testAddFunction() {
		Function<Integer, Function<Integer, Integer>> add = new AddFunction();
		
		int result = add.apply(5).apply(13);
		
		assertEquals(18, result);
	}
	
	@Test
	public void testMemberCompose() {
		Function<Integer, Integer> addFive = x -> x + 5;
		Function<Integer, Double> timesFive = x -> x * 5.0;
		Function<Integer, Double> composed = timesFive.compose(addFive);
		
		double result = composed.apply(5);
		
		assertEquals(50.0, result);
	}
	
	@Test
	public void testMemberAndThen() {
		Function<Integer, Integer> addFive = x -> x + 5;
		Function<Integer, Double> timesFive = x -> x * 5.0;
		Function<Integer, Double> composed = addFive.andThen(timesFive);
		
		double result = composed.apply(5);
		
		assertEquals(50.0, result);
	}
	
	@Test
	public void testIdentity() {
		Function<Integer, Integer> ident = Function.identity();
		
		for (int i = 0; i < 10; ++i) {
			assertEquals(Integer.valueOf(i), ident.apply(i));
		}
	}
	
	@Test
	public void testComposeSimple() {
		Function<Integer, Integer> tripleSquare = Function.compose(new TripleFunction(), new SquareFunction());
		
		int result = tripleSquare.apply(2);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testComposeVariousTypes() {
		Function<Integer, String> printSquare = Function.compose(x -> "Square of x is: " + x, new SquareFunction());
		
		String result = printSquare.apply(2);
		
		assertEquals("Square of x is: 4", result);
	}
	
	@Test
	public void testAndThenSimple() {
		Function<Integer, Integer> tripleSquare = Function.andThen(new SquareFunction(), new TripleFunction());
		
		int result = tripleSquare.apply(2);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testAndThenVariousTypes() {
		Function<Integer, String> printSquare = Function.andThen(new SquareFunction(), x -> "Square of x is: " + x);
		
		String result = printSquare.apply(2);
		
		assertEquals("Square of x is: 4", result);
	}
	
	@Test
	public void testParameterlessComposeSimple() {
		Function<Integer, Integer> squareTriple = Function.<Integer, Integer, Integer>compose().apply(new TripleFunction()).apply(new SquareFunction());
		
		int result = squareTriple.apply(2);
		
		assertEquals(36, result);
	}
	
	@Test
	public void testParameterlessComposeVariousTypes() {
		Function<Integer, String> printSquare = Function.<Integer, Integer, String>compose().apply(new SquareFunction()).apply(x -> "Square of x is: " + x);
		
		String result = printSquare.apply(2);
		
		assertEquals("Square of x is: 4", result);
	}
	
	@Test
	public void testParameterlessAndThenSimple() {
		Function<Integer, Integer> tripleSquare = Function.<Integer, Integer, Integer>andThen().apply(new TripleFunction()).apply(new SquareFunction());
		
		int result = tripleSquare.apply(2);
		
		assertEquals(12, result);
	}
	
	@Test
	public void testParameterlessAndThenVariousTypes() {
		Function<Integer, String> printSquare = Function.<Integer, String, Integer>andThen().apply(x -> "Square of x is: " + x).apply(new SquareFunction());
		
		String result = printSquare.apply(2);
		
		assertEquals("Square of x is: 4", result);
	}
	
	@Test
	public void testHigherComposeVariousTypes() {
		Function<Double, Integer> f = a -> (int) (a * 3);
		Function<Long, Double> g = a -> a + 2.0;
		
		int result = Function.<Long, Double, Integer>higherCompose().apply(f).apply(g).apply(1L);
		
		assertEquals(9, result);
	}
	
	@Test
	public void testHigherComposeSimple() {
		// this one won't compile as java infers T, U, V to be of type Object and Function<Integer, Integer> is not of type Function<Object, Object>
//		int tripleSquared = FunctionUtils.higherCompose().apply(new TripleFunction()).apply(new SquareFunction()).apply(2);
		// dirty solution is to specify the types of T, U, V explicitly
		int tripleSquared = Function.<Integer, Integer, Integer>higherCompose().apply(new TripleFunction()).apply(new SquareFunction()).apply(2);
		
		assertEquals(12, tripleSquared);
	}

	@Test
	public void testHigherAndThenSimple() {
		int tripleSquared = Function.<Integer, Integer, Integer>higherAndThen().apply(new SquareFunction()).apply(new TripleFunction()).apply(2);
		
		assertEquals(12, tripleSquared);
	}
	
	@Test
	public void testComposeAndThenVariousTypes() {
		Function<Double, Integer> f = a -> (int) (a * 3);
		Function<Long, Double> g = a -> a + 2.0;
		
		int result = Function.<Long, Double, Integer>higherAndThen().apply(g).apply(f).apply(1L);
		
		assertEquals(9, result);
	}
	
}
