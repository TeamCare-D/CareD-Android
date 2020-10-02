package com.caredirection.cadi.cadizone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.caredirection.cadi.product.search.adapter.ingredientIdxData
import com.caredirection.cadi.product.search.adapter.item

class CadiZoneViewPagerAdapter(fm: FragmentManager, private val cnt: Int) : FragmentStatePagerAdapter(fm, cnt) {

    var items =  mutableListOf<item>()

    override fun getItem(position: Int): Fragment {
        return TopDicFragment(items[position].nameItem)
    }

    override fun getCount(): Int = cnt
}