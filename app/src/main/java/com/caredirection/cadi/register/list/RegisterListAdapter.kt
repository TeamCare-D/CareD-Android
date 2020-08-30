package com.caredirection.cadi.register.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterList

class RegisterListAdapter(private val context: Context) : RecyclerView.Adapter<RegisterListViewHolder>(){

    private var dummyRegisterList = DummyRegisterList()
    var data  = dummyRegisterList.getRegisterList()
    private lateinit var btnDeleted : ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_list, parent, false)

        btnDeleted = view.findViewById(R.id.btn_register_list_delete)

        return RegisterListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterListViewHolder, position: Int) {
        holder.onBind(data[position])

        btnDeleted.setOnClickListener {
            showDeleteDialog(position)
        }
    }

    private fun removeItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    private fun showDeleteDialog(position: Int){
        val deleteDialog = AppCompatDialog(context)
        val deleteLayout : LayoutInflater = LayoutInflater.from(context)
        val deleteView : View = deleteLayout.inflate(R.layout.dialog_register_product_delete,null)

        val btnCancel : Button = deleteView.findViewById(R.id.btn_register_product_cancel)
        val btnConfirm : Button = deleteView.findViewById(R.id.btn_register_product_confirm)

        btnCancel.setOnClickListener {
            deleteDialog.cancel()
        }

        btnConfirm.setOnClickListener {
            removeItem(position)
            deleteDialog.dismiss()
        }

        deleteDialog.setContentView(deleteView)
        deleteDialog.setCanceledOnTouchOutside(false)
        deleteDialog.create()
        deleteDialog.show()
    }
}