package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.data.research.ResearchSelectList
import kotlinx.android.synthetic.main.activity_research_name.*

class ResearchNicknameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_name)

        ResearchSelectList.researchActivityList.add(this)

        setStatusBarTransparent()

        makeListener()

        initName()
    }

    private fun initName(){
        if(UserController.getName(this).isNotEmpty()){
            edt_nick.text = Editable.Factory.getInstance().newEditable(UserController.getName(this))
        }
    }

    private fun makeListener(){
        setNextClickListener()
        setCloseClickListener()

        checkNicknameEmpty()
        checkNicknameFocus()
    }

    private fun setCloseClickListener(){
        btn_nick_close.setOnClickListener {
            ResearchSelectList.showStopDialog(this)
        }
    }

    private fun setNextClickListener(){
        btn_nick_next?.setOnClickListener{
            UserController.setName(this, edt_nick.text.toString())

            val genderIntent = Intent(this,ResearchGenderActivity::class.java)

            startActivity(genderIntent)
        }
    }

    private fun checkNicknameEmpty(){
        edt_nick?.addTextChangedListener(object: TextWatcher{
            var nickLength = 0
            override fun afterTextChanged(p0: Editable?) {
                nickLength = edt_nick?.length()!!

                if(nickLength > 0){
                    btn_nick_next?.isEnabled = true
                    btn_nick_next?.setTextColor(resources.getColor(R.color.colorWhite))
                }

                else{
                    btn_nick_next?.isEnabled = false
                    btn_nick_next?.setTextColor(resources.getColor(R.color.colorCoolGray2))
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun checkNicknameFocus(){
        edt_nick.setOnFocusChangeListener { _, hasFocus ->
            edt_nick.hint = ""
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_researchNick.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
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