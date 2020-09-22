package com.caredirection.cadi.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartFunctionAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphFunctionList
import kotlinx.android.synthetic.main.view_pager_home_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentFunction: Fragment(R.layout.view_pager_home_chart){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvNameSetting()
    }
    fun rvNameSetting(){
        val chartAdapter = ChartFunctionAdapter(requireContext())


        val call: Call<GraphFunctionList> = RequestURL.service.getGraphFunction("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<GraphFunctionList>{
                override fun onFailure(call: Call<GraphFunctionList>, t: Throwable?) {
                    Log.d("HomeFragmentFunction GraphFunctionList", t.toString())
                }

                override fun onResponse(
                    call: Call<GraphFunctionList>,
                    response: Response<GraphFunctionList>
                ) {
                    chartAdapter.items.addAll(response.body()!!.data)
                    rv_view_pager_home_chart.adapter = chartAdapter
                }
            }
        )



    }
}