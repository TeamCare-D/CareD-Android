package com.caredirection.cadi.product.search.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var items = mutableListOf<Fragment>()
    override fun getItem(position: Int): Fragment {
        return items.get(position)
    }

    override fun getCount(): Int = items.size
}