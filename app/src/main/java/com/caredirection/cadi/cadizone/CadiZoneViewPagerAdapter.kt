package com.caredirection.cadi.cadizone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CadiZoneViewPagerAdapter(fm: FragmentManager, private val cnt: Int) : FragmentStatePagerAdapter(fm, cnt) {
    override fun getItem(position: Int): Fragment {
        return TopDicFragment(position)
    }


    override fun getCount(): Int = cnt
}