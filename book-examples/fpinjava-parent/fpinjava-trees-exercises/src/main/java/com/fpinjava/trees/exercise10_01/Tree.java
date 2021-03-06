package com.fpinjava.trees.exercise10_01;


public abstract class Tree<A extends Comparable<A>> {

  @SuppressWarnings("rawtypes")
  private static Tree EMPTY = new Empty();

  abstract Tree<A> left();
  abstract Tree<A> right();

  public abstract A value();

  public abstract Tree<A> insert(A a);

  private static class Empty<A extends Comparable<A>> extends Tree<A> {

    @Override
    public A value() {
      throw new IllegalStateException("value() wywołane dla pustego elementu");
    }

    @Override
    Tree<A> left() {
      throw new IllegalStateException("left() wywołane dla pustego elementu");
    }

    @Override
    Tree<A> right() {
      throw new IllegalStateException("right() wywołane dla pustego elementu");
    }

    @Override
    public Tree<A> insert(A insertedValue) {
      throw new IllegalStateException("Do zaimplementowania");
    }

    @Override
    public String toString() {
      return "E";
    }
  }

  private static class T<A extends Comparable<A>> extends Tree<A> {

    private final Tree<A> left;
    private final Tree<A> right;
    private final A value;

    private T(Tree<A> left, A value, Tree<A> right) {
      this.left = left;
      this.right = right;
      this.value = value;
    }

    @Override
    public A value() {
      return value;
    }

    @Override
    Tree<A> left() {
      return left;
    }

    @Override
    Tree<A> right() {
      return right;
    }

    @Override
    public Tree<A> insert(A insertedValue) {
      throw new IllegalStateException("Do zaimplementowania");
    }

    @Override
    public String toString() {
      return String.format("(T %s %s %s)", left, value, right);
    }
  }

  @SuppressWarnings("unchecked")
  public static <A extends Comparable<A>> Tree<A> empty() {
    return EMPTY;
  }
}
