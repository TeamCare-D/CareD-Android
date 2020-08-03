package com.caredirection.cadi.research.interest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.DummyInterest
import com.caredirection.cadi.research.medicine.ResearchMedicineActivity
import kotlinx.android.synthetic.main.activity_research_interest.*

class ResearchInterestActivity : AppCompatActivity() {

    private lateinit var intButtons: List<CheckedTextView>
    private lateinit var interestAdapter: InterestAdapter
    private var dummyInterest = DummyInterest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_interest)

        setStatusBarTransparent()

        initInterestList()

        makeListener()
    }

    private fun initInterestList(){
        interestAdapter = InterestAdapter(this)

        rv_interest.adapter = interestAdapter

        rv_interest.layoutManager = LinearLayoutManager(this)

        interestAdapter.data = dummyInterest.getInterestList()

        interestAdapter.notifyDataSetChanged()
    }

    // 버튼 클릭리스너 지정
    private fun makeListener(){
        //setButtonsClickListener()
        setBackClickListener()
        setNextClickListener()
    }

    private fun setBackClickListener(){
        btn_interest_back.setOnClickListener {
            finish()
        }
    }

    private fun setNextClickListener(){
        btn_interest_next.setOnClickListener {
            val interestIntent = Intent(this, ResearchMedicineActivity::class.java)

            startActivity(interestIntent)
        }
    }

    // 다음 버튼 활성화 처리
    private fun checkNextButton(){
        if(intButtons.any{it.isChecked}){
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
}