package porwol.krzysztof.painzone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Cwiczenie::class, Trening::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ćwiczenieDao(): CwiczenieDao
    abstract fun treningDao(): TreningDao

    companion object {
        @Volatile
        private var instancja: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `treningi` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`data` INTEGER NOT NULL, " +
                        "`cwiczenieId` INTEGER NOT NULL, " +
                        "`cwiczenieNazwa` TEXT NOT NULL, " +
                        "`plan` TEXT NOT NULL, " +
                        "`wyniki` TEXT NOT NULL" +
                    ")"
                )
            }
        }

        fun get(context: Context): AppDatabase =
            instancja ?: synchronized(this) {
                instancja ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trening.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { instancja = it }
            }
    }

}