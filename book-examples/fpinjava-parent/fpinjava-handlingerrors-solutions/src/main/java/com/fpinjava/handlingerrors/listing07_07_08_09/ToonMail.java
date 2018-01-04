package com.fpinjava.handlingerrors.listing07_07_08_09;


import com.fpinjava.handlingerrors.listing07_06.Result;

import java.io.IOException;

public class ToonMail {

  public static void main(String[] args) {
    Map<String, Toon> toons = new Map<String, Toon>()
        .put("Mickey", new Toon("Myszka", "Miki", "mickey@disney.com"))
        .put("Minnie", new Toon("Myszka", "Minnie"))
        .put("Donald", new Toon("Kaczor", "Donald", "donald@disney.com"));

    Result<String> result = getName().flatMap(toons::get).flatMap(Toon::getEmail);

    System.out.println(result);

  }

  public static Result<String> getName() {
    return Result.success("Mickey");
    //return Result.failure(new IOException("Błąd wejścia"));
    //return Result.success("Minnie");
    //return Result.success("Goofy");
  }
}
