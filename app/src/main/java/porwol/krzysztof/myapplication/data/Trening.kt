package porwol.krzysztof.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "treningi")
data class Trening(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val data: Long,
    val cwiczenieId: Long,
    val cwiczenieNazwa: String,
    val plan: Plan,
    val wyniki: String
)