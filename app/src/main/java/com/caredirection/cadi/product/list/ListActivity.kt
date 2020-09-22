package com.caredirection.cadi.product.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.ProductSearchData
import com.caredirection.cadi.networkdata.ProductSearchInfoList
import com.caredirection.cadi.networkdata.criterionDescription1Data
import com.caredirection.cadi.product.list.FragmentDetail.FragmentBitaminComplex
import com.caredirection.cadi.product.list.FragmentDetail.FragmentDetail
import com.caredirection.cadi.product.list.FragmentDetail.FragmentDetailMulti
import com.caredirection.cadi.product.list.adapter.ProductListData
import com.caredirection.cadi.product.list.adapter.ProductListRvAdapter
import kotlinx.android.synthetic.main.activity_product_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ListActivity : AppCompatActivity() {


    private lateinit var fragmentDetail: Fragment
    private val fragmentDetailsimplicity = FragmentDetailSimplicity()
    private lateinit var active: Fragment

    private lateinit var productList: ProductSearchInfoList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val productName = intent.getStringExtra("name")

        Log.d("실험이다", productName.toString())

        keyowrdSetting(productName)




    }

    fun changeFragment(FragmentInstance: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_product_list_detail, FragmentInstance).commit()

        img_product_list_btn.setOnClickListener {

            if (active == fragmentDetail) {
                active = fragmentDetailsimplicity
                supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.nav_default_pop_enter_anim,
                    R.anim.nav_default_pop_exit_anim
                ).replace(R.id.fragment_product_list_detail, active).commit()
            } else if (active == fragmentDetailsimplicity) {
                active = fragmentDetail
                supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.nav_default_pop_enter_anim,
                    R.anim.nav_default_pop_exit_anim
                ).replace(R.id.fragment_product_list_detail, active).commit()
            }
        }
    }

    fun keyowrdSetting(productName: String?) {
        Log.d("종합 비타민은?", productName.toString())
        productRvSetting(productName)


        when (productName) {
            "종합 비타민" -> {
                fragmentDetail = FragmentDetailMulti()
            }
            "비타민B 컴플렉스" -> {
                fragmentDetail = FragmentBitaminComplex()
            }
            else -> {
                fragmentDetail = FragmentDetail()
                (fragmentDetail as FragmentDetail).productName = productName.toString()
            }
        }


        active = fragmentDetail
        changeFragment(fragmentDetail)
    }

    fun productRvSetting(productName: String?) {
        val productListAdapter = ProductListRvAdapter(this@ListActivity)


        val call: Call<ProductSearchData> = RequestURL.service.getSearchPrudct(keyword = productName, token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<ProductSearchData>{
                override fun onFailure(call: Call<ProductSearchData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProductSearchData>,
                    response: Response<ProductSearchData>
                ) {
                    try{
                        val data = response.body()!!.data
                        Log.d("이건 테스트", data.toString())
                        productListAdapter.items.addAll(data.products)
                        rv_product_list.adapter = productListAdapter
                    }catch (e: Exception){
                        Log.d("이건 에러", e.toString())
                    }


                }
            }

        )

    }




}