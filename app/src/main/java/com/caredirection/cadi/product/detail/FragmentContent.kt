package com.caredirection.cadi.product.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.networkdata.ProductDetailList
import com.caredirection.cadi.product.detail.adapter.*
import kotlinx.android.synthetic.main.fragment_product_detail.*

class FragmentContent : Fragment(R.layout.fragment_product_detail)  {

    lateinit var productDetailData: ProductDetailList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        functionSetting()

        functionDetailSetting()

        bitaminDetailSetting()

        bitaminSetting()

        certificationSetting()

        productBuySetting()

        productExplainDetail()


    }
    fun functionSetting(){
        val chartAdapter = DetailFunctionChartAdapter(requireContext())

        chartAdapter.items.addAll(productDetailData.product_functional_graph)

        chart_product_detail_intake.adapter = chartAdapter

        functionSizeSetting()
    }

    fun functionDetailSetting(){
        val ingredientAdapter = FunctionDetailAdapter(requireContext())

        Log.d("이ㅣ것또한 테스트", productDetailData.toString())

        ingredientAdapter.items.addAll(productDetailData.product_functional_graph)
        rv_product_detail_intake_functional.adapter = ingredientAdapter
    }

    fun functionSizeSetting(){
        txt_product_detail_intake_function_number.text = productDetailData.product_functional_graph.size.toString()
    }

    fun bitaminSetting(){
        var charBitaminAdapter = DetailBitaminChartAdapter(requireContext())

        charBitaminAdapter.items.addAll(productDetailData.product_vitamin_mineral_graph)

        chart_product_detail_bitamin.adapter = charBitaminAdapter

        bitaminSizeSetting()
    }

    fun bitaminSizeSetting(){
        txt_product_detail_bitamin_title_number.text = productDetailData.product_vitamin_mineral_graph.size.toString()
    }

    fun bitaminDetailSetting(){
        val bitaminDetailAdapter = BitaminDetailAdapter(requireContext())

        bitaminDetailAdapter.items.addAll(productDetailData.product_vitamin_mineral_graph)

        rv_product_detail_intake_bitamin.adapter = bitaminDetailAdapter
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