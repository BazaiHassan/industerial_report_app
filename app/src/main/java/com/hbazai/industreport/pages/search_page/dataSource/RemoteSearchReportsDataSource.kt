package com.hbazai.industreport.pages.search_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import io.reactivex.Single

class RemoteSearchReportsDataSource(private val apiService: ApiService):SearchReportsDataSource {
    override fun searchReports(
        keyword: String,
        startDate: String,
        endDate: String
    ): Single<List<ResponseSearch>> {
        return apiService.searchReports(keyword, startDate, endDate)
    }
}