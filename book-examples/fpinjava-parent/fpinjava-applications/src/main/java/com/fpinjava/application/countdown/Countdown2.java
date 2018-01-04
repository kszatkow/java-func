package com.fpinjava.application.countdown;


import com.fpinjava.common.Executable;
import com.fpinjava.common.List;
import com.fpinjava.common.Nothing;
import com.fpinjava.common.Result;
import com.fpinjava.common.Tuple;
import com.fpinjava.io.Console;
import com.fpinjava.io.IO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Pełna wersja z funkcją wyświetlania. To bardzo brzydka implementacja.
 * Możemy napisać znacznie ładniejszą funkcję wyświetlającą.
 */
public class Countdown2 {

  private static IO<Long> getCpuTime() {
    return getCpuTime(Nothing.instance);
  }

  public static IO<Long> getCpuTime(Nothing nothing) {
    return System::currentTimeMillis;
  }

  private static Result<Integer> read(String s) {
    return Result.of(() -> Integer.valueOf(s));
  }

  private static Result<List<Integer>> readNumbers(String s) {
    return List.sequence(List.words(s).map(Countdown2::read));
  }

  private static abstract class Op {
    abstract boolean valid(int x, int y);
    abstract boolean valid_(int x, int y);
    abstract int apply(int x, int y);
    public static Op add = new Add();
    public static Op sub = new Sub();
    public static Op mul = new Mul();
    public static Op div = new Div();
    public static List<Op> ops = List.list(add, sub, mul, div);
  }

  private static class Add extends Op {
    @Override
    boolean valid(int x, int y) {
      return true;
    }

    @Override
    boolean valid_(int x, int y) {
      return x <= y;
    }

    @Override
    int apply(int x, int y) {
      return x + y;
    }

    @Override
    public String toString() {
      return "+";
    }
  }

  private static class Sub extends Op {
    @Override
    boolean valid(int x, int y) {
      return x > y;
    }

    @Override
    boolean valid_(int x, int y) {
      return x > y;
    }

    @Override
    int apply(int x, int y) {
      return x - y;
    }

    @Override
    public String toString() {
      return "-";
    }
  }

  private static class Mul extends Op {
    @Override
    boolean valid(int x, int y) {
      return true;
    }

    @Override
    boolean valid_(int x, int y) {
      return x != 1 && y != 1 && x <= y;
    }

    @Override
    int apply(int x, int y) {
      return x * y;
    }

    @Override
    public String toString() {
      return "*";
    }
  }

  private static class Div extends Op {
    @Override
    boolean valid(int x, int y) {
      return x % y == 0;
    }

    @Override
    boolean valid_(int x, int y) {
      return y != 1 && x % y == 0;
    }

    @Override
    int apply(int x, int y) {
      return x / y;
    }

    @Override
    public String toString() {
      return "/";
    }
  }

  private static int apply(Op op, int x, int y) {
    return op.apply(x, y);
  }

  private static boolean valid(Op op, int x, int y) {
    return op.valid(x, y);
  }

  private static abstract class Expr {

    public abstract List<Integer> values();
    public abstract List<Integer> eval();

    public static Expr expr(int n) {
      return new Simple(n);
    }

    public static Expr expr(Op op, Expr expr1, Expr expr2) {
      return new Composite(op, expr1, expr2);
    }

    public static class Simple extends Countdown2.Expr {
      public final int value;

      private Simple(int value) {
        this.value = value;
      }

      @Override
      public List<Integer> values() {
        return List.list(value);
      }

      @Override
      public List<Integer> eval() {
        return value > 0 ? List.list(value) : List.list();
      }

      @Override
      public String toString() {
        return Integer.toString(value);
      }
    }

    public static class Composite extends Countdown2.Expr {
      public final Op op;
      public final Countdown2.Expr l;
      public final Countdown2.Expr r;

      private Composite(Op op, Countdown2.Expr x, Countdown2.Expr y) {
        this.op = op;
        this.l = x;
        this.r = y;
      }

      @Override
      public List<Integer> values() {
        return l.values().concat(r.values());
      }

      @Override
      public String toString() {
        return String.format("(%s %s %s)", l, op, r);
      }

      /*
       * To przykład dlaczego dla prawdziwie opcjonalncyh danych pusta lista jest często lepsza niż obiekt Optional.
       */
      @Override
      public List<Integer> eval() {
        return l.eval().flatMap(x -> r.eval().flatMap(y -> valid(op, x, y) ? List.list(apply(op, x, y)) : List.list()));
      }
    }
  }

  //  Funkcje kombinacyjne.
  //  ---------------------

