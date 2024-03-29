package com.caredirection.cadi.register.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeSearchItem
import kotlinx.android.synthetic.main.rv_item_register_search_result.view.*

class RegisterSearchAdapter(private val context: Context) : RecyclerView.Adapter<RegisterSearchViewHolder>(){

    var data : List<RvTakeSearchItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnChecked : ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_search_result, parent, false)

        btnChecked = view.findViewById(R.id.btn_register_search_result_check)

        return RegisterSearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSearchViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: RegisterSearchViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_register_search_result_check.background = context.resources.getDrawable(R.drawable.btn_checkbox_selected_product_register)
        }
        else{
            holder.itemView.btn_register_search_result_check.background = context.resources.getDrawable(R.drawable.btn_checkbox_unselected_product_register)
        }
    }

    private fun isItemSelected(position: Int): Boolean{
        return selectedItem.contains(data[position].productIdx)
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedItem.contains(data[position].productIdx)) {
            selectedItem.remove(data[position].productIdx)
        }
        else {
            selectedItem.add(data[position].productIdx)
        }
        notifyItemChanged(position)

        Log.d("명", selectedItem.toString())

        (context as RegisterSearchActivity).checkRegisterButton()
    }
}