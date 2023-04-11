package com.hbazai.industreport.pages.report_page.dataSource.daily

import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.Single

interface CreateDailyReportDataSource {
    fun createDailyReport(dailyReport: RequestCreateDailyReport):Single<ResponseCreateReport>
}