package com.caredirection.cadi.product.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.IngredientListData
import com.caredirection.cadi.product.search.adapter.componentRvAdapter
import kotlinx.android.synthetic.main.fragment_product_search2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentName: Fragment(R.layout.fragment_product_search2) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rvNameSetting()
    }

    fun rvNameSetting(){
        val rvNameAdapter = componentRvAdapter(requireContext())

        val call: Call<IngredientListData> = RequestURL.service.getIngredentList()
        call.enqueue(
            object : Callback<IngredientListData>{
                override fun onFailure(call: Call<IngredientListData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<IngredientListData>,
                    response: Response<IngredientListData>
                ) {
                    val data = response.body()
                    rvNameAdapter.items.addAll(data!!.data)
                    rv_product_search_name.adapter = rvNameAdapter
                }
            }
        )
    }

}