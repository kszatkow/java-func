package com.fpinjava.state.exercise12_10;


import com.fpinjava.common.List;

/**
 * Klasa reprezentuje wynik, który zostanie zwrócony przez maszynę stanową.
 * Nie jest konieczne użycie tego rodzaju klasy, ale to znacznie czystsze niż
 * korzystanie z krotek.
 */
public class Outcome {

  public final Integer account;
  public final List<Integer> operations;

  public Outcome(Integer account, List<Integer> operations) {
    super();
    this.account = account;
    this.operations = operations;
  }

  public String toString() {
    return "(" + account.toString() + "," + operations.toString() + ")";
  }
}
