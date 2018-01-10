package chapter4.ex4_2.solution;

import java.math.BigInteger;

public class Fib {

	public static BigInteger fibBigInt(int i) {
		return i < 0 ? BigInteger.valueOf(-1) : fibBigInt_(BigInteger.ZERO, BigInteger.ONE, i).eval();
	}
	
	private static TailCall<BigInteger> fibBigInt_(BigInteger a, BigInteger b, int i) {
		return i == 0 ? TailCall.ret(a) : TailCall.sus(() -> fibBigInt_(b, a.add(b), i - 1));
	}

}
