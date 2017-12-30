package ks.java.func;

public final class FunctionUtils {

	public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
		return new Function<Integer, Integer>() {
			
			@Override
			public Integer apply(Integer arg) {
				return f.apply(g.apply(arg));
			}
		};
	}
}
