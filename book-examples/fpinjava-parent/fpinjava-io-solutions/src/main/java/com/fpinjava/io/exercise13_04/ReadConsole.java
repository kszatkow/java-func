package com.fpinjava.io.exercise13_04;


import com.fpinjava.common.Result;
import com.fpinjava.common.Stream;
import com.fpinjava.common.Tuple;


public class ReadConsole {

  public static void main(String... args) {
    Input input = ConsoleReader.consoleReader();

    Stream<Person> stream = Stream.unfold(input, ReadConsole::person);
    stream.toList().forEach(System.out::println);
  }

  public static Result<Tuple<Person, Input>> person(Input input) {
    return input.readInt("Wpisz ID:")
       .flatMap(id -> id._2.readString("Wpisz imię:")
           .flatMap(firstName -> firstName._2.readString("Wpisz nazwisko:")
               .map(lastName -> new Tuple<>(Person.apply(id._1, firstName._1, lastName._1), lastName._2))));
  }
}
