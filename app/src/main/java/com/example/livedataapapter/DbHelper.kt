package com.example.livedataapapter

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DB_VERSION = 2
private const val DB_NAME = "db_flighy.db"
private const val TAG = "DbHelper"

@Database(entities = [Flight::class], version = DB_VERSION, exportSchema = false)
abstract class DbHelper: RoomDatabase() {
    abstract val flightDao: FlightDao

    companion object {
        @Volatile
        private var INSTANCE: DbHelper? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): DbHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbHelper::class.java,
                    DB_NAME
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(DbInitCallback(coroutineScope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        DbHelper::class.java,
//                        DB_NAME
//                    )
////                        .fallbackToDestructiveMigration()
//                        .addCallback(DbInitCallback(coroutineScope))
//                        .build()
//                    INSTANCE = instance
//                }
//
//                // Return instance; smart cast to be non-null.
//                return instance
//            }
        }
    }

    private class DbInitCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            //Chamado após ter um abertura de conexao com o banco
            INSTANCE?.let {db ->
                scope.launch(Dispatchers.IO) {
                    populateData(db.flightDao)
                }
            }
        }

        private fun populateData(dao: FlightDao) {
            dao.clear()
            Log.i(TAG, "Name: ${Thread.currentThread().name} id:  ${Thread.currentThread().id}")
            val flights = FlightDummy.data()
            flights.forEach {
                dao.insert(it)
            }
        }
    }
}
