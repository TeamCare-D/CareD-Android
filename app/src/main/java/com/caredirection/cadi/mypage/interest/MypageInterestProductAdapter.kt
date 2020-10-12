package com.caredirection.cadi.mypage.interest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageInterestListItem
import kotlinx.android.synthetic.main.rv_item_mypage_interest_product.view.*

class MypageInterestProductAdapter(private val context: Context) : RecyclerView.Adapter<MypageInterestProductViewHolder>(){
    var data: List<RvMypageInterestListItem> = listOf()
    var selectedItem = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageInterestProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_mypage_interest_product, parent, false)

        return MypageInterestProductViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MypageInterestProductViewHolder, position: Int) {
        holder.onBind(data[position])

        setItemBackground(holder, position)

        holder.itemView.setOnClickListener {
            toggleItemSelected(position)
        }
    }

    private fun setItemBackground(holder: MypageInterestProductViewHolder, position: Int){
        if(isItemSelected(position)){
            holder.itemView.btn_mypage_interest.background = context.resources.getDrawable(R.drawable.btn_like_unselected_my)
        }
        else{
            holder.itemView.btn_mypage_interest.background = context.resources.getDrawable(R.drawable.btn_like_my)
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

        notifyDataSetChanged()
    }
}