package chapter3.ex3_4.solution;

import java.util.Collections;
import java.util.List;

public class HeadTailList {

	public static <T> T head(List<T> list) {
		return list.isEmpty() ? null : list.get(0);
	}
	
	public static <T> List<T> tail(List<T> list) {
		return ( list.size() <= 1 ? Collections.<T>emptyList() : list.subList(1, list.size()) );
	}
	
}
