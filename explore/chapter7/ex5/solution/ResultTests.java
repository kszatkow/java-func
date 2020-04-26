package chapter7.ex5.solution;

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
	public void testFilterFailure() {
		String inMsg = "Not found!!!";
		TestException exc = new TestException(inMsg);
		Result<Integer> result = Result.failure(exc);
		
		Result<Integer> foundResult = result.filter(x -> true);
		
		assertEquals(String.format("Failure(%s)", inMsg), foundResult.toString());
	}

	@Test
	public void testFilterSuccessFound() {
		Integer i = Integer.valueOf(1);
		Result<Integer> result = Result.success(i);
		
		Result<Integer> foundResult = result.filter(x -> x == 1);
		
		assertEquals("Success(1)", foundResult.toString());
	}

	@Test
	public void testFilterSuccessNotFound() {
		Integer i = Integer.valueOf(1);
		Result<Integer> result = Result.success(i);
		
		Result<Integer> foundResult = result.filter(x -> false);
		
		assertEquals(String.format("Failure(No match!)"), foundResult.toString());
	}

}
