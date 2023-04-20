package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import com.hbazai.industreport.pages.user_page.auth.dataSource.AuthDataSource
import io.reactivex.Single

class ImplAuthRepository(private val checkPhoneDataSource: AuthDataSource):AuthRepository {
    override fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return checkPhoneDataSource.checkPhoneNumber(requestPhoneNumber)
    }

    override fun getToken(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return checkPhoneDataSource.getToken(requestPhoneNumber)
    }
}