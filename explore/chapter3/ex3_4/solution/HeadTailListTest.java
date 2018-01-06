package chapter3.ex3_4.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HeadTailListTest {

	@Test
	public void testHeadEmptyList() {
		ArrayList<Integer> list = new ArrayList<>();
		
		Integer head = HeadTailList.head(list);
		
		assertNull(head);
	}
	
	@Test
	public void testHeadNonEmptyList() {
		List<Integer> list = Arrays.asList( 1, 2, 3 );
		
		int head = HeadTailList.head(list);
		
		assertEquals(1, head);
	}
	
	@Test
	public void testTailEmptyList() {
		ArrayList<Integer> list = new ArrayList<>();
		
		List<Integer> tail = HeadTailList.tail(list);
		
		assertTrue(tail.isEmpty());
	}
	
	@Test
	public void testTailSingletonList() {
		List<Integer> list = Collections.singletonList(1);
		
		List<Integer> tail = HeadTailList.tail(list);
		
		assertTrue(tail.isEmpty());
	}
	
	@Test
	public void testTailLongerList() {
		List<Integer> list = Arrays.asList( 1, 2, 3 );
		
		List<Integer> tail = HeadTailList.tail(list);
		
		assertEquals(Arrays.asList( 2, 3 ), tail);
	}
	
}
