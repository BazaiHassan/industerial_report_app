package com.hbazai.industreport.pages.report_page.dataSource.chemical

import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import io.reactivex.Single

interface ShowChemicalReportsDataSource {
    fun showChemicalReports():Single<List<ResponseShowChemicalReportItem>>
}