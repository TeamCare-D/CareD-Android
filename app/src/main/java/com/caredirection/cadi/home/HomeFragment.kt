package com.caredirection.cadi.home
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.home.caredetail.CareDetailActivity
import com.caredirection.cadi.home.careuser.HomeDetailActivity
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.MagazineHome
import com.caredirection.cadi.networkdata.MagazineHomeData
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import com.caredirection.cadi.product.search.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewPagerSetting()

        magazineSetting()

        moreCategoryIntent()

        userIntakeMore()
    }

    fun ViewPagerSetting(){

        val adapter: ViewPagerAdapter = ViewPagerAdapter(childFragmentManager)

        adapter.items.add(HomeFragmentBitamin())
        adapter.items.add(HomeFragmentFunction())

        view_pager_home.adapter = adapter

        tab_layout_home.setupWithViewPager(view_pager_home)
        val menu = arrayListOf("비타민 & 미네랄", "기능성 원료")
        for(i in 0..menu.size){
            tab_layout_home.getTabAt(i)?.text = menu[i]
        }
    }

    fun magazineSetting(){
        val productMagazineRvAdapter = ProductMagazineRvAdapter()

        val call: Call<MagazineHome> = RequestURL.service.getMagazineHome("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<MagazineHome>{
                override fun onFailure(call: Call<MagazineHome>, t: Throwable) {
                    Log.d("getMagazineHome Fail", t.toString())
                }

                override fun onResponse(
                    call: Call<MagazineHome>,
                    response: Response<MagazineHome>
                ) {
                    val data = response.body()!!.data
                    productMagazineRvAdapter.items.addAll(data.magazine)
                    productMagazineRvAdapter.marketingItems.addAll(data.directions)
                    rv_home_magazine.adapter = productMagazineRvAdapter
                }
            }
        )

    }

    fun moreCategoryIntent(){
        img_home_care_category_more.setOnClickListener{
            val intent = Intent(context, CareDetailActivity::class.java)
            startActivity(intent)
        }
    }

    fun userIntakeMore(){
        img_home_more.setOnClickListener{
            val intent = Intent(context, HomeDetailActivity::class.java)
            startActivity(intent)
        }
    }

}