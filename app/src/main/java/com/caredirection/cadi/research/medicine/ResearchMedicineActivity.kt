package com.caredirection.cadi.research.medicine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.ResearchDetailList
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.data.research.RvResearchListItem
import com.caredirection.cadi.research.allergy.ResearchAllergyActivity
import kotlinx.android.synthetic.main.activity_research_medicine.*

class ResearchMedicineActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var medicineAdapter: ResearchMedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_medicine)

        ResearchSelectList.researchActivityList.add(this)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initProgressBar()
        initMedicineList()

        makeListener()
    }

    private fun initMedicineList(){
        medicineAdapter = ResearchMedicineAdapter(this)

        rv_research_medicine.adapter = medicineAdapter

        rv_research_medicine.layoutManager = GridLayoutManager(this, 2)

        val researchItem = mutableListOf<RvResearchListItem>()

        for(item in ResearchDetailList.getResearchList().data.userDiseaseMedicine){
            researchItem.add(
                RvResearchListItem(
                    item.itemIdx,
                    item.name
                )
            )
        }

        medicineAdapter.data = researchItem

        medicineAdapter.notifyDataSetChanged()
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
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
        setBackClickListener()
        setNextClickListener()
        setCloseClickListener()
    }

    private fun setBackClickListener(){
        btn_medicine_back.setOnClickListener {
            finish()
        }
    }

    private fun setCloseClickListener(){
        btn_medicine_close.setOnClickListener {
            ResearchSelectList.showStopDialog(this)
        }
    }

    private fun setNextClickListener(){
        btn_medicine_next.setOnClickListener {
            val allergyIntent = Intent(this, ResearchAllergyActivity::class.java)

            startActivity(allergyIntent)
        }
    }

    // 다음 버튼 활성화 처리
    fun checkNextButton(){
        if(ResearchMedicineAdapter.selectedItem.size > 0){
            btn_medicine_next.isEnabled = true
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_medicine_next.isEnabled = false
            btn_medicine_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) setDarkStatusBar()
    }

    // 상태바 어둡게
    private fun setDarkStatusBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
}