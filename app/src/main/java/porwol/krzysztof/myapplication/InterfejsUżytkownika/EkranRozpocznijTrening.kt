package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
            Spacer(modifier = Modifier.height(70.dp))

            Text(
                "Trening — Plan $plan",
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            cwiczenia.forEach { pojedyńczeĆwiczenie ->
                Text(
                    "${pojedyńczeĆwiczenie.nazwa}",
                    color = MaterialTheme.colorScheme.onSurface
                )

                val ostatniTrening by vm.obserwujOstatniTrening(pojedyńczeĆwiczenie.id)
                    .collectAsStateWithLifecycle(initialValue = null)

                Text(
                    text = if (ostatniTrening != null) {
                        "Ostatnio: ${ostatniTrening!!.wyniki}"
                    } else {
                        "Ostatnio: brak danych"
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Lista wyników dla tego ćwiczenia (jeden Pair na serię)
                val wynikiSerii = remember(pojedyńczeĆwiczenie.id) {
                    mutableStateListOf<Pair<String, String>>().apply {
                        repeat(pojedyńczeĆwiczenie.serie) { add("" to "") }
                    }
                }

                repeat(pojedyńczeĆwiczenie.serie) { IndeksSerii ->

                    OutlinedTextField(
                        value = wynikiSerii[IndeksSerii].first,
                        onValueChange = { nowyCiezar ->
                            wynikiSerii[IndeksSerii] = wynikiSerii[IndeksSerii].copy(first = nowyCiezar)
                        },
                        label = { Text("Podaj ciężar") },
                        trailingIcon = {
                            Text(
                                "Seria ${IndeksSerii + 1}",
                                modifier = Modifier.padding(end = 12.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = wynikiSerii[IndeksSerii].second,
                        onValueChange = { nowePowt ->
                            wynikiSerii[IndeksSerii] = wynikiSerii[IndeksSerii].copy(second = nowePowt)
                        },
                        label = { Text("Podaj ilość powtórzeń") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        // Składamy wyniki w string typu "60×8, 60×7, 0×0"
                        val tekstWynikow = wynikiSerii.joinToString(", ") { para ->
                            val ciezar = para.first.ifBlank { "0" }
                            val powt = para.second.ifBlank { "0" }
                            "${ciezar}×${powt}"
                        }
                        vm.zapiszTrening(pojedyńczeĆwiczenie, tekstWynikow)

                        // Czyścimy pola po zapisie
                        repeat(pojedyńczeĆwiczenie.serie) { i ->
                            wynikiSerii[i] = "" to ""
                        }
                    }
                ) {
                    Text("Zapisz to ćwiczenie")
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}