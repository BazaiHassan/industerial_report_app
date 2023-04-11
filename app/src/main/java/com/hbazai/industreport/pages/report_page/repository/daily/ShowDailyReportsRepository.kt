package com.hbazai.industreport.pages.report_page.repository.daily

import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import io.reactivex.Single

interface ShowDailyReportsRepository {
    fun showDailyReports() : Single<List<ResponseShowReportsItem>>
}