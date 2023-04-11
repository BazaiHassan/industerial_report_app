package com.hbazai.industreport.pages.report_page.dataSource.chemical

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import io.reactivex.Single

class RemoteCreateChemicalReportDataSource(private val apiService: ApiService) :
    CreateChemicalReportDataSource {
    override fun createChemicalReport(chemicalReport: RequestCreateChemicalReport): Single<ResponseCreateReport> {
        return apiService.createChemicalReport(chemicalReport)
    }
}