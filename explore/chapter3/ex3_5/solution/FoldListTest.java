package chapter3.ex3_5.solution;

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
		
		int result = FoldList.fold(list, 0, x -> y -> x + y);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testFoldSingletonList() {
		List<Integer> list = Collections.singletonList(17);
		
		int result = FoldList.fold(list, 0, x -> y -> x + y);
		
		assertEquals(17, result);
	}
	
	@Test
	public void testFoldNonEmptyList() {
		List<Integer> list= Arrays.asList(1,2,3,4,5);
		
		int result = FoldList.fold(list, 0, x -> y -> x + y);
		
		assertEquals(15, result);
	}
	
}
