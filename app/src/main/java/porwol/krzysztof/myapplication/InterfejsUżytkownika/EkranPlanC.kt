package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu

@Composable
fun EkranPlanC(navController: NavController) {
    Text("Plan C")
    PrzyciskPowrotuDoGłównegoEkranu(navController)
}