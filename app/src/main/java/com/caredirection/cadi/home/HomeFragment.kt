package com.caredirection.cadi.home
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.home.caredetail.CareDetailActivity
import com.caredirection.cadi.home.careuser.HomeDetailActivity
import com.caredirection.cadi.product.list.adapter.MarketingData
import com.caredirection.cadi.product.list.adapter.ProductMagazineData
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewPagerSetting()

        magazineSetting()

        moreCategoryIntent()

        userIntakeMore()

    }

    fun ViewPagerSetting(){

        val adapter: ViewPagerAdapter = ViewPagerAdapter(childFragmentManager)

        adapter.items.add(HomeFragmentBitamin())
        adapter.items.add(HomeFragmentFunction())

        view_pager_home.adapter = adapter

        tab_layout_home.setupWithViewPager(view_pager_home)
        val menu = arrayListOf("증상", "이름")
        for(i in 0..menu.size){
            tab_layout_home.getTabAt(i)?.text = menu[i]
        }
    }

    fun magazineSetting(){
        val productMagazineRvAdapter = ProductMagazineRvAdapter()

        val tag = listOf<String>("방향성", "방향성")

        productMagazineRvAdapter.items.add(
            ProductMagazineData("테스트테스트테스트", tag)
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )
        productMagazineRvAdapter.items.add(
            ProductMagazineData(
                "테스트테스트테스트",
                tag
            )
        )

        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )
        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )
        productMagazineRvAdapter.marketingItems.add(
            MarketingData(
                "test",
                "test"
            )
        )

        rv_home_magazine.adapter = productMagazineRvAdapter



    }

    fun moreCategoryIntent(){
        img_home_care_category_more.setOnClickListener{
            val intent = Intent(context, CareDetailActivity::class.java)
            startActivity(intent)
        }
    }

    fun userIntakeMore(){
        img_home_more.setOnClickListener{
            val intent = Intent(context, HomeDetailActivity::class.java)
            startActivity(intent)
        }
    }

}