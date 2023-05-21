package com.hbazai.industreport.pages.user_page.documents.data

import com.google.gson.annotations.SerializedName


data class ResponseShowDocs(

	@field:SerializedName("doc_unit")
	val docUnit: String? = null,

	@field:SerializedName("doc_title")
	val docTitle: String? = null,

	@field:SerializedName("doc_link")
	val docLink: String? = null,

	@field:SerializedName("doc_describe")
	val docDescribe: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("doc_token")
	val docToken: String? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null
)
