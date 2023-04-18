package com.hbazai.industreport.pages.notify_page.repository

import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import com.hbazai.industreport.pages.notify_page.dataSource.ShowNotificationDataSource
import io.reactivex.Single

class ImplShowNotificationRepository(private val showNotificationDataSource: ShowNotificationDataSource):ShowNotificationRepository {
    override fun showNotification(): Single<List<ResponseShowNotificationItem>> {
        return showNotificationDataSource.showNotification()
    }
}