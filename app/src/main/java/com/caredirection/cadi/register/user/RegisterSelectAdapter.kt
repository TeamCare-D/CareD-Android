package com.caredirection.cadi.register.user

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvSelectListItem
import kotlinx.android.synthetic.main.rv_item_register_product_ingredient.view.*

class RegisterSelectAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterSelectViewHolder>(){
    var data: List<RvSelectListItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSelectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_product_ingredient, parent, false)

        return RegisterSelectViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSelectViewHolder, position: Int) {
        holder.onBind(data[position])

        checkContentEmpty(holder)
    }

    private fun checkContentEmpty(holder: RegisterSelectViewHolder){
        holder.itemView.edt_register_ingredient_content?.addTextChangedListener(object: TextWatcher {
            var contentLength = 0
            override fun afterTextChanged(p0: Editable?) {
                contentLength = holder.itemView.edt_register_ingredient_content?.length()!!

                if(contentLength > 0){
                    holder.itemView.edt_register_ingredient_content.background = context!!.resources.getDrawable(R.drawable.blue_2_line_4)
                }
                else{
                    holder.itemView.edt_register_ingredient_content.background = context!!.getDrawable(R.drawable.gray_line_4)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
    }
}