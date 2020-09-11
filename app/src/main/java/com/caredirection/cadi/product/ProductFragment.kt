package com.caredirection.cadi.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.caredirection.cadi.R
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.MagazineHome
import com.caredirection.cadi.networkdata.SimilarIngredient
import com.caredirection.cadi.product.list.adapter.ProductMagazineRvAdapter
import com.caredirection.cadi.product.search.ProductSearch
import kotlinx.android.synthetic.main.fragment_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductFragment : Fragment(R.layout.fragment_product) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        magazineSetting()

        intentSearchSetting()

        gifSetting()

        similarSetting()

    }

    fun magazineSetting() {
        val productMagazineRvAdapter = ProductMagazineRvAdapter()


        val call: Call<MagazineHome> =
            RequestURL.service.getMagazineHome("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<MagazineHome> {
                override fun onFailure(call: Call<MagazineHome>, t: Throwable) {
                    Log.d("getMagazineHome Fail", t.toString())
                }

                override fun onResponse(
                    call: Call<MagazineHome>,
                    response: Response<MagazineHome>
                ) {
                    val data = response.body()!!.data
                    productMagazineRvAdapter.items.addAll(data.magazine)
                    productMagazineRvAdapter.marketingItems.addAll(data.directions)
                    rv_product_magazine_interested.adapter = productMagazineRvAdapter
                }
            }
        )

    }

    fun intentSearchSetting(){
        btn_product_search.setOnClickListener {
            val intent = Intent(requireContext(), ProductSearch::class.java)
            startActivity(intent)
        }
    }

    fun gifSetting(){
        Glide.with(this).load(R.raw.product_gif).into(img_product_main_top)
    }

    fun similarSetting(){
        val call: Call<SimilarIngredient> = RequestURL.service.getSimilarIngredient("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<SimilarIngredient>
            {
                override fun onFailure(call: Call<SimilarIngredient>, t: Throwable) {
                    Log.d("ProductFragment Similar Fail", t.toString())
                }

                override fun onResponse(
                    call: Call<SimilarIngredient>,
                    response: Response<SimilarIngredient>
                ) {
                    val data = response.body()!!.data
                    txt_product_suggestion_item1.text = data[0]
                    txt_product_suggestion_item2.text = data[1]
                    txt_product_suggestion_item3.text = data[2]
                }
            }
        )
    }


}