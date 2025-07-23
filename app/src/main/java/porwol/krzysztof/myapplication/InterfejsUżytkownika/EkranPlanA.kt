package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.ReprezentacjaDanych.TymczasowyZestawPlanówĆwiczeń

@Composable
fun EkranPlanA(navController: NavController) {

    val listaĆwiczeń = TymczasowyZestawPlanówĆwiczeń.planA

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(70.dp))

        Text("Plan A - Ćwiczenia")

        Spacer(modifier = Modifier.height(16.dp))

        if (listaĆwiczeń.isEmpty()) {
            Text("Brak ćwiczeń w planie.")
        } else {
            listaĆwiczeń.forEach { PojedyńczeĆwiczenie ->
                Text(
                    "${PojedyńczeĆwiczenie.nazwa} " +
                            "- ilość serii: ${PojedyńczeĆwiczenie.serie} " +
                            "- Powtórzeń: ${PojedyńczeĆwiczenie.powtórzenia}"
                )
                // Powtórz tyle razy, ile jest serii
                repeat(PojedyńczeĆwiczenie.serie) { IlośćSerii ->

                var powtórzeniaDanejSerii by remember { mutableStateOf("") }
                var ciężarDanejSerii by remember { mutableStateOf("")}

                    OutlinedTextField(
                        value = ciężarDanejSerii,
                        onValueChange = { ciężarDanejSerii = it },
                        label = { Text("Ciężar") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = powtórzeniaDanejSerii,
                        onValueChange = { powtórzeniaDanejSerii = it },
                        label = { Text("Ilość wykonanych powtórzeń") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }




    PrzyciskPowrotuDoGłównegoEkranu(navController)
}