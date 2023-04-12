package com.hbazai.industreport.pages.report_page.repository.risk

import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import com.hbazai.industreport.pages.report_page.dataSource.risk.ShowRiskReportsDataSource
import io.reactivex.Single

class ImplShowRiskReportsRepository(private val showRiskReportsDataSource: ShowRiskReportsDataSource):ShowRiskReportsRepository {
    override fun showRiskReports(): Single<List<ResponseShowRiskReportItem>> {
        return showRiskReportsDataSource.showRiskReports()
    }
}