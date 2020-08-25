package com.caredirection.cadi.product.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.list.adapter.ProductListData
import com.caredirection.cadi.product.list.adapter.ProductListRvAdapter
import kotlinx.android.synthetic.main.activity_product_list.*

class ListActivity: AppCompatActivity() {


    private val fragmentDetail = FragmentDetail()
    private val fragmentDetailsimplicity = FragmentDetailSimplicity()
    private var active: Fragment = fragmentDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        changeFragment()

        productRvSetting()


    }
    fun changeFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_product_list_detail, FragmentDetail()).commit()

        img_product_list_btn.setOnClickListener{

            if(active == fragmentDetail){
                active = fragmentDetailsimplicity
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim).replace(R.id.fragment_product_list_detail, active).commit()
            }

            else if(active == fragmentDetailsimplicity){
                active = fragmentDetail
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim).replace(R.id.fragment_product_list_detail, active).commit()
            }
        }
    }

    fun productRvSetting(){
        val productListAdapter = ProductListRvAdapter(this@ListActivity)

        var testString = mutableListOf<String>()
        testString.add("산화 마그네슘")
        testString.add("RTG")


        productListAdapter.items.add(ProductListData("브랜드","제품 이름", "30", "21000","20000",testString))
        productListAdapter.items.add(ProductListData("브랜드","제품 이름", "30", "21000","20000",testString))
        productListAdapter.items.add(ProductListData("브랜드","제품 이름", "30", "21000","20000",testString))
        productListAdapter.items.add(ProductListData("브랜드","제품 이름", "30", "21000","20000",testString))
        productListAdapter.items.add(ProductListData("브랜드","제품 이름", "30", "21000","20000",testString))


        rv_product_list.adapter = productListAdapter
        rv_product_list.layoutManager = LinearLayoutManager(this@ListActivity)
    }
}