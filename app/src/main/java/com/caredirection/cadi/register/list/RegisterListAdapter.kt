package com.caredirection.cadi.register.list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.DeleteTakeData
import com.caredirection.cadi.data.register.RvTakeListItem
import com.caredirection.cadi.network.RequestURL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterListAdapter(private val context: Context) : RecyclerView.Adapter<RegisterListViewHolder>(){

    var data  : List<RvTakeListItem> = listOf()

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
            //deleteTakeProductResponse(position)
            deleteDialog.dismiss()
        }

        deleteDialog.setContentView(deleteView)
        deleteDialog.setCanceledOnTouchOutside(false)
        deleteDialog.create()
        deleteDialog.show()
    }

    private fun deleteTakeProductResponse(idx: Int){
        val call: Call<DeleteTakeData> = RequestURL.service.deleteTakeProduct(
            product_idx = idx,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<DeleteTakeData> {
                override fun onFailure(call: Call<DeleteTakeData>, t: Throwable) {
                    Log.d("복용 제품 삭제 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<DeleteTakeData>,
                    response: Response<DeleteTakeData>
                ) {
                    if(response.isSuccessful){
                        val message=response.body()!!.message

                        notifyDataSetChanged()

                        Log.d("복용 제품 삭제 성공", "메시지 : $message")
                    }
                }

            }
        )
    }
}