package com.hbazai.industreport.utils

import android.content.Context

class AppPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

    fun returnUserToken():String{
        val token = sharedPreferences.getString("token", "")
        return token.toString()
    }
}