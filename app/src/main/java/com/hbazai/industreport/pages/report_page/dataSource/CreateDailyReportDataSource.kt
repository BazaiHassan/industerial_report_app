package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.pages.report_page.dataModel.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

interface CreateDailyReportDataSource {
    fun createDailyReport(dailyReport: RequestCreateDailyReport):Single<ResponseCreateReport>
}