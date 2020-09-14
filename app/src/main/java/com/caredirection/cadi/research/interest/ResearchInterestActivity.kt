package com.caredirection.cadi.research.interest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.data.network.DeleteTakeData
import com.caredirection.cadi.data.network.ResearchSelecListData
import com.caredirection.cadi.data.research.ResearchDetailList
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.data.research.RvResearchListItem
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.register.list.RegisterListActivity
import kotlinx.android.synthetic.main.activity_research_interest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchInterestActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var detailAdapter: ResearchInterestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_interest)

        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        setStatusBarTransparent()

        initProgressBar()
        initInterestList()

        makeListener()
    }

    private fun initInterestList(){
        detailAdapter = ResearchInterestAdapter(this)

        rv_research_interest.adapter = detailAdapter

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

        detailAdapter.data = researchItem

        detailAdapter.notifyDataSetChanged()
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
        //setButtonsClickListener()
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
            showDeleteDialog()
        }
    }

    private fun setNextClickListener(){
        btn_interest_next.setOnClickListener {
            showToastMessage()

            ResearchSelectList.setInterestList(detailAdapter.selectedItem)

            postResearchSelectResponse()

            val registerIntent = Intent(this, RegisterListActivity::class.java)

            startActivity(registerIntent)
        }
    }

    private fun postResearchSelectResponse(){
        val call: Call<DeleteTakeData> = RequestURL.service.postResearchSelectedList(
            userInfoReq = ResearchSelecListData(
                nickName = UserController.getName(this),
                gender = ResearchSelectList.getAge(),
                age = ResearchSelectList.getAge(),
                warning = ResearchSelectList.getDiseaseList(),
                diseaseMedicine = ResearchSelectList.getMedicineList(),
                allergy = ResearchSelectList.getAllergyList(),
                efficacy = ResearchSelectList.getInterestList()
            ),
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<DeleteTakeData> {
                override fun onFailure(call: Call<DeleteTakeData>, t: Throwable) {
                    Log.d("설문조사 등록 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<DeleteTakeData>,
                    response: Response<DeleteTakeData>
                ) {
                    if(response.isSuccessful){
                        val message = response.body()!!.message

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
        if(detailAdapter.selectedItem.size > 0){
            btn_interest_next.isEnabled = true
            btn_interest_next.setTextColor(resources.getColor(R.color.colorWhite))
        }
        else{
            btn_interest_next.isEnabled = false
            btn_interest_next.setTextColor(resources.getColor(R.color.colorCoolGray2))
        }
    }

    private fun showDeleteDialog(){
        val deleteDialog = AppCompatDialog(this)
        val deleteLayout : LayoutInflater = LayoutInflater.from(this)
        val deleteView : View = deleteLayout.inflate(R.layout.dialog_popup,null)

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