package porwol.krzysztof.painzone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.painzone.ui.screens.components.`PrzyciskPowrotuDoGłównegoEkranu`
import porwol.krzysztof.painzone.ui.navigation.Ekran

@Composable
fun EkranWyboruPlanuHistorii(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Nagłówek
            Text(
                "HISTORIA",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                "wybierz plan",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Trzy karty planów
            KartaWyboruPlanu(
                tekst = "PLAN A",
                ikona = Icons.Default.FitnessCenter,
                onClick = { navController.navigate(Ekran.Historia_Planu.zTrasa("A")) }
            )

            KartaWyboruPlanu(
                tekst = "PLAN B",
                ikona = Icons.Default.SportsGymnastics,
                onClick = { navController.navigate(Ekran.Historia_Planu.zTrasa("B")) }
            )

            KartaWyboruPlanu(
                tekst = "PLAN C",
                ikona = Icons.Default.DirectionsRun,
                onClick = { navController.navigate(Ekran.Historia_Planu.zTrasa("C")) }
            )
        }

        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun KartaWyboruPlanu(
    tekst: String,
    ikona: ImageVector,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ikona,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                tekst,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}