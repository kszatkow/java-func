## Pytania ogólne

###Pytanie: Nie zdefiniowałeś xyz. Co to oznacza?

Zawsze przeczytaj pełne wyjaśnienie przed próbą odpowiedzi na pytanie. Wiele tematów wymaga cyklicznych zależności między skłądowymi. Tego rodzaju wyjaśnienie trzeba przeczytać przynajmniej dwa razy. Weźmy następujący przykład:


		public class Case<T> {
		
		  public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
		    return new Case<>(condition, value);
		  }
		
		  public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value) {
		    return new DefaultCase<>(() -> true, value);
		  }

Zastanawiasz się co to za klasa DefaultCase, której jeszcze nie definiowaliśmy. Jej definicja jest następująca:


		private static class DefaultCase<T> extends Case<T> {
		  ...

Gdybyśmy zdefiniowali `DefaultCase` jako pierwszą, zastanawiałby się, co to `Case`. Przeczytanie całkego kodu rozwiewa wątpliwości:

		public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {
		
		  private Case(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
		    super(booleanSupplier, resultSupplier);
		  }
		
		  public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
		    return new Case<>(condition, value);
		  }
		
		  public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value) {
		    return new DefaultCase<>(() -> true, value);
		  }
		
		  private static class DefaultCase<T> extends Case<T> {
		
		    private DefaultCase(Supplier<Boolean> booleanSupplier, 
		                        Supplier<Result<T>> resultSupplier) {
		      super(booleanSupplier, resultSupplier);
		    }
		  }
		
		  @SafeVarargs
		  public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {
		    for (Case<T> aCase : matchers) {
		      if (aCase._1.get()) return aCase._2.get();
		    }
		    return defaultCase._2.get();
		  }
		}

Ponieważ nie zawsze można umieścić cały kod w jednej części książki, zawsze korzystaj z dołączonych przykładów.		

## Lambdy

###Pytanier: Jak utwórzyć instancję Xyz bez konkretnej implementacji?

Dla następującego interfejsu:

		public interface Effect<T> {
		  void apply(T t);
		}

Jak utworzyć jego egzemplarz w poniższej wersji?

    Effect<String> success = s -> System.out.println("E-mail wysłany do " + s);

To jest funkcja anonimowa (lambda, nowość w Javie 8), która jest mniej więcej równoważna poniższemu zapisowi:

		static Effect<String> success = new Effect<String>() {
		
		  @Override
		  public void apply(String s) {
		    System.out.println("Mail sent to " + s);
		  }
		  
		};

Wszystko jest opisane dokładnie w rozdziale 2., gdzie znajduje się dokładny proces krok po kroku dla zamiany klas anonimowych na funkcje anonimowe.

Obie formy są równoważne tylko z punktu widzenia programisty. Istnieją różnice w ich obsłudze przez kompilator.

###Pytanie: Jak w poniższym kodzie kompilator wie, że lambda powinna być zamieniona na Supplier<...> (lub cokolwiek innego)?

		public static <T> DefaultCase<T> mcase(Supplier<Result<T>> value) {
		  return new DefaultCase<>(() -> true, value);
		}


Lambda `() -> true` zostanie skompilowana do anoniowej klasy implementującej inferfejs funkcyjny z jedną metodą nieprzyjmującą parametrów (wskazuje na to `()`) i zwracającą `boolean`.

To, który interfejs wybrać, wskazuje konstruktor `DefaultCase`:

		private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
		  super(booleanSupplier, resultSupplier);
		}

Pierwszym parametrem musi być `Supplier<Boolean>`.

##Referencje do metod

Referencje do metod to lukier składniowy nałożony na lambdy. Gdy prawa część lambdy to wywołanie metody, która przyjmuje lewą część jako argument, czyli:

		map(x -> change(x))
		flatMap(x -> a.change(x))

można ją zastąpić referencją do metody:

		map(this::change)
		flatMap(a::change)

Jeśli metoda jest statyczna, po prostu użyj nazwy klasy:

		forEach(x -> System.out.println(x))

Powyższy zapis można zastąpić poniższym:

		forEach(System.out::println)

Działa to również dla konstruktorów:

		map(x -> new Person(x))

Można użyć poniższej formy:

		map(Person::new)

##Adnotacje typów

Wszyscy programiści Javy wiedzą, jak używać adnotacji typów na poziomie klasy. To najprostszy sposób użycia elementów generycznych. Adnotacje typów na poziomie klasy składają się z dwóch kategorii.

###Definicja klasy

Klasę można zdefiniować z adnotacją typu. Na przykład klasę `Comparator` dla tekstów można zdefiniować następująco:

		public class String Comparator implements Comparator<String> {
		  ...
		}

###Instancja klasy

Tworząc instancję klasy, możemy użyć adnotacji typu w następujący sposób:

		List<String> list = new ArrayList<String>();

Możemy nawet zastosować składnię rombową (od Javy 7), która upraszcza kod:

		List<String> list = new ArrayList<>();

Nie musimy powtarzać adnotacji typu po prawej stronie, ponieważ kompilator potrafi ją określić na podstawie lewe strony.

###Typy parametryzowane

Możemy zdefiniować `List` i `Comparator` w taki sposób, ponieważ klasy te są definiowane za pomocą parametrów typu:

		public class List<T> {
		  ...
		}
		
		public class Comparator<T> {
		  ...
		}

Tutaj `T` to zmienna typu. Ich zastosowanie czyni klasy generycznymi. Jedna definicja `List` może posłużyć do obsługi wszystkich list różnych typów -- wystarczy zastąpić `T` właściwym typem.

