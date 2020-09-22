package com.caredirection.cadi.product.list.FragmentDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.ProductSearchData
import com.caredirection.cadi.networkdata.ProductSearchInfoList
import com.caredirection.cadi.product.list.FragmentDetailSimplicity
import kotlinx.android.synthetic.main.fragment_product_list_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class FragmentDetail : Fragment(R.layout.fragment_product_list_detail) {

    lateinit var productList: ProductSearchInfoList
    lateinit var productName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getProductData()
    }

    fun getProductData() {

        val call: Call<ProductSearchData> = RequestURL.service.getSearchPrudct(
            keyword = productName,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE"
        )

        call.enqueue(
            object : Callback<ProductSearchData> {
                override fun onFailure(call: Call<ProductSearchData>, t: Throwable) {
                    Log.d("searchProductInfoSetting onFail", t.toString())
                }

                override fun onResponse(
                    call: Call<ProductSearchData>,
                    response: Response<ProductSearchData>
                ) {
                    try {
                        productList = response.body()!!.data
                        Log.d("테스트다", productList.toString())

                        productDataSetting(productList)

                    } catch (e: Exception) {
                        Log.d("이게 왜 에러?", e.toString())
                    }
                }
            }
        )

    }

    fun productDataSetting(productList: ProductSearchInfoList) {
        txt_fragment_product_list_detail_1.text = productList.criterion.ingredientName
        txt_fragment_product_list_detail_explain.text = productList.criterion.ingredientDescription
        txt_product_list_detail_item1_title.text = productList.criterion.criterionName1
        txt_product_list_detail_item1_content.text = productList.criterion.criterionValue1[1]
        txt_product_list_detail_item2_title.text = productList.criterion.criterionName2


    }

}