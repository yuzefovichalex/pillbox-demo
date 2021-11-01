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

    abstract fun medicineDao(): MedicineDao

    private class Callback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val medicineDao = database.medicineDao()

                    // Delete all content here.
                    medicineDao.deleteAll()

                    listOf(
                        MedicineData(
                            1L,
                            "Aspirin",
                            "Fever in infectious and inflammatory diseases; pain syndrome of mild to moderate intensity of various genesis (including neuralgia, myalgia, headache)",
                            100, "pcs",
                            500, "mg",
                            1665705600000,
                            "tablet"
                        ),
                        MedicineData(
                            2L,
                            "Amoxicillin",
                            "For use as monotherapy and in combination with clavulanic acid: infectious and inflammatory diseases caused by susceptible microorganisms",
                            20, "pcs",
                            500, "mg",
                            1665705600000,
                            "capsule"
                        ),
                        MedicineData(
                            3L,
                            "Multivitamins",
                            "It contains an essential vitamin complex to support effective metabolism and healthy functioning of the immune system.",
                            30, "pcs",
                            2, "g",
                            1665705600000,
                            "tablet"
                        ),
                        MedicineData(
                            4L,
                            "Azithromycin",
                            "",
                            3, "pcs",
                            500, "mg",
                            1665705600000,
                            "tablet"
                        )
                    ).forEach {
                        medicineDao.saveMedicine(it)
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