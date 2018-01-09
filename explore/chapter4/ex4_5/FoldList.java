package chapter4.ex4_5;

import java.util.ArrayList;
import java.util.List;

import chapter3.ex3_4.solution.HeadTailList;
import ks.java.func.Function;

public class FoldList { 
	
	public static <T, U> U foldRight(List<T> list, U accum, Function<T, Function<U, U>> f) {
		return foldLeft_(reverse(list), accum, x -> y -> f.apply(y).apply(x)).eval();
	}
	
	private static <T, U> TailCall<U> foldLeft_(List<T> list, U accum, Function<U, Function<T, U>> g) {
		return list.isEmpty() 
				? TailCall.ret(accum) 
				: TailCall.sus(() -> foldLeft_(HeadTailList.tail(list), g.apply(accum).apply(HeadTailList.head(list)), g));
	}
	
	private static <T> List<T> reverse(List<T> list) {
		return foldLeft_(list, (List<T>) new ArrayList<T>(), l -> e -> prepend(e, l)).eval();
	}
	
	private static <T> List<T> prepend(T elem, List<T> list) {
		list.add(0, elem);
		return list;
	}
	
}
 