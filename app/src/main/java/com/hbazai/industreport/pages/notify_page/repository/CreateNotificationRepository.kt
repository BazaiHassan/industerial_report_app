package com.hbazai.industreport.pages.notify_page.repository

import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

interface CreateNotificationRepository {
    fun createNotification(requestCreateNotification: RequestCreateNotification):Single<ResponseCreateReport>
}