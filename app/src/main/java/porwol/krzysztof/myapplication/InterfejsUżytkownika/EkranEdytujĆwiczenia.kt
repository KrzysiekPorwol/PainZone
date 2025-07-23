package porwol.krzysztof.myapplication.InterfejsUżytkownika

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.DodajĆwiczenieDoPlanu
import porwol.krzysztof.myapplication.ReprezentacjaDanych.TymczasowyZestawPlanówĆwiczeń
import porwol.krzysztof.myapplication.ReprezentacjaDanych.Ćwiczenie
import java.nio.file.WatchEvent

@Composable
fun EkranEdytujĆwiczenia(navController: NavController) {

    //Stan dla wybranego planu
    var wybranyPlan by remember { mutableStateOf("Plan A") }

    // Stan dla pól tekstowych
    var nazwa by remember { mutableStateOf("") }
    var powtórzenia by remember { mutableStateOf("") }
    var serie by remember { mutableStateOf("") }

    Box() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nazwa,
                onValueChange = { nazwa = it },
                label = { Text("Nazwa Ćwiczenia") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = powtórzenia,
                onValueChange = { powtórzenia = it },
                label = { Text("Ilość powtórzeń") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = serie,
                onValueChange = { serie = it },
                label = { Text("Ilość serii") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Button(
                        onClick = { wybranyPlan = "Plan A" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                            .alpha(
                                if (wybranyPlan == "Plan A") 1f else 0.5f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )

                    ) { Text("Plan A") }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Button(
                        onClick = { wybranyPlan = "Plan B" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(10.dp)
                            .alpha(
                                if (wybranyPlan == "Plan B") 1f else 0.5f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )

                    ) { Text("Plan B") }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Button(
                        onClick = { wybranyPlan = "Plan C"},
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd)
                            .padding(10.dp)
                            .alpha(
                                if (wybranyPlan == "Plan C") 1f else 0.5f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )

                    ) { Text("Plan C") }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Button(
                        onClick = {
                            val noweĆwiczenie = Ćwiczenie(
                                nazwa = nazwa,
                                powtórzenia = powtórzenia.toInt(),
                                serie = serie.toInt()
                            )

                            DodajĆwiczenieDoPlanu(wybranyPlan, noweĆwiczenie)

                            println("Plan A:" + TymczasowyZestawPlanówĆwiczeń.planA)

                            navController.popBackStack()


                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )

                    ) { Text("Zapisz") }
                }
            }

            Spacer(modifier = Modifier.weight(0.15f))

        }
        PrzyciskPowrotuDoGłównegoEkranu(
            navController,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }

}

