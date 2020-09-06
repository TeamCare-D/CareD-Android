package com.caredirection.cadi.mypage.take

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.RvTakeListItem
import com.caredirection.cadi.data.network.TakeProductData
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.register.search.RegisterSearchActivity
import kotlinx.android.synthetic.main.activity_mypage_take_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageTakeProductActivity : AppCompatActivity() {

    private lateinit var mypageTakeProductAdapter: MypageTakeProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_take_product)

        setStatusBarTransparent()
        setSkipUnderLine()

        takeListInit()
        makeListener()
    }

    private fun takeListInit(){
        mypageTakeProductAdapter = MypageTakeProductAdapter(this)

        rv_mypage_take_product_list.adapter = mypageTakeProductAdapter

        rv_mypage_take_product_list.layoutManager = LinearLayoutManager(this)

        getTakeProductResponse()
    }

    private fun makeListener(){
        setCloseClickListener()
        setAddClickListener()
        setCompleteClickListener()
        setSkipClickListener()
    }

    private fun setCloseClickListener(){
        btn_mypage_take_product_close.setOnClickListener {
            finish()
        }
    }

    private fun setAddClickListener(){
        btn_mypage_take_product_add.setOnClickListener {
            val searchIntent = Intent(this, RegisterSearchActivity::class.java)

            startActivity(searchIntent)
        }
    }

    private fun setCompleteClickListener(){
        btn_mypage_take_product_complete.setOnClickListener {
            finish()
        }
    }

    private fun setSkipClickListener(){
        btn_mypage_take_prdouct_skip.setOnClickListener {
            finish()
        }
    }

    private fun checkCompleteButton(){
        btn_mypage_take_product_complete.isEnabled = false

        if(mypageTakeProductAdapter.itemCount > 0){
            btn_mypage_take_product_complete.isEnabled = true
            btn_mypage_take_product_complete.setTextColor(resources.getColor(R.color.colorWhite))
            btn_mypage_take_prdouct_skip.visibility = View.GONE
            btn_mypage_take_product_close.visibility = View.VISIBLE
        }
    }

    private fun setSkipUnderLine(){
        var spannableString = SpannableString("건너뛰기")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        btn_mypage_take_prdouct_skip.text = spannableString
    }

    private fun getTakeProductResponse(){
        val call: Call<TakeProductData> = RequestURL.service.getTakeList(
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<TakeProductData> {
                override fun onFailure(call: Call<TakeProductData>, t: Throwable) {
                    Log.d("복용 제품 리스트 조회 실패","메시지 : $t")
                }

                override fun onResponse(
                    call: Call<TakeProductData>,
                    response: Response<TakeProductData>
                ) {
                    if(response.isSuccessful){
                        val takeList=response.body()!!

                        val takeItem = mutableListOf<RvTakeListItem>()
                        for(item in takeList.data.products){
                            takeItem.add(
                                RvTakeListItem(
                                item.productIdx,
                                item.imgUrl,
                                item.brand,
                                item.name,
                                item.overseas,
                                item.day
                            )
                            )
                        }

                        mypageTakeProductAdapter.data=takeItem
                        mypageTakeProductAdapter.notifyDataSetChanged()

                        checkCompleteButton()
                    }
                }

            }
        )
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mypage_take_product.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}