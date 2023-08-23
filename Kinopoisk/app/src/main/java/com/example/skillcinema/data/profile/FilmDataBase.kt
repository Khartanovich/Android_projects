package com.example.skillcinema.data.profile

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.skillcinema.entity.profile.Collections
import com.example.skillcinema.entity.profile.FilmEntity
import com.example.skillcinema.entity.profile.FilmsAndCollectionsCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [FilmEntity::class,
        Collections::class,
        FilmsAndCollectionsCrossRef::class], version = 1, exportSchema = false
)
abstract class FilmDataBase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

//    companion object {
//        @Volatile
//        private var INSTANCE: FilmDataBase? = null
//
////        fun getDataBase(context: Context, scope: CoroutineScope): FilmDataBase {
////            return INSTANCE ?: synchronized(this) {
////                val instance = Room.databaseBuilder(
////                    context.applicationContext,
////                    FilmDataBase::class.java,
////                    "film_data_base"
////                )
////                    .addCallback(FilmDataBaseCallback(scope))
////                    .build()
////                INSTANCE = instance
////                instance
////            }
////        }
//    }


}

class FilmDataBaseCallback (private val provider: Provider<FilmDao> ) :
    RoomDatabase.Callback() {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
            scope.launch {
                provider.get().insertNewCollection(Collections("Любимые"))
                provider.get().insertNewCollection(Collections("Хочу посмотреть"))
            }
    }
}