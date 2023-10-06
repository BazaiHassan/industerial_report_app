package com.hbazai.industreport.pages.user_page.auth.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCheckInviteCode
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseCreateGroup
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import com.hbazai.industreport.pages.user_page.auth.repository.AuthRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val authRepository: AuthRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val checkPhoneLiveData = MutableLiveData<ResponseSendOTP>()
    val createGroupLiveData = MutableLiveData<ResponseCreateGroup>()
    val checkInviteCodeLiveData = MutableLiveData<ResponseCheckInviteCode>()

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

    fun createGroup(requestCreateGroup: RequestCreateGroup){
        authRepository.createGroup(requestCreateGroup).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateGroup>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_GROUP", "onError:${e.message} ")
                }

                override fun onSuccess(t: ResponseCreateGroup) {
                    createGroupLiveData.value = t
                }

            })
    }

    fun checkInviteCode(requestCheckInviteCode: RequestCheckInviteCode){
        authRepository.checkInviteCode(requestCheckInviteCode).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<ResponseCheckInviteCode>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CHECK_INVITE_CODE", "onError:${e.message} ")
                }

                override fun onSuccess(t: ResponseCheckInviteCode) {
                    checkInviteCodeLiveData.value = t
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}