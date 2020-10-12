package com.caredirection.cadi.cadizone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineIngredientRvAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.EfficacyListData
import com.caredirection.cadi.networkdata.MagazineDirectionData
import com.caredirection.cadi.networkdata.MagazineGuideData
import com.caredirection.cadi.networkdata.MagazineHome
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import com.caredirection.cadi.product.search.adapter.SymptomRvAdapter
import com.caredirection.cadi.product.search.adapter.ingredientIdxData
import com.caredirection.cadi.product.search.adapter.item
import kotlinx.android.synthetic.main.dialog_register_product_ingredient.view.*
import kotlinx.android.synthetic.main.fragment_cadi_zone.*
import kotlinx.android.synthetic.main.fragment_product_search1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadiZoneFragment : Fragment(R.layout.fragment_cadi_zone) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()

        RvAdapterSetting()
    }

    private fun initViewPager(items: MutableList<item>) {
        val tabLayout = tab_top_dic
        val viewPager = vp_viewPager

        val viewPagerAdapter = CadiZoneViewPagerAdapter(fragmentManager!!, items.count())

        viewPagerAdapter.items = items

        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        (0 until items.size).forEach {
            tabLayout.getTabAt(it)!!.text = items[it].sysmptom

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

    fun RvAdapterSetting(){

        val call: Call<EfficacyListData> = RequestURL.service.getEfficacy()
        call.enqueue(
            object : Callback<EfficacyListData>{
                override fun onFailure(call: Call<EfficacyListData>, t: Throwable) {
                    Log.d("testDis", t.toString())
                }

                override fun onResponse(
                    call: Call<EfficacyListData>,
                    response: Response<EfficacyListData>
                ) {
                    val data = response.body()!!.data
                    val efficacyList = mutableListOf<item>()


                    for (i in data){
                        val testString = mutableListOf<ingredientIdxData>()
                        for(j in i){
                            testString.add(ingredientIdxData(j.ingredient_name, j.ingredient_idx))
                        }
                        efficacyList.add(item(i[0].efficacy_name, testString))
                    }

                    initViewPager(efficacyList)



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
        val productMagazineRvAdapter = MagazineIngredientRvAdapter()

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

                    productMagazineRvAdapter.items.addAll(data.magazine)

                    rv_magazine_list.adapter = productMagazineRvAdapter
                }
            }
        )

    }


}