package com.hbazai.industreport.pages.notify_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import io.reactivex.Single

class RemoteShowNotificationDataSource(private val apiService: ApiService):ShowNotificationDataSource {
    override fun showNotification(): Single<List<ResponseShowNotificationItem>> {
        return apiService.showNotifications()
    }
}