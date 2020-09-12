package com.caredirection.cadi.research

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.caredirection.cadi.R
import kotlinx.android.synthetic.main.activity_research_name.*

class ResearchNicknameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_name)

        setStatusBarTransparent()

        makeListener()
    }

    private fun makeListener(){
        setNextClickListener()
        setCloseClickListener()

        checkNicknameEmpty()
        checkNicknameFocus()
    }

    private fun setCloseClickListener(){
        btn_nick_close.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun setNextClickListener(){
        btn_nickNext?.setOnClickListener{
            val genderIntent = Intent(this,ResearchGenderActivity::class.java)
            genderIntent.putExtra("nick", edt_nick.text.toString())

            startActivity(genderIntent)
        }
    }

    private fun checkNicknameEmpty(){
        edt_nick?.addTextChangedListener(object: TextWatcher{
            var nickLength = 0
            override fun afterTextChanged(p0: Editable?) {
                nickLength = edt_nick?.length()!!

                if(nickLength > 0){
                    btn_nickNext?.isEnabled = true
                    btn_nickNext?.setTextColor(resources.getColor(R.color.colorWhite))
                }

                else{
                    btn_nickNext?.isEnabled = false
                    btn_nickNext?.setTextColor(resources.getColor(R.color.colorCoolGray2))
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