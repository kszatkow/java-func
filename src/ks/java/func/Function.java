package ks.java.func;

public interface Function<T, U> {
	
	U apply(T arg);
	
	default <V> Function<V, U> compose(Function<V, T> f) {
		return arg -> apply(f.apply(arg));
	}
	
	default <V> Function<T, V> andThen(Function<U, V> f) {
		return arg -> f.apply(apply(arg));
	}
	

	static <T> Function<T, T> identity() {
		return arg -> arg;
	}

	static <T, U, V> Function<V, U> compose(Function<T, U> f, Function<V, T> g) {
		return arg -> f.apply(g.apply(arg));
	}

	static <T, U, V> Function<T, V> andThen(Function<T, U> f, Function<U, V> g) {
		return arg -> g.apply(f.apply(arg));
	}

	static <T, U, V> Function<Function<T, U>,
							  Function<Function<U, V>,
							  		   Function<T, V>>> compose() {
		return f -> g -> g.compose(f);
	}
	
	static <T, U, V> Function<Function<T, U>,
							  Function<Function<V, T>,
							  		   Function<V, U>>> andThen() {
		return f -> g -> g.andThen(f);
	}

	static <T, U, V> Function<Function<U, V>,
							  Function<Function<T, U>,
							  		   Function<T, V>>> higherCompose() {
		// a simple implementation looks like that
		return f -> g -> arg -> f.apply(g.apply(arg));
		// but you can make it more verbose
		// return (Function<U, V> x) -> (Function<T, U> y) -> (T z) -> x.apply(y.apply(z));
	}
	
	static <T, U, V> Function<Function<T, U>,
							  Function<Function<U, V>,
							  		   Function<T, V>>> higherAndThen() {
		return f -> g -> arg -> g.apply(f.apply(arg));
	}
	
}
