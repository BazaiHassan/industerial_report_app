package com.hbazai.industreport.pages.report_page.dataModel.chemical

import com.google.gson.annotations.SerializedName

data class RequestCreateChemicalReport(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("material_name")
	val materialName: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
