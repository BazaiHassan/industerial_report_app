package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import io.reactivex.Single

interface ShowReportsRepository {
    fun showReports() : Single<List<ResponseShowReportsItem>>
}