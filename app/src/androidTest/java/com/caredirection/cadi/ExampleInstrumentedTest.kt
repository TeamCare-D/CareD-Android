package com.caredirection.cadi

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphFunctionList
import com.caredirection.cadi.networkdata.GraphBitaminList

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.caredirection.cadi", appContext.packageName)

        val call : Call<GraphBitaminList> = RequestURL.service.getGraphVitamin("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")

        call.enqueue(
            object: Callback<GraphBitaminList>{

                override fun onFailure(call: Call<GraphBitaminList>, t: Throwable) {
                    Log.d("테스트 코드", t.toString())
                }

                override fun onResponse(
                    call: Call<GraphBitaminList>,
                    response: Response<GraphBitaminList>
                ) {
                    Log.d("테스트 코드", response.body().toString())
                }
            }
        )

        val call2: Call<GraphFunctionList> = RequestURL.service.getGraphFunction("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call2.enqueue(
            object : Callback<GraphFunctionList>{
                override fun onFailure(call: Call<GraphFunctionList>, t: Throwable?) {
                    Log.d("테스트 코드", t.toString())
                }

                override fun onResponse(call: Call<GraphFunctionList>, response: Response<GraphFunctionList>) {
                    Log.d("테스트 코드", response.body().toString())
                }
            }
        )
    }
}