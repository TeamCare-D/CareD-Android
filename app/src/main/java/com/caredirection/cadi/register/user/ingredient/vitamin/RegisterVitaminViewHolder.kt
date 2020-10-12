package com.caredirection.cadi.register.user.ingredient.vitamin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvIngredientListItem

class RegisterVitaminViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val txtVitaminName : TextView = view.findViewById(R.id.txt_register_vitamin_name)

    fun onBind(vitamin : RvIngredientListItem){
        txtVitaminName.text = vitamin.name
    }
}