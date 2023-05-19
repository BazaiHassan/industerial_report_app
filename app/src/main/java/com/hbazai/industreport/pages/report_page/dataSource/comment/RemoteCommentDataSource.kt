package com.hbazai.industreport.pages.report_page.dataSource.comment

import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
import com.hbazai.industreport.pages.report_page.dataModel.comments.ResponseComments
import com.hbazai.industreport.utils.SendReportToken
import io.reactivex.Single

class RemoteCommentDataSource(private val apiService: ApiService):CommentDataSource {
    override fun showComments(commentToken: SendReportToken): Single<List<ResponseComments>> {
        return apiService.showComments(commentToken)
    }

    override fun addComment(createCommentModel: CreateCommentModel): Single<ResponseCreateReport> {
        return apiService.addComment(createCommentModel)
    }
}