package com.hbazai.industreport.pages.report_page.repository.risk

import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import io.reactivex.Single

interface ShowRiskReportsRepository {
    fun showRiskReports():Single<List<ResponseShowRiskReportItem>>
}