package com.hbazai.industreport.pages.report_page.repository.chemical

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import io.reactivex.Single

interface CreateChemicalReportRepository {
    fun createChemicalReport(chemicalReport: RequestCreateChemicalReport):Single<ResponseCreateReport>
}