package porwol.krzysztof.painzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import porwol.krzysztof.painzone.ui.screens.EkranEdytujĆwiczenia
import porwol.krzysztof.painzone.ui.screens.`EkranGłówny`
import porwol.krzysztof.painzone.ui.screens.EkranHistoriiPlanu
import porwol.krzysztof.painzone.ui.screens.EkranPlanA
import porwol.krzysztof.painzone.ui.screens.EkranPlanB
import porwol.krzysztof.painzone.ui.screens.EkranPlanC
import porwol.krzysztof.painzone.ui.screens.EkranRozpocznijTrening
import porwol.krzysztof.painzone.ui.screens.EkranWyboruPlanuHistorii
import porwol.krzysztof.painzone.ui.navigation.Ekran
import porwol.krzysztof.painzone.data.Plan
import porwol.krzysztof.painzone.ui.theme.MyApplicationTheme


class PainZoneActivity :
    ComponentActivity() { // "Chcę stworzyć nowy rower (MainActivity) na bazie gotowego szkieletu (ComponentActivity). Dodam tylko własne siodełko i kolor."

    override fun onCreate(savedInstanceState: Bundle?) { // override fun - „Nadpisuję funkcję, która została już wcześniej zdefiniowana w klasie nadrzędnej.”. Czyli: onCreate() to funkcja, która już istnieje w ComponentActivity (tej z Google), a Ty ją nadpisujesz, żeby zrobić coś swojego (np. wyświetlić ekran, ustawić motyw itp.). // onCreate - Uruchamia się automatycznie, gdy aplikacja (lub aktywność) się włącza. // (savedInstanceState: Bundle?) - To są parametry funkcji. savedInstanceState to specjalna paczka danych (typu Bundle?), która zawiera informacje o stanie ekranu przed np. obrotem lub zamknięciem.

        super.onCreate(savedInstanceState) // Uruchamia domyślną logikę startu ekranu od Google // super - W Kotlinie (i innych językach obiektowych), super oznacza: „Odwołaj się do klasy nadrzędnej (czyli tej, po której dziedziczysz)”.

        enableEdgeToEdge() // To funkcja pomocnicza z Androida (dostarczona przez Jetpack), która: Pozwala Twojej aplikacji rozciągać się na całą powierzchnię ekranu, także pod paskami systemowymi (np. pasek stanu u góry i nawigacji na dole).

        setContent {

            MyApplicationTheme() { // MyApplicationTheme ustawia wygląd całej aplikacji, ładując kolory, czcionki i style z plików w folderze ui.theme.

                // rememberNavController() – tworzy obiekt, który zarządza nawigacją
                // (rememberNavController to specialna funkcja, która zarządza ekranami
                // pobrana dzięki import w build gradle (biblioteka))
                val navController = rememberNavController()

                PainZoneNavHost(navController)

            }
        }
    }
}

@Composable
fun PainZoneNavHost(navController: NavHostController) {
    // NavHost(...) – to miejsce, w którym określasz wszystkie możliwe ekrany Twojej aplikacji.
    NavHost(
        navController = navController,
        startDestination = Ekran.Główny_Ekran.trasa
    ) {
        composable(Ekran.Główny_Ekran.trasa) {
            EkranGłówny(navController)
        }
        composable(Ekran.Plan_A.trasa) {
            EkranPlanA(navController)
        }
        composable(Ekran.Plan_B.trasa) {
            EkranPlanB(navController)
        }
        composable(Ekran.Plan_C.trasa) {
            EkranPlanC(navController)
        }
        composable(Ekran.Edytuj_Ćwiczenia.trasa) {
            EkranEdytujĆwiczenia(navController)
        }
        composable(
            route = Ekran.Rozpocznij_Trening.trasa,
            arguments = listOf(navArgument("plan") { type = NavType.StringType })
        ) { backStackEntry ->
            val planNazwa = backStackEntry.arguments?.getString("plan") ?: "A"
            val plan = Plan.valueOf(planNazwa)
            EkranRozpocznijTrening(plan = plan, navController = navController)
        }
        composable(Ekran.Wybor_Planu_Historii.trasa) {
            EkranWyboruPlanuHistorii(navController)
        }
        composable(
            route = Ekran.Historia_Planu.trasa,
            arguments = listOf(navArgument("plan") { type = NavType.StringType })
        ) { backStackEntry ->
            val planNazwa = backStackEntry.arguments?.getString("plan") ?: "A"
            val plan = Plan.valueOf(planNazwa)
            EkranHistoriiPlanu(plan = plan, navController = navController)
        }
    }
}

