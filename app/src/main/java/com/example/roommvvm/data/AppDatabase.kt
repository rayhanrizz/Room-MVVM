package com.example.roommvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommvvm.dao.StudentDao
import com.example.roommvvm.entity.Student

@Database(entities = arrayOf(Student::class), version = 1) //membuat table yang berisi entity dari class Student
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        //fungsi untuk cek apakah database sudah ada atau belum dalam device, dan jika belum akan dibuild terlebih dahulu
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) { //untuk membuat database yang bernama student-database
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() { //menghapus instance
            INSTANCE = null
        }
    }
}