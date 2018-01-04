package com.fpinjava.recursion.exercise04_06;

import java.util.List;

import com.fpinjava.common.Function;
import static com.fpinjava.recursion.exercise04_05.FoldRight.*;

import static com.fpinjava.common.Function.*;

public class ComposeAll {

  static <T> Function<T, T> composeAll(List<Function<T, T>> list) {
    return foldRight(list, identity(), x -> y -> x.compose(y));
  }

  /*
   * Ponieważ funkcje są od T do T, kompozycję można zaimplementować
   * używając andThen zamiast compose.
   */
  static <T> Function<T, T> composeAllViaAndThen(List<Function<T, T>> list) {
    return foldRight(list, identity(), x -> y -> y.andThen(x));
  }
}
