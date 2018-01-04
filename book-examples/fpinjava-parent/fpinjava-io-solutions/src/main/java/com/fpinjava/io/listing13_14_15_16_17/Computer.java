package com.fpinjava.io.listing13_14_15_16_17;


import com.fpinjava.common.Function;
import com.fpinjava.common.Nothing;
import com.fpinjava.common.Stream;

import static com.fpinjava.io.listing13_14_15_16_17.Console.*;
import static com.fpinjava.io.listing13_14_15_16_17.IO.*;


public class Computer {

  public static void main(String... args) {

    //factorial(5).flatMap(Console::printLine).run();

    compute(factorialComputer, "silnia").run();
    compute(squareComputer, "kwadrat").run();
  }


  public static IO<String> readInt() {
    return readLine();
  }

  public static IO<Integer> factorial(int n) {
    return ref(1)
        .flatMap(acc -> forEach(Stream.range(1, n), i -> skip(acc.modify(x -> x * i)))
                .flatMap(result -> acc.get()));
  }

  public static int square(int n) {
    return n * n;
  }

  private static Function<String, IO<Boolean>> factorialComputer = line -> when(!line.equals("x"), () -> factorial(Integer.parseInt(line))
      .map(s -> String.format("silnia: %s", s))
      .flatMap(Console::printLine));

  private static Function<String, IO<Boolean>> squareComputer = line -> when(!line.equals("x"), () -> unit(square(Integer.parseInt(line)))
      .map(s -> String.format("kwadrat: %s", s))
      .flatMap(Console::printLine));

  public static IO<Nothing> compute(Function<String, IO<Boolean>> f, String name) {
    return sequence(
        printLine(String.format("Komputer %s", name)),
        printLine(String.format(" - Wpisz liczbę do wyliczenia %s", name)),
        printLine(" - Wpisz 'x', aby wyjść"),
        doWhile(readLine(), f),
        printLine("Jeszcze wrócę.")
    );
  }

}
