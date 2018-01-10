package chapter4.ex4_1.solution;

import java.math.BigInteger;

public class Fib {

	public static int fib(int i) {
		return i < 0 ? -1 : fib_(0, 1, i);
	}
	
	private static int fib_(int a, int b, int i) {
		return i == 0 ? a : fib_(b, a + b, i - 1);
	}

	public static BigInteger fibBigInt(int i) {
		return i < 0 ? BigInteger.valueOf(-1) : fibBigInt_(BigInteger.ZERO, BigInteger.ONE, i);
	}
	
	private static BigInteger fibBigInt_(BigInteger a, BigInteger b, int i) {
		return i == 0 ? a : fibBigInt_(b, a.add(b), i - 1);
	}

}
