package com.fpinjava.application.xml.version_3;


import com.fpinjava.common.Executable;
import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import com.fpinjava.common.Tuple;

public class Test {

  private final static Tuple<String, List<String>> format = new Tuple<>("Imię : %s\n" +
      "\tNazwisko : %s\n" +
      "\tE-mail : %s\n" +
      "\tWynagrodzenie : %s", List.list("firstname", "lastname", "email", "salary"));

  public static void main(String... args) {
    Executable program = ReadXmlFile.readXmlFile(Test::getXmlFilePath, Test::getRootElementName, format, Test::processList);
    program.exec();
  }

  private static Result<String> getXmlFilePath() {
    return Result.of("plik.xml"); // <- dostosuj ścieżkę
  }

  private static Result<String> getRootElementName() {
    return Result.of("staff");
  }

  private static <T> void processList(List<T> list) {
    list.forEach(System.out::println);
  }

}
