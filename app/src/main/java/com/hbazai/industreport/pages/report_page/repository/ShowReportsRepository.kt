package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import io.reactivex.Single

interface ShowReportsRepository {
    fun showReports() : Single<List<ResponseShowReportsItem>>
}