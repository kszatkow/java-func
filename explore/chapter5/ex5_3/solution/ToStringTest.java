package chapter5.ex5_3.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToStringTest {

	@Test
	public void testConsEmptyList() {
		List<Integer> list = List.list();
		
		String string = list.toString();
		
		assertEquals("[NIL]", string);
	}
	
	@Test
	public void testConsNonEmptyList() {
		List<Integer> list = List.list(1,2,3);
		
		String string = list.toString();
		
		assertEquals("[1, 2, 3, NIL]", string);
	}
	
}
