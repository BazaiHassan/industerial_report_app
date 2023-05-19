package com.hbazai.industreport.utils

import com.google.gson.annotations.SerializedName

data class SendReportToken(

	@field:SerializedName("report_token")
	val reportToken: String? = null
)
