package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseCheckInviteCode(

	@field:SerializedName("group_info")
	val groupInfo: GroupInfo? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class GroupInfo(

	@field:SerializedName("group_name")
	val groupName: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
