package ks.java.func.auxiliary;

import ks.java.func.Function;

public final class TripleFunction implements Function<Integer, Integer> {

	@Override
	public Integer apply(Integer arg) {
		return 3 * arg;
	}
}