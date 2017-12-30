package ks.java.func;

final class SquareFunction implements Function<Integer, Integer> {
	@Override
	public Integer apply(Integer arg) {
		return arg * arg;
	}
}