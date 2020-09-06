package com.caredirection.cadi.mypage.interest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageInterestListItem
import com.caredirection.cadi.data.network.MypageDeleteInterestData
import com.caredirection.cadi.data.network.MypageInterestData
import com.caredirection.cadi.network.RequestURL
import kotlinx.android.synthetic.main.activity_mypage_interest_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageInterestProductActivity : AppCompatActivity() {

    private lateinit var mypageInterestProductAdapter: MypageInterestProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_interest_product)

        setStatusBarTransparent()

        makeListener()
        interestListInit()
    }

    private fun interestListInit(){
        mypageInterestProductAdapter = MypageInterestProductAdapter(this)

        rv_mypage_interest_list.adapter = mypageInterestProductAdapter

        rv_mypage_interest_list.layoutManager = GridLayoutManager(this,2)

        getInterestResponse()
    }

    private fun makeListener(){
        setBackClickListener()
    }

    private fun setBackClickListener(){
        btn_mypage_interest_back.setOnClickListener {

            for(item in mypageInterestProductAdapter.selectedItem) {
                //Log.d("명, 선택취소번호", item.toString())
                deleteInterestProductResponse(item)
            }

            finish()
        }
    }

    private fun checkProductCount(){
        Log.d("명2",mypageInterestProductAdapter.data.toString())
        txt_mypage_interest_count.text = mypageInterestProductAdapter.itemCount.toString()

        if(mypageInterestProductAdapter.itemCount > 0){
            rv_mypage_interest_list.visibility = View.VISIBLE
            txt_mypage_interest_none.visibility = View.GONE
        }
    }

    private fun getInterestResponse(){
        val call: Call<MypageInterestData> = RequestURL.service.getInterestList(
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<MypageInterestData> {
                override fun onFailure(call: Call<MypageInterestData>, t: Throwable) {
                   //Log.d("명",t.toString())
                }

                override fun onResponse(
                    call: Call<MypageInterestData>,
                    response: Response<MypageInterestData>
                ) {
                    if(response.isSuccessful){
                        val interestList=response.body()!!

                        val interest = mutableListOf<RvMypageInterestListItem>()
                        for(item in interestList.data){
                            interest.add(RvMypageInterestListItem(
                                item.productIdx,
                                item.imgUrl,
                                item.brand,
                                item.name,
                                item.overseas,
                                item.day
                            ))
                        }

                        mypageInterestProductAdapter.data=interest
                        mypageInterestProductAdapter.notifyDataSetChanged()

                        checkProductCount()
                    }
                }

            }
        )
    }

    private fun deleteInterestProductResponse(idx: Int){
        val call: Call<MypageDeleteInterestData> = RequestURL.service.deleteInterestProduct(
            product_idx = idx,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<MypageDeleteInterestData> {
                override fun onFailure(call: Call<MypageDeleteInterestData>, t: Throwable) {
                    Log.d("관심 제품 취소 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<MypageDeleteInterestData>,
                    response: Response<MypageDeleteInterestData>
                ) {
                    if(response.isSuccessful){
                        val message=response.body()!!.message

                        Log.d("관심 제품 취소 성공", "메시지 : $message")
                    }
                }

            }
        )
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mypage_interest_product.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}