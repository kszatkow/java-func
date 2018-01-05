package chapter3.ex3_1.solution3;

public class Failure extends Result {

	private final String errorMessage;
	
    private final Effect<String> effect; 

    public Failure(String eMail, String errorMessage, Effect<String> effect) {
    	super(eMail);
      	this.errorMessage = errorMessage;
      	this.effect = effect;
    }

	@Override
	public void effect() {
		effect.effect(errorMessage);
	}
  }