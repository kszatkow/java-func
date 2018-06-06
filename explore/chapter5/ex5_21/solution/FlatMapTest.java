package chapter5.ex5_21.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FlatMapTest {

	@Test 
	public void testFlatMapEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.flatMap(x -> List.list(x)));
	}
	
	@Test
	public void testFlatMapNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		assertEquals("[1, -1, 2, -2, 3, -3, NIL]", list.flatMap(x -> List.list(x, -x)).toString());
	}

	@Test
	public void testFilterSingletonList() {
		List<Integer> list = List.list(1);

		assertEquals("[1, -1, NIL]", list.flatMap(x -> List.list(x, -x)).toString());
	}
}
