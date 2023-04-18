package com.hbazai.industreport.pages.search_page.dataSource

import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import io.reactivex.Single

interface SearchReportsDataSource {
    fun searchReports(
        keyword: String,
        startDate: String,
        endDate: String
    ): Single<List<ResponseSearch>>
}