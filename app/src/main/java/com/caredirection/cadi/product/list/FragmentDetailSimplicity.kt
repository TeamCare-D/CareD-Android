package com.caredirection.cadi.product.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.ProductSearchData
import com.caredirection.cadi.networkdata.ProductSearchInfoList
import com.caredirection.cadi.product.list.adapter.ProductStandardAdapter
import kotlinx.android.synthetic.main.fragment_product_list_detail_simplicity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FragmentDetailSimplicity: Fragment(R.layout.fragment_product_list_detail_simplicity) {

    lateinit var productList: ProductSearchInfoList
    lateinit var productName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        img_product_list_detail_simplicity_filter.setOnClickListener{
            val intent = Intent(requireContext(), ProductFilterActivity()::class.java)
            startActivity(intent)
        }

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
                        txt_product_list_detail_simplicity_item3_top_component.text = productName

                        ingredientSetting(productList)

                    } catch (e: Exception) {
                        Log.d("이게 왜 에러?", e.toString())
                    }
                }
            }
        )

    }

    fun ingredientSetting(productList: ProductSearchInfoList){
            val productStandardAdapter1 = ProductStandardAdapter()
            productStandardAdapter1.items.addAll(productList.criterion.criterionValue1)
            rv_product_list_detail_simplicity_item3_1_component.adapter = productStandardAdapter1

            val productStandardAdapter2 = ProductStandardAdapter()
            productStandardAdapter2.items.addAll(productList.criterion.criterionValue2)
            rv_product_list_detail_simplicity_item3_2_component.adapter = productStandardAdapter2

            val productStandardAdapter3 = ProductStandardAdapter()
            productStandardAdapter3.items.addAll(productList.criterion.criterionValue3)
            txt_product_list_detail_simplicity_item3_3_component.adapter = productStandardAdapter3
    }
}