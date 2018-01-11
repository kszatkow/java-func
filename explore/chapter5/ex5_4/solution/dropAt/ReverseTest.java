package chapter5.ex5_4.solution.dropAt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReverseTest {

	@Test
	public void testReverseEmpty() {
		List<Integer> list = List.list();
	
		List<Integer> newList = list.reverse();
		
		assertTrue(newList.isEmpty());
	}
	
	@Test
	public void testReverse() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.reverse();
		
		assertEquals("[3, 2, 1, NIL]", newList.toString());
	}
	
	@Test
	public void testSingleton() {
		List<Integer> list = List.list(1);
	
		List<Integer> newList = list.reverse();
		
		assertEquals("[1, NIL]", newList.toString());
	}
	
}
