package ks.java.func;

public final class FunctionUtils {

	public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
		return x -> f.apply(g.apply(x));
	}
	
	public static <S, T, U> Function<U, T> genericCompose(Function<S, T> f, Function<U, S> g) {
		return x -> f.apply(g.apply(x));
	}
}
