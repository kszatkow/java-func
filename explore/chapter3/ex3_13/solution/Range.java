package chapter3.ex3_13.solution;

import java.util.List;

import chapter3.ex3_12.solution.GenericRange;

public class Range {

	public static List<Integer> range(int beg, int end) {
		return GenericRange.unfold(beg, x -> x + 1, x -> x < end);
	}
}
