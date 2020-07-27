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

        makeController()
    }

    // 사용자 입력 확인
    private fun makeController(){
        // 이름 입력 실시간 검사
        edt_nick?.addTextChangedListener(object: TextWatcher{
            var nick_length = 0
            override fun afterTextChanged(p0: Editable?) {
                nick_length = edt_nick?.length()!!
                // 입력값 있는 경우
                if(nick_length > 0){
                    btn_nick_next?.isEnabled = true
                    btn_nick_next?.setTextColor(resources.getColor(R.color.colorWhite))
                }
                // 입력값 없는 경우
                else{
                    btn_nick_next?.isEnabled = false
                    btn_nick_next?.setTextColor(resources.getColor(R.color.colorDarkGray))
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })

        // 다음 버튼 처리
        btn_nick_next?.setOnClickListener{
            val gender_intent = Intent(this,ResearchGenderActivity::class.java)
            gender_intent.putExtra("nick", edt_nick.text.toString())

            startActivity(gender_intent)
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_nick.setPadding(0, statusBarHeightInfo(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun statusBarHeightInfo(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}