package com.hbazai.industreport.pages.report_page.dataSource.permit

import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import io.reactivex.Single

interface ShowPermitReportDataSource {
    fun showPermitReports():Single<List<ResponseShowPermitReportItem>>
}