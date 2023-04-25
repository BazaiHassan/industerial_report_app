package com.hbazai.industreport.pages.user_page.auth.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseUserInfo
import com.hbazai.industreport.utils.SendToken
import io.reactivex.Single

class RemoteShowUserInfoDataSource(private val apiService: ApiService):ShowUserInfoDataSource {
    override fun showUserInfo(sendToken: SendToken): Single<ResponseUserInfo> {
        return apiService.showUserProfile(sendToken)
    }
}