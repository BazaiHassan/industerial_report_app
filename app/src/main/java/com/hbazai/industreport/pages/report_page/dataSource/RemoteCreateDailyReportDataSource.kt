package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

class RemoteCreateDailyReportDataSource(private val apiService: ApiService):CreateDailyReportDataSource {
    override fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport> {
        return apiService.createDailyReport(dailyReport)
    }
}