package chapter3.ex3_5.solution;

import java.util.List;

import chapter3.ex3_4.solution.HeadTailList;
import ks.java.func.Function;

public class FoldList { 
	public static <T> T fold(List<T> list, T accum, Function<T, Function<T, T>> f) {
		return list.isEmpty() 
				? accum 
				: fold(HeadTailList.tail(list), f.apply(accum).apply(HeadTailList.head(list)), f);
	}
}
 