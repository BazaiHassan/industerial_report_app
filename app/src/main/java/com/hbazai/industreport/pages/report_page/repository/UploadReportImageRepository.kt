package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import io.reactivex.Single
import okhttp3.MultipartBody

interface UploadReportImageRepository {
    fun uploadReportImage(image : MultipartBody.Part): Single<ResponseUpload>

}