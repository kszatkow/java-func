package com.fpinjava.application.xml.version_4;

import com.fpinjava.common.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadXmlFile {

  public static <T> Executable readXmlFile(Supplier<FilePath> sPath,
                                           Supplier<ElementName> sRootName,
                                           Function<Element, T> f,
                                           Effect<List<T>> e) {
    final Result<String> path = sPath.get().value;
    final Result<String> rDoc = path.flatMap(ReadXmlFile::readFile2String);
    final Result<String> rRoot =sRootName.get().value;
    final Result<List<T>> result = rDoc.flatMap(doc -> rRoot
        .flatMap(rootElementName -> readDocument(rootElementName, doc))
        .map(list -> list.map(f)));
    return () -> result.forEachOrException(e)
          .forEach(Throwable::printStackTrace);
  }

  public static Result<String> readFile2String(String path) {
    try {
      return Result.success(new String(Files.readAllBytes(Paths.get(path))));
    } catch (IOException e) {
      return Result.failure(String.format("Błąd IO w trakcie odczytu pliku %s", path), e);
    } catch (Exception e) {
      return Result.failure(String.format("Nieoczekiany błąd w trakcie odczytu %s", path), e);
    }
  }

  private static Result<List<Element>> readDocument(String rootElementName, String stringDoc) {
    final SAXBuilder builder = new SAXBuilder();
    try {
      final Document document = builder.build(new StringReader(stringDoc));
      final Element rootElement = document.getRootElement();
      return Result.success(List.fromCollection(rootElement.getChildren(rootElementName)));
    } catch (IOException | JDOMException io) {
      return Result.failure(String.format("Niewłaściwa nazwa elementu głównego '%s' lub danych XML %s", rootElementName, stringDoc), io);
    } catch (Exception e) {
      return Result.failure(String.format("Nieoczekiwany błąd w trakcie odczytu danych XML %s", stringDoc), e);
    }
  }
}
