package com.caredirection.cadi.register.user.ingredient.material

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
import kotlinx.android.synthetic.main.fragment_register_material.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterMaterialFragment : Fragment(){
    private lateinit var registerMaterialAdapter: RegisterMaterialAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        materialListInit()
    }

    private fun materialListInit(){
        registerMaterialAdapter = RegisterMaterialAdapter(context)

        rv_register_material_list.adapter = registerMaterialAdapter

        rv_register_material_list.layoutManager = LinearLayoutManager(context)

        getIngredientListResponse()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_material,container,false)
    }

    private fun getIngredientListResponse(){
        val call: Call<RegisterIngredientData> = RequestURL.service.getIngredientList()
        call.enqueue(
            object : Callback<RegisterIngredientData> {
                override fun onFailure(call: Call<RegisterIngredientData>, t: Throwable) {
                    Log.d("성분 리스트 조회 실패", "메시지 : $t")
                }

                override fun onResponse(
                    call: Call<RegisterIngredientData>,
                    response: Response<RegisterIngredientData>
                ) {
                    if(response.isSuccessful){
                        val ingredientList=response.body()!!

                        val ingredientItem = mutableListOf<RvIngredientListItem>()
                        for(item in ingredientList.data){
                            ingredientItem.add(
                                RvIngredientListItem(
                                    item.ingredientIdx,
                                    item.name
                                )
                            )
                        }
                        registerMaterialAdapter.data=ingredientItem
                        registerMaterialAdapter.notifyDataSetChanged()
                    }
                }

            }
        )
    }
}