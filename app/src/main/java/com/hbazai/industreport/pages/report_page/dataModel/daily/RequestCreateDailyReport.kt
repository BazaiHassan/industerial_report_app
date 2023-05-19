package com.hbazai.industreport.pages.report_page.dataModel.daily

import com.google.gson.annotations.SerializedName



data class RequestCreateDailyReport(

    @field:SerializedName("report_type")
    val reportType: String? = null,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("unit")
    val unit: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("instrument_tag")
    val instrumentTag: String? = null,

    @field:SerializedName("user_token")
    val userToken: String? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null
)
