package com.example.roommvvm.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {
    //deklarasi variabel yg dibutuhkan (memanggil database dan tabel student)
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>()

    fun storeMovie(title: String) { //function berfungsi untuk insert data ke dalam database

        val student = Student()
        student.name = title

        GlobalScope.launch {
            mDb?.studentDao()?.insertStudent(student) //memanggil fungsi insert pada DAO
        }
    }

    fun retrieveStudent(): LiveData<List<Student>> { //function ini berfungsi untuk apabila terjadi perubahan data pada
                                                    // table student-database maka function ini akan me return data terbaru dari student-database
        GlobalScope.launch {
            val list = mDb?.studentDao()?.getAll()

            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        return allStudent
    }
}