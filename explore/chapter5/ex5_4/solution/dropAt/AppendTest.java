package chapter5.ex5_4.solution.dropAt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppendTest {

	@Test
	public void testEmptyListToEmptyList() {
		List<Integer> list = List.list();
	
		List<Integer> newList = list.append(List.list());
		
		assertTrue(newList.isEmpty());
	}
	
	@Test
	public void testSingleToEmptyList() {
		List<Integer> list = List.list();
	
		List<Integer> newList = list.append(1);

		assertEquals("[1, NIL]", newList.toString());
	}
	
	@Test
	public void testListToEmptyList() {
		List<Integer> list = List.list();
	
		List<Integer> newList = list.append(List.list(1,2,3));

		assertEquals("[1, 2, 3, NIL]", newList.toString());
	}
	
	@Test
	public void testEmptyListToList() {
		List<Integer> list = List.list(1,2,3);
		
		List<Integer> newList = list.append(List.list());

		assertEquals("[1, 2, 3, NIL]", newList.toString());
	}

	@Test
	public void testSingleToList() {
		List<Integer> list = List.list(1,2,3);
		
		List<Integer> newList = list.append(4);

		assertEquals("[1, 2, 3, 4, NIL]", newList.toString());
	}
	
	@Test
	public void testListToList() {
		List<Integer> list = List.list(1,2,3);
		
		List<Integer> newList = list.append(List.list(4,5,6));

		assertEquals("[1, 2, 3, 4, 5, 6, NIL]", newList.toString());
	}
	
}
