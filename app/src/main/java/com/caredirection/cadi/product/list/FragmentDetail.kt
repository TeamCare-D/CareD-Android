package com.caredirection.cadi.product.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.view_product_list_detail.*

class FragmentDetail : Fragment(R.layout.view_product_list_detail) {

    private val fragmentDetailItem = FragmentDetailItem()
    private val fragmentDetailSimplicity = FragmentDetailSimplicity()
    private var active: Fragment = fragmentDetailItem

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        changeView()
    }

    fun changeView(){
        childFragmentManager.beginTransaction().replace(R.id.fragment_view_product_list_detail_item, active).commit()

        btn_view_product_list_detail_up.setOnClickListener{

            if(active.equals(fragmentDetailItem)){
                active = fragmentDetailSimplicity
                childFragmentManager.beginTransaction().replace(R.id.fragment_view_product_list_detail_item, active).commit()
            }

            else if(active.equals(fragmentDetailSimplicity)){
                active = fragmentDetailItem
                childFragmentManager.beginTransaction().replace(R.id.fragment_view_product_list_detail_item, active).commit()
            }
        }
    }
}