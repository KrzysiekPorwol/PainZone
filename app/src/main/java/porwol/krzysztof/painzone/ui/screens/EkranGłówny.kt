package porwol.krzysztof.painzone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.painzone.ui.navigation.Ekran

@Composable
fun EkranGłówny(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        // Nagłówek
        Spacer(Modifier.height(24.dp))
        Text(
            "PAINZONE",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            "twój trening, twoja strefa",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(20.dp))

        // Wiersz 1: Plan A + Historia
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            KafelekZIkoną(
                tekst = "Plan A",
                ikona = Icons.Default.FitnessCenter,
                kolorTła = MaterialTheme.colorScheme.primary,
                kolorTekstu = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_A.trasa) }
            )

            Spacer(Modifier.width(12.dp))

            KafelekZIkoną(
                tekst = "Historia",
                ikona = Icons.Default.History,
                kolorTła = MaterialTheme.colorScheme.surface,
                kolorTekstu = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Wybor_Planu_Historii.trasa) }
            )
        }

        Spacer(Modifier.height(12.dp))

        // Wiersz 2: Plan B + Plan C
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            KafelekZIkoną(
                tekst = "Plan B",
                ikona = Icons.Default.SportsGymnastics,
                kolorTła = MaterialTheme.colorScheme.primary,
                kolorTekstu = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_B.trasa) }
            )

            Spacer(Modifier.width(12.dp))

            KafelekZIkoną(
                tekst = "Plan C",
                ikona = Icons.Default.DirectionsRun,
                kolorTła = MaterialTheme.colorScheme.primary,
                kolorTekstu = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_C.trasa) }
            )
        }

        Spacer(Modifier.height(12.dp))

        // Wiersz 3: Edytuj (na całą szerokość)
        Row(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
        ) {
            KafelekZIkoną(
                tekst = "Edytuj plan",
                ikona = Icons.Default.Edit,
                kolorTła = MaterialTheme.colorScheme.secondary,
                kolorTekstu = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Edytuj_Ćwiczenia.trasa) }
            )
        }
    }
}

@Composable
fun KafelekZIkoną(
    tekst: String,
    ikona: ImageVector,
    kolorTła: Color,
    kolorTekstu: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = kolorTła),
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ikona,
                contentDescription = tekst,
                tint = kolorTekstu,
                modifier = Modifier
                    .padding(8.dp)
                    .height(40.dp)
            )
            Text(
                text = tekst.uppercase(),
                color = kolorTekstu,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
    }
}