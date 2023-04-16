package com.hbazai.industreport.pages.report_page.dataSource

import com.hbazai.industreport.pages.report_page.dataModel.ResponseUploadImage
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

interface UploadReportImageDataSource {

    fun uploadReportImage(image : MultipartBody.Part, description:RequestBody):Single<ResponseUploadImage>
}