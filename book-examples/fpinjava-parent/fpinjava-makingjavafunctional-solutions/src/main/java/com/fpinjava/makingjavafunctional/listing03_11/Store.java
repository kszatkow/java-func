package com.fpinjava.makingjavafunctional.listing03_11;


import java.util.List;

import static com.fpinjava.common.CollectionUtilities.*;
import com.fpinjava.makingjavafunctional.listing03_10.OrderLine;
import com.fpinjava.makingjavafunctional.listing03_10.Product;

public class Store {

  public static void main(String[] args) {

    Product toothPaste = new Product("Pasta do zębów", 3.5, 0.5);
    Product toothBrush = new Product("Szczoteczka do zębów", 9.5, 0.3);

    List<OrderLine> order = list(
        new OrderLine(toothPaste, 2), 
        new OrderLine(toothBrush, 3));

    double weight = foldLeft(order, 0.0, x -> y -> x + y.getAmount());
    double price = foldLeft(order, 0.0, x -> y -> x + y.getWeight());

    System.out.println(String.format("Łączna cena: %s", price));
    System.out.println(String.format("Łączna waga: %s", weight));
  }
}