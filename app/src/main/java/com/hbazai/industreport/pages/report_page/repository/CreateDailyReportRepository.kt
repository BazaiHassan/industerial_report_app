package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

interface CreateDailyReportRepository {
    fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport>
}