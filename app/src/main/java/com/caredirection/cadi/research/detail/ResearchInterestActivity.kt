package com.caredirection.cadi.research.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.research.DummyDetail
import com.caredirection.cadi.register.list.RegisterListActivity
import kotlinx.android.synthetic.main.activity_research_interest.*

class ResearchInterestActivity : AppCompatActivity() {

    private var displayMetrics = DisplayMetrics()
    private lateinit var intButtons: List<CheckedTextView>
    private lateinit var detailAdapter: ResearchDetailAdapter
    private var dummyDetail = DummyDetail()

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
        detailAdapter = ResearchDetailAdapter(this)

        rv_research_interest.adapter = detailAdapter

        rv_research_interest.layoutManager = LinearLayoutManager(this)

        detailAdapter.data = dummyDetail.getDetailList()

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
            val registerIntent = Intent(this, RegisterListActivity::class.java)

            startActivity(registerIntent)
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