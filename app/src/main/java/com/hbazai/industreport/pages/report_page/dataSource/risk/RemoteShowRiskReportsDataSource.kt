package com.hbazai.industreport.pages.report_page.dataSource.risk

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import io.reactivex.Single

class RemoteShowRiskReportsDataSource(private val apiService: ApiService):ShowRiskReportsDataSource {
    override fun showRiskReports(): Single<List<ResponseShowRiskReportItem>> {
        return apiService.showRiskReports()
    }
}