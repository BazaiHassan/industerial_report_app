package com.hbazai.industreport.pages.report_page.repository.permit

import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import com.hbazai.industreport.pages.report_page.dataSource.permit.ShowPermitReportDataSource
import io.reactivex.Single

class ImplShowPermitReportsRepository(private val showPermitReportDataSource: ShowPermitReportDataSource):ShowPermitReportRepository {
    override fun showPermitReports(): Single<List<ResponseShowPermitReportItem>> {
        return showPermitReportDataSource.showPermitReports()
    }
}