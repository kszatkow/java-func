package chapter6.ex6_3.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class MapTest {

	@Test
	public void mapNoneTest() {
		Option<Integer> none = Option.none();
		assertEquals(Option.none(), none.map(i -> Double.valueOf(i)));
	}
	
	@Test
	public void mapSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Double.valueOf(3), oa.map(i -> Double.valueOf(i)).getOrElse(() -> 0.0));
	}

}
