package porwol.krzysztof.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cwiczenia")
data class Cwiczenie (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nazwa: String,
    val serie: Int,
    val plan: Plan
)