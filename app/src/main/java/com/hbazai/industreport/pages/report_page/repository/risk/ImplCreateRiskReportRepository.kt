package com.hbazai.industreport.pages.report_page.repository.risk

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.dataSource.risk.CreateRiskReportDataSource
import io.reactivex.Single

class ImplCreateRiskReportRepository(private val createRiskReportDataSource: CreateRiskReportDataSource):CreateRiskReportRepository {
    override fun createRiskReport(riskReport: RequestCreateRiskReport): Single<ResponseCreateReport> {
        return createRiskReportDataSource.createRiskReport(riskReport)
    }
}