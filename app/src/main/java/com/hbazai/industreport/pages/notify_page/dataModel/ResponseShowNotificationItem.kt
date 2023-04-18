package com.hbazai.industreport.pages.notify_page.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseShowNotificationItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("importance_lvl")
	val importanceLvl: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
