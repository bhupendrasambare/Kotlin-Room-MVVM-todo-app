package com.example.todo.List

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.DataBase.UserViewModel
import com.example.todo.MainActivity
import com.example.todo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListView : Fragment() {
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.recycle
        val adapter = ListRecycler()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        recyclerView.adapter = adapter
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_list_to_insert)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delet_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }
    fun deleteAllUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _, ->
            mUserViewModel.deleteAll()
            Toast.makeText(requireContext(),"deleted succesFully",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _, ->}
            builder.setTitle("Delete ")
            builder.setMessage("Are you shure you want to delete this ")
            builder.create().show()
    }
}