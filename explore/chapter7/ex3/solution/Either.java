package chapter7.ex3.solution;

import ks.java.func.Function;

public abstract class Either<E, A> {

	public abstract <B> Either<E, B> map(Function<A, B> f);
	
	public abstract <B> Either<E, B> flatMap(Function<A, Either<E, B>> f);

	public abstract A getOrElse(Supplier<A> s);
	
	public Either<E, A> orElse(Supplier<Either<E, A>> s) {
		return map(x -> this).getOrElse(s);
	}
	
	private static class Left<E, A> extends Either<E, A> {

		private final E value;

		private Left(E value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("Left(%s)", value);
		}

		@Override
		public <B> Either<E, B> map(Function<A, B> f) {
			return new Left<>(value);
		}

		@Override
		public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
			return new Left<>(value);
		}

		@Override
		public A getOrElse(Supplier<A> s) {
			return s.get();
		}

	}

	private static class Right<E, A> extends Either<E, A> {

		private final A value;

		private Right(A value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("Right(%s)", value);
		}

		@Override
		public <B> Either<E, B> map(Function<A, B> f) {
			return new Right<>(f.apply(value));
		}

		@Override
		public <B> Either<E, B> flatMap(Function<A, Either<E, B>> f) {
			return f.apply(value);
		}

		@Override
		public A getOrElse(Supplier<A> s) {
			return value;
		}

	}

	public static <E, A> Either<E, A> left(E value) {
		return new Left<>(value);
	}

	public static <E, A> Either<E, A> right(A value) {
		return new Right<>(value);
	}

}
