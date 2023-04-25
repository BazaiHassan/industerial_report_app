package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseUserInfo
import com.hbazai.industreport.utils.SendToken
import io.reactivex.Single

interface ShowUserInfoRepository {
    fun showUserInf(sendToken: SendToken):Single<ResponseUserInfo>
}