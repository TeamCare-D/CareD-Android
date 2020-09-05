package com.caredirection.cadi.mypage.interest

import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageInterestListItem

class MypageInterestProductAdapter(private val context: Context) : RecyclerView.Adapter<MypageInterestProductViewHolder>(){
    var data: List<RvMypageInterestListItem> = listOf()
    var selectedItem = SparseBooleanArray()

    private lateinit var btnDelete : CheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageInterestProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_mypage_interest_product, parent, false)

        btnDelete= view.findViewById(R.id.btn_mypage_interest)

        return MypageInterestProductViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MypageInterestProductViewHolder, position: Int) {
        holder.onBind(data[position])

        btnDelete.setOnClickListener {
            if(selectedItem.get(position)){
                selectedItem.delete(position)
                Log.d("명",position.toString()+"삭제")
            }
            else{
                selectedItem.put(position,true)
                Log.d("명",position.toString()+"추가")
            }

        }
    }
}