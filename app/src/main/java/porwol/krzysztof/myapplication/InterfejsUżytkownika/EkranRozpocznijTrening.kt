package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.data.Plan
import porwol.krzysztof.myapplication.viewmodel.TreningViewModel

@Composable
fun EkranRozpocznijTrening(
    plan: Plan,
    navController: NavController
) {
    val vm: TreningViewModel = viewModel()

    val cwiczenia by when (plan) {
        Plan.A -> vm.planA
        Plan.B -> vm.planB
        Plan.C -> vm.planC
    }.collectAsStateWithLifecycle()

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
                "TRENING",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                "plan $plan",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (cwiczenia.isEmpty()) {
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
                        "dodaj ćwiczenia w sekcji edycji",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                cwiczenia.forEach { pojedyńczeĆwiczenie ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Nazwa ćwiczenia
                            Text(
                                pojedyńczeĆwiczenie.nazwa.uppercase(),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleLarge
                            )

                            // "Ostatnio:" w pomarańczu
                            val ostatniTrening by vm.obserwujOstatniTrening(pojedyńczeĆwiczenie.id)
                                .collectAsStateWithLifecycle(initialValue = null)

                            Text(
                                text = if (ostatniTrening != null) {
                                    "ostatnio: ${ostatniTrening!!.wyniki}"
                                } else {
                                    "ostatnio: brak danych"
                                },
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Pola serii
                            val wynikiSerii = remember(pojedyńczeĆwiczenie.id) {
                                mutableStateListOf<Pair<String, String>>().apply {
                                    repeat(pojedyńczeĆwiczenie.serie) { add("" to "") }
                                }
                            }

                            repeat(pojedyńczeĆwiczenie.serie) { IndeksSerii ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "${IndeksSerii + 1}.",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.width(24.dp)
                                    )

                                    OutlinedTextField(
                                        value = wynikiSerii[IndeksSerii].first,
                                        onValueChange = { nowyCiezar ->
                                            wynikiSerii[IndeksSerii] =
                                                wynikiSerii[IndeksSerii].copy(first = nowyCiezar)
                                        },
                                        label = { Text("kg") },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier.weight(1f)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    OutlinedTextField(
                                        value = wynikiSerii[IndeksSerii].second,
                                        onValueChange = { nowePowt ->
                                            wynikiSerii[IndeksSerii] =
                                                wynikiSerii[IndeksSerii].copy(second = nowePowt)
                                        },
                                        label = { Text("powt.") },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Przycisk Zapisz
                            Button(
                                onClick = {
                                    val tekstWynikow = wynikiSerii.joinToString(", ") { para ->
                                        val ciezar = para.first.ifBlank { "0" }
                                        val powt = para.second.ifBlank { "0" }
                                        "${ciezar}×${powt}"
                                    }
                                    vm.zapiszTrening(pojedyńczeĆwiczenie, tekstWynikow)

                                    repeat(pojedyńczeĆwiczenie.serie) { i ->
                                        wynikiSerii[i] = "" to ""
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                )
                            ) {
                                Text(
                                    "ZAPISZ",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}