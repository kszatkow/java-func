package chapter3.ex3_12.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GenericRangeTest {

	@Test
	public void testInvalidRange() {
		List<Integer> result = GenericRange.unfold(10, x -> x + 1, x -> x < 8);
		
		assertEquals(Collections.<Integer>emptyList(), result);
	}
	
	@Test
	public void testEmptyRange() {
		List<Integer> result = GenericRange.unfold(10, x -> x + 1, x -> x < 10);
		
		assertEquals(Collections.<Integer>emptyList(), result);
	}
	
	@Test
	public void testOneElemRange() {
		List<Integer> result = GenericRange.unfold(10, x -> x + 1, x -> x < 11);
		
		assertEquals(Collections.singletonList(10), result);
	}
	
	@Test
	public void testLogerRange() {
		List<Integer> result = GenericRange.unfold(10, x -> x + 1, x -> x < 15);
		
		assertEquals(Arrays.asList(10, 11, 12, 13, 14), result);
	}
	
	@Test
	public void testStringRange() {
		List<String> result = GenericRange.unfold("a", s -> s + "a", s -> s.length() < 5);
		
		assertEquals(Arrays.asList("a", "aa", "aaa", "aaaa"), result);
	}
	
}
