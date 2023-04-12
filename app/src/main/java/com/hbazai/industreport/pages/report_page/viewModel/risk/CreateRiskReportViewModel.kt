package com.hbazai.industreport.pages.report_page.viewModel.risk

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.repository.risk.CreateRiskReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreateRiskReportViewModel(private val createRiskReportRepository: CreateRiskReportRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val createRiskReportLiveData = MutableLiveData<ResponseCreateReport>()
    fun createRiskReport(riskReport:RequestCreateRiskReport){
        createRiskReportRepository.createRiskReport(riskReport).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createRiskReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_RISK_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}