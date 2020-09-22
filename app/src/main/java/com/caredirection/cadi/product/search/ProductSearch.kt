package com.caredirection.cadi.product.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
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
            if(edt_product_search.text.isEmpty()){
                Toast.makeText(baseContext, "입력하세요", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(baseContext, ListActivity::class.java)

                val productName: String = edt_product_search.text.toString()

                intent.putExtra("name", productName)
                startActivity(intent)
            }
        }

        autoTextSetting()

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

    fun autoTextSetting(){
        val list = mutableListOf<String>()
        val adaptwer = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list)
        list.add("오메가3")
        list.add("비타민B")
        list.add("비타민C")
        list.add("비타민B 컴플렉스")
        list.add("종합 비타민")
        list.add("코엔자임Q10")
        edt_product_search.setAdapter(adaptwer)
    }
}