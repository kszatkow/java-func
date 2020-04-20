package chapter7.ex4.solution;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTests {

	@SuppressWarnings("serial")
	private static final class TestException extends Exception {
		
		private final String msg;
		
		public TestException(String inMsg) {
			msg = inMsg;
		}

		@Override
		public String getMessage() {
			return msg;
		}
	}

	@Test
	public void testMapLeft() {
		String inMsg = "Error!!!";
		Exception exc = new TestException(inMsg);
		Result<Integer> result = Result.failure(exc);
		
		Result<Double> mappedResult = result.map(x -> Double.valueOf(x));
		
		assertEquals(String.format("Failure(%s)", inMsg), mappedResult.toString());
	}

	@Test
	public void testMapRight() {
		Integer i = new Integer(1);
		Result<Integer> result = Result.success(i);
		
		Result<Double> mappedResult = result.map(x -> Double.valueOf(x));
		
		assertEquals("Success(1.0)", mappedResult.toString());
	}
	
//	@Test
//	public void testFlatMapLeft() {
//		String inMsg = "Error!!!";
//		Exception exc = new TestException(inMsg);
//		Either<Exception, Integer> e = Either.left(exc);
//		
//		Either<Exception, Double> mappedE = e.flatMap(x -> Either.right(Double.valueOf(x)));
//		
//		assertEquals(String.format("Left(%s)", inMsg), mappedE.toString());
//	}
//
//	@Test
//	public void testFlatMapRight() {
//		Integer i = new Integer(1);
//		Either<Exception, Integer> e = Either.right(i);
//		
//		Either<Exception, Double> mappedE = e.flatMap(x -> Either.right(Double.valueOf(x)));
//		
//		assertEquals("Right(1.0)", mappedE.toString());
//	}
//	
//	@Test
//	public void testGetOrElseLeft() {
//		Either<Exception, Integer> e = Either.left(new Exception());
//		final Integer expectedVal = 5;
//		
//		Integer retrievedVal = e.getOrElse(() -> expectedVal);
//		
//		assertEquals(expectedVal, retrievedVal);
//	}
//
//	@Test
//	public void testGetOrElseRight() {
//		Integer expectedVal = new Integer(1);
//		Either<Exception, Integer> e = Either.right(expectedVal);
//		
//		Integer retrievedVal = e.getOrElse(() -> 7);
//		
//		assertEquals(expectedVal, retrievedVal);
//	}
//	
//	@Test
//	public void testOrElseLeft() {
//		Either<Exception, Integer> e = Either.left(new Exception());
//		final Integer expectedVal = 5;
//		
//		Either<Exception, Integer> retrieved = e.orElse(() -> Either.right(expectedVal));
//		
//		assertEquals(String.format("Right(%d)", expectedVal), retrieved.toString());
//	}
//
//	@Test
//	public void testOrElseRight() {
//		Integer expectedVal = new Integer(1);
//		Either<Exception, Integer> e = Either.right(expectedVal);
//		
//		Either<Exception, Integer> retrieved = e.orElse(() -> Either.right(7));
//		
//		assertEquals(String.format("Right(%d)", expectedVal), retrieved.toString());
//	}
}
