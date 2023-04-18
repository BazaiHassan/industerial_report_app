package com.hbazai.industreport.pages.notify_page.repository

import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import io.reactivex.Single

interface ShowNotificationRepository {
    fun showNotification():Single<List<ResponseShowNotificationItem>>
}