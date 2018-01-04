package com.fpinjava.actors.listing14_12;

import com.fpinjava.common.List;
import com.fpinjava.common.Result;
import com.fpinjava.state.SimpleRNG;

import java.util.concurrent.Semaphore;

public class WorkersExample {

  private static final Semaphore semaphore = new Semaphore(1);
  private static int listLength = 200_000;
  private static int workers = 4;
  private static final List<Integer> testList = SimpleRNG.doubles(listLength, new SimpleRNG.Simple(3))._1.map(x -> (int) (x * 30)).reverse();

  public static void main(String... args) throws InterruptedException {
    semaphore.acquire();
    long time = System.currentTimeMillis();
    final AbstractActor<List<Integer>> client = new AbstractActor<List<Integer>>("Klient", Actor.Type.SERIAL) {

      @Override
      public void onReceive(List<Integer> message, Result<Actor<List<Integer>>> sender) {
        System.out.println("Łączny czas: " + (System.currentTimeMillis() - time));
        System.out.println("Wynik: " + message.takeAtMost(40));
        semaphore.release();
      }
    };

    final Receiver receiver = new Receiver("Odbiorca", Actor.Type.SERIAL, client);
    final Manager manager = new Manager("Menedżer", testList, receiver, workers);
    manager.start();
    semaphore.acquire();
  }

  private static void processFailure(String s) {
    System.out.println(s);
  }

  public static void processSuccess(List<Integer> lst) {
    System.out.println("Wynik: " + lst.takeAtMost(40));
  }
}
