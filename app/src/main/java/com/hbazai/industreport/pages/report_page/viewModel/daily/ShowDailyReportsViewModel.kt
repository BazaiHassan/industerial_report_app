package com.hbazai.industreport.pages.report_page.viewModel.daily

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.repository.daily.ShowDailyReportsRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowDailyReportsViewModel(private val showReportsRepository: ShowDailyReportsRepository):ViewModel() {

    val showReportsLiveData = MutableLiveData<List<ResponseShowReportsItem>>()
    private val compositeDisposable = CompositeDisposable()


    init {
        showReportsRepository.showDailyReports().subscribeOn(Schedulers.io())
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