package com.fpinjava.makingjavafunctional.listing03_11;

import java.util.ArrayList;
import java.util.List;

import com.fpinjava.makingjavafunctional.listing03_10.OrderLine;
import com.fpinjava.makingjavafunctional.listing03_10.Product;

public class StoreImperative {

  public static void main(String[] args) {

    Product toothPaste = new Product("Pasta do zębów", 3.5, 0.5);
    Product toothBrush = new Product("Szczoteczka do zębów", 9.5, 0.3);

    List<OrderLine> order = new ArrayList<>();
    order.add(new OrderLine(toothPaste, 2));
    order.add(new OrderLine(toothBrush, 3));

    double price = 0.0;
    double weight = 0.0;
    for (OrderLine orderLine : order) {
      weight += orderLine.getAmount();
      price += orderLine.getWeight();
    }
    System.out.println(String.format("Łączna cena: %s", price));
    System.out.println(String.format("Łączna waga: %s", weight));
  }
}
