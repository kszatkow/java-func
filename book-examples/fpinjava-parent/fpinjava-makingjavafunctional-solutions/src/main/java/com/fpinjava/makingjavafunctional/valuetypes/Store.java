package com.fpinjava.makingjavafunctional.valuetypes;

import static com.fpinjava.common.CollectionUtilities.*;
import static com.fpinjava.makingjavafunctional.valuetypes.Price.*;
import static com.fpinjava.makingjavafunctional.valuetypes.Weight.*;

import java.util.List;

public class Store {

  public static void main(String[] args) {

    Product toothPaste = new Product("Pasta do zębów", price(3.5), weight(0.5));
    Product toothBrush = new Product("Szczoteczka do zębów", price(9.5), weight(0.3));
    
    List<OrderLine> order = list(
        new OrderLine(toothPaste, 2), 
        new OrderLine(toothBrush, 3));
        
    Price price = foldLeft(order, Price.ZERO, Price.sum);
    Weight weight = foldLeft(order, Weight.ZERO, Weight.sum);
    
    System.out.println(String.format("Łączna cena: %s", price));
    System.out.println(String.format("Łączna waga: %s", weight));

  }
}