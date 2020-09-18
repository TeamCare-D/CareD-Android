package com.caredirection.cadi.data

import android.content.Context
import android.content.SharedPreferences

object TokenController {

    private const val ACCESS_TOKEN="user_token"

    fun setAccessToken(ctx: Context, token:String){
        val  preference: SharedPreferences =ctx.getSharedPreferences(ACCESS_TOKEN,Context.MODE_PRIVATE)
        val  editor:SharedPreferences.Editor=preference.edit()
        editor.putString("access_token",token)
        editor.apply()
    }

    fun getAccessToken(ctx:Context): String? {
        val preference: SharedPreferences = ctx.getSharedPreferences(ACCESS_TOKEN, Context.MODE_PRIVATE)
        return preference.getString("access_token", "")
    }
}