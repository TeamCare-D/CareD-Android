package com.caredirection.cadi.register.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeSearchItem

class RegisterSearchAdapter(private val context: Context) : RecyclerView.Adapter<RegisterSearchViewHolder>(){

    var data : List<RvTakeSearchItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    private lateinit var btnChecked : CheckBox

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

        setItemClickListener(holder, position)
    }

    private fun setItemClickListener(holder: RegisterSearchViewHolder, position: Int){
        holder.itemView.setOnClickListener {
            if(selectedItem.contains(data[position].productIdx)){
                selectedItem.remove(data[position].productIdx)
                Log.d("명",data[position].productIdx.toString()+"삭제")
            }
            else{
                selectedItem.add(data[position].productIdx)
                Log.d("명",data[position].productIdx.toString()+"추가")
            }
        }
    }
}