package chapter5.ex5_2.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SetHeadTest {

	@Test
	public void testSetHeadEmptyList() {
		List<Integer> list = List.list();
		
		List<Integer> newList = list.setHead(12);
		
		assertEquals(Integer.valueOf(12), newList.head());
		assertEquals(List.NIL, newList.tail());
	}
	
	@Test
	public void testSetHeadNonEmptyList() {
		List<Integer> list = List.list(1,2,3);
		
		List<Integer> newList = list.setHead(12);
		
		assertEquals(Integer.valueOf(12), newList.head());
		assertEquals(list.tail(), newList.tail());
	}
	
	@Test
	public void testSetHeadSingletonList() {
		List<Integer> list = List.list(1);
		
		List<Integer> newList = list.setHead(12);
		
		assertEquals(Integer.valueOf(12), newList.head());
		assertEquals(List.NIL, newList.tail());
	}
	
}
