package com.hbazai.industreport.pages.report_page.dataSource.chemical

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import io.reactivex.Single

class RemoteShowChemicalReportsDataSource(private val apiService: ApiService):ShowChemicalReportsDataSource {
    override fun showChemicalReports(): Single<List<ResponseShowChemicalReportItem>> {
        return apiService.showChemicalReports()
    }
}