package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataSource.daily.CreateDailyReportDataSource
import io.reactivex.Single

class ImplCreateDailyReportRepository(
    private val createDailyReportDataSource: CreateDailyReportDataSource
):CreateDailyReportRepository {
    override fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport> {
        return createDailyReportDataSource.createDailyReport(dailyReport)
    }
}