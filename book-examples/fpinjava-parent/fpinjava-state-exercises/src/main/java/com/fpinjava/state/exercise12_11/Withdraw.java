package com.fpinjava.state.exercise12_11;


import com.fpinjava.common.Result;

public class Withdraw implements Input {

  private final Result<Integer> amount;

  public Withdraw(Result<Integer> amount) {
    super();
    this.amount = amount;
  }

  public Withdraw(Integer amount) {
    super();
    this.amount = Result.success(amount);
  }

  @Override
  public Type type() {
    throw new IllegalStateException("Do zaimplementowania");
  }

  @Override
  public boolean isDeposit() {
    throw new IllegalStateException("Do zaimplementowania");
  }

  @Override
  public boolean isWithdraw() {
    throw new IllegalStateException("Do zaimplementowania");
  }
}
