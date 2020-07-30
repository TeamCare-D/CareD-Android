package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import com.caredirection.cadi.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_research_gender.*
import kotlinx.android.synthetic.main.dialog_research_year.*

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
            checkNextButton()

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

        setDividerColor(npYear, this.resources.getColor(R.color.colorWhite))

        btnCancel.setOnClickListener {
            yearDialog.dismiss()
            yearDialog.cancel()
        }

        btnConfirm.setOnClickListener {
            btn_year.text = npYear.value.toString()
            btn_year?.isChecked = true

            yearDialog.dismiss()
            yearDialog.cancel()
        }

        yearDialog.setContentView(yearView)
        yearDialog.setCanceledOnTouchOutside(false)
        yearDialog.create()
        yearDialog.show()
    }

    private fun setDividerColor(picker: NumberPicker, color: Int)
    {
        val pickerFields = NumberPicker::class.java.declaredFields
        for (pf in pickerFields) {
            if (pf.name == "mSelectionDivider")
            {
                pf.isAccessible = true
                try
                {
                    val colorDrawable = ColorDrawable(color)
                    pf.set(picker, colorDrawable)
                } catch (e: IllegalArgumentException)
                {
                    e.printStackTrace()
                } catch (e: Resources.NotFoundException)
                {
                    e.printStackTrace()
                } catch (e: IllegalAccessException)
                {
                    e.printStackTrace()
                }
                break
            }
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