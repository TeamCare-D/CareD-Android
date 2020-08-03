package com.caredirection.cadi.research.interest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.InterestItem

class InterestAdapter(private val context: Context) : RecyclerView.Adapter<InterestViewHolder>() {

    var data : List<InterestItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        return InterestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}