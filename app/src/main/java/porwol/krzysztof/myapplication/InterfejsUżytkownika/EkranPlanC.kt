package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskRozpocznijTrening
import porwol.krzysztof.myapplication.viewmodel.TreningViewModel

@Composable
fun EkranPlanC(navController: NavController) {

    val vm: TreningViewModel = viewModel()
    val listaĆwiczeń by vm.planC.collectAsStateWithLifecycle()
    val stanScrolla = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
                .verticalScroll(stanScrolla)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Nagłówek
            Text(
                "PLAN C",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                "twój trening",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (listaĆwiczeń.isEmpty()) {
                // Pusta lista — z ikoną
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "BRAK ĆWICZEŃ",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        "dodaj pierwsze w sekcji edycji",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                listaĆwiczeń.forEach { pojedyńczeĆwiczenie ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    pojedyńczeĆwiczenie.nazwa.uppercase(),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    "${pojedyńczeĆwiczenie.serie} serie",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            IconButton(
                                onClick = { vm.usun(pojedyńczeĆwiczenie) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Usuń",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(100.dp))

        }

        PrzyciskRozpocznijTrening(
            navController,
            "C",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        )

        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}