package com.caredirection.cadi.register.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterListItem

class RegisterListAdapter(private val context: Context) : RecyclerView.Adapter<RegisterListViewHolder>(){

    var data : List<RegisterListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_list, parent, false)

        return RegisterListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterListViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}