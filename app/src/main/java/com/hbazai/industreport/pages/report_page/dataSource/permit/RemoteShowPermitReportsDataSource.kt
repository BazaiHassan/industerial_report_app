package com.hbazai.industreport.pages.report_page.dataSource.permit

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import io.reactivex.Single

class RemoteShowPermitReportsDataSource(private val apiService: ApiService):ShowPermitReportDataSource {
    override fun showPermitReports(): Single<List<ResponseShowPermitReportItem>> {
        return apiService.showPermitReports()
    }
}