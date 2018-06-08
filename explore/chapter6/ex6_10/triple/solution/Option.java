package chapter6.ex6_10.triple.solution;

import ks.java.func.Function;

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
	
	public static <A, B> Function<Option<A>, Option<B>> lift(Function<A, B> f) {
		return x -> x.map(f);
	}
	
	public static <A, B, C, D> Option<D> map3(Option<A> a, Option<B> b, Option<C> c, 
			Function<A, Function<B, Function<C, D>>> f) {
		return a.map(f).flatMap(g -> b.map(g).flatMap(h -> c.map(h))); 
	}
	
	public abstract A getOrElse(Supplier<A> a);
	public abstract <B> Option<B> map(Function<A, B> f);
	
	public <B> Option<B> flatMap(Function<A, Option<B>> f) {
		return map(f).getOrElse(Option::none);
	}

	public Option<A> orElse(Supplier<Option<A>> a) {
		return map(x -> this).getOrElse(a);
	}
	
	public Option<A> filter(Function<A, Boolean> pred) {
		return flatMap(x-> pred.apply(x) 
				? this 
				: none());
	}
	
	private final static class Some<A> extends Option<A> {
	
		private final A value;
		
		private Some(A a) {
			value = a;
		}
		
		@Override
		public A getOrElse(Supplier<A> a) {
			return value;
		}
	
		@Override
		public <B> Option<B> map(Function<A, B> f) {
			return some(f.apply(value));
		}

	}
	
	private final static class None<A> extends Option<A> {
	
		private None() {}
		
		@Override
		public A getOrElse(Supplier<A> a) {
			return a.get();
		}
		
		@Override
		public <B> Option<B> map(Function<A, B> f) {
			return none();
		}

	}

}	
