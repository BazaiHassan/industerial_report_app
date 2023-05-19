package com.hbazai.industreport.pages.report_page.dataSource.comment

import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
import com.hbazai.industreport.pages.report_page.dataModel.comments.ResponseComments
import com.hbazai.industreport.utils.SendReportToken
import io.reactivex.Single

interface CommentDataSource {
    fun showComments(commentToken: SendReportToken):Single<List<ResponseComments>>
    fun addComment(createCommentModel: CreateCommentModel):Single<ResponseCreateReport>
}