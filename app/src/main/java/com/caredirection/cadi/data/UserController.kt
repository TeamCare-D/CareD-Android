package com.caredirection.cadi.data

import android.content.Context

object UserController{
    private const val NAME_KEY = "name"

    fun getName(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(NAME_KEY,Context.MODE_PRIVATE)
        return sharedPreferences.getString(NAME_KEY,"")?:""
    }

    fun setName(context: Context, name: String){
        val sharedPreferences = context.getSharedPreferences(NAME_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(NAME_KEY,name)
            .apply()
    }

    fun clearName(context: Context){
        val sharedPreferences = context.getSharedPreferences(NAME_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }
}