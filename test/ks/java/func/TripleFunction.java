package ks.java.func;

final class TripleFunction implements Function<Integer, Integer> {

	@Override
	public Integer apply(Integer arg) {
		return 3 * arg;
	}
}