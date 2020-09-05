package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_allergy.*

class ResearchAllergyActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var allergyButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_allergy)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initButtons()
        initProgressBar()

        makeListener()
    }

    private fun initButtons(){
        allergyButtons = listOf(
            btn_allergy_1,btn_allergy_2,btn_allergy_3,btn_allergy_4,btn_allergy_5,btn_allergy_6,btn_allergy_7,btn_allergy_8,btn_allergy_9,btn_allergy_10,
            btn_allergy_11,btn_allergy_12,btn_allergy_13,btn_allergy_14,btn_allergy_15,btn_allergy_16,btn_allergy_17,btn_allergy_18,btn_allergy_19,btn_allergy_20
        )
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = (displayMetrics.widthPixels/5)*4
        param.height = getDisplayHeight()/6

        pb_research_allergy.layoutParams = param

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.translate2)
        pb_research_allergy.startAnimation(animation)
    }

    private fun getDisplayHeight():Int{
        val displayHeight = displayMetrics.heightPixels
        val statusBarHeight = getStatusBarHeight(this)

        if(getNavigationBarInfo(this)){
            val navigationBarHeight = getNavigationBarHeight(this)
            return displayHeight-statusBarHeight-navigationBarHeight
        }
        return displayHeight-statusBarHeight
    }

    private fun makeListener(){
        setNoneClickListener()
        setButtonsClickListener()
        setBackClickListener()
        setNextClickListener()
    }

    private fun setNoneClickListener(){
        btn_allergy_none.setOnClickListener {
            btn_allergy_none.isChecked = !btn_allergy_none.isChecked
            allergyButtons.forEach {
                it.isChecked = false
            }
            checkNextButton()
        }
    }

    private fun setButtonsClickListener() {
        allergyButtons.forEachIndexed { index, _ ->
            allergyButtons[index].setOnClickListener {
                allergyButtons[index].isChecked = !allergyButtons[index].isChecked
                btn_allergy_none.isChecked = false
                checkNextButton()
            }
        }
    }

    private fun setBackClickListener(){
        btn_allergy_back.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener(){
        btn_allergy_next.setOnClickListener {
            val interestIntent = Intent(this, ResearchInterestActivity::class.java)

            startActivity(interestIntent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(btn_allergy_none.isChecked || allergyButtons.any{it.isChecked}){
            btn_allergy_next.isEnabled = true
            btn_allergy_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_allergy_next.isEnabled = false
            btn_allergy_next.setTextColor(resources.getColor(R.color.colorDarkGray))
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_allergy.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 네비게이션바 높이 정보
    private fun getNavigationBarHeight(context: Context): Int{
        val resourceBottom = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")

        return if (resourceBottom > 0) context.resources.getDimensionPixelSize(resourceBottom)
        else 0
    }

    // 네비게이션바 구분(소프트/하드)
    private fun getNavigationBarInfo(context: Context):Boolean{
        val resourceBottom = context.resources.getIdentifier("config_showNavigationBar", "bool", "android")

        return context.resources.getBoolean(resourceBottom)
    }
}