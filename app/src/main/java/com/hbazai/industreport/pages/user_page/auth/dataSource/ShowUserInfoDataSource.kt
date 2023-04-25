package com.hbazai.industreport.pages.user_page.auth.dataSource

import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseUserInfo
import com.hbazai.industreport.utils.SendToken
import io.reactivex.Single

interface ShowUserInfoDataSource {
    fun showUserInfo(sendToken: SendToken):Single<ResponseUserInfo>
}