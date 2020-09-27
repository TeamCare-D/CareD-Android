package com.caredirection.cadi.product.detail

import android.os.Bundle
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
    }
    fun intakeRvSetting(){
        val chartAdapter = DetailFunctionChartAdapter(requireContext())

        chartAdapter.items.addAll(productDetailData.product_functional_graph)

        chart_product_detail_intake.adapter = chartAdapter
    }

    fun ingredientSetting(){
        val ingredientAdapter = RvIngredientAdapter(requireContext())


        ingredientAdapter.items.addAll(productDetailData.product_functional_graph)
        rv_product_detail_intake_functional.adapter = ingredientAdapter
    }


    fun bitaminSetting(){
        var charBitaminAdapter = DetailBitaminChartAdapter(requireContext())

        charBitaminAdapter.items.addAll(productDetailData.product_vitamin_mineral_graph)

        chart_product_detail_bitamin.adapter = charBitaminAdapter
    }

    fun certificationSetting(){
        val certicationRvAdapter = CertificationRvAdapter()

        certicationRvAdapter.items.add("")
        certicationRvAdapter.items.add("")
        certicationRvAdapter.items.add("")

        rv_product_detail_intake_information_certification.adapter = certicationRvAdapter
    }

    fun productBuySetting(){

        val productBuyRvAdapter = ProductBuyRvAdapter(requireContext())
        productBuyRvAdapter.items.addAll(productDetailData.store_info)

        rv_product_detail_buy.adapter = productBuyRvAdapter

    }
}