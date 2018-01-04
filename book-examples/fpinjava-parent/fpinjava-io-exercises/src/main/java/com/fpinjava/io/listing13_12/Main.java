package com.fpinjava.io.listing13_12;


import com.fpinjava.common.Nothing;
import com.fpinjava.io.exercise13_08.Console;
import com.fpinjava.io.exercise13_08.IO;

public class Main {

  public static void main(String... args) {
    IO<Nothing> script = sayHello();
    script.run();
  }

  private static IO<Nothing> sayHello() {
    return Console.printLine("Wpisz imię: ")
        .flatMap(Console::readLine)
        .map(Main::buildMessage)
        .flatMap(Console::printLine);
  }

  private static String buildMessage(String name) {
    return String.format("Witaj, %s!", name);
  }
}

