# PainZone - Notatnik do prowadzenia postępów 




**2025-07-10**
1. Wymyślenie pomysłu na aplikację (aplikacja do kontrolowania postępów w treningu siłowym, głównym
założeniem aplikacji to szybkie i przejrzyste wprowadzanie ciężarów, serii, powtórzeń w trakcie
treningu)
2. Stworzenie nowego projektu o nazwie "PainZone"


**2025-07-12**
1. Stworzenie wstępnego wyglądu aplikacji w edytorze graficznym.
2. Wprowadzenie typowych layoutów do projektu.


**2025-07-13**
1. Dopracowanie wyglądu aplikacji.
2. Wprowadzenie Navhost w celu przełączania między ekranami (wstępne).


**2025-07-14**
1. Dodanie Git i GitHuba, testowanie Git i Githuba, zapisywanie i edytowanie commitów lokalnie i
   zdalnie, nadpisywanie stanu zdalnego stanem lokalnym, używanie do tego konsoli alt+F12.
2. Stworzenie tego pliku do prowadzenia postępów (Po wprowadzeniu git i githuba).


**2025-07-15**
1. Edycja Navhost, stworzenie tras, stworzenie reszty ekranów (wstępne), 
!- Nie rozumiem jak działa navHost -!
2. IRYTACJA przez niezrozumienie :).


**2025-07-16**
1. Zrozumiałem jak działa NavHost, ale dalej sprawia mi problemy.
2. Dodanie Pliku komponenty (W nim przycisk powrotu).
3. Dodanie przycisku powrotu do ekranów Plan A, Plan B, Plan C oraz edytuj ćwiczenia w planie.
4. Dodanie tymczasowych baz danych (Bez ViewModelu i room).
5. Stworzenie pól tekstowych w Ekranie "Edytuj ćwiczenia".


**2025-07-21**
1. Rozbudowanie widoku ekranu "EdytujĆwiczenia", tj. dodanie przycisków i ich odpowiednie
   ustawienie.
2. Dodanie zaznaczenia wybranego planu podczas dodawania ćwiczenia.



**2025-07-23**
1. Zapisywanie (w pamięci włączonej aplikacji) danych dodanego ćwiczenia.
2. Wyświetlanie zapisanego ćwiczenia w planie A.
3. Wyświetlanie póltekstowych w ilości zależnej od ilości serii danego ćwiczenia.

**2026-04-20**
1. Powrót do pracy nad projektem po 9 miesięcznej przerwie.
2. Instalacja środowiska pracy na MacOS.
3. Przypomniałem sobie schemat odpalania aplikacji przez Android.
4. Przyjrzałem się plikom AndroidManifest.xml oraz PainZoneActivity.kt - szczegółowo 
zrozumiałem do czego służą te pliki.


**2026-04-21**
1. Ciąg dalszy nauki jak działa i do czego służy PainZoneActivity.kt.
2. Zrozumienie działania pliku Ekrany.kt.
3. Zrozumienie działania i powodu stosowania konkretnych rozwiązań w pliku EkranGłówny.kt.
4. Usuniecie duplikacji kodu w MałyKafelek, DużyKafelek, TekstMotywacyjny.
5. Zapoznanie się z plikiem EkranPlanA.kt.


**2026-04-22**
1. Pełne zrozumienie pliku EkranPlanA.kt.
2. Pełne zrozumienie plików Cwiczenie.kt, TymczasowyZestawPlanówCwiczen.kt.


**2026-04-23**
1. Pełne zrozumienie pliku komponenty.kt. 
2. Dodano klawiature numeryczna przy wyborze pola "serie" i "powtórzenia" podczas dodawania ćwiczenia.
3. Pełne zrozumienie pliku EkranEdytujCwiczenia.kt.
4. Dodanie funkcji scrolowania ćwiczeń w planie A.
5. Usunięcie ilości powtórzeń podczas edycji ćwiczenia oraz z tytułu ćwiczenia w Planie.
6. Dodano przycisk "Usuń Ćwiczenie" w Planie A.
7. Poprawki wizualne w Planie A.


**2026-04-24**
1. Zrobienie walidacji podczas dodawania ćwiczenia.
2. Poprawki wizualne w wyświetlanym ćwiczeniu.
3. Stylizacja text fieldów w Planie A.
4. Pobranie bilbioteki Room.
5. Rozpoczęcie prac stworzenia bazy danych, która jest przeżywalna nawet
po zamknięciu aplikacji. Zgodnie z architekturą MVVM.
5. Edycja pliku "Ćwiczenie.kt" według standardów biblioteki room.
6. Stworzenie pliku Plan.kt - dzięki niemu możemy przypisać konkretnemu ćwiczeniu 
w jakim ma być planie w ROOM.


**2026-04-25**
1. Stworzenie całego elementu "Model" według architektury MVVM. tj. 
stworzenie folderu data razem z plikami AppDatabase, Cwiczenie, CwiczenieDao, Plan.


**2026-04-28**
1. Stworzenie folderu viewmodel i pliku "TreningViewModel".
2. Przerobienie w EkraniePlanuA pobierania danych o ćwiczeniach (zamiana z zmiennej na 
viewModel + bazę danych ROOM).
3. Przerobienie EkranEdytujĆwiczenia zapisywania cwiczen (zamiana z zmiennej lista na
viewModel + bazę danych ROOM).
4. Szukanie rozwiązania do stworzenia historii treningu (główny zamysł aplikacji to
stworzenie SZYBKIEGO, WYGODNEGO sposobu do zapisywania postępów w treningu. Chodzi
głównie o to, że w porównaniu z topowymi aplikacjami tego typu, zawsze trzeba 
zapoznawać się z wieloma funkcjami, i zajmuje więcej czasu zwykłe zapisywanie
postępów w swoim treningu. A obserwująć ludzi na siłowni, i po rozmowach z znajomymi
co również trenują, zdecydowana wiekszość robi po prostu parę serii przy paru
ćwiczeniach, i stara się podnieść więcej niż poprzednio).
5. Stworzenie wstępnego zarysu (nowa baza danych na historie treningu, nowy ekran
dla wprowadzania ciezarow i powtorzen w dniu treningowym. Stary ekran to widok 
poprzednich wyników siłowych). Te rozwiązanie pozwoli na stworzenie pełnej historii
treningu w przyszlości (będzie już na stałe zapisywać wyniki siłowe w bazie danych).
6. Zakomentowanie starego kodu od zapisywania ćwiczeń.
7. Stworzenie nowego pliku (encje) Trening.kt w folderze data.
8. Stworzenie nowego pliku (operacje) TreningDao.kt.


**2026-04-29**
1. Aktualizacja pliku AppDataBase - dodanie nowej tabeli w bazie, odwołanie do nowego DAO itd.
2. Aktualizacja pliku TreningViewModel. - dodanie opcji zapisu treningu, oraz obserwacji ostatniego.
3. Dodanie nowej trasy do Ekrany.kt - tworzę nawigację która przyjmuje parametry
chcę stworzyć nowy ekran do wprowdzania wynikow siłowych, który będzie "dziedziczony"
do planu A, B, C a nie tworzony w zasadzie taki sam ekran 3 razy.
4. określenie nowego ekranu w PainZoneActivity NavHost.
5. Stworzenie szkieletu i testy czy działa ekran "EkranRozpocznijTrening".
6. Ciąg dalszy prac nad ekranem "EkranRozpocznijTrening".


**2026-04-30**
1. Stworzenie w komponentach przycisku wchodzenia do ekranu treningu, 
i przypisanie go do Planu A.