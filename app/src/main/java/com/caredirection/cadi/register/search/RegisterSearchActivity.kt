package com.caredirection.cadi.register.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_register_search.*

class RegisterSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_search)

        setStatusBarTransparent()

        makeListener()
    }

    private fun makeListener(){
        setBackClickListener()
        setResetClickListener()
        setSearchClickListener()
    }

    private fun setBackClickListener(){
        btn_register_search_back.setOnClickListener {
            finish()
        }
    }

    private fun setResetClickListener(){
        btn_register_search_reset.setOnClickListener {
            edt_register_search_keyword.text = null
        }
    }

    private fun setSearchClickListener(){
        btn_register_search.setOnClickListener {
            val searchIntent = Intent(this, RegisterSearchResultActivity::class.java)

            startActivity(searchIntent)
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_search.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}