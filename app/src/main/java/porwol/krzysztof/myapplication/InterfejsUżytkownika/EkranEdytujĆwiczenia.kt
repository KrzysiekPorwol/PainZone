package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu

@Composable
fun EkranEdytujĆwiczenia(navController: NavController) {
    Text("Ekran Edytuj Ćwiczenia")
    PrzyciskPowrotuDoGłównegoEkranu(navController)
}