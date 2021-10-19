package com.alexyuzefovich.pillbox.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alexyuzefovich.pillbox.data.db.dao.MedicineDao
import com.alexyuzefovich.pillbox.data.db.model.MedicineData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [MedicineData::class], version = 1, exportSchema = false)
abstract class PillBoxDatabase : RoomDatabase() {

    abstract fun pillDao(): MedicineDao

    private class Callback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val pillDao = database.pillDao()

                    // Delete all content here.
                    pillDao.deleteAll()

                    listOf(
                        MedicineData(
                            0L,
                            "Ибупрофен",
                            "Обезболивающее",
                            50, "pcs",
                            200, "mg",
                            1665705600000,
                            "TABLET"
                        ),
                        MedicineData(
                            1L,
                            "Уролесан",
                            "При цистите и проблемах с мочевым",
                            40, "pcs",
                            null, "mg",
                            1665705600000,
                            "CAPSULE"
                        ),
                        MedicineData(
                            2L,
                            "Доксициклин",
                            "Антибиотик, назначали для мочевого",
                            10, "pcs",
                            100, "mg",
                            1665705600000,
                            "TABLET"
                        ),
                        MedicineData(
                            3L,
                            "Солидагорен",
                            null,
                            50, "ml",
                            null, "mg",
                            1665705600000,
                            "TABLET"
                        )
                    ).forEach {
                        pillDao.savePill(it)
                    }
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PillBoxDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PillBoxDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    PillBoxDatabase::class.java,
                    "pillbox_database"
                ).addCallback(Callback(scope)).build().also {
                    INSTANCE = it
                }
            }
        }
    }

}