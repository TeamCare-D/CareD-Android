package com.caredirection.cadi.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartBitaminAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphBitaminList
import kotlinx.android.synthetic.main.view_pager_home_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentBitamin: Fragment(R.layout.view_pager_home_chart) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvNameSetting()
    }
    fun rvNameSetting(){
        val chartBitaminAdapter = ChartBitaminAdapter(requireContext())


        val call: Call<GraphBitaminList> = RequestURL.service.getGraphVitamin("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")

        call.enqueue(
            object : Callback<GraphBitaminList>{
                override fun onFailure(call: Call<GraphBitaminList>, t: Throwable?) {
                    Log.d("HomeFragmentBitamin onFailure", t.toString())
                }

                override fun onResponse(
                    call: Call<GraphBitaminList>,
                    response: Response<GraphBitaminList>
                ) {
                    try{
                         chartBitaminAdapter.items.addAll(response.body()!!.data)


                        rv_view_pager_home_chart.adapter = chartBitaminAdapter
                    }catch (e: Exception){

                    }

                }
            }
        )





    }
}