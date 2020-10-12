package com.caredirection.cadi.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.data.UserController
import com.caredirection.cadi.data.network.MypageInterestData
import com.caredirection.cadi.data.network.TakeProductData
import com.caredirection.cadi.mypage.interest.MypageInterestProductActivity
import com.caredirection.cadi.mypage.notice.MypageNoticeActivity
import com.caredirection.cadi.mypage.take.MypageTakeProductActivity
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.research.ResearchNicknameActivity
import kotlinx.android.synthetic.main.fragment_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment : Fragment(R.layout.fragment_mypage) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initMypage()

        makeListener()
    }

    private fun initMypage(){
        txt_mypage_user_name.text = "${UserController.getName(context!!)}님"

        getTakeProductResponse()

        getInterestProductResponse()
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
                    if (response.isSuccessful) {
                        val takeList = response.body()!!

                        txt_mypage_taking_count.text = takeList.data.products.size.toString()
                    }
                }
            }
        )
    }

    private fun getInterestProductResponse(){
        val call: Call<MypageInterestData> = RequestURL.service.getInterestList(
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<MypageInterestData> {
                override fun onFailure(call: Call<MypageInterestData>, t: Throwable) {
                    Log.d("관심 제품 리스트 조회 실패","메시지 : $t")
                }

                override fun onResponse(
                    call: Call<MypageInterestData>,
                    response: Response<MypageInterestData>
                ) {
                    if(response.isSuccessful){
                        val interestList=response.body()!!

                        txt_mypage_interest_count.text = interestList.data.size.toString()
                    }
                }

            }
        )
    }

    private fun makeListener(){
        setTakeClickListener()
        setInterestClickListener()
        setNoticeClickListener()
        setRequestClickListener()
        setQuestionClickListener()
        setResearchClickListener()
    }
    
    private fun setResearchClickListener(){
        btn_mypage_modified.setOnClickListener {
            val researchIntent = Intent(context, ResearchNicknameActivity::class.java)

            startActivity(researchIntent)
        }
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