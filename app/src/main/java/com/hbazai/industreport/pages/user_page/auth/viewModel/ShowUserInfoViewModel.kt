package com.hbazai.industreport.pages.user_page.auth.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseUserInfo
import com.hbazai.industreport.pages.user_page.auth.repository.ShowUserInfoRepository
import com.hbazai.industreport.utils.SendToken
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowUserInfoViewModel(private val showUserInfoRepository: ShowUserInfoRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showUserInfoLiveData = MutableLiveData<ResponseUserInfo>()

    fun showUserInfo(sendToken: SendToken){
        showUserInfoRepository.showUserInf(sendToken).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseUserInfo>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseUserInfo) {
                    showUserInfoLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SHOW_USER_INFO", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}