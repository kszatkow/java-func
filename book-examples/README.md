## Jak korzystać z projektu

Kod to projekt typu Gradle, który można wykorzystać:
- wykonując polecenia Gradle w terminalu,
- importując go do Eclipse (z ograniczeniami),
- importując do go IntelliJ,
- importując do do NetBeans.

Oczywiście można też wykozystać same pliki źródłowe i zaimportować je do dowolnego edytora lub skompilować i uruchomić z poziomu wiersza poleceń.
Nie będę tu opisuwał użycia poleceń Gradle. Jeśli wybierasz tę drogę, powinieneś wiedzieć, co robisz.

## Import do Eclipse

Uwaga: Eclipse nie jest w pełni zgodny z Javą. Nie korzysta z kompilatora Oracle. Kompilator używany w Eclipse nie jest w pełni zgodny z kompilatorem Javy 8 od Oracle.
Może zmieni się to w pzyszłości, ale obecnie nie wszystkie rozdziały działają poprawnie w środowisku Eclipse. Eclipse Luna pozwala na edycję i uruchomienie kodu do rozdziału 8.
Nie można korzystać z Eclipse Mars. Nie mam wiedzy, kiedy wszystko będzie działać poprawnie.

Aby zaimportować projekt do Eclipse, najpierw zainstaluj plug-in Gradle. W tym celu:

1. Wybierz **Help > Eclipse** MarketPlace.
1. W polu **Find** wpisz **Gradle**.
1. Kliknij przycisk **Go**.
1. Wybierz plug-in **Gradle integration for Eclipse** i kliknij przycisk **Install**.

Zaakceptuj instalację niezweryfikowanego oprogramowania i uruchom ponownie Eclipse.

### Import projektu

Zaimportuj nadrzędny projekt `fpinjava-parent` do Eclipse:

1. Wybierz **File > Import > Gradle > Gradle Project**.
1. W oknie dialogowym **Import Gradle** kliknij przycisk **Browse**, przejdź do folderu, w którym umieściłeś projekt i wybierz folder `fpinjava-parent`.
1. Kliknij przycisk **Build Model**.
1. W oknie dialogowym sprawdź, czy wszystkie moduły są zaznaczone. Jeśli nie, wybór modułu nadrzędnego automatycznie wybierze podmoduły.
1. Pozostaw wszystkie standardowe ustawienia bez zmian i kliknij przycisk **Finish**. Projekt powinien być zaimportowany.

## Import do IntelliJ

Aby zaimportować projekt do intelliJ:

1. Wybierz **File > Import project**.
1. W oknie dialogowym **Select file or directory to import** przejdź do folderu, w którym znajduje się repozytorium i wybierz folder `fpinjava-parent`.
1. W oknie dialogowym importu projektu wybierz **Gradle** i kliknij przycisk **Next**.
1. Kliknij przycisk **Finish**.
1. Znajdź projekt `fpinjava-parent` w części **Project**, kliknij go prawym klawiszem myszy i wybierz polecenie **Open module settings**.
1. W oknie dialogowym **Project Structure** kliknij **Project** w części **Project setting** i wybierz **8 - Lambdas, type annotations etc.** dla pola wybory **Project language level**.
1. Kliknij przycisk **OK**.

## Import do NetBeans

Aby zaimportować projekt do NetBeans, musisz najpierw zainstaować plug-in Gradle. W tym celu:

1. Wybierz **Tools > Plugins > Available Plugins**.
1. W polu **Search** wpisz **gradle**.
1. Zaznacz opcję wyboru **Install** w wierszu **Gradle Support**.
1. Kliknij przycisk **Install**.

Musisz zaakceptować instalację niepodpisanego oprogramowania i ponownie uruchomić NetBeans.


### Import projektu

Teraz musisz zaimportować projekt `fpinjava-parent` do NetBeans:

1. Wybierz **File > Open Project...**.
1. W oknie dialogowym **Open Project** przejdź do folderu z projektemi wybierz podfolder `fpinjava-parent`.
1. Kliknij przycisk **Open Project**.

Projekt powinien zostać zaimportowany.


## Wykonywanie ćwiczeń

Dla każdego z rozdziałów istnieją dwa moduły `nazwa-rozdziału-exercises` i `nazwa-rozdziału-solutions`. Przejdź do pierwszego ćwiczenia w hierarchii `src/main/java`.
Znajdź w kodzie komentarz typu "Do zaimplementowania" lub metodę z jednym wierszem kodu zgłaszającym wyjątek. Zaimplementuj brakujący kod.

Pamiętaj, że kod jest czesto kopiowany z jednego ćwiczenia do następnego, więc nie należy zaglądać do kodu ćwiczenia 2. przed wykonaniem ćwiczenia 1., bo będzie ono często zawierało rozwiązanie ćwiczenia 1.

## Weryfikacja odpowiedzi

Aby sprawdzić, czy proponowane rozwiązanie działa, przejdź do odpowiedniego testu jednostkowego w folderze `src/test/java` z tego samego modułu.
Kliknij go prawym klawiszem myszy (klasa ćwiczenia ma taką samą nazwę, ale dodatkowo dopisek `Test`). Wybierz polecenie **Run as JUnit test**. Test powinien się powieść.
Jeśli tak się nie stało, poprawk kod i spróbuj ponownie. 

## Zaglądanie do rozwiązań

Jeśli nie potrafisz znaleźć prawidłowego rozwiązania, zajrzyj do modułu `nazwa-rozdziału-solutions`. Uruchom test, aby przekonać się, że rozwiązanie działa.

## Uwagi

Mnóstwo kodu jest zduplikowane. Wynika to z chęci uczynienia wszystkich ćwiczeń możliwe niezależnymi. Z drugiej strony kod z poprzednich rozdziałów trafia powoli do modułu
`fpinjava-common` i powinien byś stamtąd używany.

## Nazwy modułów

Kody modułów są najczęściej nazwane na podstawie angielskich tytułów rozdziałów, a nie numerów rozdziałów, co czasem utrudnia ich odnalezienie. Oto lista modułów:

* Rozdział 1. fpinjava-introduction

* Rozdział 2. fpinjava-usingfunctions

* Rozdział 3. fpinjava-makingjavafunctional

* Rozdział 4. fpinjava-recursion

* Rozdział 5. fpinjava-lists

* Rozdział 6. fpinjava-optionaldata

* Rozdział 7. fpinjava-handlingerrors

* Rozdział 8. fpinjava-advancedlisthandling

* Rozdział 9. fpinjava-laziness

* Rozdział 10. fpinjava-trees

* Rozdział 11. fpinjava-advancedtrees

* Rozdział 12. fpinjava-state

* Rozdział 13. fpinjava-io

* Rozdział 14. fpinjava-actors

* Rozdział 15. fpinjava-applications

Większość modułów istnieje w dwóch wersjach: ćwiczeń i rozwiązań. Jednak rozdziały 1. (fpinjava-introduction), 14. (fpinjava-actors) i 15. (fpinjava-applications) nie zawierają ćwiczeń.

Większość modułów zawiera testy jednostkowe służące do werfikacji poprawności rozwiązania. Rozdział 13. jednak ich nie posiada.
Wszystkie pakiety w tym rozdziale posiadają programy wykonywalne, których wynik pozwala zorientować się, czy program jest zgodny z oczekiwaniami.
