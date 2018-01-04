package com.fpinjava.makingjavafunctional.exercise03_03;

import java.util.List;


public class CollectionUtilities {

  public static <T> List<T > list() {
    throw new RuntimeException("Do zaimplementowania");
  }

  public static <T> List<T > list(T t) {
    throw new RuntimeException("Do zaimplementowania");
  }
  public static <T> List<T > list(List<T> ts) {
    throw new RuntimeException("Do zaimplementowania");
  }

  @SafeVarargs
  public static <T> List<T > list(T... t) {
    throw new RuntimeException("Do zaimplementowania");
  }
}
