package porwol.krzysztof.myapplication.InterfejsUżytkownika

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import porwol.krzysztof.myapplication.InterfejsUżytkownika.Komponenty.PrzyciskPowrotuDoGłównegoEkranu
import java.nio.file.WatchEvent

@Composable
fun EkranEdytujĆwiczenia(navController: NavController) {
    // Stan dla pól tekstowych
    var nazwa by remember { mutableStateOf("") }
    var powtórzenia by remember { mutableStateOf("") }
    var serie by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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

        OutlinedTextField(
            value = serie,
            onValueChange = { serie = it },
            label = { Text("Ilość serii") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    PrzyciskPowrotuDoGłównegoEkranu(navController)
}