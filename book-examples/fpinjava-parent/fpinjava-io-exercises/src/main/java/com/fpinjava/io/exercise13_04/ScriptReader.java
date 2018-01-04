package com.fpinjava.io.exercise13_04;

import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import com.fpinjava.common.Tuple;

public class ScriptReader implements Input {

  private final List<String> commands;

  public ScriptReader(List<String> commands) {
    super();
    this.commands = commands;
  }

  public ScriptReader(String... commands) {
    super();
    this.commands = List.list(commands);
  }

  /*
   * W przypadku błędu, ponieważ nie ma wystarczająco dużo wpisów w skrypcie,
   * tak naprawdę zobaczymy go do przetrworzeniu wszystkich wpisów.
   * Choć ostatni błąd wynika z pustego skryptu, sygnalizujemy:
   * Nie masz wystarczająco dużo wpisów w skrypcie.
   */
  @Override
  public Result<Tuple<String, Input>> readString() {
    return commands.isEmpty()
        ? Result.failure("Nie masz wystarczająco dużo wpisów w skrypcie.")
        : Result.success(new Tuple<>(commands.headOption().getOrElse(""), new ScriptReader(commands.drop(1))));
  }

  @Override
  public Result<Tuple<Integer, Input>> readInt() {
    try {
      return commands.isEmpty()
          ? Result.failure("Nie masz wystarczająco dużo wpisów w skrypcie.")
          : Integer.parseInt(commands.headOption().getOrElse("")) >= 0
              ? Result.success(new Tuple<>(Integer.parseInt(commands.headOption().getOrElse("")), new ScriptReader(commands.drop(1))))
              : Result.empty();
    } catch(Exception e) {
      return Result.failure(e);
    }
  }
}
