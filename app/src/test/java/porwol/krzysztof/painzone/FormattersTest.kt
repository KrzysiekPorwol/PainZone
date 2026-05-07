package porwol.krzysztof.painzone.ui

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNotNull

class FormattersTest {

    @Test
    fun `sformatujDate zwraca niepusty string dla poprawnego timestampa`() {
        // Arrange (przygotuj dane)
        val timestamp = 1746619200000L  // 7 maja 2026, godz. 12:00 UTC

        // Act (wykonaj akcję)
        val wynik = sformatujDate(timestamp)

        // Assert (sprawdź wynik)
        assertNotNull(wynik)
        assertTrue("Wynik powinien być niepusty", wynik.isNotEmpty())
    }

    @Test
    fun `sformatujDate dla timestampa 0 zwraca date z 1970 roku`() {
        val timestamp = 0L
        val wynik = sformatujDate(timestamp)
        assertTrue(
            "Wynik powinien zawierać 1970, ale jest: $wynik",
            wynik.contains("1970")
        )
    }

    @Test
    fun `sformatujDate zawiera dwukropek dla godziny`() {
        val timestamp = 1746619200000L
        val wynik = sformatujDate(timestamp)
        assertTrue(
            "Wynik powinien zawierać dwukropek (HH:mm), ale jest: $wynik",
            wynik.contains(":")
        )
    }

    @Test
    fun `sformatujDate zwraca rozne wyniki dla roznych timestampow`() {
        val timestamp1 = 1000000000000L
        val timestamp2 = 1700000000000L

        val wynik1 = sformatujDate(timestamp1)
        val wynik2 = sformatujDate(timestamp2)

        assertNotEquals(
            "Różne timestampy powinny zwracać różne wyniki",
            wynik1,
            wynik2
        )
    }
}