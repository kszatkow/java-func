package chapter5.ex5_6.solution;

import ks.java.func.Function;

public abstract class List<A> {

  public abstract A head();
  public abstract List<A> tail();
  public abstract boolean isEmpty();
  public abstract List<A> setHead(A h);
  public abstract List<A> drop(int n);
  public abstract List<A> dropAt(int n);
  public abstract List<A> dropWhile(Function<A, Boolean> pred);
  public abstract List<A> reverse();
  public abstract List<A> append(A a);
  public abstract List<A> append(List<A> a);
  public abstract List<A> init();
  
  public List<A> cons(A e) {
  	return new Cons<A>(e, this);
  }
  
  @SuppressWarnings("rawtypes")
  public static final List NIL = new Nil();

  private List() {}

  private static class Nil<A> extends List<A> {

    private Nil() {}

    public A head() {
      throw new IllegalStateException("head wywołanie dla pustej listy");
    }

    public List<A> tail() {
      throw new IllegalStateException("tail wywołane dla pustej listy");
    }

    public boolean isEmpty() {
      return true;
    }
    
    public List<A> setHead(A h) {
  	  return new Cons<A>(h, this);
    }
    
    public String toString() {
    	return new String("[NIL]");
    }

	@Override
	public List<A> dropAt(int n) {
		throw new IllegalStateException("No drop for Nil list");
	}

	@Override
	public List<A> append(A a) {
		return cons(a);
	}

	@Override
	public List<A> append(List<A> a) {
		return a;
	}

	@Override
	public List<A> reverse() {
		return this;
	}

	@Override
	public List<A> drop(int n) {
		throw new IllegalStateException("No drop for Nil list");
	}

	@Override
	public List<A> dropWhile(Function<A, Boolean> pred) {
		throw new IllegalStateException("No drop for Nil list");
	}

	@Override
	public List<A> init() {
		throw new IllegalStateException("No drop for Nil list");
	}

  }

  private static class Cons<A> extends List<A> {

    private final A head;
    private final List<A> tail;

    private Cons(A head, List<A> tail) {
      this.head = head;
      this.tail = tail;
    }

    public A head() {
      return head;
    }

    public List<A> tail() {
      return tail;
    }

    public boolean isEmpty() {
      return false;
    }
    
    public List<A> setHead(A h) {
	  return new Cons<A>(h, tail());
    }
    
    public String toString() {
    	return String.format("[%sNIL]", makeStringBuilder(this, new StringBuilder(), ", ").eval());
    }
    
    private static <A> TailCall<StringBuilder> makeStringBuilder(List<A> list, StringBuilder sb, String separator) {
    	return list.isEmpty() ?
    			TailCall.ret(sb) :
    			TailCall.sus(() -> makeStringBuilder(list.tail(), sb.append(list.head()).append(separator), separator));	
    }

	@Override
	public List<A> dropAt(int n) {
		return dropAt_(this, n, list());
	}
	
	private static <A> List<A> dropAt_(List<A> list, int n, List<A> newList) {
		if (n >= 0 && list.isEmpty()) {
			throw new IllegalStateException("Cannot drop past the end of the list.");
		}
		
		return n == 0 ? newList.append(list.tail()) : dropAt_(list.tail(), n - 1, newList.append(list.head()));  
	}

	@Override
	public List<A> append(A a) {
		return appendSingle_(this, a, list()).eval().reverse();
	}

	private static <A> TailCall<List<A>> appendSingle_(List<A> list, A a, List<A> newList) {
		return list.isEmpty() ? 
				TailCall.ret(new Cons<>(a, newList)) : 
				TailCall.sus(() -> appendSingle_(list.tail(), a, new Cons<A>(list.head(), newList)));
	}
	
	@Override
	public List<A> append(List<A> l) {
		return appendMultiple_(this, l, list()).eval().reverse();
	}
	
	private static <A> TailCall<List<A>> appendMultiple_(List<A> list, List<A> toAppend, List<A> newList) {
		return list.isEmpty() ? 
				toAppend.isEmpty() ?
						TailCall.ret(newList) :
						TailCall.sus(() -> appendMultiple_(toAppend, list, newList)) : 
				TailCall.sus(() -> appendMultiple_(list.tail(), toAppend, new Cons<A>(list.head(), newList)));
	}

	@Override
	public List<A> reverse() {
		return reverse_(this, list()).eval();
	}
	
	private static <A> TailCall<List<A>> reverse_(List<A> list, List<A> reversed) {
		return list.isEmpty() ?
				TailCall.ret(reversed) :
				TailCall.sus(() -> reverse_(list.tail(), new Cons<A>(list.head(), reversed)));
	}

	@Override
	public List<A> drop(int n) {
		return n == 0 ? this : drop_(this, n).eval();
	}
	
	private static <A> TailCall<List<A>> drop_(List<A> list, int n) {
		if (n > 0 && list.isEmpty()) {
			throw new IllegalStateException("Cannot drop past the end of the list.");
		}
		
		return n == 1 ? TailCall.ret(list.tail()) : TailCall.sus(() -> drop_(list.tail(), n - 1));  
	}

	@Override
	public List<A> dropWhile(Function<A, Boolean> pred) {
		return dropWhile_(this, pred).eval();
	}

	private TailCall<List<A>> dropWhile_(List<A> list, Function<A, Boolean> pred) {
		return !list.isEmpty() && pred.apply(list.head()) ?
				TailCall.sus(() -> dropWhile_(list.tail(), pred)) :	
				TailCall.ret(list);
	}

	@Override
	public List<A> init() {
		return init_(this, list()).eval().reverse();
	}

	private TailCall<List<A>> init_(List<A> list, List<A> newList) {
		return list.tail().isEmpty() ?
				TailCall.ret(newList) :
				TailCall.sus(() -> init_(list.tail(), new Cons<>(list.head(), newList)));	
	}
	
  }

  @SuppressWarnings("unchecked")
  public static <A> List<A> list() {
    return NIL;
  }

  @SafeVarargs
  public static <A> List<A> list(A... a) {
    List<A> n = list();
    for (int i = a.length - 1; i >= 0; i--) {
      n = new Cons<>(a[i], n);
    }
    return n;
  }

}
