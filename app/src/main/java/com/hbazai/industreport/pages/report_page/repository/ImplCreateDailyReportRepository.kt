package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataSource.CreateDailyReportDataSource
import io.reactivex.Single

class ImplCreateDailyReportRepository(
    private val createDailyReportDataSource: CreateDailyReportDataSource):CreateDailyReportRepository {
    override fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport> {
        return createDailyReportDataSource.createDailyReport(dailyReport)
    }
}