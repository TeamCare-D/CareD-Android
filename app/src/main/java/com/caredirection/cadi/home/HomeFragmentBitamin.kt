package com.caredirection.cadi.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphIngredientList
import kotlinx.android.synthetic.main.view_pager_home_chart.*
import retrofit2.Call

class HomeFragmentBitamin: Fragment(R.layout.view_pager_home_chart) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvNameSetting()
    }
    fun rvNameSetting(){
        val chartAdapter = ChartAdapter(requireContext())




        chartAdapter.items.add( ChartData( "비타민",  200))
        chartAdapter.items.add( ChartData( "비타민",  50))
        chartAdapter.items.add( ChartData( "비타민",  600))
        chartAdapter.items.add( ChartData( "비타민",  60))
        chartAdapter.items.add( ChartData( "비타민",  20))
        chartAdapter.items.add( ChartData("비타민", 600))
        chartAdapter.items.add( ChartData("비타민", 600))
        chartAdapter.items.add( ChartData("비타민", 600))

        rv_view_pager_home_chart.adapter = chartAdapter

    }
}