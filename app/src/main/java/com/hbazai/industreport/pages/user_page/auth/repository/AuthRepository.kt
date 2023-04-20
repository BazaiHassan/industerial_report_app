package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import io.reactivex.Single

interface AuthRepository {
    fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>
    fun getToken(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>
}