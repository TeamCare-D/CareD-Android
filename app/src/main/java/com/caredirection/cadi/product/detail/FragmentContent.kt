package com.caredirection.cadi.product.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.networkdata.ProductDetailList
import com.caredirection.cadi.product.detail.adapter.*
import kotlinx.android.synthetic.main.fragment_product_detail.*

class FragmentContent : Fragment(R.layout.fragment_product_detail)  {

    lateinit var productDetailData: ProductDetailList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        intakeRvSetting()

        ingredientSetting()

        bitaminSetting()

        certificationSetting()

        productBuySetting()

        productExplainDetail()
    }
    fun intakeRvSetting(){
        val chartAdapter = DetailFunctionChartAdapter(requireContext())

        chartAdapter.items.addAll(productDetailData.product_functional_graph)

        chart_product_detail_intake.adapter = chartAdapter
    }

    fun ingredientSetting(){
        val ingredientAdapter = RvIngredientAdapter(requireContext())

        Log.d("이ㅣ것또한 테스트", productDetailData.toString())

        ingredientAdapter.items.addAll(productDetailData.product_functional_graph)
        rv_product_detail_intake_functional.adapter = ingredientAdapter
    }

    fun ingredientBitaminSetting(){

    }

    fun bitaminSetting(){
        var charBitaminAdapter = DetailBitaminChartAdapter(requireContext())

        charBitaminAdapter.items.addAll(productDetailData.product_vitamin_mineral_graph)

        chart_product_detail_bitamin.adapter = charBitaminAdapter
    }

    fun certificationSetting(){
        val certicationRvAdapter = CertificationRvAdapter()
        certicationRvAdapter.items.addAll(productDetailData.product_certification)
        rv_product_detail_intake_information_certification.adapter = certicationRvAdapter
    }


    fun productBuySetting(){

        val productBuyRvAdapter = ProductBuyRvAdapter(requireContext())
        productBuyRvAdapter.items.addAll(productDetailData.store_info)

        rv_product_detail_buy.adapter = productBuyRvAdapter

    }

    fun productExplainDetail(){
        txt_product_detail_intake_informatioin_way_content.text = productDetailData.product_suggested_use
        txt_product_detail_intake_informatioin_way_shape_content.text = productDetailData.product_capsule_info
        txt_product_detail_intake_informatioin_way_packing_content.text = productDetailData.product_package
        txt_product_detail_intake_informatioin_caution_content.text = productDetailData.product_warnings

    }
}