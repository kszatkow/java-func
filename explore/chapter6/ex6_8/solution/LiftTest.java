package chapter6.ex6_8.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ks.java.func.Function;


public class LiftTest {

	@Test
	public void liftTestSome() {
		Function<Option<Double>, Option<Double>> liftedMax = Option.lift(Math::abs);
		Double arg = -3.34;
		
		assertEquals(
				Double.valueOf(Math.abs(arg)), 
				liftedMax.apply(Option.some(arg)).getOrElse(() -> 0.0));
	}
	
	@Test
	public void liftTestNone() {
		Function<Option<Double>, Option<Double>> liftedMax = Option.lift(Math::abs);
		
		assertEquals(
				Option.none(), 
				liftedMax.apply(Option.none()));
	}
}
