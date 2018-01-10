package chapter4.ex4_9.solution;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import chapter4.ex4_3.solution.FoldList;

public class Fib {

	public static String fibListString(int i) {
		StringBuilder sb = new StringBuilder(fibListStringAux(i));
		return sb.length() > 1 ? sb.substring(0, sb.length() - 2) + ")" : "";
	}
	
	private static String fibListStringAux(int i) {
		return FoldList.foldLeft(fibList(i), "(", s -> n -> s + "" + n.toString() + ", ");
	}
	
	
	public static List<BigInteger> fibList(int i) {
		return i < 0 ? new ArrayList<>() : fibList_(BigInteger.ZERO, BigInteger.ONE, new ArrayList<>(), i).eval();
	}
	
	private static TailCall<List<BigInteger>> fibList_(BigInteger a, BigInteger b, List<BigInteger> currentList, int i) {
		return i == 0 ? TailCall.ret(prepend(currentList, a)) : TailCall.sus(() -> fibList_(b, a.add(b), prepend(currentList, a), i - 1));
	}
	
	private static <T> List<T> prepend(List<T> l, T e) {
		l.add(e);
		return l;
	}

}
