package com.caredirection.cadi.home.caredetail

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.home.caredetail.adapter.CareRvAdapter
import com.caredirection.cadi.home.caredetail.adapter.CareSimliarData
import com.caredirection.cadi.home.caredetail.adapter.CareSimliarRvAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.CareDetailData
import com.caredirection.cadi.networkdata.SimilarCareData
import com.caredirection.cadi.networkdata.UserCaredEfficacyListData
import com.caredirection.cadi.product.list.ListActivity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import kotlinx.android.synthetic.main.activity_home_care_detail.*
import kotlinx.android.synthetic.main.activity_product_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CareDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_care_detail)

        careDetailSetting()
    }

    fun SimilarRvSetting(item: MutableList<SimilarCareData>){
        val careSimliarRvAdapter =CareSimliarRvAdapter()
        careSimliarRvAdapter.items.addAll(item)

        rv_home_care_detail_similar.adapter = careSimliarRvAdapter

    }

    fun userCaredSetting(item: MutableList<UserCaredEfficacyListData>){
        val careRvAdapter = CareRvAdapter(supportFragmentManager)

        careRvAdapter.items.addAll(item)
        rv_home_care_detail.adapter = careRvAdapter
    }

    fun careDetailSetting(){
        val call: Call<CareDetailData> = RequestURL.service.getUserCareDetail("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<CareDetailData>{
                override fun onFailure(call: Call<CareDetailData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<CareDetailData>,
                    response: Response<CareDetailData>
                ) {
                    val data = response.body()!!.data

                    SimilarRvSetting(data.SimilarCare)
                    userCaredSetting(data.userCaredEfficacyList)
                }
            }
        )
    }

}