Wszystko to jest dobrze znane. Metody i pola klas mogą wykorzystywać przekazany typ. Na przykład klasę `Pair<T, U>` możemy zdefiniować w następujący sposób:

		public class Pair<T, U> {
		
		  private final T left;
		
		  private final U right;
		
		  public Pair(T t, U u) {
		    left = t;
		    right = u;
		  }
		
		  public T getLeft() {
		    return left;
		  }
		
		  public U getRight() {
		    return right;
		  }
		}

Zauważ, że oba pola (lewe i prawe), konstruktor i dwie metody dostępowe używają parametru typu `T`.

###Adnotacje typu w deklaracji metod

Metody trzeba czasem parametryzować parametrami typu, które nie znajdują się w zasięgu, co oznacza, że nie były deklarowane w klasie. Dotyczy to głównie metod statycznych, bo parametry typu nie są dostępne dla tych metod. 

Deklaracje metod statycznych można adnotować typami. Aby zdefiniować statycznąmetodę fabryczną dla klasy `Pair` możemy napisać:

		public static <T, U> Pair<T, U> create(T t, U u) {
		  return new Pair<>(t, u);
		}

Zauważ użycie notacji rombowej, co jest możliwe, bo Java jest w stanie określić typ `Pair` do utworzenia na podstawie sygnatury metody.

Metody instancji również można adnotować. Na przykład, możemy zdefiniować metodę `mapLeft` do przekształcania lewej wartości pary przez zastosowanie dla niej funkcji:

		public <V> Pair<V, U> mapLeft(Function<T, V> f) {
		  return new Pair<>(f.apply(left), right);
		}

###Adnotacje typu dla wywołań metod

Inferencja typów jest najczęściej używana przez Javę w dereferencji metod i pól. Czasem Java nie potrafi odnadnąć właściwego typu, czy to dlatego, że nie jest to możliwe (za mało informacji), czy też sam mechanizm zgadywania działa w Javie słabo w przypadku dereferencji metod i pól. Na przykład, funkcyjna klasa `List` może zdefiniować metodę zwracającą pustą listę, na przykład `List.list()`. Pusta lista najczęściej stosuje typ surowy, co oznacza, że nie ma on adnotacji. Tę samą pustą listę można zastosować dla pustej listy elementów `String` lub pustej listy elementów `Integer`. Aby jej użyć, musimy jednak nadać jej typ. Java mogłaby odgadnąć typ, jak w poniższym mechaniźmie:

		List<String> list = List.list();

Jednak w pewnych sytuacjach Java nie potrafi odgadnąć typu, jak w poniższym kodzie:

		public static <A> Function<A, Function<List<A>, A>> headOr() {
		  return foldRight().apply(constant());
		}

Java nie potrafi odgadnąć typu w momencie wywoływanie metody instancji `foldRight`. PRzykłądu nie uda się skompilować. Pojawi się błąd:

		Error:(113, 27) java: incompatible types: funclib.Function<java.lang.Object,funclib.Function<exercises.List<java.lang.Object>,java.lang.Object>> cannot be converted to funclib.Function<A,funclib.Function<exercises.List<A>,A>>

Java nie potrafi odgadnąć typu i używa `Object`. Aby to naprawić, musimy dodać adnotację do wywołania metody:

		public static <A> Function<A, Function<List<A>, A>> headOr() {
		  return List.<A, A>foldRight().apply(constant());
		}

Adnotacje typu metody zapisujemy po kropce, więc musimy dodać nazwę klasy (lub inną referencję dla metod instancji), aby móc użyć kropki. Poniższy kod się nie skompiluje:

		public static <A> Function<A, Function<List<A>, A>> headOr() {
		  return <A, A>foldRight().apply(constant());
		}

###Adnotacje typu pola

W imperatywnej Javie bardzo rzadko (jeśli w ogóle) przydaje się dodawać adnotacje dla typów. W zasadzie to adnotacje typu pola tak naprawdę w Javie nie istnieją. W funkcyjnej Javie chcemy czasem zadeklarować funkcje jako właściwości statyczne. Jeśli funkcja musi używać adnotacji typów, do reprezentacji tych właściwości nie możemy użyć pól. Musimy użyć metod (patrz poniżej).

###Czym są właściwości?

Jak wskazuje dokumentacja Javy, właściwość to charekterystyka obiektu, którą można odczytać lub zapisać. W imperatywnej Javie właściwości można często zapisywać i odczytywać, więc najczęściej używa się prywatnych pól z metodami dostępowymi.

W funkcyjnej Javie właściwości są niezmienne, więc nie potrzebujemy metod ustawiających. Właściwości można zaimplementować jako publiczne pola finalne lub prywatne pola finalne z metodami je pobierającymi. Właściwości najczęściej inicjalizuje się w konstruktorze lub w momencie deklaracji z wartością literałową. W tym drugim przypadku mówimy o właściwości literałowej (stałej), bo właściwośćjest niezmienna.

Aby zdefiniować funkcję jako stałą literałową, chcielibyśmy napisać:

		public static <T, U> Function<T, U> function = x -> …;

Nie możemy tego jednak zrobić, bo bo adnotacja typu dla pól jest niedozwolona. Rozwiązaniem jest implementacja stałej jako metody zwracającej wartość literałową (zdefiniowaną w ciele metody).

		public static <T, U> Function<T, U> function() {
		  return x -> …;
		}

Tutaj `function()` to nie funkcja. To metoda zwracająca funkcję. Aby użyć funkcji, zamiast pisać:

		T argument = ...
		U result = function.apply(argument);

musimy napisać:

		T argument = ...
		U result = function().apply(argument);