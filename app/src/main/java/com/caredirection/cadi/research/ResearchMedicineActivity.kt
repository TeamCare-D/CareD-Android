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
import kotlinx.android.synthetic.main.activity_research_medicine.*

class ResearchMedicineActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var medicineButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_medicine)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initButtons()
        initProgressBar()

        makeListener()
    }

    private fun initButtons(){
        medicineButtons = listOf(
            btn_medicine_1,btn_medicine_2,btn_medicine_3,btn_medicine_4,btn_medicine_5,btn_medicine_6,btn_medicine_7,btn_medicine_8,btn_medicine_9,btn_medicine_10,
            btn_medicine_11,btn_medicine_12,btn_medicine_13,btn_medicine_14,btn_medicine_15,btn_medicine_16,btn_medicine_17,btn_medicine_18,btn_medicine_19
        )
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = (displayMetrics.widthPixels/5)*3
        param.height = getDisplayHeight()/6

        pb_research_medicine.layoutParams = param
        pb_research_medicine.progress = 100

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.translate3)
        pb_research_medicine.startAnimation(animation)
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
        btn_medicine_none.setOnClickListener {
            btn_medicine_none.isChecked = !btn_medicine_none.isChecked
            medicineButtons.forEach {
                it.isChecked = false
            }
            checkNextButton()
        }
    }

    private fun setButtonsClickListener(){
        medicineButtons.forEachIndexed { index, checkedTextView ->
            medicineButtons[index].setOnClickListener {
                medicineButtons[index].isChecked = !medicineButtons[index].isChecked
                btn_medicine_none.isChecked = false
                checkNextButton()
            }
        }
    }

    private fun setBackClickListener(){
        btn_medicine_back.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener(){
        btn_medicine_next.setOnClickListener {
            val allergyIntent = Intent(this, ResearchAllergyActivity::class.java)

            startActivity(allergyIntent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(btn_medicine_none.isChecked || medicineButtons.any{it.isChecked}){
            btn_medicine_next.isEnabled = true
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_medicine_next.isEnabled = false
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorDarkGray))
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_medicine.setPadding(0, getStatusBarHeight(this), 0, 0)
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