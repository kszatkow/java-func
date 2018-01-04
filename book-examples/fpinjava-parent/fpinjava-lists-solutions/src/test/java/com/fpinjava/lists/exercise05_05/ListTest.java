package com.fpinjava.lists.exercise05_05;

import static com.fpinjava.lists.exercise05_05.List.list;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ListTest {

  @Test
  public void testDropWhile() {
    // Dla pustej listy stosujemy adnotacjędla metody list(), aby wspomóc Javę w określeniu typu.
    assertEquals("[NIL]", List.<Integer>list().dropWhile(x -> x < 3).toString());
    assertEquals("[1, 2, 3, NIL]", list(1, 2, 3).dropWhile(x -> x > 3).toString());
    assertEquals("[3, NIL]", list(1, 2, 3).dropWhile(x -> x < 3).toString());
    assertEquals("[NIL]", list(1, 2, 3).dropWhile(x -> x < 5).toString());
  }

}
