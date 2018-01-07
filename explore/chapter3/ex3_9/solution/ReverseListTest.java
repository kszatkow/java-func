package chapter3.ex3_9.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ReverseListTest {

	@Test
	public void reverseEmptyList() {
		List<Integer> list = new ArrayList<>();
		
		List<Integer> result = ReverseList.reverse(list);
		
		assertEquals(list, result);
	}
	
	@Test
	public void reverseNonEmptyList() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		List<Integer> result = ReverseList.reverse(list);
		
		Collections.reverse(list);
		assertEquals(list, result);
	}
	
	@Test
	public void reverseCharList() {
		List<Character> list = Arrays.asList('1','2','3');
		
		List<Character> result = ReverseList.reverse(list);
		
		assertEquals(Arrays.asList('3', '2', '1'), result);
	}
}
