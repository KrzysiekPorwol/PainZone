package porwol.krzysztof.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TreningDao {

    @Insert
    suspend fun dodaj(trening: Trening): Long

    @Query("SELECT * FROM treningi WHERE cwiczenieId = :cwiczenieId ORDER BY data DESC LIMIT 1")
    fun obserwujOstatniDlaCwiczenia(cwiczenieId: Long): Flow<Trening?>

    @Query("SELECT * FROM treningi WHERE `plan` = :plan ORDER BY data DESC")
    fun obserwujHistoriePlanu(plan: Plan): Flow<List<Trening>>
}