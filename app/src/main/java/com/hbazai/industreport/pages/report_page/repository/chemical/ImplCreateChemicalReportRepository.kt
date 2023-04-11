package com.hbazai.industreport.pages.report_page.repository.chemical

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.dataSource.chemical.CreateChemicalReportDataSource
import io.reactivex.Single

class ImplCreateChemicalReportRepository(private val createChemicalReportDataSource: CreateChemicalReportDataSource) :
    CreateChemicalReportRepository {
    override fun createChemicalReport(chemicalReport: RequestCreateChemicalReport): Single<ResponseCreateReport> {
        return createChemicalReportDataSource.createChemicalReport(chemicalReport)
    }
}