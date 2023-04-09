package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

class RemoteShowReportDataSource(private val apiService: ApiService):ShowReportDataSource {
    override fun showReports(): Single<List<ResponseShowReportsItem>> {
        return apiService.showDailyReports()
    }
}