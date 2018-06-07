package chapter6.ex6_4.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class FlatMapTest {

	@Test
	public void flatMapNoneTest() {
		Option<Integer> none = Option.none();
		assertEquals(Option.none(), none.flatMap(i -> Option.some(Double.valueOf(i))));
	}
	
	@Test
	public void flatMapSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Double.valueOf(3), 
				oa.flatMap(i -> Option.some(Double.valueOf(i))).getOrElse(() -> 0.0));
	}

}
