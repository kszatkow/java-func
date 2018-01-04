package com.fpinjava.makingjavafunctional.listing03_09;

import static com.fpinjava.makingjavafunctional.exercise03_01.Result.failure;
import static com.fpinjava.makingjavafunctional.exercise03_01.Result.success;
import static com.fpinjava.makingjavafunctional.listing03_08.Case.match;
import static com.fpinjava.makingjavafunctional.listing03_08.Case.mcase;

import java.util.regex.Pattern;

import com.fpinjava.common.Function;
import com.fpinjava.makingjavafunctional.exercise03_01.Effect;
import com.fpinjava.makingjavafunctional.exercise03_01.Result;

public class EmailValidation {

  static Pattern emailPattern =
      Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

  static Effect<String> success = s -> 
                 System.out.println("E-mail weryfikacyjny wysłany do " + s);

  static Effect<String> failure = s -> 
                 System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + s);

  public static void main(String... args) {
    emailChecker.apply("to.jest@jakis.adres").bind(success, failure);
    emailChecker.apply(null).bind(success, failure);
    emailChecker.apply("").bind(success, failure);
    emailChecker.apply("jan.kowalski@test.com").bind(success, failure);
  }

  static Function<String, Result<String>> emailChecker = s -> match(
      mcase(() -> success(s)),
      mcase(() -> s == null, () -> failure("Adres e-mail nie może być wartością null.")),
      mcase(() -> s.length() == 0, () -> 
                                   failure("Adres e-mail nie może być pusty.")),
      mcase(() -> !emailPattern.matcher(s).matches(), () -> 
                                   failure("Adres e-mail " + s + " jest niepoprawny."))
  );
}