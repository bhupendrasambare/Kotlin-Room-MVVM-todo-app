package com.example.todo.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.DataBase.User
import com.example.todo.R
import kotlinx.android.synthetic.main.recycler_layout.view.*

class ListRecycler:RecyclerView.Adapter<ListRecycler.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.recyclerid.text = (position+1).toString()
        holder.itemView.recyclerTitle.text = currentItem.title.toString()
        holder.itemView.recyclerDate.text = currentItem.date.toString()
        holder.itemView.recyclerClick.setOnClickListener{
            val action = ListViewDirections.actionListToView(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}