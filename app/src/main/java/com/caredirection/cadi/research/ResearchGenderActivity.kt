package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_gender.*

class ResearchGenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_gender)

        setStatusBarTransparent()

        initTitle()

        makeListener()
    }

    private fun initTitle(){
        val nick = intent.getStringExtra("nick")

        txt_genderTitle.text = nick + "님의\n건강기능식품 선택을 도와드릴게요"
    }

    // 버튼 클릭리스너 지정
    private fun makeListener(){
        setGenderClickListener()
        setYearClickListener()
        setBackClickListener()
        setNextClickListener()
    }

    private fun setGenderClickListener(){
        btn_women?.setOnClickListener {
            btn_women?.isChecked = true
            btn_man?.isChecked = false
            checkNextButton()
        }

        btn_man?.setOnClickListener {
            btn_women?.isChecked = false
            btn_man?.isChecked = true
            checkNextButton()
        }
    }

    private fun setYearClickListener(){
        btn_year?.setOnClickListener {
            btn_year?.isChecked = true
            checkNextButton()
            Toast.makeText(this,"아직 안했지롱", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setBackClickListener(){
        btn_genderBack?.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener() {
        btn_genderNext?.setOnClickListener {
            val diseaseIntent = Intent(this, ResearchDiseaseActivity::class.java)

            startActivity(diseaseIntent)
        }
    }

    // 다음 버튼 처리를 위한 확인
    private fun checkNextButton(){
        btn_genderNext.isEnabled = (btn_women.isChecked || btn_man.isChecked) && btn_year.isChecked
        if(btn_genderNext.isEnabled) btn_genderNext.setTextColor(resources.getColor(R.color.colorWhite))
        else btn_genderNext.setTextColor(resources.getColor(R.color.colorDarkGray))
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_researchGender.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}