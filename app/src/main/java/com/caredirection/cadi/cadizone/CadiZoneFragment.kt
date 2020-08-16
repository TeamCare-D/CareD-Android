package com.caredirection.cadi.cadizone

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.fragment_cadi_zone.*

class CadiZoneFragment : Fragment(R.layout.fragment_cadi_zone) {

    private val directionAdapter = DirectionRecyclerViewAdapter()
    private val guideAdapter = GuideRecyclerViewAdapter()
    private val magazineAdapter = MagazineRecyclerViewAdapter()

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
        rv_magazine_list.layoutManager =
            LinearLayoutManager(activity)
        rv_magazine_list.adapter = magazineAdapter
        magazineAdapter.submitList(
            listOf(
                Magazine("메거진", listOf("원", "투", "쓰리"), "img", 0),
                Magazine("메거진", listOf("원", "투", "쓰리"), "img", 0),
                Magazine("메거진", listOf("원", "투", "쓰리"), "img", 0),
                Magazine("메거진", listOf("원", "투", "쓰리"), "img", 0),
                Magazine("메거진", listOf("원", "투", "쓰리"), "img", 0)
            )
        )
    }

}