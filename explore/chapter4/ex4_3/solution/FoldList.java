package chapter4.ex4_3.solution;

import java.util.List;

import chapter3.ex3_4.solution.HeadTailList;
import ks.java.func.Function;

public class FoldList { 
	public static <T, U> U foldLeft(List<T> list, U accum, Function<U, Function<T, U>> f) {
		return foldLeft_(list, accum, f).eval();
	}
	
	private static <T, U> TailCall<U> foldLeft_(List<T> list, U accum, Function<U, Function<T, U>> f) {
		return list.isEmpty() 
				? TailCall.ret(accum) 
				: TailCall.sus(() -> foldLeft_(HeadTailList.tail(list), f.apply(accum).apply(HeadTailList.head(list)), f));
	}
}
 