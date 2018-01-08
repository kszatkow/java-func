package chapter3.ex3_12.solution;

import java.util.ArrayList;
import java.util.List;

import ks.java.func.Function;

public class GenericRange {

	public static <T> List<T> unfold(T seed, Function<T, T> gen, Function<T, Boolean> continueCond) {
		List<T> result = new ArrayList<>();
		T cur = seed;
		while (continueCond.apply(cur)) {
			result.add(cur);
			cur = gen.apply(cur);
		}
		return result;
	}
}
