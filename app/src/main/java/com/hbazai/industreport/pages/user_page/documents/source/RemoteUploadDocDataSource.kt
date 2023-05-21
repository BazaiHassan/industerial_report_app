package com.hbazai.industreport.pages.user_page.documents.source

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.user_page.documents.data.RequestUploadDocument
import com.hbazai.industreport.pages.user_page.documents.data.ResponseShowDocs
import io.reactivex.Single
import okhttp3.MultipartBody

class RemoteUploadDocDataSource(private val apiService: ApiService):UploadDocDataSource {
    override fun uploadDocument(file: MultipartBody.Part): Single<ResponseUpload> {
        return apiService.uploadDocument(file)
    }

    override fun createDocItem(requestUploadDocument: RequestUploadDocument): Single<ResponseCreateReport> {
        return apiService.createDocumentItem(requestUploadDocument)
    }

    override fun showDocs(): Single<List<ResponseShowDocs>> {
        return apiService.showDocs()
    }
}