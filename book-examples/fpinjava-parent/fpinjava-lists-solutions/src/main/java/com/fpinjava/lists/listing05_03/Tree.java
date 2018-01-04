package com.fpinjava.lists.listing05_03;

public abstract class Tree<A> {

  public abstract Tree<A> left();
  public abstract Tree<A> right();
  public abstract A value();

  private Tree() {}

  private static class Leaf<A> extends Tree<A> {

    private final A value;

    private Leaf(A value) {
      this.value = value;
    }

    public Tree<A> left() {
      throw new IllegalStateException("left wywołane dla Leaf");
    }

    public Tree<A> right() {
      throw new IllegalStateException("right wywołane dla Leaf");
    }

    public A value() {
      return this.value;
    }
  }

  private static class Branch<A> extends Tree<A> {

    private final Tree<A> left;
    private final Tree<A> right;

    private Branch(Tree<A> left, Tree<A> right) {
      this.left = left;
      this.right = right;
    }

    public Tree<A> left() {
      return this.left;
    }

    @Override
    public Tree<A> right() {
      return this.right;
    }

    @Override
    public A value() {
      throw new IllegalStateException("value wywołane dla Branch");
    }
  }

  public static <A> Tree<A> tree(A a) {
    return new Leaf<>(a);
  }

  public static <A> Tree<A> tree(Tree<A> left, Tree<A> right) {
    return new Branch<>(left, right);
  }
}
