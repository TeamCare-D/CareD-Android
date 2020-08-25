package com.caredirection.cadi.product.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caredirection.cadi.R

class CertificationRvAdapter: RecyclerView.Adapter<CertificationRvAdapter.CertificationRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_product_certification, parent, false)
        return CertificationRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CertificationRvHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CertificationRvHolder(view: View): RecyclerView.ViewHolder(view){

        fun bind(item: String){

        }
    }
}