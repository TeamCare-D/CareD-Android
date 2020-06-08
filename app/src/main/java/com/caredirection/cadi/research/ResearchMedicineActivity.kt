package com.caredirection.cadi.research

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.CheckedTextView
import android.widget.Toast
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_medicine.*

class ResearchMedicineActivity : AppCompatActivity() {

    private lateinit var medButtons: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_medicine)

        // 상태바 투명 설정
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_medicine.setPadding(0, statusBarHeight(this), 0, 0)

        medButtons = listOf(
            btn_medicine_A, btn_medicine_B, btn_medicine_C, btn_medicine_D, btn_medicine_E, btn_medicine_F, btn_medicine_G, btn_medicine_H
        )
        //TODO: 이어하기_keeper 연결

        makeController()
    }

    private fun makeController(){
        medButtons.forEachIndexed { index, checkedTextView ->
            medButtons[index].setOnClickListener {
                medButtons[index].isChecked = !medButtons[index].isChecked
                checkNextButton()
            }
        }

        btn_medicine_add.setOnClickListener {
            Toast.makeText(this,"아직 추가못하지롱",Toast.LENGTH_SHORT).show()
        }

        btn_medicine_back.setOnClickListener {
            finish()
        }

        btn_medicine_next.setOnClickListener {
            Toast.makeText(this,"끝이지롱",Toast.LENGTH_SHORT).show()
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(medButtons.any{it.isChecked}){
            btn_medicine_next.isEnabled = true
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_medicine_next.isEnabled = false
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorDarkGray))
        }
    }

    // 상태바 투명 설정
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}