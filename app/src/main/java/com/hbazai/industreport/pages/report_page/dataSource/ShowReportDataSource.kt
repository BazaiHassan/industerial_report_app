package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

interface ShowReportDataSource {
    fun showReports():Single<List<ResponseShowReportsItem>>
}