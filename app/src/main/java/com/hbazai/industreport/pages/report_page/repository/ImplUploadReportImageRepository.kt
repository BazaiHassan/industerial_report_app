package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.report_page.dataSource.UploadReportImageDataSource
import io.reactivex.Single
import okhttp3.MultipartBody

class ImplUploadReportImageRepository(private val uploadReportImageDataSource: UploadReportImageDataSource):UploadReportImageRepository {
    override fun uploadReportImage(
        image: MultipartBody.Part
    ): Single<ResponseUpload> {
        return uploadReportImageDataSource.uploadReportImage(image)
    }
}