package com.caredirection.cadi.network

import com.caredirection.cadi.networkdata.GraphIngredient
import com.caredirection.cadi.networkdata.GraphIngredientList
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface RequestInterface {

    @FormUrlEncoded
    @POST("/graph/vitamin-mineral")
    fun getGraphVitamin(
        @Header("token")token : String
    ) : Call<GraphIngredientList>
}