  private static <A> List<List<A>> subs(List<A> list) {
    return list.subLists();
  }

  private static <A> List<List<A>> interleave(A a, List<A> list) {
    return list.interleave(a);
  }

  private static <A> List<List<A>> perms(List<A> list) {
    return list.perms();
  }

  private static <A> List<List<A>> choices(List<A> list) {
    return list.choices();
  }

  // Formalizacja problemu.
  // ----------------------

  private static boolean solution(Expr e, List<Integer> ns, int n) {
    return ns.choices().elem(e.values()) && e.eval().equals(List.list(n));
  }

  // Rozwiązanie siłowe.
  // -------------------

  private static <A> List<Tuple<List<A>, List<A>>> split(List<A> list) {
    return list.split();

  }

  private static List<Expr> exprs(List<Integer> list) {
    return list.isEmpty()
        ? List.list()
        : list.length() == 1
            ? List.list(new Expr.Simple(list.headOption().getOrElse(-1))) // domyślna wartości  nie zostanie nigdy użyta
            : split(list).flatMap(t -> exprs(t._1).flatMap(l -> exprs(t._2).flatMap(r -> combine(l, r))));
  }

  private static List<Expr> combine(Expr expr1, Expr expr2) {
    return Op.ops.map(op -> Expr.expr(op, expr1, expr2));
  }

  private static List<Expr> solutions(List<Integer> list, int n) {
    return choices(list).flatMap(list2 -> exprs(list2).filter(e -> e.eval().equals(List.list(n))));
  }

  // Łączenie generacji i ewaluacji.
  // -------------------------------

  static class Solution {
    public final Expr expr;
    public final int value;

    Solution(Expr expr, int value) {
      this.expr = expr;
      this.value = value;
    }
  }

  private static List<Solution> results(List<Integer> ns) {
    return ns.isEmpty()
        ? List.list()
        : ns.length() == 1
            ? ns.flatMap(n -> n > 0 ? List.list(new Solution(Expr.expr(n), n)) : List.list())
            : split(ns).flatMap(t1 -> results(t1._1).flatMap(lx -> results(t1._2).flatMap(ry -> combine(lx, ry))));
  }

  private static List<Solution> combine(Solution lx, Solution ry) {
    return Op.ops.filter(op -> op.valid(lx.value, ry.value)).map(op -> new Solution(Expr.expr(op, lx.expr, ry.expr), apply(op, lx.value, ry.value)));
  }

  private static List<Expr> solution_(List<Integer> ns, int n) {
    return choices(ns).flatMap(ns2 -> results(ns2).filter(solution -> solution.value == n).map(solution -> solution.expr));
  }

  private static List<Solution> results_(List<Integer> ns) {
    return ns.isEmpty()
        ? List.list()
        : ns.length() == 1
            ? ns.flatMap(n -> n > 0 ? List.list(new Solution(Expr.expr(n), n)) : List.list())
            : split(ns).flatMap(t1 -> results_(t1._1).flatMap(lx -> results(t1._2).flatMap(ry -> combine_(lx, ry))));
  }

  private static List<Solution> combine_(Solution lx, Solution ry) {
    return Op.ops.filter(op -> op.valid_(lx.value, ry.value)).map(op -> new Solution(Expr.expr(op, lx.expr, ry.expr), apply(op, lx.value, ry.value)));
  }


  private static List<Expr> solutions_(List<Integer> ns, int n) {
    return choices(ns).flatMap(ns_ -> results_(ns_).filter(e -> e.value == n)).map(solution -> solution.expr);
  }

  private static String showTime(long l) {
    return String.format("%s ms.", l);
  }

  private static IO<Nothing> sayHello() {
    return Console.printLine("Wpisz imię: ")
                  .flatMap(Console::readLine)
                  .map(Countdown2::buildMessage)
                  .flatMap(Console::printLine);
  }

  private static String buildMessage(String s) {
    return s;
  }

  private static IO<Nothing> display(Result<List<Expr>> res) {
    long t0 = getCpuTime().run();
    res.forEachOrFail(es -> {
      if (es.isEmpty()) {
        long t1 = getCpuTime().run();
        System.out.print("\nNie ma rozwiązań, zweryfikowano w ");
        System.out.print(showTime(t1 - t0));
      } else {
        long t1 = getCpuTime().run();
        System.out.print("\nJedynym możliwym rozwiązaniem jest ");
        System.out.print(es.first(x -> true));
        System.out.print(", found in ");
        System.out.print(showTime (t1 - t0));
        System.out.print("\n\nNaciśnij Enter, aby kontynuować wyszukiwania...");
        Console.readLine().run();
        System.out.print("\n");
        long t2 = getCpuTime().run();
        Result<List<Expr>> tailR = es.tailOption();
        if (tailR.isEmpty()) {
          System.out.print("Nie ma więcej rozwiązań");
        } else {
          tailR.forEach(tail -> {
            System.out.print("\nJest łącznie ");
            System.out.print(tail.length() + 1);
            System.out.print(" rozwiązań, znalezione w ");
            long t3 = getCpuTime().run();
            System.out.print(showTime((t1 - t0) + (t3 - t2)));
          });
        }
      }
    }).forEach(System.out::println);
    System.out.println("\n");
    return () -> Nothing.instance;
  }

