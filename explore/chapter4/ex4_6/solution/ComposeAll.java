package chapter4.ex4_6.solution;

import java.util.Arrays;
import java.util.List;

import chapter4.ex4_5.solution.FoldList;
import ks.java.func.Function;

public class ComposeAll {

	@SafeVarargs
	public static <T> Function<T, T> composeAll(Function<T, T>... funcs) {
		return FoldList.<Function<T, T>, Function<T, T>>foldRight(Arrays.asList(funcs), f -> f, f -> g -> f.compose(g));
	}
	
	public static <T> Function<T, T> composeAll(List<Function<T, T>> funcs) {
		return FoldList.<Function<T, T>, Function<T, T>>foldRight(funcs, f -> f, f -> g -> f.compose(g));
	}
}
