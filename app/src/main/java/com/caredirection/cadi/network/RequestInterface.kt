package com.caredirection.cadi.network

import com.caredirection.cadi.networkdata.GraphFunctionList
import com.caredirection.cadi.networkdata.GraphBitaminList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RequestInterface {

    @GET("/graph/vitaminMineral")
    fun getGraphVitamin(
        @Header("token")token : String
    ) : Call<GraphBitaminList>

    @GET("/graph/functional")
    fun getGraphFunction(
        @Header("token")token : String
    ): Call<GraphFunctionList>
}