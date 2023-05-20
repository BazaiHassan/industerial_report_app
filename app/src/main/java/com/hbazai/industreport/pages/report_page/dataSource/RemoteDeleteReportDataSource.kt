package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

class RemoteDeleteReportDataSource(private val apiService: ApiService):DeleteReportDataSource {
    override fun deleteReport(requestDeleteReport: RequestDeleteReport): Single<ResponseCreateReport> {
        return apiService.deleteReport(requestDeleteReport)
    }
}