package chapter7.ex4.solution;

import static org.junit.Assert.*;

import org.junit.Test;

import ks.java.func.Function;

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
	public void testMapFailure() {
		String inMsg = "Error!!!";
		Exception exc = new TestException(inMsg);
		Result<Integer> result = Result.failure(exc);
		
		Result<Double> mappedResult = result.map(x -> Double.valueOf(x));
		
		assertEquals(String.format("Failure(%s)", inMsg), mappedResult.toString());
	}

	@Test
	public void testMapSuccess() {
		Integer i = Integer.valueOf(1);
		Result<Integer> result = Result.success(i);
		
		Result<Double> mappedResult = result.map(x -> Double.valueOf(x));
		
		assertEquals("Success(1.0)", mappedResult.toString());
	}

	@Test
	public void testMapSuccessExcRaised() {
		Result<Integer> result = Result.success(1);
		final String inMsg = "Exc!!!";
		
		Result<Double> mappedResult = result.map(new Function<Integer, Double>() {
			@Override
			public Double apply(Integer arg) {
				throw new RuntimeException(inMsg);
			}
		});
				
		assertEquals(String.format("Failure(%s)", inMsg), mappedResult.toString());
	}
	
	@Test
	public void testFlatMapFailure() {
		String inMsg = "Error!!!";
		Exception exc = new TestException(inMsg);
		Result<Integer> result = Result.failure(exc);
		
		Result<Double>  mappedResult = result.flatMap(x -> Result.success(Double.valueOf(x)));
		
		assertEquals(String.format("Failure(%s)", inMsg), mappedResult.toString());
	}

	@Test
	public void testFlatMapSuccess() {
		Integer i = Integer.valueOf(1);
		Result<Integer> result = Result.success(i);
		
		Result<Double>  mappedResult = result.flatMap(x -> Result.success(Double.valueOf(x)));
		
		assertEquals("Success(1.0)", mappedResult.toString());
	}
	
	@Test
	public void testFlatMapSuccessExcRaised() {
		Result<Integer> result = Result.success(1);
		final String inMsg = "Exc2!!!";
		
		Result<Double> mappedResult = result.flatMap(new Function<Integer, Result<Double>>() {
			@Override
			public Result<Double> apply(Integer arg) {
				throw new RuntimeException(inMsg);
			}
		});
				
		assertEquals(String.format("Failure(%s)", inMsg), mappedResult.toString());
	}
	
	
	@Test
	public void testGetOrElseSupFailure() {
		Result<Integer> result = Result.failure(new Exception());
		final Integer expectedVal = 5;
		
		Integer retrievedVal = result.getOrElse(() -> expectedVal);
		
		assertEquals(expectedVal, retrievedVal);
	}
	
	@Test
	public void testGetOrElseSupSuccess() {
		Integer expectedVal = 1;
		Result<Integer> e = Result.success(expectedVal);
		
		Integer retrievedVal = e.getOrElse(() -> 7);
		
		assertEquals(expectedVal, retrievedVal);
	}
	
	@Test
	public void testGetOrElseFailure() {
		Result<Integer> result = Result.failure(new Exception());
		final Integer expectedVal = 5;
		
		Integer retrievedVal = result.getOrElse(expectedVal);
		
		assertEquals(expectedVal, retrievedVal);
	}
	
	@Test
	public void testGetOrElseSuccess() {
		Integer expectedVal = 1;
		Result<Integer> e = Result.success(expectedVal);
		
		Integer retrievedVal = e.getOrElse(7);
		
		assertEquals(expectedVal, retrievedVal);
	}
	
	@Test
	public void testOrElseFailure() {
		Result<Integer> result = Result.failure(new Exception());
		final Integer expectedVal = 13;
		
		Result<Integer> retrievedVal = result.orElse(() -> Result.success(expectedVal));
		
		assertEquals(String.format("Success(%d)", expectedVal), retrievedVal.toString());
	}
	
	@Test
	public void testOrElseSuccess() {
		final Integer expectedVal = 15;
		Result<Integer> result = Result.success(expectedVal);
		
		Result<Integer> retrievedVal = result.orElse(() -> Result.success(106));
		
		assertEquals(String.format("Success(%d)", expectedVal), retrievedVal.toString());
	}
	
}
