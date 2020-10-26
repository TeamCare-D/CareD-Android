package com.caredirection.cadi.register.user

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.IngredientSelectList
import com.caredirection.cadi.data.register.RvSelectListItem
import kotlinx.android.synthetic.main.rv_item_register_product_ingredient.view.*

class RegisterSelectAdapter (private val context: Context?) : RecyclerView.Adapter<RegisterSelectViewHolder>(){
    var data: List<RvSelectListItem> = listOf()

    private lateinit var txtIngredientUnit : TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterSelectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_register_product_ingredient, parent, false)

        txtIngredientUnit = view.findViewById(R.id.txt_register_ingredient_unit)

        return RegisterSelectViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RegisterSelectViewHolder, position: Int) {
        holder.onBind(data[position])

        checkContentEmpty(holder, position)

        unitClickListener(holder, position)
    }

    private fun checkContentEmpty(holder: RegisterSelectViewHolder, position: Int){
        holder.itemView.edt_register_ingredient_content?.addTextChangedListener(object: TextWatcher {
            var contentLength = 0
            override fun afterTextChanged(p0: Editable?) {
                contentLength = holder.itemView.edt_register_ingredient_content?.length()!!

                if(contentLength > 0){
                    holder.itemView.edt_register_ingredient_content.background = context!!.resources.getDrawable(R.drawable.blue_2_line_4)
                    data[position].content = holder.itemView.edt_register_ingredient_content.text.toString()
                }
                else{
                    holder.itemView.edt_register_ingredient_content.background = context!!.getDrawable(R.drawable.gray_line_4)
                }
                IngredientSelectList.selectedContent.add(holder.itemView.edt_register_ingredient_content.text.toString())
                (context as RegisterProductActivity).checkNextButton()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
    }

    private fun unitClickListener(holder: RegisterSelectViewHolder, position: Int){
        holder.itemView.btn_register_ingredient_unit.setOnClickListener {
            IngredientSelectList.showUnitPicker(context!!, data[position].unit, txtIngredientUnit)
        }
    }
}