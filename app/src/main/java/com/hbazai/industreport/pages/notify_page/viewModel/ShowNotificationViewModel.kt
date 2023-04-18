package com.hbazai.industreport.pages.notify_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import com.hbazai.industreport.pages.notify_page.repository.ShowNotificationRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowNotificationViewModel(private val showNotificationRepository: ShowNotificationRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showNotificationLiveData = MutableLiveData<List<ResponseShowNotificationItem>>()

    init {
        showNotificationRepository.showNotification().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowNotificationItem>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowNotificationItem>) {
                    showNotificationLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SHOW_NOTIFICATION", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}