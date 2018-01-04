package com.fpinjava.io.exercise13_09;

public class Main {

  public static void main(String... args) {
    IO program = IO.forever(IO.unit("Witaj ponownie!")
                              .flatMap(Console::printLine));
    program.run();
  }
}
