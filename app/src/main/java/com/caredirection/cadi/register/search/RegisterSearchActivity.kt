package com.caredirection.cadi.register.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.register.DummyRegisterSearchList
import com.caredirection.cadi.register.user.RegisterProductActivity
import com.caredirection.cadi.register.user.RegisterProductCompleteActivity
import kotlinx.android.synthetic.main.activity_register_search.*

class RegisterSearchActivity : AppCompatActivity() {

    private lateinit var registerSearchListAdapter: RegisterSearchAdapter
    private var dummyRegisterSearchList = DummyRegisterSearchList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_search)

        setStatusBarTransparent()
        setRegisterUnderLine()

        makeListener()
    }

    private fun initRegisterSearchResultList(){
        registerSearchListAdapter = RegisterSearchAdapter(this)

        rv_register_search_list.adapter = registerSearchListAdapter

        rv_register_search_list.layoutManager = GridLayoutManager(this,2)

        registerSearchListAdapter.data = dummyRegisterSearchList.getRegisterSearchResultList()

        registerSearchListAdapter.notifyDataSetChanged()

        txt_register_search_count.text = "결과 "+registerSearchListAdapter.itemCount+"건"
    }

    private fun makeListener(){
        setBackClickListener()
        setDeleteClickListener()
        setSearchClickListener()
        setRegisterClickListener()
        setNextClickListener()
    }

    private fun setBackClickListener(){
        btn_register_search_back.setOnClickListener {
            finish()
        }
    }

    private fun setDeleteClickListener(){
        btn_register_search_delete.setOnClickListener {
            edt_register_search_keyword.text = null
        }
    }

    private fun setSearchClickListener(){
        btn_register_search.setOnClickListener {
            initRegisterSearchResultList()
        }
    }

    private fun setNextClickListener(){
        btn_register_search_next.setOnClickListener {
            val userCompleteIntent = Intent(this,RegisterProductCompleteActivity::class.java)

            startActivity(userCompleteIntent)
        }
    }

    private fun setRegisterClickListener(){
        txt_register_search_user_product.setOnClickListener{
            val userProductIntent = Intent(this, RegisterProductActivity::class.java)

            startActivity(userProductIntent)
        }
    }

    private fun setRegisterUnderLine(){
        var spannableString = SpannableString("나만의 제품 등록")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        txt_register_search_user_product.text = spannableString
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