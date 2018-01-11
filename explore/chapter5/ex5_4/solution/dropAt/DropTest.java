package chapter5.ex5_4.solution.dropAt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DropTest {

	@Test
	public void testDropEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.dropAt(1));
	}
	
	@Test
	public void testDropInsufficientElements() {
		List<Integer> list = List.list(1,2,3);
	
		assertThrows(IllegalStateException.class, () -> list.dropAt(3));
	}
	
	@Test
	public void testDropMiddle() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.dropAt(1);
		
		assertEquals("[1, 3, NIL]", newList.toString());
	}
	
	@Test
	public void testDropFirst() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.dropAt(0);
		
		assertEquals("[2, 3, NIL]", newList.toString());
	}
	
	@Test
	public void testDropLast() {
		List<Integer> list = List.list(1,2,3);
	
		List<Integer> newList = list.dropAt(2);
		
		assertEquals("[1, 2, NIL]", newList.toString());
	}
	
	@Test
	public void testDropSingletonList() {
		List<Integer> list = List.list(1);
	
		List<Integer> newList = list.dropAt(0);
		
		assertTrue(newList.isEmpty());
	}
	
}
