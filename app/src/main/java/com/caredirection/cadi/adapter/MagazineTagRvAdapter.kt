package com.caredirection.cadi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class MagazineTagRvAdapter(val context: Context): RecyclerView.Adapter<MagazineTagRvAdapter.MagazineTagRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazineTagRvHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_magazine_tag, parent, false)
        return MagazineTagRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MagazineTagRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MagazineTagRvHolder(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_magazine_tag: TextView = view.findViewById(R.id.txt_rv_item_magazine_tag)

        fun bind(text: String){
            txt_rv_item_magazine_tag.text = text
        }
    }
}