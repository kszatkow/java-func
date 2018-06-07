package chapter6.ex6_7.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class VarianceTest {

	@Test
	public void varianceEmptyListTest() {
		List<Double> list = List.list();
		
		assertEquals(Option.none(), Variance.computeVariance(list));
	}
	
	@Test
	public void zeroVarianceListTest() {
		List<Double> list = List.list(2.0,2.0,2.0,2.0,2.0);
		
		assertEquals(Double.valueOf(0.0), 
				Variance.computeVariance(list).getOrElse(() -> 100.0));
	}
	
	@Test
	public void nonZeroVarianceListTest() {
		List<Double> list = List.list(1.0,2.0,3.0,4.0,5.0);
		
		assertEquals(Double.valueOf(2.0), 
				Variance.computeVariance(list).getOrElse(() -> 0.0));
	}
}
