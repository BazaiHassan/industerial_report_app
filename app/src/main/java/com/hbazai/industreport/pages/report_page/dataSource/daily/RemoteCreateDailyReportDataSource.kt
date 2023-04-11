package com.hbazai.industreport.pages.report_page.dataSource.daily

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

class RemoteCreateDailyReportDataSource(private val apiService: ApiService):
    CreateDailyReportDataSource {
    override fun createDailyReport(dailyReport: RequestCreateDailyReport): Single<ResponseCreateReport> {
        return apiService.createDailyReport(dailyReport)
    }
}