package chapter3.ex3_10.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListMapTest {

	@Test
	public void testMapEmptyList() {
		List<Integer> list = Collections.<Integer>emptyList();
		
		List<String> result = ListMap.map(list, x -> String.valueOf(x));
		
		assertEquals(Collections.<String>emptyList(), result);
	}
	
	@Test
	public void testMapNonEmptyListSameTypes() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		List<Integer> result = ListMap.map(list, x -> 10 * x);
		
		assertEquals(Arrays.asList(10, 20, 30, 40, 50), result);
	}
	
	@Test
	public void testMapNonEmptyListDifferentTypes() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		List<String> result = ListMap.map(list, x -> String.valueOf(x));
		
		assertEquals(Arrays.asList("1", "2", "3", "4", "5"), result);
	}

	

	@Test
	public void testMap2EmptyList() {
		List<Integer> list = Collections.<Integer>emptyList();
		
		List<String> result = ListMap.map2(list, x -> String.valueOf(x));
		
		assertEquals(Collections.<String>emptyList(), result);
	}
	
	@Test
	public void testMap2NonEmptyListSameTypes() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		List<Integer> result = ListMap.map2(list, x -> 10 * x);
		
		assertEquals(Arrays.asList(10, 20, 30, 40, 50), result);
	}
	
	@Test
	public void testMap2NonEmptyListDifferentTypes() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		List<String> result = ListMap.map2(list, x -> String.valueOf(x));
		
		assertEquals(Arrays.asList("1", "2", "3", "4", "5"), result);
	}
}
