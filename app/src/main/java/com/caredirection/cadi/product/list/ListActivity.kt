package com.caredirection.cadi.product.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.list.adapter.ProductListRvAdapter
import kotlinx.android.synthetic.main.activity_product_list.*

class ListActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_product_list_detail, FragmentDetail()).commit()

        val productListAdapter = ProductListRvAdapter(this@ListActivity)

        productListAdapter.items.add("String")
        productListAdapter.items.add("String")
        productListAdapter.items.add("String")
        productListAdapter.items.add("String")

        rv_product_list.adapter = productListAdapter
        rv_product_list.layoutManager = LinearLayoutManager(this@ListActivity)


    }
}