  public static void main(String... args) {
    doMain().run();
  }

  private static IO<Nothing> doMain() {
    return Console.printLine("\nROZWIĄZYWANIE GRY W ODLICZANIE") // wyświetl pierwszy wiersz daych informacyjnych
                  .flatMap(x -> Console.printLine("-----------------------------\n")) // wyświetl drugi wiersz
                  .flatMap(x -> Console.print("Wpisz przekazane liczby: ")) // wyświetl pierwsze zapytanie
                  .flatMap(Console::readLine) // odczytaj listę liczb jako tekst
                  .map(Countdown2::readNumbers) // zamień na wynik będący listą liczb całkowitych
                  .flatMap(ns -> Console.print("Wpisz liczbę docelową: ") // wyświetl drugie zapytanie
                                        .flatMap(Console::readLine) // odczytaj liczbę w postaci tekstu
                                        .map(Countdown2::read) // zamień wynik na liczbę całkowitą
                                        .map(n -> ns.flatMap(ns_ -> n.map(n_ -> solutions_(ns_, n_))))) // oblicz wynik jak listę elementów Expr
                  .flatMap(Countdown2::display); // wyświetl rozwiązanie
  }

  /**
   * Użycie monada IO. Ta wersja zwraca instancję monada IO, którą można uruchomić lub połączyć z innymi instancjami.
   * Zaletą jest możliwość kompozycji przy użyciou wielu metod takich jak: map, flatMap, map2, repeat, when, doWhile, forever, itp.
   * @return Instancja monada IO, którą można uruchomić.
   */
  private static IO<Nothing> main0_() {
    return Console.printLine("\nROZWIĄZYWANIE GRY W ODLICZANIE") // wyświetl pierwszy wiersz daych informacyjnych
                  .flatMap(x -> Console.printLine("-----------------------------\n")) // wyświetl drugi wiersz
                  .flatMap(x -> Console.print("Wpisz przekazane liczby: ")) // wyświetl pierwsze zapytanie
                  .flatMap(Console::readLine) // odczytaj listę liczb jako tekst
                  .map(Countdown2::readNumbers) // zamień na wynik będący listą liczb całkowitych
                  .flatMap(ns -> Console.print("Wpisz liczbę docelową: ") // wyświetl drugie zapytanie
                                        .flatMap(Console::readLine) // odczytaj liczbę w postaci tekstu
                                        .map(Countdown2::read) // zamień wynik na liczbę całkowitą
                                        .map(n -> ns.flatMap(ns_ -> n.map(n_ -> solutions_(ns_, n_))))) // oblicz wynik jak listę elementów Expr
                  .flatMap(Console::printLine); // wyświetl rozwiązanie
  }

  /**
   * Zdekomponowana wersja używająca monada IO i pokazująca, jak poszczególne kroki obliczeniowe zapisuje się w postaci
   * monadowej, a następnie łączy. Zauważ, że każdy pośredni monad IO może być uruchomiony niezależnie.
   *
   * @return Instancja monada IO, którą można uruchomić.
   */
  private static IO<Nothing> main1_() {
    IO<Nothing> title = Console.printLine("\nROZWIĄZYWANIE GRY W ODLICZANIE") // wyświetl pierwszy wiersz daych informacyjnych
                               .flatMap(x -> Console.printLine("-----------------------------\n"));

    IO<Result<List<Integer>>> numbers = Console.print("Wpisz przekazane liczby: ") // wyświetl pierwsze zapytanie
                                       .flatMap(Console::readLine) // odczytaj listę liczb jako tekst
                                       .map(Countdown2::readNumbers); // zamień na wynik będący listą liczb całkowitych

    IO<Result<Integer>> number = Console.print("Wpisz liczbę docelową: ") // wyświetl drugie zapytanie
                                .flatMap(Console::readLine) // odczytaj liczbę w postaci tekstu
                                .map(Countdown2::read); // zamień wynik na liczbę całkowitą

    IO<Result<List<Expr>>> exprs = numbers.flatMap(ns -> number.map(n -> ns.flatMap(ns_ -> n.map(n_ -> solutions_(ns_, n_))))); // oblicz wynik jak listę elementów Expr

    return title.flatMap(x -> exprs.flatMap(Console::printLine)); // wyświetl rozwiązanie
  }

