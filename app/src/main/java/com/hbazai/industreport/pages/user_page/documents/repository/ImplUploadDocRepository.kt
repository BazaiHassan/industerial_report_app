package com.hbazai.industreport.pages.user_page.documents.repository

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.user_page.documents.data.RequestUploadDocument
import com.hbazai.industreport.pages.user_page.documents.data.ResponseShowDocs
import com.hbazai.industreport.pages.user_page.documents.source.UploadDocDataSource
import io.reactivex.Single
import okhttp3.MultipartBody

class ImplUploadDocRepository(private val uploadDocDataSource: UploadDocDataSource):UploadDocRepository {
    override fun uploadDocument(file: MultipartBody.Part): Single<ResponseUpload> {
        return uploadDocDataSource.uploadDocument(file)
    }

    override fun createDocItem(requestUploadDocument: RequestUploadDocument): Single<ResponseCreateReport> {
        return uploadDocDataSource.createDocItem(requestUploadDocument)
    }

    override fun showDocs(): Single<List<ResponseShowDocs>> {
        return uploadDocDataSource.showDocs()
    }
}