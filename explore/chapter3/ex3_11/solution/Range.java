package chapter3.ex3_11.solution;

import java.util.ArrayList;
import java.util.List;

public class Range {

	public static List<Integer> range(int beg, int end) {
		List<Integer> list = new ArrayList<>();
		for (int i = beg; i < end; ++i) {
			list.add(i);
		}
		return list;
	}
}
