package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

interface DeleteReportRepository {
    fun deleteReport(requestDeleteReport: RequestDeleteReport):Single<ResponseCreateReport>
}