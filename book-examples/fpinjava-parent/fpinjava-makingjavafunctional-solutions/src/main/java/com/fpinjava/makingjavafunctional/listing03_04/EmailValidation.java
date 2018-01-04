package com.fpinjava.makingjavafunctional.listing03_04;

import java.util.regex.Pattern;

import com.fpinjava.common.Function;
import com.fpinjava.makingjavafunctional.listing03_03.Result;

public class EmailValidation {

  static Pattern emailPattern =
      Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

  static Function<String, Result> emailChecker = s -> {
    if (s == null) {
      return new Result.Failure("Adres e-mail nie może być wartością null.");
    } else if (s.length() == 0) {
      return new Result.Failure("Adres e-mail nie może być pusty.");
    } else if (emailPattern.matcher(s).matches()) {
      return new Result.Success();
    } else {
      return new Result.Failure("Adres e-mail " + s + " jest niepoprawny.");
    }
  };

  public static void main(String... args) {
    validate("to.jest@jakis.adres");
    validate(null);
    validate("");
    validate("jan.kowalski@test.com");
  }

  private static void logError(String s) {
    System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + s);
  }

  private static void sendVerificationMail(String s) {
    System.out.println("E-mail weryfikacyjny wysłany do " + s);
  }

  static void validate(String s) {
    Result result = emailChecker.apply(s);
    if (result instanceof Result.Success) {
      sendVerificationMail(s);
    } else {
      logError(((Result.Failure) result).getMessage());
    }
  }
}
