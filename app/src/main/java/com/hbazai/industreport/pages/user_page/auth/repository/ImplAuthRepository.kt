package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import com.hbazai.industreport.pages.user_page.auth.dataSource.AuthDataSource
import io.reactivex.Single

class ImplAuthRepository(private val authDataSource: AuthDataSource) : AuthRepository {
    override fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return authDataSource.checkPhoneNumber(requestPhoneNumber)
    }

    override fun getToken(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return authDataSource.getToken(requestPhoneNumber)
    }

    override fun createGroup(requestCreateGroup: RequestCreateGroup): Single<ResponseCreateGroup> {
        return authDataSource.createGroup(requestCreateGroup)
    }

    override fun checkInviteCode(requestCheckInviteCode: RequestCheckInviteCode): Single<ResponseCheckInviteCode> {
        return authDataSource.checkInviteCode(requestCheckInviteCode)
    }
}