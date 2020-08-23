package com.caredirection.cadi.product.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.search.adapter.componentRvAdapter
import kotlinx.android.synthetic.main.fragment_product_search2.*

class FragmentName: Fragment(R.layout.fragment_product_search2) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rvNameSetting()
    }

    fun rvNameSetting(){
        val rvNameAdapter = componentRvAdapter(requireContext())

        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("레시틴")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")
        rvNameAdapter.items.add("가르시니아")



        rv_product_search_name.layoutManager = GridLayoutManager(requireContext(), 3)

        rv_product_search_name.adapter = rvNameAdapter
    }

}