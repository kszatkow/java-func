package chapter6.ex6_1.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;


public class GetOrElseTest {

	@Test
	public void noneTest() {
		Option<Integer> none = Option.none();
		assertSame(Option.none(), none);
	}
	
	@Test
	public void getOrElseNoneTest() {
		Option<Integer> oa = Option.none();
		
		assertEquals(Integer.valueOf(0), oa.getOrElse(0));
	}
	
	@Test
	public void getOrElseSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Integer.valueOf(3), oa.getOrElse(0));
	}
	
}
