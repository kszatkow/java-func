package com.fpinjava.handlingerrors.exercise07_05;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fpinjava.common.Function;

public class ResultTest {

  Result<Integer> empty = Result.empty();
  Result<Integer> failure = Result.failure("Komunikat błędu");
  Result<Integer> success = Result.success(4);
  Function<Integer, Boolean> even = x -> x % 2 == 0;
  Function<Integer, Boolean> odd = x -> !even.apply(x);

  @Test
  public void testFilterFunctionOfVBooleanEmpty() {
    assertEquals("Empty()", empty.filter(even).toString());
    assertEquals("Empty()", empty.filter(odd).toString());
  }

  @Test
  public void testFilterFunctionOfVBooleanStringEmpty() {
    assertEquals("Empty()", empty.filter(even, "Warunek nie jest spełniony").toString());
    assertEquals("Empty()", empty.filter(odd, "Warunek nie jest spełniony").toString());
  }

  @Test
  public void testFilterFunctionOfVBooleanFailure() {
    assertEquals("Failure(Komunikat błędu)", failure.filter(even).toString());
    assertEquals("Failure(Komunikat błędu)", failure.filter(odd).toString());
  }

  @Test
  public void testFilterFunctionOfVBooleanStringFailure() {
    assertEquals("Failure(Komunikat błędu)", failure.filter(even, "Warunek nie jest spełniony").toString());
    assertEquals("Failure(Komunikat błędu)", failure.filter(odd, "Warunek nie jest spełniony").toString());
  }

  @Test
  public void testFilterFunctionOfVBooleanSuccess() {
    assertEquals("Success(4)", success.filter(even).toString());
    assertEquals("Failure(Brak dopasowania do warunku)", success.filter(odd).toString());
  }

  @Test
  public void testFilterFunctionOfVBooleanStringSuccess() {
    assertEquals("Success(4)", success.filter(even, "Liczba nie jest parzysta").toString());
    assertEquals("Failure(Liczba nie jest parzysta)", success.filter(odd, "Liczba nie jest parzysta").toString());
  }

}
