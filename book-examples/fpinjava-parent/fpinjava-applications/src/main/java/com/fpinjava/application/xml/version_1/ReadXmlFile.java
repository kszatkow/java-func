package com.fpinjava.application.xml.version_1;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Wypróbuj z pustym plikiem.
 */
public class ReadXmlFile {

  /**
   * Nie do przetestowania, zgłasza wyjątki.
   */
  public static void main(String[] args) {

    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File("/sciezka/do/pliku.xml"); // Popraw ścieżkę.

    try {

      Document document = (Document) builder.build(xmlFile);
      Element rootNode = document.getRootElement();
      List list = rootNode.getChildren("staff");

      for (int i = 0; i < list.size(); i++) {

        Element node = (Element) list.get(i);

        System.out.println("Imię : " + node.getChildText("firstname"));
        System.out.println("\tNazwisko : " + node.getChildText("lastname"));
        System.out.println("\tE-mail : " + node.getChildText("email"));
        System.out.println("\tWynagrodzenie : " + node.getChildText("salary"));
      }
    } catch (IOException io) {
      System.out.println(io.getMessage());
    } catch (JDOMException jdomex) {
      System.out.println(jdomex.getMessage());
    }
  }
}
