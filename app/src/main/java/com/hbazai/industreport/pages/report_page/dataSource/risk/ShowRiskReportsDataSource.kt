package com.hbazai.industreport.pages.report_page.dataSource.risk

import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import io.reactivex.Single

interface ShowRiskReportsDataSource {
    fun showRiskReports():Single<List<ResponseShowRiskReportItem>>
}