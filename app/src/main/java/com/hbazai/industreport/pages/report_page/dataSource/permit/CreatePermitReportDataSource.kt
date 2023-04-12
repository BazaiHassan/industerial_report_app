package com.hbazai.industreport.pages.report_page.dataSource.permit

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import io.reactivex.Single

interface CreatePermitReportDataSource {
    fun createPermitReport(permitReport:RequestCreatePermitReport):Single<ResponseCreateReport>
}