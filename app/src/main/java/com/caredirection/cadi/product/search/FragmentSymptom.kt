package com.caredirection.cadi.product.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.EfficacyListData
import com.caredirection.cadi.product.search.adapter.SymptomRvAdapter
import com.caredirection.cadi.product.search.adapter.ingredientIdxData
import com.caredirection.cadi.product.search.adapter.item
import kotlinx.android.synthetic.main.fragment_product_search1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSymptom: Fragment(R.layout.fragment_product_search1) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RvAdapterSetting()


    }
    fun RvAdapterSetting(){
        val RvAdapter = SymptomRvAdapter(requireContext())

        val call: Call<EfficacyListData> = RequestURL.service.getEfficacy()
        call.enqueue(
            object : Callback<EfficacyListData>{
                override fun onFailure(call: Call<EfficacyListData>, t: Throwable) {
                    Log.d("testDis", t.toString())
                }

                override fun onResponse(
                    call: Call<EfficacyListData>,
                    response: Response<EfficacyListData>
                ) {
                    val data = response.body()!!.data
                    val efficacyList = mutableListOf<item>()


                    for (i in data){
                        val testString = mutableListOf<ingredientIdxData>()
                        for(j in i){
                            testString.add(ingredientIdxData(j.ingredient_name, j.ingredient_idx))
                        }
                        efficacyList.add(item(i[0].efficacy_name, testString))
                    }

                    Log.d("testDis", efficacyList.toString())
                    RvAdapter.items.addAll(efficacyList)
                    rv_product_search_symptom.adapter = RvAdapter


                }
            }
        )


    }
}
