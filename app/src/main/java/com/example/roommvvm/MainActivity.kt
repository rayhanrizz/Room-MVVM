package com.example.roommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roommvvm.fragment.NameListFragment
import com.example.roommvvm.fragment.NewNameFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(),
    NewNameFragment.OnFragmentInteractionListener,
    NameListFragment.OnFragmentInteractionListener{

    //ini fungsi utama dalam class yg dipanggil pertama kali ketika class dipanggil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) { //mengecek kondisi untuk menampilkan fragment pada saat aplikasi dijalankan
            goToStudentListFragment() //memanggil fungsi untuk menampilkan fragment student list
        }

        Timber.plant(Timber.DebugTree())
    }

    override fun goToStudentListFragment() { //function untuk memanggil fragment list mahasiswa
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        //memulai transaksi untuk berpindah ke fragment lainnya
        transaction.replace(R.id.flContent, NameListFragment.newInstance())
        transaction.commit()
    }

    override fun goToNewNameFragment() { //function untuk memanggil fragment add mahasiswa
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        //memulai transaksi untuk berpindah ke fragment lainnya
        transaction.replace(R.id.flContent, NewNameFragment.newInstance())
        transaction.commit()
    }
}
