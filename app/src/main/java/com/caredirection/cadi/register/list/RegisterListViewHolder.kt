package com.caredirection.cadi.register.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RegisterListItem

class RegisterListViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txtProductBrand : TextView = view.findViewById(R.id.txt_register_list_brand)
    private val txtProductName : TextView = view.findViewById(R.id.txt_register_list_name)
    private val txtProductOverseas : TextView = view.findViewById(R.id.txt_register_list_overseas)
    private val txtProductDay : TextView = view.findViewById(R.id.txt_register_list_day)
    private val btnDeleted : ImageView = view.findViewById(R.id.btn_register_list_delete)

    fun onBind(product: RegisterListItem){
        txtProductBrand.text = product.brand
        txtProductName.text = product.name
        txtProductOverseas.text = product.overseas
        txtProductDay.text = product.day.toString()

        btnDeleted.setOnClickListener {
//            showDeleteDialog()
        }
    }

//    private fun showDeleteDialog(){
//        val fragmentManager = FragmentManager
//        val registerListDeleteFragment = RegisterListDeleteFragment()
//
//        registerListDeleteFragment.show(fragmentManager,"DeleteDialog")
//    }
}