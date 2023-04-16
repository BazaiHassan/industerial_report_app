package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUploadImage
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class RemoteUploadReportImageDataSource(private val apiService: ApiService):UploadReportImageDataSource {
    override fun uploadReportImage(image: MultipartBody.Part, description:RequestBody): Single<ResponseUploadImage> {
        return apiService.uploadReportImage(image, description)
    }
}