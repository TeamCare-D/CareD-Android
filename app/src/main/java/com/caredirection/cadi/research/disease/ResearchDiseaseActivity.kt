package com.caredirection.cadi.research.disease

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.DummyDisease
import com.caredirection.cadi.research.ResearchMedicineActivity
import kotlinx.android.synthetic.main.activity_research_disease.*

class ResearchDiseaseActivity : AppCompatActivity() {

    private lateinit var disButtons: List<CheckedTextView>
    private lateinit var diseaseAdapter: DiseaseAdapter
    private var dummyDisease = DummyDisease()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)

        setStatusBarTransparent()

        //initButtons()
        initDiseaseList()

        makeListener()
    }

    private fun initDiseaseList(){
        diseaseAdapter = DiseaseAdapter(this)

        rv_disease.adapter = diseaseAdapter

        rv_disease.layoutManager = GridLayoutManager(this,2)

        diseaseAdapter.data = dummyDisease.getDiseaseList()

        diseaseAdapter.notifyDataSetChanged()
    }

//    private fun initButtons(){
//        disButtons = listOf(
//            btn_disease_none, btn_disease_1, btn_disease_2, btn_disease_3, btn_disease_4, btn_disease_5, btn_disease_6, btn_disease_7
//        )
//    }

    // 버튼 클릭리스너 지정
    private fun makeListener(){
        //setButtonsClickListener()
        setBackClickListener()
        setNextClickListener()
    }

    private fun setButtonsClickListener() {
        disButtons.forEachIndexed { index, checkedTextView ->
            disButtons[index].setOnClickListener {
                disButtons[index].isChecked = !disButtons[index].isChecked
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
}