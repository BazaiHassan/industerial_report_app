package com.hbazai.industreport.pages.report_page.dataSource.risk

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import io.reactivex.Single

class RemoteCreateRiskReportDataSource(private val apiService: ApiService):CreateRiskReportDataSource {
    override fun createRiskReport(riskReport: RequestCreateRiskReport): Single<ResponseCreateReport> {
        return apiService.createRiskReport(riskReport)
    }
}