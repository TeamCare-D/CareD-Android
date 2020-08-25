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
import kotlinx.android.synthetic.main.activity_research_disease.*

class ResearchDiseaseActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var diseaseButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initButtons()
        initProgressBar()

        makeListener()
    }

    private fun initButtons(){
        diseaseButtons = listOf(
            btn_disease_1, btn_disease_2, btn_disease_3, btn_disease_4, btn_disease_5, btn_disease_6, btn_disease_7, btn_disease_8, btn_disease_9, btn_disease_10,
            btn_disease_11, btn_disease_12, btn_disease_13, btn_disease_14, btn_disease_15, btn_disease_16, btn_disease_17, btn_disease_18, btn_disease_19
        )
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = (displayMetrics.widthPixels/4)*2
        param.height = getDisplayHeight()/6

        pb_disease.layoutParams = param
        pb_disease.progress = 100
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
        setNoneClickListener()
        setButtonsClickListener()
        setBackClickListener()
        setNextClickListener()
    }

    private fun setNoneClickListener(){
        btn_disease_none.setOnClickListener {
            btn_disease_none.isChecked = !btn_disease_none.isChecked
            diseaseButtons.forEach {
                it.isChecked = false
            }
            checkNextButton()
        }
    }

    private fun setButtonsClickListener() {
        diseaseButtons.forEachIndexed { index, _ ->
            diseaseButtons[index].setOnClickListener {
                diseaseButtons[index].isChecked = !diseaseButtons[index].isChecked
                btn_disease_none.isChecked = false
                checkNextButton()
            }
        }
    }

    private fun setBackClickListener(){
        btn_disease_back.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener(){
        btn_disease_next.setOnClickListener {
            val medicineIntent = Intent(this, ResearchMedicineActivity::class.java)

            startActivity(medicineIntent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(btn_disease_none.isChecked || diseaseButtons.any{it.isChecked}){
            btn_disease_next.isEnabled = true
            btn_disease_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_disease_next.isEnabled = false
            btn_disease_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_disease.setPadding(0, getStatusBarHeight(this), 0, 0)
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