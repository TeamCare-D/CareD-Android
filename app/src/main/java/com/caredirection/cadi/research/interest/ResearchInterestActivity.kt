package com.caredirection.cadi.research.interest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.TokenController
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.data.network.ResearchTokenData
import com.caredirection.cadi.data.research.ResearchDetailList
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.data.research.RvResearchListItem
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.register.list.RegisterListActivity
import com.caredirection.cadi.research.ResearchGenderActivity
import com.caredirection.cadi.research.allergy.ResearchAllergyAdapter
import com.caredirection.cadi.research.disease.ResearchDiseaseAdapter
import com.caredirection.cadi.research.medicine.ResearchMedicineAdapter
import kotlinx.android.synthetic.main.activity_research_interest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchInterestActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var interestAdapter: ResearchInterestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_interest)

        ResearchSelectList.researchActivityList.add(this)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initProgressBar()
        initInterestList()

        makeListener()
    }

    private fun initInterestList(){
        interestAdapter = ResearchInterestAdapter(this)

        rv_research_interest.adapter = interestAdapter

        rv_research_interest.layoutManager = LinearLayoutManager(this)

        val researchItem = mutableListOf<RvResearchListItem>()

        for(item in ResearchDetailList.getResearchList().data.userEfficacy){
            researchItem.add(
                RvResearchListItem(
                    item.itemIdx,
                    item.name
                )
            )
        }

        interestAdapter.data = researchItem

        interestAdapter.notifyDataSetChanged()
    }

    private fun initProgressBar(){
        var param : ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        param.width = (displayMetrics.widthPixels/5)*5
        param.height = getDisplayHeight()/6

        pb_research_interest.layoutParams = param
        pb_research_interest.progress = 100

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext,R.anim.translate5)
        pb_research_interest.startAnimation(animation)
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
        btn_interest_back.setOnClickListener {
            finish()
        }
    }

    private fun setCloseClickListener(){
        btn_interest_close.setOnClickListener {
            ResearchSelectList.showStopDialog(this)
        }
    }

    private fun setNextClickListener(){
        btn_interest_next.setOnClickListener {
            if(ResearchSelectList.checkFirst){
                showToastMessage()

                ResearchSelectList.checkFirst = false

                setSelectList()

                postResearchSelectResponse()

                val registerIntent = Intent(this, RegisterListActivity::class.java)

                startActivity(registerIntent)
            }
            else{
                (0 until ResearchSelectList.researchActivityList.size).forEach {
                    ResearchSelectList.researchActivityList[it].finish()
                }
            }

        }
    }

    private fun setSelectList(){
        ResearchSelectList.age = ResearchGenderActivity.age.toInt()
        ResearchSelectList.gender = ResearchGenderActivity.gender
        ResearchSelectList.selectedDiseaseList = ResearchDiseaseAdapter.selectedItem
        ResearchSelectList.selectedMedicineList = ResearchMedicineAdapter.selectedItem
        ResearchSelectList.selectedAllergyList = ResearchAllergyAdapter.selectedItem
        ResearchSelectList.selectedInterestList = ResearchInterestAdapter.selectedItem
    }

    private fun postResearchSelectResponse(){
        Log.d("실행", "실행됩니다")
        val call: Call<ResearchTokenData> = RequestURL.service.postResearchSelectedList(
            nickName = UserController.getName(this),
            gender = ResearchSelectList.gender,
            age = ResearchSelectList.age,
            warning = ResearchSelectList.selectedDiseaseList,
            diseaseMedicine = ResearchSelectList.selectedMedicineList,
            allergy = ResearchSelectList.selectedAllergyList,
            efficacy = ResearchSelectList.selectedInterestList
        )
        call.enqueue(
            object : Callback<ResearchTokenData> {
                override fun onFailure(call: Call<ResearchTokenData>, t: Throwable) {
                    Log.d("실행2", "실행됩니다")
                    Log.d("설문조사 등록 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<ResearchTokenData>,
                    response: Response<ResearchTokenData>
                ) {
                    if(response.isSuccessful){
                        Log.d("실행3", "실행됩니다")
                        val researchResponse = response.body()!!

                        val message = researchResponse.message
                        val token = researchResponse.data.token

                        TokenController.setAccessToken(parent, token)

                        Log.d("토큰", token)

                        Log.d("설문조사 등록 성공", "메시지 : $message")
                    }
                }

            }
        )
    }

    private fun showToastMessage(){
        var toastView = layoutInflater.inflate(R.layout.toast_message, null)

        toastView.setBackgroundResource(android.R.drawable.toast_frame)

        var completeToast = Toast(this)
        completeToast.setGravity(Gravity.TOP,10,60)
        completeToast.view = toastView
        completeToast.show()
    }

    // 다음 버튼 활성화 처리
    fun checkNextButton(){
        if(ResearchInterestAdapter.selectedItem.size > 0){
            btn_interest_next.isEnabled = true
            btn_interest_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_interest_next.isEnabled = false
            btn_interest_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
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