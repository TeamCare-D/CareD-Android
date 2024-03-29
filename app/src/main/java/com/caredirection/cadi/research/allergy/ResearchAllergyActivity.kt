package com.caredirection.cadi.research.allergy

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
import com.caredirection.cadi.research.interest.ResearchInterestActivity
import kotlinx.android.synthetic.main.activity_research_allergy.*

class ResearchAllergyActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var allergyAdapter: ResearchAllergyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_allergy)

        ResearchSelectList.researchActivityList.add(this)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initAllergyList()
        initProgressBar()

        makeListener()
    }

    private fun initAllergyList(){
        allergyAdapter = ResearchAllergyAdapter(this)

        rv_research_allergy.adapter = allergyAdapter

        rv_research_allergy.layoutManager = GridLayoutManager(this,2)

        val researchItem = mutableListOf<RvResearchListItem>()

        for(item in ResearchDetailList.getResearchList().data.userAllergy){
            researchItem.add(
                RvResearchListItem(
                    item.itemIdx,
                    item.name
                )
            )
        }

        allergyAdapter.data = researchItem

        allergyAdapter.notifyDataSetChanged()
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.width = (displayMetrics.widthPixels/5)*4
        param.height = getDisplayHeight()/6

        pb_research_allergy.layoutParams = param
        pb_research_allergy.progress = 100

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.translate4)
        pb_research_allergy.startAnimation(animation)
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

    private fun makeListener(){
        setBackClickListener()
        setNextClickListener()
        setCloseClickListener()
    }

    private fun setBackClickListener(){
        btn_allergy_back.setOnClickListener {
            finish()
        }
    }

    private fun setCloseClickListener(){
        btn_allergy_close.setOnClickListener {
            ResearchSelectList.showStopDialog(this)
        }
    }

    private fun setNextClickListener(){
        btn_allergy_next.setOnClickListener {
            val interestIntent = Intent(this, ResearchInterestActivity::class.java)

            startActivity(interestIntent)
        }
    }

    // 다음 버튼 활성화 처리
    fun checkNextButton(){
        if(ResearchAllergyAdapter.selectedItem.size > 0){
            btn_allergy_next.isEnabled = true
            btn_allergy_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_allergy_next.isEnabled = false
            btn_allergy_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_research_allergy.setPadding(0, getStatusBarHeight(this), 0, 0)
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