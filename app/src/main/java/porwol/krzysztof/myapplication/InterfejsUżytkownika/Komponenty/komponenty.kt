package porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.Nawigacja.Ekran

@Composable
fun PrzyciskPowrotuDoGłównegoEkranu(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Ekran.Główny_Ekran.trasa) {
                popUpTo(Ekran.Główny_Ekran.trasa) {inclusive = true }
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text("<--")
    }
}