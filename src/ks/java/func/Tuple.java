package ks.java.func;

import java.util.Objects;

public class Tuple<T, U> {
	public final T _1;
	public final U _2;

	public Tuple(T t, U u) {
		this._1 = Objects.requireNonNull(t);
		this._2 = Objects.requireNonNull(u);
	}
}
