package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import io.reactivex.Single

interface AuthRepository {
    fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>
    fun getToken(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>
    fun createGroup(requestCreateGroup: RequestCreateGroup):Single<ResponseCreateGroup>
    fun checkInviteCode(requestCheckInviteCode: RequestCheckInviteCode):Single<ResponseCheckInviteCode>
}