package chapter4.ex4_7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import chapter4.ex4_3.FoldList;
import chapter4.ex4_4.Range;
import ks.java.func.Function;

public class ComposeAllTest {

	@Test
	public void testComposeCall() {
		Function<Integer, Integer> f = x -> 3 * x;
		Function<Integer, Integer> g = x -> x + 7;
		Function<Integer, Integer> h = x -> 2 * x;
		
		ComposedCall<Integer> composed = 
				ComposedCall.composed(
						ComposedCall.composed(
								ComposedCall.single(f), g), h);
		
		int result = composed.eval(3);
		
		assertEquals(39, result);
	}
	
	@Test
	public void testCompose1() {
		Function<Integer, Integer> f = ComposeAll.<Integer>composeAll(
				x -> 3 * x,
				x -> x + 7,
				x -> 2 * x);
		
		int result = f.apply(3);
		
		assertEquals(39, result);
	}
	
	@Test
	public void testCompose2() {
		Function<Integer, Integer> add = y -> y + 1;
		Function<Integer, Integer> f = ComposeAll.composeAll(map(Range.range(0, 500), x -> add));
		
		int result = f.apply(0);
		
		assertEquals(500, result);
	}
	
	@Test
	public void testCompose_NoStackOverflow() {
		Function<Integer, Integer> add = y -> y + 1;
		Function<Integer, Integer> f = ComposeAll.composeAll(map(Range.range(0, 15000), x -> add));
		
		int result = f.apply(0);
		
		assertEquals(15000, result);
	}
	
	private static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		return FoldList.foldLeft(list, new ArrayList<U>(), l -> e -> append(l, f.apply(e)));
	}
	
	private static <T> List<T> append(List<T> list, T elem) {
		list.add(elem);
		return list;
	}
	
	@Test
	public void testCompose2FR() {
		Function<Integer, Integer> add = y -> y + 1;
		Function<Integer, Integer> f = ComposeAll.composeAllFoldRight(map(Range.range(0, 500), x -> add));
		
		int result = f.apply(0);
		
		assertEquals(500, result);
	}
	
	@Test
	public void testComposeFR_NoStackOverflow() {
		Function<Integer, Integer> add = y -> y + 1;
		Function<Integer, Integer> f = ComposeAll.composeAllFoldRight(map(Range.range(0, 15000), x -> add));
		
		int result = f.apply(0);
		
		assertEquals(15000, result);
	}
	
}
