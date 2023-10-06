package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class ResponseCreateGroup(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("group_invite_code")
	val groupInviteCode: String? = null,
)
