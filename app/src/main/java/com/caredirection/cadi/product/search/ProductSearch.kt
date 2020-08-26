package com.caredirection.cadi.product.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.list.ListActivity
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_product_search.*

class ProductSearch : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_search)


        ViewPagerSetting()

        img_product_search_search.setOnClickListener{
            val intent = Intent(baseContext, ListActivity::class.java)
            startActivity(intent)
        }

    }

    fun ViewPagerSetting(){
        mViewPager = findViewById(R.id.view_pager_product_search)
        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        adapter.items.add(FragmentSymptom())
        adapter.items.add(FragmentName())

        mViewPager.adapter = adapter

        tab_layout_product_search.setupWithViewPager(mViewPager)
        val menu = arrayListOf("증상", "이름")
        for(i in 0..menu.size){
            tab_layout_product_search.getTabAt(i)?.text = menu[i]
        }
    }


}