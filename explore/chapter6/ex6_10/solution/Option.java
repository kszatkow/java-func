package chapter6.ex6_10.solution;

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
	
	public static <A, B, C> Option<C> map2(Option<A> a, Option<B> b, 
			Function<A, Function<B, C>> f) {
		// function application on option is replaced by mapping
		// first we need to apply f to a: a.map(f)
		// this leaves us with a instance of Option<Function<B, C>> which we call g, or in other word Option of f.apply(a) (none if a was none)
		// then we need to apply g to b, we do it by means of flat mapping Option<g> to get g itself and then apply it to b via map
		// bear in mind that map returns Option<B>, so by applying g:Function<B, C> to b:Option<B> via map, the return type is not C but Option<C> 
		// it is broken nicely in the commented line of code g_map_c ...
		return a.map(f).flatMap(g -> b.map(g)); 
//		return g_map_c( a_map_f(a, f), b );
	}
	
	private static <A, B, C> Option<Function<B, C>> a_map_f(Option<A> a, Function<A, Function<B, C>> f) {
		return a.map(f);
	}
	
	private static <B, C> Option<C> g_map_c(Option<Function<B, C>> g, Option<B> b) {
		return g.flatMap(f -> b.map(f));
		// f is of type map<A>: Function<B, C>
		// return type is of type map<B>: C
		// internal function in flat map is of type: Function<B, C> -> Option<C> and the return type of flat map is Option<C> 
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
