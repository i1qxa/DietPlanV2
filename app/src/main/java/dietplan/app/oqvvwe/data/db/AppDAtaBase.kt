package dietplan.app.oqvvwe.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dietplan.app.oqvvwe.data.remote.entities.RecipeItemShort
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [
        DataDB::class,
        RecipeDB::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    abstract fun recipeDao():RecipeDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "recipe_db"

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            kotlinx.coroutines.internal.synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}