  /**
   * Wersja używająca imperatywnej Javy. Można jąkomponować za pomocą technik imperatywnych takich jak wywołania
   * sekwencyjne, wykonywanie warunkowe (if...else, switch...case) lub pętle.
   */
  public static void main2_() {
    System.out.println("\nROZWIĄZYWANIE GRY W ODLICZANIE");
    System.out.println("-----------------------------\n");
    Result<List<Integer>> ns = Console_.readLine("Wpisz przekazane liczby: ").flatMap(Countdown2::readNumbers);
    Result<Integer> n = Console_.readLine("Wpisz liczbę docelową: ").flatMap(Countdown2::read);
    Result<List<Expr>> result = ns.flatMap(ns_ -> n.map(n_ -> solutions_(ns_, n_)));
    result.forEachOrFail(System.out::println).forEach(System.out::println);
  }

  /**
   * Użycie imperatywnej Javy w otoczce wykonywalnej. To dokładnie ta sama wersja, co kod imperatywny,
   * ale umożliwia produkcję wyniku jako danych (wykonawczych) zamiast efektu. To bardziej funkcyjne podejście,
   * bo umożliwia przekazanie wyniku do innego programu, który go wykona.
   *
   * @return Executable zawierający imperatywną Javę.
   */
  public static Executable main3_() {
    return () -> {
      System.out.println("\nROZWIĄZYWANIE GRY W ODLICZANIE");
      System.out.println("-----------------------------\n");
      Result<String> ns = Console_.readLine("Wpisz przekazane liczby: ");
      Result<String> n = Console_.readLine("Wpisz liczbę docelową: ");
      Result<List<Expr>> result = ns.flatMap(Countdown2::readNumbers).flatMap(ns_ -> n.flatMap(Countdown2::read).map(n_ -> solutions_(ns_, n_)));
      result.forEachOrFail(System.out::println).forEach(System.out::println);
    };
  }

  /**
   * Kompozycja imperatywnych instrukcji w sposób funkcyjny poza instrukcjami zwracającymi void.
   * Nie ma większych zalet, bo jest wewnętrzne dla metody i niewidoczne na zewnątrz.
   * Jest tylko nieco bardziej złożone w czytaniu i pisaniu.
   *
   * @return Executable stanowiący mieszankę kodu imperatywnego i funkcyjnego.
   */
  public static Executable main4_() {
    return () -> {
      System.out.println("\nROZWIĄZYWANIE GRY W ODLICZANIE");
      System.out.println("-----------------------------\n");
      Console_.readLine("Wpisz przekazane liczby: ").flatMap(Countdown2::readNumbers)
              .flatMap(ns -> Console_.readLine("Wpisz liczbę docelową: ").flatMap(Countdown2::read).map(n -> solutions_(ns, n)))
              .forEachOrFail(System.out::println)
              .forEach(System.out::println);
    };
  }

  /**
   * Kompozycja imperatywnych instrukcji w sposób funkcyjny włącznie z instrukcjami zwracającymi void.
   * Nie ma większych zalet, bo jest wewnętrzne dla metody i niewidoczne na zewnątrz.
   * Jest tylko nieco bardziej złożone w czytaniu i pisaniu.
   *
   * @return Executable zawierający kod funkcyjny.
   */
  public static Executable main5_() {
    return () -> {
      Console_.printLine("\nROZWIĄZYWANIE GRY W ODLICZANIE")
              .flatMap(x -> Console_.printLine("-----------------------------\n"))
              .flatMap(x -> Console_.readLine("Wpisz przekazane liczby: ").flatMap(Countdown2::readNumbers)
                                    .flatMap(ns -> Console_.readLine("Wpisz liczbę docelową: ")
                                                           .flatMap(Countdown2::read).map(n -> solutions_(ns, n))))
              .forEachOrFail(System.out::println)
              .forEach(System.out::println);
    };
  }

  static class Console_ {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static Result<String> readLine() {
      return Result.of(() -> br.readLine(), "Błąd w trakcie odczytu z konsoli");
    }

    public static Result<String> readLine(String prompt) {
      System.out.print(prompt);
      return Result.of(() -> br.readLine(), "Błąd w trakcie zapisu do konsoli");
    }

    public static Result<Nothing> printLine(String s) {
      return Result.of(() -> {
        System.out.println(s);
        return Nothing.instance;
      });
    }
  }
}
