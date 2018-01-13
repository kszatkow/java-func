package chapter5.ex5_6.solution2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InitTest {

	@Test
	public void testInitEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.init());
	}
	
	@Test
	public void testInitNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		List<Integer> newList = list.init();
		
		assertEquals("[1, 2, NIL]", newList.toString());
	}
	
	@Test
	public void testInitSingletonList() {
		List<Integer> list = List.list(2);
	
		List<Integer> newList = list.init();

		assertTrue(newList.isEmpty());
	}
	
}
