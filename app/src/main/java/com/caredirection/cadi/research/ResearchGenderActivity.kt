package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.caredirection.cadi.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_research_gender.*

class ResearchGenderActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_gender)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initTitle()
        initProgressBar()

        makeListener()
    }

    private fun initTitle(){
        val nick = intent.getStringExtra("nick")

        txt_genderTitle.text = nick + "님의\n건강기능식품 선택을 도와드릴게요"
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = displayMetrics.widthPixels/4
        param.height = getDisplayHeight()/6

        pb_gender.layoutParams = param
        pb_gender.progress = 100
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
            showYearPicker()
        }
    }

    private fun showYearPicker(){
        val yearDialog = BottomSheetDialog(this)
        val yearLayout : LayoutInflater = LayoutInflater.from(this)
        val yearView : View = yearLayout.inflate(R.layout.dialog_research_year,null)

        val npYear : NumberPicker = yearView.findViewById(R.id.np_year)
        val btnCancel : Button = yearView.findViewById(R.id.btn_cancel)
        val btnConfirm : Button = yearView.findViewById(R.id.btn_confirm)

        npYear.minValue = 1900
        npYear.maxValue = 2020
        npYear.value = 1997

        npYear.wrapSelectorWheel = false
        npYear.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        npYear.textColor = this.resources.getColor(R.color.colorWhite)

        btnCancel.setOnClickListener {
            yearDialog.dismiss()
            yearDialog.cancel()

            checkNextButton()
        }

        btnConfirm.setOnClickListener {
            btn_year.text = npYear.value.toString()
            btn_year?.isChecked = true

            yearDialog.dismiss()
            yearDialog.cancel()

            checkNextButton()
        }

        yearDialog.setContentView(yearView)
        yearDialog.setCanceledOnTouchOutside(false)
        yearDialog.create()
        yearDialog.show()
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