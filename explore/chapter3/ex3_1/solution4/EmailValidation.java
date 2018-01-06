package chapter3.ex3_1.solution4;

import static ks.java.func.IfThenElse.ifThenElse;

import java.util.regex.Pattern;

public class EmailValidation {

	  private static final Pattern emailPattern =
	          Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

	  private static final Effect<String> sendVerificationMail = 
			  eMail -> System.out.println("E-mail weryfikacyjny wysłany do " + eMail);
	  
	  private static final Effect<String> logError = 
			  errorMessage -> System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + errorMessage); 
				    
	  private static final EMailValidator<String, String, String> validator = 
			  success -> failure -> arg -> 
			  	ifThenElse( (String x) -> x == null,
			  				x -> new Failure(arg, "Adres e-mail nie może być wartością null.", failure),
			  				ifThenElse( x -> x.length() == 0,
			  							x -> new Failure(arg, "Adres e-mail nie może być pusty.", failure),
			  							ifThenElse( x -> emailPattern.matcher(x).matches(),
			  										x -> new Success(arg, success),
			  										x -> new Failure(arg, "Adres e-mail " + arg + " jest niepoprawny.", failure)
			  							)
			  				)
			  	).apply(arg);

	  public static void main(String... args) {
		  validator.apply(sendVerificationMail).apply(logError).apply("to.jest@jakis.adres").effect();
		  validator.apply(sendVerificationMail).apply(logError).apply(null).effect();
		  Result result1 = validator.apply(sendVerificationMail).apply(logError).apply("");
		  Result result2 = validator.apply(sendVerificationMail).apply(logError).apply("jan.kowalski@test.com");
		  result2.effect();
		  result1.effect();
	  }
	  
}
