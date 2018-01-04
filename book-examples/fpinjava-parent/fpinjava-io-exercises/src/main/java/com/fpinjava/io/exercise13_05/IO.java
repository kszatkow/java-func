package com.fpinjava.io.exercise13_05;


public interface IO {

  void run();

  default IO add(IO io) {
    throw new IllegalStateException("Do zaimplementowania");
  }

  IO empty = () -> {};

}
