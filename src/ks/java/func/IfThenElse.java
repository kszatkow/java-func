package ks.java.func;

public class IfThenElse {

	public static <T, U> Function<Boolean, Function<T, U>> ifThenElse(Function<T, U> thenExpr, Function<T, U> elseExpr) {
		return cond -> cond ? thenExpr : elseExpr;
	}
	
	public static <T, U> Function<T, U> ifThenElse(Function<T, Boolean> cond, Function<T, U> thenExpr, Function<T, U> elseExpr) {
		return x -> cond.apply(x) ? thenExpr.apply(x) : elseExpr.apply(x);
	}
	
}
