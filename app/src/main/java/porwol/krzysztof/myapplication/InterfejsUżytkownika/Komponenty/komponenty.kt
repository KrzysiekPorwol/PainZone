package porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.NawigacjaMiędzyEkranami.Ekran
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Instant


@Composable
fun PrzyciskPowrotuDoGłównegoEkranu(
    navController: NavController,
    modifier: Modifier = Modifier
        .padding(16.dp)
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Ekran.Główny_Ekran.trasa) {
                popUpTo(Ekran.Główny_Ekran.trasa) {
                    inclusive = true
                } // usun wszystkie ekrany ze stosu włacznie z tym.
                launchSingleTop =
                    true // usun ekran glowny ze stosu jesli juz istnieje (zeby nie bylo dwoch) (tutaj raczej niemozliwe, bo juz usunelismy wszystkie ekrany linijke wyzej)
            }
        },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Text("<--")
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
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Rozpocznij trening"
        )
    }
}