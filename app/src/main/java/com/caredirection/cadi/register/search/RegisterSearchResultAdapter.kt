package com.caredirection.cadi.register.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterSearchResultListItem

class RegisterSearchResultAdapter(private val context: Context) : RecyclerView.Adapter<RegisterSearchResultViewHolder>(){

    var data : List<RegisterSearchResultListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSearchResultViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_search_result, parent, false)

        return RegisterSearchResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSearchResultViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}