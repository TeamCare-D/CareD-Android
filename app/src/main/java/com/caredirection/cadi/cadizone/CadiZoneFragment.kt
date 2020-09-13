package com.caredirection.cadi.cadizone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineIngredientRvAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.MagazineHome
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import kotlinx.android.synthetic.main.fragment_cadi_zone.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadiZoneFragment : Fragment(R.layout.fragment_cadi_zone) {

    private val directionAdapter = DirectionRecyclerViewAdapter()
    private val guideAdapter = GuideRecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
        initRecyclerView()
    }

    private fun initViewPager() {
        val tabLayout = tab_top_dic
        val viewPager = vp_viewPager
        val menu = arrayListOf("피부회복", "활력", "성기능", "피부", "모발", "뼈", "관절")
        val viewPagerAdapter = CadiZoneViewPagerAdapter(fragmentManager!!, menu.count())
        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        (0 until menu.size).forEach {
            tabLayout.getTabAt(it)!!.text = menu[it]
        }
    }

    private fun initRecyclerView() {

        rv_direction_list.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_direction_list.adapter = directionAdapter
        directionAdapter.submitList(
            listOf(
                Direction("아ㅏㅏ", "방향성", 0),
                Direction("아ㅏㅏ", "방향성", 0),
                Direction("아ㅏㅏ", "방향성", 0),
                Direction("아ㅏㅏ", "방향성", 0),
                Direction("아ㅏㅏ", "방향성", 0)
            )
        )
        rv_guide_list.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_guide_list.adapter = guideAdapter
        guideAdapter.submitList(
            listOf(
                Guide("아ㅏㅏ", 0),
                Guide("아ㅏㅏ", 0),
                Guide("아ㅏㅏ", 0),
                Guide("아ㅏㅏ", 0),
                Guide("아ㅏㅏ", 0)
            )
        )


        magazineSetting()


    }

    fun magazineSetting(){
        val productMagazineRvAdapter = ProductMagazineRvAdapter()

        val call: Call<MagazineHome> = RequestURL.service.getMagazineHome("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
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
                    productMagazineRvAdapter.items.addAll(data.magazine)
                    rv_magazine_list.adapter = productMagazineRvAdapter
                }
            }
        )

    }

}