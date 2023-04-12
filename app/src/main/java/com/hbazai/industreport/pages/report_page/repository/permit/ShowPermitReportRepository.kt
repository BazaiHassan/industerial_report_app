package com.hbazai.industreport.pages.report_page.repository.permit

import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import io.reactivex.Single

interface ShowPermitReportRepository {
    fun showPermitReports():Single<List<ResponseShowPermitReportItem>>
}