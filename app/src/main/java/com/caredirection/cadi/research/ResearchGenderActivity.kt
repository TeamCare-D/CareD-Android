package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.caredirection.cadi.R
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.research.disease.ResearchDiseaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_research_gender.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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
        txt_gender_title.text = UserController.getName(this) + "님의\n건강기능식품 선택을 도와드릴게요"
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        param.width = displayMetrics.widthPixels/5
        param.height = getDisplayHeight()/6

        pb_research_gender.layoutParams = param
        pb_research_gender.progress = 100

        val animation: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.translate
        )
        pb_research_gender.startAnimation(animation)
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
        setCloseClickListener()
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
        val yearView : View = yearLayout.inflate(R.layout.dialog_research_year, null)

        val npYear : NumberPicker = yearView.findViewById(R.id.np_year)
        val btnCancel : TextView = yearView.findViewById(R.id.btn_cancel)
        val btnConfirm : TextView = yearView.findViewById(R.id.btn_confirm)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy")
        val formatted = current.format(formatter)

        npYear.minValue = 1900
        npYear.maxValue = formatted.toInt()
        npYear.value = 1990

        npYear.wrapSelectorWheel = false
        npYear.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        btnCancel.setOnClickListener {
            yearDialog.cancel()

            checkNextButton()
        }

        btnConfirm.setOnClickListener {
            btn_year.text = npYear.value.toString()
            btn_year?.isChecked = true

            yearDialog.dismiss()

            checkNextButton()
        }

        yearDialog.behavior.isHideable = false
        yearDialog.setContentView(yearView)
        yearDialog.setCanceledOnTouchOutside(false)
        yearDialog.create()
        yearDialog.show()
    }

    private fun setBackClickListener(){
        btn_gender_back?.setOnClickListener {
            finish()
        }
    }
    private fun setCloseClickListener(){
        btn_gender_close.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun setNextClickListener() {
        btn_genderNext?.setOnClickListener {
            setSelectedList()

            val diseaseIntent = Intent(this, ResearchDiseaseActivity::class.java)

            startActivity(diseaseIntent)
        }
    }

    private fun setSelectedList(){
        if(btn_women.isChecked){
            ResearchSelectList.setGender(0)
        }
        else{
            ResearchSelectList.setGender(1)
        }

        ResearchSelectList.setAge(btn_year.text.toString())
    }

    // 다음 버튼 처리를 위한 확인
    private fun checkNextButton(){
        btn_genderNext.isEnabled = (btn_women.isChecked || btn_man.isChecked) && btn_year.isChecked

        if(btn_genderNext.isEnabled) btn_genderNext.setTextColor(resources.getColor(R.color.colorWhite))
        else btn_genderNext.setTextColor(resources.getColor(R.color.colorCoolGray2))
    }

    private fun showDeleteDialog(){
        val deleteDialog = AppCompatDialog(this)
        val deleteLayout : LayoutInflater = LayoutInflater.from(this)
        val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup, null)

        val btnCancel : Button = deleteView.findViewById(R.id.btn_popup_cancel)
        val btnConfirm : Button = deleteView.findViewById(R.id.btn_popup_confirm)
        val txtTitle : TextView = deleteView.findViewById(R.id.txt_popup_tilte)

        txtTitle.text = "지금 설문을 중단하시면\n케어디의 서비스를 이용할 수 없습니다."

        btnCancel.setOnClickListener {
            deleteDialog.cancel()
        }

        btnConfirm.setOnClickListener {
            deleteDialog.dismiss()
        }

        deleteDialog.setContentView(deleteView)
        deleteDialog.setCanceledOnTouchOutside(false)
        deleteDialog.create()
        deleteDialog.show()
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
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
        val resourceBottom = context.resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        )

        return if (resourceBottom > 0) context.resources.getDimensionPixelSize(resourceBottom)
        else 0
    }

    // 네비게이션바 구분(소프트/하드)
    private fun getNavigationBarInfo(context: Context):Boolean{
        val resourceBottom = context.resources.getIdentifier(
            "config_showNavigationBar",
            "bool",
            "android"
        )

        return context.resources.getBoolean(resourceBottom)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setDarkStatusBar()
    }

    // 상태바 어둡게
    private fun setDarkStatusBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
}