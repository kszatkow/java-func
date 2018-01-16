package chapter5.ex5_10.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FoldLeftTest {

	@Test
	public void testInitEmptyList() {
		List<Integer> list = List.list();
		
		assertEquals(0, list.foldLeft(0, x -> y -> x + 1));
	}
	
	@Test
	public void testInitNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		assertEquals(3, list.foldLeft(0, x -> y -> x + 1));
	}
	
	@Test
	public void testInitSingletonList() {
		List<Integer> list = List.list(2);

		assertEquals(1, list.foldLeft(0, x -> y -> x + 1));
	}
	
}
