package chapter4.ex4_6.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import chapter4.ex4_3.solution.FoldList;
import chapter4.ex4_4.solution.Range;
import ks.java.func.Function;

public class ComposeAllTest {

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
	public void testComposeStackOverflow() {
		Function<Integer, Integer> add = y -> y + 1;
		
		Function<Integer, Integer> f = ComposeAll.composeAll(map(Range.range(0, 15000), x -> add));
		
		assertThrows(StackOverflowError.class, () -> f.apply(0));
	}
	
	private static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		return FoldList.foldLeft(list, new ArrayList<U>(), l -> e -> append(l, f.apply(e)));
	}
	
	private static <T> List<T> append(List<T> list, T elem) {
		list.add(elem);
		return list;
	}
	
	
}
