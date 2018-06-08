package chapter6.ex6_10.triple.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ks.java.func.Function;


public class Map3Test {

	private static Function<Integer, Function<Integer, Function<Integer, Integer>>> addInts = 
			x -> y -> z -> x + y + z;
	
	@Test
	public void map3AllNoneTest() {
		Option<Integer> none1 = Option.none();
		Option<Integer> none2 = Option.none();
		Option<Integer> none3 = Option.none();
		assertEquals(Option.none(), Option.map3(none1, none2, none3, addInts));
	}
	
	@Test
	public void map3FirstNoneTest() {
		Option<Integer> none1 = Option.none();
		Option<Integer> some2 = Option.some(4);
		Option<Integer> some3 = Option.some(3);
		assertEquals(Option.none(), Option.map3(none1, some2, some3, addInts));
	}
	
	@Test
	public void map3SecondNoneTest() {
		Option<Integer> some1 = Option.some(3);
		Option<Integer> none2 = Option.none();
		Option<Integer> none3 = Option.none();
		assertEquals(Option.none(), Option.map3(some1, none2, none3, addInts));
	}
	
	@Test
	public void map3ThirsNoneTest() {
		Option<Integer> none1 = Option.none();
		Option<Integer> none2 = Option.none();
		Option<Integer> some3 = Option.some(3);
		assertEquals(Option.none(), Option.map3(none1, none2, some3, addInts));
	}
	
	@Test
	public void map3AllSomeTest() {
		Option<Integer> some1 = Option.some(3);
		Option<Integer> some2 = Option.some(4);
		Option<Integer> some3 = Option.some(5);
		assertEquals(Integer.valueOf(12), 
				Option.map3(some1, some2, some3, addInts).getOrElse(() -> 0));
	}
	
}
