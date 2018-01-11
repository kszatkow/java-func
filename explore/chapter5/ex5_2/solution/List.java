package chapter5.ex5_2.solution;

public abstract class List<A> {

  public abstract A head();
  public abstract List<A> tail();
  public abstract boolean isEmpty();
  public abstract List<A> setHead(A h);
  
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