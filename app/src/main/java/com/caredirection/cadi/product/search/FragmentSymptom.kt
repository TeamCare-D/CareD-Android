package com.caredirection.cadi.product.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.search.adapter.SymptomRvAdapter
import com.caredirection.cadi.product.search.adapter.item
import kotlinx.android.synthetic.main.fragment_product_search1.*

class FragmentSymptom: Fragment(R.layout.fragment_product_search1) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RvAdapterSetting()


    }
    fun RvAdapterSetting(){
        val RvAdapter = SymptomRvAdapter(requireContext())

        RvAdapter.items.add(item("피로회복", mutableListOf("test","test","test","test","test")))
        RvAdapter.items.add(item("피로회복2",mutableListOf("test","test","test","test","test")))
        RvAdapter.items.add(item("피로회복2",mutableListOf("test","test","test","test","test")))
        RvAdapter.items.add(item("피로회복2",mutableListOf("test","test","test","test","test")))


        rv_product_search_symptom.adapter = RvAdapter

        rv_product_search_symptom.layoutManager = LinearLayoutManager(requireContext())

    }
}
