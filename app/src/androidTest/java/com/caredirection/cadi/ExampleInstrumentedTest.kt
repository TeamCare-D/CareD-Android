package com.caredirection.cadi

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caredirection.cadi.network.RequestURL
import com.caredirection.cadi.networkdata.GraphInfo
import com.caredirection.cadi.networkdata.GraphIngredientList

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.runner.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

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

        val call : Call<GraphIngredientList> = RequestURL.service.getGraphVitamin("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")

        call.enqueue(
            object: Callback<GraphIngredientList>{

                override fun onFailure(call: Call<GraphIngredientList>, t: Throwable) {
                    Log.d("테스트 코드", t.toString())
                }

                override fun onResponse(
                    call: Call<GraphIngredientList>,
                    response: Response<GraphIngredientList>
                ) {
                    Log.d("테스트 코드", response.body().toString())
                }
            }
        )

        val call2: Call<GraphInfo> = RequestURL.service.getGraphInfo("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDYXJlRCIsInVzZXJfaWR4Ijo0NH0.6CVrPAgdAkapMrWtK40oXP_3-vjCAaSxR3gcSrVgVhE")
        call2.enqueue(
            object : Callback<GraphInfo>{
                override fun onFailure(call: Call<GraphInfo>, t: Throwable?) {
                    Log.d("테스트 코드", t.toString())
                }

                override fun onResponse(call: Call<GraphInfo>, response: Response<GraphInfo>) {
                    Log.d("테스트 코드", response.body().toString())
                }
            }
        )
    }
}