package com.caredirection.cadi.research.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CheckedTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.ResearchItemData
import com.caredirection.cadi.data.research.RvResearchListItem
import com.caredirection.cadi.network.RequestURL
import kotlinx.android.synthetic.main.activity_research_disease.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchDiseaseActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var disButtons: List<CheckedTextView>
    private lateinit var detailAdapter: ResearchDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_disease)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initProgressBar()
        initDiseaseList()

        makeListener()
    }

    private fun initDiseaseList(){
        detailAdapter = ResearchDetailAdapter(this)

        rv_research_disease.adapter = detailAdapter

        rv_research_disease.layoutManager = GridLayoutManager(this,2)

        getResearchItemResponse()
    }

    private fun getResearchItemResponse(){
        val call: Call<ResearchItemData> = RequestURL.service.getResearchList()
        call.enqueue(
            object : Callback<ResearchItemData> {
                override fun onFailure(call: Call<ResearchItemData>, t: Throwable) {
                    Log.d("설문조사 리스트 조회 실패","메시지 : $t")
                }

                override fun onResponse(
                    call: Call<ResearchItemData>,
                    response: Response<ResearchItemData>
                ) {
                    if(response.isSuccessful){
                        val researchList=response.body()!!

                        val researchItem = mutableListOf<RvResearchListItem>()
                        for(item in researchList.data.userWarning){
                            researchItem.add(
                                RvResearchListItem(
                                item.itemIdx,
                                item.name
                            )
                            )
                        }

                        detailAdapter.data=researchItem
                        detailAdapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        param.width = (displayMetrics.widthPixels/5)*2
        param.height = getDisplayHeight()/6

        pb_research_disease.layoutParams = param
        pb_research_disease.progress = 100

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.translate2)
        pb_research_disease.startAnimation(animation)
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
            btn_disease_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
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