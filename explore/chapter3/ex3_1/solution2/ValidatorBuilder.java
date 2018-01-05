package chapter3.ex3_1.solution2;

public interface ValidatorBuilder<V> {

	<S, T> Validator<V> bindEffects(Effect<S> success, Effect<T> failure);
}
