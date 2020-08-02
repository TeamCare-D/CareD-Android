package com.caredirection.cadi.research.disease

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.DiseaseItem

class DiseaseAdapter(private val context: Context) : RecyclerView.Adapter<DiseaseViewHolder>(){

    var data : List<DiseaseItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_disease, parent, false)

        return DiseaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}