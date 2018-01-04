package ks.java.func.explore;


import ks.java.func.Function;

public final class FunctionUtils {

	public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
		return x -> f.apply(g.apply(x));
	}
	
	public static <T, U, V> Function<U, V> partialA(T argA, Function<T, Function<U, V>> f) {
		return f.apply(argA);
	}

	public static <T, U, V> Function<T, V> partialB(U argB, Function<T, Function<U, V>> f) {
		return x -> f.apply(x).apply(argB);
	}
	
}
