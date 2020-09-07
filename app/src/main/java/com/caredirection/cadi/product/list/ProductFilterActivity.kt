package com.caredirection.cadi.product.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ProductFilterFromRvAdapter
import com.caredirection.cadi.adapter.ProductFilterRvAdapter
import kotlinx.android.synthetic.main.activity_product_filter.*

class ProductFilterActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_filter)


        seekbarSetting()

        rvMaintainMonth()

        rvFormCategory()

        rvBrand()

        rvRange()

        closeSetting()

    }

    fun seekbarSetting(){
        seekbar_product_filter.setOnRangeSeekbarChangeListener{ minValue, maxValue ->
            txt_product_filter_minimum.text = minValue.toString()
            txt_product_filter_maximum.text = maxValue.toString()
        }
    }

    fun rvMaintainMonth(){
        val maintainMonthRvAdapter = ProductFilterRvAdapter()
        maintainMonthRvAdapter.items.add("1개월 미만")
        maintainMonthRvAdapter.items.add("1개월 미만")
        maintainMonthRvAdapter.items.add("1개월 미만")
        rv_product_filter_maintain_month.adapter = maintainMonthRvAdapter
    }

    fun rvFormCategory(){
        val rvFormCategoryRvAdapter = ProductFilterFromRvAdapter()

        rvFormCategoryRvAdapter.items.add("동물성 캡슐")
        rvFormCategoryRvAdapter.items.add("식물성 캡슐")
        rvFormCategoryRvAdapter.items.add("식물성 캡슐")
        rvFormCategoryRvAdapter.items.add("식물성 캡슐")
        rvFormCategoryRvAdapter.items.add("식물성 캡슐")
        rvFormCategoryRvAdapter.items.add("식물성 캡슐")

        rv_product_filter_form_category.adapter = rvFormCategoryRvAdapter

    }

    fun rvBrand(){
        val rvBrandAdapter = ProductFilterRvAdapter()
        rvBrandAdapter.items.add("브랜드")
        rvBrandAdapter.items.add("브랜드")
        rvBrandAdapter.items.add("브랜드")
        rvBrandAdapter.items.add("브랜드")
        rvBrandAdapter.items.add("브랜드")

        rv_product_filter_brand.adapter = rvBrandAdapter
    }

    fun rvRange(){
        val rvRangeAdapter = ProductFilterRvAdapter()
        rvRangeAdapter.items.add("국내 제품")
        rvRangeAdapter.items.add("해외 제품")

        rv_product_filter_range.adapter = rvRangeAdapter
    }

    fun closeSetting(){
        img_product_filter_close.setOnClickListener{
            finish()
        }
    }
}