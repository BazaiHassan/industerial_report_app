package com.hbazai.industreport.utils

object TokenContainer {

    var token: String? = null
        private set

    fun updateToken(token: String?) {
        this.token = token
    }

}