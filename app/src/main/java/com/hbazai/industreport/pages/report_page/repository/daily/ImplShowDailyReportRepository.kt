package com.hbazai.industreport.pages.report_page.repository.daily

import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataSource.daily.ShowDailyReportsDataSource
import io.reactivex.Single

class ImplShowDailyReportRepository(private val showDailyReportDataSource: ShowDailyReportsDataSource):
    ShowDailyReportsRepository {
    override fun showDailyReports(): Single<List<ResponseShowReportsItem>> {
        return showDailyReportDataSource.showDailyReports()
    }

}