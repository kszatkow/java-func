package chapter3.ex3_2.solution;

public class Case<T> {

	static class DefaultCase<T> extends Case<T> {
		protected DefaultCase(Supplier<Result<T>> value) {
			super( () -> true, value );
		}
	};
	
	public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
		return new Case<T>(condition, value);
	}
	
	public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value) {
		return new DefaultCase<>(value);
	}
	
	static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {
		for (Case<T> matcher : matchers) {
			if (matcher.condition()) {
				return matcher.result();
			}
		}
		
		return defaultCase.result();
	}

	private Supplier<Boolean> condition;

	private Supplier<Result<T>> value;
	
	protected Case(Supplier<Boolean> condition, Supplier<Result<T>> value) {
		this.condition = condition;
		this.value = value;
	}
	
	protected Result<T> result() {
		return value.get();
	}

	private boolean condition() {
		return condition.get();
	}
	
}
