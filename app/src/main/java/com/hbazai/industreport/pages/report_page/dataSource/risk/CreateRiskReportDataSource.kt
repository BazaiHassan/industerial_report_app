package com.hbazai.industreport.pages.report_page.dataSource.risk

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import io.reactivex.Single

interface CreateRiskReportDataSource {
    fun createRiskReport(riskReport:RequestCreateRiskReport):Single<ResponseCreateReport>
}