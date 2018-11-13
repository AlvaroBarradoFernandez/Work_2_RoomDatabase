package com.utad.bbdd_clase

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.AsyncTask

@Database(entities = [ClassEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun studentsDao():ClassDao

    companion object {
        private val DB_NAME = BuildConfig.DATABASE_NAME
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Application): AppDatabase =
                INSTANCE ?: synchronized(lock = this){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun buildDatabase(context: Application) =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(sRoomDatabaseCallback)
                        .build()

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE).execute()
            }
        }


        private class PopulateDbAsync internal constructor(db: AppDatabase?): AsyncTask<String, String, Int>() {
            override fun doInBackground(vararg params: String?): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        private var classes:List<ClassEntity> = listOf(
                ClassEntity(teacherName= "Pedro", subjectName= "Computing", yearNum= 2018),
                ClassEntity(teacherName = "David", subjectName = "Android", yearNum = 2018),
                ClassEntity(teacherName = "Cristina", subjectName = "Inglés", yearNum = 2018),
                ClassEntity(teacherName = "Carlos", subjectName = "IOS", yearNum = 2018),
                ClassEntity(teacherName = "Jaime", subjectName = "ADAT", yearNum = 2018),
                ClassEntity(teacherName = "Dani", subjectName = "TFG", yearNum = 2018),
                ClassEntity(teacherName = "Meritxell", subjectName = "Empresa", yearNum = 2018),
                ClassEntity(teacherName = "Laura", subjectName = "SGEM", yearNum = 2018),
                ClassEntity(teacherName = "Margarita", subjectName = "Inglés extra", yearNum = 2018),
                ClassEntity(teacherName = "Alvaro B.", subjectName = "Autodidacta", yearNum = 2018)

        )



        }
    }
}

/*class StundentRepository {
}*/