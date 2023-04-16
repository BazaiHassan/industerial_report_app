package com.hbazai.industreport.pages.report_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUploadImage
import com.hbazai.industreport.pages.report_page.repository.UploadReportImageRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadReportImageViewModel(private val uploadReportImageRepository: UploadReportImageRepository):ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val uploadReportImageLiveData = MutableLiveData<ResponseUploadImage>()
    fun uploadReportImage(image:MultipartBody.Part, description:RequestBody){
        uploadReportImageRepository.uploadReportImage(image, description).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseUploadImage>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseUploadImage) {
                    uploadReportImageLiveData.value = t
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