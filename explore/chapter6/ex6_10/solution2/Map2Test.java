package chapter6.ex6_10.solution2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ks.java.func.Function;


public class Map2Test {

	private static Function<Integer, Function<Integer, Integer>> addInts = 
			x -> y -> x + y;
	
	@Test
	public void map2BothNoneTest() {
		Option<Integer> none1 = Option.none();
		Option<Integer> none2 = Option.none();
		assertEquals(Option.none(), Option.map2(none1, none2, addInts));
	}
	
	@Test
	public void map2FirstNoneTest() {
		Option<Integer> none1 = Option.none();
		Option<Integer> some2 = Option.some(4);
		assertEquals(Option.none(), Option.map2(none1, some2, addInts));
	}
	
	@Test
	public void map2SecondNoneTest() {
		Option<Integer> some1 = Option.some(3);
		Option<Integer> none2 = Option.none();
		assertEquals(Option.none(), Option.map2(some1, none2, addInts));
	}
	
	@Test
	public void map2BothSomeTest() {
		Option<Integer> some1 = Option.some(3);
		Option<Integer> some2 = Option.some(4);
		assertEquals(Integer.valueOf(7), 
				Option.map2(some1, some2, addInts).getOrElse(() -> 0));
	}
	
}
