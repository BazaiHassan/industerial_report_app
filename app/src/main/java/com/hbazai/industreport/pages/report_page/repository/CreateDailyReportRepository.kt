package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

interface CreateDailyReportRepository {
    fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport>
}