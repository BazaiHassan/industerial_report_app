package com.hbazai.industreport.pages.report_page.dataSource.daily

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import io.reactivex.Single

class RemoteShowDailyReportDataSource(private val apiService: ApiService):
    ShowDailyReportsDataSource {
    override fun showDailyReports(): Single<List<ResponseShowReportsItem>> {
        return apiService.showDailyReports()
    }

}