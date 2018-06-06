package chapter5.ex5_19.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MapTest {

	@Test 
	public void testMapEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.map(x -> 3 * x));
	}
	
	@Test
	public void testMapNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		assertEquals("[3, 6, 9, NIL]", list.map(x -> 3 * x).toString());
	}
	
	@Test
	public void testMapSingletonList() {
		List<Integer> list = List.list(2);

		assertEquals("[6, NIL]", list.map(x -> 3 * x).toString());
	}
	
}
