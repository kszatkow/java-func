package chapter3.ex3_6.solution;

import java.util.List;

import chapter3.ex3_4.solution.HeadTailList;
import ks.java.func.Function;

public class FoldList { 
	public static <T, U> U foldLeft(List<T> list, U accum, Function<U, Function<T, U>> f) {
		return list.isEmpty() 
				? accum 
				: foldLeft(HeadTailList.tail(list), f.apply(accum).apply(HeadTailList.head(list)), f);
	}
}
 