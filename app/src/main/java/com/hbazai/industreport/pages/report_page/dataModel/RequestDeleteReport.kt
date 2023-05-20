package com.hbazai.industreport.pages.report_page.dataModel

import com.google.gson.annotations.SerializedName

data class RequestDeleteReport(

	@field:SerializedName("report_token")
	val reportToken: String? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null,

	@field:SerializedName("report_type")
	val reportType: String? = null
)
