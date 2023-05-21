package com.hbazai.industreport.pages.user_page.documents.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.user_page.documents.data.RequestUploadDocument
import com.hbazai.industreport.pages.user_page.documents.data.ResponseShowDocs
import io.reactivex.Single
import okhttp3.MultipartBody

interface UploadDocRepository {
    fun uploadDocument(file:MultipartBody.Part):Single<ResponseUpload>
    fun createDocItem(requestUploadDocument: RequestUploadDocument):Single<ResponseCreateReport>
    fun showDocs():Single<List<ResponseShowDocs>>

}