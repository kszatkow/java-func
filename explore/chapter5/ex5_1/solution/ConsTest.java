package chapter5.ex5_1.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConsTest {

	@Test
	public void testConsEmptyList() {
		List<Integer> list = List.list();
		
		List<Integer> consList = list.cons(12);
		
		assertEquals(Integer.valueOf(12), consList.head());
		assertEquals(List.NIL, consList.tail());
	}
	
	@Test
	public void testConsNonEmptyList() {
		List<Integer> list = List.list(1,2,3);
		
		List<Integer> consList = list.cons(12);
		
		assertEquals(Integer.valueOf(12), consList.head());
		assertEquals(list, consList.tail());
	}
	
}
