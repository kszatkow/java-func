package com.fpinjava.io.listing13_13;

import com.fpinjava.common.Function;
import com.fpinjava.common.Nothing;

import java.io.IOException;

public class Main {

  public static void main(String... args) throws IOException {
    IO program = program(buildMessage, "Wpisz imiona osób do powitania lub naciśnij Enter, aby zakończyć.");
    program.run();
  }

  public static IO<Nothing> program(Function<String, IO<Boolean>> f, String title) {
    return IO.sequence(
        Console.printLine(title),
        IO.doWhile(Console.readLine(), f),
        Console.printLine("do widzenia!")
    );
  }

  private static Function<String, IO<Boolean>> buildMessage = name -> IO.when(name.length() != 0, () -> IO.unit(String.format("Witaj, %s!", name)).flatMap(Console::printLine));

}
