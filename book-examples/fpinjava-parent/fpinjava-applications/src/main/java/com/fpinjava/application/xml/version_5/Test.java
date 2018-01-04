package com.fpinjava.application.xml.version_5;


import com.fpinjava.common.Executable;
import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import org.jdom2.Element;

public class Test {

  private final static String format = "Imię : %s\n" +
      "\tNazwisko : %s\n" +
      "\tE-mail : %s\n" +
      "\tWynagrodzenie : %s";

  private final static List<String> elementNames = List.list("firstname", "lastname", "email"); // <- brakuje "salary"

  public static void main(String... args) {
    Executable program = ReadXmlFile.readXmlFile(Test::getXmlFilePath, Test::getRootElementName, Test::processElement, Test::processList);
    try {
      program.exec();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static Result<String> processElement(Element element) {
    try {
      return Result.of(String.format(format, elementNames.map(name -> getChildText(element, name)).toJavaList().toArray()));
    } catch (Exception e) {
      return Result.failure("Wyjątek w trakcie formatowania elementu. Prawdopodobną przyczyną jest brak nazwy elementu na liście elementów " + elementNames);
    }
  }

  private static String getChildText(Element element, String name) {
    String string = element.getChildText(name);
    return string != null ? string :  "Nie znaleziono elementu " + name;
  }

  private static FilePath getXmlFilePath() {
    return FilePath.apply("plik.xml"); // <- dostosuj ścieżkę
  }

  private static ElementName getRootElementName() {
    return ElementName.apply("staff");
  }

  private static <T> void processList(List<T> list) {
    list.forEach(System.out::println);
  }
}
