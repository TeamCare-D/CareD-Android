package com.caredirection.cadi.home.caredetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import kotlinx.android.synthetic.main.fragment_home_care_detail_chart_bitamin.*

class FragmentChartBitamin: Fragment(R.layout.fragment_home_care_detail_chart_bitamin) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val chartRvADapter = ChartAdapter(requireContext())
        chartRvADapter.items.add(ChartData("비타민A", 153))
        chartRvADapter.items.add(ChartData("비타민A", 153))
        chartRvADapter.items.add(ChartData("비타민A", 153))
        chartRvADapter.items.add(ChartData("비타민A", 153))
        chartRvADapter.items.add(ChartData("비타민A", 153))

        rv_home_care_detail_bitamin.adapter = chartRvADapter
    }
}