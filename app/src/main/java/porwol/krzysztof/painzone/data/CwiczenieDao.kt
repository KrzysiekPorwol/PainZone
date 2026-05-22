package porwol.krzysztof.painzone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CwiczenieDao {

    @Query("SELECT * FROM cwiczenia WHERE `plan` = :plan ORDER BY id ASC")
    fun obserwujPlan(plan: Plan): Flow<List<Cwiczenie>>

    @Insert
    suspend fun dodaj(cwiczenie: Cwiczenie): Long

    @Delete
    suspend fun usun(cwiczenie: Cwiczenie)

    @Query("SELECT COUNT(*) FROM cwiczenia WHERE plan = :plan")
    suspend fun policzWPlanie(plan: Plan): Int

    /**
     * Liczy ćwiczenia w planie i dodaje nowe tylko jeśli limit nie jest przekroczony.
     * Dzięki @Transaction sprawdzenie i wstawienie są jedną, niepodzielną operacją —
     * dwa równoległe wywołania nie mogą oba „zobaczyć” tej samej wolnej liczby miejsc.
     * Zwraca true, gdy ćwiczenie zostało dodane; false, gdy plan był już pełny.
     */
    @Transaction
    suspend fun dodajZLimitem(cwiczenie: Cwiczenie, limit: Int): Boolean {
        if (policzWPlanie(cwiczenie.plan) >= limit) return false
        dodaj(cwiczenie)
        return true
    }

}