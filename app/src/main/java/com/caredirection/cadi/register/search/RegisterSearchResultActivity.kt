package com.caredirection.cadi.register.search

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterSearchResultList
import kotlinx.android.synthetic.main.activity_register_search_result.*

class RegisterSearchResultActivity : AppCompatActivity() {

    private lateinit var registerSearchResultListAdapter: RegisterSearchResultAdapter
    private var dummyRegisterSearchResultList = DummyRegisterSearchResultList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_search_result)

        setStatusBarTransparent()

        initRegisterSearchResultList()
    }

    private fun initRegisterSearchResultList(){
        registerSearchResultListAdapter = RegisterSearchResultAdapter(this)

        rv_register_search_result_list.adapter = registerSearchResultListAdapter

        rv_register_search_result_list.layoutManager = GridLayoutManager(this,2)

        registerSearchResultListAdapter.data = dummyRegisterSearchResultList.getRegisterSearchResultList()

        registerSearchResultListAdapter.notifyDataSetChanged()
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_search_result.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}