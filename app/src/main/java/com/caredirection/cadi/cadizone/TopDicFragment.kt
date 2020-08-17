package com.caredirection.cadi.cadizone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.fragment_top_dic.*

class TopDicFragment(val idx: Int) : Fragment(R.layout.fragment_top_dic) {

    private val adapter = TabMenuRecyclerViewAdapter()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(activity, 3)
        adapter.submitList(
            setData(idx)
        )
    }


    private fun setData(idx: Int): List<Ingredient> {

        //여기서 통신해서 데이터 받아오슛!
        return when (idx) {
            0-> listOf(
                Ingredient("냐악", 0),
                Ingredient("냐악", 0),
                Ingredient("냐악", 0),
                Ingredient("냐악", 0),
                Ingredient("냐악", 0)
            )
            1 -> listOf(
                Ingredient("프로틴", 0),
                Ingredient("프로틴", 0),
                Ingredient("프로틴", 0),
                Ingredient("프로틴", 0),
                Ingredient("프로틴", 0),
                Ingredient("프로틴", 0)
            )
            2 -> listOf(
                Ingredient("2", 0),
                Ingredient("2", 0),
                Ingredient("2", 0),
                Ingredient("2", 0),
                Ingredient("2", 0),
                Ingredient("2", 0)
            )
            else -> listOf(
                Ingredient("d", 0),
                Ingredient("d", 0),
                Ingredient("d", 0),
                Ingredient("d", 0),
                Ingredient("d", 0),
                Ingredient("d", 0)
            )
        }


    }
}