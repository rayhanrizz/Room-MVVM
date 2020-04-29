package com.example.roommvvm.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.entity.Student

//class yg berisi adapter dari list data tabel student melalui Recycler View
class StudentRecyclerAdapter(private val myDataset: List<Student>):
        RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() { //menampilkan data mahasiswa pada list recycler

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //mendeklarasikan textview dari layout
        var tvName: TextView = itemView.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder { //menambahkan textview kedalam list recycle item
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return StudentViewHolder(v)
    }

    override fun getItemCount(): Int { //menghitung jumlah data mahasiswa
        return myDataset.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) { //penempatan data mahasiswa pada recycler_item
        holder.tvName.text = myDataset[position].name
    }
}