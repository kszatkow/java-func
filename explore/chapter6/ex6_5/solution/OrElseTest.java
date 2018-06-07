package chapter6.ex6_5.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class OrElseTest {

	@Test
	public void flatMapNoneTest() {
		Option<Integer> none = Option.none();
		assertEquals(Integer.valueOf(3), 
				none.orElse(() -> Option.some(3)).getOrElse(() -> 0));
	}
	
	@Test
	public void flatMapSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Integer.valueOf(3), oa.orElse(() -> Option.some(0)).getOrElse(() -> 0));
	}

}
