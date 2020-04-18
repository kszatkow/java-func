package chapter6.ex6_11.solution;

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
	
	// in the end the reverseSequence operation is quite similar to sequence, just foldLeft is used instead of foldRight
	// I'm leaving short variable names, see more verbose version in sequence method
	// I'm actually wondering which one is more readable
	// By the way, there's some thought to it:
	// * ol - optional list
	// * oe - optional elem
	// * l - list
	// * e - elem
	public static <A> Option<List<A>> reverseSequence(List<Option<A>> list) {
		return list.isEmpty() ? 
				none() :
				list.foldLeft(Option.some(List.list()), 
						ol -> oe -> ol.flatMap(
								l ->  oe.map(
											e -> l.cons(e)
										)
						) 
				);
	}

	public static <A> Option<List<A>> sequence(List<Option<A>> list) {
	return list.isEmpty() ? 
			none() :
			list.foldRight(Option.some(List.list()), 
							currentElemOption -> alreadyProcessedListOption -> alreadyProcessedListOption.flatMap(
									alreadyProcessedList -> currentElemOption.map(currentElem -> alreadyProcessedList.cons(currentElem))
							)
			);
			
			// types of variables used above
			// currentElemeOption : Option<A>
			// alreadyProcessedListOption : Option<List<A>>
			// alreadyProcessedList : List<A>
			// currentElem : A
			
			// the return type of foldRight is Option<List<A>>
			
			// the list is build from the very end - the last list element is processed first
			// so the first thing to do is to map the current accumulator (which hold the list of  already processed elements in Option)
			// to a new list with currently processed element as new head of the list and the accumulator as tail
			// the new list is wrapped in Option
			
			// if the accumulator is none, calling flatMap is just gonna return NONE
			// if the currently processed element is NONE, calling map is going to return NONE
			// if none of the above hold, we just need to prepend the currently processed element to the accumulator by using cons method
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
