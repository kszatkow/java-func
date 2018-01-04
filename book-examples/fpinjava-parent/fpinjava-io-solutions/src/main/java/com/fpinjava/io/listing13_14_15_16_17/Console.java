package com.fpinjava.io.listing13_14_15_16_17;


import com.fpinjava.common.Function;
import com.fpinjava.common.Nothing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static IO<String> readLine(Nothing nothing) {
    return new IO.Suspend<>(() -> {
      try {
        return br.readLine();
      } catch (IOException e) {
        throw new IllegalStateException((e));
      }
    });
  }

  /**
   * Możliwa implementacja readLine jako funkcja.
   */
  public static Function<Nothing, IO<String>> readLine_ = x -> new IO.Suspend<>(() -> {
    try {
      return br.readLine();
    } catch (IOException e) {
      throw new IllegalStateException((e));
    }
  });

  /**
   * Prostsza implementacja readLine jako funkcja używająca referencji do metody.
   */
  public static Function<Nothing, IO<String>> readLine = Console::readLine;

  /**
   * Metoda pomocnicza umożliwiająca wywoływanie metody readLine bez podawania Nothing.
   */
  public static IO<String> readLine() {
    return readLine(Nothing.instance);
  }

  public static IO<Nothing> printLine(Object s) {
    return new IO.Suspend<>(() -> println(s));
  }

  private static Nothing println(Object s) {
    System.out.println(s);
    return Nothing.instance;
  }

  public static IO<Nothing> printLine_(Object s) {
    return new IO.Suspend<>(() -> {
      System.out.println(s);
      return Nothing.instance;
    });
  }

  public static Function<String, IO<Nothing>> printLine_ = s -> new IO.Suspend<>(() -> {
    System.out.println(s);
    return Nothing.instance;
  });

  public static Function<String, IO<Nothing>> printLine = Console::printLine;

}
