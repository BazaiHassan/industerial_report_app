package com.hbazai.industreport.pages.report_page.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseUploadImage
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UploadReportImageRepository {
    fun uploadReportImage(image : MultipartBody.Part, description: RequestBody): Single<ResponseUploadImage>

}