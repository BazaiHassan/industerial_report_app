package com.hbazai.industreport.pages.notify_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.notify_page.repository.CreateNotificationRepository
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreateNotificationViewModel(private val createNotificationRepository: CreateNotificationRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val createNotificationLiveData = MutableLiveData<ResponseCreateReport>()
    fun createNotification(requestCreateNotification: RequestCreateNotification){
        createNotificationRepository.createNotification(requestCreateNotification).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createNotificationLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_NOTIFICATION", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}