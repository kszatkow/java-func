package com.fpinjava.makingjavafunctional.exercise03_01;

import static com.fpinjava.makingjavafunctional.exercise03_01.Result.failure;
import static com.fpinjava.makingjavafunctional.exercise03_01.Result.success;

import java.util.regex.Pattern;

import com.fpinjava.common.Function;
import com.fpinjava.makingjavafunctional.exercise03_01.Effect;
import com.fpinjava.makingjavafunctional.exercise03_01.Result;

public class EmailValidation {

  static Pattern emailPattern =
      Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

  static Function<String, Result<String>> emailChecker = s -> {
    if (s == null) {
      return failure("Adres e-mail nie może być wartością null.");
    } else if (s.length() == 0) {
      return failure("Adres e-mail nie może być pusty.");
    } else if (emailPattern.matcher(s).matches()) {
      return success(s);
    } else {
      return failure("Adres e-mail " + s + " jest niepoprawny.");
    }
  };

  public static void main(String... args) {
    emailChecker.apply("to.jest@jakis.adres").bind(success, failure);
    emailChecker.apply(null).bind(success, failure);
    emailChecker.apply("").bind(success, failure);
    emailChecker.apply("jan.kowalski@test.com").bind(success, failure);
  }

  static Effect<String> success = s -> 
                             System.out.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + s);
  
  static Effect<String> failure = s -> 
                    System.err.println("E-mail weryfikacyjny wysłany do " + s);
}