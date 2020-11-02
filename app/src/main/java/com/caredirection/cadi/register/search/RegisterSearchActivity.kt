package com.caredirection.cadi.register.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.RegisterTakeProductData
import com.caredirection.cadi.data.network.TakeSearchData
import com.caredirection.cadi.data.register.DummyRegisterSearchList
import com.caredirection.cadi.data.register.RvTakeSearchItem
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.register.user.RegisterProductActivity
import kotlinx.android.synthetic.main.activity_register_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterSearchActivity : AppCompatActivity() {

    private lateinit var registerSearchListAdapter: RegisterSearchAdapter
    private val dummyRegisterSearchList = DummyRegisterSearchList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_search)

        setStatusBarTransparent()
        setRegisterUnderLine()

        makeListener()
        checkKeywordEmpty()
    }

    private fun initRegisterSearchResultList(){
        registerSearchListAdapter = RegisterSearchAdapter(this)

        rv_register_search_list.adapter = registerSearchListAdapter

        rv_register_search_list.layoutManager = GridLayoutManager(this,2)

        txt_register_search_count.text = "결과 ${registerSearchListAdapter.itemCount}건"

        checkSearchResult()

        getTakeSearchResponse(edt_register_search_keyword.text.toString())
    }

    private fun makeListener(){
        setBackClickListener()
        setDeleteClickListener()
        setSearchClickListener()
        setRegisterClickListener()
        setNextClickListener()
    }

    private fun setBackClickListener(){
        btn_register_search_back.setOnClickListener {
            finish()
        }
    }

    private fun setDeleteClickListener(){
        btn_register_search_delete.setOnClickListener {
            edt_register_search_keyword.text = null
        }
    }

    private fun setSearchClickListener(){
        btn_register_search.setOnClickListener {
            initRegisterSearchResultList()
        }
    }

    private fun setNextClickListener(){
        btn_register_search_complete.setOnClickListener {

            for(item in registerSearchListAdapter.selectedItem) {
                //postTakeProductResponse(item)
            }

            finish()
        }
    }

    private fun setRegisterClickListener(){
        txt_register_search_user_product.setOnClickListener{
            val userProductIntent = Intent(this, RegisterProductActivity::class.java)

            startActivity(userProductIntent)
        }
    }

    private fun checkSearchResult(){
        if(registerSearchListAdapter.itemCount > 0){
            cl_register_search_result.visibility = View.VISIBLE
            txt_register_search_none.visibility = View.GONE
        }
        else{
            cl_register_search_result.visibility = View.GONE
            txt_register_search_none.visibility = View.VISIBLE
        }
    }

    fun checkRegisterButton(){

        if(registerSearchListAdapter.selectedItem.size > 0){
            btn_register_search_complete.isEnabled = true
            btn_register_search_complete.setTextColor(getColor(R.color.colorWhite))
        }
        else{
            btn_register_search_complete.isEnabled = false
            btn_register_search_complete.setTextColor(getColor(R.color.colorCoolGray2))
        }
    }

    private fun checkKeywordEmpty(){
        edt_register_search_keyword?.addTextChangedListener(object: TextWatcher {
            var keywordLength = 0
            override fun afterTextChanged(p0: Editable?) {
                keywordLength = edt_register_search_keyword?.length()!!

                if(keywordLength > 0){
                    btn_register_search_delete.visibility = View.VISIBLE
                }
                else{
                    btn_register_search_delete.visibility = View.GONE
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

    private fun setRegisterUnderLine(){
        var spannableString = SpannableString("나만의 제품 등록")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        txt_register_search_user_product.text = spannableString
    }

    private fun getTakeSearchResponse(keyword: String){
        val call: Call<TakeSearchData> = RequestURL.service.getTakeSearchList(
            keyword = keyword,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<TakeSearchData> {
                override fun onFailure(call: Call<TakeSearchData>, t: Throwable) {
                    Log.d("복용 제품 검색 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<TakeSearchData>,
                    response: Response<TakeSearchData>
                ) {
                    if(response.isSuccessful){
                        val searchList=response.body()!!

                        val searchItem = mutableListOf<RvTakeSearchItem>()
                        for(item in searchList.data.products){
                            searchItem.add(
                                RvTakeSearchItem(
                                    item.productIdx,
                                    item.imgUrl,
                                    item.brand,
                                    item.name,
                                    item.overseas,
                                    item.day
                                )
                            )
                        }

                        registerSearchListAdapter.data=searchItem
                        registerSearchListAdapter.notifyDataSetChanged()

                        txt_register_search_count.text = "결과 "+registerSearchListAdapter.itemCount+"건"
                        checkSearchResult()

//                        Log.d("복용 제품 검색 성공", "메시지 : ${searchList.message}")
                    }
                }

            }
        )
    }

    private fun postTakeProductResponse(idx: Int){
        val call: Call<RegisterTakeProductData> = RequestURL.service.postTakeProduct(
            product_idx = idx,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )
        call.enqueue(
            object : Callback<RegisterTakeProductData> {
                override fun onFailure(call: Call<RegisterTakeProductData>, t: Throwable) {
                    Log.d("복용 제품 등록 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<RegisterTakeProductData>,
                    response: Response<RegisterTakeProductData>
                ) {
                    if(response.isSuccessful){
                        val message = response.body()!!.message

                        Log.d("복용 제품 등록 성공", "메시지 : $message")
                    }
                }

            }
        )
    }

    // 상태바 투명 설정
    private fun setStatusBarTransparent(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        cl_register_search.setPadding(0, getStatusBarHeight(this), 0, 0)
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