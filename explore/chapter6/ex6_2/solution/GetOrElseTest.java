package chapter6.ex6_2.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		
		assertEquals(Integer.valueOf(0), oa.getOrElse(() -> 0));
	}
	
	@Test
	public void getOrElseSomeTest() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Integer.valueOf(3), oa.getOrElse(() -> 0));
	}
	
	@Test
	public void getOrElseNoneTest_Except() {
		Option<Integer> oa = Option.none();
		
		assertThrows(RuntimeException.class,
				() -> 
			assertEquals(Integer.valueOf(0), oa.getOrElse(() -> throwANastyExcept())));
	}
	
	@Test
	public void getOrElseSomeTest_NoExcept() {
		Integer a = 3;
		Option<Integer> oa = Option.some(a);
		
		assertEquals(Integer.valueOf(3), oa.getOrElse(() -> throwANastyExcept()));
	}
	
	private static Integer throwANastyExcept() {
		throw new RuntimeException("BAAAM!!!");
	}
}
