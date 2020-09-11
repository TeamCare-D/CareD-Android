package com.caredirection.cadi.research.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.ResearchDetailItem

class ResearchDetailAdapter(private val context: Context) : RecyclerView.Adapter<ResearchDetailViewHolder>(){

    var data : List<ResearchDetailItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research, parent, false)

        return ResearchDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResearchDetailViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}