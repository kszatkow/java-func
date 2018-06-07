package chapter6.ex6_2.solution;

public abstract class Option<A> {

	@SuppressWarnings("rawtypes")
	private static final None NONE = new None();
	
	public static <A> Option<A> some(A a) {
		return new Some<>(a);
	}
	
	@SuppressWarnings("unchecked")
	public static <A> Option<A> none() {
		return NONE;
	}
	
	public abstract A getOrElse(Supplier<A> a);
	
	
	private final static class Some<A> extends Option<A> {
	
		private final A value;
		
		private Some(A a) {
			value = a;
		}
		
		public A getOrElse(Supplier<A> a) {
			return value;
		}
		
	}
	
	private final static class None<A> extends Option<A> {
	
		private None() {}
		
		public A getOrElse(Supplier<A> a) {
			return a.get();
		}
	}
}	
