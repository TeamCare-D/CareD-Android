package com.caredirection.cadi.register.user.ingredient

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RegisterIngredientMenuAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment){
    lateinit var data: ArrayList<Fragment>

    override fun getItem(position: Int): Fragment {
        return data[position]
    }

    override fun getCount(): Int {
        return data.size
    }
}