package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class RequestCreateGroup(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("group_name")
	val groupName: String? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("group_description")
	val groupDescription: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

)
