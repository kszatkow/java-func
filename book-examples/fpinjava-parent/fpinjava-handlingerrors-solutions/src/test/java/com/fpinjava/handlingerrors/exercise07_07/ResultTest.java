package com.fpinjava.handlingerrors.exercise07_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest {

  static class TestException extends RuntimeException {
    public TestException() {
    }

    public TestException(String message) {
      super(message);
    }

    public TestException(String message, Throwable cause) {
      super(message, cause);
    }

    public TestException(Throwable cause) {
      super(cause);
    }
  }

  Result<Integer> empty = Result.empty();
  Result<Integer> failure = Result.failure("Komunikat błędu");
  Result<Integer> success = Result.success(4);
  TestException testException = new TestException("testowy komunikat wyjątku");

  @Test
  public void testMapFailureStringEmpty() {
    assertEquals("Empty()", empty.mapFailure("brak danych").toString());
  }

  @Test
  public void testMapFailureStringFailure() {
    assertEquals("Failure(brak danych)", failure.mapFailure("brak danych").toString());
  }

  @Test
  public void testMapFailureStringSuccess() {
    assertEquals("Success(4)", success.mapFailure("brak danych").toString());
  }

  @Test
  public void testMapFailureStringExceptionEmpty() {
    assertEquals("Empty()", empty.mapFailure("brak danych", testException).toString());
  }

  @Test
  public void testMapFailureStringExceptionFailure() {
    assertEquals("Failure(brak danych)", failure.mapFailure("brak danych", testException).toString());
  }

  @Test
  public void testMapFailureStringExceptionSuccess() {
    assertEquals("Success(4)", success.mapFailure("brak danych", testException).toString());
  }

  @Test
  public void testMapFailureExceptionEmpty() {
    assertEquals("Empty()", empty.mapFailure(testException).toString());
  }

  @Test
  public void testMapFailureExceptionFailure() {
    assertEquals("Failure(testowy komunikat wyjątku)", failure.mapFailure(testException).toString());
  }

  @Test
  public void testMapFailureExceptionFailureThrow() {
    try {
      failure.mapFailure(testException).failIfEmpty("");
    } catch (IllegalStateException e) {
      assertEquals("testowy komunikat wyjątku", e.getMessage());
    }
  }

  @Test
  public void testMapFailureExceptionSuccess() {
    assertEquals("Success(4)", success.mapFailure(testException).toString());
  }

  @Test
  public void testFailIfEmptyEmpty() {
    assertEquals("Failure(brak danych)", empty.failIfEmpty("brak danych").toString());
  }

  @Test
  public void testFailIfEmptyFailure() {
    assertEquals("Failure(Komunikat błędu)", failure.failIfEmpty("brak danych").toString());
  }

  @Test
  public void testFailIfEmptySuccess() {
    assertEquals("Success(4)", success.failIfEmpty("brak danych").toString());
  }
}
