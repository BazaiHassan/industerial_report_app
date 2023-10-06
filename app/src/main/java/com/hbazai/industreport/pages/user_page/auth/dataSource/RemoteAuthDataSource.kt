package com.hbazai.industreport.pages.user_page.auth.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import io.reactivex.Single

class RemoteAuthDataSource(private val apiService: ApiService):AuthDataSource {
    override fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return apiService.checkPhoneNumber(requestPhoneNumber)
    }

    override fun getToken(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return apiService.getToken(requestPhoneNumber)
    }

    override fun createGroup(requestCreateGroup: RequestCreateGroup): Single<ResponseCreateGroup> {
        return apiService.createGroup(requestCreateGroup)
    }

    override fun checkInviteCode(requestCheckInviteCode: RequestCheckInviteCode): Single<ResponseCheckInviteCode> {
        return apiService.checkInviteCode(requestCheckInviteCode)
    }
}