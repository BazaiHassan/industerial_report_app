package com.hbazai.industreport.pages.report_page.dataModel.comments

import com.google.gson.annotations.SerializedName

data class CreateCommentModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("report_token")
	val reportToken: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null
)
