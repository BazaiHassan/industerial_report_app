package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseUserInfo(

	@field:SerializedName("access_level")
	val accessLevel: String? = null,

	@field:SerializedName("signature")
	val signature: String? = null,

	@field:SerializedName("shift")
	val shift: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("personal_number")
	val personalNumber: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("date_joined")
	val dateJoined: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
