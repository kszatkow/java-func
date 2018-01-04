package com.fpinjava.advancedlisthandling.exercise08_12;

import org.junit.Test;

import static org.junit.Assert.*;


public class ListTest {

  @Test
  public void testGetAt() throws Exception {
    List<Integer> list = List.list(1, 3, 5, 7, 9, 11, 13);
    assertEquals("Failure(java.lang.IllegalStateException: Indeks poza granicami)", list.getAt(-2).toString());
    assertEquals("Success(1)", list.getAt(0).toString());
    assertEquals("Success(11)", list.getAt(5).toString());
    assertEquals("Failure(java.lang.IllegalStateException: Indeks poza granicami)", list.getAt(7).toString());
    assertEquals("Failure(java.lang.IllegalStateException: Indeks poza granicami)", list.getAt(9).toString());
  }
}
