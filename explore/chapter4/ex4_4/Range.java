package chapter4.ex4_4;

import java.util.ArrayList;
import java.util.List;

public class Range {

	public static List<Integer> range(int beg, int end) {
		return range_(new ArrayList<Integer>(), beg, end).eval();
	}
	
	private static TailCall<List<Integer>> range_(List<Integer> accum, int cur, int end) {
		return cur < end ? TailCall.sus(() -> range_(append(accum, cur), cur + 1, end)) : TailCall.ret(accum);
	}
	
	private static <T> List<T> append(List<T> list, T elem) {
		list.add(elem);
		return list;
	}
}
