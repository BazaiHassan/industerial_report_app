package com.hbazai.industreport.pages.user_page.documents.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.user_page.documents.data.RequestUploadDocument
import com.hbazai.industreport.pages.user_page.documents.data.ResponseShowDocs
import com.hbazai.industreport.pages.user_page.documents.repository.UploadDocRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class UploadDocViewModel(private val uploadDocRepository: UploadDocRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val uploadDocLiveData = MutableLiveData<ResponseUpload>()
    val createDocLiveData = MutableLiveData<ResponseCreateReport>()
    val showDocsLiveData = MutableLiveData<List<ResponseShowDocs>>()

    fun uploadDocument(file: MultipartBody.Part) {
        uploadDocRepository.uploadDocument(file).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseUpload> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseUpload) {
                    uploadDocLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_UPLOAD_DOC", "onError:${e.message} ")
                }

            })
    }

    fun createDocument(requestUploadDocument: RequestUploadDocument) {
        uploadDocRepository.createDocItem(requestUploadDocument).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createDocLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_CREATE_DOC", "onError:${e.message} ")
                }

            })
    }

    fun showDocs(){
        uploadDocRepository.showDocs().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowDocs>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowDocs>) {
                    showDocsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_SHOW_DOCS", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}