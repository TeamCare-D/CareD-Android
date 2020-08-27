package com.caredirection.cadi.home.caredetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.caredirection.cadi.R
import com.caredirection.cadi.home.caredetail.FragmentChartBitamin
import com.caredirection.cadi.home.caredetail.FragmentChartFunction
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_product_search.*

class CareRvAdapter(val mFragment: FragmentManager): RecyclerView.Adapter<CareRvAdapter.CareRvHolder>() {

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareRvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_home_care_detail, parent, false)
        return CareRvHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CareRvHolder, position: Int) {
        holder.bind()
    }

    inner class CareRvHolder(view: View):RecyclerView.ViewHolder(view){
        val tab_layout_home_care_detail: TabLayout = itemView.findViewById(R.id.tab_layout_home_care_detail)
        val img_rv_item_home_care_detail: ImageView = itemView.findViewById(R.id.img_rv_item_home_care_detail)

        val view_pager_home_care_detail: ViewPager = itemView.findViewById(R.id.view_pager_home_care_detail)
        var checked: Boolean = false

        


        fun bind(){
            tab_layout_home_care_detail.visibility = View.GONE

            img_rv_item_home_care_detail.setOnClickListener{
                if(!checked){
                    viewPagerSetting(itemView)
                    tab_layout_home_care_detail.visibility = View.VISIBLE
                    view_pager_home_care_detail.visibility = View.VISIBLE
                    checked = true
                }
                else {
                    tab_layout_home_care_detail.visibility = View.GONE
                    view_pager_home_care_detail.visibility = View.GONE

                    checked = false
                }
            }


        }
    }

    fun viewPagerSetting(view: View){
        val mViewPager: ViewPager = view.findViewById(R.id.view_pager_home_care_detail)
        val tab_layout_home_care_detail: TabLayout = view.findViewById(R.id.tab_layout_home_care_detail)
        val adapter : ViewPagerAdapter= ViewPagerAdapter(mFragment)

        adapter.items.add(FragmentChartBitamin())
        adapter.items.add(FragmentChartFunction())

        mViewPager.adapter = adapter

        tab_layout_home_care_detail.setupWithViewPager(mViewPager)
        val menu = arrayListOf("증상", "이름")
        for(i in 0..menu.size){
            tab_layout_home_care_detail.getTabAt(i)?.text = menu[i]
        }
    }
}