package chapter4.ex4_6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chapter3.ex3_10.solution.ListMap;
import chapter3.ex3_14.solution.Range;
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
		Function<Integer, Integer> f = ComposeAll.composeAll(ListMap.map(Range.range(0, 500), x -> add));
		
		int result = f.apply(0);
		
		assertEquals(500, result);
	}
	
	@Test
	public void testComposeStackOverflow() {
		Function<Integer, Integer> add = y -> y + 1;
		
		assertThrows(StackOverflowError.class, () -> ComposeAll.composeAll(ListMap.map(Range.range(0, 15000), x -> add)));
	}
}
