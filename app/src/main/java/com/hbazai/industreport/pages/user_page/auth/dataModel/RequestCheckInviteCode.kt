package com.hbazai.industreport.pages.user_page.auth.dataModel

import com.google.gson.annotations.SerializedName

data class RequestCheckInviteCode(

	@field:SerializedName("group_invite_code")
	val groupInviteCode: String? = null
)
