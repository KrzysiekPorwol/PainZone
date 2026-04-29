package porwol.krzysztof.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cwiczenie::class, Trening::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ćwiczenieDao(): CwiczenieDao
    abstract fun treningDao(): TreningDao

    companion object {
        @Volatile
        private var instancja: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instancja ?: synchronized(this) {
                instancja ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trening.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { instancja = it }
            }
    }

}