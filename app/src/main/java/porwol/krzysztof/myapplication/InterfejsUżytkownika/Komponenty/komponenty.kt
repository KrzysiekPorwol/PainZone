package porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.NawigacjaMiędzyEkranami.Ekran

@Composable
fun PrzyciskPowrotuDoGłównegoEkranu(
    navController: NavController,
    modifier: Modifier = Modifier
        .padding(16.dp)
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Ekran.Główny_Ekran.trasa) {
                popUpTo(Ekran.Główny_Ekran.trasa) {inclusive = true }
                launchSingleTop = true
            }
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Text("<--")
    }
}