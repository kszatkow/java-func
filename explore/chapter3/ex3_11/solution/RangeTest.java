package chapter3.ex3_11.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RangeTest {

	@Test
	public void testInvalidRange() {
		List<Integer> result = Range.range(10, 8);
		
		assertEquals(Collections.<Integer>emptyList(), result);
	}
	
	@Test
	public void testEmptyRange() {
		List<Integer> result = Range.range(10, 10);
		
		assertEquals(Collections.<Integer>emptyList(), result);
	}
	
	@Test
	public void testOneElemRange() {
		List<Integer> result = Range.range(10, 11);
		
		assertEquals(Collections.singletonList(10), result);
	}
	
	@Test
	public void testLogerRange() {
		List<Integer> result = Range.range(10, 15);
		
		assertEquals(Arrays.asList(10, 11, 12, 13, 14), result);
	}
}
