package com.hbazai.industreport.pages.report_page.repository.permit

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import com.hbazai.industreport.pages.report_page.dataSource.permit.CreatePermitReportDataSource
import io.reactivex.Single

class ImplCreatePermitReportRepository(private val createPermitReportDataSource: CreatePermitReportDataSource):CreatePermitReportRepository {
    override fun createPermitReport(permitReport: RequestCreatePermitReport): Single<ResponseCreateReport> {
        return createPermitReportDataSource.createPermitReport(permitReport)
    }
}