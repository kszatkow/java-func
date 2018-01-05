package chapter3.ex3_1.solution2;

import java.util.regex.Pattern;

public class EmailValidation {

	  private static final Pattern emailPattern =
	          Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

	  private static final Effect<String> sendVerificationMail = 
			  eMail -> System.out.println("E-mail weryfikacyjny wysłany do " + eMail);
	  
	  private static final Effect<String> logError = 
			  errorMessage -> System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + errorMessage); 
				    
	  private static final Validator<String> validator = 
			arg -> arg == null
        		? new Failure(arg, "Adres e-mail nie może być wartością null.", logError)
        	: arg.length() == 0
        		? new Failure(arg, "Adres e-mail nie może być pusty.", logError)
        	: emailPattern.matcher(arg).matches()
        		? new Success(arg, sendVerificationMail)
        	: new Failure(arg, "Adres e-mail " + arg + " jest niepoprawny.", logError);		  
			  
	  private static final ValidatorBuilder<String> builder = new ValidatorBuilder<String>() {
			@Override
			public <S, T> Validator<String> bindEffects(Effect<S> success, Effect<T> failure) {
				return validator; 
			}
	  };
	  
	  public static void main(String... args) {
		  builder.bindEffects(sendVerificationMail, logError).apply("to.jest@jakis.adres").effect();
		  builder.bindEffects(sendVerificationMail, logError).apply(null).effect();
		  Result result1 = builder.bindEffects(sendVerificationMail, logError).apply("");
		  Result result2 = builder.bindEffects(sendVerificationMail, logError).apply("jan.kowalski@test.com");
		  result2.effect();
		  result1.effect();
	  }
	  
}
