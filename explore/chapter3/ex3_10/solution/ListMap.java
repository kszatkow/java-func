package chapter3.ex3_10.solution;

import java.util.ArrayList;
import java.util.List;

import chapter3.ex3_6.solution.FoldList;
import ks.java.func.Function;

public class ListMap {

	public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		return FoldList.foldLeft(list, new ArrayList<U>(), l -> e -> append(l, f.apply(e)));
	}
	
	private static <T> List<T> append(List<T> list, T elem) {
		list.add(elem);
		return list;
	}
	
	public static <T, U> List<U> map2(List<T> list, Function<T, U> f) {
		return chapter3.ex3_8.solution.FoldList.foldRight(list, (List<U>) new ArrayList<U>(), e -> l -> prepend(l, f.apply(e)));
	}

	private static <T> List<T> prepend(List<T> list, T elem) {
		list.add(0, elem);
		return list;
	}
	
}
