package com.caredirection.cadi.register.user.ingredient.vitamin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.IngredientSelectList
import com.caredirection.cadi.data.register.RvIngredientListItem
import kotlinx.android.synthetic.main.rv_item_register_vitamin.view.*

class RegisterVitaminAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterVitaminViewHolder>(){

    var data: List<RvIngredientListItem> = listOf()
    var selectedItem = IngredientSelectList.selectedVitaminList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterVitaminViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_vitamin, parent, false)

        return RegisterVitaminViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterVitaminViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: RegisterVitaminViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_register_vitamin_check.background = context!!.resources.getDrawable(R.drawable.img_selected)
        }
        else{
            holder.itemView.btn_register_vitamin_check.background = context!!.resources.getDrawable(R.drawable.img_empty)
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