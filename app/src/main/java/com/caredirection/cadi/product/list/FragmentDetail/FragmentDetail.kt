package com.caredirection.cadi.product.list.FragmentDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.ProductSearchData
import com.caredirection.cadi.networkdata.ProductSearchInfoList
import com.caredirection.cadi.networkdata.criterionDescription3Data
import com.caredirection.cadi.product.list.FragmentDetailSimplicity
import com.caredirection.cadi.product.list.adapter.ProductStandardAdapter
import com.caredirection.cadi.product.list.adapter.ProductStandardDetailAdapter
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

        txt_product_list_detail_item2_title.text = productList.criterion.criterionName2
        txt_product_list_detail_item3_title.text = productList.criterion.criterionName3

        txt_fragment_product_list_detail_explanation1_sub_title1.text = productList.user.age
        txt_fragment_product_list_detail_explanation1_sub_title.text = productList.user.gender

        txt_fragment_product_list_detail_explanation1_content.text = productList.criterion.criterionDescription1.content[0]
        txt_fragment_product_list_detail_explanation2_content.text = productList.criterion.criterionDescription2.content[0]




        standardDetail(productList.criterion.criterionDescription3)


        standardSetting(productList)
    }

    fun standardSetting(productList: ProductSearchInfoList){
        val productStandardAdapter1 = ProductStandardAdapter()
        productStandardAdapter1.items.addAll(productList.criterion.criterionValue1)
        rv_product_list_detail_item1_content.adapter = productStandardAdapter1

        val productStandardAdapter2 = ProductStandardAdapter()
        productStandardAdapter2.items.addAll(productList.criterion.criterionValue2)
        rv_product_list_detail_item2_content.adapter = productStandardAdapter2

        val productStandardAdapter3 = ProductStandardAdapter()
        productStandardAdapter3.items.addAll(productList.criterion.criterionValue3)
        rv_product_list_detail_item3_content.adapter = productStandardAdapter3
    }

    fun standardDetail(item: criterionDescription3Data){
        val productStandardDetailAdapter = ProductStandardDetailAdapter()
        productStandardDetailAdapter.itemsContent.addAll(item.content)
        productStandardDetailAdapter.itemsSubtitle.addAll(item.subTitle)

        rv_fragment_product_list_detail.adapter = productStandardDetailAdapter
    }


}