package com.caredirection.cadi.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.mypage.interest.MypageInterestProductActivity
import com.caredirection.cadi.mypage.notice.MypageNoticeActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment(R.layout.fragment_mypage) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        makeListener()
    }

    private fun makeListener(){
        setInterestClickListener()
        setNoticeClickListener()
    }

    private fun setInterestClickListener(){
        btn_mypage_interest_product.setOnClickListener {
            val interestIntent = Intent(context, MypageInterestProductActivity::class.java)

            startActivity(interestIntent)
        }
    }

    private fun setNoticeClickListener(){
        btn_mypage_notice.setOnClickListener {
            val noticeIntent = Intent(context, MypageNoticeActivity::class.java)

            startActivity(noticeIntent)
        }
    }
}