package com.hbazai.industreport.pages.search_page.repository

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import com.hbazai.industreport.pages.search_page.dataSource.SearchReportsDataSource
import com.hbazai.industreport.pages.search_page.repository.SearchReportsRepository
import io.reactivex.Single

class ImplSearchReportsRepository(private val searchReportsDataSource: SearchReportsDataSource): SearchReportsRepository {
    override fun searchReports(
        keyword: String,
        startDate: String,
        endDate: String
    ): Single<List<ResponseSearch>> {
        return searchReportsDataSource.searchReports(keyword, startDate, endDate)
    }
}