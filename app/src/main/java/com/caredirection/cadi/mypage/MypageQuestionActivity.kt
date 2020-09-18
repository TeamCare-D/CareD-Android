package com.caredirection.cadi.mypage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.MypageRequestData
import com.caredirection.cadi.network.RequestURL
import kotlinx.android.synthetic.main.activity_mypage_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_question)

        setStatusBarTransparent()

        makeListener()

        checkNameEmpty()
    }

    private fun makeListener(){
        setBackClickListener()
        setCompleteClickListener()
    }

    private fun setBackClickListener(){
        btn_mypage_question_back.setOnClickListener {
            showBackDialog()
        }
    }

    private fun setCompleteClickListener(){
        btn_mypage_question_complete.setOnClickListener {
            postRequestProductResponse(edt_mypage_question.text.toString())

            finish()
        }
    }

    private fun showBackDialog(){
        val backDialog = AppCompatDialog(this)
        val backLayout : LayoutInflater = LayoutInflater.from(this)
        val backView : View = backLayout.inflate(R.layout.dialog_popup,null)

        val btnCancel : Button = backView.findViewById(R.id.btn_popup_cancel)
        val btnConfirm : Button = backView.findViewById(R.id.btn_popup_confirm)
        val txtTitle : TextView = backView.findViewById(R.id.txt_popup_tilte)

        txtTitle.text = "입력하신 내용이 문의되지 않았습니다.\n문의하기를 나가시겠습니까?"

        btnCancel.setOnClickListener {
            backDialog.cancel()
        }

        btnConfirm.setOnClickListener {
            backDialog.dismiss()
            finish()
        }

        backDialog.setContentView(backView)
        backDialog.setCanceledOnTouchOutside(false)
        backDialog.create()
        backDialog.show()
    }

    private fun checkNameEmpty(){
        edt_mypage_question?.addTextChangedListener(object: TextWatcher {
            var questionLength = 0
            override fun afterTextChanged(p0: Editable?) {
                questionLength = edt_mypage_question?.length()!!

                if(questionLength > 0){
                    btn_mypage_question_complete.isEnabled = true
                    btn_mypage_question_complete.setTextColor(resources.getColor(R.color.colorWhite))
                }
                else{
                    btn_mypage_question_complete.isEnabled = false
                    btn_mypage_question_complete.setTextColor(resources.getColor(R.color.colorCoolGray2))
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO("Not yet implemented")
            }
        })
    }

    private fun postRequestProductResponse(contents: String){
        val call: Call<MypageRequestData> = RequestURL.service.postRequest(
            contents = contents,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<MypageRequestData> {
                override fun onFailure(call: Call<MypageRequestData>, t: Throwable) {
                    Log.d("문의하기 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<MypageRequestData>,
                    response: Response<MypageRequestData>
                ) {
                    if(response.isSuccessful){
                        val message = response.body()!!.message

                        Log.d("문의하기 성공", "메시지 : $message")
                    }
                }

            }
        )
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mypage_question.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}