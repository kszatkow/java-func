package com.fpinjava.advancedtrees.exercise11_02;


public class Map<K extends Comparable<K>, V> {

  protected final Tree<MapEntry<K, V>> delegate;

  private Map() {
    this.delegate = Tree.empty();
  }

  private Map(Tree<MapEntry<K, V>> delegate) {
    this.delegate = delegate;
  }

  public Map<K, V> add(K key, V value) {
    throw new IllegalStateException("Do zaimplementowania");
  }

  public boolean contains(K key) {
    throw new IllegalStateException("Do zaimplementowania");
  }

  public Map<K, V> remove(K key) {
    throw new IllegalStateException("Do zaimplementowania");
  }

  public boolean isEmpty() {
    throw new IllegalStateException("Do zaimplementowania");
  }

  public static <K extends Comparable<K>, V> Map<K, V> empty() {
    return new Map<>();
  }
}
