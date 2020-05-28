package com.song2.cadi.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.song2.cadi.R
import com.song2.cadi.home.homecare.HomeCareRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var rvHomeMyCareRvAdapter: HomeCareRvAdapter
    private lateinit var rvHomeCareSimilarRvAdapter: HomeCareRvAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_home_my_care.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvHomeMyCareRvAdapter= HomeCareRvAdapter(context!!)
        rv_home_my_care.adapter=rvHomeMyCareRvAdapter
        rvHomeMyCareRvAdapter.data= arrayListOf("간 건강","피로회복","면역력\n활성화")
        rvHomeMyCareRvAdapter.notifyDataSetChanged()


        rv_home_care_similar.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvHomeCareSimilarRvAdapter= HomeCareRvAdapter(context!!)
        rv_home_care_similar.adapter=rvHomeCareSimilarRvAdapter
        rvHomeCareSimilarRvAdapter.data= arrayListOf("간 건강","피로회복","면역력\n활성화")
        rvHomeCareSimilarRvAdapter.notifyDataSetChanged()
    }




}