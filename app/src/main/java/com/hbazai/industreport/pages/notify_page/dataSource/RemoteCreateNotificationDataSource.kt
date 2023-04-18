package com.hbazai.industreport.pages.notify_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

class RemoteCreateNotificationDataSource(private val apiService: ApiService):CreateNotificationDataSource {
    override fun createNotification(requestCreateNotification: RequestCreateNotification): Single<ResponseCreateReport> {
        return apiService.createNotification(requestCreateNotification)
    }
}