package com.hbazai.industreport.pages.report_page.repository.chemical

import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import io.reactivex.Single

interface ShowChemicalReportsRepository {
    fun showChemicalReports():Single<List<ResponseShowChemicalReportItem>>
}