package chapter6.ex6_11.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ReverseSequenceTest {

	@Test
	public void sequenceTestEmptyList() {
		List<Option<Double>> list = List.list();
		
		assertEquals(Option.none(), Option.reverseSequence(list));
	}
	
	@Test
	public void sequenceSingleElemList() {
		List<Option<Double>> list = List.list(
				Option.some(1.0));
		
		assertEquals("[1.0, NIL]", 
				Option.reverseSequence(list).getOrElse(() -> List.list()).toString());
	}
	
	@Test
	public void sequenceNoneEmptySomeListTest() {
		List<Option<Double>> list = List.list(
				Option.some(1.0),
				Option.some(2.0),
				Option.some(3.0),
				Option.some(4.0),
				Option.some(5.0));
		
		assertEquals("[5.0, 4.0, 3.0, 2.0, 1.0, NIL]", 
				Option.reverseSequence(list).getOrElse(() -> List.list()).toString());
	}
	
	@Test
	public void sequenceNoneEmptySomeNoneListTest() {
		List<Option<Double>> list = List.list(
				Option.some(1.0),
				Option.some(2.0),
				Option.none(),
				Option.some(4.0),
				Option.some(5.0));
		
		assertEquals(Option.none(), Option.reverseSequence(list));
	}
	
	@Test
	public void sequenceNoneFirstNoneListTest() {
		List<Option<Double>> list = List.list(
				Option.none(),
				Option.some(1.0),
				Option.some(2.0),
				Option.some(4.0),
				Option.some(5.0));
		
		assertEquals(Option.none(), Option.reverseSequence(list));
	}
	
	@Test
	public void sequenceNoneLastNoneListTest() {
		List<Option<Double>> list = List.list(
				Option.some(1.0),
				Option.some(2.0),
				Option.some(4.0),
				Option.some(5.0),
				Option.none());
		
		assertEquals(Option.none(), Option.reverseSequence(list));
	}
}
