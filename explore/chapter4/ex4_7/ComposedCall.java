package chapter4.ex4_7;

import ks.java.func.Function;
import ks.java.func.Tuple;

public abstract class ComposedCall<T> {

	  public abstract Tuple<ComposedCall<T>, T> resume(T x);

	  public abstract T eval(T x);

	  public abstract boolean isComposed();

	  private ComposedCall() {
	  }

	  private static class Single<T> extends ComposedCall<T> {

	    private final Function<T, T> f;

	    private Single(Function<T, T> f) {
	      this.f = f;
	    }

	    @Override
	    public T eval(T x) {
	      return f.apply(x);
	    }

	    @Override
	    public boolean isComposed() {
	      return false;
	    }

		@Override
		public Tuple<ComposedCall<T>, T> resume(T x) {
			throw new IllegalStateException("RNo resume in single call");
		}

	  }

	  private static class Composed<T> extends ComposedCall<T> {

	    private final ComposedCall<T> f;
	    
	    private final Function<T, T> g;

	    private Composed(ComposedCall<T> f, Function<T, T> g) {
	      this.f = f;
	      this.g = g;
	    }

	    @Override
	    public T eval(T x) {
	    	Tuple<ComposedCall<T>, T> composedCallAndArg = new Tuple<>(this, x);
	    	while (composedCallAndArg._1.isComposed()) {
	    		composedCallAndArg = composedCallAndArg._1.resume(composedCallAndArg._2);
	    	}
	    	return composedCallAndArg._1.eval(composedCallAndArg._2);
	    }

	    @Override
	    public boolean isComposed() {
	      return true;
	    }

	    @Override
	    public Tuple<ComposedCall<T>, T> resume(T x) {
	      return new Tuple<>(f, g.apply(x));
	    }
	  }

	  public static <T> Single<T> single(Function<T, T> f) {
	    return new Single<>(f);
	  }

	  public static <T> Composed<T> composed(ComposedCall<T> f, Function<T, T> g) {
	    return new Composed<>(f, g);
	  }
	  
}
