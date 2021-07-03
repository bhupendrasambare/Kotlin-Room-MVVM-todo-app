package com.example.todo.update

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.DataBase.User
import com.example.todo.DataBase.UserViewModel
import com.example.todo.R
import kotlinx.android.synthetic.main.fragment_insert.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
class Update : Fragment() {

    private val argsUpdate by navArgs<UpdateArgs>()

    private lateinit var mUserViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateTitle.setText(argsUpdate.parcelUpdate.title.toString())
        view.updateSubtitle.setText(argsUpdate.parcelUpdate.subTitle.toString())

        view.updateBtn.setOnClickListener{
            updateDataToDataBase()
        }
        return view
    }
    private fun updateDataToDataBase() {
        val updateT = updateTitle.text.toString()
        val updateSub = updateSubtitle.text.toString()
        val updateDate = date()
        if(!inputCheck(updateT,updateSub,updateDate)){

            val userUpdate = User(argsUpdate.parcelUpdate.id,updateT,updateSub,updateDate)

            mUserViewModel.updateUser(userUpdate)

            Toast.makeText(requireContext(),"Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_to_list)
        }else{
            Toast.makeText(requireContext(),"Fail to add task", Toast.LENGTH_SHORT).show()
        }

    }
    private fun inputCheck(title: String, sub: String, date: String): Boolean {
        return (TextUtils.isEmpty(title) && TextUtils.isEmpty(sub))
    }

    private fun date(): String {
        val current = LocalDateTime.now()
        return current.format(DateTimeFormatter.ofPattern("dd/MM/yyy")).toString()
    }
}