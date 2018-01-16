package chapter5.ex5_9.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthTest {

	@Test
	public void testInitEmptyList() {
		List<Integer> list = List.list();
		
		assertEquals(0, list.length());
	}
	
	@Test
	public void testInitNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		assertEquals(3, list.length());
	}
	
	@Test
	public void testInitSingletonList() {
		List<Integer> list = List.list(2);

		assertEquals(1, list.length());
	}
	
}
