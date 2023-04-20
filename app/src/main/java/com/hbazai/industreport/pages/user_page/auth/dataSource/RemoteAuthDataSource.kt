package com.hbazai.industreport.pages.user_page.auth.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import io.reactivex.Single

class RemoteAuthDataSource(private val apiService: ApiService):AuthDataSource {
    override fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return apiService.checkPhoneNumber(requestPhoneNumber)
    }

    override fun getToken(requestPhoneNumber: RequestPhoneNumber): Single<ResponseSendOTP> {
        return apiService.getToken(requestPhoneNumber)
    }
}