package chapter3.ex3_1.solution;

public class Failure extends Result {

    private final String errorMessage;

    public Failure(String eMail, String errorMessage) {
    	super(eMail);
      	this.errorMessage = errorMessage;
    }

    public String getMessage() {
      return errorMessage;
    }

    private void logError() { 
    	System.err.println("Komunikat błędu umieszczony w dzienniku zdarzeń: " + errorMessage); 
    }
	    
    
	@Override
	public Thunk getEffect() {
		return () -> logError();
	}
  }