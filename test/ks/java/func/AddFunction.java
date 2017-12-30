package ks.java.func;

public class AddFunction implements Function<Integer, Function<Integer, Integer>>{

	@Override
	public Function<Integer, Integer> apply(Integer x) {
		return y -> x + y;
	}
	
}
