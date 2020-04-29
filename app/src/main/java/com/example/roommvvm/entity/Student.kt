package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (//untuk mendeklarasikan jumlah kolom, nama kolom, dan tipe data kolom
    @PrimaryKey(autoGenerate = true) var id: Int? = null,  //mendeklarasikan id dengan tipe data Int, bersifat auto increment dan
                                                             //menjadi primary key atau tipe data yang berisfat unique
    @ColumnInfo var name: String = "" //mendeklarasikan kolom nama dengan tipe data string
)