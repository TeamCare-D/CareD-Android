package com.caredirection.cadi.register.user.ingredient.material

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.IngredientSelectList
import com.caredirection.cadi.data.register.RvIngredientListItem
import kotlinx.android.synthetic.main.rv_item_register_material.view.*

class RegisterMaterialAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterMaterialViewHolder>(){

    var data: List<RvIngredientListItem> = listOf()
    var selectedItem = IngredientSelectList.selectedMaterialList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterMaterialViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_material,parent,false)

        return RegisterMaterialViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterMaterialViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: RegisterMaterialViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_register_material_check.background = context!!.resources.getDrawable(R.drawable.img_selected)
        }
        else{
            holder.itemView.btn_register_material_check.background = context!!.resources.getDrawable(R.drawable.img_empty)
        }

    }

    private fun isItemSelected(position: Int): Boolean{
        return selectedItem.contains(data[position].name)
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedItem.contains(data[position].name)) {
            selectedItem.remove(data[position].name)
        }
        else {
            selectedItem.add(data[position].name)
        }

        notifyDataSetChanged()
    }
}