package chapter3.ex3_2.solution;

public class Result<T> {

	private T t;
	
	public Result(T t) {
		this.t = t;
	}
	
	public T get() {
		return t;
	}
	
}
