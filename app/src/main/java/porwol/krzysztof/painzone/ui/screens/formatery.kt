package porwol.krzysztof.painzone.InterfejsUzytkownika

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.Instant


fun sformatujDate(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val dataLokalna = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, HH:mm", Locale("pl"))
    return dataLokalna.format(formatter)
}