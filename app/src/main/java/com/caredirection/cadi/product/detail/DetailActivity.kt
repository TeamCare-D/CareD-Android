package com.caredirection.cadi.product.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.CareCategoryRvAdapter
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_product_detail.*

class DetailActivity : AppCompatActivity(), pickerCallback {

    private lateinit var productDetailViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)



        ViewPagerSetting()

        careCategorySetting()

        pickerSetting()


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

    fun careCategorySetting() {
        val careCategoryAdapter = CareCategoryRvAdapter()
        careCategoryAdapter.items.add("피로회복")
        careCategoryAdapter.items.add("피로")
        careCategoryAdapter.items.add("간")
        rv_product_detail_care_category.adapter = careCategoryAdapter
    }

    fun pickerSetting() {

        txt_product_detail_amount.setOnClickListener {
            val bottomSheetFragment = bottomSheetFragment()
            val bottomView = layoutInflater.inflate(R.layout.view_bottom_sheet, null)

            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)

        }


    }

    override fun callback(per: String) {
        txt_product_detail_amount.text = per
    }
}


interface pickerCallback {
    fun callback(per: String)
}