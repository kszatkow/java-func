package chapter3.ex3_1.solution;

import java.util.regex.Pattern;

import ks.java.func.Function;

public class EmailValidation {

	  static Pattern emailPattern =
	          Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

	  static Function<String, Result> emailChecker = s ->
	          s == null
	                  ? new Failure(s, "Adres e-mail nie może być wartością null.")
	                  : s.length() == 0
	                  ? new Failure(s, "Adres e-mail nie może być pusty.")
	                  : emailPattern.matcher(s).matches()
	                  ? new Success(s)
	                  : new Failure(s, "Adres e-mail " + s + " jest niepoprawny.");

	  public static void main(String... args) {
	    validationEffect.apply("to.jest@jakis.adres").apply();
	    validationEffect.apply(null).apply();
	    Thunk effect1 = validationEffect.apply("");
	    Thunk effect2 = validationEffect.apply("jan.kowalski@test.com");
	    effect2.apply();
	    effect1.apply();
	  }

  static Function<String, Thunk> validationEffect =
	  s -> emailChecker.apply(s).getEffect();
			  
}
