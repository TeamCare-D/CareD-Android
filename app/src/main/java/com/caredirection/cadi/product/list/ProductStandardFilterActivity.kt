package com.caredirection.cadi.product.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ProductFilterRvAdapter
import kotlinx.android.synthetic.main.activity_product_standard_filter.*

class ProductStandardFilterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_standard_filter)

        rvStandardSetting()

        rvCategorySetting()

        emptySetting()
    }
    fun rvStandardSetting(){
        val standardRvAdapter = ProductFilterRvAdapter()
        standardRvAdapter.items.add("1번째 범위 미만")
        standardRvAdapter.items.add("1번째 범위 미만")
        standardRvAdapter.items.add("1번째 범위 미만")

        rv_product_standard_filter_content.adapter = standardRvAdapter
    }

    fun rvCategorySetting(){
        val categoryRvAdapter = ProductFilterRvAdapter()
        categoryRvAdapter.items.add("산화마그네슘")
        categoryRvAdapter.items.add("산화마그네슘")
        categoryRvAdapter.items.add("산화마그네슘")
        categoryRvAdapter.items.add("산화마그네슘")

        rv_product_standard_filter_category.adapter =categoryRvAdapter
    }

    fun emptySetting(){
        val emptyRvAdapter = ProductFilterRvAdapter()
        emptyRvAdapter.items.add("testsetest")
        emptyRvAdapter.items.add("testsetest")
        emptyRvAdapter.items.add("testsetest")


        rv_product_standard_filter_empty.adapter =emptyRvAdapter
    }
}