package com.hbazai.industreport.pages.report_page.dataSource.permit

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import io.reactivex.Single

class RemoteCreatePermitReportDataSource(private val apiService: ApiService):CreatePermitReportDataSource {
    override fun createPermitReport(permitReport: RequestCreatePermitReport): Single<ResponseCreateReport> {
        return apiService.createPermitReport(permitReport)
    }
}