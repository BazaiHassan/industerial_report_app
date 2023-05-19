package com.hbazai.industreport.pages.report_page.viewModel.comment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.comments.CreateCommentModel
import com.hbazai.industreport.pages.report_page.dataModel.comments.ResponseComments
import com.hbazai.industreport.pages.report_page.repository.comment.CommentRepository
import com.hbazai.industreport.utils.SendReportToken
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CommentViewModel(private val commentRepository: CommentRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showCommentsLiveData = MutableLiveData<List<ResponseComments>>()
    val addCommentLiveData = MutableLiveData<ResponseCreateReport>()

    fun showComments(commentToken: SendReportToken){
        commentRepository.showComments(commentToken).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseComments>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseComments>) {
                    showCommentsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_SHOW_COMMENTS", "onError:${e.message} ")
                }

            })
    }

    fun addComment(createCommentModel: CreateCommentModel){
        commentRepository.addComment(createCommentModel).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    addCommentLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ADD_COMMENT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}