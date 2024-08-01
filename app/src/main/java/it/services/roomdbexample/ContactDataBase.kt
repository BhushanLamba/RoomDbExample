package it.services.roomdbexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contacts::class], version = 4)
@TypeConverters(Convertors::class)
abstract class ContactDataBase : RoomDatabase() {

    abstract fun contactDao(): ContactDao


    companion object {

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN relation TEXT NOT NULL DEFAULT 'NA'")
            }
        }

        val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN lastName TEXT NOT NULL DEFAULT 'NA'")
            }
        }

        val migration_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN middleName TEXT NOT NULL DEFAULT 'NA'")
            }
        }

        //Text String


        @Volatile
        private var INSTANCE: ContactDataBase? = null

        fun getDataBase(context: Context): ContactDataBase {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE =
                        Room.databaseBuilder(context, ContactDataBase::class.java, "contactDb")
                            .addMigrations(migration_1_2)
                            .addMigrations(migration_2_3)
                            .addMigrations(migration_3_4)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}