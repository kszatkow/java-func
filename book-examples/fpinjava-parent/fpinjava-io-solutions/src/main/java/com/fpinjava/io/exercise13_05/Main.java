package com.fpinjava.io.exercise13_05;


import com.fpinjava.common.List;

public class Main {

  public static void main(String... args) {

    String name = getName();

    // Te trzy wiersze nic nie robią. Każdy z nich zwraca program.
    IO instruction1 = println("Witaj, ");
    IO instruction2 = println(name);
    IO instruction3 = println("!\n");

    // Napisz skrypt.
    IO script = instruction1.add(instruction2).add(instruction3);
    // Wykonaj go.
    script.run();

    // Lub prościej:
    println("Witaj, ").add(println(name)).add(println("!\n")).run();

    // Możemy wykonać skrypt z listy instrukcji:

    List<IO> script2 = List.list(
            println("Witaj, "),  // Czy to nie wygląda na program imperatywny?
            println(name),
            println("!\n")
    );
    // Możemy go w pewnym sensie "skompilować", a następnie wykonać.
    script2.foldRight(IO.empty, io -> io::add).run();
    script2.foldLeft(IO.empty, acc -> acc::add).run();
  }

  private static IO println(String name) {
    return () -> System.out.print(name);
  }

  private static String getName() {
    return "Mickey";
  }
}

