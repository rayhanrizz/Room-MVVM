package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roommvvm.R
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.helper.StudentRecyclerAdapter
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class NameListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")

            rvList.adapter = StudentRecyclerAdapter(it)
        })
    }
    // mendeklarasikan function ini untuk menampilkan fragment list mahasiswa
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList.layoutManager = LinearLayoutManager(activity)

        btnAdd.setOnClickListener {
        //berfungsi untuk memberikan aksi pada button add pada fragment list mahasiswa
            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()
            GlobalScope.launch {
                dao?.getAll() //mengambil data semua mahasiswa
            }

            listener?.goToNewNameFragment() //menampilkan fragment add mahasiswa
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() { //fungsi yg berjalan ketika fragment dinonaktifkan atau replaced
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener { //mengarahkan ke fragment add mahasiswa
        fun goToNewNameFragment()
    }

    companion object { //membuat companion agar pemanggilan fungsi diluar class tidak membutuhkan objek
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
