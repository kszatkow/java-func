package chapter3.ex3_2.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaseTest {
	
	private static int x;
	
	private static Case.DefaultCase<String> defaultCase;
	
	private static Case<String> cases[];
	
	@SuppressWarnings("unchecked")
	@BeforeAll
	public static void setUpClass() {
		Case<String> caseFizzBuzz = Case.mcase( () -> x % 3 == 0 && x % 5 == 0, () -> new Result<String>("FizzBuzz") );
		Case<String> caseFizz = Case.mcase( () ->  x % 3 == 0, () -> new Result<String>("Fizz") );
		Case<String> caseBuzz = Case.mcase( () ->  x % 5 == 0, () -> new Result<String>("Buzz") );
		cases = (Case<String>[]) new Case[] { caseFizzBuzz, caseFizz, caseBuzz };
		defaultCase = Case.mcase( () -> new Result<String>(String.valueOf(x)) );
	}
	
	@BeforeEach
	public void setUp() {
		x = 0;
	}
	
	@Test
	public void testFizzCase() {
		x = 3;
		
		Result<String> result = Case.match(defaultCase, cases);
		
		assertEquals("Fizz", result.get());
	}
	
	@Test
	public void testBuzzCase() {
		x = 5;
		
		Result<String> result = Case.match(defaultCase, cases);
		
		assertEquals("Buzz", result.get());
	}
	
	@Test
	public void testFizzBuzzCase() {
		x = 15;
		
		Result<String> result = Case.match(defaultCase, cases);
		
		assertEquals("FizzBuzz", result.get());
	}
	
	@Test
	public void testDefaultCase() {
		x = 2;
		
		Result<String> result = Case.match(defaultCase, cases);
		
		assertEquals("2", result.get());
	}
	
}
