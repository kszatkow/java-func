package chapter4.ex4_7;

import java.util.Arrays;
import java.util.List;

import chapter4.ex4_3.FoldList;
import ks.java.func.Function;

public class ComposeAll {

	@SafeVarargs
	public static <T> Function<T, T> composeAll(Function<T, T>... funcs) {
		return composeAll(Arrays.asList(funcs));
	}
	
	public static <T> Function<T, T> composeAll(List<Function<T, T>> funcs) {
		return x -> FoldList.foldLeft(funcs, ComposedCall.<T>single(arg -> arg), accum -> f -> ComposedCall.composed(accum, f) ).eval(x);
	}
	
	public static <T> Function<T, T> composeAllFoldRight(List<Function<T, T>> funcs) {
		return x -> chapter4.ex4_5.FoldList.<Function<T, T>, ComposedCall<T>>foldRight(
				funcs, ComposedCall.single(arg -> arg), f -> accum -> ComposedCall.composed(accum, f)).eval(x);
	}

	
}
