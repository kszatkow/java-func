package com.fpinjava.state.exercise12_11;


import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import com.fpinjava.state.exercise12_10.StateTuple;

/**
 * Klasa reprezentuje wynik, który zostanie zwrócony przez maszynę stanową.
 * Nie jest konieczne użycie tego rodzaju klasy, ale to znacznie czystsze niż
 * korzystanie z krotek.
 */
public class Outcome {

  public final Integer account;
  public final List<Result<Integer>> operations;

  public Outcome(Integer account, List<Result<Integer>> operations) {
    super();
    this.account = account;
    this.operations = operations;
  }

  public String toString() {
    return "(" + account.toString() + "," + operations.toString() + ")";
  }

  public static Outcome add(StateTuple<Input, Outcome> t) {
    return new Outcome(t.value.getAmount().map(a -> t.state.account + a).getOrElse(t.state.account), t.state.operations.cons(t.value.getAmount()));
  }

  public static Outcome sub(StateTuple<Input, Outcome> t) {
    return new Outcome(t.value.getAmount().map(a -> t.state.account - a).getOrElse(t.state.account), t.state.operations.cons(t.value.getAmount().map(a -> -a)));
  }

  public static Outcome err(StateTuple<Input, Outcome> t) {
    return new Outcome(t.state.account, t.state.operations.cons(Result.failure("Insufficient balance")));
  }
}
