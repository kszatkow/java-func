package chapter5.ex5_20.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FilterTest {

	@Test 
	public void testFilterEmptyList() {
		List<Integer> list = List.list();
		
		assertThrows(IllegalStateException.class, () -> list.filter(x -> x % 2 == 0));
	}
	
	@Test
	public void testFilterNonEmptyList_SomeMatching() {
		List<Integer> list = List.list(1,2,3,4,5,6,7,8);

		assertEquals("[2, 4, 6, 8, NIL]", list.filter(x -> x % 2 == 0).toString());
	}

	@Test
	public void testFilterNonEmptyList_NoneMatching() {
		List<Integer> list = List.list(1,3,5,7);

		assertEquals("[NIL]", list.filter(x -> x % 2 == 0).toString());
	}

	@Test
	public void testFilterNonEmptyList_AllMatching() {
		List<Integer> list = List.list(2,4,6,8);

		assertEquals("[2, 4, 6, 8, NIL]", list.filter(x -> x % 2 == 0).toString());
	}
	
	@Test
	public void testFilterSingletonList_Matching() {
		List<Integer> list = List.list(2);

		assertEquals("[2, NIL]", list.filter(x -> x % 2 == 0).toString());
	}

	@Test
	public void testFilterSingletonList_NotMatching() {
		List<Integer> list = List.list(1);

		assertEquals("[NIL]", list.filter(x -> x % 2 == 0).toString());
	}
}
