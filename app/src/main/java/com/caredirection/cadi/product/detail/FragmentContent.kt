package com.caredirection.cadi.product.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.product.detail.adapter.RvIngredientAdapter
import com.caredirection.cadi.product.detail.adapter.RvIngredientData
import kotlinx.android.synthetic.main.fragment_product_detail.*

class FragmentContent : Fragment(R.layout.fragment_product_detail)  {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        intakeRvSetting()

        ingredientSetting()

        bitaminSetting()
    }
    fun intakeRvSetting(){
        val chartAdapter = ChartAdapter(requireContext())


        chartAdapter.items.add( ChartData( "비타민",  200))
        chartAdapter.items.add( ChartData( "비타민",  50))
        chartAdapter.items.add( ChartData( "비타민",  600))
        chartAdapter.items.add( ChartData( "비타민",  60))
        chartAdapter.items.add( ChartData( "비타민",  20))
        chartAdapter.items.add( ChartData("비타민", 600))
        chartAdapter.items.add( ChartData("비타민", 600))
        chartAdapter.items.add( ChartData("비타민", 600))

        chart_product_detail_intake.adapter = chartAdapter
        chart_product_detail_intake.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    fun ingredientSetting(){
        val ingredientAdapter = RvIngredientAdapter(requireContext())

        var data2 = mutableListOf<String>()
        data2.add("프로바이오틱스 종류")
        data2.add("프로바이오틱스 종류")
        data2.add("프로바이오틱스 종류")



        ingredientAdapter.items.add(RvIngredientData("프로바이오 틱스", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        ingredientAdapter.items.add(RvIngredientData("기능성원료", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        ingredientAdapter.items.add(RvIngredientData("기능성원료", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        rv_product_detail_intake_functional.adapter = ingredientAdapter
        rv_product_detail_intake_functional.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }


    fun bitaminSetting(){
        var charBitaminAdapter = ChartAdapter(requireContext())

        charBitaminAdapter.items.add( ChartData( "비타민",  200))
        charBitaminAdapter.items.add( ChartData( "비타민",  50))
        charBitaminAdapter.items.add( ChartData( "비타민",  600))
        charBitaminAdapter.items.add( ChartData( "비타민",  60))
        charBitaminAdapter.items.add( ChartData( "비타민",  20))
        charBitaminAdapter.items.add( ChartData("비타민", 600))
        charBitaminAdapter.items.add( ChartData("비타민", 600))
        charBitaminAdapter.items.add( ChartData("비타민", 600))

        chart_product_detail_bitamin.adapter = charBitaminAdapter
        chart_product_detail_bitamin.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

}