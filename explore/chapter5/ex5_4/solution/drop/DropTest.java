package chapter5.ex5_4.solution.drop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DropTest {

	@Test
	public void testDropZeroEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.drop(0));
	}
	
	@Test
	public void testDropZeroNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		List<Integer> newList = list.drop(0);
		
		assertSame(list, newList);
	}
	
	
	@Test
	public void testDropEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.drop(1));
	}
	
	@Test
	public void testDropInsufficientElements() {
		List<Integer> list = List.list(1,2,3);
	
		assertThrows(IllegalStateException.class, () -> list.drop(4));
	}
	
	@Test
	public void testDropInTheMiddle() {
		List<Integer> list = List.list(1,2,3,4);
	
		List<Integer> newList = list.drop(2);
		
		assertEquals("[3, 4, NIL]", newList.toString());
	}
	
	@Test
	public void testDropFirst() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.drop(1);
		
		assertEquals("[2, 3, NIL]", newList.toString());
	}
	
	@Test
	public void testDropAll() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.drop(3);

		assertTrue(newList.isEmpty());
	}
	
	@Test
	public void testDropAllSingletonList() {
		List<Integer> list = List.list(1);
	
		List<Integer> newList = list.drop(1);
		
		assertTrue(newList.isEmpty());
	}
	
}
