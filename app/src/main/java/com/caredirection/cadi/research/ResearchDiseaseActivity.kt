package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.CheckedTextView
import android.widget.Toast
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_disease.*

class ResearchDiseaseActivity : AppCompatActivity() {

    private lateinit var disButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)
        // 상태바 투명 설정
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_disease.setPadding(0, statusBarHeight(this), 0, 0)

        disButtons = listOf(
            btn_disease_A, btn_disease_B, btn_disease_C, btn_disease_D, btn_disease_E, btn_disease_F, btn_disease_G, btn_disease_H
        )
        //TODO: 이어하기_keeper 연결

        makeController()
    }

    // 사용자 입력 확인
    private fun makeController() {
        disButtons.forEachIndexed { index, checkedTextView ->
            disButtons[index].setOnClickListener {
                disButtons[index].isChecked = !disButtons[index].isChecked
                checkNextButton()
            }
        }

        btn_disease_search.setOnClickListener {
            Toast.makeText(this,"아직 못찾지롱", Toast.LENGTH_SHORT).show()
        }

        btn_disease_back.setOnClickListener {
            finish()
        }

        btn_disease_next.setOnClickListener {
            val medicine_intent = Intent(this, ResearchMedicineActivity::class.java)

            startActivity(medicine_intent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(disButtons.any{it.isChecked}){
            btn_disease_next.isEnabled = true
            btn_disease_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_disease_next.isEnabled = false
            btn_disease_next.setTextColor(resources.getColor(R.color.colorDarkGray))
        }

    }

    // 상태바 투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}