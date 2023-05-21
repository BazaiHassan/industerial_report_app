package com.hbazai.industreport.pages.search_page.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("material_name")
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

	@field:SerializedName("report_token")
	val reportToken: String? = null,

	@field:SerializedName("report_type")
	val reportType: String? = null
){
	companion object {
		private var counter = 0

		// Generate a new 'id' based on the current value of the counter.
		fun generateId(): Int {
			return counter++
		}
	}
}
