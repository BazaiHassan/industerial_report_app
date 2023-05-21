package com.hbazai.industreport.pages.report_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUpload
import com.hbazai.industreport.pages.report_page.repository.UploadReportImageRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class UploadReportImageViewModel(private val uploadReportImageRepository: UploadReportImageRepository):ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val uploadReportImageLiveData = MutableLiveData<ResponseUpload>()
    fun uploadReportImage(image:MultipartBody.Part){
        uploadReportImageRepository.uploadReportImage(image).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseUpload>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseUpload) {
                    uploadReportImageLiveData.value = t
                    Log.d("TAG_RESPONSE_UPLOAD_IMAGE_REPORT", "onError:${t.message} ")
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_UPLOAD_IMAGE_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}