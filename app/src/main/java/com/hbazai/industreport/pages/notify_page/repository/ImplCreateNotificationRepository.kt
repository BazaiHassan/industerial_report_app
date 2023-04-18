package com.hbazai.industreport.pages.notify_page.repository

import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.notify_page.dataSource.CreateNotificationDataSource
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

class ImplCreateNotificationRepository(private val createNotificationDataSource: CreateNotificationDataSource):CreateNotificationRepository {
    override fun createNotification(requestCreateNotification: RequestCreateNotification): Single<ResponseCreateReport> {
        return createNotificationDataSource.createNotification(requestCreateNotification)
    }
}