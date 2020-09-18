package com.caredirection.cadi.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.mypage.interest.MypageInterestProductActivity
import com.caredirection.cadi.mypage.notice.MypageNoticeActivity
import com.caredirection.cadi.mypage.take.MypageTakeProductActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment(R.layout.fragment_mypage) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initMypage()

        makeListener()
    }

    private fun initMypage(){
        txt_mypage_user_name.text = "${UserController.getName(context!!)}+님"

        //txt_mypage_taking_count.text = takeList.data.products.size.toString()

        //txt_mypage_interest_count.text = interestList.data.size.toString()
    }

    private fun makeListener(){
        setTakeClickListener()
        setInterestClickListener()
        setNoticeClickListener()
        setRequestClickListener()
        setQuestionClickListener()
    }

    private fun setTakeClickListener(){
        btn_mypage_take_product.setOnClickListener {
            val takeIntent = Intent(context, MypageTakeProductActivity::class.java)

            startActivity(takeIntent)
        }
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

    private fun setRequestClickListener(){
        btn_mypage_request.setOnClickListener {
            val requestIntent = Intent(context, MypageRequestActivity::class.java)

            startActivity(requestIntent)
        }
    }

    private fun setQuestionClickListener(){
        btn_mypage_question.setOnClickListener {
            val questionIntent = Intent(context, MypageQuestionActivity::class.java)

            startActivity(questionIntent)
        }
    }
}