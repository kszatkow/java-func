package chapter3.ex3_1.solution3;

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
			  					arg == null
			  						? new Failure(arg, "Adres e-mail nie może być wartością null.", failure)
		        	        	: arg.length() == 0
		        	        		? new Failure(arg, "Adres e-mail nie może być pusty.", failure)
		        	        	: emailPattern.matcher(arg).matches()
		        	        		? new Success(arg, success)
		        	        	: new Failure(arg, "Adres e-mail " + arg + " jest niepoprawny.", failure);;
	  
	  public static void main(String... args) {
		  validator.apply(sendVerificationMail).apply(logError).apply("to.jest@jakis.adres").effect();
		  validator.apply(sendVerificationMail).apply(logError).apply(null).effect();
		  Result result1 = validator.apply(sendVerificationMail).apply(logError).apply("");
		  Result result2 = validator.apply(sendVerificationMail).apply(logError).apply("jan.kowalski@test.com");
		  result2.effect();
		  result1.effect();
	  }
	  
}
