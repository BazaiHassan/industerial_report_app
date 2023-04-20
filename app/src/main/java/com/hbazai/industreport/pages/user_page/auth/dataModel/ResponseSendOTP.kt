package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseSendOTP(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
