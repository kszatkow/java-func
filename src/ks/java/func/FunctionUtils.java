package ks.java.func;

public final class FunctionUtils {

	public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
		return x -> f.apply(g.apply(x));
	}
	
	public static <S, T, U> Function<U, T> genericCompose(Function<S, T> f, Function<U, S> g) {
		return x -> f.apply(g.apply(x));
	}
	
	public static <T, U, V> Function<
								Function<U, V>,
								Function<
									Function<T, U>,
									Function<T, V>
								>
	> higherCompose() {
		// a simple implementation looks like that
		return x -> y -> z -> x.apply(y.apply(z));
		// but you can make it more verbose
//		return (Function<U, V> x) -> (Function<T, U> y) -> (T z) -> x.apply(y.apply(z));
	}
}
