package com.caredirection.cadi.research.medicine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.MedicineItem

class MedicineAdapter(private val context: Context) : RecyclerView.Adapter<MedicineViewHolder>() {

    var data : List<MedicineItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_research,parent,false)

        return MedicineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}