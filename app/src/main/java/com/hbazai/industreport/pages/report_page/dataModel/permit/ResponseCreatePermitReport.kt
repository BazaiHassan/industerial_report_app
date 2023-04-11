package com.hbazai.industreport.pages.report_page.dataModel.permit

import com.google.gson.annotations.SerializedName

data class ResponseCreatePermitReport(

	@field:SerializedName("ResponseCreatePermitReport")
	val responseCreatePermitReport: List<ResponseCreatePermitReportItem?>? = null
)

data class ResponseCreatePermitReportItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("instrument_tag")
	val instrumentTag: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
