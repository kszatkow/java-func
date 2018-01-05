package chapter3.ex3_1.solution3;

public class Success extends Result {

	private final Effect<String> effect;
	
	public Success(String eMail, Effect<String> effect) {
		super(eMail);
		this.effect = effect;
	}

	@Override
	public void effect() {
		effect.effect(eMail);
	}
  
}