package com.song2.cadi.home.homecare

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.song2.cadi.R
import kotlinx.android.synthetic.main.rv_item_home_care.view.*

class HomeCareRvHolder(private val view: View, private val context: Context):RecyclerView.ViewHolder(view){
    val txtCareName:TextView = view.findViewById(R.id.txt_rv_item_care_name)

    fun bind(txt:String){
        txtCareName.text=txt
    }

}