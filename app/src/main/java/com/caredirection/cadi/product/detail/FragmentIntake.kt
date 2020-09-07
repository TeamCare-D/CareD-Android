package com.caredirection.cadi.product.detail

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.ChartAdapter
import com.caredirection.cadi.adapter.ChartData
import com.caredirection.cadi.product.detail.adapter.*
import kotlinx.android.synthetic.main.dialog_proudct_ingredient_explaination.view.*
import kotlinx.android.synthetic.main.fragment_product_detail.*

class FragmentIntake : Fragment(R.layout.fragment_product_detail) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        intakeRvSetting()

        ingredientSetting()

        bitaminSetting()

        certificationSetting()

        productBuySetting()

        //dialogSetting()
    }
    fun intakeRvSetting(){
        val chartAdapter = ChartAdapter(requireContext())


        chartAdapter.items.add( ChartData( "비타민1",  0))
        chartAdapter.items.add( ChartData( "비타민1",  0))
        chartAdapter.items.add( ChartData( "비타민1",  0))
        chartAdapter.items.add( ChartData( "비타민1",  30))
        chartAdapter.items.add( ChartData( "비타민2",  50))
        chartAdapter.items.add( ChartData( "비타민3",  70))
        chartAdapter.items.add( ChartData( "비타민4",  70))
        chartAdapter.items.add( ChartData( "비타민5",  70))
        chartAdapter.items.add( ChartData("비타민6", 100))
        chartAdapter.items.add( ChartData("비타민7", 110))
        chartAdapter.items.add( ChartData("비타민8", 100))
        chartAdapter.items.add( ChartData("비타민9", 100))
        chartAdapter.items.add( ChartData("비타민10", 100))
        chartAdapter.items.add( ChartData("비타민11", 100))
        chartAdapter.items.add( ChartData("비타민12", 100))
        chartAdapter.items.add( ChartData("비타민13", 100))
        chartAdapter.items.add( ChartData("비타민14", 100))
        chartAdapter.items.add( ChartData("비타민15", 100))
        chartAdapter.items.add( ChartData("비타민16", 100))
        chartAdapter.items.add( ChartData("비타민17", 100))





        chart_product_detail_intake.adapter = chartAdapter
        chart_product_detail_intake.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    fun ingredientSetting(){
        val ingredientAdapter = RvIngredientAdapter(requireContext())

        var data2 = mutableListOf<String>()
        data2.add("프로바이오틱스 종류")
        data2.add("프로바이오틱스 종류")
        data2.add("프로바이오틱스 종류")



        ingredientAdapter.items.add(RvIngredientData("프로바이오 틱스", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        ingredientAdapter.items.add(RvIngredientData("기능성원료", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        ingredientAdapter.items.add(RvIngredientData("기능성원료", "30,000mg - 30,000mg","50,000mg","20,000Up", data2))
        rv_product_detail_intake_functional.adapter = ingredientAdapter
        rv_product_detail_intake_functional.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }


    fun bitaminSetting(){
        var charBitaminAdapter = ChartAdapter(requireContext())

        charBitaminAdapter.items.add( ChartData( "비타민",  200))
        charBitaminAdapter.items.add( ChartData( "비타민",  50))
        charBitaminAdapter.items.add( ChartData( "비타민",  600))
        charBitaminAdapter.items.add( ChartData( "비타민",  60))
        charBitaminAdapter.items.add( ChartData( "비타민",  20))
        charBitaminAdapter.items.add( ChartData("비타민", 600))
        charBitaminAdapter.items.add( ChartData("비타민", 600))
        charBitaminAdapter.items.add( ChartData("비타민", 600))

        chart_product_detail_bitamin.adapter = charBitaminAdapter
        chart_product_detail_bitamin.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    fun certificationSetting(){
        val certicationRvAdapter = CertificationRvAdapter()

        certicationRvAdapter.items.add("")
        certicationRvAdapter.items.add("")
        certicationRvAdapter.items.add("")

        rv_product_detail_intake_information_certification.adapter = certicationRvAdapter
    }

    fun productBuySetting(){

        val productBuyRvAdapter = ProductBuyRvAdapter()
        productBuyRvAdapter.items.add(ProductBuyData("쿠팡", "25000",""))
        productBuyRvAdapter.items.add(ProductBuyData("쿠팡", "25000",""))
        productBuyRvAdapter.items.add(ProductBuyData("쿠팡", "25000",""))

        rv_product_detail_buy.adapter = productBuyRvAdapter

    }



}