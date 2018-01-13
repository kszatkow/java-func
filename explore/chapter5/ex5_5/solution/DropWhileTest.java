package chapter5.ex5_5.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ks.java.func.Function;

public class DropWhileTest {

	private Function<Integer, Boolean> isEven = x -> x % 2 == 0;
	
	@Test
	public void testDropWhileEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.dropWhile(isEven));
	}
	
	@Test
	public void testDropWhileNoneNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		List<Integer> newList = list.dropWhile(isEven);
		
		assertSame(list, newList);
	}
	
	@Test
	public void testDropFirst() {
		List<Integer> list = List.list(2,3,4);
	
		List<Integer> newList = list.dropWhile(isEven);
		
		assertEquals("[3, 4, NIL]", newList.toString());
	}
	
	@Test
	public void testDropSome() {
		List<Integer> list = List.list(2,4,6,7,8,9,10);
	
		List<Integer> newList = list.dropWhile(isEven);
		
		assertEquals("[7, 8, 9, 10, NIL]", newList.toString());
	}
	
	
	@Test
	public void testDropAll() {
		List<Integer> list = List.list(2,4,6,8);
	
		List<Integer> newList = list.dropWhile(isEven);

		assertTrue(newList.isEmpty());
	}
	
	@Test
	public void testDropAllSingletonList() {
		List<Integer> list = List.list(2);
	
		List<Integer> newList = list.dropWhile(isEven);
		
		assertTrue(newList.isEmpty());
	}
	
}
