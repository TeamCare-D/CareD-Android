package com.caredirection.cadi.home.caredetail

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.home.caredetail.adapter.CareRvAdapter
import com.caredirection.cadi.product.list.ListActivity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.activity_home_care_detail.*
import kotlinx.android.synthetic.main.activity_product_search.*

class CareDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_care_detail)

        val careRvAdapter = CareRvAdapter(supportFragmentManager)

        careRvAdapter.items.add("test")
        careRvAdapter.items.add("test")





        rv_home_care_detail.adapter = careRvAdapter
    }
}