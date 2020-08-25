package com.caredirection.cadi.product.detail.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.dialog_proudct_ingredient_explaination.view.*

class RvCategoryAdapter(val context: Context): RecyclerView.Adapter<RvCategoryAdapter.RvCategoryHoler>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvCategoryHoler {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_detail_ingredient_category, parent, false)
        return RvCategoryHoler(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RvCategoryHoler, position: Int) {
        holder.bind(items[position])
    }

    inner class RvCategoryHoler(view: View): RecyclerView.ViewHolder(view){
        val txt_rv_item_detail_ingredient_category: TextView = itemView.findViewById(R.id.txt_rv_item_detail_ingredient_category)

        fun bind(text: String){
            txt_rv_item_detail_ingredient_category.text = text

            itemView.setOnClickListener {
                dialogSetting(context)
            }
        }
    }

    fun dialogSetting(conext: Context){


        val builder = AlertDialog.Builder(conext)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_proudct_ingredient_explaination, null)

        ///여기 하기
        dialogView.txt_dialog_product_ingredient_explainaion_title.text = "비타민B 컴플랙스"
        dialogView.txt_dialog_product_ingredient_explainaion_content.text = "구연산, 글리콘산 등의 유기염과 \n" +
                "결합된 마그네슘을 통칭해요.\n" +
                "마그네슘의 여러 제형들 중 중간정도의 흡수율, 중간정도의 가격대의 무난한 종류입니다.\n" +
                "\n" +
                "위산이 있어야 흡수가 잘 되는 산화 마그네슘의 단점을 보완해 대부분의 환경에서도 \n" +
                "무난히 흡수되는 종류입니다."

        val dialog = builder.setView(dialogView).show()

        dialogView.btn_dialog_product_ingredient_explaination_close.setOnClickListener {
            dialog.dismiss()
        }
    }
}