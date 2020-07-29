package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_name.*

class ResearchNicknameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_name)

        setStatusBarTransparent()

        checkNicknameEmpty()

        makeListener()
    }

    // 버튼 클릭리스너 지정
    private fun makeListener(){
        // 다음 버튼 처리
        btn_nickNext?.setOnClickListener{
            val genderIntent = Intent(this,ResearchGenderActivity::class.java)
            genderIntent.putExtra("nick", edt_nick.text.toString())

            startActivity(genderIntent)
        }
    }

    // 사용자 입력 확인
    private fun checkNicknameEmpty(){
        // 이름 입력 실시간 검사
        edt_nick?.addTextChangedListener(object: TextWatcher{
            var nickLength = 0
            override fun afterTextChanged(p0: Editable?) {
                nickLength = edt_nick?.length()!!
                // 입력값 있는 경우
                if(nickLength > 0){
                    btn_nickNext?.isEnabled = true
                    btn_nickNext?.setTextColor(resources.getColor(R.color.colorWhite))
                }
                // 입력값 없는 경우
                else{
                    btn_nickNext?.isEnabled = false
                    btn_nickNext?.setTextColor(resources.getColor(R.color.colorDarkGray))
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
        cl_researchNick.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}