package com.caredirection.cadi.register.search

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterSearchListItem

class RegisterSearchAdapter(private val context: Context) : RecyclerView.Adapter<RegisterSearchViewHolder>(){

    var data : List<RegisterSearchListItem> = listOf()
    private lateinit var btnChecked : Button

    var selectedItems = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_search_result, parent, false)

        btnChecked = view.findViewById(R.id.btn_register_search_result_check)

        return RegisterSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSearchViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemClickListener(holder, position)
    }

    private fun setItemClickListener(holder: RegisterSearchViewHolder, position: Int){
        holder.itemView.setOnClickListener {
            //btnChecked.isEnabled = !btnChecked.isEnabled
        }
    }
}