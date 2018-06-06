package chapter5.ex5_17.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TripleTest {

	private static List<Integer> triple(List<Integer> list) {
//		return list.foldLeft(List.<Integer>list(), x -> elem -> x.cons(elem * 3)).reverse();
		return list.foldRight(List.list(), elem -> x -> x.cons(elem * 3));
	}

	
	@Test
	public void testTripleEmptyList() {
		List<Integer> list = List.list();
		
		assertEquals(list, triple(list));
	}
	
	@Test
	public void testTripleNonEmptyList() {
		List<Integer> list = List.list(1,2,3);

		assertEquals("[3, 6, 9, NIL]", triple(list).toString());
	}
	
	@Test
	public void testInitSingletonList() {
		List<Integer> list = List.list(2);

		assertEquals("[6, NIL]", triple(list).toString());
	}
	
}
