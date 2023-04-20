package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class RequestPhoneNumber(

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null
)
