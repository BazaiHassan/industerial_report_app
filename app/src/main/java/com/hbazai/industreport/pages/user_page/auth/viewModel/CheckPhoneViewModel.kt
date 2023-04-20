package com.hbazai.industreport.pages.user_page.auth.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import com.hbazai.industreport.pages.user_page.auth.repository.AuthRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CheckPhoneViewModel(private val authRepository: AuthRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val checkPhoneLiveData = MutableLiveData<ResponseSendOTP>()

    fun checkPhoneNumber(requestPhoneNumber: RequestPhoneNumber){
        authRepository.checkPhoneNumber(requestPhoneNumber).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseSendOTP>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseSendOTP) {
                    checkPhoneLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CHECK_PHONE", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}