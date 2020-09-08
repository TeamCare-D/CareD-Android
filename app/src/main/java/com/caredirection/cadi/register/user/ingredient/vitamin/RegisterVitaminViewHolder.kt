package com.caredirection.cadi.register.user.ingredient.vitamin

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvIngredientListItem

class RegisterVitaminViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val txtVitaminName : TextView = view.findViewById(R.id.txt_register_vitamin_name)
    private val btnVitaminCheck : CheckBox = view.findViewById(R.id.btn_register_vitamin_check)

    fun onBind(vitamin : RvIngredientListItem){
        txtVitaminName.text = vitamin.name

        itemView.setOnClickListener {
            btnVitaminCheck.isChecked = !btnVitaminCheck.isChecked
        }
    }
}