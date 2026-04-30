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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskRozpocznijTrening
import porwol.krzysztof.myapplication.viewmodel.TreningViewModel

@Composable
fun EkranPlanA(navController: NavController) {

    val vm: TreningViewModel = viewModel()

//    val listaĆwiczeń = remember { TymczasowyZestawPlanówĆwiczeń.planA } Zamiana odczytu z "Zmiennej" na baze ROOM.
    val listaĆwiczeń by vm.planA.collectAsStateWithLifecycle()

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
                "Plan A - Ćwiczenia",
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (listaĆwiczeń.isEmpty()) {
                Text(
                    "Brak ćwiczeń w planie.",
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                listaĆwiczeń.forEach { pojedyńczeĆwiczenie ->
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Column() {
                           Text(
                               "- ${pojedyńczeĆwiczenie.nazwa} ",
                               color = MaterialTheme.colorScheme.onSurface,
                           )
                           Text(
                               "- Ilość serii: ${pojedyńczeĆwiczenie.serie} ",
                               color = MaterialTheme.colorScheme.onSurface
                           )
                       }

                       Button(

//                           onClick = { listaĆwiczeń.remove(pojedyńczeĆwiczenie) }, Zmiana sposobu usuwania z zmiennej na baze ROOM.
                           onClick = { vm.usun(pojedyńczeĆwiczenie) },

                           colors = ButtonDefaults.buttonColors(
                               containerColor = MaterialTheme.colorScheme.surface,
                               contentColor = MaterialTheme.colorScheme.onSurface
                           )
                       ) {
                           Text("Usuń")
                       }
                   }
                    Spacer(Modifier.height(30.dp))
                }
            }

            Spacer(Modifier.height(80.dp))

        }

        PrzyciskRozpocznijTrening(
            navController,
            "A",
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