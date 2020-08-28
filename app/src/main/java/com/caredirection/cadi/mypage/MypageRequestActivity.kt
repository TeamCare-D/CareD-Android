package com.caredirection.cadi.mypage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_mypage_request.*

class MypageRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_request)

        setStatusBarTransparent()

        checkNameEmpty()

        makeListener()
    }

    private fun makeListener(){
        setBackClickListener()
        setCompleteClickListener()
    }

    private fun setBackClickListener(){
        btn_mypage_request_back.setOnClickListener {
            finish()
        }
    }

    private fun setCompleteClickListener(){
        btn_mypage_request_complete.setOnClickListener {
            finish()
        }
    }

    private fun checkNameEmpty(){
        edt_mypage_request_name?.addTextChangedListener(object: TextWatcher{
            var nameLength = 0
            override fun afterTextChanged(p0: Editable?) {
                nameLength = edt_mypage_request_name?.length()!!

                if(nameLength > 0){
                    btn_mypage_request_complete.isEnabled = true
                    btn_mypage_request_complete.setTextColor(resources.getColor(R.color.colorWhite))
                }
                else{
                    btn_mypage_request_complete.isEnabled = false
                    btn_mypage_request_complete.setTextColor(resources.getColor(R.color.colorCoolGray2))
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

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mypage_request.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}