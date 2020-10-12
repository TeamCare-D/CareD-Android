package com.caredirection.cadi.register.user.ingredient.material

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvIngredientListItem

class RegisterMaterialViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val txtMaterialName : TextView = view.findViewById(R.id.txt_register_material_name)

    fun onBind(material : RvIngredientListItem){
        txtMaterialName.text = material.name

    }
}