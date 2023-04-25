package com.hbazai.industreport.pages.user_page.auth.repository

import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseUserInfo
import com.hbazai.industreport.pages.user_page.auth.dataSource.ShowUserInfoDataSource
import com.hbazai.industreport.utils.SendToken
import io.reactivex.Single

class ImplShowUserInfoRepository(private val showUserInfoDataSource: ShowUserInfoDataSource):ShowUserInfoRepository {
    override fun showUserInf(sendToken: SendToken): Single<ResponseUserInfo> {
        return showUserInfoDataSource.showUserInfo(sendToken)
    }
}