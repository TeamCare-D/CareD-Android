package com.caredirection.cadi.product.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.CareCategoryRvAdapter
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_product_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var productDetailViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)



        ViewPagerSetting()

        careCategorySetting()
    }

    fun ViewPagerSetting() {
        productDetailViewPager = findViewById(R.id.view_pager_product_detail)
        val adapter: ViewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager
        )

        adapter.items.add(FragmentContent())
        adapter.items.add(FragmentIntake())

        productDetailViewPager.adapter = adapter

        tab_layout_product_detail.setupWithViewPager(productDetailViewPager)
        val menu = arrayListOf("제품 함량", "예상 섭취 변화량")
        for (i in 0..menu.size) {
            tab_layout_product_detail.getTabAt(i)?.text = menu[i]
        }
    }

    fun careCategorySetting(){
        val careCategoryAdapter = CareCategoryRvAdapter()
        careCategoryAdapter.items.add("피로회복")
        careCategoryAdapter.items.add("피로")
        careCategoryAdapter.items.add("간")
        rv_product_detail_care_category.adapter = careCategoryAdapter
    }
}