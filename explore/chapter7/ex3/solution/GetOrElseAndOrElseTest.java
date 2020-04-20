package chapter7.ex3.solution;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetOrElseAndOrElseTest {

	@Test
	public void testGetOrElseLeft() {
		Either<Exception, Integer> e = Either.left(new Exception());
		final Integer expectedVal = 5;
		
		Integer retrievedVal = e.getOrElse(() -> expectedVal);
		
		assertEquals(expectedVal, retrievedVal);
	}

	@Test
	public void testGetOrElseRight() {
		Integer expectedVal = Integer.valueOf(1);
		Either<Exception, Integer> e = Either.right(expectedVal);
		
		Integer retrievedVal = e.getOrElse(() -> 7);
		
		assertEquals(expectedVal, retrievedVal);
	}
	
	@Test
	public void testOrElseLeft() {
		Either<Exception, Integer> e = Either.left(new Exception());
		final Integer expectedVal = 5;
		
		Either<Exception, Integer> retrieved = e.orElse(() -> Either.right(expectedVal));
		
		assertEquals(String.format("Right(%d)", expectedVal), retrieved.toString());
	}

	@Test
	public void testOrElseRight() {
		Integer expectedVal =  Integer.valueOf(1);
		Either<Exception, Integer> e = Either.right(expectedVal);
		
		Either<Exception, Integer> retrieved = e.orElse(() -> Either.right(7));
		
		assertEquals(String.format("Right(%d)", expectedVal), retrieved.toString());
	}
}
