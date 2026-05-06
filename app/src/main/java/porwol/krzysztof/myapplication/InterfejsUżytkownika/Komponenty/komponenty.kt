package porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.NawigacjaMiędzyEkranami.Ekran

@Composable
fun PrzyciskPowrotuDoGłównegoEkranu(
    navController: NavController,
    modifier: Modifier = Modifier.padding(16.dp)
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Ekran.Główny_Ekran.trasa) {
                popUpTo(Ekran.Główny_Ekran.trasa) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Powrót do ekranu głównego"
        )
    }
}

@Composable
fun PrzyciskRozpocznijTrening(
    navController: NavController,
    plan: String,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = { navController.navigate(Ekran.Rozpocznij_Trening.zTrasa(plan)) },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Rozpocznij trening"
        )
    }
}