package chapter3.ex3_9.solution;

import java.util.ArrayList;
import java.util.List;

import chapter3.ex3_8.solution.FoldList;

public class ReverseList {

	public static <T> List<T> reverse(List<T> list) {
		return FoldList.foldRight(list, (List<T>) new ArrayList<T>(), e -> l -> append(l, e));
	}
	
	private static <T> List<T> append(List<T> list, T elem) {
		list.add(elem);
		return list;
	}
	
}
