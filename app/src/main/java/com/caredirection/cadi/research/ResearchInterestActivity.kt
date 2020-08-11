package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.caredirection.cadi.R
import com.caredirection.cadi.home.HomeFragment
import kotlinx.android.synthetic.main.activity_research_interest.*

class ResearchInterestActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var interestButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_interest)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initButtons()
        initProgressBar()

        makeListener()
    }

    private fun initButtons(){
        interestButtons = listOf(
            btn_interest_1,btn_interest_2,btn_interest_3,btn_interest_4,btn_interest_5,btn_interest_6,btn_interest_7,btn_interest_8,btn_interest_9,btn_interest_10,btn_interest_11
        )
    }
    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = (displayMetrics.widthPixels/4)*4
        param.height = getDisplayHeight()/6

        pb_interest.layoutParams = param
        pb_interest.progress = 100
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

    // 버튼 클릭리스너 지정
    private fun makeListener(){
        setButtonsClickListener()
        setBackClickListener()
        //setNextClickListener()
    }

    private fun setButtonsClickListener(){
        interestButtons.forEachIndexed { index, checkedTextView ->
            interestButtons[index].setOnClickListener {
                interestButtons[index].isChecked = !interestButtons[index].isChecked
                checkNextButton()
            }
        }
    }

    private fun setBackClickListener(){
        btn_interest_back.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener(){
        btn_interest_next.setOnClickListener {
            val interestIntent = Intent(this, HomeFragment::class.java)

            startActivity(interestIntent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(interestButtons.any{it.isChecked}){
            btn_interest_next.isEnabled = true
            btn_interest_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_interest_next.isEnabled = false
            btn_interest_next.setTextColor(resources.getColor(R.color.colorDarkGray))
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_interest.setPadding(0, getStatusBarHeight(this), 0, 0)
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