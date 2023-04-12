package com.hbazai.industreport.pages.report_page.repository.risk

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import io.reactivex.Single

interface CreateRiskReportRepository {
    fun createRiskReport(riskReport:RequestCreateRiskReport):Single<ResponseCreateReport>
}