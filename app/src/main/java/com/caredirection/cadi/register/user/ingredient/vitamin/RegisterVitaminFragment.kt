package com.caredirection.cadi.register.user.ingredient.vitamin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.caredirection.cadi.R
import com.caredirection.cadi.data.network.RegisterIngredientData
import com.caredirection.cadi.data.register.RvIngredientListItem
import com.caredirection.cadi.network.RequestURL
import kotlinx.android.synthetic.main.fragment_register_vitamin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVitaminFragment : Fragment(){
    private lateinit var registerVitaminAdapter: RegisterVitaminAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vitaminListInit()
    }

    private fun vitaminListInit(){
        registerVitaminAdapter =
            RegisterVitaminAdapter(
                context
            )

        rv_register_vitamin_list.adapter = registerVitaminAdapter

        rv_register_vitamin_list.layoutManager = LinearLayoutManager(context)

        getIngredientListResponse()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_vitamin, container, false)
    }

    private fun getIngredientListResponse(){
        val call: Call<RegisterIngredientData> = RequestURL.service.getIngredientList()
        call.enqueue(
            object : Callback<RegisterIngredientData> {
                override fun onFailure(call: Call<RegisterIngredientData>, t: Throwable) {
                    Log.d("성분(비타민&미네랄) 리스트 조회 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<RegisterIngredientData>,
                    response: Response<RegisterIngredientData>
                ) {
                    if(response.isSuccessful){
                        val ingredientList=response.body()!!

                        val ingredientItem = mutableListOf<RvIngredientListItem>()
                        for(item in ingredientList.data.vitaminMineralIngredients){
                            ingredientItem.add(
                                RvIngredientListItem(
                                    item.ingredientIdx,
                                    item.name
                                )
                            )
                        }
                        registerVitaminAdapter.data=ingredientItem
                        registerVitaminAdapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }
}