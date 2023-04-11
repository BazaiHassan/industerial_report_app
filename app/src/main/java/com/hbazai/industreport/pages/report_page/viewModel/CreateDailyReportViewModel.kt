package com.hbazai.industreport.pages.report_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.repository.CreateDailyReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreateDailyReportViewModel(private val createDailyReportRepository: CreateDailyReportRepository):ViewModel() {
    val createReportLiveData = MutableLiveData<ResponseCreateReport>()
    private val compositeDisposable = CompositeDisposable()

    fun createReport(dailyReport: RequestCreateDailyReport){
        createDailyReportRepository.createDailyReport(dailyReport).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_DAILY_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}