package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

@Dao
interface StudentDao {
    @Query("Select * from student") //query dan function untuk menampilkan semua data mahasiswa
    fun getAll(): List<Student>

    @Insert
    fun insertStudent(item: Student) //function untuk melakukan insert data mahasiswa
}