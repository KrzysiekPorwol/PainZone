package porwol.krzysztof.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cwiczenie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ćwiczenieDao(): CwiczenieDao

    companion object {
        @Volatile
        private var instancja: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instancja ?: synchronized(this) {
                instancja ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trening.db"
                ).build().also { instancja = it }
            }
    }

}