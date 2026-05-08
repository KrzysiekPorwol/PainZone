package porwol.krzysztof.painzone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import porwol.krzysztof.painzone.ui.screens.components.`PrzyciskPowrotuDoGłównegoEkranu`
import porwol.krzysztof.painzone.data.Plan
import porwol.krzysztof.painzone.viewmodel.TreningViewModel

@Composable
fun EkranEdytujĆwiczenia(navController: NavController) {

    val vm: TreningViewModel = viewModel()
    var wybranyPlan by remember { mutableStateOf("A") }
    var nazwa by remember { mutableStateOf("") }
    var serie by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Nagłówek
            Text(
                "EDYCJA",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                "dodaj ćwiczenie do planu",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Pole nazwy ćwiczenia
            OutlinedTextField(
                value = nazwa,
                placeholder = { Text("Maksymalnie 30 znaków") },
                onValueChange = { nazwa = it.take(30) },
                label = { Text("Nazwa ćwiczenia") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Pole liczby serii
            OutlinedTextField(
                value = serie,
                placeholder = { Text("Maksymalna ilość serii to 20.") },
                onValueChange = { nowa ->
                    if (nowa.all { it.isDigit() }) {
                        val liczba = nowa.toIntOrNull() ?: 0
                        if (liczba <= 20) serie = nowa.take(2)

                    }
                },
                label = { Text("Ilość serii") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Wybór planu
            Text(
                "WYBIERZ PLAN",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                PrzyciskWyboruPlanu(
                    nazwa = "A",
                    wybrany = wybranyPlan == "A",
                    onClick = { wybranyPlan = "A" },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PrzyciskWyboruPlanu(
                    nazwa = "B",
                    wybrany = wybranyPlan == "B",
                    onClick = { wybranyPlan = "B" },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PrzyciskWyboruPlanu(
                    nazwa = "C",
                    wybrany = wybranyPlan == "C",
                    onClick = { wybranyPlan = "C" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Przycisk Zapisz — duży, na dole
            Button(
                onClick = {
                    if (nazwa.isNotBlank() && serie.isNotBlank()) {
                        vm.dodaj(
                            nazwa = nazwa,
                            serie = serie.toInt(),
                            plan = Plan.valueOf(wybranyPlan)
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "ZAPISZ ĆWICZENIE",
                    style = MaterialTheme.typography.titleMedium
                )
            }
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
private fun PrzyciskWyboruPlanu(
    nazwa: String,
    wybrany: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (wybrany) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            },
            contentColor = if (wybrany) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    ) {
        Text(
            "PLAN $nazwa",
            style = MaterialTheme.typography.titleMedium
        )
    }
}