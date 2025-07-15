package porwol.krzysztof.myapplication.InterfejsUżytkownika

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import porwol.krzysztof.myapplication.Nawigacja.Ekran

@Composable
fun EkranGłówny(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            MałyKafelek(
                tekst = "Plan A",
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_A.trasa) }
            )

            Spacer(Modifier.width(16.dp))

            TekstMotywacyjny(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.weight(0.1f))

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            MałyKafelek(
                tekst = "Plan B",
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_B.trasa) }
            )

            Spacer(Modifier.width(16.dp))

            MałyKafelek(
                tekst = "Plan C",
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(Ekran.Plan_C.trasa) }
            )
        }

        Spacer(Modifier.weight(0.1f))

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { DużyKafelek(
            modifier = Modifier.weight(1f),
            onClick = { navController.navigate(Ekran.Edytuj_Ćwiczenia.trasa) }
        ) }
    }
}


@Composable
fun MałyKafelek(tekst: String, modifier: Modifier, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = tekst,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun DużyKafelek(modifier: Modifier, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .clickable{ onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Edytuj ćwiczenia w planie")
        }
    }
}


@Composable
fun TekstMotywacyjny(modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = modifier
            .fillMaxHeight()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Motywacja? Jaka Kurwa motywacja!? " +
                        "Ty potrzebujesz jej żeby tu przyjść? " +
                        "Ja potrzębuje żeby stąd wyjść!!",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}