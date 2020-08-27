package com.caredirection.cadi.home.caredetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.custom.OnSnapPositionChangeListener
import com.caredirection.cadi.custom.getSnapPosition
import kotlinx.android.synthetic.main.fragment_home_care_detail_chart.*

class FragmentChartFunction : Fragment(R.layout.fragment_home_care_detail_chart) {
    lateinit var chartRvADapter: ChartAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ChartSetting()

        ChartDataSetting()
    }

    inner class SnapOnScrollListener(
        private val snapHelper: SnapHelper,
        var behavior: Behavior = Behavior.NOTIFY_ON_SCROLL,
        var onSnapPositionChangeListener: OnSnapPositionChangeListener? = null
    ) : RecyclerView.OnScrollListener() {

        private var snapPosition = RecyclerView.NO_POSITION

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (behavior == Behavior.NOTIFY_ON_SCROLL) {
                maybeNotifySnapPositionChange(recyclerView)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
                && newState == RecyclerView.SCROLL_STATE_IDLE) {
                maybeNotifySnapPositionChange(recyclerView)
                Log.d("승희 테스트",chartRvADapter.items[snapHelper.getSnapPosition(rv_home_care_detail)].toString())
                box_home_care_detail_chart.text = chartRvADapter.items[snapHelper.getSnapPosition(rv_home_care_detail)].toString()
                txt_home_care_detail_chart_content_intake_number.text = chartRvADapter.items[snapHelper.getSnapPosition(rv_home_care_detail)].height.toString()
            }
        }

        private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
            val snapPosition = snapHelper.getSnapPosition(recyclerView)
            val snapPositionChanged = this.snapPosition != snapPosition
            if (snapPositionChanged) {
                onSnapPositionChangeListener?.onSnapPositionChange(snapPosition)
                this.snapPosition = snapPosition
            }
        }

    }


    fun ChartSetting(){
        val snapHelper = LinearSnapHelper()

        chartRvADapter = ChartAdapter(requireContext())

        snapHelper.attachToRecyclerView(rv_home_care_detail)
        val layoutManager = rv_home_care_detail.layoutManager
        val snapView = snapHelper.findSnapView(layoutManager)

        val snapPosition = snapView?.let { layoutManager?.getPosition(it) }

        val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior = Behavior.NOTIFY_ON_SCROLL_STATE_IDLE)

        rv_home_care_detail.addOnScrollListener(snapOnScrollListener)



        rv_home_care_detail.adapter = chartRvADapter



        snapHelper.attachToRecyclerView(rv_home_care_detail)

    }

    fun ChartDataSetting(){
        chartRvADapter.items.add(ChartData("",0))
        chartRvADapter.items.add(ChartData("",0))
        chartRvADapter.items.add(ChartData("",0))
        chartRvADapter.items.add(ChartData("",0))

        chartRvADapter.items.add(ChartData("비타민1", 70))
        chartRvADapter.items.add(ChartData("비타민2", 50))
        chartRvADapter.items.add(ChartData("비타민3", 40))
        chartRvADapter.items.add(ChartData("비타민4", 60))
        chartRvADapter.items.add(ChartData("비타민5", 70))
        chartRvADapter.items.add(ChartData("비타민6", 80))
        chartRvADapter.items.add(ChartData("비타민7", 100))
        chartRvADapter.items.add(ChartData("비타민8", 110))
        chartRvADapter.items.add(ChartData("비타민9", 70))
        chartRvADapter.items.add(ChartData("비타민10", 50))
        chartRvADapter.items.add(ChartData("비타민11", 40))
        chartRvADapter.items.add(ChartData("비타민12", 40))
        chartRvADapter.items.add(ChartData("비타민13", 40))
    }
}
enum class Behavior {
    NOTIFY_ON_SCROLL,
    NOTIFY_ON_SCROLL_STATE_IDLE
}