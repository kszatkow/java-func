package chapter3.ex3_14.solution;

import java.util.ArrayList;
import java.util.List;

public class Range {

	public static List<Integer> range(int beg, int end) {
		return beg < end ? prepend(beg, range(beg + 1, end)) : new ArrayList<Integer>();
	}
	
	private static <T> List<T> prepend(T elem, List<T> list) {
		list.add(0, elem);
		return list;
	}
}
