package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUzytkownika.sformatujDate
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.data.Plan
import porwol.krzysztof.myapplication.data.Trening
import porwol.krzysztof.myapplication.viewmodel.TreningViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EkranHistoriiPlanu(
    plan: Plan,
    navController: NavController
) {
    val vm: TreningViewModel = viewModel()

    val historia by vm.obserwujHistoriePlanu(plan)
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val stanScrolla = rememberScrollState()

    var treningDoUsuniecia by remember { mutableStateOf<Trening?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
                .verticalScroll(stanScrolla)
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Text(
                "Historia — Plan $plan",
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (historia.isEmpty()) {
                Text(
                    "Brak treningów dla tego planu.",
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                historia.forEach { trening ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .combinedClickable(
                                onClick = { /* Nic nie robi, bo tylko gdy przytrzymamy */ },
                                onLongClick = { treningDoUsuniecia = trening }
                            ),
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                "${sformatujDate(trening.data)}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                trening.cwiczenieNazwa,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                "Wyniki: ${trening.wyniki}",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))


        }

        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )

        // Dialog potwierdzenia usunięcia
        if (treningDoUsuniecia != null) {
            AlertDialog(
                onDismissRequest = { treningDoUsuniecia = null },
                title = { Text("Usunąć to ćwiczenie?") },
                text = { Text("Ta operacja jest nieodwracalna.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            vm.usunTrening(treningDoUsuniecia!!)
                            treningDoUsuniecia = null
                        }
                    ) {
                        Text("Usuń")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { treningDoUsuniecia = null }) {
                        Text("Anuluj")
                    }
                }
            )
        }
    }
}