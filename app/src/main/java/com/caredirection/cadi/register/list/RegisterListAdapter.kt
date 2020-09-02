package com.caredirection.cadi.register.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterList

class RegisterListAdapter(private val context: Context) : RecyclerView.Adapter<RegisterListViewHolder>(){

    private val registerListActivity = RegisterListActivity()
    private var dummyRegisterList = DummyRegisterList()
    var data  = dummyRegisterList.getRegisterList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_list, parent, false)

        return RegisterListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterListViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            showDeleteDialog(position)
        }
    }

    private fun removeItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
        //checkCompleteButton()
    }

//    private fun checkCompleteButton(){
//        if(itemCount == 0){
//            Log.d("명",btnComplete.isEnabled.toString())
//            btnComplete.isEnabled = false
//            btnComplete.setTextColor(context.resources.getColor(R.color.colorWhite))
//            registerListActivity.btn_register_list_skip.visibility = View.VISIBLE
//            registerListActivity.btn_register_list_close.visibility = View.GONE
//        }
//    }

    private fun showDeleteDialog(position: Int){
        val deleteDialog = AppCompatDialog(context)
        val deleteLayout : LayoutInflater = LayoutInflater.from(context)
        val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup,null)

        val btnCancel : Button = deleteView.findViewById(R.id.btn_popup_cancel)
        val btnConfirm : Button = deleteView.findViewById(R.id.btn_popup_confirm)

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