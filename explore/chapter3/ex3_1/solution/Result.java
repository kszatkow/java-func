package chapter3.ex3_1.solution;

public abstract class Result {

  protected final String eMail;
  
  protected Result(String eMail) {
	  this.eMail = eMail;
  }
	
  public abstract Thunk getEffect();
  
}
