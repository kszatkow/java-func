package chapter3.ex3_1.solution;

public class Success extends Result {

  public Success(String eMail) {
	  super(eMail);
  }

  void sendVerificationMail() { 
	  System.out.println("E-mail weryfikacyjny wysłany do " + eMail);
  }
	
  @Override
  public Thunk getEffect() {
	  return () -> sendVerificationMail();
  }
  
}