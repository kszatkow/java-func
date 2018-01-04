package com.fpinjava.handlingerrors.exercise07_08;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest {

  @Test
  public void testOfTValue() {
    assertEquals("Success(4)", Result.of(4).toString());
  }

  @Test
  public void testOfTNull() {
    assertEquals("Empty()", Result.of((Integer) null).toString());
  }

  @Test
  public void testOfTStringValue() {
    assertEquals("Success(4)", Result.of(4, "brak wartości").toString());
  }

  @Test
  public void testOfTStringNull() {
    assertEquals("Failure(brak wartości)", Result.of((Integer) null, "brak wartości").toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTValueTrue() {
    assertEquals("Success(4)", Result.of((Integer x) -> x % 2 == 0, 4).toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTValueFalse() {
    assertEquals("Empty()", Result.of((Integer x) -> x % 2 == 0, 5).toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTException() {
    assertEquals("Failure(Wyjątek w trakcie wyliczania predykatu: 4)", Result.of((Integer x) -> {throw new RuntimeException("wyjątek");}, 4).toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTStringValueTrue() {
    assertEquals("Success(4)", Result.of((Integer x) -> x % 2 == 0, 4, "nieparzysta").toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTStringValueFalse() {
    assertEquals("Failure(nieparzysta)", Result.of((Integer x) -> x % 2 == 0, 5, "nieparzysta").toString());
  }

  @Test
  public void testOfFunctionOfTBooleanTStringException() {
    assertEquals("Failure(Wyjątek w trakcie wyliczania predykatu: nieparzysta)", Result.of((Integer x) -> {throw new RuntimeException("wyjątek");}, 4, "nieparzysta").toString());
  }

}
