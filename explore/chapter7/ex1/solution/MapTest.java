package chapter7.ex1.solution;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@SuppressWarnings("serial")
	private static final class TestException extends Exception {
		
		private final String msg;
		
		public TestException(String inMsg) {
			msg = inMsg;
		}

		@Override
		public String toString() {
			return msg;
		}
	}

	@Test
	public void testMapLeft() {
		String inMsg = "Error!!!";
		Exception exc = new TestException(inMsg);
		Either<Exception, Integer> e = Either.left(exc);
		
		Either<Exception, Double> mappedE = e.map(x -> Double.valueOf(x));
		
		assertEquals(String.format("Left(%s)", inMsg), mappedE.toString());
	}

	@Test
	public void testMapRight() {
		Integer i = new Integer(1);
		Either<Exception, Integer> e = Either.right(i);
		
		Either<Exception, Double> mappedE = e.map(x -> Double.valueOf(x));
		
		assertEquals("Right(1.0)", mappedE.toString());
	}
}
