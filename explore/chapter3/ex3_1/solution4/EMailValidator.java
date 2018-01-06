package chapter3.ex3_1.solution4;

import ks.java.func.Function;

public interface EMailValidator<S, T, V> extends 
	Function<Effect<S>, Function<Effect<T>, Function<V, Result>>> {

}
