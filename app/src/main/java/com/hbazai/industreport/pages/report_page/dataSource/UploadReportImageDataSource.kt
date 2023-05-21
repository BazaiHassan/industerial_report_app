package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import io.reactivex.Single
import okhttp3.MultipartBody

interface UploadReportImageDataSource {

    fun uploadReportImage(image : MultipartBody.Part):Single<ResponseUpload>
}