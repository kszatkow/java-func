package chapter3.ex3_1.solution4;

public abstract class Result {

  protected final String eMail;
  
  protected Result(String eMail) {
	  this.eMail = eMail;
  }
	
  public abstract void effect();
  
}
