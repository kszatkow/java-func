package chapter7.ex4.solution;

import ks.java.func.Function;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Result<V> implements Serializable {

  private Result() {
  }

  public abstract V getOrElse(final V defaultValue);

  public abstract V getOrElse(final Supplier<V> defaultValue);

  public abstract <U> Result<U> map(Function<V, U> f);

  public abstract <U> Result<U> flatMap(Function<V, Result<U>> f);

  private static class Failure<V> extends Result<V> {

    private final RuntimeException exception;

    private Failure(String message) {
      super();
      this.exception = new IllegalStateException(message);
    }

    private Failure(RuntimeException e) {
      super();
      this.exception = e;
    }

    private Failure(Exception e) {
      super();
      this.exception = new IllegalStateException(e.getMessage(), e);
    }

    @Override
    public String toString() {
      return String.format("Failure(%s)", exception.getMessage());
    }

	@Override
	public V getOrElse(V defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getOrElse(Supplier<V> defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Result<U> map(Function<V, U> f) {
		return failure(exception);
	}

	@Override
	public <U> Result<U> flatMap(Function<V, Result<U>> f) {
		// TODO Auto-generated method stub
		return null;
	}

  }

  private static class Success<V> extends Result<V> {

    private final V value;

    private Success(V value) {
      super();
      this.value = value;
    }

    @Override
    public String toString() {
      return String.format("Success(%s)", value.toString());
    }

	@Override
	public V getOrElse(V defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getOrElse(Supplier<V> defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Result<U> map(Function<V, U> f) {
		return success(f.apply(value));
	}

	@Override
	public <U> Result<U> flatMap(Function<V, Result<U>> f) {
		// TODO Auto-generated method stub
		return null;
	}

  }

  public static <V> Result<V> failure(String message) {
    return new Failure<>(message);
  }

  public static <V> Result<V> failure(Exception e) {
    return new Failure<>(e);
  }

  public static <V> Result<V> failure(RuntimeException e) {
    return new Failure<>(e);
  }

  public static <V> Result<V> success(V value) {
    return new Success<>(value);
  }
}
