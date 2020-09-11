package com.caredirection.cadi.cadizone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineIngredientRvAdapter
import kotlinx.android.synthetic.main.fragment_cadi_zone.*

class CadiZoneFragment : Fragment(R.layout.fragment_cadi_zone) {

    private val directionAdapter = DirectionRecyclerViewAdapter()
    private val guideAdapter = GuideRecyclerViewAdapter()
    private val magazineAdapter = MagazineIngredientRvAdapter()

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


        val tag = listOf<String>("방향성", "방향성")
//        magazineAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
//        magazineAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
//        magazineAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))
//        magazineAdapter.items.add(ProductMagazineData("테스트테스트테스트", tag))

        rv_magazine_list.adapter = magazineAdapter


    }

}