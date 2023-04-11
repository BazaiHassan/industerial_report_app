package com.hbazai.industreport.pages.report_page.dataSource.daily

import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import io.reactivex.Single

interface ShowDailyReportsDataSource {
    fun showDailyReports() : Single<List<ResponseShowReportsItem>>
}