package com.fpinjava.io.exercise13_08;

import com.fpinjava.common.Nothing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String... args) throws IOException {
//    IO program = IO.repeat(3, sayHello());
//    program.run();

    sayHello(3);
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

  private static void sayHello(int n) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < n; i++) {
      System.out.println("Wpisz imię: ");
      String name = br.readLine();
      System.out.println(buildMessage(name));
    }
  }
}
