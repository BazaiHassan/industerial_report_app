package com.hbazai.industreport.pages.search_page.repository

import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import io.reactivex.Single

interface SearchReportsRepository {
    fun searchReports(
        keyword: String,
        startDate: String,
        endDate: String
    ):Single<List<ResponseSearch>>
}