package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import io.reactivex.Single
import okhttp3.MultipartBody

class RemoteUploadReportImageDataSource(private val apiService: ApiService):UploadReportImageDataSource {
    override fun uploadReportImage(image: MultipartBody.Part): Single<ResponseUpload> {
        return apiService.uploadReportImage(image)
    }
}