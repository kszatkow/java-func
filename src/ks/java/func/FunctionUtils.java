package ks.java.func;

public final class FunctionUtils {

	public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
		return x -> f.apply(g.apply(x));
	}
}
