package com.caredirection.cadi.mypage.interest

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.DummyMypageInterestProductList
import kotlinx.android.synthetic.main.activity_mypage_interest_product.*

class MypageInterestProductActivity : AppCompatActivity() {

    private lateinit var mypageInterestProductAdapter: MypageInterestProductAdapter
    private var dummyMypageInterestProductList = DummyMypageInterestProductList()

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

        mypageInterestProductAdapter.data = dummyMypageInterestProductList.getMypageInterestProductList()

        mypageInterestProductAdapter.notifyDataSetChanged()

        checkProductCount()
    }

    private fun makeListener(){
        setBackClickListener()
    }

    private fun setBackClickListener(){
        btn_mypage_interest_back.setOnClickListener {
            finish()
        }
    }

    private fun checkProductCount(){
        txt_mypage_interest_count.text = mypageInterestProductAdapter.itemCount.toString()

        if(mypageInterestProductAdapter.itemCount > 0){
            rv_mypage_interest_list.visibility = View.VISIBLE
            txt_mypage_interest_none.visibility = View.GONE
        }
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