package com.caredirection.cadi.cadizone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.product.search.adapter.ingredientIdxData
import kotlinx.android.synthetic.main.fragment_top_dic.*

class TopDicFragment(private val items: MutableList<ingredientIdxData>) : Fragment(R.layout.fragment_top_dic) {

    private val adapter = TabMenuRecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(activity, 3)
        adapter.submitList(items)
    }

}