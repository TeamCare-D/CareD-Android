package com.caredirection.cadi.research.medicine

import android.view.View
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.MedicineItem

class MedicineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val btnMedicine : CheckedTextView = view.findViewById(R.id.btn_item)

    fun onBind(item : MedicineItem){
        btnMedicine.text = item.medicine

        itemView.setOnClickListener {
            btnMedicine.isChecked = !btnMedicine.isChecked
        }
    }
}