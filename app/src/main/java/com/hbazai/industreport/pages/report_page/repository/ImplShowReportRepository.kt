package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataSource.ShowReportDataSource
import io.reactivex.Single

class ImplShowReportRepository(private val showReportDataSource: ShowReportDataSource):ShowReportsRepository {
    override fun showReports(): Single<List<ResponseShowReportsItem>> {
        return showReportDataSource.showReports()
    }

}