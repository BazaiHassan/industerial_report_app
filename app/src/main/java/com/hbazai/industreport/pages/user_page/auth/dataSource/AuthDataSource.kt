package com.hbazai.industreport.pages.user_page.auth.dataSource

import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import io.reactivex.Single

interface AuthDataSource {
    fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>
    fun getToken(requestPhoneNumber: RequestPhoneNumber):Single<ResponseSendOTP>

}