package porwol.krzysztof.painzone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val MyColorScheme = darkColorScheme(
    background = CzarneTło,
    surface = CzarneSurface,
    surfaceVariant = CzarneSurfaceWyższe,
    primary = SpalonyPomarańczowy,
    onPrimary = CzarnyTekstNaPomarańczu,
    secondary = KrwistaCzerwień,
    onSecondary = BiałyTekst,
    onSurface = BiałyTekst,
    onSurfaceVariant = SzaryTekst,
    onBackground = BiałyTekst
)

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MyColorScheme,
        typography = Typography,
        content = content
    )
}