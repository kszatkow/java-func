package chapter3.ex3_8.solution;

import java.util.List;

import chapter3.ex3_4.solution.HeadTailList;
import ks.java.func.Function;

public class FoldList { 
	public static <T, U> U foldRight(List<T> list, U accum, Function<T, Function<U, U>> f) {
		return list.isEmpty() 
				? accum 
				: f.apply(HeadTailList.head(list)).apply(foldRight(HeadTailList.tail(list), accum, f));
	}
}
 