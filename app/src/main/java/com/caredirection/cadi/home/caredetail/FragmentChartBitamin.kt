package com.caredirection.cadi.home.caredetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartBitaminAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.custom.OnSnapPositionChangeListener
import com.caredirection.cadi.custom.getSnapPosition
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphBitamin
import com.caredirection.cadi.networkdata.GraphBitaminList
import com.caredirection.cadi.networkdata.IngredientDetail
import kotlinx.android.synthetic.main.fragment_home_care_detail_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentChartBitamin: Fragment(R.layout.fragment_home_care_detail_chart) {
    lateinit var chartRvADapter: ChartBitaminAdapter
    var items = mutableListOf<GraphBitamin>()

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

            }
        }

        private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
            val snapPosition = snapHelper.getSnapPosition(recyclerView)
            val snapPositionChanged = this.snapPosition != snapPosition
            if (snapPositionChanged) {
                onSnapPositionChangeListener?.onSnapPositionChange(snapPosition)
                this.snapPosition = snapPosition

                chartDetailContent(chartRvADapter.items[snapPosition].ingredient_idx)
            }
        }

    }


    fun ChartSetting(){
        val snapHelper = LinearSnapHelper()

        chartRvADapter = ChartBitaminAdapter(requireContext())

        chartRvADapter.items = items

        snapHelper.attachToRecyclerView(rv_home_care_detail)
        val layoutManager = rv_home_care_detail.layoutManager
        val snapView = snapHelper.findSnapView(layoutManager)

        val snapPosition = snapView?.let { layoutManager?.getPosition(it) }

        val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior = Behavior.NOTIFY_ON_SCROLL_STATE_IDLE)

        rv_home_care_detail.addOnScrollListener(snapOnScrollListener)

        snapHelper.attachToRecyclerView(rv_home_care_detail)

    }

    fun ChartDataSetting(){
        chartRvADapter.items = items
        rv_home_care_detail.adapter = chartRvADapter
    }


    fun chartDetailContent(ingredient_idx: Int){
        val call: Call<IngredientDetail> = RequestURL.service.getIngredientDetail(ingredient_idx, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object: Callback<IngredientDetail>{
                override fun onFailure(call: Call<IngredientDetail>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<IngredientDetail>,
                    response: Response<IngredientDetail>
                ) {
                    val data = response.body()!!.data
                    txt_home_care_detail_chart_content_title.text = data.graphVitaminMineralDetail.ingredient_name
                    txt_home_care_detail_chart_content_recommended_number1.text = data.graphVitaminMineralDetail.vitamin_mineral_recommended_amount
                    txt_home_care_detail_chart_content_recommended_number2.text = data.graphVitaminMineralDetail.vitamin_mineral_upper_amount
                    txt_home_care_detail_chart_content_intake_number.text = data.graphVitaminMineralDetail.my_amount

                }
            }
        )

    }

}


