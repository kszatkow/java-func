package ks.java.func.auxiliary;

import ks.java.func.Function;

public final class SquareFunction implements Function<Integer, Integer> {
	@Override
	public Integer apply(Integer arg) {
		return arg * arg;
	}
}