package chapter4.ex4_7.solution2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chapter4.ex4_3.solution.FoldList;
import ks.java.func.Function;

public class ComposeAll {

	@SafeVarargs
	public static <T> Function<T, T> composeAll(Function<T, T>... funcs) {
		return composeAll(Arrays.asList(funcs));
	}
	
	public static <T> Function<T, T> composeAll(List<Function<T, T>> funcs) {
		return x -> FoldList.<Function<T,T>, T>foldLeft(reverse(funcs), x, accum -> f -> f.apply(accum) );
	}
	
	@SafeVarargs
	public static <T> Function<T, T> composeAllFoldRight(Function<T, T>... funcs) {
		return composeAllFoldRight(Arrays.asList(funcs));
	}
	
	public static <T> Function<T, T> composeAllFoldRight(List<Function<T, T>> funcs) {
		return x -> chapter4.ex4_5.solution.FoldList.foldRight(funcs, x, f -> f::apply
																		// equivalent to f -> arg -> f.apply(arg)
		);			
	}

	private static <T> List<T> reverse(List<T> l) {
		Collections.reverse(l);
		return l;
	}
}
