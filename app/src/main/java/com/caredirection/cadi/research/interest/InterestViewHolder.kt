package com.caredirection.cadi.research.interest

import android.view.View
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.InterestItem

class InterestViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val btnInterest : CheckedTextView = view.findViewById(R.id.btn_item)

    fun onBind(item: InterestItem){
        btnInterest.text = item.interest

        itemView.setOnClickListener {
            btnInterest.isChecked = !btnInterest.isChecked
        }
    }

}