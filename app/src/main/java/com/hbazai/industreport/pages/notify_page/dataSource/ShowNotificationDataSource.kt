package com.hbazai.industreport.pages.notify_page.dataSource

import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import io.reactivex.Single

interface ShowNotificationDataSource {
    fun showNotification():Single<List<ResponseShowNotificationItem>>
}