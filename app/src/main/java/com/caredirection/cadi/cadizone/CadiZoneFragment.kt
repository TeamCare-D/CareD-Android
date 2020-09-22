package com.caredirection.cadi.cadizone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.MagazineDirectionData
import com.caredirection.cadi.networkdata.MagazineGuideData
import com.caredirection.cadi.networkdata.MagazineHome
import kotlinx.android.synthetic.main.fragment_cadi_zone.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadiZoneFragment : Fragment(R.layout.fragment_cadi_zone) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
        initRecyclerView()


    }

    private fun initViewPager() {
        val tabLayout = tab_top_dic
        val viewPager = vp_viewPager
        val menu = arrayListOf("피부회복", "활력 · 성기능", "피부 · 모발", "뼈 · 관절")
        val viewPagerAdapter = CadiZoneViewPagerAdapter(fragmentManager!!, menu.count())
        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        (0 until menu.size).forEach {
            tabLayout.getTabAt(it)!!.text = menu[it]
        }
    }

    private fun initRecyclerView() {

        guideSetting()

        directionSetting()

        magazineSetting()
    }

    fun directionSetting() {
        val directionAdapter = DirectionRecyclerViewAdapter()

        rv_direction_list.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_direction_list.adapter = directionAdapter

        val call: Call<MagazineDirectionData> = RequestURL.service.getMagazineDirection()
        call.enqueue(
            object : Callback<MagazineDirectionData> {
                override fun onFailure(call: Call<MagazineDirectionData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<MagazineDirectionData>,
                    response: Response<MagazineDirectionData>
                ) {
                    directionAdapter.submitList(
                        response.body()!!.data
                    )
                }
            }
        )


    }

    fun guideSetting() {
        val guideAdapter = GuideRecyclerViewAdapter()

        rv_guide_list.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_guide_list.adapter = guideAdapter



        val call: Call<MagazineGuideData> = RequestURL.service.getMAgazineGuide()
        call.enqueue(
            object : Callback<MagazineGuideData>{
                override fun onFailure(call: Call<MagazineGuideData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MagazineGuideData>,
                    response: Response<MagazineGuideData>
                ) {
                    guideAdapter.submitList(response.body()!!.data)
                }
            }
        )



    }

    fun magazineSetting() {
        val productMagazineRvAdapter = MagazineRecyclerViewAdapter()

        val call: Call<MagazineHome> =
            RequestURL.service.getMagazineHome("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<MagazineHome> {
                override fun onFailure(call: Call<MagazineHome>, t: Throwable) {
                    Log.d("getMagazineHome Fail", t.toString())
                }

                override fun onResponse(
                    call: Call<MagazineHome>,
                    response: Response<MagazineHome>
                ) {
                    val data = response.body()!!.data

                    Log.d("이거 잘 되니ㅏ", data.toString())

                    productMagazineRvAdapter.submitList(data.magazine)

                    rv_magazine_list.adapter = productMagazineRvAdapter
                }
            }
        )

    }


}