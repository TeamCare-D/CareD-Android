package com.caredirection.cadi.contents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caredirection.cadi.R
import com.caredirection.cadi.adapter.MagazineIngredientRvAdapter
import com.caredirection.cadi.adapter.MagazineTagRvAdapter
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.DictionaryData
import com.caredirection.cadi.networkdata.DictionaryListData
import com.caredirection.cadi.networkdata.MagazineListData
import kotlinx.android.synthetic.main.activity_contents.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ContentActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents)

        val ingredient_idx: Int = intent.getIntExtra("ingredient_idx", 1)
        getDataSetting(ingredient_idx)

    }

    fun getDataSetting(ingredient_idx: Int){
        val call: Call<DictionaryData> = RequestURL.service.getDictionary(ingredient_idx = ingredient_idx,token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call.enqueue(
            object : Callback<DictionaryData>{
                override fun onFailure(call: Call<DictionaryData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<DictionaryData>,
                    response: Response<DictionaryData>
                ) {
                    val data = response.body()!!.data
                    topDataSetting(data.dictionary)
                    magazineSetting(data.magazine)
                }
            }
        )
    }

    fun topDataSetting(data: DictionaryListData){
        txt_contents_title.text = data.ingredientName
        txt_contents_sub_title.text = data.ingredientSubTitle


        txt_contents_ingredient_title.text = data.ingredientName
        txt_contents_ingredient_low_num.text = data.recommendedAmount
        txt_contents_ingredient_high_num.text = data.upperAmount
        txt_contents_ingredient_person.text = data.person
        txt_contents_content.text = data.dictionaryContents



        val magazineTagAdapter = MagazineTagRvAdapter()
        magazineTagAdapter.items.addAll(data.efficacy)
        rv_contents_sub_title.adapter = magazineTagAdapter

    }

    fun magazineSetting(data: MutableList<MagazineListData>){
        val magazineRvAdapter = MagazineIngredientRvAdapter()
        magazineRvAdapter.items.addAll(data)
        rv_contents_magazine.adapter = magazineRvAdapter
    }


}