package porwol.krzysztof.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.EkranGłówny
import porwol.krzysztof.myapplication.ui.theme.MyApplicationTheme


class PainZoneActivity :
    ComponentActivity() { // "Chcę stworzyć nowy rower (MainActivity) na bazie gotowego szkieletu (ComponentActivity). Dodam tylko własne siodełko i kolor."

    override fun onCreate(savedInstanceState: Bundle?) { // override fun - „Nadpisuję funkcję, która została już wcześniej zdefiniowana w klasie nadrzędnej.”. Czyli: onCreate() to funkcja, która już istnieje w ComponentActivity (tej z Google), a Ty ją nadpisujesz, żeby zrobić coś swojego (np. wyświetlić ekran, ustawić motyw itp.). // onCreate - Uruchamia się automatycznie, gdy aplikacja (lub aktywność) się włącza. // (savedInstanceState: Bundle?) - To są parametry funkcji. savedInstanceState to specjalna paczka danych (typu Bundle?), która zawiera informacje o stanie ekranu przed np. obrotem lub zamknięciem.

        super.onCreate(savedInstanceState) // Uruchamia domyślną logikę startu ekranu od Google // super - W Kotlinie (i innych językach obiektowych), super oznacza: „Odwołaj się do klasy nadrzędnej (czyli tej, po której dziedziczysz)”.

        enableEdgeToEdge() // To funkcja pomocnicza z Androida (dostarczona przez Jetpack), która: Pozwala Twojej aplikacji rozciągać się na całą powierzchnię ekranu, także pod paskami systemowymi (np. pasek stanu u góry i nawigacji na dole).

        setContent {

            MyApplicationTheme() { // MyApplicationTheme ustawia wygląd całej aplikacji, ładując kolory, czcionki i style z plików w folderze ui.theme.
                val navController = rememberNavController() // rememberNavController() – tworzy obiekt, który zarządza nawigacją między ekranami.

                NavHost(navController = navController, startDestination = "ekran_główny") { // NavHost(...) – to miejsce, w którym określasz wszystkie możliwe ekrany Twojej aplikacji.
                    composable("ekran_główny") { // to definicja pierwszego ekranu.
                        EkranGłówny(
                            onKlikPlanA = {
                                navController.navigate("plan_A") // przejście do nowego ekranu o nazwie plan_a
                            }
                        )
                    }
                    composable("plan_A") {
                        Text("ekran planu A")
                    }
                }
            }
        }
    }
}

// Git nad GitHub Test