package com.song2.cadi.home.homecare

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.song2.cadi.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeCareRvAdapter (private val context:Context):RecyclerView.Adapter<HomeCareRvHolder>(){
    var data = arrayListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCareRvHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.rv_item_home_care,parent,false)
        return HomeCareRvHolder(view,context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeCareRvHolder, position: Int) {
        holder.bind(data[position])
    }

}