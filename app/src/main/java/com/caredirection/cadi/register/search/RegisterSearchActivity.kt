package com.caredirection.cadi.register.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
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
        checkKeywordEmpty()
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

            if(registerSearchListAdapter.itemCount > 0){
                cl_register_search_result.visibility = View.VISIBLE
            }
            else{
                txt_register_search_none.visibility = View.VISIBLE
            }
        }
    }

    private fun setNextClickListener(){
        btn_register_search_next.setOnClickListener {
            Log.d("명",registerSearchListAdapter.selectedItems.toString())
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

    private fun checkKeywordEmpty(){
        edt_register_search_keyword?.addTextChangedListener(object: TextWatcher {
            var keywordLength = 0
            override fun afterTextChanged(p0: Editable?) {
                keywordLength = edt_register_search_keyword?.length()!!

                if(keywordLength > 0){
                    btn_register_search_delete.visibility = View.VISIBLE
                }
                else{
                    btn_register_search_delete.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
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