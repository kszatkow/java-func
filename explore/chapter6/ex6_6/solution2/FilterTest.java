package chapter6.ex6_6.solution2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class FilterTest {

	@Test
	public void filterNoneTest() {
		Option<Integer> none = Option.none();
		assertEquals(Option.none(), none.filter(x -> true));
	}
	
	@Test
	public void filterTrueSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Integer.valueOf(3), 
				oa.filter(x -> x % 3 == 0).getOrElse(() -> 0));
	}

	@Test
	public void filterFalseSomeTest() {
		Integer a = 4;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Option.none(), oa.filter(x -> x % 3 == 0));
	}
}
