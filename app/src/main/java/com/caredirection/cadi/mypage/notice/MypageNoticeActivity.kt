package com.caredirection.cadi.mypage.notice

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.mypage.RvMypageNoticeListItem
import com.caredirection.cadi.data.network.MypageNoticeData
import com.caredirection.cadi.network.RequestURL
import kotlinx.android.synthetic.main.activity_mypage_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageNoticeActivity : AppCompatActivity() {

    private lateinit var mypageNoticeAdapter: MypageNoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage_notice)

        setStatusBarTransparent()

        makeListener()
        noticeListInit()
    }

    private fun noticeListInit(){
        mypageNoticeAdapter = MypageNoticeAdapter(this)

        rv_mypage_notice_list.adapter = mypageNoticeAdapter

        rv_mypage_notice_list.layoutManager = LinearLayoutManager(this)

        getNoticeResponse()
    }

    private fun makeListener(){
        setBackClickListener()
    }

    private fun setBackClickListener(){
        btn_mypage_notice_back.setOnClickListener {
            finish()
        }
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_mypage_notice.setPadding(0, getStatusBarHeight(this), 0, 0)
    }

    // 상태바 높이 정보
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }

    private fun getNoticeResponse(){
        val call: Call<MypageNoticeData> = RequestURL.service.getNoticeList()
        call.enqueue(
            object : Callback<MypageNoticeData> {
                override fun onFailure(call: Call<MypageNoticeData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<MypageNoticeData>,
                    response: Response<MypageNoticeData>
                ) {
                    if(response.isSuccessful){
                        val noticeInfo=response.body()!!

                        val notice = mutableListOf<RvMypageNoticeListItem>()
                        for(item in noticeInfo.data){
                            notice.add(RvMypageNoticeListItem(item.notice_idx,item.notice_time,item.notice_title,null))
                        }
                        mypageNoticeAdapter.data=notice
                        mypageNoticeAdapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }
}