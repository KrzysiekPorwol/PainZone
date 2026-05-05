package porwol.krzysztof.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val MyColorScheme = darkColorScheme(
    background = CzarneTło,
    surface = Pomarańczowy,
    onSurface = BiałyTekst,
    onBackground = BiałyTekst
)

/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = MyColorScheme,
        typography = Typography,
        content = content
    )
}