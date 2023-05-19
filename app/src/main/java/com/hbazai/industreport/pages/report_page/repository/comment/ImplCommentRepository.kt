package com.hbazai.industreport.pages.report_page.repository.comment

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
import com.hbazai.industreport.pages.report_page.dataModel.comments.ResponseComments
import com.hbazai.industreport.pages.report_page.dataSource.comment.CommentDataSource
import com.hbazai.industreport.utils.SendReportToken
import io.reactivex.Single

class ImplCommentRepository(private val commentDataSource: CommentDataSource):CommentRepository {
    override fun showComments(commentToken: SendReportToken): Single<List<ResponseComments>> {
        return commentDataSource.showComments(commentToken)
    }

    override fun addComment(createCommentModel: CreateCommentModel): Single<ResponseCreateReport> {
        return commentDataSource.addComment(createCommentModel)
    }
}