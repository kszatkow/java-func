package com.fpinjava.trees.exercise10_08;

import com.fpinjava.common.List;
import org.junit.Test;

import static org.junit.Assert.*;


public class TreeTest {

  /**
   * Nie używamy referencji do metod, aby był wyraźniej widać różnice.
   */

  @Test
  public void testFoldLeft() {
    List<Integer> list0 = List.list(4, 2, 1, 3, 6, 5, 7);
    List<Integer> list1 = Tree.tree(list0).foldLeft(List.list(), list -> a -> list.cons(a), x -> y -> y.concat(x));
    assertEquals(list0.toString(), list1.toString());
  }
}
