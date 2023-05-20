package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataSource.DeleteReportDataSource
import io.reactivex.Single

class ImplDeleteReportRepository(private val deleteReportDataSource: DeleteReportDataSource):DeleteReportRepository {
    override fun deleteReport(requestDeleteReport: RequestDeleteReport): Single<ResponseCreateReport> {
        return deleteReportDataSource.deleteReport(requestDeleteReport)
    }
}