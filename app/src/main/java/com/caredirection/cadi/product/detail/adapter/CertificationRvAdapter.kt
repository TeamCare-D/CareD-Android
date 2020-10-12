package com.caredirection.cadi.product.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.ProductCertificationData
import kotlinx.android.synthetic.main.activity_product_detail.*

class CertificationRvAdapter: RecyclerView.Adapter<CertificationRvAdapter.CertificationRvHolder>() {

    val items = mutableListOf<ProductCertificationData>()

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
        val img_rv_item_product_certification: ImageView = itemView.findViewById(R.id.img_rv_item_product_certification)

        fun bind(item: ProductCertificationData){
            Glide.with(itemView.context).load(item.certification_image_key).into(img_rv_item_product_certification)
        }
    }
}