package com.caredirection.cadi.home.careuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartBitaminAdapter
import com.caredirection.cadi.adapter.MagazineIngredientRvAdapter
import com.caredirection.cadi.custom.OnSnapPositionChangeListener
import com.caredirection.cadi.custom.getSnapPosition
import com.caredirection.cadi.home.caredetail.Behavior
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphBitaminList
import com.caredirection.cadi.networkdata.IngredientDetail
import com.caredirection.cadi.networkdata.MagazineListData
import kotlinx.android.synthetic.main.view_home_care_user_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCareBitaminFragment : Fragment(R.layout.view_home_care_user_detail) {
    lateinit var chartRvADapter: ChartBitaminAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ChartSetting()

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
                Log.d("엥", snapPosition.toString())
                ChartIngredientDetail(chartRvADapter.items[snapPosition].ingredient_idx)
            }
        }

    }


    fun ChartSetting(){
        val snapHelper = LinearSnapHelper()

        chartRvADapter = ChartBitaminAdapter(requireContext())

        snapHelper.attachToRecyclerView(rv_home_care_user_detail)
        val layoutManager = rv_home_care_user_detail.layoutManager
        val snapView = snapHelper.findSnapView(layoutManager)

        val snapPosition = snapView?.let { layoutManager?.getPosition(it) }

        val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior = Behavior.NOTIFY_ON_SCROLL_STATE_IDLE)

        rv_home_care_user_detail.addOnScrollListener(snapOnScrollListener)

        ChartDataSetting()


        snapHelper.attachToRecyclerView(rv_home_care_user_detail)

    }

    fun ChartDataSetting(){

        val call: Call<GraphBitaminList> = RequestURL.service.getGraphVitamin("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<GraphBitaminList>{
                override fun onFailure(call: Call<GraphBitaminList>, t: Throwable?) {
                    Log.d("UserCareBitaminFragment onFailure", t.toString())
                }

                override fun onResponse(
                    call: Call<GraphBitaminList>,
                    response: Response<GraphBitaminList>
                ) {
                    chartRvADapter.items.addAll(response.body()!!.data)
                    rv_home_care_user_detail.adapter = chartRvADapter
                }
            }
        )
    }

    fun ChartIngredientDetail(ingredient_idx: Int){
        val call: Call<IngredientDetail> = RequestURL.service.getIngredientDetail(ingredient_idx, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<IngredientDetail>{
                override fun onFailure(call: Call<IngredientDetail>, t: Throwable) {
                    Log.d("잘된대", t.toString())
                }

                override fun onResponse(
                    call: Call<IngredientDetail>,
                    response: Response<IngredientDetail>
                ) {
                    if(response.isSuccessful){


                        val data = response.body()!!.data
                        txt_home_detail_desc_title.text = data.graphDetail.ingredient_name
                        txt_home_detail_user_bottom_limit.text = data.graphDetail.vitamin_mineral_recommended_amount
                        txt_home_detail_user_top_limit.text = data.graphDetail.vitamin_mineral_upper_amount
                        textView13.text = data.graphDetail.ingredient_description
                        txt_home_detail_user_Intake.text = data.graphDetail.my_amount

                        MagazineSetting(data.magazineList)
                    }
                    else{
                        Log.d("잘안된대", response.message())
                    }
                }
            }
        )
    }

    fun MagazineSetting(items: MutableList<MagazineListData>){
        val magazineIngredientRvAdapter = MagazineIngredientRvAdapter()

        magazineIngredientRvAdapter.items.addAll(items)

        rv_home_Care_user_detail_magazine.adapter = magazineIngredientRvAdapter


    }
}