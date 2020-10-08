package com.caredirection.cadi.register.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.MainActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.TakeProductData
import com.caredirection.cadi.data.register.RvTakeListItem
import com.caredirection.cadi.data.research.ResearchSelectList
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.register.search.RegisterSearchActivity
import kotlinx.android.synthetic.main.activity_register_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterListActivity : AppCompatActivity() {

    private lateinit var registerListAdapter: RegisterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_list)

        ResearchSelectList.researchActivityList.add(this)

        setStatusBarTransparent()
        setSkipUnderLine()

        initRegisterList()
        makeListener()
    }

    private fun initRegisterList(){
        registerListAdapter = RegisterListAdapter(this)

        rv_register_list.adapter = registerListAdapter

        rv_register_list.layoutManager = LinearLayoutManager(this)

        getTakeProductResponse()
    }

    private fun makeListener(){
        setAddClickListener()
        setCompleteClickListener()
        setSkipClickListener()
    }

    private fun setAddClickListener(){
        btn_register_list_add.setOnClickListener {
            val searchIntent = Intent(this,RegisterSearchActivity::class.java)

            startActivity(searchIntent)
        }
    }

    private fun setCompleteClickListener(){
        btn_register_list_complete.setOnClickListener {
            val completeIntent = Intent(this,RegisterListCompleteActivity::class.java)

            startActivity(completeIntent)
        }
    }

    private fun setSkipClickListener(){
        btn_register_list_skip.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)

            startActivity(homeIntent)
        }
    }

    fun checkCompleteButton(){
        btn_register_list_complete.isEnabled = false
        btn_register_list_complete.setTextColor(resources.getColor(R.color.colorCoolGray2))
        btn_register_list_skip.visibility = View.VISIBLE
        btn_register_list_close.visibility = View.INVISIBLE

        if(registerListAdapter.itemCount > 0){
            btn_register_list_complete.isEnabled = true
            btn_register_list_complete.setTextColor(resources.getColor(R.color.colorWhite))
            btn_register_list_skip.visibility = View.GONE
            btn_register_list_close.visibility = View.VISIBLE
        }


    }

    private fun setSkipUnderLine(){
        var spannableString = SpannableString("건너뛰기")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        btn_register_list_skip.text = spannableString
    }

    private fun getTakeProductResponse(){
        val call: Call<TakeProductData> = RequestURL.service.getTakeList(
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<TakeProductData> {
                override fun onFailure(call: Call<TakeProductData>, t: Throwable) {
                    Log.d("복용 제품 리스트 조회 실패","메시지 : $t")
                }

                override fun onResponse(
                    call: Call<TakeProductData>,
                    response: Response<TakeProductData>
                ) {
                    if(response.isSuccessful){
                        val takeList=response.body()!!

                        val takeItem = mutableListOf<RvTakeListItem>()
                        for(item in takeList.data.products){
                            takeItem.add(
                                RvTakeListItem(
                                    item.productIdx,
                                    item.imgUrl,
                                    item.brand,
                                    item.name,
                                    item.overseas,
                                    item.day
                                )
                            )
                        }

                        registerListAdapter.data=takeItem
                        registerListAdapter.notifyDataSetChanged()

                        checkCompleteButton()
                    }
                }

            }
        )
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_list.setPadding(0, getStatusBarHeight(this), 0, 0)
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