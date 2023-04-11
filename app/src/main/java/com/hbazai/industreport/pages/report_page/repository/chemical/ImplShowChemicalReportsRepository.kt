package com.hbazai.industreport.pages.report_page.repository.chemical

import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import com.hbazai.industreport.pages.report_page.dataSource.chemical.ShowChemicalReportsDataSource
import io.reactivex.Single


class ImplShowChemicalReportsRepository(private val showChemicalReportsDataSource: ShowChemicalReportsDataSource):ShowChemicalReportsRepository {
    override fun showChemicalReports(): Single<List<ResponseShowChemicalReportItem>> {
        return showChemicalReportsDataSource.showChemicalReports()
    }
}