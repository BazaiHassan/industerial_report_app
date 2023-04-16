package com.hbazai.industreport.pages.report_page.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseUploadImage(

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
