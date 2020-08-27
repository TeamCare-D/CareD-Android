package com.caredirection.cadi.home
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewPagerSetting()

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

}