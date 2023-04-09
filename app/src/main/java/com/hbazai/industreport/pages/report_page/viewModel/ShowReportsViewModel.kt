package com.hbazai.industreport.pages.report_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.repository.ShowReportsRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowReportsViewModel(private val showReportsRepository: ShowReportsRepository):ViewModel() {

    val showReportsLiveData = MutableLiveData<List<ResponseShowReportsItem>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        showReportsRepository.showReports().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowReportsItem>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowReportsItem>) {
                    showReportsLiveData.value = t
                    Log.d("TAG_ShowReportsViewModel", "onSuccess:$t ")
                }

                override fun onError(e: Throwable) {
                    Log.e("TAG_ShowReportsViewModel", "onError: ${e.message}", )
                }

            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}