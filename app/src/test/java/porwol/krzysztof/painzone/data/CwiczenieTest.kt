package porwol.krzysztof.painzone.data

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue

class CwiczenieTest {

    @Test
    fun `cwiczenie zachowuje wartosci podane w konstruktorze`() {
        // Arrange + Act
        val cwiczenie = Cwiczenie(
            id = 1L,
            nazwa = "Pompki",
            serie = 3,
            plan = Plan.A
        )

        // Assert
        assertEquals(1L, cwiczenie.id)
        assertEquals("Pompki", cwiczenie.nazwa)
        assertEquals(3, cwiczenie.serie)
        assertEquals(Plan.A, cwiczenie.plan)
    }

    @Test
    fun `cwiczenie ma domyslne id rowne 0`() {
        // Arrange + Act
        val cwiczenie = Cwiczenie(
            nazwa = "Przysiady",
            serie = 4,
            plan = Plan.B
        )

        // Assert
        assertEquals(0L, cwiczenie.id)
    }

    @Test
    fun `dwa cwiczenia o identycznych polach sa rowne`() {
        // Arrange
        val cwiczenie1 = Cwiczenie(
            id = 5L,
            nazwa = "Martwy ciag",
            serie = 5,
            plan = Plan.C
        )
        val cwiczenie2 = Cwiczenie(
            id = 5L,
            nazwa = "Martwy ciag",
            serie = 5,
            plan = Plan.C
        )

        // Assert
        assertEquals(cwiczenie1, cwiczenie2)
    }

    @Test
    fun `dwa cwiczenia z roznymi nazwami nie sa rowne`() {
        // Arrange
        val cwiczenie1 = Cwiczenie(nazwa = "Pompki", serie = 3, plan = Plan.A)
        val cwiczenie2 = Cwiczenie(nazwa = "Przysiady", serie = 3, plan = Plan.A)

        // Assert
        assertNotEquals(cwiczenie1, cwiczenie2)
    }

    @Test
    fun `copy pozwala zmienic tylko jedno pole`() {
        // Arrange
        val oryginalne = Cwiczenie(
            id = 10L,
            nazwa = "Pompki",
            serie = 3,
            plan = Plan.A
        )

        // Act - kopia z większą liczbą serii
        val zmodyfikowane = oryginalne.copy(serie = 5)

        // Assert - serie zmienione, reszta bez zmian
        assertEquals(5, zmodyfikowane.serie)
        assertEquals(oryginalne.id, zmodyfikowane.id)
        assertEquals(oryginalne.nazwa, zmodyfikowane.nazwa)
        assertEquals(oryginalne.plan, zmodyfikowane.plan)
    }
}