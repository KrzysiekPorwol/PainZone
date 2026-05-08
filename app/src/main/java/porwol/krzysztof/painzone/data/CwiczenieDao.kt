package porwol.krzysztof.painzone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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

}