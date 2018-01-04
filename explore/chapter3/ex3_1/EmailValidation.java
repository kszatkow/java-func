package chapter3.ex3_1;

import java.util.regex.Pattern;

import ks.java.func.Function;

public class EmailValidation {

  static Pattern emailPattern =
          Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

  static Function<String, Result> emailChecker = s ->
          s == null
                  ? new Result.Failure("Adres e-mail nie może być wartością null.")
                  : s.length() == 0
                  ? new Result.Failure("Adres e-mail nie może być pusty.")
                  : emailPattern.matcher(s).matches()
                  ? new Result.Success()
                  : new Result.Failure("Adres e-mail " + s + " jest niepoprawny.");


  public static void main(String... args) {
    validate("to.jest@jakis.adres").exec();
    validate(null).exec();
    validate("").exec();
    validate("jan.kowalski@test.com").exec();
  }

  private static void logError(String s) {
    System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + s);
  }

  private static void sendVerificationMail(String s) {
    System.out.println("E-mail weryfikacyjny wysłany do " + s);
  }

  static Executable validate(String s) {
    Result result = emailChecker.apply(s);
    return (result instanceof Result.Success)
            ? () -> sendVerificationMail(s)
            : () -> logError(((Result.Failure) result).getMessage());
  }
}