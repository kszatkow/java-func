package chapter4.ex4_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FoldListTest {

	@Test
	public void testFoldEmptyList() {
		List<Integer> list = new ArrayList<>();
		
		Integer result = FoldList.foldLeft(list, Integer.valueOf(0), x -> y -> x + y);
		
		assertEquals(Integer.valueOf(0), result);
	}
	
	@Test
	public void testFoldSingletonList() {
		List<Integer> list = Collections.singletonList(17);
		
		int result = FoldList.<Integer, Integer>foldLeft(list, 0, x -> y -> x + y);
		
		assertEquals(17, result);
	}
	
	@Test
	public void testFoldNonEmptyList() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		int result = FoldList.<Integer, Integer>foldLeft(list, 0, x -> y -> x + y);
		
		assertEquals(15, result);
	}
	
	@Test 
	public void testFoldLeft() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);

		String result = FoldList.foldLeft(list, "0", x -> y -> addSI(x,y));
		
		assertEquals("(((((0 + 1) + 2) + 3) + 4) + 5)", result);
	}
	
	private static String addSI(String s, Integer i) {
		return "(" + s + " + " + i + ")";
	}
	
}
