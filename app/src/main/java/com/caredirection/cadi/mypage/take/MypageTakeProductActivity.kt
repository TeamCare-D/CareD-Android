package com.caredirection.cadi.mypage.take

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.DummyMypageTakeProductList
import com.caredirection.cadi.register.search.RegisterSearchActivity
import kotlinx.android.synthetic.main.activity_mypage_take_product.*

class MypageTakeProductActivity : AppCompatActivity() {

    private lateinit var mypageTakeProductAdapter: MypageTakeProductAdapter
    private var dummyMypageTakeProductList = DummyMypageTakeProductList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_take_product)

        setStatusBarTransparent()

        takeListInit()
        makeListener()
    }

    private fun takeListInit(){
        mypageTakeProductAdapter = MypageTakeProductAdapter(this)

        rv_mypage_take_product_list.adapter = mypageTakeProductAdapter

        rv_mypage_take_product_list.layoutManager = LinearLayoutManager(this)

        mypageTakeProductAdapter.data = dummyMypageTakeProductList.getMypageTakeProductList()

        mypageTakeProductAdapter.notifyDataSetChanged()

        checkCompleteButton()
    }

    private fun makeListener(){
        setCloseClickListener()
        setAddClickListener()
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

    private fun checkCompleteButton(){
        btn_mypage_take_product_complete.isEnabled = false

        if(mypageTakeProductAdapter.itemCount > 0){
            btn_mypage_take_product_complete.isEnabled = true
            btn_mypage_take_product_complete.setTextColor(resources.getColor(R.color.colorWhite))
        }
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