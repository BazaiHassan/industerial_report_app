package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

interface DeleteReportDataSource {
    fun deleteReport(requestDeleteReport: RequestDeleteReport):Single<ResponseCreateReport>
}