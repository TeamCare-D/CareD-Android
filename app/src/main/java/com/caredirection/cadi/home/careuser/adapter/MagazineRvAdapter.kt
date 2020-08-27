package com.caredirection.cadi.home.careuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class MagazineRvAdapter: RecyclerView.Adapter<MagazineRvAdapter.MagazineRvHolder>() {

    var items = mutableListOf<MagazineRvData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazineRvHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_magazine_ingredient, parent, false)
        return MagazineRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MagazineRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MagazineRvHolder(view: View): RecyclerView.ViewHolder(view){



        fun bind(item: MagazineRvData){

        }
    }

}
data class MagazineRvData(
    var title: String,
    var tag: MutableList<String>
)