package com.caredirection.cadi.home.careuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.caredirection.cadi.R
import com.caredirection.cadi.home.HomeFragmentBitamin
import com.caredirection.cadi.home.HomeFragmentFunction
import com.caredirection.cadi.home.caredetail.adapter.CareSimliarData
import com.caredirection.cadi.home.caredetail.adapter.CareSimliarRvAdapter
import com.caredirection.cadi.home.homecare.HomeCareRvAdapter
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_home_care_detail.*
import kotlinx.android.synthetic.main.activity_home_detail.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)


        ViewPagerSetting()

    }
    fun ViewPagerSetting(){

        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        adapter.items.add(UserCareBitaminFragment())
        adapter.items.add(UserCareFunctionFragment())

        view_pager_home_care_user_detail.adapter = adapter

        tab_layout_home_care_user_detail.setupWithViewPager(view_pager_home_care_user_detail)

        val menu = arrayListOf("증상", "이름")
        for(i in 0..menu.size){
            tab_layout_home_care_user_detail.getTabAt(i)?.text = menu[i]
        }
    }
}